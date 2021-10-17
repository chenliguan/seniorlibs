package com.seniorlibs.designpattern.bridge.v1

class TrivialNotification(msgSender : MsgSender) : Notification(msgSender) {
    override fun notify(message: String?) {
        TODO("Not yet implemented")
    }
}