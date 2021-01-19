package com.read.kotlinlib.basic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.read.kotlinlib.R
import com.read.kotlinlib.model.People
import com.seniorlibs.baselib.utils.LogUtils

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/4/1
 * Mender:
 * Modify:
 * Description: 进阶总结
 */
class AdvanceActivity : AppCompatActivity() {

    companion object {
        const val TAG = "kotlin + AdvanceActivity : "

        fun companionFun(): String {
            return "companionFun"
        }

        @JvmStatic
        fun jvm() {

        }

        // 单例：静态内部类式
        fun getInstance() = SingletonHolder.INSTANCE

        fun actionStart(context: Context?) {
            if (context == null) {
                return
            }
            val intent = Intent(context, AdvanceActivity::class.java)
            context.startActivity(intent)
        }
    }

    object SingletonHolder {
        val INSTANCE = {

        }
    }

    lateinit var mRoot: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advance)

        initView()

        // 测试高阶函数
        testFun()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun initView() {
        mRoot = findViewById<View>(R.id.ll_coroutine)
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
     * 测试高阶函数
     */
    private fun testFun() {
        // 对比高阶函数间的区别
        higherFunCompare()
    }

    /**
     * 对比高阶函数间的区别
     * https://mp.weixin.qq.com/s/QDv6Fjx-0XxmcPSM68ODKQ
     */
    private fun higherFunCompare() {
        val people : People = People("chen", 1, listOf("x", "y"))

        /**
         * let
         * 作用：1.避免写判空的处理；2.使用it替代object对象在其作用域内访问属性&方法；3.返回值是最后一行 == return ...
         */
        val let = people?.let {
            it.age
            it.name
            it.isYong()
            it.isMen()
            999
        }
        LogUtils.d(TAG, "people?.let：$let")
        // people?.let：999

        // java
        if (people != null) {
            people.age
            people.name
            people.isYong()
            people.isMen()
            999
        }

        /**
         * also：类似let函数，但区别在于返回值：
         * 3.返回值是传入的对象的本身
         */
        val also = people?.also {
            it.age
            it.name
            it.isYong()
            it.isMen()
            999
        }
        LogUtils.d(TAG, "people?.also：$also")
        // people?.also：com.read.kotlinlib.model.People@1f24758d

        /**
         * with：
         * 1.调用同一个对象的多个方法/属性时，省去重复的对象名，直接调用方法名/属性即可；2.返回值是最后一行
         */
        val with = with(people) {
            LogUtils.d(TAG,"my name is $name, I am $age years old, ${isYong()}, ${isMen()}")
            999
        }
        LogUtils.d(TAG, "with(people)：$with")
        // my name is chen, I am 1 years old, false, true
        // with(people)：999

        // java
        LogUtils.d(TAG,"my name is $people.name, I am $people.age years old, ${people.isYong()}, ${people.isMen()}")

        /**
         * run：结合了let、with两个函数的作用
         * 作用：1.避免写判空的处理；2.使用it替代object对象在其作用域内访问属性&方法；3.返回值是最后一行；
         *      4.调用同一个对象的多个方法/属性时，省去重复的对象名，直接调用方法名/属性即可
         */
        val run = people?.run {
            LogUtils.d(TAG,"my name is $name, I am $age years old, ${isYong()}, ${isMen()}")
            999
        }
        LogUtils.d(TAG, "people?.run：$run")
        // my name is chen, I am 1 years old, false, true
        // people?.run：999

        /**
         * apply：与run函数类似，但区别在于返回值：
         * 3.返回值是传入的对象的本身；
         */
        val apply = people?.apply {
            LogUtils.d(TAG,"my name is $name, I am $age years old, ${isYong()}, ${isMen()}")
            999
        }
        LogUtils.d(TAG, "people?.apply：$apply")
        // my name is chen, I am 1 years old, false, true
        // people?.apply：com.read.kotlinlib.model.People@1f24758d
    }
}