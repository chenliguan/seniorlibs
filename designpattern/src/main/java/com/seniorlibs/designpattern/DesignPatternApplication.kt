package com.seniorlibs.designpattern

import android.content.Context
import androidx.multidex.MultiDex
import com.seniorlibs.baselib.BaseApplication

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/8/15
 * Mender:
 * Modify:
 * Description: 主页
 */
class DesignPatternApplication : BaseApplication() {

    companion object {
        var context: Context? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}