package com.seniorlibs.thirdlib.rxjava.schedulers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Provides different types of schedulers.
 */
public class SchedulerProvider implements BaseSchedulerProvider {

    @Nullable
    private static SchedulerProvider INSTANCE;

    private final Scheduler downloadScheduler;
    private final Map<String, Scheduler> schedulerMap = new HashMap<>();

    private final ThreadPoolExecutor novelExecutorService;

    /**
     * Prevent direct instantiation.
     */
    private SchedulerProvider() {
        novelExecutorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        downloadScheduler = Schedulers.from(novelExecutorService);
    }

    public static synchronized SchedulerProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SchedulerProvider();
        }
        return INSTANCE;
    }

    /**
     * CPU计算操作线程：大量计算操作
     *
     * @return
     */
    @Override
    @NonNull
    public Scheduler computation() {
        return Schedulers.computation();
    }

    /**
     * io操作线程：网络请求、读写文件等io密集型操作
     *
     * @return
     */
    @Override
    @NonNull
    public Scheduler io() {
        return Schedulers.io();
    }

    /**
     * 常规新线程：耗时等操作
     *
     * @return
     */
    @NonNull
    @Override
    public Scheduler newThread() {
        return Schedulers.newThread();
    }

    /**
     * Android主线程：操作UI
     *
     * @return
     */
    @Override
    @NonNull
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    @NonNull
    public Scheduler download() {
        return downloadScheduler;
    }

    @Override
    public void clearNovelDownload() {
        try {
            BlockingQueue<Runnable> q = novelExecutorService.getQueue();
            q.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @NonNull
    public Scheduler getFixedThreadPool(String poolName, int nThreads){
        if(!schedulerMap.containsKey(poolName)){
            Scheduler scheduler = Schedulers.from(Executors.newFixedThreadPool(nThreads));
            schedulerMap.put(poolName, scheduler);
        }
        return schedulerMap.get(poolName);
    }
}
