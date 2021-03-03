package com.read.kotlinlib.memory

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.read.kotlinlib.R

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/2/28.
 * Mender:
 * Modify:
 * Description: 模拟非静态 Handler 引起的内存泄露
 */
class HandlerLeakActivity : AppCompatActivity() {

    companion object {

        const val TAG = "kotlin + HandlerLeakActivity : "

        fun actionStart(context: Context?) {
            if (context == null) {
                return
            }
            val intent = Intent(context, HandlerLeakActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val mHandler = LeakHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memoryleak)

        val imageView = findViewById<ImageView>(R.id.iv_memoryleak)
        initBitmap(imageView)

        clickHandler()
    }

    private fun initBitmap(imageView: ImageView) {
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.splash)
        imageView.setImageBitmap(bitmap)
    }

    private fun clickHandler() {
        mHandler.sendEmptyMessageDelayed(0x123, 10000)
        mHandler.sendEmptyMessageDelayed(0x123, 30000)
        mHandler.sendEmptyMessageDelayed(0x123, 60000)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private class LeakHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                0x123 -> {
                    // 模拟10秒的耗时
                    Thread.sleep(2000)
                }
            }
        }
    }
}