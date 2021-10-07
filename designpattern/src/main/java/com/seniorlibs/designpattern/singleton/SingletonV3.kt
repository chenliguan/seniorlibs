package com.seniorlibs.designpattern.singleton

import java.util.concurrent.atomic.AtomicLong

/**
 * 双重检测
 */
class SingletonV3 private constructor() {
    private val id = AtomicLong(0)

    fun getId(): Long {
        return id.incrementAndGet()
    }

    companion object {
        // 既支持延迟加载、又支持高并发的单例实现方式
        // 只要 instance 被创建之后，即便再调用 getInstance() 函数也不会再进入到加锁逻辑中了
        @Volatile
        var instance: SingletonV3? = null
            get() {
                if (field == null) {
                    // 此处为类级别的锁
                    synchronized(SingletonV3::class.java) {
                        if (field == null) {
                            field = SingletonV3()
                        }
                    }
                }
                return field
            }
            private set
    }
}