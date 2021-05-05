package com.seniorlibs.thirdlib.okhttp;

/**
 * 0kHttp 事件统计对象，存储请求和响应相关的数据
 */
public class OkHttpEvent {

    /**
     * DNS 解析开始时间
     */
    public long dnsStartTime;
    /**
     * DNS 解析结束时间
     */
    public long dnsEndTime;
    /**
     * 响应体大小，大于一定值时上报给服务器
     */
    public long responseBodySize;
    /**
     * 是否请求成功
     */
    public boolean apiSuccess;
    /**
     * 错误原因
     */
    public String errorReason;
}
