package com.seniorlibs.thirdlib;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
//        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus().register(this);
    }
}
