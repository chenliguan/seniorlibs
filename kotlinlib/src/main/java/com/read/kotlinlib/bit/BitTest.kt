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

    /**
     * 如何存储字符
     */
    fun storeChar() {
        val bytesUtf8 = "斗".toByteArray(charset("utf-8"))
        LogUtils.d(TAG, "bytesUtf8.size：${bytesUtf8.size}")
        for (b in bytesUtf8) {
            LogUtils.d(TAG, "bytesUtf8.toHexString：${Integer.toHexString(b.toInt())}")
        }
//        bytesUtf8.size：3
//        bytesUtf8.toHexString：ffffffe6
//        bytesUtf8.toHexString：ffffff96
//        bytesUtf8.toHexString：ffffff97

        val bytesUtf16 = "斗".toByteArray(charset("utf-16"))
        LogUtils.d(TAG, "bytesUtf16.size：${bytesUtf16.size}")
        for (b in bytesUtf16) {
            LogUtils.d(TAG, "bytesUtf16.toHexString：${Integer.toHexString(b.toInt())}")
        }
//        bytesUtf16.size：4
//        bytesUtf16.toHexString：ffffffff
//        bytesUtf16.toHexString：fffffffe
//        bytesUtf16.toHexString：ffffff97
//        bytesUtf16.toHexString：65

        val bytesUtf = "中".toByteArray(charset("utf-16"))
        LogUtils.d(TAG, "bytesUtf.size：${bytesUtf.size}")
        for (b in bytesUtf) {
            LogUtils.d(TAG, "bytesUtf.toHexString：${Integer.toHexString(b.toInt())}")
        }
//        bytesUtf.size：4
//        bytesUtf.toHexString：ffffffff
//        bytesUtf.toHexString：fffffffe
//        bytesUtf.toHexString：2d
//        bytesUtf.toHexString：4e

        val bytesChar = charArrayOf('A')
        LogUtils.d(TAG, "bytesChar.size：${bytesChar.size}")
        for (b in bytesChar) {
            LogUtils.d(TAG, "bytesChar.toHexString：${Integer.toHexString(b.toInt())}")
        }

        // emoji 代表表情
        val byte = Character.toChars(0x1F601)    // 1个笑脸，真实长度是1
        LogUtils.d(TAG, "byte.size：${byte.size} + emoji：${String(byte).length}")  // byte.size：2 + emoji：2

        // 类型转换
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
}