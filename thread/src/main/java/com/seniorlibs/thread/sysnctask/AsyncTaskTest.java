package com.seniorlibs.thread.sysnctask;

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
 * Description: AsyncTask测试
 */
public class AsyncTaskTest {

    /**
     * 为什么线程池容量不够抛出异常rejectedExecution?
     */
    public static void textrejectedExecution() {
        int CPU_COUNT = Runtime.getRuntime().availableProcessors(); // 可用的CPU个数
        int CORE_POOL_SIZE = CPU_COUNT + 1; // 核心线程数:5
        int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1; // 最大线程数量:9
        int KEEP_ALIVE = 1;// 闲置回收:1

        // 异步任务队列（128）
        final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<Runnable>(128);
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
        Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS,
                sPoolWorkQueue, sThreadFactory);

        // 如果当前线程池中的核心线程数量大于corePoolSize，缓冲队列sPoolWorkQueue已满，并且线程池中的数量等于maximumPoolSize，新提交任务由Handler处理。
        // 否则抛出：RejectedExecutionException
        for (int i = 0; i < 200; i++) {
            // sPoolWorkQueue:200 > 128
            // 相当于new AsyncTask().execute();
            THREAD_POOL_EXECUTOR.execute(new MyTask());
        }
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
//            // 解决方案：1、防止线程阻塞，注释睡眠，使用此方法
//            //          2、执行线程数量尽可能小于缓冲队列数目
//            System.out.println(Thread.currentThread().getName());

			while (true) {
				try {
					System.out.println(Thread.currentThread().getName());
					Thread.sleep(1000);// 线程阻塞时，线程执行缓慢，容易抛出：RejectedExecutionException
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        }
    }

}
