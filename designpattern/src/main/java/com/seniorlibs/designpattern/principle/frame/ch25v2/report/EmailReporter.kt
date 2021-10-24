package com.seniorlibs.designpattern.principle.frame.ch25v2.report

import com.seniorlibs.designpattern.principle.frame.ch25v2.aggregate.Aggregator
import com.seniorlibs.designpattern.principle.frame.ch25v2.repository.MetricsStorage
import com.seniorlibs.designpattern.principle.frame.ch25v2.viewer.StatViewer
import java.util.*

/**
 * 组装类并定时触发执行统计显示
 */
class EmailReporter(
    private val metricsStorage: MetricsStorage,
    private val aggregator: Aggregator,
    private val viewer: StatViewer
) {
    fun startDailyReport() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, 1)
        calendar[Calendar.HOUR_OF_DAY] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
        val firstTime = calendar.time
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                val durationInMillis = DAY_HOURS_IN_SECONDS * 1000
                val endTimeInMillis = System.currentTimeMillis()
                val startTimeInMillis = endTimeInMillis - durationInMillis
                val requestInfos =
                    metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis)
                val stats = aggregator.aggregate(requestInfos, durationInMillis)
                viewer.output(stats, startTimeInMillis, endTimeInMillis)
            }
        }, firstTime, DAY_HOURS_IN_SECONDS * 1000)
    }

    companion object {
        private const val DAY_HOURS_IN_SECONDS = 86400L
    }
}