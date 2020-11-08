package com.read.kotlinlib.inner

import com.seniorlibs.baselib.utils.LogUtils

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/11/8.
 * Mender:
 * Modify:
 * Description: 测试Kotlin匿名内部类有哪些限制?
 */
class OuterClassKt {

    private val TAG = "OuterClass"

    fun main() {
        // 1、匿名内部类的名字
        innerClassName()
        // 2、匿名内部类的继承结构
        innerClassSucceedStructure()
    }

    /**
     * 1、匿名内部类的名字
     */
    private fun innerClassName() {
        try {
            val fooClass = Class.forName("com.read.kotlinlib.inner.OuterClass$1")
            LogUtils.e(TAG, "fooClass：$fooClass")
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        // fooClass：class com.read.kotlinlib.inner.OuterClass$1（正常）

        try {
            val fooClass = Class.forName("com.read.kotlinlib.inner.OuterClass$2")
            LogUtils.e(TAG, "fooClass：$fooClass")
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        // java.lang.ClassNotFoundException: com.read.kotlinlib.inner.OuterClass$1（报错）
    }

    /**
     * 2、匿名内部类的继承结构
     */
    private fun innerClassSucceedStructure() {
        // 另外，Kotlin是可以实现：既 继承某个父类 又 实现某个接口 的“匿名内部类” 的 这种类型的：
        // （object类似于class与定义一个引用， object与后面冒号之间不接名字表示匿名，冒号后面要继承什么，实现什么，直接写出来就是了）
        val runnableFoo = object: Foo(), Runnable {
            override fun run() {}
        }

        // 可是如果实在是想实现一个，既 继承某个父类 又 实现某个接口 的“匿名内部类”这样的类型，但要不想占用太多资源，要求同匿名内部类一样用完即销毁，怎么办？
        // 那别用匿名内部类，在方法体内部实现即可，便可以在方法调用完毕后将其回收，也可以达到需求：
        val runnableFoo1 = RunnableFoo()
    }

    /**
     * 方法内部定义一个有名的内部类
     */
    internal class RunnableFoo : Foo(), Runnable {
        override fun run() {}
    }


    var foo: Foo = object : Foo() {
        override fun bar(): Int {
            return 0
        }
    }

    open class Foo {
        open fun bar(): Int {
            return 1
        }
    }

    interface Runnable {
        fun run()
    }
}