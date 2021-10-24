package com.seniorlibs.designpattern.proxy.v0

import com.seniorlibs.designpattern.principle.frame.ch25v0.model.UserVo
import com.seniorlibs.designpattern.principle.frame.ch25v3.metrics.MetricsCollector
import com.seniorlibs.designpattern.principle.frame.ch25v3.model.RequestInfo

/**
 * 有两个问题：
 * 第一，性能计数器框架代码侵入到业务代码中，跟业务代码高度耦合。如果未来需要替换这个框架，那替换的成本会比较大。
 * 第二，收集接口请求的代码跟业务代码无关，本就不应该放到一个类中。
 */
// 依赖注入
class UserController(private val metricsCollector: MetricsCollector) {
    //...省略其他属性和方法...

    fun login(telephone: String?, password: String?): UserVo {
        val startTimestamp = System.currentTimeMillis()

        // ... 省略login逻辑...

        val endTimeStamp = System.currentTimeMillis()
        val responseTime = (endTimeStamp - startTimestamp).toDouble()
        val requestInfo = RequestInfo("login", responseTime, startTimestamp)
        metricsCollector.recordRequest(requestInfo)

        //...返回UserVo数据...
        return UserVo()
    }

    fun register(telephone: String?, password: String?): UserVo {
        val startTimestamp = System.currentTimeMillis()

        // ... 省略register逻辑...

        val endTimeStamp = System.currentTimeMillis()
        val responseTime = (endTimeStamp - startTimestamp).toDouble()
        val requestInfo = RequestInfo("register", responseTime, startTimestamp)
        metricsCollector.recordRequest(requestInfo)

        //...返回UserVo数据...
        return UserVo()
    }
}