package com.seniorlibs.designpattern.principle.frame.ch25v2.viewer

import com.google.gson.Gson
import com.seniorlibs.designpattern.principle.frame.ch25v2.model.RequestStat

/**
 * 负责将统计结果显示到命令行中
 */
class ConsoleViewer : StatViewer {
    override fun output(
        requestStats: Map<String?, RequestStat?>?,
        startTimeInMillis: Long,
        endTimeInMills: Long
    ) {
        println("Time Span: [$startTimeInMillis, $endTimeInMills]")
        val gson = Gson()
        println(gson.toJson(requestStats))
    }
}