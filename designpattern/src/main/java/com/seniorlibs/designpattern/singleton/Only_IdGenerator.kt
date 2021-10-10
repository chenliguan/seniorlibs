package com.seniorlibs.designpattern.singleton

import java.util.concurrent.atomic.AtomicLong

/**
 * 唯一递增 ID 号码生成器
 */
class Only_IdGenerator private constructor() {
    // AtomicLong是一个Java并发库中提供的一个原子变量类型,
    // 它将一些线程不安全需要加锁的复合操作封装为了线程安全的原子操作，
    // 比如下面会用到的incrementAndGet().
    private val id: AtomicLong = AtomicLong(0)

    fun getId(): Long {
        return id.incrementAndGet()
    }

    companion object {
        val instance = Only_IdGenerator()
    }
}

// IdGenerator使用举例
var id: Long = Only_IdGenerator.instance.getId()

