package com.seniorlibs.designpattern.principle.frame.ch25v3.viewer

import com.seniorlibs.designpattern.principle.frame.ch25v3.model.RequestStat

interface StatViewer {
    fun output(
        requestStats: Map<String, RequestStat>,
        startTimeInMillis: Long,
        endTimeInMills: Long
    )
}