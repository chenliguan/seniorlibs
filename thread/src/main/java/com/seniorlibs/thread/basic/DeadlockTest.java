package com.seniorlibs.thread.basic;/** * Author: chen * Version: 1.0.0 * Date: 2020/11/28. * Mender: * Modify: * Description: 死锁 */public class DeadlockTest {    private static final String TAG = "DeadlockTest";    /**     * 模拟死锁     */    public static class MustDeadLock implements Runnable {        public int flag;        static Object o1 = new Object();        static Object o2 = new Object();        public void run() {            System.out.println(TAG + " 线程"+Thread.currentThread().getName() + "的flag为" + flag);            if (flag == 1) {                synchronized (o1) {                    System.out.println(TAG + " 线程1获得了1把锁");                    try {                        Thread.sleep(500);                    } catch (Exception e) {                        e.printStackTrace();                    }                    synchronized (o2) {                        System.out.println(TAG + " 线程1获得了两把锁");                    }                }            }            if (flag == 2) {                synchronized (o2) {                    System.out.println(TAG + " 线程2获得了1把锁");                    try {                        Thread.sleep(500);                    } catch (Exception e) {                        e.printStackTrace();                    }                    synchronized (o1) {                        System.out.println(TAG + " 线程2获得了两把锁");                    }                }            }        }    }    /**     * 模拟死锁     */    public static void deadLockTest() {        MustDeadLock r1 = new MustDeadLock();        MustDeadLock r2 = new MustDeadLock();        r1.flag = 1;        r2.flag = 2;        Thread t1 = new Thread(r1, "Object_blocked_1");        Thread t2 = new Thread(r2, "Object_blocked_2");        t1.start();        t2.start();    }}