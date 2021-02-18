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

    public static void main() throws Exception {
        System.out.println(TAG + " 开始前内存情况:");
        printMemory();
        AsyncTask at = new AsyncTask(new GCRootThread());
        Thread thread = new Thread(at);
        thread.start();
        System.gc();
        System.out.println(TAG + " main方法执行完毕，完成GC");
        printMemory();

        thread.join();
        at = null;
        System.gc();
        System.out.println(TAG + " 线程代码执行完毕，完成GC");
        printMemory();
    }

    /**
     * 打印出当前JVM剩余空间和总的空间大小
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
}
