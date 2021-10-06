package com.seniorlibs.designpattern.ch25v3.report

import android.util.Log
import com.seniorlibs.designpattern.ch25v3.aggregate.Aggregator
import com.seniorlibs.designpattern.ch25v3.repository.MetricsStorage
import com.seniorlibs.designpattern.ch25v3.repository.RedisMetricsStorage
import com.seniorlibs.designpattern.ch25v3.viewer.ConsoleViewer
import com.seniorlibs.designpattern.ch25v3.viewer.StatViewer
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

/**
 * 组装类并定时触发执行统计显示
 */
class ConsoleReporter(
    // 依赖注入。兼顾灵活性和代码的可测试性，这个构造函数继续保留；兼顾代码的易用性，构造函数新增一个封装了默认依赖
    metricsStorage: MetricsStorage = RedisMetricsStorage(),
    aggregator: Aggregator = Aggregator(),
    statViewer: StatViewer = ConsoleViewer()
) : ScheduledReporter(metricsStorage, aggregator, statViewer) {
    private val executor: ScheduledExecutorService
    private val isRunning = AtomicBoolean()

    init {
        this.metricsStorage = metricsStorage
        this.aggregator = aggregator
        this.statViewer = statViewer
        this.executor = Executors.newSingleThreadScheduledExecutor()
    }

    fun startRepeatedReport(periodInSeconds: Long, durationInSeconds: Long) {
        Log.d("ConsoleReporter", "线程：" + Thread.currentThread().name)
        if (isRunning.compareAndSet(false, true)) {
            Log.e("ConsoleReporter", "任务开始执行")
            executor.scheduleAtFixedRate({
                val durationInMillis = durationInSeconds * 5000
                val endTimeInMillis = System.currentTimeMillis()
                val startTimeInMillis = endTimeInMillis - durationInMillis
                doStatAndReport(startTimeInMillis, endTimeInMillis)
                Thread.sleep(1000)
                isRunning.set(false)
            }, 0L, periodInSeconds, TimeUnit.SECONDS)
        } else {
            Log.i("ConsoleReporter", "任务已执行在线程：" + Thread.currentThread().name + " 略过")
        }
    }
}