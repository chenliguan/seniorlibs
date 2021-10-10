package com.seniorlibs.designpattern.singleton

import java.util.*

/**
 * 多例模式-递增 ID 号码生成器
 */
class Multi_IdGenerator private constructor(
    private val serverNo: Long,
    private val serverAddress: String
) {

    companion object {
        private const val SERVER_COUNT = 3
        private val instances = mutableMapOf<Long, Multi_IdGenerator>()

        init {
            instances[1L] = Multi_IdGenerator(1L, "192.134.22.138:8080")
            instances[2L] = Multi_IdGenerator(2L, "192.134.22.139:8080")
            instances[3L] = Multi_IdGenerator(3L, "192.134.22.140:8080")
        }
    }

    fun getInstance(serverNo: Long): Multi_IdGenerator? {
        return instances[serverNo]
    }

    val randomInstance: Multi_IdGenerator?
        get() {
            val no: Int = Random().nextInt(SERVER_COUNT) + 1
            return instances.get(no)
        }
}

