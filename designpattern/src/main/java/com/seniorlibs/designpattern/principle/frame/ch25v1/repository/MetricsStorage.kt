package com.seniorlibs.designpattern.principle.frame.ch25v1.repository

import com.seniorlibs.designpattern.principle.frame.ch25v1.model.RequestInfo

interface MetricsStorage {
    fun saveRequestInfo(requestInfo: RequestInfo?)

    fun getRequestInfos(
        apiName: String?,
        startTimeInMillis: Long,
        endTimeInMillis: Long
    ): List<RequestInfo>

    fun getRequestInfos(
        startTimeInMillis: Long,
        endTimeInMillis: Long
    ): Map<String, List<RequestInfo>>
}