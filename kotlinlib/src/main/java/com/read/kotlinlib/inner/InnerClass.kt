package com.read.kotlinlib.inner

import com.seniorlibs.baselib.utils.LogUtils

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/11/4.
 * Mender:
 * Modify:
 * Description: 内部类测试
 */
class InnerClass {

    private val TAG = "InnerClass"

    private var innerObject : ABSClass? = null

    abstract class ABSClass {
        abstract fun print()
    }

    /**
     * 一旦参数在匿名类内部使用，Java则必须是final，kotlin是val
     * 备注：Java8更加智能，如果局部变量被方法内的匿名内部类访问，该局部变量相当于自动使用了final修饰
     *
     * @param s
     */
    private fun test(s: String?) {
        val c: ABSClass = object : ABSClass() {
            override fun print() {
//                s = "2222"  // 报错，不能修改，因为参数是val
                LogUtils.d(TAG, "print：$s")
            }
        }
        c.print()
    }
//    InnerClass: print：Hello World0!
//    InnerClass: print：Hello World11!


    /**
     * 对于匿名内部类对象要访问的所有final(val)类型局部变量，都拷贝成为该对象中的一个数据成员
     * 以下情况，只创建一次innerObject对象，它的s也被拷贝成为该对象中的一个数据成员。因此，对象不变，此成员也不会变
     *
     * @param s
     */
    private fun testInnerObject(s: String?) {
        if (innerObject == null) {
            innerObject = object : ABSClass() {
                override fun print() {
                    LogUtils.d(TAG, "print：$s")
                }
            }
        }
        innerObject?.print()
    }
//    InnerClass: print：Hello World2!
//    InnerClass: print：Hello World2!
//    InnerClass: print：Hello World2!


    fun main() {
        test("Hello World0!")
        test("Hello World11!")
        testInnerObject("Hello World2!")
        testInnerObject("Hello World3!")
        testInnerObject("Hello World4!")
    }
}