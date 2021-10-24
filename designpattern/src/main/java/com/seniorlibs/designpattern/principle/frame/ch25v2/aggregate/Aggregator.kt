package com.seniorlibs.designpattern.principle.frame.ch25v2.aggregate

import com.seniorlibs.designpattern.principle.frame.ch25v2.model.RequestInfo
import com.seniorlibs.designpattern.principle.frame.ch25v2.model.RequestStat
import java.util.*

/**
 * Aggregator 负责
 * 1.根据给定时间区间，从数据库中拉取数据
 * 2.各种统计数据的计算
 */
class Aggregator {
    /**
     * 根据给定时间区间，从数据库中拉取数据
     *
     * @param requestInfos
     * @param durationInMillis
     * @return
     */
    fun aggregate(
        requestInfos: Map<String, List<RequestInfo>>, durationInMillis: Long
    ): Map<String?, RequestStat> {
        val requestStats: MutableMap<String?, RequestStat> = HashMap()
        for ((apiName, requestInfosPerApi) in requestInfos) {
            val requestStat = doAggregate(requestInfosPerApi, durationInMillis)
            requestStats[apiName] = requestStat
        }
        return requestStats
    }

    /**
     * 负责各种统计数据的计算
     *
     * @param requestInfos
     * @param durationInMillis
     * @return
     */
    private fun doAggregate(
        requestInfos: List<RequestInfo>,
        durationInMillis: Long
    ): RequestStat {
        val respTimes = mutableListOf<Double>()
        for (requestInfo in requestInfos) {
            val respTime = requestInfo.responseTime
            respTimes.add(respTime)
        }
        val requestStat = RequestStat()
        requestStat.maxResponseTime = max(respTimes)
        requestStat.minResponseTime = min(respTimes)
        requestStat.avgResponseTime = avg(respTimes)
        requestStat.p999ResponseTime = percentile999(respTimes)
        requestStat.p99ResponseTime = percentile99(respTimes)
        requestStat.count = respTimes.size.toLong()
        requestStat.tps = tps(respTimes.size, (durationInMillis / 1000).toDouble()).toLong()
        return requestStat
    }

    // 以下的函数的代码实现均省略...
    private fun max(dataset: List<Double>): Double {
        return 0.0
    }

    private fun min(dataset: List<Double>): Double {
        return 0.0
    }

    private fun avg(dataset: List<Double>): Double {
        return 0.0
    }

    private fun tps(count: Int, duration: Double): Double {
        return 0.0
    }

    private fun percentile999(dataset: List<Double>): Double {
        return 0.0
    }

    private fun percentile99(dataset: List<Double>): Double {
        return 0.0
    }

    private fun percentile(dataset: List<Double>, ratio: Double): Double {
        return 0.0
    }
}