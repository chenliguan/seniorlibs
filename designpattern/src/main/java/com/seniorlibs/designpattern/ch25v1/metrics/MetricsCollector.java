package com.seniorlibs.designpattern.ch25v1.metrics;

import com.seniorlibs.designpattern.ch25v1.model.RequestInfo;
import com.seniorlibs.designpattern.ch25v1.repository.MetricsStorage;
import external.org.apache.commons.lang3.StringUtils;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/9/5
 * Mender:
 * Modify:
 * Description: 统计实体
 *
 * MetricsCollector 负责采集和存储数据，职责相对来说还算比较单一。它基于接口而非实现编程，
 * 通过依赖注入的方式来传递 MetricsStorage 对象，可以在不需要修改代码的情况下，灵活地替换不同的存储方式，满足开闭原则。
 */
public class MetricsCollector {

    // 基于接口而非实现编程
    private MetricsStorage metricsStorage;

    /**
     * 依赖注入
     *
     * @param metricsStorage
     */
    public MetricsCollector(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    /**
     * 用一个函数代替了最小原型中的两个函数
     *
     * @param requestInfo
     */
    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())) {
            return;
        }
        metricsStorage.saveRequestInfo(requestInfo);
    }
}


