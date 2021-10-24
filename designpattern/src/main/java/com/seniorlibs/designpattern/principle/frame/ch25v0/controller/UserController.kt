package com.seniorlibs.designpattern.principle.frame.ch25v0.controller

import android.os.Build
import androidx.annotation.RequiresApi
import com.seniorlibs.designpattern.principle.frame.ch25v0.metrics.Metrics
import com.seniorlibs.designpattern.principle.frame.ch25v0.model.UserVo
import java.util.concurrent.TimeUnit

/**
 * 统计下面两个接口(注册和登录）的响应时间和访问次数
 */
class UserController {
    private val mMetrics = Metrics()
    @RequiresApi(api = Build.VERSION_CODES.N)
    fun register(user: UserVo?) {
        val startTimestamp = System.currentTimeMillis()
        mMetrics.recordTimestamp("regsiter", startTimestamp.toDouble())
        //...
        val respTime = System.currentTimeMillis() - startTimestamp
        mMetrics.recordResponseTime("register", respTime.toDouble())
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    fun login(telephone: String?, password: String?): UserVo {
        val startTimestamp = System.currentTimeMillis()
        mMetrics.recordTimestamp("login", startTimestamp.toDouble())
        //...
        val respTime = System.currentTimeMillis() - startTimestamp
        mMetrics.recordResponseTime("login", respTime.toDouble())
        return UserVo()
    }

    init {
        mMetrics.startRepeatedReport(60, TimeUnit.SECONDS)
    }
}