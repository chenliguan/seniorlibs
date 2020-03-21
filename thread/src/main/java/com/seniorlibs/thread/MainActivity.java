package com.seniorlibs.thread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.seniorlibs.thread.aqs.SemaphoreTest;
import com.seniorlibs.thread.atomic.AtomicIntegerTest;
import com.seniorlibs.thread.collection.ArrayListTest;
import com.seniorlibs.thread.collection.HashSetTest;
import com.seniorlibs.thread.synchronize.SynchronizedTest;
import com.seniorlibs.thread.sysnctask.AsyncTaskTest;

public class MainActivity extends AppCompatActivity {

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
     * 测试为什么线程池容量不够抛出异常rejectedExecution
     *
     * @param view
     */
    public void textrejectedExecution(View view) {
        AsyncTaskTest.textrejectedExecution();
    }

    /**
     * 测试HashSet是否线程安全
     *
     * @param view
     */
    public void testHashSet(View view) {
        HashSetTest.main();
    }
}
