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
    }

    override fun onClick(v: View) {
        when (v.id) {
        }
    }

    /**
     * 测试ReentrantLock的lock()和Unlock()
     *
     * @param view
     */
    fun lockAndUnlock(view: View?) {
        ReentrantLockTest.lockAndUnlock()
    }

     /**
     * 测试fair()和unfair()分别是公平锁和非公平锁
     *
     * @param view
     */
    fun fairAndUnFair(view: View?) {
        ReentrantLockTest.fairAndUnFair()
    }

    /**
     * 测试Semaphore的acquire()
     *
     * @param view
     */
    fun testSemaphoreAcquire(view: View?) {
        SemaphoreTest.testAcquire()
    }

    /**
     * 测试Semaphore的tryAcquire
     *
     * @param view
     */
    fun testSemaphoreTryAcquire(view: View?) {
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

    /**
     * 测试CyclicBarrier
     *
     * @param view
     */
    fun testCyclicBarrier(view: View?) {
        CyclicBarrierTest.main()
    }
}