package com.seniorlibs.designpattern.proxy.v1

import com.seniorlibs.designpattern.ch25v0.model.UserVo
import com.seniorlibs.designpattern.ch25v3.metrics.MetricsCollector
import com.seniorlibs.designpattern.ch25v3.model.RequestInfo

/**
 * 但是，如果原始类并没有定义接口，并且原始类代码并不是我们开发维护的（比如它来自一个第三方的类库），我们也没办法直接修改原始类，给它重新定义一个接口。
 * 对于这种外部类的扩展，我们一般都是采用继承的方式，让代理类继承原始类，然后扩展附加功能。
 */
class UserControllerProxy1 : UserController() {
    private val metricsCollector: MetricsCollector

    init {
        metricsCollector = MetricsCollector()
    }

    override fun login(telephone: String?, password: String?): UserVo {
        val startTimestamp = System.currentTimeMillis()

        // 委托
        val userVo = super.login(telephone, password)

        val endTimeStamp = System.currentTimeMillis()
        val responseTime = (endTimeStamp - startTimestamp).toDouble()
        val requestInfo = RequestInfo("login", responseTime, startTimestamp)
        metricsCollector.recordRequest(requestInfo)
        return userVo
    }

    override fun register(telephone: String?, password: String?): UserVo {
        val startTimestamp = System.currentTimeMillis()

        // 委托
        val userVo = super.register(telephone, password)

        val endTimeStamp = System.currentTimeMillis()
        val responseTime = (endTimeStamp - startTimestamp).toDouble()
        val requestInfo = RequestInfo("register", responseTime, startTimestamp)
        metricsCollector.recordRequest(requestInfo)
        return userVo
    }
}

// UserControllerProxy 使用举例
// 让代理类继承原始类，然后扩展附加功能
// 将 UserController 类对象替换为 UserControllerProxy 类对象，不需要改动太多代码
var userController1: IUserController = UserControllerProxy1()
// userController1.login("", "")
// userController1.register("", "")