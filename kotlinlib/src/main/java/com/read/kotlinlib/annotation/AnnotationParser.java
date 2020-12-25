package com.read.kotlinlib.annotation;

import java.lang.reflect.Method;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/12/25.
 * Mender:
 * Modify:
 * Description: 注解解析
 */
public class AnnotationParser {

    public final static String TAG = "AnnotationParser";

    public static void main() {
        parser();
        testInherited();
    }

    /**
     * 解析注解
     */
    public static void parser()  {
        String clazz = "com.read.kotlinlib.annotation.AnnotationDemo";
        Method[] demoMethod = new Method[0];
        try {
            demoMethod = AnnotationParser.class.getClassLoader().loadClass(clazz).getMethods();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (Method method : demoMethod) {
            if (method.isAnnotationPresent(AuthorAnno.class)) {
                AuthorAnno authorInfo = method.getAnnotation(AuthorAnno.class);
                System.out.println(TAG + " method: " + method);
                System.out.println(TAG + " name= " + authorInfo.name() + " , website= " + authorInfo.website() + " , revision= " + authorInfo.revision());
            }
        }

//        ethod: public void com.read.kotlinlib.annotation.AnnotationDemo.demo()
//        ame= chenDemo , website= chenDemo.com , revision= 2
//        ethod: public static void com.read.kotlinlib.annotation.AnnotationDemo.main()
//        ame= chen , website= chen.com , revision= 1
    }

    /**
     * 测试Inherited注解
     */
    public static void testInherited() {
        System.out.println(TAG + " " + MyInherited.class.getAnnotation(MyInherited.class));
        System.out.println(TAG + " " + MyInheritedParent.class.getAnnotation(MyInherited.class));
        System.out.println(TAG + " " + MyInheritedB.class.getAnnotation(MyInherited.class));

//        AnnotationParser null
//        AnnotationParser @com.read.kotlinlib.annotation.MyInherited()
//        AnnotationParser @com.read.kotlinlib.annotation.MyInherited()
    }
}
