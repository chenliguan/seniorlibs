package com.seniorlibs.designpattern.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.designpattern.R
import com.seniorlibs.designpattern.ch14.auth.DefaultIApiAuthencatorImpl
import com.seniorlibs.designpattern.ch14.storage.MySqlICredentialStorage

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
        findViewById<View>(R.id.btn_c_14).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_c_14 -> {
                authencatorClient()
            }
            else -> {
            }
        }
    }

    /**
     * C14 鉴权功能客户端
     */
    fun authencatorClient() {
        val req =
            ("geekbang?AppID=designpattern&Token=IXIGIpJ9hdOBCyjStaDJ5Nom07g=1&Timestamp=1465185768")
        // 通过依赖注入 MySqlICredentialStorage，提高了代码的扩展性，灵活地替换依赖的类
        val authencator =
            DefaultIApiAuthencatorImpl(
                MySqlICredentialStorage()
            )
        authencator.auth(req)
    }
}
