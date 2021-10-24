package com.seniorlibs.designpattern.refactor.ch35v1

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
 * Description: "能用"的 ID 生成器的开发
 */
class IdGenerator {

    fun generate(): String {
        var id = ""
        try {
            var hostName: String = InetAddress.getLocalHost().getHostName()
            val tokens = hostName.split("\\.").toTypedArray()
            if (tokens.isNotEmpty()) {
                hostName = tokens[tokens.size - 1]
            }
            val randomChars = CharArray(8)
            var count = 0
            val random = Random()
            while (count < 8) {
                val randomAscii: Int = random.nextInt(122)
                if (randomAscii in 48..57) {
                    randomChars[count] = ('0'.toInt() + (randomAscii - 48)).toChar()
                    count++
                } else if (randomAscii in 65..90) {
                    randomChars[count] = ('A'.toInt() + (randomAscii - 65)).toChar()
                    count++
                } else if (randomAscii in 97..122) {
                    randomChars[count] = ('a'.toInt() + (randomAscii - 97)).toChar()
                    count++
                }
            }
            id = String.format("%s-%d-%s", hostName,
                System.currentTimeMillis(), String(randomChars))
        } catch (e: UnknownHostException) {
            Log.w("Failed to get the host name.", e)
        }
        return id
    }
}