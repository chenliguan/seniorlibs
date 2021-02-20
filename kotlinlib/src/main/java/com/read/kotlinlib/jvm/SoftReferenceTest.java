package com.read.kotlinlib.jvm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: chen
 * Time: 2021/2/20
 * Description: 软引用隐藏问题
 */
class SoftReferenceTest {

    public static class SoftObject {
        byte[] data = new byte[1024]; // 1KB
    }

    public static int CACHE_INITIAL_CAPACITY = 100 * 1024; // 100M

    // Static collections save soft references, which will cause these soft reference objects themselves to not be recycled by the garbage collector
    public static Set<SoftReference<SoftObject>> cache = new HashSet<>(CACHE_INITIAL_CAPACITY);

    public static void main(String[] args) {
        for (int i = 0; i < CACHE_INITIAL_CAPACITY; i++) {
            SoftObject obj = new SoftObject();
            cache.add(new SoftReference<>(obj, null));
            if (i % 10000 == 0) {
                System.out.println("size of cache:" + cache.size());
                System.out.println("End! ");
            }
        }
    }

//    java -Xms4M -Xmx4M -Xmn2M SoftReferenceTest

//    size of cache:1
//    End!
//    size of cache:10001
//    End!
//    size of cache:20001
//    End!
//    Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
//    at SoftReferenceTest$SoftObject.<init>(SoftReferenceTest.java:10)
//    at SoftReferenceTest.main(SoftReferenceTest.java:21)
}
