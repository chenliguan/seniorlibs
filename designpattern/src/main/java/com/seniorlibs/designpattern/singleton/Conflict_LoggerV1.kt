package com.seniorlibs.designpattern.singleton

import java.io.File
import java.io.FileWriter

/**
 * 实现了一个往文件中打印日志的Logger类
 */
class Conflict_LoggerV1 {
    private val writer: FileWriter

    init {
        val file = File("/Users/wangzheng/log.txt")
        // true表示追加写入
        writer = FileWriter(file, true)
    }

    fun log(message: String?) {
        // 同时写日志到 log.txt 文件中，那就有可能存在日志信息互相覆盖的情况
        writer.write(message)

        // 对象级别的锁：给 log() 函数加锁，同一时刻只允许一个线程调用执行 log() 函数
        // 问题：在不同的线程下，通过不同的对象调用执行 log() 函数，锁并不会起作用
        synchronized(this) {
            writer.write(message)
        }

        // 解决资源竞争问题：类级别的锁
        synchronized(Conflict_LoggerV1::class.java) {
            writer.write(message)
        }
    }


    // Logger 类的应用示例
    class UserController {
        private val logger = Conflict_LoggerV1()
        fun login(username: String, password: String?) {
            // ...省略业务逻辑代码...
            logger.log("$username logined!")
        }
    }

    class OrderController {
        private val logger = Conflict_LoggerV1()
        fun create(order: String) {
            // ...省略业务逻辑代码...
            logger.log("Created an order: $order")
        }
    }
}
