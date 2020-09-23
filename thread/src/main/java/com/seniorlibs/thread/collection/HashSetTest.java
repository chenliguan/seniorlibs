package com.seniorlibs.thread.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author: chen
 * Version: 1.28
 * Date: 2020/3/8.
 * Mender:
 * Modify:
 * Description: 测试HashSet是否线程安全
 *
 * java.util包下的HashSet和HashMap类不是线程安全的，java.util.concurrent包下的ConcurrentHashMap类是线程安全的。
 *
 * 如果需要保证线程安全的场景：
 * 1.将HashSet或HashMap转换为线程安全，使用Collections.synchronizedSet或Collections.synchronizedMap方法；
 * 2.使用Collections.newSetFromMap(new ConcurrentHashMap<Integer, Boolean>())或使用java.util.concurrent包下的ConcurrentHashMap；
 * 3.仍然使用HashSet或HashMap，使用时手动进行加锁或同步；// 注意加锁粒度，尽可能保证性能
 */
public class HashSetTest {

    public static void main() {
        final Set<Integer> set = new HashSet<>();// 结果可能大于1000
//        final Set<Integer> set = Collections.synchronizedSet(new HashSet<Integer>());// 结果等于1000
//        final Set<Integer> set = Collections.newSetFromMap(new ConcurrentHashMap<Integer, Boolean>());// 结果等于1000

        // 往set写入1-1000
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 1000; i++) {
                    set.add(i);
                }
            }
        };

        // 线程数
        int threadNum = 10;
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            Thread thread = new Thread(runnable);
            threadList.add(thread);
            thread.start();
        }

        // 主线程等待子线程执行完成
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 结果可能大于1000
        System.out.println(set.size());
    }
}
