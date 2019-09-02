package com.seniorlibs.thread;

import android.app.Activity;
import android.os.Bundle;

import com.seniorlibs.thread.atomic.AtomicIntegerTest;
import com.seniorlibs.thread.synchronize.SynchronizedTest;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SynchronizedTest.mainTest();

        AtomicIntegerTest.mainTest();
    }
}
