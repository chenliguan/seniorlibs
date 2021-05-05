package com.seniorlibs.baselib;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.seniorlibs.baselib.hook.ImageHook;
import com.seniorlibs.baselib.utils.DebugUtils;
import com.seniorlibs.baselib.utils.LogUtils;

import java.io.File;

import de.robv.android.xposed.DexposedBridge;
import de.robv.android.xposed.XC_MethodHook;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/1/3
 * Mender:
 * Modify:
 * Description: BaseApplication
 */
public class BaseApplication extends Application {

    public static final String TAG = "BaseApplication";

    private static final int MEMORY_SIZE = 5 * 1024 * 1024;
    private static final int DISK_SIZE = 20 * 1024 * 1024;
    private static Context mContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        mContext = base;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    public static Context getAppContext() {
        return mContext;
    }

    private void init() {
        // 初始化 Image-Loader
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCache(new LruMemoryCache(MEMORY_SIZE))
                .diskCache(new UnlimitedDiscCache(new File(getCacheDir(),"caches")))
                .diskCacheSize(DISK_SIZE)
                .defaultDisplayImageOptions(options)
                .build();

        ImageLoader.getInstance().init(configuration);

        if (!DebugUtils.isDebugMode) {
            // 在Application设置RxJava全局异常监控
            RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    // 错误上报：throwable.getCause()才能获取到真实的错误，如果是严重异常直接抛出
//                    report(throwable instanceof OnErrorNotImplementedException ? throwable.getCause() : throwable);
                }
            });
        }

        // 钩住所有构造函数
        DexposedBridge.hookAllConstructors(ImageView.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                // 查找挂钩方法
                DexposedBridge.findAndHookMethod(ImageView.class, "setImageBitmap", Bitmap.class, new ImageHook());
                DexposedBridge.findAndHookMethod(ImageView.class, "setImageDrawable", Bitmap.class, new ImageHook());
            }
        });
    }
}
