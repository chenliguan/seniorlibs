package com.seniorlibs.designpattern.ch25v3.repository

import com.seniorlibs.designpattern.ch25v3.model.RequestInfo

interface MetricsStorage {
    fun saveRequestInfo(requestInfo: RequestInfo)

    fun getRequestInfos(
        apiName: String,
        startTimeInMillis: Long,
        endTimeInMillis: Long
    ): List<RequestInfo>

    fun getRequestInfos(
        startTimeInMillis: Long,
        endTimeInMillis: Long
    ): Map<String, List<RequestInfo>>
}