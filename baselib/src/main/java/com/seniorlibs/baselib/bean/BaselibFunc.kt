package com.seniorlibs.baselib.bean

/**
 * Author: 陈李冠
 * Time: 2021/6/30
 * Description: 普通父类
 */
object BaselibFunc {

    public var mPublicFieLd = ""
    internal var mInternalFieLd = ""
    private var mPrivateFieLd = ""

    fun getBaseFunc() : String {
        return "BaseFunc"
    }
}