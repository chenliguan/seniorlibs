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
 * Author: chen
 * Version: 1.28
 * Date: 2020/3/8.
 * Mender:
 * Modify:
 * Description: 测试线程池
 */
public class ThreadPoolManagerTest {

    public static final String TAG = "ThreadPoolManagerTest";

    /**
     * 为什么线程池容量不够抛出异常rejectedExecution?
     */
    public static void textRejectedExecution() {
        int CORE_POOL_SIZE = 2; // 核心线程数:5
        int MAXIMUM_POOL_SIZE = 2; // 最大线程数量:10
        int KEEP_ALIVE = 1;// 闲置回收:1

        // 异步任务队列（10）
        final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<Runnable>(1);
        // 线程工厂
        ThreadFactory sThreadFactory = new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                String name = "创建新线程 Thread name #" + mCount.getAndIncrement();
                LogUtils.d(ThreadPoolManagerActivity.TAG, name);
                return new Thread(r, name);
            }
        };

        // 线程池
        Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS,
                sPoolWorkQueue, sThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 5; i++) {
            // 调用execute()的线程是main。5 > 2 + 1
            THREAD_POOL_EXECUTOR.execute(new MyTask(i));
        }

        // new ThreadPoolExecutor.AbortPolicy()
        // 如果当前线程池中的核心线程数量大于corePoolSize，缓冲队列sPoolWorkQueue已满，并且线程池中的数量等于maximumPoolSize，新提交任务由Handler处理。
        // 否则抛出：RejectedExecutionException
        // 解决方案：1、防止线程阻塞，注释睡眠，使用此方法
        //          2、执行线程数量尽可能小于缓冲队列数目

        // new ThreadPoolExecutor.CallerRunsPolicy()
        // 总提交5个任务 核心线程数=最大线程数量=2 等待队列=1 因此：5-2-1=2,有2个线程被拒了,由提交任务(调用execute())的线程处理该任务。
//        创建新线程 Thread name #1
//        创建新线程 Thread name #2
//        任务序号：0 Thread.currentThread().getName()：创建新线程 Thread name #1
//        任务序号：3 Thread.currentThread().getName()：main
//        任务序号：4 Thread.currentThread().getName()：main
//        任务序号：2 Thread.currentThread().getName()：创建新线程 Thread name #1
//        任务序号：1 Thread.currentThread().getName()：创建新线程 Thread name #2
    }

    static class MyTask implements Runnable {

        private int num;

        public MyTask(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            try {
//                Thread.sleep(2000); // 线程阻塞时，线程执行缓慢，容易抛出：RejectedExecutionException

                LogUtils.d(ThreadPoolManagerActivity.TAG, "任务序号：" + num +
                        " Thread.currentThread().getName()：" + Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.e(ThreadPoolManagerActivity.TAG, "e.printStackTrace()：" + e.toString());
            }
        }
    }
}
