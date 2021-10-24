package com.seniorlibs.designpattern.refactor.ch35v2

import android.util.Log
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.*

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/9/20
 * Mender:
 * Modify:
 * Description: 第一轮重构：提高"随机 ID 生成算"法代码的可读性
 */
class RandomIdGenerator : LogTraceIdGenerator {

    /**
     * generate() 函数中的三个 if 逻辑重复了，且实现过于复杂，我们要对其进行简化
     *
     * @return String?
     */
    override fun generate(): String {
        val substrOfHostName = getLastfieldOfHostName()
        val currentTimeMillis = System.currentTimeMillis()
        val randomString = generateRandomAlphameric(8)
        return String.format("%s-%d-%s",
            substrOfHostName, currentTimeMillis, randomString)
    }

    /**
     * 将获取 hostName 的代码抽离出来，定义为 getLastfieldOfHostName() 函数
     *
     * @return String?
     */
    private fun getLastfieldOfHostName(): String? {
        var substrOfHostName: String? = null
        try {
            val hostName: String = InetAddress.getLocalHost().getHostName()
            val tokens = hostName.split("\\.").toTypedArray()
            substrOfHostName = tokens[tokens.size - 1]
            return substrOfHostName
        } catch (e: UnknownHostException) {
            Log.w("Failed to get the host name.", e)
        }
        return substrOfHostName
    }

    /**
     * 将随机数生成的代码抽离出来，定义为 generateRandomAlphameric() 函数
     */
    private fun generateRandomAlphameric(length: Int): String {
        val randomChars = CharArray(length)
        var count = 0
        val random = Random()
        while (count < length) {
            val maxAscii = 'z'.toInt()
            val randomAscii: Int = random.nextInt(maxAscii)
            val isDigit = randomAscii >= '0'.toInt() && randomAscii <= '9'.toInt()
            val isUppercase = randomAscii >= 'A'.toInt() && randomAscii <= 'Z'.toInt()
            val isLowercase = randomAscii >= 'a'.toInt() && randomAscii <= 'z'.toInt()
            if (isDigit || isUppercase || isLowercase) {
                randomChars[count] = randomAscii.toChar()
                ++count
            }
        }
        return String(randomChars)
    }
}