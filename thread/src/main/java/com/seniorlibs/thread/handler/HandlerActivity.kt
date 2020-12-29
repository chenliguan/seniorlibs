package com.seniorlibs.thread.handler

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.*
import android.os.MessageQueue.IdleHandler
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.baselib.utils.LogUtils
import com.seniorlibs.thread.R
import kotlin.concurrent.thread

/**
 * Author: chen
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
                                // 抛出异常：android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
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
        val thread = LooperThread()
        thread.start()

        // 1.报空指针，因为：多线程并发的问题，当主线程执行到sendEmptyMessage时，子线程的Handler还没有初始化
//        thread.mHandler!!.sendEmptyMessage(0x123)
        // 2.解决方法是：主线程延时给子线程发消息，等待子线程的Handler完成初始化
        Handler().postDelayed(Runnable {
            thread.mHandler!!.sendMessage(obtainSyncMessage(0x123, "同步消息"))
        }, 1000)
        // 3.更优解决方法是：通过HandlerThread获取子线程的Looper，再在主线程初始化Handler，并传入子线程的Looper
        initHandlerThread()
    }

    // 通过HandlerThread获取子线程的Looper，再在主线程初始化Handler，并传入子线程的Looper
    private fun initHandlerThread() {
        val handlerThread = HandlerThread("Thread-1")
        handlerThread.start()
        val handler = MyHandler(handlerThread.looper)
        handler.sendMessage(obtainSyncMessage(0x123, "同步消息"))
    }

    /**
     * 子线程的Handler接收信息
     */
    private inner class LooperThread : Thread() {
        var mHandler: Handler? = null

        override fun run() {
            Looper.prepare()

            // 存在报空指针风险，因为：多线程并发的问题，当主线程执行到sendEmptyMessage时，子线程的Handler还没有初始化
            mHandler = MyHandler(Looper.myLooper())

            Looper.loop()
        }

        /**
         * // 官方最基础案例：在主线程中给子线程的Handler发送信息
         *  class LooperThread extends Thread {
         *      public Handler mHandler;
         *
         *      public void run() {
         *          Looper.prepare();
         *
         *          mHandler = new Handler() {
         *              public void handleMessage(Message msg) {
         *                  // process incoming messages here
         *              }
         *          };
         *
         *          Looper.loop();
         *      }
         *  }
         */
    }

    private class MyHandler(looper: Looper?) : Handler(looper) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                0x123 -> LogUtils.d(TAG, "子线程的MyHandler接收信息：${msg.obj}")

                0x140 -> LogUtils.d(TAG, "接收同步信息：${msg.obj}")
                0x130 -> LogUtils.d(TAG, "开启同步屏障，优先处理异步消息，MyHandler接收的异步信息：${msg.obj}")
            }
        }
    }

    // 同步消息
    private fun obtainSyncMessage(what: Int, str: String): Message {
        val msg = Message.obtain()
        msg.what = what //消息的标识
        msg.obj = str // 消息的存放
        return msg
    }


    /**
     * 开启同步屏障，优先处理异步消息
     * https://juejin.im/post/6844903910113705998
     * https://blog.csdn.net/start_mao/article/details/98963744
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun postSyncBarrier(view: View) {
        val handler = MyHandler(Looper.getMainLooper())

        // 发送同步消息
        handler.sendMessageDelayed(obtainSyncMessage(0x140, "同步消息1"), 1000)
        handler.sendMessageDelayed(obtainSyncMessage(0x140, "同步消息2"), 2000)
        handler.sendMessageDelayed(obtainSyncMessage(0x140, "同步消息3"), 3000)

        // 插入同步屏障
        sendSyncBarrier(handler)

        // 发送异步消息
        handler.sendMessageDelayed(obtainAsyncMessage(0x130, "异步信息1"), 1000)
        handler.sendMessageDelayed(obtainAsyncMessage(0x130, "异步信息2"), 2000)

        // 移除屏障
        removeSyncBarrier(handler)
        val asyncMsg3 = obtainAsyncMessage(0x130, "异步信息3")
        handler.sendMessage(asyncMsg3)
    }

    // 屏障token
    var mToken: Int = 0

    // 反射：往消息队列插入同步屏障
    @SuppressLint("DiscouragedPrivateApi")
    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun sendSyncBarrier(handler: Handler) {
        try {
            val method = MessageQueue::class.java.getDeclaredMethod("postSyncBarrier", Long::class.java)
            method.isAccessible = true
            mToken = method.invoke(handler.looper.queue) as Int
        } catch (e: Exception) {
            e.printStackTrace()
            LogUtils.e(TAG, "sendSyncBarrier：${e.printStackTrace()}")
        }
    }

    // 反射：移除屏障
    @SuppressLint("DiscouragedPrivateApi")
    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun removeSyncBarrier(handler: Handler) {
        try {
            val method = MessageQueue::class.java.getDeclaredMethod("removeSyncBarrier", Int::class.javaPrimitiveType)
            method.isAccessible = true
            method.invoke(handler.looper.queue, mToken)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            LogUtils.e(TAG, "removeSyncBarrier：${e.printStackTrace()}")
        }
    }

    // 异步消息
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private fun obtainAsyncMessage(what: Int, str: String): Message {
        val msg = Message.obtain()
        msg.what = what
        msg.obj = str
        msg.isAsynchronous = true  // 直接设置消息为异步
        return msg
    }



    /**
     * IdleHandler的原理
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun idleHandler(view: View) {
        val handler = MyHandler(Looper.getMainLooper())
        handler.sendMessageDelayed(obtainSyncMessage(0x140, "延迟1秒处理的消息1"), 1000)
        handler.sendMessageDelayed(obtainSyncMessage(0x140, "延迟1秒处理的消息2"), 1000)
        // 发送3个空闲消息，每个消息耗时1000ms，总3000ms；进入空闲消息处理后，上面的2个延时1000ms的消息必须在3个空闲消息处理完后才被执行
        handler.looper.queue.addIdleHandler(mIdleHandler)
        handler.sendMessage(obtainSyncMessage(0x140, "立即执行的同步消息"))  // 优先在空闲消息前处理了
        handler.looper.queue.addIdleHandler(mIdleHandler)
        handler.looper.queue.addIdleHandler(mIdleHandler)

//        15:43:28.721 : 接收同步信息：立即执行的同步消息
//        15:43:29.722 : 空闲时做一些骚操作 0
//        15:43:30.722 : 空闲时做一些骚操作 1
//        15:43:31.723 : 空闲时做一些骚操作 2
//        15:43:31.728 : 接收同步信息：延迟1秒处理的消息1
//        15:43:31.728 : 接收同步信息：延迟1秒处理的消息2
    }

    /**
     * IdleHandler的原理2
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun idleHandler2(view : View) {
        val handler = MyHandler(Looper.getMainLooper())
        handler.sendMessageDelayed(obtainSyncMessage(0x140, "延迟10秒处理的消息3"), 6000)
        if (handler.looper.queue.isIdle) {
            handler.looper.queue.addIdleHandler(mIdleHandler)
        }
        handler.postDelayed(Runnable {
            handler.looper.queue.addIdleHandler(mIdleHandler)
        }, 2000)

//        13:52:37.704 : 空闲时做一些骚操作 3
//        13:52:39.706 : 空闲时做一些骚操作 4
//        13:52:42.707 r: 接收同步信息：延迟10秒处理的消息3
    }

    var num: Int = 0

    /**
     * 由于onResume()和performTraversals()本身都是在Looper的事件循环中执行，所以IdleHandler的queueIdle()方法一定会在ui绘制完毕且MessageQueue无消息处理时执行。
     * 因此，把一些ui线程执行的耗时逻辑放在IdleHandler中执行，以此来优化页面的启动时间
     */
    private var mIdleHandler: IdleHandler = IdleHandler {
        Thread.sleep(1000)
        LogUtils.e(TAG, "空闲时做一些骚操作 ${num++}")
        // true会保留，每到空闲都会执行；false执行一次后会remove
        false
    }
}