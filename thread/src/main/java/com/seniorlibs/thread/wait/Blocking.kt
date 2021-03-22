package com.seniorlibs.thread.wait

import java.util.*


/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/3/18
 * Mender:
 * Modify:
 * Description: 修改图片加载的 Config 实现 Bitmap 压缩
 */
class Blocking : Object()  {

    var buffer: Queue<String> = LinkedList()

    fun give(data: String?) {
        synchronized(this) {
            buffer.add(data)
            notify()
        }
    }

    @Throws(InterruptedException::class)
    fun take(): String? {
        synchronized(this) {
            while (buffer.isEmpty()) {
                wait()
            }
            return buffer.remove()
        }
    }



    fun notifyOnly() {
        synchronized(this) {
            notify()
        }
    }

    fun notifyAllOnly() {
        synchronized(this) {
            notifyAll()
        }
    }

    @Throws(InterruptedException::class)
    fun takeOnly(data: String?): String? {
        synchronized(this) {
            wait()
            return data
        }
    }
}