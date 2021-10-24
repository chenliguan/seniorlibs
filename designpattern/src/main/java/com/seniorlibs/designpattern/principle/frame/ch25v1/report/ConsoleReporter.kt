package com.seniorlibs.designpattern.principle.frame.ch25v1.report

import com.google.gson.Gson
import com.seniorlibs.designpattern.principle.frame.ch25v1.aggregate.Aggregator
import com.seniorlibs.designpattern.principle.frame.ch25v1.model.RequestStat
import com.seniorlibs.designpattern.principle.frame.ch25v1.repository.MetricsStorage
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * ConsoleReporter 和 EmailReporter 中存在代码重复问题。在这两个类中，从数据库中取数据、
 * 做统计的逻辑都是相同的，可以抽取出来复用，否则就违反了 DRY 原则。而且整个类负责的事情比较多，职责不是太单一。
 * 特别是显示部分的代码，可能会比较复杂（比如 Email 的展示方式），最好是将显示部分的代码逻辑拆分成独立的类。
 * 除此之外，因为代码中涉及线程操作，并且调用了 Aggregator 的静态函数，所以代码的可测试性不好。
 */
class ConsoleReporter(private val metricsStorage: MetricsStorage) {
    private val executor: ScheduledExecutorService

    // 第4个代码逻辑：定时触发第1、2、3代码逻辑的执行；
    fun startRepeatedReport(periodInSeconds: Long, durationInSeconds: Long) {
        executor.scheduleAtFixedRate({ // 第1个代码逻辑：根据给定的时间区间，从数据库中拉取数据；
            val durationInMillis = durationInSeconds * 1000
            val endTimeInMillis = System.currentTimeMillis()
            val startTimeInMillis = endTimeInMillis - durationInMillis
            val requestInfos = metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis)
            val stats: MutableMap<String?, RequestStat?> = HashMap()
            for ((apiName, requestInfosPerApi) in requestInfos!!) {
                // 第2个代码逻辑：根据原始数据，计算得到统计数据；
                val requestStat = Aggregator.aggregate(requestInfosPerApi, durationInMillis)
                stats[apiName] = requestStat
            }
            // 第3个代码逻辑：将统计数据显示到终端（命令行或邮件）；
            println("Time Span: [$startTimeInMillis, $endTimeInMillis]")
            val gson = Gson()
            println(gson.toJson(stats))
        }, 0, periodInSeconds, TimeUnit.SECONDS)
    }

    init {
        executor = Executors.newSingleThreadScheduledExecutor()
    }
}