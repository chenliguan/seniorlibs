package com.read.kotlinlib.jvm;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/2/18.
 * Mender:
 * Modify:
 * Description: 验证活跃线程作为 GC Root
 */
public class GCRootThread {

    private static String TAG = "GCRoot";

    private int _10MB = 10 * 1024 * 1024;
    private byte[] memory = new byte[8 * _10MB];

    public static void main(String[] args) throws Exception {
        System.out.println(TAG + " start");
        printMemory();
        AsyncTask at = new AsyncTask(new GCRootThread());
        Thread thread = new Thread(at);
        thread.start();

        System.gc();
        System.out.println(TAG + " main() end, GC end");
        printMemory();

        thread.join();
        at = null;
        System.gc();
        System.out.println(TAG + " thread end, GC end");
        printMemory();
    }

    /**
     * Print out the remaining space and total space of the current JVM
     */
    public static void printMemory() {
        System.out.print(TAG + " free is " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + " M, ");
        System.out.println(TAG + " total is " + Runtime.getRuntime().totalMemory() / 1024 / 1024 + " M, ");
    }

    private static class AsyncTask implements Runnable {
        private GCRootThread gcRootThread;

        public AsyncTask(GCRootThread gcRootThread) {
            this.gcRootThread = gcRootThread;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
    }

//    GCRoot start
//    GCRoot free is 240 M, GCRoot total is 243 M,
//    GCRoot main() end, GC end
//    GCRoot free is 161 M, GCRoot total is 243 M,
//    GCRoot thread end, GC end
//    GCRoot free is 241 M, GCRoot total is 243 M,
//    可以看出：
//    程序刚开始时是 242M 内存，当调用第一次 GC 时线程并没有执行结束，并且它作为 GC Root，所以它所引用的 80M 内存并不会被 GC 回收掉。
//    thread.join() 保证线程结束再调用后续代码。
//    所以当调用第二次 GC 前，线程已经执行完毕并被置为 null，这时线程已经被销毁，所以之前线程所引用的 80M 此时会被 GC 回收掉。
}
