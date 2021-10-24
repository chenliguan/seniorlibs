package com.seniorlibs.designpattern.principle.frame.ch25v0.metrics

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/9/5
 * Mender:
 * Modify:
 * Description: 首先要采集每次接口请求的响应时间，并且存储起来；然后按照某个时间间隔做聚合统计；最后才是将结果输出。
 * 备注：在原型系统的代码实现中，可以把所有代码都塞到一个类中，暂时不用考虑任何代码质量、线程安全、性能、扩展性等等问题，怎么简单怎么来就行
 */
class Metrics {
    // Map 的 key 是接口名称，value 对应接口请求的响应时间或时间戳；
    private val mResponseTimes: MutableMap<String, MutableList<Double>> = HashMap()
    private val mTimestamps: MutableMap<String, MutableList<Double>> = HashMap()
    private val mExecutor = Executors.newSingleThreadScheduledExecutor()

    /**
     * 记录接口请求的响应时间
     *
     * @param apiName
     * @param responseTime
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    fun recordResponseTime(apiName: String, responseTime: Double) {
        mResponseTimes.putIfAbsent(apiName, ArrayList())
        mResponseTimes[apiName]!!.add(responseTime)
    }

    /**
     * 记录接口请求的访问时间
     *
     * @param apiName
     * @param timestamp
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    fun recordTimestamp(apiName: String, timestamp: Double) {
        mTimestamps.putIfAbsent(apiName, ArrayList())
        mTimestamps[apiName]!!.add(timestamp)
    }

    /**
     * 指定的频率统计数据并输出结果
     *
     * @param period
     * @param unit
     */
    fun startRepeatedReport(period: Long, unit: TimeUnit?) {
        mExecutor.scheduleAtFixedRate({
            val gson = Gson()
            val stats: MutableMap<String, MutableMap<String, Double>> = HashMap()
            for ((apiName, apiRespTimes) in mResponseTimes) {
                stats.putIfAbsent(apiName, HashMap())
                stats[apiName]!!["max"] = max(apiRespTimes)
                stats[apiName]!!["avg"] = avg(apiRespTimes)
            }
            for ((apiName, apiTimestamps) in mTimestamps) {
                stats.putIfAbsent(apiName, HashMap())
                stats[apiName]!!["count"] = apiTimestamps.size.toDouble()
            }
            println(gson.toJson(stats))
        }, 0, period, unit)
    }

    private fun max(dataset: List<Double>): Double {
        // 省略代码实现
        return 1
    }

    private fun avg(dataset: List<Double>): Double {
        // 省略代码实现
        return 1
    }
}