package com.seniorlibs.view;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.seniorlibs.baselib.BaseApplication;

public class ViewApplication extends BaseApplication {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getContext(){
        return sContext;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this);
    }
}
