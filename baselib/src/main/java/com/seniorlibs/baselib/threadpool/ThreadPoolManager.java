package com.seniorlibs.baselib.threadpool;

import android.support.annotation.NonNull;

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
 * Date: 2020/3/6.
 * Mender:
 * Modify:
 * Description: 线程池管理类
 */
public class ThreadPoolManager {

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAXIMUM_POOL_SIZE = 128;
    private static final int KEEP_ALIVE = 1;
    private static final int MAX_QUEUE_SIZE = 128;

    private static volatile ThreadPoolManager sInstance;
    private ThreadPoolExecutor THREAD_POOL_EXECUTOR;

    private ThreadPoolManager() {
        ThreadFactory threadFactory = new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(1);

            @Override
            public Thread newThread(@NonNull Runnable r) {
                return new Thread(r, "ThreadPoolManager #" + mCount.getAndIncrement());
            }
        };

        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(MAX_QUEUE_SIZE), threadFactory, new ThreadPoolExecutor.AbortPolicy());
        THREAD_POOL_EXECUTOR.allowCoreThreadTimeOut(true);
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
     * 执行任务
     *
     * @param runnable
     */
    public void execute(Runnable runnable) {
        try {
            THREAD_POOL_EXECUTOR.execute(runnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取线程池对象
     *
     * @return
     */
    public Executor getExecutor() {
        return THREAD_POOL_EXECUTOR;
    }

    /**
     * 获取无界阻塞队列
     */
    public BlockingQueue<Runnable> getQueue() {
        return THREAD_POOL_EXECUTOR.getQueue();
    }
}
