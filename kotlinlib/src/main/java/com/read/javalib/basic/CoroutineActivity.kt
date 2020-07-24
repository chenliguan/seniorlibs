package com.read.javalib.basic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.read.javalib.R
import com.seniorlibs.baselib.utils.LogUtils
import kotlinx.coroutines.*

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/4/1
 * Mender:
 * Modify:
 * Description: 测试协程
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        testCoroutine()
    }

    /**
     * kotlin简单测试
     *
     * @param view
     */
    fun testKotlin(view: View?) {
        BasicActivity.actionStart(this)
    }

    /**
     * 测试协程
     */
    private fun testCoroutine() {
//        launch()
//        launchRunBlocking()
//        runBlockings()
//        runBlockingJoin()
        coroutineScopes()
        suspendLaunch()


    }

    /**
     * 协程程序
     */
    private fun launch() {
        GlobalScope.launch { // 在后台启动一个新的协程并继续
            delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
            LogUtils.d(TAG, "launch World! 在延迟后打印输出") // 在延迟后打印输出
        }
        LogUtils.d(TAG, "launch Hello, 协程已在等待时主线程还在继续") // 协程已在等待时主线程还在继续
        Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
        LogUtils.d(TAG, "launch end") // 阻塞：主线程

//        07-24 11:16:57.890 3431-3431/? D/kotlin + CoroutineActivity :: launch Hello, 协程已在等待时主线程还在继续
//        07-24 11:16:58.894 3431-3472/? D/kotlin + CoroutineActivity :: launch World! 在延迟后打印输出
//        07-24 11:16:59.890 3431-3431/? D/kotlin + CoroutineActivity :: launch end
    }

    /**
     * 桥接阻塞与非阻塞的世界
     * 调用了runBlocking的主线程会一直阻塞，直到runBlocking内部的协程执行完毕
     */
    private fun launchRunBlocking() {
        GlobalScope.launch { // 在后台启动一个新的协程并继续
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
        runBlocking { // this: CoroutineScope
            launch { // 在 runBlocking 作用域中启动一个新协程
                delay(1000L)
                LogUtils.d(TAG, "runBlocking World! ")
            }
            LogUtils.d(TAG,"runBlocking Hello,")
        }

//        07-24 11:32:32.605 6189-6189/? D/kotlin + CoroutineActivity :: runBlocking Hello,
//        07-24 11:32:33.606 6189-6189/? D/kotlin + CoroutineActivity :: runBlocking World!
    }

    /**
     * 作用域构建器:coroutineScope 构建器声明自己的作用域。它会创建一个协程作用域并且在所有已启动子协程执行完毕之前不会结束
     * runBlocking 方法会阻塞当前线程来等待， 而 coroutineScope 只是挂起，会释放底层线程用于其他用途
     */
    private fun coroutineScopes() {
        runBlocking { // this: CoroutineScope
            launch {
                delay(200L)
                LogUtils.d(TAG,"coroutineScopes Task1 from runBlocking")
            }

            runBlocking { // 创建一个协程作用域
                launch {
                    delay(500L)
                    LogUtils.d(TAG,"coroutineScopes Task2 from nested launch")
                }
                delay(100L)
                LogUtils.d(TAG,"coroutineScopes Task3 from coroutine scope") // 这一行会在内嵌 launch 之前输出
            }
            LogUtils.d(TAG,"coroutineScopes 4 Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
        }

//        07-24 11:35:45.105 6504-6504/? D/kotlin + CoroutineActivity :: coroutineScopes Task3 from coroutine scope
//        07-24 11:35:45.204 6504-6504/? D/kotlin + CoroutineActivity :: coroutineScopes Task1 from runBlocking
//        07-24 11:35:45.505 6504-6504/? D/kotlin + CoroutineActivity :: coroutineScopes Task2 from nested launch
//        07-24 11:35:45.505 6504-6504/? D/kotlin + CoroutineActivity :: coroutineScopes 4 Coroutine scope is over


        runBlocking { // this: CoroutineScope
            launch {
                delay(200L)
                LogUtils.d(TAG,"coroutineScope Task1 from runBlocking")
            }
        }

        runBlocking { // 创建一个协程作用域
            launch {
                delay(500L)
                LogUtils.d(TAG, "coroutineScope Task2 from nested launch")
            }
            delay(100L)
            LogUtils.d(TAG,"coroutineScope Task3 from coroutine scope") // 这一行会在内嵌 launch 之前输出
        }
        LogUtils.d(TAG,"coroutineScope 4 Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出

//        07-24 11:45:26.202 7305-7305/com.seniorlibs.app D/kotlin + CoroutineActivity :: coroutineScope Task1 from runBlocking
//        07-24 11:45:26.303 7305-7305/com.seniorlibs.app D/kotlin + CoroutineActivity :: coroutineScope Task3 from coroutine scope
//        07-24 11:45:26.703 7305-7305/com.seniorlibs.app D/kotlin + CoroutineActivity :: coroutineScope Task2 from nested launch
//        07-24 11:45:26.703 7305-7305/com.seniorlibs.app D/kotlin + CoroutineActivity :: coroutineScope 4 Coroutine scope is over
    }

    /**
     * 提取函数重构：挂起函数
     */
    private fun suspendLaunch() {
        runBlocking { // 创建一个协程作用域
            launch {
                doWord()
            }
            LogUtils.d(TAG,"suspendLaunch Hello,") // 这一行会在内嵌 launch 之前输出
        }
    }

    // suspend 修饰的挂起函数
    private suspend fun doWord() {
        delay(500L)
        LogUtils.d(TAG, "suspendLaunch World!")
    }

}