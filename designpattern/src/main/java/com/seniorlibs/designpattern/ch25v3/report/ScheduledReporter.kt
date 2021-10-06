package com.seniorlibs.designpattern.ch25v3.report

import android.os.Build
import androidx.annotation.RequiresApi
import com.seniorlibs.designpattern.ch25v3.aggregate.Aggregator
import com.seniorlibs.designpattern.ch25v3.model.RequestInfo
import com.seniorlibs.designpattern.ch25v3.model.RequestStat
import com.seniorlibs.designpattern.ch25v3.repository.MetricsStorage
import com.seniorlibs.designpattern.ch25v3.viewer.StatViewer

/**
 * 将 ConsoleReporter 和 EmailReporter 中的相同代码逻辑，提取到父类 ScheduledReporter 中，以解决代码重复问题。
 */
abstract class ScheduledReporter(
    internal var metricsStorage: MetricsStorage,
    internal var aggregator: Aggregator,
    internal var statViewer: StatViewer
) {
    internal fun doStatAndReport(startTimeInMillis: Long, endTimeInMillis: Long) {
        val durationInMillis = endTimeInMillis - startTimeInMillis
        val requestInfos =
            metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis)
        val requestStats =
            aggregator.aggregate(requestInfos, durationInMillis)
        statViewer.output(requestStats, startTimeInMillis, endTimeInMillis)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun doStat(
        startTimeInMillis: Long,
        endTimeInMillis: Long
    ): Map<String?, RequestStat?> {
        val segmentStats = mutableMapOf<String, MutableList<RequestStat>>()
        var segmentStartTimeMillis = startTimeInMillis
        while (segmentStartTimeMillis < endTimeInMillis) {
            var segmentEndTimeMillis = segmentStartTimeMillis + MAX_STAT_DURATION_IN_MILLIS
            if (segmentEndTimeMillis > endTimeInMillis) {
                segmentEndTimeMillis = endTimeInMillis
            }
            val requestInfos: Map<String, List<RequestInfo>> =
                metricsStorage.getRequestInfos(segmentStartTimeMillis, segmentEndTimeMillis)
            if (requestInfos.isEmpty()) {
                continue
            }
            val segmentStat: Map<String, RequestStat> =
                aggregator.aggregate(
                    requestInfos, segmentEndTimeMillis - segmentStartTimeMillis
                )
            addStat(segmentStats, segmentStat)
            segmentStartTimeMillis += MAX_STAT_DURATION_IN_MILLIS
        }
        val durationInMillis = endTimeInMillis - startTimeInMillis
        return aggregateStats(segmentStats, durationInMillis)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun addStat(
        segmentStats: MutableMap<String, MutableList<RequestStat>>,
        segmentStat: Map<String, RequestStat>
    ) {
        for ((apiName, stat) in segmentStat) {
            val statList = segmentStats.putIfAbsent(apiName, ArrayList())!!
            statList.add(stat)
        }
    }

    private fun aggregateStats(
        segmentStats: Map<String, MutableList<RequestStat>>,
        durationInMillis: Long
    ): Map<String?, RequestStat?> {
        val aggregatedStats = mutableMapOf<String?, RequestStat?>()
        for ((apiName, apiStats) in segmentStats) {
            var maxRespTime = Double.MIN_VALUE
            var minRespTime = Double.MAX_VALUE
            var count: Long = 0
            var sumRespTime = 0.0
            for (stat in apiStats) {
                if (stat.maxResponseTime > maxRespTime) maxRespTime = stat.maxResponseTime
                if (stat.maxResponseTime < minRespTime) minRespTime = stat.maxResponseTime
                count += stat.count
                sumRespTime += stat.count * stat.avgResponseTime
            }
            val aggregatedStat = RequestStat()
            aggregatedStat.maxResponseTime = maxRespTime
            aggregatedStat.minResponseTime = minRespTime
            aggregatedStat.avgResponseTime = (sumRespTime / count)
            aggregatedStat.count = count
            aggregatedStat.tps = (count / durationInMillis * 1000)
            aggregatedStats[apiName] = aggregatedStat
        }
        return aggregatedStats
    }

    companion object {
        private const val MAX_STAT_DURATION_IN_MILLIS = 10 * 60 * 1000 // 10minutes.toLong()
    }
}