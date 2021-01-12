package com.seniorlibs.lifecycle.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.seniorlibs.baselib.utils.LogUtils;
import com.seniorlibs.lifecycle.R;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/1/12.
 * Mender:
 * Modify:
 * Description: 测试四大组件-服务
 */
public class ServiceTestActivity extends Activity implements View.OnClickListener {

    public static final String TAG = TestService.TAG + " + TestActivity";

    private Intent mStartIntent;
    private TestService.TestBinder mBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        initView();
    }

    private void initView() {
        findViewById(R.id.btn_start_service).setOnClickListener(this);
        findViewById(R.id.btn_bind_service).setOnClickListener(this);
        findViewById(R.id.btn_stop_service).setOnClickListener(this);
        findViewById(R.id.btn_unbind_service).setOnClickListener(this);
        findViewById(R.id.btn_send_msg_to_service).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_service:
                // 开启服务
                mStartIntent = new Intent(this, TestService.class);
                startService(mStartIntent);
                break;

            case R.id.btn_bind_service:
                // 绑定服务
                Intent bindIntent = new Intent(this, TestService.class);
                // 参数1：Intent意图
                // 参数2：是一个接口，通过这个接口接收服务开启或者停止的消息，并且这个参数不能为null
                // 参数3：开启服务时的操作，BIND_AUTO_CREATE代表自动创建service
                bindService(bindIntent, mConnection, BIND_AUTO_CREATE);
                break;

            case R.id.btn_stop_service:
                // 结束服务
                stopService(mStartIntent);
                break;

            case R.id.btn_unbind_service:
                // 结束连接服务
                unbindService(mConnection);
                break;

            case R.id.btn_send_msg_to_service:
                // 发送消息给Service
                if (mBinder != null) {
                    mBinder.sendMsgToService("hi, service");
                } else {
                    LogUtils.e(TAG, "请先绑定服务，再执行发送消息给Service");
                }
                break;

            default:
                break;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 只有当TestService的onBind方法返回值不为null时，才会被调用
            LogUtils.d(TAG, "onServiceConnected");

            if (service != null) {
                // 取得Service里的binder对象
                mBinder = (TestService.TestBinder) service;
                // 注册自定义回调
                mBinder.setOnTestListener(new TestService.OnTestListener() {
                    @Override
                    public void onTest(String str) {
                        LogUtils.d(TAG, "收到来自Service的消息: "+str);
                    }
                });
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 只有出现异常时才会调用，服务正常退出不会调用。
            LogUtils.e(TAG, "onServiceDisconnected");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 结束连接服务
        unbindService(mConnection);
    }
}
