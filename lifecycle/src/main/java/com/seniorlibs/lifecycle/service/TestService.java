package com.seniorlibs.lifecycle.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

import com.seniorlibs.baselib.utils.LogUtils;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/1/12.
 * Mender:
 * Modify:
 * Description: 测试四大组件-服务
 */
public class TestService extends Service {

    public static final String TAG = "TestService";

    private Handler sWork = new Handler();

    private TestBinder mBinder = new TestBinder();
    private OnTestListener mListener;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d(TAG, "onCreate---");

        // 制造异常，模拟系统杀死该进程
//        sWork.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                LogUtils.d(TAG, 2000 / 1000 + "s after---");
//                // 故意制造异常，使该进程挂掉
//                Integer.parseInt("ok");
//            }
//        }, 5000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.d(TAG, "onStartCommand---startId = " + startId + " and intent = " + intent);
        // 实验中，可轮换这几个值测试 ：START_NOT_STICKY | START_STICKY | START_STICKY_COMPATIBILITY | START_REDELIVER_INTENT;
//        return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.d(TAG, "onBind---");
        // 返回mBinder对象
        return mBinder;
    }

    @Override
    public void onDestroy() {
        LogUtils.d(TAG, "onDestroy---");
        super.onDestroy();
    }


    /**
     * 新建一个继承自Binder的类TestBinder
     */
    public class TestBinder extends Binder {
        // Activity通过Binder来调用Service的方法将消息传给Service
        public void sendMsgToService(String msg) {
            LogUtils.d(TAG, "收到来自Activity的消息: " + msg);
            // 并回调mListener.onTest告诉Activity已收到消息
            if (mListener != null) {
                mListener.onTest("hi, activity");
            }
        }

        // TestBinder里面提供一个注册回调的方法
        public void setOnTestListener(OnTestListener listener) {
            mListener = listener;
        }
    }

    /**
     * 自定义一个回调接口
     */
    public interface OnTestListener {
        void onTest(String str);
    }
}