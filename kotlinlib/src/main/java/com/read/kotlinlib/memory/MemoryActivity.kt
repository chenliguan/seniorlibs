package com.read.kotlinlib.memory

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.read.kotlinlib.R

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/2/17
 * Mender:
 * Modify:
 * Description: 内存优化
 */
class MemoryActivity : AppCompatActivity() {

    companion object {

        const val TAG = "kotlin + MemoryActivity : "

        fun actionStart(context: Context?) {
            if (context == null) {
                return
            }
            val intent = Intent(context, MemoryActivity::class.java)
            context.startActivity(intent)
        }
    }

    object SingletonHolder {
        val INSTANCE = {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory)
    }

    /**
     * 模拟内存抖动
     *
     * @param view
     */
    fun testMemoryShake(view: View?) {
        val memory = MemoryShakeTest()
        memory.onClick()
    }

    /**
     * 模拟静态变量引起的内存泄露
     *
     * @param view
     */
    fun testMemoryLeak(view: View?) {
        MemoryLeakActivity.actionStart(this)
    }

    /**
     * 模拟非静态 Handler 引起的内存泄露
     *
     * @param view
     */
    fun testHandlerLeak(view: View?) {
        HandlerLeakActivity.actionStart(this)
    }

    /**
     * 修改图片加载的 Config，压缩 Bitmap 内存大小
     *
     * @param view
     */
    fun compressBitmap(view: View?) {
        BitmapActivity.actionStart(this)
    }

    /**
     * Bitmap 复用
     *
     * @param view
     */
    fun bitmapPool(view: View?) {
        BitmapPoolActivity.actionStart(this)
    }

    /**
     * Bitmap 缓存
     *
     * @param view
     */
    fun imageCache(view: View?) {
        ImageCacheActivity.actionStart(this)
    }

    /**
     * BitmapRegionDecoder 图片分片显示
     *
     * @param view
     */
    fun bitmapRegion(view: View?) {
        BitmapRegionActivity.actionStart(this)
    }
}