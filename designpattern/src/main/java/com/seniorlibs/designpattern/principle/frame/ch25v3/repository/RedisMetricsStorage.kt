package com.seniorlibs.designpattern.principle.frame.ch25v3.repository

import com.seniorlibs.designpattern.principle.frame.ch25v3.model.RequestInfo
import java.util.*

/**
 * MetricsStorage 和 RedisMetricsStorage 的设计比较简单。当我们需要实现新的存储方式的时候，只需要实现 MetricsStorage 接口即可。
 * 因为所有用到 MetricsStorage 和 RedisMetricsStorage 的地方，都是基于相同的接口函数来编程的，
 * 所以，除了在组装类的地方有所改动（从 RedisMetricsStorage 改为新的存储实现类），其他接口函数调用的地方都不需要改动，满足开闭原则。
 */
class RedisMetricsStorage : MetricsStorage {
    // ...省略属性和构造函数等...
    override fun saveRequestInfo(requestInfo: RequestInfo) {
        // ...
    }

    override fun getRequestInfos(
        apiName: String,
        startTimestamp: Long,
        endTimestamp: Long
    ): List<RequestInfo> {
        // ...
        return ArrayList()
    }

    override fun getRequestInfos(
        startTimestamp: Long,
        endTimestamp: Long
    ): Map<String, List<RequestInfo>> {
        // ...
        return HashMap()
    }
}