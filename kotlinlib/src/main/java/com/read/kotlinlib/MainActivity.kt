package com.read.kotlinlib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.View
import com.read.kotlinlib.annotation.AnnotationParser
import com.read.kotlinlib.basic.*
import com.read.kotlinlib.bit.BitTest
import com.read.kotlinlib.generic.GenericTest
import com.read.kotlinlib.generic.GenericTestKt
import com.read.kotlinlib.inner.*
import com.read.kotlinlib.polymorphisn.Polymorphisn
import com.read.kotlinlib.string.StringTest
import com.seniorlibs.baselib.utils.LogUtils

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/4/1
 * Mender:
 * Modify:
 * Description: 测试kotlin
 */
class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "kotlin + MainActivity : "

        fun companionFun(): String {
            return "companionFun"
        }

        @JvmStatic
        fun jvm() {

        }

        init {

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

    object DemoManager {
        fun a() {
            LogUtils.d(TAG, "此时 object 表示 声明静态内部类")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BasicActivity.companionFun()
    }

    /**
     * 字节测试
     *
     * @param view
     */
    fun testBit(view: View?) {
        val bitTest = BitTest()
        bitTest.storeChar()
    }

    /**
     * 字符串测试
     *
     * @param view
     */
    fun testString(view: View?) {
        val stringTest = StringTest()
        stringTest.store()
    }

    /**
     * 泛型测试
     *
     * @param view
     */
    fun testGeneric(view: View?) {
        // Kotlin泛型测试类
        val genericKt = GenericTestKt()
        genericKt.main()

        // Java泛型测试类
        val generic = GenericTest<Int>()
        generic.main()
    }

    /**
     * 多态的表现：分派
     *
     * @param view
     */
    fun polymorphisn(view: View?) {
        val p = Polymorphisn()
        p.main()
    }

    /**
     * 注解
     *
     * @param view
     */
    fun testAnnotation(view: View?) {
        AnnotationParser.main()
    }

    /**
     * 内部类测试
     *
     * @param view
     */
    fun testInner(view: View?) {
        // final测试
        val final = Final()
        final.main()

        // 测试Java匿名内部类有哪些限制
        val outerClass = OuterClass()
        outerClass.main()

        // 测试Java内部类
        val outer = Outer()
        outer.main()
        // 测试Kotlin内部类
        val outerKt = Outerkt()
        outerKt.main()

        // 测试匿名内部类的构造方法（关注：匿名内部类对外部类的引用）
        val client = ConstructorClient()
        client.runInnerClass()
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
     * 协程
     *
     * @param view
     */
    fun testCoroutine(view: View?) {
        CoroutineActivity.actionStart(this)
    }

    /**
     * 协程
     *
     * @param view
     */
    fun testFlow(view: View?) {
        FlowFunActivity.actionStart(this)
    }

    /**
     * 进阶总结
     *
     * @param view
     */
    fun testAdvance(view: View?) {
        AdvanceActivity.actionStart(this)
    }

    /**
     * kotlin定义组件化接口
     *
     * @param view
     */
    fun testComponentInterface(view: View?) {
        ComponentActivity.actionStart(this)
    }
}