package com.seniorlibs.systrace.methodvisitor;

import java.lang.reflect.Constructor;

/**
 * Author: chenliguan
 * Version: 1.0.4
 * Date: 2019/8/3
 * Mender:
 * Modify:
 * Description: MethodVisitor Factory
 */
public class MethodVisitorFactory extends AbstractFactory {

    /**
     * 创建MethodVisitor工厂
     *
     * @param clz
     * @param classParam
     * @param paramValues
     * @param <T>
     * @return
     */
    public <T extends MethodAdapter> T createMethodVisitor(Class<T> clz, Class[] classParam, Object[] paramValues) {
        MethodAdapter adapter = null;
        try {
            // Get the class type based on the class path
            Class c = Class.forName(clz.getName());
            // Call its constructor to create an instance
            Constructor con = c.getConstructor(classParam);
            // Call the constructor and create the real
            adapter = (MethodAdapter) con.newInstance(paramValues);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return (T) adapter;
    }
}
