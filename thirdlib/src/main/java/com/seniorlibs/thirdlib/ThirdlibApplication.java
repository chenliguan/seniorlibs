package com.seniorlibs.thirdlib;

import com.seniorlibs.baselib.BaseApplication;
import com.seniorlibs.baselib.utils.LogUtils;
import com.tencent.mmkv.MMKV;

import java.io.File;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/1/3
 * Mender:
 * Modify:
 * Description: thirdlib 的 Application
 */
public class ThirdlibApplication extends BaseApplication {

    private static final String TAG = "ThirdlibApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {
        // EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus().register(this);

        // 初始化MMKV——基于 mmap 的高性能通用 key-value 组件
        // https://github.com/Tencent/MMKV/blob/master/readme_cn.md

        File file = android.os.Environment.getExternalStorageDirectory();
        String path = file.getAbsoluteFile() + File.separator + "mmkv";
        String root = MMKV.initialize(path);
        LogUtils.d("testMmkv", "MMKV + root：" + root);
    }
}
