package com.seniorlibs.thread.threadpool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.seniorlibs.baselib.utils.LogUtils;
import com.seniorlibs.thread.R;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/6/27
 * Mender:
 * Modify:
 * Description: 测试线程池页面
 */
public class ThreadPoolManagerActivity extends AppCompatActivity {

    public static final String TAG = "TagThreadPoolManagerActivity";

    public static void actionStart(Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, ThreadPoolManagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool_manager);
    }

    /**
     * 测试线程池的拒绝策略：为什么线程池容量不够抛出异常rejectedExecution？
     *
     * @param view
     */
    public void rejectedExecution(View view) {
        ThreadPoolManagerTest.textRejectedExecution();
    }

    /**
     * 测试Future的get方法
     * <p>
     * D/TagThreadPoolManagerActivity: future.isDone() ：false
     * D/TagThreadPoolManagerActivity: future.get() ：1176208690
     * D/TagThreadPoolManagerActivity: future.isDone() ：true
     *
     * @param view
     */
    public void futureGet(View view) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        Future<Integer> future = service.submit(new CallableTask());
        try {
            LogUtils.d(ThreadPoolManagerActivity.TAG, "future.isDone() ：" + future.isDone());
            LogUtils.d(ThreadPoolManagerActivity.TAG, "future.get() ：" + future.get());
            LogUtils.d(ThreadPoolManagerActivity.TAG, "future.isDone() ：" + future.isDone());

        } catch (InterruptedException e) {
            e.printStackTrace();
            LogUtils.d(ThreadPoolManagerActivity.TAG, "InterruptedException ：" + e.toString());
        } catch (ExecutionException e) {
            e.printStackTrace();
            LogUtils.d(ThreadPoolManagerActivity.TAG, "ExecutionException ：" + e.toString());
        }
        service.shutdown();
    }

    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }
    }

    /**
     * 测试Future的isDone方法
     * <p>
     * D/TagThreadPoolManagerActivity: future i ：0
     * D/TagThreadPoolManagerActivity: future i ：1
     * D/TagThreadPoolManagerActivity: future i ：2
     * D/TagThreadPoolManagerActivity: future i ：3
     * D/TagThreadPoolManagerActivity: future i ：4
     * D/TagThreadPoolManagerActivity: future.isDone() ：true
     * D/TagThreadPoolManagerActivity: ExecutionException ：java.util.concurrent.ExecutionException: java.lang.IllegalArgumentException: Callable抛出异常
     *
     * @param view
     */
    public void futureIsDone(View view) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        Future<Integer> future = service.submit(new CallableTask1());
        try {
            for (int i = 0; i < 5; i++) {
                LogUtils.d(ThreadPoolManagerActivity.TAG, "future i ：" + i);
                Thread.sleep(500);
            }
            LogUtils.d(ThreadPoolManagerActivity.TAG, "future.isDone() ：" + future.isDone());
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            LogUtils.d(ThreadPoolManagerActivity.TAG, "InterruptedException ：" + e.toString());
        } catch (ExecutionException e) {
            e.printStackTrace();
            LogUtils.d(ThreadPoolManagerActivity.TAG, "ExecutionException ：" + e.toString());
        }
        service.shutdown();
    }

    static class CallableTask1 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            throw new IllegalArgumentException("Callable抛出异常");
        }
    }

    /**
     * 测试FutureTask
     * <p>
     * D/TagThreadPoolManagerActivity: future.isDone() ：false
     * D/TagThreadPoolManagerActivity: futureTask + 子线程正在计算
     * D/TagThreadPoolManagerActivity: future.get() ：4950
     * D/TagThreadPoolManagerActivity: future.isDone() ：true
     *
     * @param view
     */
    public void futureTask(View view) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        FutureTask<Integer> futureTask = new FutureTask<>(new CallableTask2());
        service.submit(futureTask);

        try {
            LogUtils.d(ThreadPoolManagerActivity.TAG, "future.isDone() ：" + futureTask.isDone());
            LogUtils.d(ThreadPoolManagerActivity.TAG, "future.get() ：" + futureTask.get());
            LogUtils.d(ThreadPoolManagerActivity.TAG, "future.isDone() ：" + futureTask.isDone());
        } catch (InterruptedException e) {
            e.printStackTrace();
            LogUtils.d(ThreadPoolManagerActivity.TAG, "InterruptedException ：" + e.toString());
        } catch (ExecutionException e) {
            e.printStackTrace();
            LogUtils.d(ThreadPoolManagerActivity.TAG, "ExecutionException ：" + e.toString());
        }

        service.shutdown();
    }

    static class CallableTask2 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            LogUtils.d(ThreadPoolManagerActivity.TAG, "futureTask + 子线程正在计算");
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
