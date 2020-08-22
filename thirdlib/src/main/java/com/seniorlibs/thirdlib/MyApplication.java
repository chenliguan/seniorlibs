package com.seniorlibs.thirdlib;

import android.app.Application;

import com.seniorlibs.baselib.utils.LogUtils;
import com.tencent.mmkv.MMKV;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
//        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus().register(this);

        // 初始化MMKV——基于 mmap 的高性能通用 key-value 组件
        // https://github.com/Tencent/MMKV/blob/master/readme_cn.md

        File file = android.os.Environment.getExternalStorageDirectory();
        String path = file.getAbsoluteFile() + File.separator + "mmkv";
        String root = MMKV.initialize(path);
        LogUtils.d("testMmkv", "MMKV + root：" + root);
    }
}
