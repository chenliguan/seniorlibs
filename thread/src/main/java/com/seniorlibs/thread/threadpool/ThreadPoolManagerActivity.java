package com.seniorlibs.thread.threadpool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.seniorlibs.baselib.threadpool.ThreadPoolManager;
import com.seniorlibs.baselib.utils.LogUtils;
import com.seniorlibs.thread.R;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
            LogUtils.e(ThreadPoolManagerActivity.TAG, "InterruptedException ：" + e.toString());
        } catch (ExecutionException e) {
            e.printStackTrace();
            LogUtils.e(ThreadPoolManagerActivity.TAG, "ExecutionException ：" + e.toString());
        }
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
            LogUtils.e(ThreadPoolManagerActivity.TAG, "InterruptedException ：" + e.toString());
        } catch (ExecutionException e) {
            e.printStackTrace();
            LogUtils.e(ThreadPoolManagerActivity.TAG, "ExecutionException ：" + e.toString());
        }
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
            LogUtils.e(ThreadPoolManagerActivity.TAG, "InterruptedException ：" + e.toString());
        } catch (ExecutionException e) {
            e.printStackTrace();
            LogUtils.e(ThreadPoolManagerActivity.TAG, "ExecutionException ：" + e.toString());
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

    /**
     * newSingleThreadExecutor
     *
     * D/TagThreadPoolManagerActivity: new Random().nextInt()：519240209 Thread.currentThread().getName()：pool-1-thread-1
     * D/TagThreadPoolManagerActivity: new Random().nextInt()：-2126627150 Thread.currentThread().getName()：pool-1-thread-1
     * D/TagThreadPoolManagerActivity: new Random().nextInt()：739670292 Thread.currentThread().getName()：pool-1-thread-1
     * D/TagThreadPoolManagerActivity: new Random().nextInt()：-1463648297 Thread.currentThread().getName()：pool-1-thread-1
     * D/TagThreadPoolManagerActivity: service.shutdownNow().size()：995
     * D/TagThreadPoolManagerActivity: new Random().nextInt()：1246144705 Thread.currentThread().getName()：pool-1-thread-1
     * @param view
     */
    public void newSingleThreadExecutor(View view) {
        final ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 1000; i++) {
            service.execute(new MyTask());
        }
    }

    /**
     * newFixedThreadPool
     *
     * @param view
     */
    public void newFixedThreadPool(View view) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 1000; i++) {
            service.execute(new MyTask());
        }
    }

    /**
     * newCachedThreadPool
     *
     * @param view
     */
    public void newCachedThreadPool(View view) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            service.execute(new MyTask());
        }
    }

    /**
     * newScheduledThreadPool
     *
     * @param view
     */
    public void newScheduledThreadPool(View view) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        // 2秒后执行一次任务后就结束
//        service.schedule(new MyTask(), 2, TimeUnit.SECONDS);

        /**
         * 以固定的频率执行任务: 第一次延时2秒后，每次延时3秒执行一次任务（以任务开始的时间为时间起点开始计时，时间到就开始执行第二次任务，而不管任务需要花多久执行）
         *
         * 06-27 15:52:02.386 new Random().nextInt()：-1912208461 Thread.currentThread().getName()：pool-1-thread-1
         * 06-27 15:52:05.386 new Random().nextInt()：-1378944765 Thread.currentThread().getName()：pool-1-thread-1
         * 06-27 15:52:08.385 new Random().nextInt()：-97809203 Thread.currentThread().getName()：pool-1-thread-2
         */
//        service.scheduleAtFixedRate(new MyTask(), 2, 3, TimeUnit.SECONDS);

        /**
         * 任务结束的时间为下一次循环的时间起点开始计时: 第一次延时2秒后，每次延时3+1秒执行一次任务（以任务结束的时间为下一次循环的时间起点开始计时）
         *
         * 06-27 15:58:24.535 new Random().nextInt()：779350097 Thread.currentThread().getName()：pool-1-thread-1
         * 06-27 15:58:28.536 new Random().nextInt()：277033286 Thread.currentThread().getName()：pool-1-thread-1
         * 06-27 15:58:32.538 new Random().nextInt()：-1066926209 Thread.currentThread().getName()：pool-1-thread-2
         */
        service.scheduleWithFixedDelay(new MyTask(), 2, 3, TimeUnit.SECONDS);
    }

    public class MyTask implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LogUtils.d(ThreadPoolManagerActivity.TAG, "new Random().nextInt()：" + new Random().nextInt()
                    + " Thread.currentThread().getName()：" + Thread.currentThread().getName());
        }
    }

    /**
     * execute
     *
     * @param view
     */
    public void execute(View view) {
        for (int i = 0; i < 1000; i++) {
            ThreadPoolManager.getCpuExecutor().execute(new MyTask());
        }
    }

    /**
     * shutdown
     *
     * @param view
     */
    public void shutdown(View view) {
        // 调用shutdown()后线程池会在执行完正在执行的任务和队列中等待的任务后才彻底关闭
        ThreadPoolManager.getCpuExecutor().shutdown();
        /**
         * 首先会给所有线程池中的线程发送 interrupt 中断信号，尝试中断这些任务的执行，
         * 然后会将任务队列中正在等待的所有任务转移到一个List中并返回，我们可以根据List来进行一些补救的操作，例如记录在案并在后期重试；
         * 如果被中断的线程对于中断信号不理不睬，那么依然有可能导致任务不会停止
         */
        ThreadPoolManager.getCpuExecutor().shutdownNow();
        // isShutdown()：返回true仅仅代表线程池开始了关闭的流程；可能线程池中依然有线程在执行任务，队列里也可能有等待被执行的任务
        LogUtils.d(ThreadPoolManagerActivity.TAG, "isShutdown()：" + ThreadPoolManager.getCpuExecutor().isShutdown());
        // isTerminated()：检测线程池是否真正“终结”了，这不仅代表线程池已关闭，同时代表线程池中的所有任务都已经都执行完毕了
        LogUtils.d(ThreadPoolManagerActivity.TAG, "isTerminated()：" + ThreadPoolManager.getCpuExecutor().isTerminated());
        try {
            // awaitTermination(5, TimeUnit.SECONDS)：当前线程会尝试等待一段指定的时间，如果在等待时间内，线程池已关闭并且内部的任务都执行完毕了，也就是说线程池真正“终结”了，那么方法就返回true，否则超时返回false
            LogUtils.e(ThreadPoolManagerActivity.TAG, "awaitTermination()：" + ThreadPoolManager.getCpuExecutor().awaitTermination(5, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
