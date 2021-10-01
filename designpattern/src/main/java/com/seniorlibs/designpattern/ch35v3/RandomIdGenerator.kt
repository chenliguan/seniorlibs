package com.seniorlibs.designpattern.ch35v3

import android.util.Log
import androidx.annotation.VisibleForTesting
import com.seniorlibs.designpattern.ch35v2.LogTraceIdGenerator
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.*


/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/9/20
 * Mender:
 * Modify:
 * Description: 用于生成随机 ID 的 ID 生成器。这个类生成的ID不是绝对唯一的，但重复的概率很低。
 *
 *              第二轮重构：提高"随机 ID 生成算法"代码的可测试性
 */
open class RandomIdGenerator : LogTraceIdGenerator {

    /**
     * 生成随机ID。只有在极端情况下才可以复制 ID。
     *
     * @return 一个随机ID
     */
    override fun generate(): String {
        val substrOfHostName = getLastfieldOfHostName()
        val currentTimeMillis = System.currentTimeMillis()
        val randomString = generateRandomAlphameric(8)
        return String.format("%s-%d-%s",
            substrOfHostName, currentTimeMillis, randomString)
    }

    /**
     * 获取本地主机名和提取由分隔符“.”分割的名称字符串的最后一个字段。
     *
     * @return 主机名的最后一个字段。如果未获得主机名，则返回 null。
     */
    private fun getLastfieldOfHostName(): String? {
        var substrOfHostName: String? = null
        try {
            val hostName = InetAddress.getLocalHost().hostName
            substrOfHostName = getLastSubstrSplittedByDot(hostName)
        } catch (e: UnknownHostException) {
            Log.w("RandomIdGenerator", "Failed to get the host name. $e")
        }
        return substrOfHostName
    }

    /**
     * 提取由分隔符“.”分割的名称字符串的最后一个字段。
     *
     * 从 getLastfieldOfHostName() 函数中逻辑比较复杂的那部分依赖本地主机名的代码剥离出来，定义为 getLastSubstrSplittedByDot() 函数
     *
     * @param hostName String
     * @return String?
     */
    @VisibleForTesting
    internal fun getLastSubstrSplittedByDot(hostName: String?): String? {
        val tokens = hostName?.split(".")?.toTypedArray()
        if (tokens != null) {
            return tokens[tokens.size - 1]
        }
        return null
    }

    /**
     * 生成随机字符串，仅包含数字、大写字母和小写字母。
     *
     * @param 长度不能小于0
     * @return 随机字符串。如果 {@length} 为 0，则返回空字符串
     */
    @VisibleForTesting
    internal fun generateRandomAlphameric(length: Int): String {
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