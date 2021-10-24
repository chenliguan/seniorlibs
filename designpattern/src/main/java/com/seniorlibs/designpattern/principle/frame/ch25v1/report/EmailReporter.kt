package com.seniorlibs.designpattern.principle.frame.ch25v1.report

import com.seniorlibs.designpattern.principle.frame.ch25v1.aggregate.Aggregator
import com.seniorlibs.designpattern.principle.frame.ch25v1.model.RequestStat
import com.seniorlibs.designpattern.principle.frame.ch25v1.repository.MetricsStorage
import java.util.*

/**
 * ConsoleReporter 和 EmailReporter 中存在代码重复问题。在这两个类中，从数据库中取数据、
 * 做统计的逻辑都是相同的，可以抽取出来复用，否则就违反了 DRY 原则。而且整个类负责的事情比较多，职责不是太单一。
 * 特别是显示部分的代码，可能会比较复杂（比如 Email 的展示方式），最好是将显示部分的代码逻辑拆分成独立的类。
 * 除此之外，因为代码中涉及线程操作，并且调用了 Aggregator 的静态函数，所以代码的可测试性不好。
 */
class EmailReporter(metricsStorage: MetricsStorage?) {
    private val metricsStorage: MetricsStorage? = null

    //    private EmailSender emailSender;
    private val toAddresses: MutableList<String> = ArrayList()

    //    public EmailReporter(MetricsStorage metricsStorage, EmailSender emailSender) {
    //        this.metricsStorage = metricsStorage;
    //        this.emailSender = emailSender;
    //    }
    fun addToAddress(address: String) {
        toAddresses.add(address)
    }

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
                    metricsStorage!!.getRequestInfos(startTimeInMillis, endTimeInMillis)
                val stats: MutableMap<String?, RequestStat?> = HashMap()
                for ((apiName, requestInfosPerApi) in requestInfos!!) {
                    val requestStat = Aggregator.aggregate(requestInfosPerApi, durationInMillis)
                    stats[apiName] = requestStat
                }
                // TODO: 格式化为html格式，并且发送邮件
            }
        }, firstTime, DAY_HOURS_IN_SECONDS * 1000)
    }

    companion object {
        private const val DAY_HOURS_IN_SECONDS = 86400L
    }
}