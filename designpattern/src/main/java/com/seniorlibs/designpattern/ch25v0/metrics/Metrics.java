package com.seniorlibs.designpattern.ch25v0.metrics;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/9/5
 * Mender:
 * Modify:
 * Description: 首先要采集每次接口请求的响应时间，并且存储起来；然后按照某个时间间隔做聚合统计；最后才是将结果输出。
 * 备注：在原型系统的代码实现中，可以把所有代码都塞到一个类中，暂时不用考虑任何代码质量、线程安全、性能、扩展性等等问题，怎么简单怎么来就行
 */
public class Metrics {

    // Map 的 key 是接口名称，value 对应接口请求的响应时间或时间戳；
    private Map<String, List<Double>> mResponseTimes = new HashMap<>();
    private Map<String, List<Double>> mTimestamps = new HashMap<>();
    private ScheduledExecutorService mExecutor = Executors.newSingleThreadScheduledExecutor();

    /**
     * 记录接口请求的响应时间
     *
     * @param apiName
     * @param responseTime
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void recordResponseTime(String apiName, double responseTime) {
        mResponseTimes.putIfAbsent(apiName, new ArrayList<>());
        mResponseTimes.get(apiName).add(responseTime);
    }

    /**
     * 记录接口请求的访问时间
     *
     * @param apiName
     * @param timestamp
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void recordTimestamp(String apiName, double timestamp) {
        mTimestamps.putIfAbsent(apiName, new ArrayList<>());
        mTimestamps.get(apiName).add(timestamp);
    }

    /**
     * 指定的频率统计数据并输出结果
     *
     * @param period
     * @param unit
     */
    public void startRepeatedReport(long period, TimeUnit unit) {
        mExecutor.scheduleAtFixedRate(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                Gson gson = new Gson();
                Map<String, Map<String, Double>> stats = new HashMap<>();
                for (Map.Entry<String, List<Double>> entry : mResponseTimes.entrySet()) {
                    String apiName = entry.getKey();
                    List<Double> apiRespTimes = entry.getValue();
                    stats.putIfAbsent(apiName, new HashMap<>());
                    stats.get(apiName).put("max", max(apiRespTimes));
                    stats.get(apiName).put("avg", avg(apiRespTimes));
                }

                for (Map.Entry<String, List<Double>> entry : mTimestamps.entrySet()) {
                    String apiName = entry.getKey();
                    List<Double> apiTimestamps = entry.getValue();
                    stats.putIfAbsent(apiName, new HashMap<>());
                    stats.get(apiName).put("count", (double) apiTimestamps.size());
                }
                System.out.println(gson.toJson(stats));
            }
        }, 0, period, unit);
    }

    private double max(List<Double> dataset) {
        // 省略代码实现
        return 1;
    }

    private double avg(List<Double> dataset) {
        // 省略代码实现
        return 1;
    }
}