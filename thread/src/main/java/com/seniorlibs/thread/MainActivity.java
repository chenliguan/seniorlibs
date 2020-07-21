package com.seniorlibs.thread;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.seniorlibs.thread.aqs.SemaphoreTest;
import com.seniorlibs.thread.asynctask.AsyncTaskTest;
import com.seniorlibs.thread.atomic.AtomicIntegerTest;
import com.seniorlibs.thread.collection.ArrayListTest;
import com.seniorlibs.thread.collection.HashSetTest;
import com.seniorlibs.thread.synchronize.SynchronizedTest;
import com.seniorlibs.thread.threadpool.ThreadPoolManagerActivity;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/6/26.
 * Mender:
 * Modify:
 * Description: 测试线程
 */
public class MainActivity extends AppCompatActivity {

    public final static String TAG = "seniorLibsThread";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 测试synchronized
     *
     * @param view
     */
    public void synchronizedTest(View view) {
        SynchronizedTest.mainTest();
    }

    /**
     * 测试原子性Integer
     *
     * @param view
     */
    public void atomicIntegerTest(View view) {
        AtomicIntegerTest.mainTest();
    }

    /**
     * 测试ArrayList和Collections.synchronizedList(new ArrayList<>());
     *
     * @param view
     */
    public void testWriteArrayListError(View view) {
        ArrayListTest.testWriteArrayListError();
    }

    /**
     * 测试Semaphore
     *
     * @param view
     */
    public void testAcquire(View view) {
        SemaphoreTest.testAcquire();
    }

    public void testTryAcquire(View view) {
        SemaphoreTest.testTryAcquire();
    }

    /**
     * 测试HashSet是否线程安全
     *
     * @param view
     */
    public void testHashSet(View view) {
        HashSetTest.main();
    }

    /**
     * 测试线程池的拒绝策略：为什么线程池容量不够抛出异常rejectedExecution？
     *
     * @param view
     */
    public void textRejectedExecution(View view) {
        ThreadPoolManagerActivity.actionStart(this);
    }

    /**
     * 测试AsyncTask的execute()
     *
     * @param view
     */
    public void testExecute(View view) {
        AsyncTaskTest.testExecute();
    }

    /**
     * 测试AsyncTask的executeOnExecutor()
     *
     * @param view
     */
    public void testExecuteOnExecutor(View view) {
        AsyncTaskTest.testExecuteOnExecutor();
    }
}
