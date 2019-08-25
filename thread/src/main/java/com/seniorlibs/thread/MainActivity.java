package com.seniorlibs.thread;

import android.app.Activity;
import android.os.Bundle;

import com.seniorlibs.thread.synchronize.LocalThisService;
import com.seniorlibs.thread.synchronize.ObjectService;
import com.seniorlibs.thread.synchronize.StaticService;
import com.seniorlibs.thread.synchronize.ThisService;
import com.seniorlibs.thread.synchronize.ThreadA;
import com.seniorlibs.thread.synchronize.ThreadB;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        textSynchronizeThis();
//        textSynchronizeLocalThis();
        textSynchronizeObject();
//        textSynchronizeLocalStatic();
    }

    /**
     * 验证synchronized代码块间的同步性
     */
    public void textSynchronizeThis() {
        ThisService service = new ThisService();
        ThreadA threadA = new ThreadA(service, "");
        threadA.setName("a");
        threadA.start();
        ThreadB threadB = new ThreadB(service, "");
        threadB.setName("b");
        threadB.start();
    }

    /**
     * 验证synchronized(this)代码块是锁定当前对象
     */
    public void textSynchronizeLocalThis() {
        LocalThisService service = new LocalThisService();
        ThreadA threadA = new ThreadA(service, "");
        threadA.setName("a");
        threadA.start();
        ThreadB threadB = new ThreadB(service, "");
        threadB.setName("b");
        threadB.start();
    }

    /**
     * 验证synchronized(任意对象)代码块是此对象
     */
    public void textSynchronizeObject() {
        ObjectService service = new ObjectService();
        ThreadA threadA = new ThreadA(service, "ThreadA");
        threadA.setName("a");
        threadA.start();
        ThreadB threadB = new ThreadB(service, "ThreadB");
        threadB.setName("b");
        threadB.start();
    }

    /**
     * 验证静态synchronized方法是对当前对应的*.Class进行持锁
     */
    public void textSynchronizeLocalStatic() {
        StaticService service = new StaticService();
        ThreadA threadA = new ThreadA(service, "");
        threadA.setName("a");
        threadA.start();
        StaticService service2 = new StaticService();
        ThreadB threadB = new ThreadB(service2, "");
        threadB.setName("b");
        threadB.start();
    }
}
