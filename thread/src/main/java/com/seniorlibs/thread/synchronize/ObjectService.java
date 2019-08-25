package com.seniorlibs.thread.synchronize;

/**
 * 验证synchronized(任意对象)代码块是锁定此对象
 */
public class ObjectService implements IService {
    private final StringBuilder lock = new StringBuilder();

    /**
     * 2019-08-25 15:16:28.490 16741-16762/com.seniorlibs.thread I/System.out: thread name=b 进入代码快:1566717388490
     * 2019-08-25 15:16:28.492 16741-16761/com.seniorlibs.thread I/System.out: thread name=a 进入代码快:1566717388492
     * 2019-08-25 15:16:31.491 16741-16762/com.seniorlibs.thread I/System.out: thread name=b 进入代码快:1566717391491 入参:ThreadB
     * 2019-08-25 15:16:31.494 16741-16761/com.seniorlibs.thread I/System.out: thread name=a 进入代码快:1566717391494 入参:ThreadA
     *
     * 可以看到ThreadA和ThreadB异步执行了
     * @param param
     */
//    @Override
//    public void serviceMethodA(String param) {
//        try {
//            StringBuilder lock = new StringBuilder();
//            synchronized (lock) {
//                System.out.println("thread name=" + Thread.currentThread().getName() + " 进入代码快:" + System.currentTimeMillis());
//                Thread.sleep(3000);
//                System.out.println("thread name=" + Thread.currentThread().getName() + " 进入代码快:" + System.currentTimeMillis() + " 入参:" + param);
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 2019-08-25 15:13:11.405 16557-16576/com.seniorlibs.thread I/System.out: thread name=a 进入代码快:1566717191405
     * 2019-08-25 15:13:14.406 16557-16576/com.seniorlibs.thread I/System.out: thread name=a 进入代码快:1566717194406 入参:ThreadA
     * 2019-08-25 15:13:14.407 16557-16577/com.seniorlibs.thread I/System.out: thread name=b 进入代码快:1566717194407
     * 2019-08-25 15:13:17.407 16557-16577/com.seniorlibs.thread I/System.out: thread name=b 进入代码快:1566717197407 入参:ThreadB
     *
     * ThreadA和ThreadB同步执行了
     * @param param
     */
    @Override
    public void serviceMethodA(String param) {
        try {
            synchronized (lock) {
                System.out.println("thread name=" + Thread.currentThread().getName() + " 进入代码快:" + System.currentTimeMillis());
                Thread.sleep(3000);
                System.out.println("thread name=" + Thread.currentThread().getName() + " 进入代码快:" + System.currentTimeMillis() + " 入参:" + param);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void serviceMethodB(String param) {
//        serviceMethodA(param);
//    }

    /**
     * synchronized(任意自定义对象)与synchronized同步方法共用
     *
     * 2019-08-25 15:22:04.148 17055-17075/com.seniorlibs.thread I/System.out: thread name=b 进入代码快:1566717724148
     * 2019-08-25 15:22:04.151 17055-17074/com.seniorlibs.thread I/System.out: thread name=a 进入代码快:1566717724151
     * 2019-08-25 15:22:07.149 17055-17075/com.seniorlibs.thread I/System.out: thread name=b 进入代码快:1566717727149 入参:ThreadB
     * 2019-08-25 15:22:07.152 17055-17074/com.seniorlibs.thread I/System.out: thread name=a 进入代码快:1566717727152 入参:ThreadA
     *
     * ThreadA和ThreadB异步执行了
     * @param param
     */
    @Override
    public synchronized void serviceMethodB(String param) {
        try {
            System.out.println("thread name=" + Thread.currentThread().getName() + " 进入代码快:" + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println("thread name=" + Thread.currentThread().getName() + " 进入代码快:" + System.currentTimeMillis() + " 入参:" + param);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
