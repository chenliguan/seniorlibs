package com.seniorlibs.designpattern.bridge.v1

import com.seniorlibs.designpattern.bridge.v0.AlertRule
import com.seniorlibs.designpattern.bridge.v0.ApiStatInfo

// 在API监控告警的例子中，我们如下方式来使用Notification类：
class ErrorAlertHandler(private val rule: AlertRule, private val notification: Notification) :
    AlertHandler(rule, notification) {
    fun check(apiStatInfo: ApiStatInfo) {
        if (apiStatInfo.errorCount > rule.count) {
            notification.notify("...")
        }
    }

//    val notification = SevereNotification(WechatMsgSender(listOf()))
//    val errorAlertHandler = ErrorAlertHandler(AlertRule(), notification)
}