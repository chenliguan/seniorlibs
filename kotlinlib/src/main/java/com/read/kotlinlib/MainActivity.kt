package com.read.kotlinlib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.View
import com.read.kotlinlib.basic.BasicActivity
import com.read.kotlinlib.basic.CoroutineActivity
import com.read.kotlinlib.basic.FlowFunActivity
import com.read.kotlinlib.generic.GenericTest
import com.seniorlibs.baselib.utils.LogUtils

/**
 * Author: 陈李冠
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
     * 泛型测试
     *
     * @param view
     */
    fun testGeneric(view: View?) {
        val genericTest = GenericTest()
        genericTest.genericErasure()
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
}