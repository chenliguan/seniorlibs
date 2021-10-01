package com.seniorlibs.designpattern.ch25v2.aggregate;

import com.seniorlibs.designpattern.ch25v2.model.RequestInfo;
import com.seniorlibs.designpattern.ch25v2.model.RequestStat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Aggregator 负责
 * 1.根据给定时间区间，从数据库中拉取数据
 * 2.各种统计数据的计算
 */
public class Aggregator {
    /**
     * 根据给定时间区间，从数据库中拉取数据
     *
     * @param requestInfos
     * @param durationInMillis
     * @return
     */
    public Map<String, RequestStat> aggregate(
            Map<String, List<RequestInfo>> requestInfos, long durationInMillis) {
        Map<String, RequestStat> requestStats = new HashMap<>();
        for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
            String apiName = entry.getKey();
            List<RequestInfo> requestInfosPerApi = entry.getValue();
            RequestStat requestStat = doAggregate(requestInfosPerApi, durationInMillis);
            requestStats.put(apiName, requestStat);
        }
        return requestStats;
    }

    /**
     * 负责各种统计数据的计算
     *
     * @param requestInfos
     * @param durationInMillis
     * @return
     */
    private RequestStat doAggregate(List<RequestInfo> requestInfos, long durationInMillis) {
        List<Double> respTimes = new ArrayList<>();
        for (RequestInfo requestInfo : requestInfos) {
            double respTime = requestInfo.getResponseTime();
            respTimes.add(respTime);
        }

        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(max(respTimes));
        requestStat.setMinResponseTime(min(respTimes));
        requestStat.setAvgResponseTime(avg(respTimes));
        requestStat.setP999ResponseTime(percentile999(respTimes));
        requestStat.setP99ResponseTime(percentile99(respTimes));
        requestStat.setCount(respTimes.size());
        requestStat.setTps((long) tps(respTimes.size(), durationInMillis/1000));
        return requestStat;
    }

    // 以下的函数的代码实现均省略...
    private double max(List<Double> dataset) {
        return 0.0;
    }

    private double min(List<Double> dataset) {
        return 0.0;
    }

    private double avg(List<Double> dataset) {
        return 0.0;
    }

    private double tps(int count, double duration) {
        return 0.0;
    }

    private double percentile999(List<Double> dataset) {
        return 0.0;
    }

    private double percentile99(List<Double> dataset) {
        return 0.0;
    }

    private double percentile(List<Double> dataset, double ratio) {
        return 0.0;
    }
}