package com.seniorlibs.designpattern.bridge.v1

class UrgencyNotification(msgSender : MsgSender) : Notification(msgSender) {
    override fun notify(message: String?) {
        TODO("Not yet implemented")
    }
}