package com.seniorlibs.binder;

import android.app.Application;
import android.os.Process;
import android.util.Log;

import com.seniorlibs.binder.utils.MyUtils;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        String processName = MyUtils.getProcessName(getApplicationContext(), Process.myPid());

        Log.d(MyUtils.TAG, "application start, process name:" + processName);

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
