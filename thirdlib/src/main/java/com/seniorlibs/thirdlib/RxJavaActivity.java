package com.seniorlibs.thirdlib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IntDef;
import android.util.Log;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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

    private Disposable mDisposable;
    private Disposable mZipDisposable;
    private @SPLASH_AD_STATUS int mSplashAdStatus = SPLASH_AD_STATUS.INIT;

    // 0/1/2/3 --> 初始化/获取广告成功/无广告/超时/
    @IntDef({SPLASH_AD_STATUS.INIT, SPLASH_AD_STATUS.ON_AD_PRESENT,SPLASH_AD_STATUS.ON_NO_AD,SPLASH_AD_STATUS.TIME_OUT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SPLASH_AD_STATUS {
        int INIT = 0;
        int ON_AD_PRESENT = 1;
        int ON_NO_AD = 2;
        int TIME_OUT = 3;
    }

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
//        showSplashGdtAd;
        rxJavaZip();
    }

    /**
     * 测试rxJava
     */
    private void rxJava() {
        Log.e(TAG, "subscribe + rxJava:");

        // onNext()回调后，还有调用onError或onComplete（只会调用一个）
        mDisposable = Observable.timer(3000, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e(TAG, "subscribe + aLong:" + aLong);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
//                        if (mSplashAdStatus == SPLASH_AD_STATUS.INIT) {
//                            mSplashAdStatus = SPLASH_AD_STATUS.TIME_OUT;
//                            emitter.onNext(false);
//                        }

                        Log.e(TAG, "subscribe + throwable:" + throwable.toString());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
//                        if (mSplashAdStatus == SPLASH_AD_STATUS.INIT) {
//                            mSplashAdStatus = SPLASH_AD_STATUS.TIME_OUT;
//                            emitter.onNext(false);
//                        }
                        Log.e(TAG, "subscribe + Action");
                    }
                });

        // 3000以内disposable.dispose()取消后，不会再回调Consumer-accept和Consumer-Action
        // 3000以外disposable.dispose()取消后，会再回调Consumer-accept和Consumer-Action
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDisposable.dispose();
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
                Log.e(TAG, "subscribe + 2000 disposable.isDisposed():" + mDisposable.isDisposed());
            }
        }, 2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "subscribe + 2900 disposable.isDisposed():" + mDisposable.isDisposed());
            }
        }, 2900);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "subscribe + 2999 disposable.isDisposed():" + mDisposable.isDisposed());
            }
        }, 2999);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "subscribe + 3000 disposable.isDisposed():" + mDisposable.isDisposed());
            }
        }, 3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "subscribe + 3001 disposable.isDisposed():" + mDisposable.isDisposed());
            }
        }, 3001);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "subscribe + 4000 disposable.isDisposed():" + mDisposable.isDisposed());
            }
        }, 4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "subscribe + 6000 disposable.isDisposed():" + mDisposable.isDisposed());
            }
        }, 6000);
    }

    /**
     * 模拟请求SplashGdtAd
     */
    public class showSplashGdtAd {

        public void onADDismissed() {
//            // mCountdown默认+1（mCountdown > 1 && 未点击 == "跳过"，因为点击也会回调onADDismissed()）
//            if (mCountdown > 1 && !mOnGdtClicked) {
//                SensorsStatUtils.adEvent(adEntity.getPid(), adEntity.getAdId(), adEntity.getAdSource(), SensorsStat.ACTION_AD_EVENT_AD_EVENT_TYPE_SKIP);
//            }
//            if (assertActivityDestroyed()) {
//                return;
//            }
            if (mSplashAdStatus == SPLASH_AD_STATUS.TIME_OUT) {
                return;
            }

        }

        public void onNoAD(int adError) {
//            if (adError != null) {
//                AdUtils.onNoGdtAdReport(adEntity, adError);
//            }
//            if (assertActivityDestroyed()) {
//                return;
//            }
            if (mSplashAdStatus == SPLASH_AD_STATUS.TIME_OUT) {
                return;
            }

            mSplashAdStatus = SPLASH_AD_STATUS.ON_AD_PRESENT;

            if (mDisposable != null && !mDisposable.isDisposed()) {
                mDisposable.dispose();
            }
//            emitter.onNext(false);
        }

        public void onADPresent() {
//            if (assertActivityDestroyed()) {
//                return;
//            }
            if (mSplashAdStatus == SPLASH_AD_STATUS.TIME_OUT) {
                return;
            }

            mSplashAdStatus = SPLASH_AD_STATUS.ON_AD_PRESENT;

            if (mDisposable != null && !mDisposable.isDisposed()) {
                // 取消Disposable后，不会再回调
                mDisposable.dispose();
            }
            if (mZipDisposable != null && !mZipDisposable.isDisposed()) {
                // 取消ZipDisposable后，不会再回调，不在ZipDisposable的回调中处理开屏请求成功逻辑（不处理）
                mZipDisposable.dispose();
            }

            // 展示开屏广告
            // setAdGuideEnable(mRlSplashLogo, mIvLogo, mIvAdGuide);
        }

        public void onADClicked() {

        }

        public void onADTick(long millisUntilFinished) {
//            if (assertActivityDestroyed()) {
//                return;
//            }
            if (mSplashAdStatus == SPLASH_AD_STATUS.TIME_OUT) {
                return;
            }

        }

        public void onADExposure() {
//            AdUtils.onThirdShowAd(adEntity);
//            if (assertActivityDestroyed()) {
//                return;
//            }

        }
    }


    /**
     * 测试rxJavaZip
     */
    private void rxJavaZip() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(final ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "被观察者1发送了事件1");
                emitter.onNext(1);
            }
        }).subscribeOn(Schedulers.io()); // 设置被观察者1在工作线程1中工作

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "被观察者2发送了事件A");
                emitter.onNext("A");
            }
        }).subscribeOn(Schedulers.newThread());// 设置被观察者2在工作线程2中工作

        mZipDisposable = Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String string) throws Exception {
                Log.d(TAG, "zip apply:" + integer + string);
                return integer + string;
            }
        }).delay(3000, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "合并后结果 next:" + s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "合并后结果 Throwable:" + throwable.toString());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG, "合并后结果 Action:");
                    }
                });

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // 取消ZipDisposable后，不会再回调
//                mZipDisposable.dispose();
//            }
//        }, 2000);
    }
}