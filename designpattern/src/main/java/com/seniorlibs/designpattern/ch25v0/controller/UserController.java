package com.seniorlibs.designpattern.ch25v0.controller;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.seniorlibs.designpattern.ch25v0.metrics.Metrics;
import com.seniorlibs.designpattern.ch25v0.model.UserVo;
import java.util.concurrent.TimeUnit;

/**
 * 统计下面两个接口(注册和登录）的响应时间和访问次数
 */
public class UserController {

    private Metrics mMetrics = new Metrics();

    public UserController() {
        mMetrics.startRepeatedReport(60, TimeUnit.SECONDS);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void register(UserVo user) {
        long startTimestamp = System.currentTimeMillis();
        mMetrics.recordTimestamp("regsiter", startTimestamp);
        //...
        long respTime = System.currentTimeMillis() - startTimestamp;
        mMetrics.recordResponseTime("register", respTime);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public UserVo login(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        mMetrics.recordTimestamp("login", startTimestamp);
        //...
        long respTime = System.currentTimeMillis() - startTimestamp;
        mMetrics.recordResponseTime("login", respTime);
        return new UserVo();
    }
}
