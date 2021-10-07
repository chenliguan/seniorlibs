package com.seniorlibs.designpattern.singleton

import java.util.concurrent.atomic.AtomicLong

/**
 * 枚举（了解）
 */
enum class SingletonV5 {
    // 保证了实例创建的线程安全性和实例的唯一性
    // 最简单的实现方式
    INSTANCE;

    private val id = AtomicLong(0)

    fun getId(): Long {
        return id.incrementAndGet()
    }
}