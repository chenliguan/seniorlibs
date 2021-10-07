package com.seniorlibs.designpattern.singleton

import java.util.concurrent.atomic.AtomicLong

/**
 * 静态内部类
 */
class SingletonV4 private constructor() {
    private val id = AtomicLong(0)

    // 既保证了线程安全，又能做到延迟加载
    // 比双重检测更加简单
    private object SingletonHolder {
        val instance = SingletonV4()
    }

    fun getId(): Long {
        return id.incrementAndGet()
    }

    companion object {
        val instance: SingletonV4
            get() = SingletonHolder.instance
    }
}
