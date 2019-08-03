package com.seniorlibs.systrace.methodvisitor;

/**
 * Author: chenliguan
 * Version: 1.0.4
 * Date: 2019/8/3
 * Mender:
 * Modify:
 * Description: MethodVisitor abstract Factory
 */
public abstract class AbstractFactory {

    /**
     * 创建MethodVisitor工厂
     *
     * @param clz
     * @param classParam
     * @param paramValues
     * @param <T>
     * @return
     */
    public abstract <T extends MethodAdapter> T createMethodVisitor(Class<T> clz, Class[] classParam, Object[] paramValues);
}
