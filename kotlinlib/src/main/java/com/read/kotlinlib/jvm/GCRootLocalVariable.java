package com.read.kotlinlib.jvm;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/2/18.
 * Mender:
 * Modify:
 * Description: 验证虚拟机栈（栈帧中的局部变量）中引用的对象作为 GC Root
 */
public class GCRootLocalVariable {

    private static String TAG = "GCRoot";

    private int _10MB = 10 * 1024 * 1024;
    private byte[] memory = new byte[8 * _10MB];

    public static void main() {
        System.out.println(TAG + " 开始时:");
        printMemory();
        method();
        System.gc();
        System.out.println(TAG + " 第二次GC完成");
        printMemory();
    }

    public static void method() {
        GCRootLocalVariable g = new GCRootLocalVariable();
        System.gc();
        System.out.println(TAG + " 第一次GC完成");
        printMemory();
    }

    /**
     * 打印出当前JVM剩余空间和总的空间大小
     */
    public static void printMemory() {
        System.out.print(TAG + " free is " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + " M, ");
        System.out.println(TAG + " total is " + Runtime.getRuntime().totalMemory() / 1024 / 1024 + " M, ");
    }
}
