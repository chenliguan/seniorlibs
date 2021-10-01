package com.seniorlibs.designpattern.ch25v2.viewer;

import com.seniorlibs.designpattern.ch25v2.model.EmailSender;
import com.seniorlibs.designpattern.ch25v2.model.RequestStat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 负责将统计结果显示到邮件中
 */
public class EmailViewer implements StatViewer {
    private EmailSender emailSender;
    private List<String> toAddresses = new ArrayList<>();

    public EmailViewer() {
        this.emailSender = new EmailSender(/*省略参数*/);
    }

    public EmailViewer(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void addToAddress(String address) {
        toAddresses.add(address);
    }

    @Override
    public void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills) {
        // format the requestStats to HTML style.
        // send it to email toAddresses.
    }
}

