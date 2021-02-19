package com.read.kotlinlib.jvm;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/2/18.
 * Mender:
 * Modify:
 * Description: 验证方法区中的静态变量引用的对象作为 GC Root
 */
public class GCRootStaticVariable {

    private static String TAG = "GCRoot";

    private static int _10MB = 10 * 1024 * 1024;
    private byte[] memory;
    private static GCRootStaticVariable staticVariable;

    public GCRootStaticVariable(int size) {
        memory = new byte[size];
    }

    public static void main(String[] args) {
        System.out.println(TAG + " start");
        printMemory();

        GCRootStaticVariable g = new GCRootStaticVariable(4 * _10MB);
        g.staticVariable = new GCRootStaticVariable(8 * _10MB);
        // Set g to null, the memory of this object can be reclaimed when calling GC
        g = null;

        System.gc();
        System.out.println(TAG + " GC end");
        printMemory();
    }

    /**
     * Print out the remaining space and total space of the current JVM
     */
    public static void printMemory() {
        System.out.print(TAG + " free is " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + " M, ");
        System.out.println(TAG + " total is " + Runtime.getRuntime().totalMemory() / 1024 / 1024 + " M, ");
    }

//    GCRoot start
//    GCRoot free is 240 M, GCRoot total is 243 M,
//    GCRoot GC end
//    GCRoot free is 161 M, GCRoot total is 243 M,
//    可以看出：
//    程序刚开始运行时内存为 242 M，并分别创建了 g 对象（40M），同时也初始化 g 对象内部的静态变量 staticVariable 对象（80M）。
//    当调用 GC 时，只有 g 对象的 40M 被 GC 回收掉，而静态变量 staticVariable 作为 GC Root，它引用的 80M 并不会被回收。
}
