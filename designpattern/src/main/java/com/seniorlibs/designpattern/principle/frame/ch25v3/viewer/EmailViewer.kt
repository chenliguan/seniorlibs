package com.seniorlibs.designpattern.principle.frame.ch25v3.viewer

import com.seniorlibs.designpattern.principle.frame.ch25v3.model.EmailSender
import com.seniorlibs.designpattern.principle.frame.ch25v3.model.RequestStat

/**
 * 负责将统计结果显示到邮件中
 */
class EmailViewer(
    val emailSender: EmailSender = EmailSender()
) : StatViewer {
    private val toAddresses = mutableListOf<String>()

    fun addToAddress(address: String) {
        toAddresses.add(address)
    }

    override fun output(
        requestStats: Map<String, RequestStat>,
        startTimeInMillis: Long,
        endTimeInMills: Long
    ) {
        // format the requestStats to HTML style. send it to email toAddresses.
    }
}