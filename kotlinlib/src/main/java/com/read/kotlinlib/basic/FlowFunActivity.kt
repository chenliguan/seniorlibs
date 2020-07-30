package com.read.kotlinlib.basic

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.read.kotlinlib.R
import com.read.kotlinlib.basic.FlowFunActivity.Companion.TAG
import com.seniorlibs.baselib.utils.LogUtils
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/4/1
 * Mender:
 * Modify:
 * Description: 测试异步流
 */
class FlowFunActivity : AppCompatActivity() {

    companion object {
        const val TAG = "kotlin + FlowActivity : "

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
            val intent = Intent(context, FlowFunActivity::class.java)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)

        initView()
        // 测试高阶函数
        testFun()
        // 测试流
        testFlow()
        // 测试异常
        testException()
        // 通道：提供了一种便捷的方法使单个值在多个协程之间进行相互传输的方法
        testChannel()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun initView() {
        mRoot = findViewById<View>(R.id.ll_coroutine)
    }

    /**
     * 测试高阶函数
     */
    private fun testFun() {
        // block闭包
//        testBlock()
        // 高阶函数
//        higherFun()
    }

    /**
     * block闭包
     */
    private fun testBlock() {
        // 如果闭包是唯一的一个参数，则闭包或方法参数所在的圆括号也可以省略
        // 1、block: () -> Unit
        measure({
            LogUtils.d(TAG, "measure( { } )")
        })
        measure() {
            LogUtils.d(TAG, "measure() { }")
        }
        measure {  // == measure() { }
            LogUtils.d(TAG, "measure { }")
        }

        // 2、block: (String) -> Unit
        measureStr() {
            LogUtils.d(TAG, "measureStr() { } + it： ${it}")  // measureStr() { } + it： measureStr
        }

        // 如果闭包是多个参数，最后一个参数可以将闭包从参数圆括号中提取出来接在最后
        measureDoubleParam("参数一", {
            LogUtils.d(TAG, "measureDoubleParam (参数一, { })")  // measureDoubleParam (参数一, { })
        })
        measureDoubleParam("参数一") {
            LogUtils.d(TAG, "measureDoubleParam (参数一) { }")  // measureDoubleParam (参数一) { }
        }

        // 以下是类似Groovy的语法，作为参考 : clouser.call() 或 clouser1.call('groovy1')
        clouser.runCatching({
            LogUtils.d(TAG, "clouser.runCatching ( { } ) ${this}")  // clouser.runCatching ( { } ) Function0<kotlin.Unit>
        })
        clouser.runCatching() {
            LogUtils.d(TAG, "clouser.runCatching() { } ${this}")  // clouser.runCatching() { } Function0<java.lang.String>
        }
        clouserStr.runCatching() {
            LogUtils.d(TAG, "clouserStr.runCatching() { } ${this}")  // clouserStr.runCatching() { } Function1<java.lang.String, java.lang.String>
        }


        // 支持return退出lambda表达式函数，解决原lambda 表达式不能使包含它的函数返回问题。
        fun measureReturn() {
            LogUtils.e(TAG, "measureReturn111 --> measure() { }")
            measureRn {
                // 因为标识为 inline 的函数会被插入到调用出，此时 return 肯定是 return 到该整个方法
                LogUtils.e(TAG, "measureReturn222 --> measure() { }")
                return  // 如果不使用 inline，这里代码会被报错。因为「不允许这么做」
                LogUtils.e(TAG, "measureReturn333 --> measure() { }")
            }
        }
        measureReturn()

//        LogUtils.e(TAG, "measureReturn111 --> measure() { }")
//
//        LogUtils.e(TAG, "measureRn111 --> block: () -> Unit")
//        val start = System.currentTimeMillis()
//
//        // 因为标识为 inline 的函数会被插入到调用出，此时 return 肯定是 return 到该整个方法
//        LogUtils.e(TAG, "measureReturn222 --> measure() { }")
//        return  // 如果不使用 inline，这里代码会被报错。因为「不允许这么做」   // 注意：在这里return了
//        LogUtils.e(TAG, "measureReturn333 --> measure() { }")               // 这里不再打印
//
//        LogUtils.e(TAG, "measureRn222 --> block: () -> Unit")
//        return System.currentTimeMillis() - start

//        07-29 14:22:12.471 15744-15744/? E/kotlin + CoroutineActivity :: measureReturn111 --> measure() { }
//        07-29 14:22:12.471 15744-15744/? E/kotlin + CoroutineActivity :: measureRn111 --> block: () -> Unit
//        07-29 14:22:12.471 15744-15744/? E/kotlin + CoroutineActivity :: measureReturn222 --> measure() { }

        // 如果希望只内联一部分传给内联函数的 lambda 表达式参数，那么可以用 noinline 修饰符标记不希望内联的函数参数
        // 内联函数的「函数参数」 不允许作为参数传递给非内联的函数，
//        inline fun measureNoinlineFun(noinline block: () -> Unit) {
//            // 这里会报错。。。
//            measureNoinline { block }
//        }
//        measureNoinlineFun {
//        }
    }

    // block 本身是一个函数
    // 当一个函数被内联 inline 标注后，在调用它的地方，会把这个函数方法体中的所以代码移动到调用的地方，而不是通过方法间压栈进栈的方式，从而使得方法的调用栈少一层
    // 应该使用 inline 的地方： 带有 lambda 参数的函数
    inline fun measure(block: () -> Unit): Long {
        val start = System.currentTimeMillis()
        block()
        return System.currentTimeMillis() - start
    }

    // 当不添加 inline 时，代码中，多出了一个类，增加内存的分配；
    // 类是：com/read/kotlinlib/basic/CoroutineActivity$testBlock$4.INSTANCE : Lcom/read/kotlinlib/basic/CoroutineActivity$testBlock$4
    inline fun measureStr(block: (str: String) -> Unit): Long {  // == fun measureStr(block: (String) -> Unit): Long {
        val start = System.currentTimeMillis()
        block("measureStr")
        return System.currentTimeMillis() - start
    }

    fun measureDoubleParam(param: String, block: () -> Unit): Long {
        val start = System.currentTimeMillis()
        block()
        LogUtils.d(TAG, "measureDoubleParam ${param}")  // measureDoubleParam 参数一
        return System.currentTimeMillis() - start
    }

    var clouser = {
        LogUtils.d(TAG, "var clouser = clouser")
        "clouser"
    }
    var clouserStr = { name: String ->
        LogUtils.d(TAG, "var clouserStr = ${name}")
        "clouserStr"
    }

    // 如果不使用 inline，上面代码会被报错。因为「不允许这么做」
    inline fun measureRn(block: () -> Unit): Long {
        LogUtils.e(TAG, "measureRn111 --> block: () -> Unit")
        val start = System.currentTimeMillis()
        block()
        LogUtils.e(TAG, "measureRn222 --> block: () -> Unit")
        return System.currentTimeMillis() - start
    }

    fun measureNoinline(block: () -> Unit): Long {
        val start = System.currentTimeMillis()
        block()
        return System.currentTimeMillis() - start
    }


    /**
     * 高阶函数
     */
    private fun higherFun() {
        // 1、将函数用作函数参数的情况的高阶函数（sumBy函数的源码）
        val testStr = "abc"
        val sum = testStr.sumBy { it.toInt() }
        LogUtils.d(TAG, "高阶函数testStr.sumBy = ${sum}")  // 高阶函数testStr.sumBy = 294

        // 2、将函数用作一个函数的返回值的高阶函数
        // （1）run(block: () -> R): R：
        val str = "kotlin"
        run {
            val str = "java"   // 和上面的变量不会冲突
            LogUtils.d(TAG, "str1 = $str")
        }
        LogUtils.d(TAG, "str2 = $str")
//        kotlin + FlowActivity :: str1 = java
//        kotlin + FlowActivity :: str2 = kotlin
        // （2）T.run(block: T.() -> R): R
        val str2 = "kotlin"
        str2.run {
            LogUtils.d(TAG, "length = ${this.length}")
            LogUtils.d(TAG, "first = ${first()}")
            LogUtils.d(TAG, "last = ${last()}")
        }
//        kotlin + FlowActivity :: length = 6
//        kotlin + FlowActivity :: first = k
//        kotlin + FlowActivity :: last = n

        // 3、with函数：with函数的返回值指定了receiver为接收者
        val str3 = "kotlin"
        with(str3) {
            LogUtils.d(TAG, "length = ${this.length}")
            LogUtils.d(TAG, "first = ${first()}")
            LogUtils.d(TAG, "last = ${last()}")
        }
//        kotlin + FlowActivity :: length = 6
//        kotlin + FlowActivity :: first = k
//        kotlin + FlowActivity :: last = n

        // 4、T.apply()函数：唯一的区别是T.apply执行完了block()函数后，返回了自身对象。而T.run是返回了执行的结果
        fun newInstance(id: Int, name: String, age: Int): Fragment {
            val fragment = Fragment()
            fragment.toString()
            // ......
            return fragment
        }

        // 改进方法
        fun newInstance1(id: Int, name: String, age: Int) = Fragment().apply {
            val fragment = Fragment()
            fragment.toString()
            // ......
        }

        // 5、T.also函数：参数block(this)函数传入了自身对象this。故而这个函数的作用是用block函数调用自身对象即是it，最后在返回自身对象
        // 实例说明其和T.apply的区别
        "kotlin".also {
            LogUtils.d(TAG, "T.also 结果：${it.plus("-java")}")
        }.also {
            LogUtils.d(TAG, "T.also 结果：${it.plus("-php")}")
        }
        "kotlin".apply {
            LogUtils.d(TAG, "T.apply 结果：${this.plus("-java")}")
        }.apply {
            LogUtils.d(TAG, "T.apply 结果：${this.plus("-php")}")
        }
//        kotlin + FlowActivity :: T.also 结果：kotlin-java  // 输出结果是相同的
//        kotlin + FlowActivity :: T.also 结果：kotlin-php
//        kotlin + FlowActivity :: T.apply 结果：kotlin-java
//        kotlin + FlowActivity :: T.apply 结果：kotlin-php

        // 6、T.let函数：和T.also一样，函数中的参数block函数传入了自身对象；但是该函数返回了转换后的自身对象
        "kotlin".let {
            LogUtils.d(TAG, "T.apply 原字符串：$it")            // kotlin
            it.reversed()
        }.let {
            LogUtils.d(TAG, "T.apply 反转字符串后的值：$it")    // niltok
            it.plus("-java")
        }.let {
            LogUtils.d(TAG, "T.apply 新的字符串：$it")          // niltok-java
        }
//        kotlin + FlowActivity :: T.apply 原字符串：kotlin
//        kotlin + FlowActivity :: T.apply 反转字符串后的值：niltok
//        kotlin + FlowActivity :: T.apply 新的字符串：niltok-java
        "kotlin".also {
            LogUtils.d(TAG, "T.also 原字符串：$it")             // kotlin
            it.reversed()
        }.also {
            LogUtils.d(TAG, "T.also 反转字符串后的值：$it")     // kotlin
            it.plus("-java")
        }.also {
            LogUtils.d(TAG, "T.also 新的字符串：$it")          // kotlin
        }
//        kotlin + FlowActivity :: T.also 原字符串：kotlin
//        kotlin + FlowActivity :: T.also 反转字符串后的值：kotlin
//        kotlin + FlowActivity :: T.also 新的字符串：kotlin

        // 7、T.takeIf()函数：传入一个你希望的一个条件，如果对象符合你的条件则返回自身，反之则返回null
        val str7 = "kotlin"
        val result = str7.takeIf {
            // 判断一个字符串是否由某一个字符起始，若条件成立则返回自身，反之，则返回null
            it.startsWith("ko")
        }
        LogUtils.d(TAG, "result = $result")

        // 8、T.takeUnless()函数：作用和T.takeIf()函数的作用是一样的。只是和其的逻辑是相反的，即：传入一个你希望的一个条件，如果对象符合你的条件则返回null，反之则返回自身。

        // 9、repeat()函数：根据传入的重复次数去重复执行一个我们想要的动作(函数)
        repeat(5) {
            LogUtils.d(TAG, "我是重复的第${it + 1}次，我的索引为：$it")
        }
//        kotlin + FlowActivity :: 我是重复的第1次，我的索引为：0
//        kotlin + FlowActivity :: 我是重复的第2次，我的索引为：1
//        kotlin + FlowActivity :: 我是重复的第3次，我的索引为：2
//        kotlin + FlowActivity :: 我是重复的第4次，我的索引为：3
//        kotlin + FlowActivity :: 我是重复的第5次，我的索引为：4

        // 1、lateinit var只能用来修饰类属性，不能用来修饰局部变量，并且只能用来修饰对象，不能用来修饰基本类型(因为基本类型的属性在类加载后的准备阶段都会被初始化为默认值)。
        lateinit var name: String
        // 2、by lazy要求属性声明为val，即不可变变量，在java中相当于被final修饰；意味着该变量一旦初始化后就不允许再被修改值了
        // 3、by lazy真正做到了声明的同时也指定了延迟初始化时的行为，在属性被第一次被使用的时候能自动初始化。但这些功能是要为此付出一丢丢代价的。lateinit var只是让编译期忽略对属性未初始化的检查，还得初始化
        val age: Int by lazy { 18 }
    }

    /**
     * 1、将函数用作函数参数的情况的高阶函数（sumBy函数的源码）
     * 函数返回一个Int类型的值。并且接受了一个block()函数作为该函数的参数。其中block()接受一个Char类型的参数，并且返回一个Int类型的值
     */
    fun CharSequence.sumBy(block: (char: Char) -> Int): Int {  // == CharSequence.sumBy(selector: (Char) -> Int): Int {
        // 定义一个sum变量，并且循环这个字符串，循环一次调用一次selector()函数并加上sum。其中this关键字代表字符串本身
        var sum: Int = 0
        for (element in this) {
            sum += block(element)
        }
        return sum
    }

    /**
     * 2、将函数用作一个函数的返回值的高阶函数
     * （1）run(block: () -> R): R：接受一个无参且返回类型为R的函数作为参数，返回值为一个函数block()。this代表的是外部类的实例
     */
    fun <R> run(block: () -> R): R {
        return block()
    }

    /**
     *（2）T.run(block: T.() -> R): R
     * block()这个函数参数是一个扩展在T类型下的函数。this代表的是自身实例，所以block()函数可以使用当前对象本身。就不能像上面run()函数那样当做单独的一个代码块来使用。
     */
    fun <T, R> T.run(block: T.() -> R): R {
        return block()
    }

    // 3、with函数的返回值指定了receiver为接收者
    fun <T, R> with(receiver: T, block: T.() -> R): R {
        return receiver.block()
    }

    // 4、T.apply()函数：唯一的区别是T.apply执行完了block()函数后，返回了自身对象。而T.run是返回了执行的结果。
    fun <T> T.apply(block: T.() -> Unit): T {
        block()
        return this
    }

    // 5、T.also函数中的参数block函数传入了自身对象。该函数的作用是用block(this)函数调用自身对象，最后在返回自身对象
    inline fun <T> T.also(block: (T) -> Unit): T {
        block(this)
        return this
    }

    // 6、T.let和T.also一样，函数中的参数block函数传入了自身对象；但是该函数返回了转换后的自身对象
    fun <T, R> T.let(block: (T) -> R): R {
        return block(this)
    }

    // 7、传入一个你希望的一个条件，如果对象符合你的条件则返回自身，反之则返回null
    fun <T> T.takeIf(predicate: (T) -> Boolean): T? {
        return if (predicate(this)) this else null
    }

    // 9、根据传入的重复次数去重复执行一个我们想要的动作(函数)
    fun repeat(times: Int, action: (Int) -> Unit) {
        for (index in 0..times - 1) {
            action(index)
        }
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
     * 测试流
     */
    private fun testFlow() {
        // 表示多个值
//        multiValue()
        // sequence：序列
//        sequence()
        // 挂起函数 suspend
//        suspend()
        // 流
//        flowCollect()
    }

    /**
     * 表示多个值
     */
    private fun multiValue() {
        // Kotlin支持局部函数，即一个函数在另一个函数内部：
        // 单表达式函数：当函数返回单个表达式时，可以省略花括号并且在 = 符号之后指定代码体即可：fun double(x: Int): Int = x * 2
        fun simple(): List<Int> = listOf(1, 2, 3)

        // 等同于
        fun simple1(): List<Int> {
            return listOf(1, 2, 3)
        }

        simple().forEach { value ->
            LogUtils.d(TAG, "multiValue：${value}")
        }
//        07-29 17:38:42.286 2564-2564/? D/kotlin + FlowActivity :: multiValue：1
//        07-29 17:38:42.286 2564-2564/? D/kotlin + FlowActivity :: multiValue：2
//        07-29 17:38:42.286 2564-2564/? D/kotlin + FlowActivity :: multiValue：3
    }

    /**
     * sequence：序列
     * 如果使用一些消耗 CPU 资源的阻塞代码计算数字（每次计算需要 100 毫秒）那么我们可以使用 Sequence 来表示数字：
     */
    private fun sequence() {
        fun simple(): Sequence<Int> = sequence { // 序列构建器
            for (i in 1..3) {
                Thread.sleep(100) // 假装我们正在计算
                yield(i) // 向正在构建的[Iterator]生成一个值，并挂起，直到产生下一个值
            }
        }
        simple().forEach { value ->
            LogUtils.d(TAG, "sequence：${value}")
        }
//        kotlin + FlowActivity :: sequence：1
//        kotlin + FlowActivity :: sequence：2
//        kotlin + FlowActivity :: sequence：3
    }

    /**
     * 挂起函数 suspend
     * 计算过程阻塞运行该代码的主线程 当这些值由异步代码计算时，我们可以使用 suspend 修饰符标记函数 simple，这样它就可以在不阻塞的情况下执行其工作并将结果作为列表返回：
     */
    private fun suspend() {
        suspend fun simple(): List<Int> {
            return listOf(1, 2, 3)
        }

        LogUtils.d(TAG, "suspend0")
        GlobalScope.launch(Dispatchers.Main) {
            LogUtils.e(TAG, "I'm working in thread ${Thread.currentThread().name}")
            simple().forEach { value ->
                LogUtils.d(TAG, "suspend：${value}")
            }
        }
        LogUtils.d(TAG, "suspend2")
//        }
        LogUtils.d(TAG, "suspend4")
//        07-30 11:41:25.664 9553-9553/? D/kotlin + FlowActivity :: suspend0
//        07-30 11:41:25.684 9553-9553/? D/kotlin + FlowActivity :: suspend1
//        07-30 11:41:26.689 9553-9553/? D/kotlin + FlowActivity :: suspend：1
//        07-30 11:41:26.689 9553-9553/? D/kotlin + FlowActivity :: suspend：2
//        07-30 11:41:26.689 9553-9553/? D/kotlin + FlowActivity :: suspend：3
//        07-30 11:41:26.690 9553-9553/? D/kotlin + FlowActivity :: suspend2
//        07-30 11:41:26.691 9553-9553/? D/kotlin + FlowActivity :: suspend4
    }

    /**
     * 流
     * 使用 List 结果类型，意味着我们只能一次返回所有值。 为了表示异步计算的值流（stream），我们可以使用 Flow 类型（正如同步计算值会使用 Sequence 类型）
     */
    private fun flowCollect() {
        fun simple(): Flow<Int> = flow { // 流构建器
            for (i in 1..3) {
                delay(100) // 假装我们在这里做了一些有用的事情
                emit(i) // 发送下一个值
            }
        }

        runBlocking<Unit> {
            // 启动并发的协程以验证主线程并未阻塞
            launch {
                for (k in 1..3) {
                    LogUtils.d(TAG, "I'm not blocked $k")
                    delay(100)
                }
            }
            // 收集这个流
//            simple().collect { value ->
//                LogUtils.d(TAG, "value ${value}")
//            }
        }
    }


    /**
     * 测试异常
     */
    private fun testException() {
        // 取消与异常
//        cancellationException()
        // 异常聚合
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            aggregationException()
        }

    }

    /**
     * 取消与异常
     */
    private fun cancellationException() {
        // 当一个协程使用 Job.cancel 取消的时候，它会被终止，但是它不会取消它的父协程
        runBlocking {
            val job = GlobalScope.launch {
                val child = launch {
                    try {
                        delay(Long.MAX_VALUE)
                    } finally {
                        LogUtils.d(TAG, "Child is cancelled")
                    }
                }
                yield()
                LogUtils.d(TAG, "Cancelling child")
                child.cancel()
                child.join()
                yield()
                LogUtils.d(TAG, "Parent is not cancelled")
            }
            job.join()
        }
//        kotlin + FlowActivity :: Cancelling child
//        kotlin + FlowActivity :: Child is cancelled
//        kotlin + FlowActivity :: Parent is not cancelled

        // 当父协程的所有子协程都结束后，原始的异常才会被父协程处理
        runBlocking {
            val handler = CoroutineExceptionHandler { _, exception ->
                LogUtils.d(TAG, "CoroutineExceptionHandler got $exception")
            }
            val job = GlobalScope.launch(handler) {
                launch { // 第一个子协程
                    try {
                        delay(Long.MAX_VALUE)
                    } finally {
                        withContext(NonCancellable) {
                            LogUtils.d(TAG, "Children are cancelled, but exception is not handled until all children terminate")
                            delay(100)
                            LogUtils.d(TAG, "The first child finished its non cancellable block")
                        }
                    }
                }
                launch { // 第二个子协程
                    delay(10)
                    LogUtils.d(TAG, "Second child throws an exception")
                    throw ArithmeticException()
                }
            }
            job.join()
        }
//        kotlin + FlowActivity :: Second child throws an exception
//        kotlin + FlowActivity :: Children are cancelled, but exception is not handled until all children terminate
//        kotlin + FlowActivity :: The first child finished its non cancellable block
//        kotlin + FlowActivity :: CoroutineExceptionHandler got java.lang.ArithmeticException
    }

    /**
     * 异常聚合
     */
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun aggregationException() {
        runBlocking {
            val handler = CoroutineExceptionHandler { _, exception ->
                LogUtils.d(TAG, "CoroutineExceptionHandler got $exception with suppressed ${exception.suppressed.contentToString()}")
            }
            val job = GlobalScope.launch(handler) {
                launch {
                    try {
                        delay(Long.MAX_VALUE) // 当另一个同级的协程因 IOException  失败时，它将被取消
                    } finally {
                        throw ArithmeticException() // 第二个异常
                    }
                }
                launch {
                    delay(100)
                    throw IOException() // 首个异常
                }
                delay(Long.MAX_VALUE)
            }
            job.join()
        }
//        CoroutineExceptionHandler got java.io.IOException with suppressed [java.lang.ArithmeticException]
    }


    /**
     * 通道：提供了一种便捷的方法使单个值在多个协程之间进行相互传输的方法
     */
    private fun testChannel() {
        // 通道基础
        channel()
        // 关闭通道
        testCloseChannel()
        // 构建通道生产者：将生产者抽象成一个函数，并且使通道作为它的参数
        channelProduce()
        // 管道是一种一个协程在流中开始生产可能无穷多个元素的模式
        channelPipe()
    }

    /**
     * 通道基础
     */
    private fun channel() {
        // 一个 Channel 是一个和 BlockingQueue 非常相似的概念。其中一个不同是它代替了阻塞的 put 操作并提供了挂起的 send，还替代了阻塞的 take 操作并提供了挂起的 receive
        LogUtils.d(TAG, "start!")
        val channel = Channel<Int>()
        GlobalScope.launch(Dispatchers.IO) {
            // 这里可能是消耗大量 CPU 运算的异步逻辑，我们将仅仅做 5 次整数的平方并发送
            for (x in 1..5) channel.send(x * x)
        }

        // 这里我们打印了 5 次被接收的整数：
        repeat(5) {
            GlobalScope.launch(Dispatchers.IO) {
                LogUtils.d(TAG, "${channel.receive()}")
            }
        }
//        07-30 17:02:19.473 4537-4537/? D/kotlin + FlowActivity :: start!
//        07-30 17:02:19.504 4537-5461/? D/kotlin + FlowActivity :: 1
//        07-30 17:02:19.504 4537-5461/? D/kotlin + FlowActivity :: 4
//        07-30 17:02:19.504 4537-5464/? D/kotlin + FlowActivity :: 9
//        07-30 17:02:19.504 4537-5461/? D/kotlin + FlowActivity :: 16
//        07-30 17:02:19.504 4537-5464/? D/kotlin + FlowActivity :: 25
    }

    /**
     * 关闭通道
     * 和队列不同，一个通道可以通过被关闭来表明没有更多的元素将会进入通道。 在接收者中可以定期的使用 for 循环来从通道中接收元素。
     * 一个 close 操作就像向通道发送了一个特殊的关闭指令
     */
    private fun testCloseChannel() {
        LogUtils.d(TAG, "start!")
        val channel = Channel<Int>()
        GlobalScope.launch(Dispatchers.IO) {
            try {
                for (x in 1..5) channel.send(x * x)
                channel.close() // 我们结束发送
                channel.send(6 * 6)
            } catch (e: Throwable) {
                LogUtils.d(TAG, "ClosedSendChannelException ${e}")
            }
        }
        GlobalScope.launch(Dispatchers.IO) {
            // 这里我们使用 `for` 循环来打印所有被接收到的元素（直到通道被关闭）
            for (y in channel) LogUtils.d(TAG, "y：${y}")
        }
//        07-30 17:16:20.108 8013-8078/? D/kotlin + FlowActivity :: y：1
//        07-30 17:16:20.108 8013-8078/? D/kotlin + FlowActivity :: y：4
//        07-30 17:16:20.108 8013-8078/? D/kotlin + FlowActivity :: y：9
//        07-30 17:16:20.108 8013-8078/? D/kotlin + FlowActivity :: y：16
//        07-30 17:16:20.108 8013-8069/? D/kotlin + FlowActivity :: y：25
//        07-30 17:16:20.109 8013-8078/? D/kotlin + FlowActivity :: ClosedSendChannelException kotlinx.coroutines.channels.ClosedSendChannelException: Channel was closed
    }

    /**
     * 构建通道生产者：将生产者抽象成一个函数，并且使通道作为它的参数
     */
    private fun channelProduce() {
        fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce {
            for (x in 1..5) {
                send(x * x)
            }
        }
        GlobalScope.launch(Dispatchers.IO) {
            val squares = produceSquares()
            squares.consumeEach { LogUtils.d(TAG, "it：${it}") }
        }
//        07-30 17:21:21.793 8346-8425/? D/kotlin + FlowActivity :: it：1
//        07-30 17:21:21.793 8346-8425/? D/kotlin + FlowActivity :: it：4
//        07-30 17:21:21.794 8346-8425/? D/kotlin + FlowActivity :: it：9
//        07-30 17:21:21.794 8346-8425/? D/kotlin + FlowActivity :: it：16
//        07-30 17:21:21.798 8346-8434/? D/kotlin + FlowActivity :: it：25
    }

    /**
     * 管道是一种一个协程在流中开始生产可能无穷多个元素的模式
     */
    private fun channelPipe() {
        GlobalScope.launch(Dispatchers.IO) {
            var numbers = produceNumbers() // 从 1 开始生成整数
            var squares = square(numbers) // 整数求平方
            repeat(10) {
                GlobalScope.launch(Dispatchers.IO) {
                    // 输出前五个
                    val prime = squares.receive()
                    LogUtils.d(TAG, "squares.receive()：${prime}")
                    // 过滤素数
                    squares = filter(squares, prime)
                }
            }
            coroutineContext.cancelChildren() // 取消子协程
        }
//        07-30 17:27:23.105 8963-9019/? D/kotlin + FlowActivity :: squares.receive()：1
//        07-30 17:27:23.105 8963-9019/? D/kotlin + FlowActivity :: squares.receive()：4
//        07-30 17:27:23.105 8963-9019/? D/kotlin + FlowActivity :: squares.receive()：16
//        07-30 17:27:23.105 8963-9019/? D/kotlin + FlowActivity :: squares.receive()：9
//        07-30 17:27:23.105 8963-9019/? D/kotlin + FlowActivity :: squares.receive()：25
    }

    fun CoroutineScope.produceNumbers() = produce<Int> {
        var x = 1
        while (true) send(x++) // 从 1 开始的无限的整数流
    }

    fun CoroutineScope.square(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
        for (x in numbers) send(x * x)
    }

    fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) = produce<Int> {
        for (x in numbers) if (x % prime != 0) send(x)
    }
}