package com.seniorlibs.systrace.methodvisitor;

import com.seniorlibs.systrace.item.TraceMethod;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: chenliguan
 * Version: 1.0.4
 * Date: 2019/8/3
 * Mender:
 * Modify:
 * Description: AdviceAdapter子类
 */
public class MethodAdapter extends AdviceAdapter {

    protected static AtomicInteger traceMethodCount = new AtomicInteger();
    protected String methodName;
    protected String name;
    protected String className;
    protected boolean isMethodBeatClass;
    protected TraceMethod mTraceMethod;
    protected HashMap<String, TraceMethod> mCollectedMethodMap;

    public MethodAdapter(int api, MethodVisitor mv, int access, String name, String desc,
                         String className, boolean isMethodBeatClass, HashMap<String, TraceMethod> collectedMethodMap) {
        super(api, mv, access, name, desc);
        TraceMethod traceMethod = TraceMethod.create(0, access, className, name, desc);
        this.methodName = traceMethod.getMethodName();
        this.isMethodBeatClass = isMethodBeatClass;
        this.className = className;
        this.name = name;
        this.mTraceMethod = traceMethod;
        this.mCollectedMethodMap = collectedMethodMap;
    }
}
