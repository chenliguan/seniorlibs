package com.seniorlibs.designpattern.singleton

import java.util.concurrent.atomic.AtomicLong

/**
 * 懒汉式
 */
class SingletonV2 private constructor() {
    private val id = AtomicLong(0)

    fun getId(): Long {
        return id.incrementAndGet()
    }

    companion object {
        // 懒汉式相对于饿汉式的优势是支持延迟加载
        // 问题：频繁地用到，那频繁加锁、释放锁及并发度低等问题
        // public static synchronized IdGenerator getInstance()
        @get:Synchronized
        var instance: SingletonV2? = null
            get() {
                if (field == null) {
                    field = SingletonV2()
                }
                return field
            }
            private set
    }
}
