package com.read.kotlinlib.bit

import com.seniorlibs.baselib.utils.LogUtils

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/10/4.
 * Mender:
 * Modify:
 * Description: 字节测试类
 */
class BitTest {

    companion object {
        const val TAG = "BitTest"
    }

    /** char
    GBK+GBK2312：'中'占3字节；
    UTF-8：'A'占1字节，'中'占3字节；
    UTF-16：'A'占2字节，'中'占2字节；
    UTF-32：'A'和'中'都占4字节；
    */

    /**
     * 如何存储字符
     */
    fun storeChar() {
        /**
         * 普通的字符串。注意：utf-16和utf-32在Unicode码前加了字节序
         */
        val sgb = "中"
        val bytesGb = sgb.toByteArray(charset("GBK"))
        LogUtils.d(TAG, "bytesGb.bytes：${bytesGb.size}  + bytesGb.char：${sgb.toCharArray().size}")
        for (b in bytesGb) {
            LogUtils.d(TAG, "bytesGb.toHexString：${Integer.toHexString(b.toInt())}")
        }
// "中"
//        bytesGb.bytes：2  + bytesGb.char：1
//        bytesGb.toHexString：ffffffd6
//        bytesGb.toHexString：ffffffd0

        val s8 = "A"
        val bytesUtf8 = s8.toByteArray(charset("utf-8"))
        LogUtils.d(TAG, "bytesUtf8.bytes：${bytesUtf8.size}  + bytesUtf8.char：${s8.toCharArray().size}")
        for (b in bytesUtf8) {
            LogUtils.d(TAG, "bytesUtf8.toHexString：${Integer.toHexString(b.toInt())}")
        }
// "中"
//        bytesUtf8.bytes：3  + bytesUtf8.char：1
//        bytesUtf8.toHexString：ffffffe6
//        bytesUtf8.toHexString：ffffff96
//        bytesUtf8.toHexString：ffffff97
// "A"
//        bytesUtf8.bytes：1  + bytesUtf8.char：1
//        bytesUtf8.toHexString：41

        val s16 = "A"
        val bytesUtf16 = s16.toByteArray(charset("utf-16"))
        LogUtils.d(TAG, "bytesUtf16.bytes：${bytesUtf16.size} + bytesUtf16.char：${s16.toCharArray().size}")
        for (b in bytesUtf16) {
            LogUtils.d(TAG, "bytesUtf16.toHexString：${Integer.toHexString(b.toInt())}")
        }
// "中"
//        bytesUtf16.bytes：4 + bytesUtf16.char：1
//        bytesUtf16.toHexString：ffffffff
//        bytesUtf16.toHexString：fffffffe
//        bytesUtf16.toHexString：ffffff97
//        bytesUtf16.toHexString：65
// "A"
//        bytesUtf8.bytes：4  + bytesUtf8.char：1
//        bytesUtf16.toHexString：ffffffff
//        bytesUtf16.toHexString：fffffffe
//        bytesUtf8.toHexString：0
//        bytesUtf8.toHexString：41

        val s32 = "中"
        val bytesUtf32 = s32.toByteArray(charset("utf-32"))
        LogUtils.d(TAG, "bytesUtf32.bytes：${bytesUtf32.size} + bytesUtf32.char：${s32.toCharArray().size}")
        for (b in bytesUtf32) {
            LogUtils.d(TAG, "bytesUtf32.toHexString：${Integer.toHexString(b.toInt())}")
        }
// "中"
//        bytesUtf32.bytes：8 + bytesUtf32.char：1
//        bytesUtf.toHexString：ffffffff
//        bytesUtf.toHexString：fffffffe
//        bytesUtf.toHexString：0
//        bytesUtf.toHexString：0
//        bytesUtf.toHexString：2d
//        bytesUtf.toHexString：4e
//        bytesUtf.toHexString：0
//        bytesUtf.toHexString：0
// "A"
//        bytesUtf32.bytes：8 + bytesUtf32.char：1
//        bytesUtf.toHexString：ffffffff
//        bytesUtf.toHexString：fffffffe
//        bytesUtf.toHexString：0
//        bytesUtf.toHexString：0
//        bytesUtf.toHexString：41
//        bytesUtf.toHexString：0
//        bytesUtf.toHexString：0
//        bytesUtf.toHexString：0


        /**
         * 中文生僻字
         */
        val sm8 = "𡃁"
        val bytesUtfm8 = sm8.toByteArray(charset("utf-8"))
        LogUtils.d(TAG, "bytesUtfm8.bytes：${bytesUtfm8.size}  + bytesUtfm8.char：${sm8.toCharArray().size}")
        for (b in bytesUtfm8) {
            LogUtils.d(TAG, "bytesUtfm8.toHexString：${Integer.toHexString(b.toInt())}")
        }
//        bytesUtfm8.bytes：4  + bytesUtfm8.char：2
//        bytesUtf8.toHexString：fffffff0
//        bytesUtf8.toHexString：ffffffa1
//        bytesUtf8.toHexString：ffffff83
//        bytesUtf8.toHexString：ffffff81


        /**
         * Bytes char
         */
        val chineseChar = charToByte('中')
        LogUtils.d(TAG, "chineseChar.byte：${chineseChar.size}")
        for (b in chineseChar) {
            LogUtils.d(TAG, "chineseChar.toHexString：${Integer.toHexString(b.toInt())}")
        }
//        chineseChar.byte：2
//        chineseChar.toHexString：4e
//        chineseChar.toHexString：2d

        val bytesCharA = charToByte('A')
        LogUtils.d(TAG, "bytesCharA.size：${bytesCharA.size}")
        for (b in bytesCharA) {
            LogUtils.d(TAG, "bytesCharA.toHexString：${Integer.toHexString(b.toInt())}")
        }
//        bytesCharA.size：2         调整为 --> 1
//        bytesCharA.toHexString：0  调整为 --> 41
//        bytesCharA.toHexString：41

        /**
         * Emoji
         */
        // emoji 代表表情        字符数是2(2个表情)；字符串长度是4；所以字符串长度 不一定等于 字符数
        val emoji  = "👦👩";
        LogUtils.d(TAG, "emoji.length：${emoji.length} + emoji.bytes：${emoji.toByteArray().size} + emoji.char：${emoji.toCharArray().size}")
//        emoji.length：4 + emoji.bytes：8 + emoji.char：4


        /**
         * 类型转换
         */
        var n = 'b'
        LogUtils.d(TAG, "类型转换 n++：${n++}")
        LogUtils.d(TAG, "类型转换 ++n：${++n}")
        LogUtils.d(TAG, "类型转换 n+1：${n+1}")
        LogUtils.d(TAG, "类型转换 n.toInt() + 1：${n.toInt() + 1}")
//        类型转换 n++：b
//        类型转换 ++n：d
//        类型转换 n+1：e
//        类型转换 n.toInt() + 1：101
    }

    fun charToByte(c: Char): ByteArray {
        val b = ByteArray(2)
        b[0] = (c.toInt() and 0xFF00 shr 8).toByte()
        b[1] = (c.toInt() and 0xFF).toByte()
        return b
    }
}