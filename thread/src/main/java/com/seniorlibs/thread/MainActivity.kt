package com.seniorlibs.thread

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.thread.aqs.SemaphoreTest
import com.seniorlibs.thread.asynctask.AsyncTaskTest
import com.seniorlibs.thread.atomic.AtomicIntegerTest
import com.seniorlibs.thread.collection.ArrayListTest
import com.seniorlibs.thread.collection.HashSetTest
import com.seniorlibs.thread.synchronize.SynchronizedTest
import com.seniorlibs.thread.threadpool.ThreadPoolManagerActivity
import kotlin.concurrent.thread

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/6/26.
 * Mender:
 * Modify:
 * Description: 测试线程
 */
class MainActivity : AppCompatActivity() {

    private lateinit var textView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testUiUpdate()
    }

    /**
     * 测试UI更新
     */
    private fun testUiUpdate() {
        textView = findViewById(R.id.tvUiTest)

        thread {
            // 1、直接在子线程中更新了 主线程创建的UI，却没有报错：
            findViewById<TextView>(R.id.tvUiTest).text = "子线程更新UI ${Thread.currentThread().name}"

            // 直接在子线程中更新了 主线程创建的UI，却没有报错：
            textView.text = "子线程更新UI ${Thread.currentThread().name}"
        }

//        thread {
//            // 2、加上休眠300毫秒，程序就崩溃了：因为ViewRootImp在onCreate()时还没创建；在onResume()时，即ActivityThread的handleResumeActivity()执行后才创建；
//            // 调用requestLayout()，走到checkThread()时就报错了
//            Thread.sleep(300)
//
//            // 2、直接在子线程中更新了 主线程创建的UI，却没有报错：
//            findViewById<TextView>(R.id.tvUiTest).text = "子线程更新UI ${Thread.currentThread().name}"
//
//            // 直接在子线程中更新了 主线程创建的UI，却没有报错：
//             textView.text = "子线程更新UI ${Thread.currentThread().name}"
//        }

//        void checkThread() {
//            if (mThread != Thread.currentThread()) {
//                throw new CalledFromWrongThreadException ("Only the original thread that created a view hierarchy can touch its views.");
//            }
//        }

        thread {
            // 3、子线程更新UI也行，但是只有 创建这个view的线程 才能操作这个view；
            // 换句话说：Android的UI更新(GUI)被设计成了单线程；
            Looper.prepare()
            val dialog = AlertDialog.Builder(this)
                    .apply {
                        setIcon(R.drawable.ic_launcher)
                        setTitle("子线程创建的对话框")
                        setOnDismissListener {
                            show()
                        }
                        setCancelable(true)
                        setNegativeButton("子线程更新 主线程创建的UI", object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                // android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
                                // 翻译后是：只有创建这个view的线程才能操作这个view；
                                textView.text = "子线程更新 主线程创建的UI ${Thread.currentThread().name}"
                            }
                        })

                        setPositiveButton("子线程更新 子线程创建的UI", object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                setTitle("子线程更新 子线程创建的UI ${Thread.currentThread().name}")
                            }
                        })
                    }.create()
            dialog.show()
            Looper.loop()
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
    fun textRejectedExecution(view: View?) {
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

    companion object {
        const val TAG = "seniorLibsThread"
    }
}