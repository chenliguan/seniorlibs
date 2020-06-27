package com.seniorlibs.thread.threadpool;

import com.seniorlibs.baselib.utils.LogUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: 陈李冠
 * Version: 1.28
 * Date: 2020/3/8.
 * Mender:
 * Modify:
 * Description: 测试线程池
 */
public class ThreadPoolManagerTest {

    /**
     * 为什么线程池容量不够抛出异常rejectedExecution?
     */
    public static void textRejectedExecution() {
        int CORE_POOL_SIZE = 1; // 核心线程数:5
        int MAXIMUM_POOL_SIZE = 1; // 最大线程数量:10
        int KEEP_ALIVE = 1;// 闲置回收:1

        // 异步任务队列（10）
        final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<Runnable>(10);
        // 线程工厂
        ThreadFactory sThreadFactory = new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                String name = "Thread #" + mCount.getAndIncrement();
                System.out.println(name);
                return new Thread(r, name);
            }
        };

        // 线程池
        Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS,
                sPoolWorkQueue, sThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

        // 如果当前线程池中的核心线程数量大于corePoolSize，缓冲队列sPoolWorkQueue已满，并且线程池中的数量等于maximumPoolSize，新提交任务由Handler处理。
        // 否则抛出：RejectedExecutionException
        for (int i = 0; i < 30; i++) {
            // sPoolWorkQueue:30 > 10 + 10
            THREAD_POOL_EXECUTOR.execute(new MyTask(i));
        }
    }

    static class MyTask implements Runnable {

        private int num;

        public MyTask(int num) {
            this.num = num;
        }

        @Override
        public void run() {
//            // 解决方案：1、防止线程阻塞，注释睡眠，使用此方法
//            //          2、执行线程数量尽可能小于缓冲队列数目

            try {
                LogUtils.d(ThreadPoolManagerActivity.TAG, "线程序号：" + num + " Thread.currentThread().getName()：" + Thread.currentThread().getName());
                if (num == 0) {
                    Thread.sleep(10000);
                } else {
                    Thread.sleep(1000); // 线程阻塞时，线程执行缓慢，容易抛出：RejectedExecutionException
                }
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.e(ThreadPoolManagerActivity.TAG, "e.printStackTrace()：" + e.toString());
            }
        }
    }
}
