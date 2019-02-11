package com.seniorlibs.ipc;

import android.app.Application;
import android.os.Process;
import android.util.Log;

import com.seniorlibs.ipc.utils.MyUtils;

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        String processName = MyUtils.getProcessName(getApplicationContext(), Process.myPid());
        Log.d(TAG, "application start, process name:" + processName);

        new Thread(new Runnable() {

            @Override
            public void run() {
                doWorkInBackground();
            }
        }).start();
    }

    private void doWorkInBackground() {
        // init binder pool
//        BinderPool.getInsance(getApplicationContext());
    }
}
