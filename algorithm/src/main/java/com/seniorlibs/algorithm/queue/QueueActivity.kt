package com.seniorlibs.algorithm.queue

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.stack.StackActivity
import com.seniorlibs.baselib.utils.LogUtils
import java.util.*

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/9/10
 * Mender:
 * Modify:
 * Description: 队列
 */
class QueueActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "QueueActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, QueueActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queue)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_design_circular_deque).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_design_circular_deque -> {
                val circularDeque = CircularDeque(5) // 设置容量大小为3
                LogUtils.e(TAG, "" + circularDeque.insertFront(7))
                Thread.sleep(200)
                LogUtils.e(TAG, "" + circularDeque.insertLast(0))
                Thread.sleep(200)
                LogUtils.e(TAG, "" + circularDeque.getFront())
                Thread.sleep(200)
                LogUtils.e(TAG, "" + circularDeque.insertLast(3))
                Thread.sleep(200)
                LogUtils.e(TAG, "" + circularDeque.getFront())
                Thread.sleep(200)
                LogUtils.e(TAG, "" + circularDeque.insertFront(9))
                Thread.sleep(200)
                LogUtils.e(TAG, "" + circularDeque.getRear())
                Thread.sleep(100)
                LogUtils.e(TAG, "" + circularDeque.getFront())
                Thread.sleep(100)
                LogUtils.e(TAG, "" + circularDeque.getFront())
                Thread.sleep(100)
                LogUtils.e(TAG, "" + circularDeque.deleteLast())
                Thread.sleep(100)
                LogUtils.e(TAG, "" + circularDeque.getRear())
            }

            else -> {
            }
        }
    }


}
