package com.seniorlibs.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2019/9/1.
 * Mender:
 * Modify:
 * Description: 测试原子性Integer
 */
public class AtomicIntegerTest {

    public static void mainTest() {
//        testAtomicInteger(true);
        testAtomicInteger(false);
    }

    // 数量达到一定才容易看效果
    private static final int THREADS_COUNT = 10000;
    private static final int NUM = 100;
    private static int sCount = 0;
    private static AtomicInteger sAtomicCount = new AtomicInteger(0);

    private static void testAtomicInteger(final boolean isAtomicInteger) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < NUM; i++) {
                        try {
                            Thread.sleep(10);
                            if (isAtomicInteger) {
                                sAtomicCount.incrementAndGet();
                            } else {
                                sCount++;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            threads[i].start();
        }

        // 让调用join()的线程先执行
        for (int i = 0; i < THREADS_COUNT; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 再执行主线程
        System.out.println(isAtomicInteger ? "AtomicInteger：" + sAtomicCount.get() : "Int：" + sCount);
    }
}
