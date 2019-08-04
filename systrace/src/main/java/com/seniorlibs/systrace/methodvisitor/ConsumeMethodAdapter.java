package com.seniorlibs.systrace.methodvisitor;

import com.seniorlibs.systrace.item.TraceMethod;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.util.HashMap;

/**
 * Author: chenliguan
 * Version: 1.0.4
 * Date: 2019/8/3
 * Mender:
 * Modify:
 * Description: 统计方法耗时
 */
public class ConsumeMethodAdapter extends MethodAdapter {

    private int mTimeLocalIndex = 0;

    public ConsumeMethodAdapter(int api, MethodVisitor mv, int access, String name, String desc,
                                String className, boolean isMethodBeatClass, HashMap<String, TraceMethod> collectedMethodMap) {
        super(api, mv, access, name, desc, className, isMethodBeatClass, collectedMethodMap);
    }

    /**
     * Insert bytecode at the entry of the function
     */
    @Override
    protected void onMethodEnter() {
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        mTimeLocalIndex = newLocal(Type.LONG_TYPE);
        mv.visitVarInsn(LSTORE, mTimeLocalIndex);
    }

    /**
     * Insert bytecode at the exit of the function
     *
     * @param opcode
     */
    @Override
    protected void onMethodExit(int opcode) {
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        mv.visitVarInsn(LLOAD, mTimeLocalIndex);
        mv.visitInsn(LSUB);
        mv.visitVarInsn(LSTORE, mTimeLocalIndex);

        int stringBuilderIndex = newLocal(Type.getType("java/lang/StringBuilder"));
        mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
        mv.visitInsn(Opcodes.DUP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
        mv.visitVarInsn(Opcodes.ASTORE, stringBuilderIndex);
        mv.visitVarInsn(Opcodes.ALOAD, stringBuilderIndex);
        mv.visitLdcInsn(className + "." + methodName + " time:");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        mv.visitInsn(Opcodes.POP);
        mv.visitVarInsn(Opcodes.ALOAD, stringBuilderIndex);
        mv.visitVarInsn(Opcodes.LLOAD, mTimeLocalIndex);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;", false);
        mv.visitInsn(Opcodes.POP);
        mv.visitLdcInsn("Geek");
        mv.visitVarInsn(Opcodes.ALOAD, stringBuilderIndex);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        mv.visitInsn(Opcodes.POP);
    }
}
