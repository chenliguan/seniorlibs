package com.seniorlibs.thread.aqs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.thread.R
import com.seniorlibs.thread.threadpool.ThreadPoolManagerActivity

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/12/29.
 * Mender:
 * Modify:
 * Description: 测试AQS
 */
class AqsActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "seniorLibsThreadAQS"

        fun actionStart(context: Context?) {
            if (context == null) {
                return
            }
            val intent = Intent(context, AqsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aqs)

        initView()
    }

    private fun initView() {
//        findViewById<View>(R.id.test_handler).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
//            R.id.test_handler -> testHandler()
        }
    }

    /**
     * 测试Semaphore
     *
     * @param view
     */
    fun testAcquire(view: View?) {
        SemaphoreTest.testAcquire()
        SemaphoreTest.testTryAcquire()
    }

    /**
     * 测试CountDownLatch
     *
     * @param view
     */
    fun testCountDownLatch(view: View?) {
        CountDownLatchTest.main()
    }
}