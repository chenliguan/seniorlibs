package com.seniorlibs.designpattern.ch25v2.viewer;

import com.seniorlibs.designpattern.ch25v2.model.RequestStat;

import java.util.Map;

public interface StatViewer {
    void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills);
}
