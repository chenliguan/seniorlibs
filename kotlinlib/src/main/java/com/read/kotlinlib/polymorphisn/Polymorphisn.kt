package com.read.kotlinlib.polymorphisn

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/11/20.
 * Mender:
 * Modify:
 * Description: 多态的表现：分派
 */
class Polymorphisn {

    companion object {
        const val TAG = "kotlinlib + Polymorphisn"
    }

    fun main() {
        // 方法分派
        val superClass: SuperClass = SubClass()
        printHello(superClass)
    }

    private fun printHello(superClass: SuperClass) {
        println("SuperClass + Hello " + superClass.name)
    }

    private fun printHello(subClass: SubClass) {
        println("SubClass + Hello " + subClass.name)
    }

    open inner class SuperClass {
        open val name: String = "Super"
    }

    inner class SubClass : SuperClass() {
        override val name: String = "Sub"
    }

    fun main(a: String?, b: Int?) : String {
        return ""
    }
}