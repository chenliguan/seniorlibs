package com.seniorlibs.designpattern.principle.frame.ch25v2.viewer

import com.seniorlibs.designpattern.principle.frame.ch25v2.model.EmailSender
import com.seniorlibs.designpattern.principle.frame.ch25v2.model.RequestStat
import java.util.*

/**
 * 负责将统计结果显示到邮件中
 */
class EmailViewer : StatViewer {
    private var emailSender: EmailSender
    private val toAddresses: MutableList<String> = ArrayList()

    constructor() {
        emailSender = EmailSender()
    }

    constructor(emailSender: EmailSender) {
        this.emailSender = emailSender
    }

    fun addToAddress(address: String) {
        toAddresses.add(address)
    }

    override fun output(
        requestStats: Map<String?, RequestStat?>?,
        startTimeInMillis: Long,
        endTimeInMills: Long
    ) {
        // format the requestStats to HTML style.
        // send it to email toAddresses.
    }
}