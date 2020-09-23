package com.seniorlibs.thread.synchronize;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2019/9/1.
 * Mender:
 * Modify:
 * Description: 测试Synchronized
 */
public class SynchronizedTest {

    public static void mainTest() {
//        textSynchronizeThis();
//        textSynchronizeLocalThis();
//        textSynchronizeObject();
//        textSynchronizeLocalStatic();
    }

    /**
     * 验证synchronized代码块间的同步性
     */
    public static void textSynchronizeThis() {
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
    public static void textSynchronizeLocalThis() {
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
    public static void textSynchronizeObject() {
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
    public static void textSynchronizeLocalStatic() {
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
