package com.read.kotlinlib.basic

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.doOnPreDraw
import com.nostra13.universalimageloader.core.ImageLoader
import com.read.kotlinlib.R
import com.seniorlibs.baselib.utils.LogUtils
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis
import androidx.core.net.toUri as toUri

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/4/1
 * Mender:
 * Modify:
 * Description: 测试协程
 */

/*
GlobalScope.launch(Dispatchers.Main) {
    suspendSimpleDelay()
}
suspend fun suspendSimpleDelay(): Unit {
    withContext(Dispatchers.IO) {
        delay(10000)
    }
}
1.Kotlin协程中挂起的是什么？协程。
2.什么是协程？kotlin协程为launch()、async() 等中的代码，协同多个程序之间进行合作，帮助我们轻松的写出复杂的并发代码。
launch在创建的一个协程，在执行到某一个suspend函数(挂起函数)时，这个协程会被 suspend(被挂起)。
3.协程是执行在单线程中的吗？协程本质上还是一套基于原生Java Thread API 的封装。只要没有魔改JVM，start了几个线程，操作系统就会创建几个线程；
Kotlin协程只是做了一个类似线程池的封装，根本谈不上什么性能更好。协程是协作式任务, 线程是抢占式任务, 本质上两者都属于并发。
4.什么时候要用到协程的？需要切换线程的时候要用到协程;
5.协程有什么好用？Kotlin协程同多个程序之间进行合作，让我们更方便的来进行多线程开发，帮助我们轻松的写出复杂的并发代码，甚至还能用非常简单的方式实现原本不可能实现的并发任务。

1.挂起是怎么做到的？在suspend修饰的挂起函数中，调用另外一个协程自带的、内部实现了协程挂起代码的挂起函数withContext()，让它来做真正的挂起，实现线程切换的工作。
2.挂起的本质是什么？挂起的本质是切线程。
3.既然suspend不能真正的做到挂起函数，那么suspend的作用是什么呢? 作用：是提醒，提醒调用者该函数为耗时操作，
避免主线程调用了一个耗时操作。因此创建一个挂起函数，一定要在内部调用别的挂起函数，否则这个挂起函数就是没有必要的

1.从哪挂起？从当前正在执行它的线程挂起(脱离)，将协程挂到自己指定的线程中去执行，在执行完毕会切换回来；分离后当前的线程不会卡住，该干嘛干嘛去。
2.为什么挂起函数只能在协程里或者另一个挂起函数中调用？挂起之后是需要恢复的，也就是把线程给切回来，而恢复这个动作必须由协程实现。
 */

class CoroutineActivity : AppCompatActivity() {

    companion object {
        const val TAG = "kotlin + CoroutineActivity : "

        fun companionFun(): String {
            return "companionFun"
        }

        @JvmStatic
        fun jvm() {

        }

        fun actionStart(context: Context?) {
            if (context == null) {
                return
            }
            val intent = Intent(context, CoroutineActivity::class.java)
            context.startActivity(intent)
        }
    }

    // 单例：静态内部类式
    class Single private constructor() {
        companion object {
            fun get(): Single {
                return Holder.instance
            }
        }

        private object Holder {
            val instance = Single()
        }
    }

    lateinit var mRoot: View
    lateinit var mIvImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        initView()
        testCoroutine()
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyCoroutineScope()
    }

    private fun initView() {
        mRoot = findViewById<View>(R.id.ll_coroutine)
        mIvImage = findViewById<ImageView>(R.id.iv_image)
    }

    /**
     * kotlin简单测试
     *
     * @param view
     */
    fun testKotlin(view: View?) {
        testCoroutine()
    }

    /**
     * 测试协程
     */
    private fun testCoroutine() {
        // 协程程序
//        launch()
        // 桥接阻塞与非阻塞的世界
//        launchRunBlocking()
        // runBlocking包装函数的执行
//        runBlockings()
        // 显式（以非阻塞方式）等待所启动的后台Job执行结束：
//        runBlockingJoin()
        // 作用域构建器:coroutineScope
//        coroutineScopes()
        // 提取函数重构：挂起函数
//        suspendLaunch()
        // kotlin的core-ktx库
//        ktx()
        // 取消和超时
//        cancelTimeOut()
        // 组合挂起函数
//        testAsync()
        // 协程上下文与调度器
//        coroutineDispatcher()
        // 通过协程作用域，管理协程的生命周期
//        coroutineScopeLife()
        // 线程局部数据
//        threadLocalData()
        // 挂起函数 suspend，演示了launch(Dispatchers.Main)-->通过withContext(Dispatchers.IO)——>切换到IO线程
        suspend()
    }

    /**
     * 启动协程最常用的方式：GlobalScope.launch { }
     */
    private fun launch() {
        // 协程代码
        LogUtils.d(TAG, "Start ${Thread.currentThread().name}")
        GlobalScope.launch(Dispatchers.Main) {
            delay(1000L)
            LogUtils.d(TAG, "Hello World ${Thread.currentThread().name}")
        }
        LogUtils.d(TAG, "End ${Thread.currentThread().name}")
//        07-30 15:07:58.714 27267-27267/com.seniorlibs.app D/kotlin + CoroutineActivity :: Start main
//        07-30 15:07:58.755 27267-27267/com.seniorlibs.app D/kotlin + CoroutineActivity :: End main
//        07-30 15:07:59.791 27267-27267/com.seniorlibs.app D/kotlin + CoroutineActivity :: Hello World main

        // 等同于：线程代码
        LogUtils.d(TAG, "Start ${Thread.currentThread().name}")
        Thread {
            Thread.sleep(1000L)
            LogUtils.d(TAG, "Hello World ${Thread.currentThread().name}")
        }.start()
        LogUtils.d(TAG, "End ${Thread.currentThread().name}")
//        07-30 15:07:58.755 27267-27267/com.seniorlibs.app D/kotlin + CoroutineActivity :: Start main
//        07-30 15:07:58.756 27267-27267/com.seniorlibs.app D/kotlin + CoroutineActivity :: End main
//        07-30 15:07:59.757 27267-27795/com.seniorlibs.app D/kotlin + CoroutineActivity :: Hello World Thread-8434

//        GlobalScope.launch { // 在后台启动一个新的协程并继续，
//            delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
//            LogUtils.d(TAG, "launch World! 在延迟后打印输出") // 在延迟后打印输出
//        }
//        LogUtils.d(TAG, "launch Hello, 协程已在等待时主线程还在继续") // 协程已在等待时主线程还在继续
//        Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
//        LogUtils.d(TAG, "launch end") // 阻塞：主线程

//        07-24 11:16:57.890 3431-3431/? D/kotlin + CoroutineActivity :: launch Hello, 协程已在等待时主线程还在继续
//        07-24 11:16:58.894 3431-3472/? D/kotlin + CoroutineActivity :: launch World! 在延迟后打印输出
//        07-24 11:16:59.890 3431-3431/? D/kotlin + CoroutineActivity :: launch end
    }

    /**
     * 桥接阻塞与非阻塞的世界
     * 调用了runBlocking的主线程会一直阻塞，直到runBlocking内部的协程执行完毕
     */
    private fun launchRunBlocking() {
        GlobalScope.launch { // 在后台启动一个新的协程并继续，GlobalScope.launch
            delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
            LogUtils.d(TAG, "launchRunBlocking World! 在延迟后打印输出") // 在延迟后打印输出
        }
        LogUtils.d(TAG, "launchRunBlocking Hello, 协程已在等待时主线程还在继续") // 协程已在等待时主线程还在继续

        runBlocking {     // 显式使用 runBlocking 协程构建器来阻塞：主线程
            delay(2000L)  // ……我们延迟 2 秒来保证 JVM 的存活
        }
        LogUtils.d(TAG, "launchRunBlocking end") // runBlocking 协程构建器来阻塞：主线程

//        07-24 11:16:59.892 3431-3431/? D/kotlin + CoroutineActivity :: launchRunBlocking Hello, 协程已在等待时主线程还在继续
//        07-24 11:17:00.896 3431-3495/? D/kotlin + CoroutineActivity :: launchRunBlocking World! 在延迟后打印输出
//        07-24 11:17:01.902 3431-3431/? D/kotlin + CoroutineActivity :: launchRunBlocking end
    }

    /**
     * runBlocking包装函数的执行
     * runBlocking<Unit> {  } 作为用来启动顶层主协程的适配器。我们显式指定了其返回类型Unit，因为函数必须返回 Unit 类型。
     */
    private fun runBlockings() {
        fun main() = runBlocking<Unit> { // 开始执行主协程
            GlobalScope.launch { // 在后台启动一个新的协程并继续
                delay(1000L)
                LogUtils.d(TAG, "runBlockings World! 在延迟后打印输出") // 在延迟后打印输出
            }
            LogUtils.d(TAG, "runBlockings Hello, 主协程在这里会立即执行") // 主协程在这里会立即执行
            delay(2000L)      // 延迟 2 秒来保证 JVM 存活
            LogUtils.d(TAG, "runBlockings end") // 阻塞：主线程
        }
        main()
//        07-24 11:17:01.904 3431-3431/? D/kotlin + CoroutineActivity :: runBlockings Hello, 主协程在这里会立即执行
//        07-24 11:17:02.907 3431-3472/? D/kotlin + CoroutineActivity :: runBlockings World! 在延迟后打印输出
//        07-24 11:17:03.905 3431-3431/? D/kotlin + CoroutineActivity :: runBlockings end
    }

    /**
     * 显式（以非阻塞方式）等待所启动的后台Job执行结束：
     */
    private fun runBlockingJoin() {
        runBlocking {
            val job = GlobalScope.launch { // 启动一个新协程并保持对这个作业的引用
                delay(3000L)
                LogUtils.d(TAG, "runBlockingJoin World! ")
            }
            LogUtils.d(TAG, "runBlockingJoin Hello, ")
            job.join() // 等待直到子协程执行结束
            LogUtils.d(TAG, "runBlockingJoin end")
        }

//        07-24 11:24:15.891 5781-5781/? D/kotlin + CoroutineActivity :: runBlockingJoin Hello,
//        07-24 11:24:18.894 5781-5832/? D/kotlin + CoroutineActivity :: runBlockingJoin World!
//        07-24 11:24:18.896 5781-5781/? D/kotlin + CoroutineActivity :: runBlockingJoin end

        /**
         * runBlocking 在内的每个协程构建器都将 CoroutineScope 的实例添加到其代码块所在的作用域中
         * 在这个作用域中启动协程而无需显式 join 之，因为外部协程（示例中的runBlocking）直到在其作用域中启动的所有协程都执行完毕后才会结束。
         */
        runBlocking { // this: CoroutineScope --> 协程作用域
            launch { // 在 runBlocking 作用域中启动一个新协程
                delay(1000L)
                LogUtils.d(TAG, "runBlocking World! ")
            }
            LogUtils.d(TAG, "runBlocking Hello,")
        }
        LogUtils.d(TAG, "runBlocking scope is over") // 这一行在内嵌 launch 执行完毕后才输出

//        07-24 11:32:32.605 6189-6189/? D/kotlin + CoroutineActivity :: runBlocking Hello,
//        07-24 11:32:33.606 6189-6189/? D/kotlin + CoroutineActivity :: runBlocking World!
//        07-24 11:32:33.606 6189-6189/? D/kotlin + CoroutineActivity :: runBlocking scope is over
    }

    /**
     * 作用域构建器:coroutineScope 构建器声明自己的作用域。它会创建一个协程作用域并且在所有已启动子协程执行完毕之前不会结束
     * runBlocking 方法会阻塞当前线程来等待， 而 coroutineScope 只是挂起，会释放底层线程用于其他用途
     */
    private fun coroutineScopes() {
        runBlocking { // this: CoroutineScope
            launch {
                delay(200L)
                LogUtils.d(TAG, "coroutineScopes Task1 from runBlocking")
            }

            coroutineScope { // 创建一个协程作用域
                launch {
                    delay(500L)
                    LogUtils.d(TAG, "coroutineScopes Task2 from nested launch")
                }
                delay(100L)
                LogUtils.d(TAG, "coroutineScopes Task3 from coroutine scope") // 这一行会在内嵌 launch 之前输出
            }
            LogUtils.d(TAG, "coroutineScopes 4 Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
        }

//        07-24 11:35:45.105 6504-6504/? D/kotlin + CoroutineActivity :: coroutineScopes Task3 from coroutine scope
//        07-24 11:35:45.204 6504-6504/? D/kotlin + CoroutineActivity :: coroutineScopes Task1 from runBlocking
//        07-24 11:35:45.505 6504-6504/? D/kotlin + CoroutineActivity :: coroutineScopes Task2 from nested launch
//        07-24 11:35:45.505 6504-6504/? D/kotlin + CoroutineActivity :: coroutineScopes 4 Coroutine scope is over


        runBlocking { // this: CoroutineScope
            launch {
                delay(200L)
                LogUtils.d(TAG, "coroutineScope Task1 from runBlocking")
            }
        }

        runBlocking { // 创建一个协程作用域
            launch {
                delay(500L)
                LogUtils.d(TAG, "coroutineScope Task2 from nested launch")
            }
            delay(100L)
            LogUtils.d(TAG, "coroutineScope Task3 from coroutine scope") // 这一行会在内嵌 launch 之前输出
        }
        LogUtils.d(TAG, "coroutineScope 4 Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出

//        07-24 11:45:26.202 7305-7305/com.seniorlibs.app D/kotlin + CoroutineActivity :: coroutineScope Task1 from runBlocking
//        07-24 11:45:26.303 7305-7305/com.seniorlibs.app D/kotlin + CoroutineActivity :: coroutineScope Task3 from coroutine scope
//        07-24 11:45:26.703 7305-7305/com.seniorlibs.app D/kotlin + CoroutineActivity :: coroutineScope Task2 from nested launch
//        07-24 11:45:26.703 7305-7305/com.seniorlibs.app D/kotlin + CoroutineActivity :: coroutineScope 4 Coroutine scope is over
    }

    /**
     * 提取函数重构：挂起函数
     */
    private fun suspendLaunch() {
        // 调用了 runBlocking 的主线程会一直 阻塞 直到 runBlocking 内部的协程执行完毕，内部协程也运行在主线程（除非切换到其他线程）
        runBlocking { // 创建一个协程作用域
            launch {
                LogUtils.d(TAG, "suspendLaunch before doWord, ${Thread.currentThread()}")
                doWord()
            }
            LogUtils.d(TAG, "suspendLaunch Hello, ${Thread.currentThread()}") // 这一行会在内嵌 launch 之前输出
        }
//        kotlin + CoroutineActivity :: suspendLaunch Hello, Thread[main,5,main]
//        kotlin + CoroutineActivity :: suspendLaunch before doWord, Thread[main,5,main]
//        kotlin + CoroutineActivity :: suspendLaunch World! Thread[DefaultDispatcher-worker-1,5,main]

        // 重试 20 次
        repeat(20) {
            GlobalScope.launch {
                // 开启协程后，先打印一下进程名称和进程id
                // 这一行会在内嵌 launch 之前输出
                LogUtils.d(
                    TAG,
                    "threadName = " + Thread.currentThread().name + " threadId = " + Thread.currentThread().id
                )
                delay(1000L)
            }
        }
        // 本质上还是一套基于原生Java Thread API 的封装。只要没有魔改JVM，start了几个线程，操作系统就会创建几个线程；
        // Kotlin协程只是做了一个类似线程池的封装，根本谈不上什么性能更好
//        threadName = DefaultDispatcher-worker-3 threadId = 2627
//        threadName = DefaultDispatcher-worker-1 threadId = 2625
//        threadName = DefaultDispatcher-worker-1 threadId = 2625
//        threadName = DefaultDispatcher-worker-3 threadId = 2627
//        threadName = DefaultDispatcher-worker-3 threadId = 2627
//        threadName = DefaultDispatcher-worker-2 threadId = 2626
//        threadName = DefaultDispatcher-worker-1 threadId = 2625
//        threadName = DefaultDispatcher-worker-1 threadId = 2625
//        threadName = DefaultDispatcher-worker-2 threadId = 2626
//        threadName = DefaultDispatcher-worker-3 threadId = 2627
//        threadName = DefaultDispatcher-worker-2 threadId = 2626
//        threadName = DefaultDispatcher-worker-3 threadId = 2627
//        threadName = DefaultDispatcher-worker-2 threadId = 2626
//        threadName = DefaultDispatcher-worker-3 threadId = 2627
//        threadName = DefaultDispatcher-worker-2 threadId = 2626
//        threadName = DefaultDispatcher-worker-3 threadId = 2627
//        threadName = DefaultDispatcher-worker-1 threadId = 2625
//        ...............
    }

    // suspend 修饰的挂起函数。挂起的含义就是：暂时切走，稍后在切回来；就是切换线程，不过在执行完毕会切换回来。
    // 什么时候需要自定义挂起函数：耗时(特殊：等待)
    // 怎么写挂起函数：添加关键字 suspend，内部代码使用 withContext 获取他挂起函数包裹
    private suspend fun doWord(): Int {  // 写法1
        // withContext使用新CoroutineContext-->中Dispatchers.IO的dispatcher，将[块]的执行转移到 不同的线程，如果指定了一个新的调度程序，并返回到原始的调度程序
        withContext(Dispatchers.IO) {
            delay(5000L)
            LogUtils.d(TAG, "suspendLaunch World! ${Thread.currentThread()}")
        }
        return 1
    }

    private suspend fun doWord2(): Int = withContext(Dispatchers.IO) { // 写法2
        delay(5000L)
        LogUtils.d(TAG, "suspendLaunch World! ${Thread.currentThread()}")
        return@withContext 1
    }

    /**
     * kotlin的core-ktx库
     */
    private fun ktx() {
        // Kotlin创建一个Uri对象
        var s = "https://www.google.com"
        var uri = Uri.parse(s)
        // 使用Android KTX + Kotlin之后
        var ktx_uri = "https://www.google.com".toUri()

        // Kotlin
        getSharedPreferences("", Context.MODE_PRIVATE).edit().putString("", "").apply()
        // Kotlin + Android KTX
        getSharedPreferences("", Context.MODE_PRIVATE).edit {
            putString("", "")
        }

        mRoot.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    // ...
                    return true
                }
            })

        mRoot.doOnPreDraw {
            // ...
        }
    }

    /**
     * 取消和超时
     */
    private fun cancelTimeOut() {
        // 取消协程的执行
        runBlocking {
            val job = launch {
                repeat(1000) { i ->
                    LogUtils.d(TAG, "job: I'm sleeping $i ...")
                    delay(500L)
                }
            }
            delay(1300L) // 延迟一段时间
            LogUtils.d(TAG, "main: I'm tired of waiting!")
            job.cancel() // 取消该作业
            job.join() // 等待作业执行结束
            LogUtils.d(TAG, "main: Now I can quit.")
        }
//        07-28 21:13:45.343 20898-20898/? D/kotlin + CoroutineActivity :: job: I'm sleeping 0 ...
//        07-28 21:13:45.845 20898-20898/? D/kotlin + CoroutineActivity :: job: I'm sleeping 1 ...
//        07-28 21:13:46.346 20898-20898/? D/kotlin + CoroutineActivity :: job: I'm sleeping 2 ...
//        07-28 21:13:46.641 20898-20898/? D/kotlin + CoroutineActivity :: main: I'm tired of waiting!
//        07-28 21:13:46.661 20898-20898/? D/kotlin + CoroutineActivity :: main: Now I can quit.

        // 如果协程正在执行计算任务，并且没有检查取消的话，那么它是不能被取消的
        // 取消是协作的：协程的取消是协作的，一段协程代码必须协作才能被取消
        runBlocking {
            val startTime = System.currentTimeMillis()
            val job = launch(Dispatchers.Default) {
                var nextPrintTime = startTime
                var i = 0
                while (i < 5) { // 一个执行计算的循环，只是为了占用 CPU
                    // 每秒打印消息两次
                    if (System.currentTimeMillis() >= nextPrintTime) {
                        LogUtils.d(TAG, "job: I'm sleeping ${i++} ...")
                        nextPrintTime += 500L
                    }
                }
            }
            delay(1300L) // 等待一段时间
            LogUtils.d(TAG, "main: I'm tired of waiting!")
            job.cancelAndJoin() // 取消一个作业并且等待它结束
            LogUtils.d(TAG, "main: Now I can quit.")
//            07-28 21:16:27.689 21254-21361/? D/kotlin + CoroutineActivity :: job: I'm sleeping 0 ...
//            07-28 21:16:28.183 21254-21361/? D/kotlin + CoroutineActivity :: job: I'm sleeping 1 ...
//            07-28 21:16:28.683 21254-21361/? D/kotlin + CoroutineActivity :: job: I'm sleeping 2 ...
//            07-28 21:16:28.986 21254-21254/? D/kotlin + CoroutineActivity :: main: I'm tired of waiting!
//            07-28 21:16:29.183 21254-21361/? D/kotlin + CoroutineActivity :: job: I'm sleeping 3 ...
//            07-28 21:16:29.683 21254-21361/? D/kotlin + CoroutineActivity :: job: I'm sleeping 4 ...
//            07-28 21:16:29.683 21254-21254/? D/kotlin + CoroutineActivity :: main: Now I can quit.
        }

        // 使计算代码可取消
        runBlocking {
            val startTime = System.currentTimeMillis()
            val job = launch(Dispatchers.Default) {
                var nextPrintTime = startTime
                var i = 0
                while (isActive) { // 可以被取消的计算循环：while (i < 5) 替换为 while (isActive) 并重新运行它。isActive 是一个可以被使用在 CoroutineScope 中的扩展属性
                    // 每秒打印消息两次
                    if (System.currentTimeMillis() >= nextPrintTime) {
                        println("job: I'm sleeping ${i++} ...")
                        nextPrintTime += 500L
                    }
                }
            }
            delay(1300L) // 等待一段时间
            println("main: I'm tired of waiting!")
            job.cancelAndJoin() // 取消该作业并等待它结束
            println("main: Now I can quit.")
        }
//        07-28 21:24:42.338 25056-25124/com.seniorlibs.app D/kotlin + CoroutineActivity :: job: I'm sleeping 0 ...
//        07-28 21:24:42.831 25056-25124/com.seniorlibs.app D/kotlin + CoroutineActivity :: job: I'm sleeping 1 ...
//        07-28 21:24:43.331 25056-25124/com.seniorlibs.app D/kotlin + CoroutineActivity :: job: I'm sleeping 2 ...
//        07-28 21:24:43.635 25056-25056/com.seniorlibs.app D/kotlin + CoroutineActivity :: main: I'm tired of waiting!
//        07-28 21:24:43.831 25056-25124/com.seniorlibs.app D/kotlin + CoroutineActivity :: job: I'm sleeping 3 ...
//        07-28 21:24:44.331 25056-25124/com.seniorlibs.app D/kotlin + CoroutineActivity :: job: I'm sleeping 4 ...
//        07-28 21:24:44.331 25056-25056/com.seniorlibs.app D/kotlin + CoroutineActivity :: main: Now I can quit.


        // 在 finally 中释放资源
        runBlocking {
            val job = launch(Dispatchers.Default) {
                try {
                    repeat(1000) { i ->
                        LogUtils.d(TAG, "job: I'm sleeping $i ...")
                        delay(500L)
                    }
                } finally {
                    LogUtils.d(TAG, "job: I'm running finally")
                }
            }
            delay(1300L) // 延迟一段时间
            LogUtils.d(TAG, "main: I'm tired of waiting!")
            job.cancelAndJoin() // 取消该作业并且等待它结束
            LogUtils.d(TAG, "main: Now I can quit.")
        }
//        07-29 10:19:01.980 18347-18751/com.seniorlibs.app D/kotlin + CoroutineActivity :: job: I'm sleeping 1 ...
//        07-29 10:19:02.483 18347-18761/com.seniorlibs.app D/kotlin + CoroutineActivity :: job: I'm sleeping 2 ...
//        07-29 10:19:02.778 18347-18347/com.seniorlibs.app D/kotlin + CoroutineActivity :: main: I'm tired of waiting!
//        07-29 10:19:02.781 18347-18762/com.seniorlibs.app D/kotlin + CoroutineActivity :: job: I'm running finally
//        07-29 10:19:02.787 18347-18347/com.seniorlibs.app D/kotlin + CoroutineActivity :: main: Now I can quit.

        LogUtils.e(TAG, "===========================================================")

        // 运行不能取消的代码块
        runBlocking {
            val job = launch {
                try {
                    repeat(1000) { i ->
                        LogUtils.d(TAG, "job: I'm sleeping $i ...")
                        delay(500L)
                    }
                } finally {
                    // withContext函数
                    withContext(NonCancellable) {
                        LogUtils.d(TAG, "job: I'm running finally")
                        delay(1000L)
                        LogUtils.d(
                            TAG,
                            "job: And I've just delayed for 1 sec because I'm non-cancellable"
                        )
                    }
                }
            }
            delay(1300L) // 延迟一段时间
            LogUtils.d(TAG, "main: I'm tired of waiting!")
//            job.cancelAndJoin() // 取消该作业并等待它结束
            LogUtils.d(TAG, "main: Now I can quit.")
        }
//        07-29 10:19:02.789 18347-18347/com.seniorlibs.app D/kotlin + CoroutineActivity :: job: I'm sleeping 0 ...
//        07-29 10:19:03.290 18347-18347/com.seniorlibs.app D/kotlin + CoroutineActivity :: job: I'm sleeping 1 ...
//        07-29 10:19:03.791 18347-18347/com.seniorlibs.app D/kotlin + CoroutineActivity :: job: I'm sleeping 2 ...
//        07-29 10:19:04.089 18347-18347/com.seniorlibs.app D/kotlin + CoroutineActivity :: main: I'm tired of waiting!
//        07-29 10:19:04.093 18347-18347/com.seniorlibs.app D/kotlin + CoroutineActivity :: job: I'm running finally
//        07-29 10:19:05.094 18347-18347/com.seniorlibs.app D/kotlin + CoroutineActivity :: job: And I've just delayed for 1 sec because I'm non-cancellable
//        07-29 10:19:05.094 18347-18347/com.seniorlibs.app D/kotlin + CoroutineActivity :: main: Now I can quit.


        // 超时
        // withTimeout 抛出了 TimeoutCancellationException，它是 CancellationException 的子类
//        runBlocking {
//            withTimeout(1300L) {
//                repeat(1000) { i ->
//                    LogUtils.d(TAG, "I'm sleeping $i ...")
//                    delay(500L)
//                }
//            }
//        }
//        07-29 10:30:03.063 21169-21169/com.seniorlibs.app D/kotlin + CoroutineActivity :: I'm sleeping 0 ...
//        07-29 10:30:03.564 21169-21169/com.seniorlibs.app D/kotlin + CoroutineActivity :: I'm sleeping 1 ...
//        07-29 10:30:04.065 21169-21169/com.seniorlibs.app D/kotlin + CoroutineActivity :: I'm sleeping 2 ...
//        kotlinx.coroutines.TimeoutCancellationException: Timed out waiting for 1300 ms

        // withTimeoutOrNull 通过返回 null 来进行超时操作，从而替代抛出一个异常
        runBlocking {
            val result = withTimeoutOrNull(1300L) {
                repeat(1000) { i ->
                    LogUtils.d(TAG, "I'm sleeping $i ...")
                    delay(500L)
                }
                "Done" // 在它运行得到结果之前取消它
            }
            LogUtils.d(TAG, "Result is $result")
        }
    }


    /**
     * 组合挂起函数
     *
     * async 就类似于 launch。它启动了一个单独的协程，这是一个轻量级的线程并与其它所有的协程一起并发的工作。
     * 不同之处在于 launch 返回一个 Job 并且不附带任何结果值，而 async 返回一个 Deferred —— 一个轻量级的非阻塞 future， 这代表了一个将会在稍后提供结果的 promise
     */
    private fun testAsync() {
        // 运行在协程中的，只要像常规的代码一样 顺序 都是默认的
        runBlocking<Unit> {
            val time = measureTimeMillis {
                val jobOne = doSomethingUsefulOne()
                val jobTwo = doSomethingUsefulTwo()
                LogUtils.d(TAG, "testAsync1 The answer is ${jobOne + jobTwo}")
            }
            LogUtils.d(TAG, "testAsync1 Completed in $time ms")
        }
//        07-29 11:33:05.265 28695-28695/com.seniorlibs.app D/kotlin + CoroutineActivity :: The answer is 42
//        07-29 11:33:05.265 28695-28695/com.seniorlibs.app D/kotlin + CoroutineActivity :: Completed in 2004 ms

        // 使用 async 并发：快了两倍
        // async 并发的原理：CoroutineStart.LAZY参数之后，协程不会立马执行，直到调用了start()或await()才会触发协程的调度
        runBlocking<Unit> {
            val time = measureTimeMillis {
                val jobOne = async {
                    doSomethingUsefulOne()
                }
                val jobTwo = async {
                    doSomethingUsefulTwo()
                }
                // 使用.await()在一个延期的值上得到它的最终结果，但是Deferred也是一个Job，所以如果需要的话，你可以取消它
                LogUtils.d(
                    TAG,
                    "testAsync2 async 并发 The answer is ${jobOne.await() + jobTwo.await()}"
                )
            }
            LogUtils.d(TAG, "testAsync2 async 并发 Completed in $time ms")
        }
//        07-29 15:01:58.925 18529-18529/? D/kotlin + CoroutineActivity :: async 并发 The answer is 42
//        07-29 15:01:58.925 18529-18529/? D/kotlin + CoroutineActivity :: async 并发 Completed in 1010 ms

        // （惰性启动的 async）CoroutineStart.LAZY使async的并发失效了，async启动的两个协程并没有立即执行。
        // 而是直到调用jobOne.await()方法之后才开始执行，而await又会去等待结果，需要等待jobOne协程执行完毕后，
        // 遇到jobTwo.await()，才会触发jobTwo协程的执行，又要去等待。
        runBlocking {
            val time = measureTimeMillis {
                val jobOne = async(start = CoroutineStart.LAZY) {
                    doSomethingUsefulOne()
                }
                val jobTwo = async(start = CoroutineStart.LAZY) {
                    doSomethingUsefulTwo()
                }
                // 执行一些计算
//                jobOne.start() // 启动第一个
//                jobTwo.start() // 启动第二个
                LogUtils.d(
                    TAG,
                    "testAsync3 The answer is ${jobOne.await() + jobTwo.await()}"
                ) // 相当于 顺序
            }
            LogUtils.d(TAG, "testAsync3 Completed in $time ms")
        }
//        07-29 15:06:25.513 19451-19451/? D/kotlin + CoroutineActivity :: The answer is 42
//        07-29 15:06:25.513 19451-19451/? D/kotlin + CoroutineActivity :: Completed in 2004 ms

        // （惰性启动的 async）可以通过start方法，手动触发协程的立即执行，相当于 并发
        runBlocking {
            val time = measureTimeMillis {
                val jobOne = async(start = CoroutineStart.LAZY) {
                    doSomethingUsefulOne()
                }
                val jobTwo = async(start = CoroutineStart.LAZY) {
                    doSomethingUsefulTwo()
                }
                // 执行一些计算
                jobOne.start() // 启动第一个
                jobTwo.start() // 启动第二个
                LogUtils.d(TAG, "testAsync4 The answer is ${jobOne.await() + jobTwo.await()}")
            }
            LogUtils.d(TAG, "testAsync4 Completed in $time ms")
        }
//        07-29 15:06:25.513 19451-19451/? D/kotlin + CoroutineActivity :: The answer is 42
//        07-29 15:06:25.513 19451-19451/? D/kotlin + CoroutineActivity :: Completed in 1004 ms

        // 使用 async 的结构化并发
        runBlocking<Unit> {
            val time = measureTimeMillis {
                LogUtils.d(TAG, "testAsync5 The answer is ${concurrentSum()}")
            }
            LogUtils.d(TAG, "testAsync5 Completed in $time ms")
        }
//        07-29 15:13:26.491 20127-20127/? D/kotlin + CoroutineActivity :: The answer is 42
//        07-29 15:13:26.491 20127-20127/? D/kotlin + CoroutineActivity :: Completed in 1004 ms
    }

    // coroutineScope是挂起函数
    suspend fun concurrentSum(): Int = coroutineScope {
        val jobOne = async { doSomethingUsefulOne() }
        val jobTwo = async { doSomethingUsefulTwo() }
        jobOne.await() + jobTwo.await()
    }

    suspend fun doSomethingUsefulOne(): Int {
        delay(1000L) // 假设我们在这里做了些有用的事
        return 13
    }

    suspend fun doSomethingUsefulTwo(): Int {
        delay(1000L) // 假设我们在这里也做了一些有用的事
        return 29
    }


    /**
     * 协程上下文与调度器
     */
    private fun coroutineDispatcher() {
        // 调度器与线程
        // 协程上下文包含一个协程调度器：CoroutineDispatcher，它确定了哪些线程或与线程相对应的协程执行。协程调度器可以将协程限制在一个特定的线程执行，或将它分派到一个线程池，亦或是让它不受限地运行。
        //  launch 和 async 接收一个可选的 CoroutineContext 参数，用来显式的为一个新协程或其它上下文元素指定一个调度器
        runBlocking<Unit> {
            // 运行在父协程的上下文中，即 runBlocking 主协程
            launch {
                LogUtils.d(
                    TAG,
                    "main runBlocking: I'm working in thread ${Thread.currentThread().name}"
                )
            }
            // Dispatchers.Unconfined 协程调度器在调用它的线程启动了一个协程，但它仅仅只是运行到第一个挂起点。挂起后，它恢复线程中的协程，而这完全由被调用的挂起函数来决定
            // 不受限的——将工作在主线程中：适用于执行不消耗 CPU 时间的任务，以及不更新局限于特定线程的任何共享数据（如UI）的协程
            launch(Dispatchers.Unconfined) {
                LogUtils.d(TAG, "Unconfined: I'm working in thread ${Thread.currentThread().name}")
            }
            // 将会获取默认调度器
            launch(Dispatchers.Default) {
                LogUtils.d(TAG, "Default: I'm working in thread ${Thread.currentThread().name}")
            }
            // 将使它获得一个新的线程池，一个专用的线程池是一种非常昂贵的资源
            launch(newSingleThreadContext("MyOwnThread")) {
                LogUtils.d(
                    TAG,
                    "newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}"
                )
            }
        }
//        kotlin + CoroutineActivity :: Unconfined: I'm working in thread main
//        kotlin + CoroutineActivity :: Default: I'm working in thread DefaultDispatcher-worker-1
//        kotlin + CoroutineActivity :: main runBlocking: I'm working in thread main
//        kotlin + CoroutineActivity :: newSingleThreadContext: I'm working in thread MyOwnThread

        // 协程可以在一个线程上挂起并在其它线程上恢复
        fun log(msg: String) = println("[${Thread.currentThread()}] $msg")
        // 这里有三个协程，包括 runBlocking 内的主协程 (#1) ， 以及计算延期的值的另外两个协程 a (#2) 和 b (#3)
        runBlocking<Unit> {
            val jobA = async {
                log("${TAG} ${this} I 'm computing a piece of the answer")
                6
            }
            val jobB = async {
                log("${TAG} ${this} I'm computing another piece of the answer")
                7
            }
            log("${TAG} ${this} The answer is ${jobA.await() * jobB.await()}")
        }
//        [Thread[main,5,main]] DeferredCoroutine{Active}@cd11540 I'm computing a piece of the answer
//        [Thread[main,5,main]] DeferredCoroutine{Active}@8ffbc79 I'm computing another piece of the answer
//        [Thread[main,5,main]] BlockingCoroutine{Active}@4b6c8c3 The answer is 42

        // 子协程
        runBlocking<Unit> {
            // 启动一个协程来处理某种传入请求（request）
            val parentJob = launch {
                // 1.通过 GlobalScope 启动子协程
                // 新协程的作业没有父作业。 因此它与这个启动的作用域无关且独立运作
                GlobalScope.launch {
                    LogUtils.d(TAG, "job1: I run in GlobalScope and execute independently!")
                    delay(1000)
                    LogUtils.d(TAG, "job1: I am not affected by cancellation of the request")
                }
                // 2.通过 CoroutineScope.launch 启动子协程
                // 新协程承袭了父协程的上下文，它的Job将会成为父协程作业的子作业。当一个父协程被取消的时候，所有它的子协程也会被递归的取消。
                launch {
                    delay(100)
                    LogUtils.d(TAG, "job2: I am a child of the request coroutine")
                    delay(1000)
                    LogUtils.d(
                        TAG,
                        "job2: I will not execute this line if my parent request is cancelled"
                    ) // 不执行了
                }
            }
            delay(500)
            parentJob.cancel() // 取消 父协程 请求（parentJob）的执行
            delay(1000) // 延迟一秒钟来看看发生了什么
            LogUtils.d(TAG, "main: Who has survived request cancellation?")
//            kotlin + CoroutineActivity :: job1: I run in GlobalScope and execute independently!
//            kotlin + CoroutineActivity :: job2: I am a child of the request coroutine
//            kotlin + CoroutineActivity :: job1: I am not affected by cancellation of the request
//            kotlin + CoroutineActivity :: main: Who has survived request cancellation?
        }

        // 父协程的职责
        // 一个父协程总是等待所有的子协程执行结束。父协程并不显式的跟踪所有子协程的启动，并且不必使用 Job.join 在最后的时候等待它们
        runBlocking<Unit> {
            // 启动一个协程来处理某种传入请求（request）
            val parentJob = launch {
                repeat(3) { i -> // 启动少量的子作业
                    launch {
                        delay((i + 1) * 200L) // 延迟 200 毫秒、400 毫秒、600 毫秒的时间
                        LogUtils.d(TAG, "Coroutine $i is done")
                    }
                }
                LogUtils.d(
                    TAG,
                    "request: I'm done and I don't explicitly join my children that are still active"
                )
            }
//            parentJob.join() // 等待请求的完成，包括其所有子协程
            LogUtils.d(TAG, "Now processing of the request is complete")
        }
//        kotlin + CoroutineActivity :: request: I'm done and I don't explicitly join my children that are still active
//        kotlin + CoroutineActivity :: Coroutine 0 is done
//        kotlin + CoroutineActivity :: Coroutine 1 is done
//        kotlin + CoroutineActivity :: Coroutine 2 is done
//        kotlin + CoroutineActivity :: Now processing of the request is complete

        // 命名协程以用于调试
        runBlocking(CoroutineName("main")) {
            LogUtils.d(TAG, "${Thread.currentThread()} Started main coroutine")
            // 运行两个后台值计算
            val v1 = async(CoroutineName("v1coroutine")) {
                delay(500)
                LogUtils.d(TAG, "${Thread.currentThread()} Computing v1")
                252
            }
            val v2 = async(CoroutineName("v2coroutine")) {
                delay(1000)
                LogUtils.d(TAG, "${Thread.currentThread()} Computing v2")
                6
            }
            LogUtils.d(
                TAG,
                "${Thread.currentThread()} The answer for v1 / v2 = ${v1.await() / v2.await()}"
            )

            launch(Dispatchers.Default + CoroutineName("test")) {
                LogUtils.d(TAG, "${Thread.currentThread()} I'm working in thread")
            }
        }
//        Thread[main,5,main] Started main coroutine
//        Thread[main,5,main] Computing v1
//        Thread[main,5,main] Computing v2
//        Thread[main,5,main] The answer for v1 / v2 = 42
//        Thread[DefaultDispatcher-worker-3,5,main] I'm working in thread
    }


    /**
     * 通过协程作用域，管理协程的生命周期
     */

    /**
     * 通过创建一个 作用域：CoroutineScope 实例来管理协程的生命周期，并使它与 activity 的生命周期相关联。
     * 作用域：CoroutineScope 可以通过 CoroutineScope() 创建，或者通过 MainScope() 工厂函数创建
     */
    private val mainScope =
        CoroutineScope(Dispatchers.Default)  // private val mainScope = MainScope()

    private fun coroutineScopeLife() {
        runBlocking<Unit> {
            LogUtils.d(TAG, "Launched coroutines")
            doSomething() // 运行测试函数
        }
//        kotlin + CoroutineActivity :: Coroutine 0 is done
//        kotlin + CoroutineActivity :: Coroutine 1 is done
//        kotlin + CoroutineActivity :: Coroutine 2 is done
//        kotlin + CoroutineActivity :: Coroutine 3 is done
//        kotlin + CoroutineActivity :: Coroutine 4 is done
//        kotlin + CoroutineActivity :: destroyCoroutineScope，取消协程作用域
    }

    fun doSomething() {
        // 在示例中启动了 10 个协程，且每个都工作了不同的时长
        repeat(100) { i ->
            mainScope.launch {
                delay((i + 1) * 500L) // 延迟 200 毫秒、400 毫秒、600 毫秒等等不同的时间
                LogUtils.d(TAG, "Coroutine $i is done")
            }
        }
    }

    // Activity类结束时取消协程作用域
    fun destroyCoroutineScope() {
        mainScope.cancel()
        LogUtils.d(TAG, "destroyCoroutineScope，取消协程作用域")
    }


    /**
     * 线程局部数据
     */
    private fun threadLocalData() {
        runBlocking<Unit> {
            threadLocal.set("main")
            LogUtils.d(
                TAG,
                "Pre-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'"
            )
            val job = launch(Dispatchers.Default + threadLocal.asContextElement(value = "launch")) {
                LogUtils.d(
                    TAG,
                    "Launch start, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'"
                )
                // 将当前协同程序分派器的线程(或线程池)分配给其他要运行的协程
                yield()
                LogUtils.d(
                    TAG,
                    "After yield, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'"
                )
            }
            job.join()
            LogUtils.d(
                TAG,
                "Post-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'"
            )
        }
//        Pre-main, current thread: Thread[main,5,main], thread local value: 'main'
//        Launch start, current thread: Thread[DefaultDispatcher-worker-5,5,main], thread local value: 'launch'
//        After yield, current thread: Thread[DefaultDispatcher-worker-2,5,main], thread local value: 'launch'
//        Post-main, current thread: Thread[main,5,main], thread local value: 'main'
    }

    val threadLocal = ThreadLocal<String?>() // 声明线程局部变量

    /**
     * 挂起函数 suspend，演示了launch(Dispatchers.Main)-->通过withContext(Dispatchers.IO)——>切换到IO线程
     * 计算过程阻塞运行该代码的主线程 当这些值由异步代码计算时，我们可以使用 suspend 修饰符标记函数 simple，这样它就可以在不阻塞的情况下执行其工作并将结果作为列表返回：
     */
    private fun suspend() {
        LogUtils.d(TAG, "suspend start")
        // suspend 修饰的挂起函数。挂起的含义就是：暂时切走，稍后在切回来；就是切换线程，不过在执行完毕会切换回来。
        // 什么时候需要自定义挂起函数：耗时(特殊：等待)
        // 怎么写挂起函数：添加关键字 suspend，内部代码使用 withContext 获取他挂起函数包裹
        suspend fun simpleDelay(): Int {
            LogUtils.d(TAG, "simpleDelay1 ${Thread.currentThread().name}")
            // withContext使用新CoroutineContext-->中Dispatchers.IO的dispatcher，将[块]的执行转移到 不同的线程，如果指定了一个新的调度程序，并返回到原始的调度程序
            withContext(Dispatchers.IO) {
                LogUtils.d(TAG, "simpleDelay withContext1 ${Thread.currentThread().name}")
                // 假装我们在这里做了一些异步的事情
                delay(3000)
                LogUtils.d(TAG, "simpleDelay withContext2 ${Thread.currentThread().name}")
            }
            LogUtils.d(TAG, "simpleDelay2 ${Thread.currentThread().name}")
            return 11111
        }

        LogUtils.d(TAG, "suspend middle")
//        runBlocking<Unit> { } // 注意：runBlocking{ }是会阻塞当前线程来等待
        GlobalScope.launch(Dispatchers.Main) {
            LogUtils.e(TAG, "I'm working1 in thread ${Thread.currentThread().name}")
            // 挂起函数（子线程）执行完后，返回结果，再在协程中（主线程）获取其值
            val result = simpleDelay()
            LogUtils.e(
                TAG,
                "I'm working2 result：${result} in thread ${Thread.currentThread().name}"
            )
            LogUtils.e(TAG, "I'm working2 in thread ${Thread.currentThread().name}")
        }
        LogUtils.d(TAG, "suspend over")

//        01-14 14:59:30.113 CoroutineActivity :: suspend start
//        01-14 14:59:30.113 CoroutineActivity :: suspend middle
//        01-14 14:59:30.113 CoroutineActivity :: suspend over

//        01-14 14:59:30.114 CoroutineActivity :: I'm working1 in thread main
//        01-14 14:59:30.114 CoroutineActivity :: simpleDelay1 main
//        01-14 14:59:30.117 CoroutineActivity :: simpleDelay withContext1 DefaultDispatcher-worker-5  // 14:59:30

//        01-14 14:59:33.120 CoroutineActivity :: simpleDelay withContext2 DefaultDispatcher-worker-5  // 14:59:33
//        01-14 14:59:33.125 CoroutineActivity :: simpleDelay2 main

//        01-14 14:59:33.125 CoroutineActivity :: I'm working2 result：11111 in thread main
//        01-14 14:59:33.125 CoroutineActivity :: I'm working2 in thread main
    }


    /**
     * 协程切换线程获取图片
     *
     * @param view
     */
    fun testImage(view: View?) {
        LogUtils.d(TAG, "testImage 开始： ${Thread.currentThread().name}")

        // 在主线程开启协程
        val scope = CoroutineScope(Dispatchers.Default)
        // 1.👈 在 UI 线程开始
        scope.launch(Dispatchers.Main) {
            // 2.👈 切换到 IO 线程，并在执行完成后切回 UI 线程
            val url = getImageUrl()
            // 4.👈 回到 UI 线程更新 UI
            ImageLoader.getInstance().displayImage(url, mIvImage)
        }

        // 不阻塞主线程
        LogUtils.d(TAG, "testImage 结束： ${Thread.currentThread().name}")
    }

    /**
     * 挂起函数
     *
     * @return
     */
    suspend fun getImageUrl(): String {
        var url = ""
        withContext(Dispatchers.IO) {
            LogUtils.d(TAG, "testImage 切到子线程： ${Thread.currentThread().name}")
            // 3.👈 将会运行在 IO 线程
            url = "https://mmbiz.qpic.cn/mmbiz_jpg/AcTPSZQQ6D0TtiaYoQzElnygYvTwTnQJDEj6fiaO9GbTR0FVzYicl3OQQ8FxdtUHY59IjetUjkkcCZCxNjLdAteVQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1"
            Thread.sleep(4000)
            LogUtils.d(TAG, "testImage 子线程中获取图片： ${Thread.currentThread().name}")
        }
        return url
    }

    /**
     * 16:28:06.228 :: testImage 开始： main
     * 16:28:06.228 :: testImage 结束： main
     * 16:28:06.237 :: testImage 切到子线程： DefaultDispatcher-worker-6
     * 16:28:10.237 :: testImage 子线程中获取图片： DefaultDispatcher-worker-6
     */
}