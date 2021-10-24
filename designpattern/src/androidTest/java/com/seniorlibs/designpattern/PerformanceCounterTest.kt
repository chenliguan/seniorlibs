package com.seniorlibs.designpattern

import com.seniorlibs.designpattern.principle.frame.ch25v3.report.EmailReporter
import org.junit.Assert
import org.junit.Test
import java.util.Date

/**
 * 性能计数器的第三轮重构：编写完善的单元测试
 */
class PerformanceCounterTest {

    @Test
    fun testTrimTimeFieldsToZeroOfNextDay() {
        val reporter = EmailReporter()
        val localDate = Date()
        val trimTime = reporter.trimTimeFieldsToZeroOfNextDay(Date())
        Assert.assertEquals(localDate, trimTime)
    }
}