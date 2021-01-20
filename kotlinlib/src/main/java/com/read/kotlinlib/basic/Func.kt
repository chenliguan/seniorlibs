package com.read.kotlinlib.basic

import com.seniorlibs.baselib.utils.LogUtils

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2021/1/20.
 * Mender:
 * Modify:
 * Description: 自定义接口方法
 */
object Func {

    var funcGetName: ((String) -> String) = {
        "123"
    }

    fun setGetNameFun(funcBlock: (str : String) -> String) {
        funcGetName = funcBlock
        LogUtils.e(ComponentActivity.TAG, "testComponent0 : ${funcGetName.invoke("00000")}")
//      testComponent0 : 00000 aaaaaaaaaaaaaaaaaaaaaaa ddddddddddddddddddddddddddddddddddddd
    }


    fun getName(str: String): String {
        return funcGetName(str)
    }

    fun <T> T.apply(block: T.() -> Unit): T {
        block()
        return this
    }

    fun <T> T.apply2(block: () -> Unit): T {
        block()
        return this
    }
}