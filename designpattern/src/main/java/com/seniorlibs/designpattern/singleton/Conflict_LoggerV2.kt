package com.seniorlibs.designpattern.singleton

import java.io.File
import java.io.FileWriter

/**
 * 实现了一个往文件中打印日志的Logger类
 */
class Conflict_LoggerV2 private constructor() {
    private val writer: FileWriter
    fun log(message: String?) {
        writer.write(message)
    }

    companion object {
        // 程序中只允许创建一个 Logger 对象，所有的线程共享使用的这一个 Logger 对象，共享一个 FileWriter 对象，
        // 而 FileWriter 本身是对象级别线程安全的，也就避免了多线程情况下写日志会互相覆盖的问题
        val instance = Conflict_LoggerV2()
    }

    init {
        val file = File("/Users/wangzheng/log.txt")
        writer = FileWriter(file, true) //true表示追加写入
    }


    // Logger类的应用示例：
    class UserController {
        fun login(username: String, password: String?) {
            // ...省略业务逻辑代码...
            Conflict_LoggerV2.instance.log("$username logined!")
        }
    }

    class OrderController {
        fun create(order: String) {
            // ...省略业务逻辑代码...
            Conflict_LoggerV2.instance.log("Created a order: " + order.toString())
        }
    }
}

