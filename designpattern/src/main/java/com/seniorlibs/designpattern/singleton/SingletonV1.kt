package com.seniorlibs.designpattern.singleton

import java.util.concurrent.atomic.AtomicLong

/**
 * 饿汉式
 */
class SingletonV1 private constructor() {
    private val id = AtomicLong(0)

    fun getId(): Long {
        return id.incrementAndGet()
    }

    companion object {
        // 在类加载的时候，instance 静态实例就已经创建并初始化好了，所以，instance 实例的创建过程是线程安全的。
        // 问题：不支持延迟加载（在真正用到 IdGenerator 的时候，再创建实例）
        val instance = SingletonV1()
    }
}
