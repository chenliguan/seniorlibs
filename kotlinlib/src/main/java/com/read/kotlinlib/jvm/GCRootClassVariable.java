package com.read.kotlinlib.jvm;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/2/18.
 * Mender:
 * Modify:
 * Description: 测试全局成员变量不可作为 GC Root
 */
public class GCRootClassVariable {

    private static String TAG = "GCRoot";

    private static int _10MB = 10 * 1024 * 1024;
    private byte[] memory;
    private GCRootClassVariable classVariable;

    public GCRootClassVariable(int size) {
        memory = new byte[size];
    }

    public static void main(String[] args) {
        System.out.println(TAG + " start");
        printMemory();

        GCRootClassVariable g = new GCRootClassVariable(4 * _10MB);
        g.classVariable = new GCRootClassVariable(8 * _10MB);
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

//    javac GCRootStaticVariable.java
//    java -Xms250m GCRootStaticVariable

//    GCRoot start
//    GCRoot free is 240 M, GCRoot total is 243 M,
//    GCRoot GC end
//    GCRoot free is 241 M, GCRoot total is 243 M,
//    可以看出：
//    当调用 GC 时，因为 g 已经置为 null，因此 g 中的全局变量 classVariable 此时也不再被 GC Root 所引用。
//    所以最后 g(40M) 和 classVariable(80M) 都会被回收掉。这也表明全局变量同静态变量不同，它不会被当作 GC Root。
}
