package com.read.kotlinlib.memory

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.read.kotlinlib.R

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/2/28.
 * Mender:
 * Modify:
 * Description: 模拟存活线程引起的内存泄露
 */
class ThreadLeakActivity : AppCompatActivity() {

    companion object {

        const val TAG = "kotlin + ThreadLeakActivity : "

        fun actionStart(context: Context?) {
            if (context == null) {
                return
            }
            val intent = Intent(context, ThreadLeakActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memoryleak)

        val imageView = findViewById<ImageView>(R.id.iv_memoryleak)
        initBitmap(imageView)

        clickThread()
    }

    private fun initBitmap(imageView: ImageView) {
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.splash)
        imageView.setImageBitmap(bitmap)
    }

    private fun clickThread() {
        object : Thread() {
            override fun run() {
                super.run()
                while (true) {
                    try {
                        // 注释后：LeakCanary 和 Memory Profiler 可能排查不到泄漏，但是 MAT 可以排查到。
//                        initBitmap(ImageView(this@ThreadLeakActivity))
                        println("Thread running!!")
                        sleep(300)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}