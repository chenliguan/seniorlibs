package com.seniorlibs.designpattern.ch25v2.viewer;

import com.google.gson.Gson;
import com.seniorlibs.designpattern.ch25v2.model.RequestStat;

import java.util.Map;

/**
 * 负责将统计结果显示到命令行中
 */
public class ConsoleViewer implements StatViewer {
    @Override
    public void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills) {
        System.out.println("Time Span: [" + startTimeInMillis + ", " + endTimeInMills + "]");
        Gson gson = new Gson();
        System.out.println(gson.toJson(requestStats));
    }
}

