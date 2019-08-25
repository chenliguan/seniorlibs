package com.seniorlibs.thread.synchronize;

/**
 * 验证synchronized static方法是对当前对应的*.Class进行持锁
 */
public class StaticService implements IService {

    @Override
    public void serviceMethodA(String param) {
        methodA(param);
    }

//    private synchronized static void methodA(String param) {
//        try {
//            System.out.println("A begin time=" + System.currentTimeMillis());
//            Thread.sleep(3000);
//            System.out.println("A end   time=" + System.currentTimeMillis());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

//    private synchronized void methodA(String param) {
//        try {
//            System.out.println("A begin time=" + System.currentTimeMillis());
//            Thread.sleep(3000);
//            System.out.println("A end   time=" + System.currentTimeMillis());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    private void methodA(String param) {
        try {
            synchronized (StaticService.class) {
                System.out.println("A begin time=" + System.currentTimeMillis());
                Thread.sleep(3000);
                System.out.println("A end   time=" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void serviceMethodB(String param) {
        methodB(param);
    }

//    private synchronized static void methodB(String param) {
//        try {
//            System.out.println("B begin time=" + System.currentTimeMillis());
//            Thread.sleep(1000);
//            System.out.println("B end   time=" + System.currentTimeMillis());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

//    private synchronized void methodB(String param) {
//        try {
//            System.out.println("B begin time=" + System.currentTimeMillis());
//            Thread.sleep(1000);
//            System.out.println("B end   time=" + System.currentTimeMillis());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    private void methodB(String param) {
        try {
            synchronized (StaticService.class) {
                System.out.println("B begin time=" + System.currentTimeMillis());
                Thread.sleep(1000);
                System.out.println("B end   time=" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * synchronized static + 一个/多个StaticService对象 --->ThreadA和ThreadB同步执行了
     * 2019-08-25 15:39:30.711 18410-18429/com.seniorlibs.thread I/System.out: B begin time=1566718770711
     * 2019-08-25 15:39:31.719 18410-18429/com.seniorlibs.thread I/System.out: B end   time=1566718771719
     * 2019-08-25 15:39:31.719 18410-18428/com.seniorlibs.thread I/System.out: A begin time=1566718771719
     * 2019-08-25 15:39:34.721 18410-18428/com.seniorlibs.thread I/System.out: A end   time=1566718774721
     *
     * synchronized + 一个StaticService对象 --->ThreadA和ThreadB同步执行了
     * 2019-08-25 15:39:30.711 18410-18429/com.seniorlibs.thread I/System.out: B begin time=1566718770711
     * 2019-08-25 15:39:31.719 18410-18429/com.seniorlibs.thread I/System.out: B end   time=1566718771719
     * 2019-08-25 15:39:31.719 18410-18428/com.seniorlibs.thread I/System.out: A begin time=1566718771719
     * 2019-08-25 15:39:34.721 18410-18428/com.seniorlibs.thread I/System.out: A end   time=1566718774721
     *
     * synchronized + 多个StaticService对象 --->ThreadA和ThreadB异步执行了
     * 2019-08-25 15:42:16.522 18724-18743/com.seniorlibs.thread I/System.out: B begin time=1566718936522
     * 2019-08-25 15:42:16.528 18724-18742/com.seniorlibs.thread I/System.out: A begin time=1566718936527
     * 2019-08-25 15:42:17.525 18724-18743/com.seniorlibs.thread I/System.out: B end   time=1566718937525
     * 2019-08-25 15:42:19.528 18724-18742/com.seniorlibs.thread I/System.out: A end   time=1566718939528
     *
     * synchronized (StaticService.class) + 一个/多个StaticService对象 --->ThreadA和ThreadB同步执行了
     * 2019-08-25 15:49:15.626 18996-19017/com.seniorlibs.thread I/System.out: B begin time=1566719355626
     * 2019-08-25 15:49:16.630 18996-19017/com.seniorlibs.thread I/System.out: B end   time=1566719356630
     * 2019-08-25 15:49:16.631 18996-19016/com.seniorlibs.thread I/System.out: A begin time=1566719356631
     * 2019-08-25 15:49:19.632 18996-19016/com.seniorlibs.thread I/System.out: A end   time=1566719359632
     */
}
