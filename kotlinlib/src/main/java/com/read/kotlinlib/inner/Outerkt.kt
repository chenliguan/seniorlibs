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

    private var count = 0

    fun add() {
        count++
    }

    /**
     * 内部类
     *
     * @constructor Create empty Inner class
     */
    inner class InnerClass {
        private val number = 1

        fun getSomething(): Int {
            // 默认持有外部类的引用，直接访问外部类的方法属性。等价于:this@Outerkt.add()
            add()
            // 等价于:this.number + this@Outerkt.count
            return number + count
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
            // add()  编译器直接报错
            // 嵌套类不持有外部类的引用，必须通过外部类的对象访问
            val outClass = Outerkt()
            outClass.add()
            // 等价于:this.number + outClass.count
            return this.number + outClass.count
        }
    }


    fun main() {
        // 1、测试内部嵌套类
        testInnerNested()
        // 2、测试外部类访问内部类的private
        testInnerPrivate()
    }

    /**
     * 1、测试内部嵌套类
     */
    private fun testInnerNested() {
        // 内部类
        val innerClass = Outerkt().InnerClass()  // InnerClass()
        val res = innerClass.getSomething()
        LogUtils.e(TAG, "testInnerNested + 内部类1：$res")
        // testInnerNested + 内部类1：2

        // 嵌套类
        val nestedClass = NestedClass()
        val value = nestedClass.getValue()
        LogUtils.e(TAG, "testInnerNested + 嵌套类1：$value")
        // testInnerNested + 嵌套类1：1
    }

    /**
     * 2、测试外部类 不可以访问 内部类或嵌套类 的 private 变量
     */
    private fun testInnerPrivate() {
        // 在 Kotlin 中，外部类 不可以访问 内部类或嵌套类 的 private 变量：
        // Outerkt().InnerClass().number   编译器直接报错
        // NestedClass().number            编译器直接报错
    }
}