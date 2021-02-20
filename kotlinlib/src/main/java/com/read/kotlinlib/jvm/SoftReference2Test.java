package com.read.kotlinlib.jvm;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: chen
 * Time: 2021/2/20
 * Description: 软引用隐藏问题优化：注册一个引用队列，每次循环之后将引用队列中出现的软引用对象从 cache 中移除
 */
class SoftReference2Test {

    public static class SoftObject {
        byte[] data = new byte[1024]; // 1KB
    }

    public static int removedSoftRefs = 0;
    public static int CACHE_INITIAL_CAPACITY = 100 * 1024; // 100M

    // Static collections save soft references, which will cause these soft reference objects
    // themselves to not be recycled by the garbage collector
    public static Set<SoftReference<SoftObject>> cache = new HashSet<>(CACHE_INITIAL_CAPACITY);
    public static ReferenceQueue<SoftObject> referenceQueue = new ReferenceQueue<>() ;

    public static void main(String[] args) {
        for (int i = 0; i < CACHE_INITIAL_CAPACITY; i++) {
            SoftObject obj = new SoftObject();
            cache.add(new SoftReference<>(obj, referenceQueue));
            clearUselessReferences();
            if (i % 10000 == 0) {
                System.out.println("size of cache:" + cache.size());
                System.out.println("End! ");
            }
        }
        System.out.println("End! removed soft references = " + removedSoftRefs) ;

    }

    public static void clearUselessReferences() {
        Reference<? extends SoftObject> ref = referenceQueue.poll();
        while (ref != null) {
            if(cache.remove(ref)) {
                removedSoftRefs++ ;
            }
            ref = referenceQueue.poll();
        }
    }

//    java -Xms4M -Xmx4M -Xmn2M SoftReferenceTest

//    size of cache:1
//    End!
//    size of cache:1023
//    End!
//    size of cache:1393
//    End!
//    size of cache:1652
//    End!
//    size of cache:1086
//    End!
//    size of cache:1298
//    End!
//    size of cache:1715
//    End!
//    size of cache:1149
//    End!
//    size of cache:583
//    End!
//    size of cache:1571
//    End!
//    size of cache:1212
//    End!
//    End! removed soft references = 100878
}
