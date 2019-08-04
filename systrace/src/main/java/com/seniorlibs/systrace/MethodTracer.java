/*
 * Tencent is pleased to support the open source community by making wechat-matrix available.
 * Copyright (C) 2018 THL A29 Limited, a Tencent company. All rights reserved.
 * Licensed under the BSD 3-Clause License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.seniorlibs.systrace;

import com.seniorlibs.systrace.item.TraceMethod;
import com.seniorlibs.systrace.methodvisitor.MethodVisitorFactory;
import com.seniorlibs.systrace.methodvisitor.TraceMethodAdapter;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Created by caichongyang on 2017/6/4.
 * <p>
 * This class hooks all collected methods in oder to trace method in/out.
 * </p>
 */
public class MethodTracer {

    private static final String TAG = "Matrix.MethodTracer";
    private final TraceBuildConfig mTraceConfig;
    private final HashMap<String, TraceMethod> mCollectedMethodMap;
    private final HashMap<String, String> mCollectedClassExtendMap;

    MethodTracer(TraceBuildConfig config, HashMap<String, TraceMethod> collectedMap, HashMap<String, String> collectedClassExtendMap) {
        this.mTraceConfig = config;
        this.mCollectedClassExtendMap = collectedClassExtendMap;
        this.mCollectedMethodMap = collectedMap;
    }


    public void trace(Map<File, File> srcFolderList, Map<File, File> dependencyJarList) {
        traceMethodFromSrc(srcFolderList);
        traceMethodFromJar(dependencyJarList);
    }

    private void traceMethodFromSrc(Map<File, File> srcMap) {
        if (null != srcMap) {
            for (Map.Entry<File, File> entry : srcMap.entrySet()) {
                innerTraceMethodFromSrc(entry.getKey(), entry.getValue());
            }
        }
    }

    private void traceMethodFromJar(Map<File, File> dependencyMap) {
        if (null != dependencyMap) {
            for (Map.Entry<File, File> entry : dependencyMap.entrySet()) {
                innerTraceMethodFromJar(entry.getKey(), entry.getValue());
            }
        }
    }

    private void innerTraceMethodFromSrc(File input, File output) {

        ArrayList<File> classFileList = new ArrayList<>();
        if (input.isDirectory()) {
            listClassFiles(classFileList, input);
        } else {
            classFileList.add(input);
        }

        for (File classFile : classFileList) {
            InputStream is = null;
            FileOutputStream os = null;
            try {
                final String changedFileInputFullPath = classFile.getAbsolutePath();
                final File changedFileOutput = new File(changedFileInputFullPath.replace(input.getAbsolutePath(), output.getAbsolutePath()));
                if (!changedFileOutput.exists()) {
                    changedFileOutput.getParentFile().mkdirs();
                }
                changedFileOutput.createNewFile();

                if (mTraceConfig.isNeedTraceClass(classFile.getName())) {
                    is = new FileInputStream(classFile);

                    // 1.Handle class files
                    // (1) read the original bytecode of the Class file through ClassReader
                    ClassReader classReader = new ClassReader(is);
                    // (2)Use the ClassWriter class to modify it based on a different Visitor class
                    ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                    // (3)The tools used to access the class, making judgments about the class name and method name in visit() and visitMethod(),
                    // need to handle inserting bytecode into the ClassVisitor class
                    ClassVisitor classVisitor = new TraceClassAdapter(Opcodes.ASM5, classWriter);
                    // (4)Agree to modify according to the mark
                    classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES);
                    is.close();

                    // 2.replace
                    // (1)Get the modified class byte stream from the classWriter, and then override the original class file by writing to the stream
                    if (output.isDirectory()) {
                        os = new FileOutputStream(changedFileOutput);
                    } else {
                        os = new FileOutputStream(output);
                    }
                    os.write(classWriter.toByteArray());
                    os.close();
                } else {
                    Util.copyFileUsingStream(classFile, changedFileOutput);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                    os.close();
                } catch (Exception e) {
                    // ignore
                }
            }
        }
    }

    private void innerTraceMethodFromJar(File input, File output) {
        ZipOutputStream zipOutputStream = null;
        ZipFile zipFile = null;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(output));
            zipFile = new ZipFile(input);
            Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
            while (enumeration.hasMoreElements()) {
                ZipEntry zipEntry = enumeration.nextElement();
                String zipEntryName = zipEntry.getName();
                if (mTraceConfig.isNeedTraceClass(zipEntryName)) {
                    InputStream inputStream = zipFile.getInputStream(zipEntry);
                    ClassReader classReader = new ClassReader(inputStream);
                    ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                    ClassVisitor classVisitor = new TraceClassAdapter(Opcodes.ASM5, classWriter);
                    classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES);
                    byte[] data = classWriter.toByteArray();
                    InputStream byteArrayInputStream = new ByteArrayInputStream(data);
                    ZipEntry newZipEntry = new ZipEntry(zipEntryName);
                    Util.addZipEntry(zipOutputStream, newZipEntry, byteArrayInputStream);
                } else {
                    InputStream inputStream = zipFile.getInputStream(zipEntry);
                    ZipEntry newZipEntry = new ZipEntry(zipEntryName);
                    Util.addZipEntry(zipOutputStream, newZipEntry, inputStream);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "[traceMethodFromJar] err! %s", output.getAbsolutePath());
        } finally {
            try {
                if (zipOutputStream != null) {
                    zipOutputStream.finish();
                    zipOutputStream.flush();
                    zipOutputStream.close();
                }
                if (zipFile != null) {
                    zipFile.close();
                }
            } catch (Exception e) {
                Log.e(TAG, "close stream err!");
            }
        }
    }

    private void listClassFiles(ArrayList<File> classFiles, File folder) {
        File[] files = folder.listFiles();
        if (null == files) {
            Log.e(TAG, "[listClassFiles] files is null! %s", folder.getAbsolutePath());
            return;
        }
        for (File file : files) {
            if (file == null) {
                continue;
            }
            if (file.isDirectory()) {
                listClassFiles(classFiles, file);
            } else {
                if (null != file && file.isFile()) {
                    classFiles.add(file);
                }

            }
        }
    }

    private class TraceClassAdapter extends ClassVisitor {

        private String className;
        private boolean isABSClass = false;
        private boolean isMethodBeatClass = false;

        TraceClassAdapter(int i, ClassVisitor classVisitor) {
            super(i, classVisitor);
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            super.visit(version, access, name, signature, superName, interfaces);
            this.className = name;
            if ((access & Opcodes.ACC_ABSTRACT) > 0 || (access & Opcodes.ACC_INTERFACE) > 0) {
                this.isABSClass = true;
            }

            // 1.Judge class and method names
            if (mTraceConfig.isMethodBeatClass(className, mCollectedClassExtendMap)) {
                isMethodBeatClass = true;
            }
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc,
                                         String signature, String[] exceptions) {
            if (isABSClass) {
                return super.visitMethod(access, name, desc, signature, exceptions);
            } else {
                // 2.Insert bytecode at the entry and exit of the function

                MethodVisitor methodVisitor = cv.visitMethod(access, name, desc, signature, exceptions);
                // Parameter class type
                Class[] params = {int.class, MethodVisitor.class, int.class, String.class, String.class, String.class, boolean.class, HashMap.class};
                // Parameter type value
                Object[] values = {api, methodVisitor, access, name, desc, className, isMethodBeatClass, mCollectedMethodMap};
                Log.i(TAG, "params = "+params[0]+" values = "+values[0]);
                MethodVisitorFactory factory = new MethodVisitorFactory();
                return factory.createMethodVisitor(TraceMethodAdapter.class, params, values);

//                return new MethodTracer.TraceMethodAdapter(api, methodVisitor, access, name, desc, className, isMethodBeatClass);
//                return new MethodTracer.ConsumeMethodAdapter(api, methodVisitor, access, name, desc, className, isMethodBeatClass);
            }
        }

        @Override
        public void visitEnd() {
            super.visitEnd();
        }
    }
}
