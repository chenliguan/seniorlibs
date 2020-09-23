package com.seniorlibs.thread.collection;

import com.seniorlibs.baselib.utils.LogUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2019/9/1.
 * Mender:
 * Modify:
 * Description: 测试ArrayList和Collections.synchronizedList(new ArrayList<>());
 */
public class ArrayListTest {

    private static final String TAG = "ArrayListTest";

    private static final int THREADS_COUNT = 100;
    private static final int NUM = 100;
//    private static List<String> sThreadList = new ArrayList<>();
    private static List<String> sThreadList = Collections.synchronizedList(new ArrayList<String>());

    public static void testWriteArrayListError() {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < NUM; i++) {
                        try {
                            Thread.sleep(10);
                            sThreadList.add(Thread.currentThread().getName() + "." + i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, i + "");
            threads[i].start();
        }

        // 让调用join()的线程先执行
        for (int i = 0; i < THREADS_COUNT; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 再执行主线程，输出list中的值
        LogUtils.d(TAG, "threadList.size()：" + sThreadList.size());
        for (int i = 0; i < sThreadList.size(); i++) {
            LogUtils.d(TAG, "index:" + i + "  ->  " + sThreadList.get(i));
        }
    }
}