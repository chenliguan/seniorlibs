package com.read.kotlinlib.memory

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/2/27.
 * Mender:
 * Modify:
 * Description: 模拟内存抖动
 */
class MemoryShakeTest {

    @SuppressLint("HandlerLeak")
    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            // 创造内存抖动
            for (index in 0..100) {
                val arg = arrayOfNulls<String>(100000)
            }
            sendEmptyMessageDelayed(0, 30)
        }
    }

    fun onClick() {
        mHandler.sendEmptyMessage(0)
    }

    fun removeCallbacksAndMessages() {
        mHandler.removeCallbacksAndMessages(null)
    }
}