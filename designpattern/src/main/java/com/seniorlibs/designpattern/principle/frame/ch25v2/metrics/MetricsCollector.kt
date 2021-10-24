package com.seniorlibs.designpattern.principle.frame.ch25v2.metrics

import com.seniorlibs.designpattern.principle.frame.ch25v2.model.RequestInfo
import com.seniorlibs.designpattern.principle.frame.ch25v2.repository.MetricsStorage
import external.org.apache.commons.lang3.StringUtils

/**
 * 基于接口而非实现编程，通过依赖注入的方式来传递 MetricsStorage 对象，可以在不需要修改代码的情况下，灵活地替换不同的存储方式，满足开闭原则。
 */
class MetricsCollector(
    // 依赖注入：基于接口而非实现编程
    private val metricsStorage: MetricsStorage
) {
    // 用一个函数代替了最小原型中的两个函数
    fun recordRequest(requestInfo: RequestInfo?) {
        if (requestInfo == null || StringUtils.isBlank(requestInfo.apiName)) {
            return
        }
        metricsStorage.saveRequestInfo(requestInfo)
    }
}