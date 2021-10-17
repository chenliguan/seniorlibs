package com.seniorlibs.designpattern.bridge.v0

class NotificationV1 private constructor(builder: Builder) {
    private var emailAddresses: List<String>? = null
    private var telephones: List<String>? = null
    private var wechatIds: List<String>? = null

    init {
        emailAddresses = builder.emailAddresses
        telephones = builder.telephones
        wechatIds = builder.wechatIds
    }

    class Builder {
        internal var emailAddresses: List<String>? = null
        internal var telephones: List<String>? = null
        internal var wechatIds: List<String>? = null

        fun build() : NotificationV1 {
            return NotificationV1(this)
        }

        fun setEmailAddresses(emailAddresses : List<String>?) : Builder {
            this.emailAddresses = emailAddresses
            return this
        }

        fun setTelephones(telephones : List<String>?) : Builder {
            this.telephones = telephones
            return this
        }

        fun setWechatIds(wechatIds : List<String>?) : Builder {
            this.wechatIds = wechatIds
            return this
        }
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

// 使用
val notificationV1 = NotificationV1.Builder()
    .setEmailAddresses(listOf())
    .setTelephones(listOf())
    .setWechatIds(listOf())
    .build()
