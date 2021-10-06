package com.seniorlibs.designpattern.ch25v3

import com.seniorlibs.designpattern.ch25v3.aggregate.Aggregator
import com.seniorlibs.designpattern.ch25v3.metrics.MetricsCollector
import com.seniorlibs.designpattern.ch25v3.model.EmailSender
import com.seniorlibs.designpattern.ch25v3.model.RequestInfo
import com.seniorlibs.designpattern.ch25v3.report.ConsoleReporter
import com.seniorlibs.designpattern.ch25v3.report.EmailReporter
import com.seniorlibs.designpattern.ch25v3.repository.MetricsStorage
import com.seniorlibs.designpattern.ch25v3.repository.RedisMetricsStorage
import com.seniorlibs.designpattern.ch25v3.viewer.ConsoleViewer
import com.seniorlibs.designpattern.ch25v3.viewer.EmailViewer

/**
 * 使用框架
 */
object PerfCounterTestV3 {
    fun main() {
        val storage: MetricsStorage = RedisMetricsStorage()
        val aggregator = Aggregator()

        // 定时触发统计并将结果显示到终端
        val consoleViewer = ConsoleViewer()
        val consoleReporter = ConsoleReporter(storage, aggregator, consoleViewer)
        consoleReporter.startRepeatedReport(60, 60)

        // 定时触发统计并将结果输出到邮件
        val emailViewer = EmailViewer()
        emailViewer.addToAddress("wangzheng@xzg.com")
        val emailReporter = EmailReporter(storage, aggregator, emailViewer)
        emailReporter.startDailyReport()

        // 收集接口访问数据
        val collector = MetricsCollector(storage)
        collector.recordRequest(RequestInfo("register", 123.0, 10234))
        collector.recordRequest(RequestInfo("register", 223.0, 11234))
        collector.recordRequest(RequestInfo("register", 323.0, 12334))
        collector.recordRequest(RequestInfo("login", 23.0, 12434))
        collector.recordRequest(RequestInfo("login", 1223.0, 14234))

        try {
            Thread.sleep(100000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun mainDefault() {
        val consoleReporter = ConsoleReporter()
        consoleReporter.startRepeatedReport(60, 60)
        val emailToAddresses = mutableListOf<String>()
        emailToAddresses.add("wangzheng@xzg.com")

        val emailReporter = EmailReporter(statViewer = EmailViewer(EmailSender()))
        emailReporter.startDailyReport()

        // 收集接口访问数据
        val collector = MetricsCollector()
        collector.recordRequest(RequestInfo("register", 123.0, 10234))
        collector.recordRequest(RequestInfo("register", 223.0, 11234))
        collector.recordRequest(RequestInfo("register", 323.0, 12334))
        collector.recordRequest(RequestInfo("login", 23.0, 12434))
        collector.recordRequest(RequestInfo("login", 1223.0, 14234))
        try {
            Thread.sleep(100000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}