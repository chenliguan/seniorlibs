package com.seniorlibs.designpattern.principle.frame.ch25v1.aggregate

import com.seniorlibs.designpattern.principle.frame.ch25v1.model.RequestInfo
import com.seniorlibs.designpattern.principle.frame.ch25v1.model.RequestStat
import java.util.*

/**
 * Aggregator 类是一个工具类，里面只有一个静态函数，有 50 行左右的代码量，负责各种统计数据的计算。
 * 当需要扩展新的统计功能的时候，需要修改 aggregate() 函数代码，并且一旦越来越多的统计功能添加进来之后，
 * 这个函数的代码量会持续增加，可读性、可维护性就变差了。所以，从刚刚的分析来看，
 * 这个类的设计可能存在职责不够单一、不易扩展等问题，需要在之后的版本中，对其结构做优化。
 */
object Aggregator {
    fun aggregate(requestInfos: List<RequestInfo?>?, durationInMillis: Long): RequestStat {
        var maxRespTime = Double.MIN_VALUE
        var minRespTime = Double.MAX_VALUE
        var avgRespTime = -1.0
        var p999RespTime = -1.0
        var p99RespTime = -1.0
        var sumRespTime = 0.0
        var count: Long = 0
        for (requestInfo in requestInfos!!) {
            ++count
            val respTime = requestInfo.getResponseTime()
            if (maxRespTime < respTime) {
                maxRespTime = respTime
            }
            if (minRespTime > respTime) {
                minRespTime = respTime
            }
            sumRespTime += respTime
        }
        if (count != 0L) {
            avgRespTime = sumRespTime / count
        }
        Collections.sort(requestInfos, Comparator<RequestInfo> { o1, o2 ->
            val diff = o1.responseTime - o2.responseTime
            if (diff < 0.0) {
                -1
            } else if (diff > 0.0) {
                1
            } else {
                0
            }
        })
        val idx999 = (count * 0.999).toInt()
        val idx99 = (count * 0.99).toInt()
        if (count != 0L) {
            p999RespTime = requestInfos[idx999].getResponseTime()
            p99RespTime = requestInfos[idx99].getResponseTime()
        }
        val requestStat = RequestStat()
        requestStat.maxResponseTime = maxRespTime
        requestStat.minResponseTime = minRespTime
        requestStat.avgResponseTime = avgRespTime
        requestStat.p999ResponseTime = p999RespTime
        requestStat.p99ResponseTime = p99RespTime
        requestStat.count = count
        requestStat.tps = (count / durationInMillis * 1000)
        return requestStat
    }
}