package com.read.kotlinlib.annotation;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/12/25.
 * Mender:
 * Modify:
 * Description: 自定义注解使用
 */
public class AnnotationDemo {

    @AuthorAnno(name = "chen", website = "chen.com", revision = 1)
    public static void main() {
        System.out.println("I am main method");
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @AuthorAnno(name = "chenDemo", website = "chenDemo.com", revision = 2)
    public void demo() {
        System.out.println("I am demo method");
    }
}
