package com.seniorlibs.designpattern.proxy.v2

import com.seniorlibs.designpattern.ch25v3.metrics.MetricsCollector
import com.seniorlibs.designpattern.ch25v3.model.RequestInfo
import com.seniorlibs.designpattern.proxy.v1.IUserController
import com.seniorlibs.designpattern.proxy.v1.UserController
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class MetricsCollectorProxy {
    private val metricsCollector: MetricsCollector

    init {
        metricsCollector = MetricsCollector()
    }

    fun createProxy(proxiedObject: Any): Any {
        val interfaces = proxiedObject.javaClass.interfaces
        val handler = DynamicProxyHandler(proxiedObject)
        return Proxy.newProxyInstance(proxiedObject.javaClass.classLoader, interfaces, handler)
    }

    private inner class DynamicProxyHandler(private val proxiedObject: Any) : InvocationHandler {
        @Throws(Throwable::class)
        override operator fun invoke(
            proxy: Any?,
            method: Method,
            args: Array<Any?>?
        ): Any {
            val startTimestamp = System.currentTimeMillis()

            // 等同：val userVo = super.login(telephone, password)
            val result: Any = method.invoke(proxiedObject, args)

            val endTimeStamp = System.currentTimeMillis()
            val responseTime = (endTimeStamp - startTimestamp).toDouble()
            val apiName = proxiedObject.javaClass.name + ":" + method.getName()
            val requestInfo = RequestInfo(apiName, responseTime, startTimestamp)
            metricsCollector.recordRequest(requestInfo)
            return result
        }

    }
}

// 动态代理 MetricsCollectorProxy 使用举例
var proxy = MetricsCollectorProxy()
var userController2 = proxy.createProxy(UserController()) as IUserController
// userController2.login("", "")
// userController2.register("", "")