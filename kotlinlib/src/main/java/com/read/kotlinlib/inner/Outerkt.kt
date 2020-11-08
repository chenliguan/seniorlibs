package com.read.kotlinlib.inner

import com.seniorlibs.baselib.utils.LogUtils

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/11/4.
 * Mender:
 * Modify:
 * Description: 测试Kotlin内部类?
 *
 * 总结：
 * 1、private
 * （1）Java 中的 private 表示类中可见，作为内部类时对外部类「可见」。外部类可以访问内部类的 private 变量。
 * （2）Kotlin 中的 private 表示类中可见，作为内部类时对外部类「不可见」。外部类不可以访问内部类的 private 变量：
 */
class Outerkt {

    private val TAG = "Outerkt"

    var count = 0

    fun add() {
        count++
    }

    fun main() {
        // 1、测试内部嵌套类
        testInnerNested()
    }

    /**
     * 1、测试内部嵌套类
     */
    private fun testInnerNested() {
        // 1、
        // 内部类
        val innerClass = Outerkt().InnerClass()
        val res = innerClass.getSomething()
        LogUtils.e(TAG, "testInnerNested + 内部类1：$res")
//        testInnerNested + 内部类1：2

        val innerClass1 = InnerClass()
        val res1 = innerClass1.getSomething()
        LogUtils.e(TAG, "testInnerNested + 内部类2：$res1")
//        testInnerNested + 内部类2：2

        // 嵌套类
        val nestedClass = NestedClass()
        val value = nestedClass.getValue()
        LogUtils.e(TAG, "testInnerNested + 嵌套类1：$value")
//        testInnerNested + 嵌套类1：1

        // 2、
        // 在 Kotlin 中，外部类和嵌套类都不可以访问内部类的 private 变量：
//        innerClass.number   // 编译器报错
//        nestedClass.number  // 编译器报错
    }

    /**
     * 内部类
     *
     * @constructor Create empty Inner class
     */
    inner class InnerClass {
        private val number = 1

        fun getSomething(): Int {
            // 默认持有外部类的引用，直接访问外部类的方法属性
            add()
            // 也可以使用this@OutClass去访问外部类的属性和方法
            this@Outerkt.add()
            return this@Outerkt.count
        }
    }

    /**
     * 嵌套类
     *
     * @constructor Create empty Nested class
     */
    class NestedClass {
        private val number = 1

        fun getValue(): Int {
//            add()  // 编译器报错
            // 嵌套类不持有外部类的引用，必须通过外部类的对象访问
            val outClass = Outerkt()
            outClass.add()
            return outClass.count
        }
    }
}