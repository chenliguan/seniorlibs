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
        val bytesUtf81 = "斗".toByteArray(charset("utf-8"))
        LogUtils.d(TAG, "bytesUtf81.size：${bytesUtf81.size}")
        for (b in bytesUtf81) {
            LogUtils.d(TAG, "bytesUtf81.toHexString：${Integer.toHexString(b.toInt())}")
        }

        val bytesUtf8 = "斗".toByteArray(charset("utf-16"))
        LogUtils.d(TAG, "bytesUtf8.size：${bytesUtf8.size}")
        for (b in bytesUtf8) {
            LogUtils.d(TAG, "bytesUtf8.toHexString：${Integer.toHexString(b.toInt())}")
        }

        val bytes = "中".toByteArray(charset("utf-16"))
        LogUtils.d(TAG, "bytes.size：${bytes.size}")
        for (b in bytes) {
            LogUtils.d(TAG, "bytes.toHexString：${Integer.toHexString(b.toInt())}")
        }

        // emoji 代表表情
        val byte = Character.toChars(0x1F601)    // 1个笑脸，真实长度是1
        LogUtils.d(TAG, "byte.size：${byte.size} + emoji：${String(byte).length}")  // byte.size：2 + emoji：2
    }
}