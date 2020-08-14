package com.seniorlibs.thread.handler

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.*
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.baselib.utils.LogUtils
import com.seniorlibs.thread.R
import kotlin.concurrent.thread

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/6/26.
 * Mender:
 * Modify:
 * Description: 测试线程
 */
class HandlerActivity : AppCompatActivity() {

    companion object {
        const val TAG = "seniorLibsThreadHandler"

        fun actionStart(context: Context?) {
            if (context == null) {
                return
            }
            val intent = Intent(context, HandlerActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)
    }

    /**
     * 测试子线程更新UI
     */
    fun testUiUpdate(view: View) {
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
     * 在主线程中给子线程的Handler发送信息
     */
    fun mainSendMessageToThread(view: View) {
        val thread = SubThread()
        thread.start()

        // 1.报空指针，因为：多线程并发的问题，当主线程执行到sendEmptyMessage时，子线程的Handler还没有初始化
//        thread.mHandler!!.sendEmptyMessage(0x123)
        // 2.解决方法是：主线程延时给子线程发消息，等待子线程的Handler完成初始化
        Handler().postDelayed(Runnable {
            thread.mHandler!!.sendMessage(obtainMessage())
        }, 1000)
        // 3.更优解决方法是：通过HandlerThread获取子线程的Looper，再在主线程初始化Handler，并传入子线程的Looper
        initHandlerThread()
    }

    private fun initHandlerThread() {
        val handlerThread = HandlerThread("Thread-1")
        handlerThread.start()
        val handler = MyHandler(handlerThread.looper)
        handler.sendMessage(obtainMessage())
    }

    /**
     * 子线程的Handler接收信息
     */
    private inner class SubThread : Thread() {
        var mHandler: Handler? = null

        override fun run() {
            Looper.prepare()
            mHandler = MyHandler(Looper.myLooper())
            Looper.loop()
        }
    }

    class MyHandler(looper: Looper?) : Handler(looper) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                0x123 -> LogUtils.e(TAG, "子线程的MyHandler接收信息：${msg.obj}")
            }
        }
    }

    private fun obtainMessage() : Message {
        val msg = Message.obtain()
        msg.what = 0x123 //消息的标识
        msg.obj = "B" // 消息的存放
        return msg
    }


}