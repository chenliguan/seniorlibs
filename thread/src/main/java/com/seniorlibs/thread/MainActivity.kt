package com.seniorlibs.thread

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.thread.aqs.CountDownLatchTest
import com.seniorlibs.thread.aqs.SemaphoreTest
import com.seniorlibs.thread.asynctask.AsyncTaskTest
import com.seniorlibs.thread.atomic.AtomicIntegerTest
import com.seniorlibs.thread.atomic.AtomicLongFieldUpdaterTest
import com.seniorlibs.thread.basic.DeadlockTest
import com.seniorlibs.thread.basic.InterruptTest
import com.seniorlibs.thread.basic.ThreadNumTest
import com.seniorlibs.thread.collection.ArrayListTest
import com.seniorlibs.thread.collection.HashMapTest
import com.seniorlibs.thread.collection.HashSetTest
import com.seniorlibs.thread.handler.HandlerActivity
import com.seniorlibs.thread.synchronize.SynchronizedTest
import com.seniorlibs.thread.threadpool.ThreadPoolManagerActivity

/**
 * Author: chen
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
//        findViewById<View>(R.id.test_handler).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
//            R.id.test_handler -> testHandler()
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
     * 测试Atomic原子性
     *
     * @param view
     */
    fun atomicTest(view: View?) {
        AtomicIntegerTest.mainTest()
        AtomicLongFieldUpdaterTest.mainTest()
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
     * 测试HashSet是否线程安全
     *
     * @param view
     */
    fun testHashSet(view: View?) {
        HashSetTest.main()
    }

    /**
     * 测试HashMap是否线程安全
     *
     * @param view
     */
    fun testHashMap(view: View?) {
        // 多线程下，扩容期间取出的值不准确
//        HashMapTest.resizeBug()
        // 为什么HashMap的"key"部分存放自定义的对象时，需要重写equals()和hashcode()方法？
        HashMapTest.testHashConflict()
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
    fun testHandler(view: View) {
        HandlerActivity.actionStart(this)
    }

    /**
     * 测试中断/停止线程 和 Java至少会创建几个线程呢？
     */
    fun testInterrupt(view: View) {
        InterruptTest.rawInterrupt()
        InterruptTest.unInterrupt()
        InterruptTest.supportInterrupted()
        InterruptTest.supportIsInterrupted()
        InterruptTest.volatileBoolean()
        InterruptTest.volatileBooleanNot()

        // 至少会创建几个线程呢
        ThreadNumTest.numTest()
    }

    /**
     * 测试死锁
     */
    fun testDeadLock(view: View) {
        DeadlockTest.deadLockTest()
    }
}