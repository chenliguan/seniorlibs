package com.seniorlibs.thread.atomic

import java.util.concurrent.atomic.AtomicLong

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/12/17.
 * Mender:
 * Modify:
 * Description: AtomicLong
 */
class Counter {

    private val counter: AtomicLong = AtomicLong()

    fun increase() {
        counter.incrementAndGet()
    }

    fun get(): Long {
        return counter.get()
    }
}