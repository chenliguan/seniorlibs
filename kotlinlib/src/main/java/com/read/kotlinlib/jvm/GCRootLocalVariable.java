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

    public static void main(String[] args) {
        System.out.println(TAG + " start");
        printMemory();

        method();

        System.gc();
        System.out.println(TAG + " method() end, GC 2 end");
        printMemory();
    }

    public static void method() {
        GCRootLocalVariable g = new GCRootLocalVariable();
        System.gc();
        System.out.println(TAG + " GC 1 end");
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
//    GCRoot GC 1 end
//    GCRoot free is 161 M, GCRoot total is 243 M,
//    GCRoot method() end，GC 2 end
//    GCRoot free is 241 M, GCRoot total is 243 M,
//    可以看出：
//    当第一次 GC 时，g 作为局部变量，引用了 new 出的对象（80M），并且它作为 GC Roots，在 GC 后并不会被 GC 回收。
//    当第二次 GC：method() 方法执行完后，局部变量 g 跟随方法消失，不再有引用类型指向该 80M 对象，所以第二次 GC 后此 80M 也会被回收。
}
