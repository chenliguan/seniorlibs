package com.read.kotlinlib.reflect;

/**
 * Author: 陈李冠
 * Time: 2021/4/22
 * Description: chen
 */
public class User {

    private final String name = "Bob";

    public static String staticName = "UserJJJJ";

    private final Student student = new Student();

    public String getName() {
        return name;
    }

    public Student getStudent() {
        return student;
    }

    static class Student {
    }
}
