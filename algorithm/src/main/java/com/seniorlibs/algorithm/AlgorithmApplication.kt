package com.seniorlibs.algorithm

import android.content.Context
import androidx.multidex.MultiDex
import com.seniorlibs.baselib.BaseApplication

class AlgorithmApplication : BaseApplication() {

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