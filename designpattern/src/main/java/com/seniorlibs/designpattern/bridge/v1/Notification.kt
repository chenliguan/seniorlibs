package com.seniorlibs.designpattern.bridge.v1

abstract class Notification(private val msgSender : MsgSender) {

    abstract fun notify(message: String?)
}