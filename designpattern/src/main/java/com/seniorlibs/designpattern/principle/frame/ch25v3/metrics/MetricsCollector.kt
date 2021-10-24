package com.seniorlibs.designpattern.principle.frame.ch25v3.metrics

import com.seniorlibs.baselib.utils.LogUtils
import com.seniorlibs.designpattern.principle.frame.ch25v3.model.RequestInfo
import com.seniorlibs.designpattern.principle.frame.ch25v3.repository.MetricsStorage
import com.seniorlibs.designpattern.principle.frame.ch25v3.repository.RedisMetricsStorage
import external.org.apache.commons.lang3.StringUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * 基于接口而非实现编程，通过依赖注入的方式来传递 MetricsStorage 对象，可以在不需要修改代码的情况下，灵活地替换不同的存储方式，满足开闭原则。
 */
class MetricsCollector(
    // 依赖注入：基于接口而非实现编程。兼顾灵活性和代码的可测试性，这个构造函数继续保留；兼顾代码的易用性，构造函数新增一个封装了默认依赖的
    private val metricsStorage: MetricsStorage = RedisMetricsStorage()
) {
    private val eventBus : EventBus = EventBus()

    // 用一个函数代替了最小原型中的两个函数
    fun recordRequest(requestInfo: RequestInfo) {
        if (StringUtils.isBlank(requestInfo.apiName)) return

        EventBus.getDefault().post(requestInfo)
    }

    /**
     * 当 RequestInfo 被发布时将调用此方法
     * 默认情况下，EventBus捕获从订阅者方法抛出的异常，并发送不强制要求处理的SubscriberExceptionEvent。
     * @param requestInfo
     */
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onRequestInfo(requestInfo: RequestInfo) {
        metricsStorage.saveRequestInfo(requestInfo)
        LogUtils.d("MetricsCollector", "接收到 EventBus 事件：" + requestInfo.apiName)
    }
}