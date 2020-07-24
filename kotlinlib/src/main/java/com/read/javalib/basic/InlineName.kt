package com.read.javalib.basic

/**
 * Author: 陈李冠
 * Version: 1.28
 * Date: 2020/7/23.
 * Mender:
 * Modify:
 * Description: 内联类
 */
inline class InlineName (val str : String) {

    companion object {
        const val TAG = "kotlin + InlineName : "
    }

    val length: Int
        get() = str.length

    fun greet() {
        println("${TAG} InlineName, $str")
        return
    }
}