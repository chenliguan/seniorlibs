package com.seniorlibs.designpattern.proxy.v1

import com.seniorlibs.designpattern.principle.frame.ch25v0.model.UserVo
import com.seniorlibs.designpattern.principle.frame.ch25v3.metrics.MetricsCollector
import com.seniorlibs.designpattern.principle.frame.ch25v3.model.RequestInfo

/**
 * 参照基于接口而非实现编程的设计思想，将原始类对象替换为代理类对象的时候，
 * 为了让代码改动尽量少，代理类和原始类需要实现相同的接口。
 */
class UserControllerProxy(private val userController: UserController) : IUserController {
    private val metricsCollector: MetricsCollector

    init {
        metricsCollector = MetricsCollector()
    }

    override fun login(telephone: String?, password: String?): UserVo {
        val startTimestamp = System.currentTimeMillis()

        // 委托
        val userVo = userController.login(telephone, password)

        val endTimeStamp = System.currentTimeMillis()
        val responseTime = (endTimeStamp - startTimestamp).toDouble()
        val requestInfo = RequestInfo("login", responseTime, startTimestamp)
        metricsCollector.recordRequest(requestInfo)
        return userVo
    }

    override fun register(telephone: String?, password: String?): UserVo {
        val startTimestamp = System.currentTimeMillis()

        // 委托
        val userVo = userController.register(telephone, password)

        val endTimeStamp = System.currentTimeMillis()
        val responseTime = (endTimeStamp - startTimestamp).toDouble()
        val requestInfo = RequestInfo("register", responseTime, startTimestamp)
        metricsCollector.recordRequest(requestInfo)
        return userVo
    }
}

// UserControllerProxy 使用举例
// 因为原始类和代理类实现相同的接口，是基于接口而非实现编程
// 将 UserController 类对象替换为 UserControllerProxy 类对象，不需要改动太多代码
var userController: IUserController = UserControllerProxy(UserController())
// userController.login("", "")
// userController.register("", "")