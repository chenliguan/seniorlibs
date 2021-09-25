package com.seniorlibs.designpattern.main

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.designpattern.R
import com.seniorlibs.designpattern.ch14.auth.ApiAuthencatorImpl
import com.seniorlibs.designpattern.ch14.storage.MySqlICredentialStorage
import com.seniorlibs.designpattern.ch25v0.controller.UserController
import com.seniorlibs.designpattern.ch25v0.model.UserVo
import com.seniorlibs.designpattern.ch25v1.metrics.MetricsCollector
import com.seniorlibs.designpattern.ch25v1.model.RequestInfo
import com.seniorlibs.designpattern.ch25v1.report.ConsoleReporter
import com.seniorlibs.designpattern.ch25v1.report.EmailReporter
import com.seniorlibs.designpattern.ch25v1.repository.MetricsStorage
import com.seniorlibs.designpattern.ch25v1.repository.RedisMetricsStorage


/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/8/15
 * Mender:
 * Modify:
 * Description: 主页
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_c14).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_c14 -> {
                authencatorClient()
            }
            R.id.btn_c15_v1 -> {
                metricsClientV1()
            }
            R.id.btn_c15_v2 -> {
                metricsClientV2()
            }
            else -> {
            }
        }
    }

    /**
     * C14 实战：如何利用面向对象设计和编程开发接口鉴权功能？
     */
    fun authencatorClient() {
        val req =
            ("geekbang?AppID=designpattern&Token=IXIGIpJ9hdOBCyjStaDJ5Nom07g=1&Timestamp=1465185768")
        // 通过依赖注入 MySqlICredentialStorage，提高了代码的扩展性，灵活地替换依赖的类
        val authencator =
            ApiAuthencatorImpl(
                MySqlICredentialStorage()
            )
        authencator.auth(req)
    }


    /**
     * 实战：V1 最小原型-针对非业务的通用框架开发，如何做需求分析和设计？
     */
    fun metricsClientV1() {
        val user = UserVo()
        val controller = UserController()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            controller.register(user)
            controller.login(user.telephone, user.password)
        }
    }

    /**
     * 实战：V2 面向对象设计-针对非业务的通用框架开发，如何做需求分析和设计？
     */
    fun metricsClientV2() {
        // 有两个执行入口：一个是 ConsoleReporter 类和 EmailReporter 类，用来触发统计显示；
        val storage: MetricsStorage = RedisMetricsStorage()
        val consoleReporter = ConsoleReporter(storage)
        consoleReporter.startRepeatedReport(60, 60)
        val emailReporter = EmailReporter(storage)
        emailReporter.addToAddress("wangzheng@xzg.com")
        emailReporter.startDailyReport()
        // 另一个是 MetricsCollector 类，提供了一组 API 来采集原始数据。
        val collector = MetricsCollector(storage)
        collector.recordRequest(RequestInfo("register", 123.0, 10234))
        collector.recordRequest(RequestInfo("register", 223.0, 11234))
        collector.recordRequest(RequestInfo("register", 323.0, 12334))
        collector.recordRequest(RequestInfo("login", 23.0, 12434))
        collector.recordRequest(RequestInfo("login", 1223.0, 14234))
        try {
            Thread.sleep(100000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
