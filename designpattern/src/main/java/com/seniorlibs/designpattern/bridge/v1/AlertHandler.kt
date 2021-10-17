package com.seniorlibs.designpattern.bridge.v1

import com.seniorlibs.designpattern.bridge.v0.AlertRule

// 在API监控告警的例子中，我们如下方式来使用Notification类：
open class AlertHandler(rule: AlertRule?, notification: Notification?) {

}