package com.read.kotlinlib.memory

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.read.kotlinlib.R
import com.read.kotlinlib.memory.listener.CallBack
import com.read.kotlinlib.memory.listener.CallBackManager.addCallBack
import com.read.kotlinlib.memory.listener.CallBackManager.removeCallBack

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/2/28.
 * Mender:
 * Modify:
 * Description: 模拟静态变量引起的内存泄露
 */
class MemoryLeakActivity : AppCompatActivity(),
    CallBack {

    companion object {

        const val TAG = "kotlin + MemoryActivity : "

        fun actionStart(context: Context?) {
            if (context == null) {
                return
            }
            val intent = Intent(context, MemoryLeakActivity::class.java)
            context.startActivity(intent)
        }

        var memoryLeak: ImageView? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memoryleak)

        val imageView = findViewById<ImageView>(R.id.iv_memoryleak)
        initBitmap(imageView)

        // View 默认会持有一个 Context 的引用，如果将其置为 static
        memoryLeak = ImageView(this)
        // 方法区中的静态变量引用了 Context
        addCallBack(this)
    }

    private fun initBitmap(imageView: ImageView) {
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.splash)
        imageView.setImageBitmap(bitmap)
    }

    override fun onDestroy() {
        super.onDestroy()
        removeCallBack(this)
    }

    override fun dpOperate() {
        // do sth
    }
}