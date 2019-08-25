package com.seniorlibs.thread.synchronize;

/**
 * 验证synchronized(this)代码块是锁定当前对象
 */
public class LocalThisService implements IService{

    /**
     * 结果：ThreadA和ThreadB异步执行了
     * 2019-08-25 14:49:51.328 15628-15649/com.seniorlibs.thread I/System.out: synchronized thread name:b-->i=1
     * 2019-08-25 14:49:51.329 15628-15648/com.seniorlibs.thread I/System.out: run----serviceMethodA
     * 2019-08-25 14:49:52.344 15628-15649/com.seniorlibs.thread I/System.out: synchronized thread name:b-->i=2
     * 2019-08-25 14:49:53.345 15628-15649/com.seniorlibs.thread I/System.out: synchronized thread name:b-->i=3
     * 2019-08-25 14:49:54.347 15628-15649/com.seniorlibs.thread I/System.out: synchronized thread name:b-->i=4
     * 2019-08-25 14:49:55.348 15628-15649/com.seniorlibs.thread I/System.out: synchronized thread name:b-->i=5
     * @param param
     */
//    @Override
//    public void serviceMethodA(String param) {
//        System.out.println("run----serviceMethodA");
//    }

    /**
     * 结果：ThreadA和ThreadB同步执行了
     * 2019-08-25 14:51:19.941 15753-15773/com.seniorlibs.thread I/System.out: synchronized thread name:b-->i=1
     * 2019-08-25 14:51:20.948 15753-15773/com.seniorlibs.thread I/System.out: synchronized thread name:b-->i=2
     * 2019-08-25 14:51:21.950 15753-15773/com.seniorlibs.thread I/System.out: synchronized thread name:b-->i=3
     * 2019-08-25 14:51:22.951 15753-15773/com.seniorlibs.thread I/System.out: synchronized thread name:b-->i=4
     * 2019-08-25 14:51:23.953 15753-15773/com.seniorlibs.thread I/System.out: synchronized thread name:b-->i=5
     * 2019-08-25 14:51:24.953 15753-15772/com.seniorlibs.thread I/System.out: run----serviceMethodA
     * @param param
     */
    @Override
    public synchronized void serviceMethodA(String param){
        System.out.println("run----serviceMethodA");
    }

    @Override
    public void serviceMethodB(String param) {
        synchronized (this) {
            try {
                for (int i = 1; i <= 5; i++) {
                    System.out.println("synchronized thread name:" + Thread.currentThread().getName() + "-->i=" + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
