package com.seniorlibs.plugin;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class StatMethodVisitor extends MethodVisitor {

    private String className;
    private String methodName;

    public StatMethodVisitor(MethodVisitor methodVisitor, String className, String methodName) {
        super(Opcodes.ASM5, methodVisitor);
        this.className = className;
        this.methodName = methodName;
    }


    /**
     * 方法执行前插入
     */
    @Override
    public void visitCode() {
        super.visitCode();
        System.out.println("MethodVisitor visitCode + trackViewOnClick------");

        mv.visitLdcInsn("+\u2014\u2014trackViewOnClick\u2014\u2014+");
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/seniorlibs/baselib/utils/AutoTrackHelper",
                "trackViewOnClick", "(Ljava/lang/String;)V", false);
    }
}