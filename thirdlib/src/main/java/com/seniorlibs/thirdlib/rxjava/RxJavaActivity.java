package com.seniorlibs.thirdlib.rxjava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.seniorlibs.thirdlib.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2019/5/3
 * Mender:
 * Modify: https://www.jianshu.com/p/cd984dd5aae8
 * Description: 后台任务恢复启动闪屏页，区别于SplashActivity页面的神策统计
 */
public class RxJavaActivity extends AppCompatActivity {

    private static final String TAG = " RxJavaActivity";

    private Disposable mDisposable;
    private Disposable mZipDisposable;
    private @SPLASH_AD_STATUS
    int mSplashAdStatus = SPLASH_AD_STATUS.INIT;

    // 0/1/2/3 --> 初始化/获取广告成功/无广告/超时/
    @IntDef({SPLASH_AD_STATUS.INIT, SPLASH_AD_STATUS.ON_AD_PRESENT, SPLASH_AD_STATUS.ON_NO_AD, SPLASH_AD_STATUS.TIME_OUT})
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
    }


    /**
     * 测试rxJava
     *
     * @param view
     */
    public void textRxJava(View view) {
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
     *
     * @param view
     */
    public void textRxJavaZip(View view) {
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

    /**
     * empty、never、error
     *
     * @param view
     */
    public void textEmptyNeverError(View view) {
        //===========onSubscribe===========
        //===========onComplete===========
        Observable.empty()
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        System.out.println("===========doOnNext===========");
                    }
                })
                .flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Exception {
                        System.out.println("===========flatMap===========：" + o);
                        return Observable.just(10);
                    }
                })
                .onErrorResumeNext(new Observable<Object>() {
                    @Override
                    protected void subscribeActual(Observer<? super Object> observer) {
                        System.out.println("===========onErrorResumeNext===========");
                    }
                })
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("===========onSubscribe===========");
                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("==========onNext============：" + o);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("===========onError===========：" + e);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("===========onComplete===========");
                    }
                });

        // 只有onComplete()才会打印，这里没任何打印
        Observable.empty()
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        System.out.println("===========doOnNext2===========");
                    }
                })
                .flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Exception {
                        System.out.println("===========flatMap2===========：" + o);
                        return Observable.just(10);
                    }
                })
                .onErrorResumeNext(new Observable<Object>() {
                    @Override
                    protected void subscribeActual(Observer<? super Object> observer) {
                        System.out.println("===========onErrorResumeNext2===========");
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        System.out.println("===========Consumer2===========：" + o);
                    }
                });
    }

    /**
     * FlatMap
     *
     * @param view
     */
    public void textFlatMap(View view) {
        // 采用RxJava基于事件流的链式操作
        Observable.create(new ObservableOnSubscribe<Integer>() {
            // 1. 被观察者发送事件 = 参数为整型 = 1、2、3
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }

            // 2. 使用Map变换操作符中的Function函数对被观察者发送的事件进行统一变换：整型变换成字符串类型
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "使用 Map变换操作符 将事件" + integer + "的参数从 整型" + integer + " 变换成 字符串类型" + integer;
            }

        }).subscribe(new Consumer<String>() {
            // 3. 观察者接收事件时，是接收到变换后的事件 = 字符串类型
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });

        Log.d(TAG, "-----------------------------------------------------------");

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }

            // 采用flatMap（）变换操作符
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("我是事件 " + integer + "拆分后的子事件" + i);
                    // 通过flatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送三个String事件
                    // 最终合并，再发送给被观察者
                }
                return Observable.fromIterable(list);
//                return Observable.just("");
            }

        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });
    }

    /**
     * 重试
     *
     * @param view
     */
    public void textRetry(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.e(TAG, "create + subscribe: ");

                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
                e.onNext(3);
            }
        })
                // 使Observable延迟一段时间再发送事件
                .delay(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        Log.e(TAG, "延迟一段时间: " + integer);

                        return Observable.just(integer);
                    }
                })
                // 拦截错误后，判断是否需要重新发送请求
                .retry(5, new Predicate<Throwable>() {
                    @Override
                    public boolean test(@NonNull Throwable throwable) throws Exception {
                        // 捕获异常
                        Log.e(TAG, "retry错误: " + throwable.toString());

                        //返回false = 不重新重新发送数据 & 调用观察者的onError结束
                        //返回true = 重新发送请求（若持续遇到错误，就持续重新发送）
                        return true;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
    }

    /**
     * 选择一个执行
     *
     * @param view
     */
    public void textTake(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // 1. 发送5个事件
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
            }

            // 采用take()变换操作符，指定了观察者只能接收2个事件
        }).take(1)
                .subscribe(new Observer<Integer>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "过滤后得到的事件是：" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
    }

    private int mNum = 0;

    /**
     * 并行执行
     *
     * @param view
     */
    public void textMerge(View view) {
        // merge()：组合多个被观察者（≤4个）一起发送数据
        // 注：串行执行
        Observable.merge(
                Observable.intervalRange(0, 1, 0, 1, TimeUnit.MILLISECONDS), // 从0开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                Observable.intervalRange(10, 1, 0, 1, TimeUnit.MILLISECONDS)) // 从10开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
    }

    /**
     * concat()：组合多个被观察者一起发送数据（可＞4个）
     * 注：串行执行
     *
     * @param view
     */
    public void textConcat(View view) {
        Observable.concat(
                // 从0开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                Observable.intervalRange(1, 8, 0, 1, TimeUnit.SECONDS),
                // 从2开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                Observable.intervalRange(10, 8, 0, 1, TimeUnit.SECONDS))

                .flatMap(new Function<Long, ObservableSource<Long>>() {
                    @Override
                    public ObservableSource<Long> apply(final Long aLong) throws Exception {
                        Log.e(TAG, "flatMap 变换操作符 : " + aLong + " ---> " + (4000 + aLong));

                        return Observable.create(new ObservableOnSubscribe<Long>() {
                            @Override
                            public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                                Log.e(TAG, "flatMap onError ：" + (4000 + aLong));
                                emitter.onError(new Throwable("onError"));
                            }
                        })
                                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Long>>() {
                                    @Override
                                    public ObservableSource<? extends Long> apply(Throwable throwable) throws Exception {
                                        mNum++;
                                        Log.e(TAG, "onErrorResumeNext Function：处理数据错误 + mNum：" + mNum);
//                                        return Observable.error(new Exception("MESSAGE_ON_ERROR"));

                                        if (mNum <= 2) {
                                            return Observable.just((4000 + aLong));
                                        } else {
                                            return Observable.empty();
                                        }
                                    }
                                });
                    }
                })
                .timeout(3, TimeUnit.SECONDS)
                // 遇到错误Throwable时，发送新的Observable
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Long>>() {
                    @Override
                    public ObservableSource<? extends Long> apply(Throwable throwable) throws Exception {
                        Log.e(TAG, "onErrorResumeNext Function：处理超时错误");
//                        return Observable.error(new Exception("MESSAGE_ON_ERROR"));
                        return Observable.empty();
                    }
                })
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "doOnNext : " + aLong);
                    }
                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long value) {
                        Log.e(TAG, "onNext接收到了事件：" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "对Error事件作出响应：" + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "对Complete事件作出响应");
                    }
                });

        /**  1-2秒：just()超时不生效；3-5秒：empty()超时生效
         com.seniorlibs.thirdlib E/ RxJavaActivity: flatMap 变换操作符 : 1 ---> 4001
         com.seniorlibs.thirdlib E/ RxJavaActivity: flatMap onError ：4001
         com.seniorlibs.thirdlib E/ RxJavaActivity: onErrorResumeNext Function：处理数据错误 + mNum：1
         com.seniorlibs.thirdlib D/ RxJavaActivity: doOnNext : 4001
         com.seniorlibs.thirdlib E/ RxJavaActivity: onNext接收到了事件：4001
         com.seniorlibs.thirdlib E/ RxJavaActivity: flatMap 变换操作符 : 2 ---> 4002
         com.seniorlibs.thirdlib E/ RxJavaActivity: flatMap onError ：4002
         com.seniorlibs.thirdlib E/ RxJavaActivity: onErrorResumeNext Function：处理数据错误 + mNum：2
         com.seniorlibs.thirdlib D/ RxJavaActivity: doOnNext : 4002
         com.seniorlibs.thirdlib E/ RxJavaActivity: onNext接收到了事件：4002
         com.seniorlibs.thirdlib E/ RxJavaActivity: flatMap 变换操作符 : 3 ---> 4003
         com.seniorlibs.thirdlib E/ RxJavaActivity: flatMap onError ：4003
         com.seniorlibs.thirdlib E/ RxJavaActivity: onErrorResumeNext Function：处理数据错误 + mNum：3
         com.seniorlibs.thirdlib E/ RxJavaActivity: flatMap 变换操作符 : 4 ---> 4004
         com.seniorlibs.thirdlib E/ RxJavaActivity: flatMap onError ：4004
         com.seniorlibs.thirdlib E/ RxJavaActivity: onErrorResumeNext Function：处理数据错误 + mNum：4
         com.seniorlibs.thirdlib E/ RxJavaActivity: flatMap 变换操作符 : 5 ---> 4005
         com.seniorlibs.thirdlib E/ RxJavaActivity: flatMap onError ：4005
         com.seniorlibs.thirdlib E/ RxJavaActivity: onErrorResumeNext Function：处理数据错误 + mNum：5
         com.seniorlibs.thirdlib E/ RxJavaActivity: onErrorResumeNext Function：处理超时错误
         com.seniorlibs.thirdlib E/ RxJavaActivity: 对Complete事件作出响应
         */
    }
}