package com.seniorlibs.designpattern.principle.frame.ch25v2.report

import com.seniorlibs.designpattern.principle.frame.ch25v2.aggregate.Aggregator
import com.seniorlibs.designpattern.principle.frame.ch25v2.repository.MetricsStorage
import com.seniorlibs.designpattern.principle.frame.ch25v2.viewer.StatViewer
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * 组装类并定时触发执行统计显示
 */
class ConsoleReporter(
    private val metricsStorage: MetricsStorage,
    private val aggregator: Aggregator,
    private val viewer: StatViewer
) {
    private val executor: ScheduledExecutorService

    fun startRepeatedReport(periodInSeconds: Long, durationInSeconds: Long) {
        executor.scheduleAtFixedRate({
            val durationInMillis = durationInSeconds * 1000
            val endTimeInMillis = System.currentTimeMillis()
            val startTimeInMillis = endTimeInMillis - durationInMillis
            val requestInfos = metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis)
            val requestStats = aggregator.aggregate(requestInfos, durationInMillis)
            viewer.output(requestStats, startTimeInMillis, endTimeInMillis)
        }, 0L, periodInSeconds, TimeUnit.SECONDS)
    }

    init {
        executor = Executors.newSingleThreadScheduledExecutor()
    }
}