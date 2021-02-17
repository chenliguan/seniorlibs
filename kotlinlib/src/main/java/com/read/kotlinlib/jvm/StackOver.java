package com.read.kotlinlib.jvm;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/2/17.
 * Mender:
 * Modify:
 * Description: 栈溢出
 */
public class StackOver {

    private int number;

    public static void main() {
        StackOver so = new StackOver();
        try {
            so.method();
        } catch (StackOverflowError e) {
            System.out.println("栈容量已经溢出!");
        }
    }

    public void method() {
        number++;
        method();
    }
}