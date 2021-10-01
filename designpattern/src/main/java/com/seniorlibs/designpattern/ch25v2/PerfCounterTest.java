package com.seniorlibs.designpattern.ch25v2;

import com.seniorlibs.designpattern.ch25v2.aggregate.Aggregator;
import com.seniorlibs.designpattern.ch25v2.metrics.MetricsCollector;
import com.seniorlibs.designpattern.ch25v2.model.RequestInfo;
import com.seniorlibs.designpattern.ch25v2.report.ConsoleReporter;
import com.seniorlibs.designpattern.ch25v2.report.EmailReporter;
import com.seniorlibs.designpattern.ch25v2.repository.MetricsStorage;
import com.seniorlibs.designpattern.ch25v2.repository.RedisMetricsStorage;
import com.seniorlibs.designpattern.ch25v2.viewer.ConsoleViewer;
import com.seniorlibs.designpattern.ch25v2.viewer.EmailViewer;

/**
 * 使用框架
 */
public class PerfCounterTest {
    public static void main() {
        MetricsStorage storage = new RedisMetricsStorage();
        Aggregator aggregator = new Aggregator();

        // 定时触发统计并将结果显示到终端
        ConsoleViewer consoleViewer = new ConsoleViewer();
        ConsoleReporter consoleReporter = new ConsoleReporter(storage, aggregator, consoleViewer);
        consoleReporter.startRepeatedReport(60, 60);

        // 定时触发统计并将结果输出到邮件
        EmailViewer emailViewer = new EmailViewer();
        emailViewer.addToAddress("wangzheng@xzg.com");
        EmailReporter emailReporter = new EmailReporter(storage, aggregator, emailViewer);
        emailReporter.startDailyReport();

        // 收集接口访问数据
        MetricsCollector collector = new MetricsCollector(storage);
        collector.recordRequest(new RequestInfo("register", 123, 10234));
        collector.recordRequest(new RequestInfo("register", 223, 11234));
        collector.recordRequest(new RequestInfo("register", 323, 12334));
        collector.recordRequest(new RequestInfo("login", 23, 12434));
        collector.recordRequest(new RequestInfo("login", 1223, 14234));

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
