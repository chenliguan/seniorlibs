package com.seniorlibs.designpattern.ch25v3.model

class RequestStat {
    //...省略getter/setter方法...
    var maxResponseTime = 0.0
    var minResponseTime = 0.0
    var avgResponseTime = 0.0
    var p999ResponseTime = 0.0
    var p99ResponseTime = 0.0
    var count: Long = 0
    var tps: Long = 0
}