package com.seniorlibs.thirdlib.rxjava.schedulers;

import androidx.annotation.NonNull;

import io.reactivex.Scheduler;

/**
 * Allow providing different types of {@link Scheduler}s.
 */
public interface BaseSchedulerProvider {

    /**
     * CPU计算操作线程：大量计算操作
     *
     * @return
     */
    @NonNull
    Scheduler computation();

    /**
     * io操作线程：网络请求、读写文件等io密集型操作
     *
     * @return
     */
    @NonNull
    Scheduler io();

    /**
     * 常规新线程：耗时等操作
     *
     * @return
     */
    @NonNull
    Scheduler newThread();

    /**
     * Android主线程：操作UI
     *
     * @return
     */
    @NonNull
    Scheduler ui();

    @NonNull
    Scheduler download();

    void clearNovelDownload();

    @NonNull
    Scheduler getFixedThreadPool(String poolName, int nThreads);
}
