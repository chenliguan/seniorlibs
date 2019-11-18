package com.seniorlibs.binder.customaidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.seniorlibs.binder.R;

/**
 * 客户端
 */
public class MyAidlManagerActivity extends Activity {

    private IMyAidlManager iMyAidlManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_aidl_manager);

        // 绑定服务
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }
    /**
     * 点击“AIDL”按钮事件
     */
    public void add(View view) {
        try {
            int res = iMyAidlManager.add(1, 2);
            Log.i("TAG", "从服务端调用成功的结果：" + res);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("TAG", "服务端未开启");
        }
    }
    /**
     * 服务回调方法
     */
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iMyAidlManager = MyAidlManagerImpl.asInterface(service);
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            iMyAidlManager = null;
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑服务，回收资源
        unbindService(mConnection);
    }
}
