package com.seniorlibs.plugin;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Arrays;
import java.util.List;

public class LifecycleClassVisitor extends ClassVisitor {

    private String className;
    private String superName;
    private String[] interfaces;

    public LifecycleClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.className = name;
        this.superName = superName;
        this.interfaces = interfaces;

        if (superName.equals("android/support/v7/app/AppCompatActivity")
                || superName.equals("androidx/appcompat/app/AppCompatActivity")) {
            System.out.println("ClassVisitor visit name-------" + name + ", superName is " + superName);
        }
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);

        System.out.println("ClassVisitor visitMethod name-------" + name + " desc：" + desc + ", superName is " + superName);

        List<String> interList = Arrays.asList(interfaces);
        if(name.equals("onClick") && desc.equals("(Landroid/view/View;)V")
                && interList.contains("android/view/View$OnClickListener")){
            System.out.println("ClassVisitor visitMethod onClick-onClick-onClick-onClick-interfaces：" + interList.get(0));
        }

        // 1.crash错误：Caused by: java.lang.ClassNotFoundException: Didn't find class "androidx.appcompat.R$drawable"
        if (superName.equals("android/support/v7/app/AppCompatActivity")
                || superName.equals("androidx/appcompat/app/AppCompatActivity")) {
            if (name.startsWith("onCreate")) {
                // 处理onCreate()方法
                return new LifecycleMethodVisitor(mv, className, name);
            }
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}