package com.read.kotlinlib.jvm;

import java.lang.ref.SoftReference;

/**
 * Author: chen
 * Time: 2021/2/20
 * Description: 软引用常规使用
 */
class SoftReferenceNormal {

    static class SoftObject {
        byte[] data = new byte[120 * 1024 * 1024]; // 120M
    }

    public static void main(String[] args) throws InterruptedException {
        SoftReference<SoftObject> cacheRef = new SoftReference<>(new SoftObject());
        System.out.println("soft reference before the first gc: " + cacheRef.get());

        System.gc();
        Thread.sleep(2000);
        System.gc();

        System.out.println("soft references after the first gc: " + cacheRef.get());

        SoftObject newSo = new SoftObject();
        System.out.println("Soft reference after allocating 120 M strong reference objects again:" + cacheRef.get());
    }

//    java -Xmx200m SoftReferenceNormal

//    soft reference before the first gc: SoftReferenceNormal$SoftObject@15db9742
//    soft references after the first gc: SoftReferenceNormal$SoftObject@15db9742
//    Soft reference after allocating 120 M strong reference objects again:null
}
