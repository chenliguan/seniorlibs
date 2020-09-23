package com.seniorlibs.baselib.utils;

import androidx.annotation.NonNull;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2019/9/1.
 * Mender:
 * Modify:
 * Description: Log工具类
 */
public class LogUtils {

    public static void v(String tag, String msg) {
        if (DebugUtils.isLogDebugMode) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DebugUtils.isLogDebugMode) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (DebugUtils.isLogDebugMode) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (DebugUtils.isLogDebugMode) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (DebugUtils.isLogDebugMode) {
            Log.e(tag, msg);
        }
    }

    /**
     * 用于如边界值等情况时主动抛出异常
     */
    public static void error(String msg) {
        if (DebugUtils.isLogDebugMode) {
            throw new RuntimeException(msg);
        } else {
            //TODO 将错误上传到服务器
        }
    }

    /**
     * 所有抛出异常的地方应调用此方法统一管理
     */
    public static void error(Throwable e) {
        if (DebugUtils.isLogDebugMode) {
            e.printStackTrace();
        } else {
            //TODO 将错误上传到服务器
        }
    }

    /**
     * 获取带日期的log文件名
     *
     * @param prefixName
     * @return
     */
    public static String getDebugFilename(@NonNull String prefixName) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault());
        String time = sdf.format(cal.getTime());
        return prefixName + "_" + time + ".log";
    }

    @Deprecated
    public static void error(String tag, String msg) {
        e(tag, msg);
    }

    @Deprecated
    public static void verbose(String logTag, String s) {
        v(logTag, s);
    }

    @Deprecated
    public static void warn(String s, String message) {
        w(s, message);
    }

    @Deprecated
    public static void debug(String logTag, String s) {
        d(logTag, s);
    }

    @Deprecated
    public static void info(String logTag, String s) {
        i(logTag, s);
    }

    public static void printException(String tag, Throwable t) {
        e(tag, t.toString());
    }

    public static void printException(String logTag, String scheduler, Throwable t) {
        e(logTag, scheduler + ", error:" + t.toString());
    }
}
