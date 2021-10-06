package com.seniorlibs.designpattern.ch25v3.report

import androidx.annotation.VisibleForTesting
import com.seniorlibs.designpattern.ch25v3.aggregate.Aggregator
import com.seniorlibs.designpattern.ch25v3.repository.MetricsStorage
import com.seniorlibs.designpattern.ch25v3.repository.RedisMetricsStorage
import com.seniorlibs.designpattern.ch25v3.viewer.ConsoleViewer
import com.seniorlibs.designpattern.ch25v3.viewer.StatViewer
import java.util.*

/**
 * 组装类并定时触发执行统计显示
 */
class EmailReporter(
    // 依赖注入。兼顾灵活性和代码的可测试性，这个构造函数继续保留；兼顾代码的易用性，构造函数新增一个封装了默认依赖
    metricsStorage: MetricsStorage = RedisMetricsStorage(),
    aggregator: Aggregator = Aggregator(),
    statViewer: StatViewer = ConsoleViewer()
) : ScheduledReporter(metricsStorage, aggregator, statViewer) {
    init {
        this.metricsStorage = metricsStorage
        this.aggregator = aggregator
        this.statViewer = statViewer
    }

    /**
     * 此函数涉及多线程，针对这个函数该如何写单元测试呢？
     * 经过多次代码重构之后，此函数里面已经没有多少代码逻辑了，所以，完全没必要对它写单元测试了。
     */
    fun startDailyReport() {
        // new Date()可以获取当前时间
        val firstTime = trimTimeFieldsToZeroOfNextDay(Date())
        val timer = Timer()
        timer.schedule(
            object : TimerTask() {
                override fun run() {
                    val durationInMillis =
                        DAY_HOURS_IN_SECONDS * 1000
                    val endTimeInMillis = System.currentTimeMillis()
                    val startTimeInMillis = endTimeInMillis - durationInMillis
                    doStatAndReport(startTimeInMillis, endTimeInMillis)
                }
            },
            firstTime,
            DAY_HOURS_IN_SECONDS * 1000
        )
    }

    /**
     * 获取当前时间的下一天的 0
     * 解决：不再强依赖当前的系统时间，所以非常容易对其编写单元测试
     *
     * ps：设置成protected而非private是为了方便写单元测试
     */
    @VisibleForTesting
    internal fun trimTimeFieldsToZeroOfNextDay(date: Date?): Date {
        // 这里可以获取当前时间
        val calendar = Calendar.getInstance()
        // 重新设置时间
        calendar.time = date
        calendar.add(Calendar.DATE, 1)
        calendar[Calendar.HOUR_OF_DAY] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
        return calendar.time
    }

    /**
     * 获取当前时间的下一天的 0
     * 问题：这个函数的可测试性仍然不好，因为它强依赖当前的系统时间
     *
     * ps：设置成protected而非private是为了方便写单元测试
     */
    @VisibleForTesting
    internal fun trimTimeFieldsToZeroOfNextDay(): Date {
        // 这里可以获取当前时间
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, 1)
        calendar[Calendar.HOUR_OF_DAY] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
        return calendar.time
    }

    companion object {
        private const val DAY_HOURS_IN_SECONDS = 86400L
    }
}