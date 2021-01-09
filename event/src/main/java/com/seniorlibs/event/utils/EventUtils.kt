package com.seniorlibs.event.utils

import android.app.ActivityManager
import android.content.Context
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.WindowManager
import java.io.Closeable
import java.io.IOException

object EventUtils {
    fun getProcessName(cxt: Context, pid: Int): String? {
        val am = cxt
            .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningApps =
            am.runningAppProcesses ?: return null
        for (procInfo in runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName
            }
        }
        return null
    }

    fun close(closeable: Closeable?) {
        try {
            closeable?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun getScreenMetrics(context: Context): DisplayMetrics {
        val wm =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        return dm
    }

    fun executeInThread(runnable: Runnable?) {
        Thread(runnable).start()
    }

    /**
     * 根据id获取ACTION名称
     *
     * @param action
     * @return
     */
    fun getAction(action : Int) : String {
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                return "DOWN"
            }
            MotionEvent.ACTION_UP -> {
                return "UP"
            }
            MotionEvent.ACTION_MOVE -> {
                return "MOVE"
            }
            MotionEvent.ACTION_CANCEL -> {
                return "CANCEL"
            }
            else -> {
                return ""
            }
        }
    }
}