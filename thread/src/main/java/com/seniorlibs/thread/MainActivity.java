package com.seniorlibs.thread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.seniorlibs.thread.aqs.SemaphoreTest;
import com.seniorlibs.thread.collection.ArrayListTest;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SynchronizedTest.mainTest();

        // 测试原子性Integer
//        AtomicIntegerTest.mainTest();

        // 测试ArrayList和Collections.synchronizedList(new ArrayList<>());
//        ArrayListTest.testWriteArrayListError();

        // 测试Semaphore
//        SemaphoreTest.testAcquire();
        SemaphoreTest.testTryAcquire();
    }
}
