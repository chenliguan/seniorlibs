package com.read.kotlinlib.generic

import com.seniorlibs.baselib.utils.LogUtils
import java.util.*

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/4/1.
 * Mender:
 * Modify:
 * Description: Kotlin泛型测试类
 */
class GenericTestKt {

    companion object {
        const val TAG = "GenericTestKt"
    }

    fun main() {
        // 泛型擦除
        genericErasure()
        // 为什么要用通配符和边界？
        whyWildcardsBoundaries()
        // 上界不能往里存，只能往外取
        testExtends()
        // 下界不影响往里存，但往外取只能放在Object对象里
        testSuper()
    }

    /**
     * 泛型擦除
     */
    fun genericErasure() {
        val list: List<*> = ArrayList<Any?>()
        val listString: List<*> = ArrayList<String>()
        val listInteger: List<*> = ArrayList<Int>()
        try {
            list.javaClass.getMethod("add", Any::class.java).invoke(list, 1)
            listString.javaClass.getMethod("add", Any::class.java).invoke(listString, 1)
            // 给不服气的读者们的测试之处，你可以改成字符串来尝试。
            listInteger.javaClass.getMethod("add", Any::class.java).invoke(listInteger, 1)
            listInteger.javaClass.getMethod("add", Any::class.java).invoke(listInteger, "12")
        } catch (e: Exception) {
            e.printStackTrace()

        }
        LogUtils.d(TAG, "list size:" + list.size)
        LogUtils.d(TAG, "listString size:" + listString.size)
        LogUtils.d(TAG, "listInteger size:" + listInteger.size)
        /**
         * 04-01 21:11:58.404 31723-31723/com.read.kotlinlib D/kotlinlib + GenericTest: list size:1
         * 04-01 21:11:58.405 31723-31723/com.read.kotlinlib D/kotlinlib + GenericTest: listString size:1
         * 04-01 21:11:58.405 31723-31723/com.read.kotlinlib D/kotlinlib + GenericTest: listInteger size:2
         */
    }

    /**
     * 为什么要用通配符和边界？
     */
    fun whyWildcardsBoundaries() {
        // 定义一个“水果盘子”，逻辑上水果盘子当然可以装苹果
        // 实际上Java编译器不允许这个操作。会报错，“装苹果的盘子”无法转换成“装水果的盘子”
//        val p: Plate<Fruit> = Plate<Apple>(Apple())
    }

    /**
     * 上界不能往里存，只能往外取
     */
    fun testExtends() {
        // 填写Fruit的位置，级别一定要Fruit或Fruit的子类；()可存放<>的子类
        val p1: Plate<out Fruit> = Plate<Fruit>(Apple())
        val p2: Plate<out Fruit> = Plate<Apple>(RedApple())
        // 检查不通过
//        val p3: Plate<out Fruit> = Plate<Fruit>(Food())
//        val p4: Plate<out Fruit> = Plate<Food>(Fruit())
//        val p5: Plate<out Fruit> = Plate<Apple>(Beef())

        // 不能存入任何元素
//        p1.set(Banana())
//        p1.set(Fruit())
//        p1.set(Apple())
//        p1.set(Any())

        // 数据获取正常
        // 但是他只能精确到Fruit或Fruit的父类
        val result1 : Fruit = p1.get()
        val result2 : Any = p1.get()
        // 数据获取错误
//        val result3: Apple = p1.get()
    }

    /**
     * 下界不影响往里存，但往外取只能放在Object对象里
     */
    fun testSuper() {
        // 填写Fruit的位置，级别一定是Fruit或Fruit的父类；()可存放<>的子类
        val p1: Plate<in Fruit> = Plate<Fruit>(Fruit())
        val p2: Plate<in Fruit> = Plate<Food>(Fruit())
        val p3: Plate<in Fruit> = Plate<Fruit>(Apple())
        // 检查不通过
//        val p4: Plate<in Fruit> = Plate<Fruit>(Beef())
//        val p5: Plate<in Fruit> = Plate<Apple>(Apple())

        // 存入Fruit的子类都可以
        p1.set(Fruit())
        p1.set(Apple())
        // 存入Fruit的父类错误
//        p1.set(Food())

        // 读取出来一定要经过强制转化，因为返回的只是一个Object
//        val result1: Apple = p1.get()
//        val result2: Fruit = p1.get()
        // 返回一个Object数据，已经属于快要丢失掉全部数据了，所以不适合读取
        val result3 = p1.get()!!
    }

    /**
     * Lev 1
     */
    internal open inner class Food

    /**
     * Lev 2
     */
    internal open inner class Fruit : Food()
    internal open inner class Meat : Food()

    /**
     * Lev 3
     */
    internal open inner class Apple : Fruit()
    internal inner class Banana : Fruit()
    internal inner class Pork : Meat()
    internal inner class Beef : Meat()

    /**
     * Lev 4
     */
    internal inner class RedApple : Apple()
    internal inner class GreenApple : Apple()

    // 承载者
    internal class Plate<T>(private var item: T) {
        fun set(t: T) {
            item = t
        }

        fun get(): T {
            return item
        }
    }
}