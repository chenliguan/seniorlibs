package com.read.kotlinlib.basic

import com.seniorlibs.baselib.bean.BaselibFunc
import com.seniorlibs.baselib.utils.LogUtils

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2021/1/20.
 * Mender:
 * Modify:
 * Description: 自定义接口方法
 */
object Func : BaseFunc() {

//    ComponentActivity :: BaseFunc init
//    ComponentActivity :: Func init
//    ComponentActivity :: testComponent0 : 00000 aaaaaaaaaaaaaaaaaaaaaaa ddddddddddddddddddddddddddddddddddddd
//    ComponentActivity :: testComponent1 : Function1<java.lang.String, java.lang.String>
//    ComponentActivity :: testComponent2 : 11111 aaaaaaaaaaaaaaaaaaaaaaa ddddddddddddddddddddddddddddddddddddd

    init {
        LogUtils.e(ComponentActivity.TAG, "Func init")

        mInternalFieLd = ""
        mPublicFieLd = " "

        BaselibFunc.mPublicFieLd
        //BaselibFunc.mInternalFieLd
        BaselibFunc.getBaseFunc()
    }

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