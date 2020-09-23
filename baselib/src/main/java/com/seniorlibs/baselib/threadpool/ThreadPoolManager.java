package com.seniorlibs.baselib.threadpool;

import androidx.annotation.NonNull;

import com.seniorlibs.baselib.utils.LogUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: chen
 * Version: 1.28
 * Date: 2020/3/6.
 * Mender:
 * Modify:
 * Description: 线程池管理类
 */
public class ThreadPoolManager {

    private static volatile ThreadPoolManager sInstance;
    private static ThreadPoolExecutor sIoThreadPoolExecutor;
    private static ThreadPoolExecutor sCpuThreadPoolExecutor;

    // IO
    static {
        // 线程池核心容量
        final int CORE_POOL_SIZE = 10;
        // 线程池最大容量
        final int MAXIMUM_POOL_SIZE = 128;
        // 过剩的空闲线程的存活时间
        final int KEEP_ALIVE_TIME = 1;
        // 使用有界队列，可以配置大一些，例如几千
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(1000);
        // ThreadFactory 线程工厂，通过工厂方法newThread来获取新线程
        ThreadFactory sThreadFactory = new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(1);

            @Override
            public Thread newThread(@NonNull Runnable r) {
                return new Thread(r, "ThreadPoolManager IO #" + mCount.getAndIncrement());
            }
        };
        // 线程池执行耗时任务时发生异常所需要做的拒绝执行处理。注意：一般不会执行到这里
        final RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                // 打印日志、暂存任务、重新执行等拒绝策略
                Executors.newCachedThreadPool().execute(r);
            }
        };

        sIoThreadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                blockingQueue, sThreadFactory, rejectedExecutionHandler);
    }

    // CPU
    static {
        // 返回可用处理器的Java虚拟机的数量
        int CPU_COUNT = Runtime.getRuntime().availableProcessors();
        // 线程池核心容量
        final int CORE_POOL_SIZE = CPU_COUNT;
        // 线程池最大容量
        final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2;
        // 过剩的空闲线程的存活时间
        final int KEEP_ALIVE_TIME = 1;
        // 使用有界队列，可以配置大一些，例如几千
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(3000);
        // ThreadFactory 线程工厂，通过工厂方法newThread来获取新线程
        final ThreadFactory sThreadFactory = new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(1);

            public Thread newThread(Runnable r) {
                return new Thread(r, "ThreadPoolManager CPU #" + mCount.getAndIncrement());
            }
        };
        // 线程池执行耗时任务时发生异常所需要做的拒绝执行处理。注意：一般不会执行到这里
        final RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                // 打印日志、暂存任务、重新执行等拒绝策略
                Executors.newCachedThreadPool().execute(r);
            }
        };

        LogUtils.e("TagThreadPoolManagerActivity", "CPU_COUNT：" + CPU_COUNT);
        LogUtils.e("TagThreadPoolManagerActivity", "CORE_POOL_SIZE：" + CORE_POOL_SIZE);
        LogUtils.e("TagThreadPoolManagerActivity", "MAXIMUM_POOL_SIZE：" + MAXIMUM_POOL_SIZE);

        sCpuThreadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                blockingQueue, sThreadFactory, rejectedExecutionHandler);
    }

    public synchronized static ThreadPoolManager getInstance() {
        if (sInstance == null) {
            synchronized (ThreadPoolManager.class) {
                if (sInstance == null) {
                    sInstance = new ThreadPoolManager();
                }
            }
        }
        return sInstance;
    }

    /**
     * 执行io任务
     *
     * @param runnable
     */
    public void executeIo(Runnable runnable) {
        try {
            sIoThreadPoolExecutor.execute(runnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行cpu任务
     *
     * @param runnable
     */
    public void executeCpu(Runnable runnable) {
        try {
            sCpuThreadPoolExecutor.execute(runnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取CPU线程池
     *
     * @return CPU线程池
     */
    public static ThreadPoolExecutor getCpuExecutor() {
        return sCpuThreadPoolExecutor;
    }

    /**
     * 获取IO线程池
     *
     * @return IO线程池
     */
    public static ExecutorService getIoExecutor() {
        return sIoThreadPoolExecutor;
    }
}
