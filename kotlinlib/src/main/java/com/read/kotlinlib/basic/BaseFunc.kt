package com.read.kotlinlib.basic

import com.seniorlibs.baselib.utils.LogUtils

/**
 * Author: 陈李冠
 * Time: 2021/6/30
 * Description: 普通父类
 */
open class BaseFunc {

    public var mPublicFieLd = ""
    internal var mInternalFieLd = ""
    private var mPrivateFieLd = ""

    init {
        LogUtils.e(ComponentActivity.TAG, "BaseFunc init")
    }

    fun getBaseFunc() : String {
        return "BaseFunc"
    }
}