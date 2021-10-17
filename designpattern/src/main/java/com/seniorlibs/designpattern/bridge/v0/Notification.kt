package com.seniorlibs.designpattern.bridge.v0

class Notification {
    private var emailAddresses: List<String>? = null
    private var telephones: List<String>? = null
    private var wechatIds: List<String>? = null
    fun setEmailAddress(emailAddress: List<String>?) {
        emailAddresses = emailAddress
    }

    fun setTelephones(telephones: List<String>?) {
        this.telephones = telephones
    }

    fun setWechatIds(wechatIds: List<String>?) {
        this.wechatIds = wechatIds
    }

    fun notify(level: NotificationEmergencyLevel, message: String?) {
        if (level == NotificationEmergencyLevel.SEVERE) {
            //...自动语音电话
        } else if (level == NotificationEmergencyLevel.URGENCY) {
            //...发微信
        } else if (level == NotificationEmergencyLevel.NORMAL) {
            //...发邮件
        } else if (level == NotificationEmergencyLevel.TRIVIAL) {
            //...发邮件
        }
    }
}