package com.seniorlibs.systrace.methodvisitor;

import com.seniorlibs.systrace.TraceBuildConstants;

import org.objectweb.asm.MethodVisitor;

/**
 * Author: chenliguan
 * Version: 1.0.4
 * Date: 2019/8/3
 * Mender:
 * Modify:
 * Description: 在每个方法前后插入Trace方法
 */
public class TraceMethodAdapter extends MethodAdapter {

    public TraceMethodAdapter(int api, MethodVisitor mv, int access, String name, String desc,
                              String className, boolean isMethodBeatClass) {
        super(api, mv, access, name, desc, className, isMethodBeatClass);
    }

    /**
     * Insert bytecode at the entry of the function
     */
    @Override
    protected void onMethodEnter() {
        if (mTraceMethod != null) {
            traceMethodCount.incrementAndGet();
            String sectionName = methodName;
            int length = sectionName.length();
            if (length > TraceBuildConstants.MAX_SECTION_NAME_LEN) {
                // gonna take out the parameters
                int parmIndex = sectionName.indexOf('(');
                sectionName = sectionName.substring(0, parmIndex);
                // If it's still bigger, cut it
                length = sectionName.length();
                if (length > TraceBuildConstants.MAX_SECTION_NAME_LEN) {
                    sectionName = sectionName.substring(length - TraceBuildConstants.MAX_SECTION_NAME_LEN);
                }
            }
            mv.visitLdcInsn(sectionName);
            mv.visitMethodInsn(INVOKESTATIC, TraceBuildConstants.MATRIX_TRACE_METHOD_BEAT_CLASS, "i", "(Ljava/lang/String;)V", false);
        }
    }

    /**
     * Insert bytecode at the exit of the function
     *
     * @param opcode
     */
    @Override
    protected void onMethodExit(int opcode) {
        //if (isMethodBeatClass && ("<clinit>").equals(name)) {
        //    StringBuffer stringBuffer = new StringBuffer();
        //
        //    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        //    mv.visitLdcInsn(stringBuffer.toString());
        //    mv.visitFieldInsn(Opcodes.PUTSTATIC, className, TraceBuildConstants.MATRIX_TRACE_APPLICATION_CREATE_FILED, "Ljava/lang/String;");
        //}
        if (mTraceMethod != null) {

            traceMethodCount.incrementAndGet();
            //mv.visitLdcInsn(traceMethod.id);
            mv.visitMethodInsn(INVOKESTATIC, TraceBuildConstants.MATRIX_TRACE_METHOD_BEAT_CLASS, "o", "()V", false);
        }
    }
}
