package com.seniorlibs.thirdlib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2019/5/3
 * Mender:
 * Modify:
 * Description: 后台任务恢复启动闪屏页，区别于SplashActivity页面的神策统计
 */
public class RxJavaActivity extends Activity {

    private static final String TAG = " RxJavaActivity";

    public static void actionStart(Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, RxJavaActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        rxJava();
    }

    /**
     * 测试rxJava
     */
    private void rxJava() {
        Log.e(TAG, "subscribe + rxJava:");

        final Disposable disposable = Observable.timer(3000,  TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e(TAG, "subscribe + aLong:" + aLong);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "subscribe + throwable:" + throwable.toString());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "subscribe + Action");
                    }
                });

        // 3000以内disposable.dispose()取消后，不会再回调Consumer-accept和Consumer-Action
        // 3000以外disposable.dispose()取消后，会再回调Consumer-accept和Consumer-Action
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                disposable.dispose();
            }
        }, 2000);

//        RxJavaActivity: subscribe + rxJava:
//        RxJavaActivity: subscribe + 2000 disposable.isDisposed():false
//        RxJavaActivity: subscribe + 2900 disposable.isDisposed():false
//        RxJavaActivity: subscribe + aLong:0
//        RxJavaActivity: subscribe + Action
//        RxJavaActivity: subscribe + 2999 disposable.isDisposed():true
//        RxJavaActivity: subscribe + 3000 disposable.isDisposed():true
//        RxJavaActivity: subscribe + 3001 disposable.isDisposed():true
//        RxJavaActivity: subscribe + 4000 disposable.isDisposed():true
//        RxJavaActivity: subscribe + 6000 disposable.isDisposed():true

//        RxJavaActivity: subscribe + rxJava:
//        RxJavaActivity: subscribe + 2000 disposable.isDisposed():false
//        RxJavaActivity: subscribe + 2900 disposable.isDisposed():false
//        RxJavaActivity: subscribe + 2999 disposable.isDisposed():true
//        RxJavaActivity: subscribe + 3000 disposable.isDisposed():true
//        RxJavaActivity: subscribe + 3001 disposable.isDisposed():true
//        RxJavaActivity: subscribe + 4000 disposable.isDisposed():true
//        RxJavaActivity: subscribe + 6000 disposable.isDisposed():true

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "subscribe + 2000 disposable.isDisposed():" + disposable.isDisposed());
            }
        }, 2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "subscribe + 2900 disposable.isDisposed():" + disposable.isDisposed());
            }
        }, 2900);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "subscribe + 2999 disposable.isDisposed():" + disposable.isDisposed());
            }
        }, 2999);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "subscribe + 3000 disposable.isDisposed():" + disposable.isDisposed());
            }
        }, 3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "subscribe + 3001 disposable.isDisposed():" + disposable.isDisposed());
            }
        }, 3001);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "subscribe + 4000 disposable.isDisposed():" + disposable.isDisposed());
            }
        }, 4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "subscribe + 6000 disposable.isDisposed():" + disposable.isDisposed());
            }
        }, 6000);
    }
}
