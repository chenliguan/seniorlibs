package com.seniorlibs.designpattern.main

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.designpattern.R
import com.seniorlibs.designpattern.ch14.auth.DefaultIApiAuthencatorImpl
import com.seniorlibs.designpattern.ch14.storage.MySqlICredentialStorage
import com.seniorlibs.designpattern.ch25v1.controller.UserController
import com.seniorlibs.designpattern.ch25v1.model.UserVo

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
                metricsClient()
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
        val authencator = DefaultIApiAuthencatorImpl(MySqlICredentialStorage())
        authencator.auth(req)
    }


    /**
     * c15_v1 实战：V1 最小原型-针对非业务的通用框架开发，如何做需求分析和设计？
     */
    fun metricsClient() {
        val user = UserVo()
        val controller = UserController()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            controller.register(user)
            controller.login(user.telephone, user.password)
        }
    }
}
