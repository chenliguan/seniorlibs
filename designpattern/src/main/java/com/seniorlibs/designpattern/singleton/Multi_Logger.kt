package com.seniorlibs.designpattern.singleton

import java.util.concurrent.ConcurrentHashMap

/**
 * 多例模式-Logger
 * 同一个 logger name 获取到的对象实例是相同的，不同的 logger name 获取到的对象实例是不同的
 */
class Multi_Logger private constructor() {
    fun log() {
        //...
    }

    companion object {
        private val instances = ConcurrentHashMap<String, Multi_Logger>()

        fun getInstance(loggerName: String): Multi_Logger? {
            instances.putIfAbsent(loggerName, Multi_Logger())
            return instances[loggerName]
        }
    }
}

// l1==l2, l1!=l3
var l1 = Multi_Logger.getInstance("User.class")
var l2 = Multi_Logger.getInstance("User.class")
var l3 = Multi_Logger.getInstance("Order.class")
