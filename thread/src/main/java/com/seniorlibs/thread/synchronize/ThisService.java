package com.seniorlibs.thread.synchronize;

/**
 * 验证synchronized代码块间的同步性
 */
public class ThisService implements IService {

    @Override
    public void serviceMethodA(String param) {
        try {
            synchronized (this) {
                System.out.println("A begin time=" + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("A end   time=" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /***
     * 2019-08-25 14:45:56.400 15246-15268/com.seniorlibs.thread I/System.out: B begin time=1566715556400
     * 2019-08-25 14:45:56.400 15246-15268/com.seniorlibs.thread I/System.out: B end   time=1566715556400
     * 2019-08-25 14:45:56.400 15246-15267/com.seniorlibs.thread I/System.out: A begin time=1566715556400
     * 2019-08-25 14:45:58.402 15246-15267/com.seniorlibs.thread I/System.out: A end   time=1566715558402
     */
    @Override
    public void serviceMethodB(String param) {
        synchronized (this) {
            System.out.println("B begin time=" + System.currentTimeMillis());
            System.out.println("B end   time=" + System.currentTimeMillis());
        }
    }
}
