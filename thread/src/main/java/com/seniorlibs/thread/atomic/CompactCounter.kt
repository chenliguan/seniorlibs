package com.seniorlibs.thread.atomic

import java.util.concurrent.atomic.AtomicLongFieldUpdater

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/12/17.
 * Mender:
 * Modify:
 * Description: AtomicLongFieldUpdater
 */
class CompactCounter {

    @Volatile
    private var counter: Long = 0

    // 如果变量的名称不存在，会直接报错
//  private val updater: AtomicLongFieldUpdater<CompactCounter> = AtomicLongFieldUpdater.newUpdater(CompactCounter::class.java, "countersssss")
//  Caused by: java.lang.RuntimeException: java.lang.NoSuchFieldException: No field countersssss in class Lcom/seniorlibs/thread/atomic/CompactCounter;

    private val updater: AtomicLongFieldUpdater<CompactCounter> = AtomicLongFieldUpdater.newUpdater(CompactCounter::class.java, "counter")

    fun increase() {
        updater.incrementAndGet(this)
    }

    fun get(): Long {
        return counter
    }
}