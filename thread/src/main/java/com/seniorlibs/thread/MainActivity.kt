package com.seniorlibs.thread

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.thread.aqs.SemaphoreTest
import com.seniorlibs.thread.asynctask.AsyncTaskTest
import com.seniorlibs.thread.atomic.AtomicIntegerTest
import com.seniorlibs.thread.collection.ArrayListTest
import com.seniorlibs.thread.collection.HashSetTest
import com.seniorlibs.thread.handler.HandlerActivity
import com.seniorlibs.thread.synchronize.SynchronizedTest
import com.seniorlibs.thread.threadpool.ThreadPoolManagerActivity

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/6/26.
 * Mender:
 * Modify:
 * Description: 测试线程
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "seniorLibsThreadMain"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        findViewById<View>(R.id.test_handler).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.test_handler -> testHandler()
        }
    }

    /**
     * 测试synchronized
     *
     * @param view
     */
    fun synchronizedTest(view: View?) {
        SynchronizedTest.mainTest()
    }

    /**
     * 测试原子性Integer
     *
     * @param view
     */
    fun atomicIntegerTest(view: View?) {
        AtomicIntegerTest.mainTest()
    }

    /**
     * 测试ArrayList和Collections.synchronizedList(new ArrayList<>());
     *
     * @param view
     */
    fun testWriteArrayListError(view: View?) {
        ArrayListTest.testWriteArrayListError()
    }

    /**
     * 测试Semaphore
     *
     * @param view
     */
    fun testAcquire(view: View?) {
        SemaphoreTest.testAcquire()
    }

    fun testTryAcquire(view: View?) {
        SemaphoreTest.testTryAcquire()
    }

    /**
     * 测试HashSet是否线程安全
     *
     * @param view
     */
    fun testHashSet(view: View?) {
        HashSetTest.main()
    }

    /**
     * 测试线程池的拒绝策略：为什么线程池容量不够抛出异常rejectedExecution？
     *
     * @param view
     */
    fun testRejectedExecution(view: View?) {
        ThreadPoolManagerActivity.actionStart(this)
    }

    /**
     * 测试AsyncTask的execute()
     *
     * @param view
     */
    fun testExecute(view: View?) {
        AsyncTaskTest.testExecute()
    }

    /**
     * 测试AsyncTask的executeOnExecutor()
     *
     * @param view
     */
    fun testExecuteOnExecutor(view: View?) {
        AsyncTaskTest.testExecuteOnExecutor()
    }

    /**
     * 测试Handler
     */
    private fun testHandler() {
        HandlerActivity.actionStart(this)
    }
}