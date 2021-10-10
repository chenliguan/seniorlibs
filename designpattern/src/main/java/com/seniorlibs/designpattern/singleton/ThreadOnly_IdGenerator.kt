package com.seniorlibs.designpattern.singleton

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

/**
 * 线程唯一递增 ID 号码生成器
 */
class ThreadOnly_IdGenerator private constructor() {

    private val id = AtomicLong(0)

    fun getId(): Long {
        return id!!.incrementAndGet()
    }

    companion object {
        private val instances = ConcurrentHashMap<Long?, ThreadOnly_IdGenerator?>()

        val instance: ThreadOnly_IdGenerator?
            get() {
                val currentThreadId = Thread.currentThread().id
                // 如果不存在才赋值
                instances.putIfAbsent(currentThreadId, ThreadOnly_IdGenerator())
                return instances[currentThreadId]
            }
    }
}

