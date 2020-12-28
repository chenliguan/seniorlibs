package com.seniorlibs.thirdlib.rxjava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.seniorlibs.baselib.utils.LogUtils;
import com.seniorlibs.thirdlib.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/3/15
 * Mender:
 * Modify: https://www.jianshu.com/p/b0c3669affdb 功能性操作符
 * https://www.jianshu.com/p/5225b2baaecd 线程控制（切换 / 调度 ）
 * Description: 功能性操作符，辅助被观察者（Observable） 在发送事件时实现一些功能性需求，如：错误处理、线程调度等等
 */
public class RxFunctionActivity extends AppCompatActivity {

    private static final String TAG = " RxFunctionActivity";

    public static void actionStart(Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, RxFunctionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_function);
    }

    /**
     * 连接被观察者 & 观察者
     * <p>
     * 场景：即使得被观察者 & 观察者 形成订阅关系
     *
     * @param view
     */
    public void subscribe(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            // 1. 创建被观察者 & 生产事件
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            // 2. 通过通过订阅（subscribe）连接观察者和被观察者
            // 3. 创建观察者 & 定义响应事件的行为
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d(TAG, "开始采用subscribe连接");
            }

            @Override
            public void onNext(Integer value) {
                LogUtils.d(TAG, "对Next事件" + value + "作出响应");
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                LogUtils.d(TAG, "对Complete事件作出响应");
            }
        });
    }

    /**
     * 线程切换
     * <p>
     * 场景：即使得被观察者 & 观察者 形成订阅关系
     *
     * @param view
     */
    public void subscribeOnObserveOn(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                LogUtils.d(TAG, "1.1 第一次指定被观察者Observable生产事件的线程： " + Thread.currentThread().getName());

                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        })
                // 1.1 第一次指定被观察者Observable生产事件的线程 = 新线程（只有第一次指定有效，其余的指定线程无效）
                .subscribeOn(Schedulers.newThread())
                // 1.2 第二次指定被观察者Observable线程 = 主线程（无效）
                .subscribeOn(Schedulers.newThread())

                // 2.1 第一次指定观察者Observer接收 & 响应事件的线程 = 主线程（有效）
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d(TAG, "2.1 第一次观察者Observer的线程是： " + Thread.currentThread().getName());
                    }
                })
                // 2.2 第二次指定观察者Observer接收 & 响应事件的线程 = 新线程（有效）
                .observeOn(Schedulers.newThread())

                // 3.1 最后再通过订阅（subscribe）连接观察者Observer和被观察者Observable
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.d(TAG, "对Next事件" + value + "作出响应");
                        LogUtils.d(TAG, "2.2 第二次观察者Observer的线程是： " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "对Complete事件作出响应");
                    }
                });

        /**
         * 03-22 14:25:15.435 21356-21356/com.seniorlibs.thirdlib D/ RxFunctionActivity: 开始采用subscribe连接
         * 03-22 14:25:15.438 21356-21523/com.seniorlibs.thirdlib D/ RxFunctionActivity: 1.1 第一次指定被观察者Observable生产事件的线程： RxNewThreadScheduler-2
         * 03-22 14:25:15.453 21356-21356/com.seniorlibs.thirdlib D/ RxFunctionActivity: 2.1 第一次观察者Observer的线程是： main
         * 03-22 14:25:15.454 21356-21356/com.seniorlibs.thirdlib D/ RxFunctionActivity: 2.1 第一次观察者Observer的线程是： main
         * 03-22 14:25:15.454 21356-21356/com.seniorlibs.thirdlib D/ RxFunctionActivity: 2.1 第一次观察者Observer的线程是： main
         * 03-22 14:25:15.455 21356-21524/com.seniorlibs.thirdlib D/ RxFunctionActivity: 对Next事件1作出响应
         * 03-22 14:25:15.455 21356-21524/com.seniorlibs.thirdlib D/ RxFunctionActivity: 2.2 第二次观察者Observer的线程是： RxNewThreadScheduler-3
         * 03-22 14:25:15.455 21356-21524/com.seniorlibs.thirdlib D/ RxFunctionActivity: 对Next事件2作出响应
         * 03-22 14:25:15.455 21356-21524/com.seniorlibs.thirdlib D/ RxFunctionActivity: 2.2 第二次观察者Observer的线程是： RxNewThreadScheduler-3
         * 03-22 14:25:15.455 21356-21524/com.seniorlibs.thirdlib D/ RxFunctionActivity: 对Next事件3作出响应
         * 03-22 14:25:15.455 21356-21524/com.seniorlibs.thirdlib D/ RxFunctionActivity: 2.2 第二次观察者Observer的线程是： RxNewThreadScheduler-3
         * 03-22 14:25:15.455 21356-21524/com.seniorlibs.thirdlib D/ RxFunctionActivity: 对Complete事件作出响应
         */
    }

    /**
     * 使得被观察者延迟一段时间再发送事件
     * <p>
     *
     * @param view
     */
    public void delay(View view) {
        Observable.just(1, 2, 3)
                // 延迟3s再发送，由于使用类似，所以此处不作全部展示
                .delay(3, TimeUnit.SECONDS)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "对Complete事件作出响应");
                    }
                });

        /**
         * 03-22 14:34:51.217 22029-22111/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 14:34:51.217 22029-22111/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 14:34:51.217 22029-22111/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件3
         * 03-22 14:34:51.217 22029-22111/com.seniorlibs.thirdlib D/ RxFunctionActivity: 对Complete事件作出响应
         */
    }

    /**
     * 使得被观察者延迟一段时间再发送事件
     * <p>
     *
     * @param view
     */
    public void delayWithFunction(View view) {
        Observable.just(1, 2, 3)
                // 使Observable延迟一段时间再发送新的Observable
                .delay(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        LogUtils.d(TAG, "延迟一段时间: " + integer);

                        return Observable.just(100);
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.d(TAG, "接收到了事件：" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "对Complete事件作出响应");
                    }
                });

        /**
         * 03-22 14:41:18.273 23039-23039/com.seniorlibs.thirdlib D/ RxFunctionActivity: 延迟一段时间: 1
         * 03-22 14:41:18.279 23039-23039/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件：1
         * 03-22 14:41:18.279 23039-23039/com.seniorlibs.thirdlib D/ RxFunctionActivity: 延迟一段时间: 2
         * 03-22 14:41:18.279 23039-23039/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件：2
         * 03-22 14:41:18.279 23039-23039/com.seniorlibs.thirdlib D/ RxFunctionActivity: 延迟一段时间: 3
         * 03-22 14:41:18.279 23039-23039/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件：3
         * 03-22 14:41:18.280 23039-23039/com.seniorlibs.thirdlib D/ RxFunctionActivity: 对Complete事件作出响应
         */
    }

    /**
     * do 在事件的生命周期中操作
     * <p>
     * 场景：在事件发送 & 接收的整个生命周期过程中进行操作
     *
     * @param view
     */
    public void doOn(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onError(new Throwable("发生错误了"));
            }
        })
                // 1. 当Observable每发送1次数据事件就会调用1次
                .doOnEach(new Consumer<Notification<Integer>>() {
                    @Override
                    public void accept(Notification<Integer> integerNotification) throws Exception {
                        LogUtils.d(TAG, "doOnEach: " + integerNotification.getValue());
                    }
                })
                // 2. 执行Next事件前调用
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d(TAG, "doOnNext: " + integer);
                    }
                })
                // 3. 执行Next事件后调用
                .doAfterNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d(TAG, "doAfterNext: " + integer);
                    }
                })
                // 4. Observable正常发送事件完毕后调用
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.e(TAG, "doOnComplete: ");
                    }
                })
                // 5. Observable发送错误事件时调用
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.d(TAG, "doOnError: " + throwable.getMessage());
                    }
                })
                // 6. 观察者订阅时调用
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        LogUtils.d(TAG, "doOnSubscribe: ");
                    }
                })
                // 7. Observable发送事件完毕后调用，无论正常发送完毕 / 异常终止
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.e(TAG, "doAfterTerminate: ");
                    }
                })
                // 8. 最后执行
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.e(TAG, "doFinally: ");
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.d(TAG, "onNext：接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "onError：对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "对Complete事件作出响应");
                    }
                });

        /**
         * 03-22 14:49:33.003 24317-24317/com.seniorlibs.thirdlib D/ RxFunctionActivity: doOnSubscribe:
         * 03-22 14:49:33.004 24317-24317/com.seniorlibs.thirdlib D/ RxFunctionActivity: doOnEach: 1
         * 03-22 14:49:33.004 24317-24317/com.seniorlibs.thirdlib D/ RxFunctionActivity: doOnNext: 1
         * 03-22 14:49:33.004 24317-24317/com.seniorlibs.thirdlib D/ RxFunctionActivity: onNext：接收到了事件1
         * 03-22 14:49:33.005 24317-24317/com.seniorlibs.thirdlib D/ RxFunctionActivity: doAfterNext: 1
         * 03-22 14:49:33.005 24317-24317/com.seniorlibs.thirdlib D/ RxFunctionActivity: doOnEach: 2
         * 03-22 14:49:33.005 24317-24317/com.seniorlibs.thirdlib D/ RxFunctionActivity: doOnNext: 2
         * 03-22 14:49:33.005 24317-24317/com.seniorlibs.thirdlib D/ RxFunctionActivity: onNext：接收到了事件2
         * 03-22 14:49:33.005 24317-24317/com.seniorlibs.thirdlib D/ RxFunctionActivity: doAfterNext: 2
         * 03-22 14:49:33.005 24317-24317/com.seniorlibs.thirdlib D/ RxFunctionActivity: doOnEach: 3
         * 03-22 14:49:33.005 24317-24317/com.seniorlibs.thirdlib D/ RxFunctionActivity: doOnNext: 3
         * 03-22 14:49:33.005 24317-24317/com.seniorlibs.thirdlib D/ RxFunctionActivity: onNext：接收到了事件3
         * 03-22 14:49:33.005 24317-24317/com.seniorlibs.thirdlib D/ RxFunctionActivity: doAfterNext: 3
         * 03-22 14:49:33.005 24317-24317/com.seniorlibs.thirdlib D/ RxFunctionActivity: doOnEach: null
         * 03-22 14:49:33.005 24317-24317/com.seniorlibs.thirdlib D/ RxFunctionActivity: doOnError: 发生错误了
         * 03-22 14:49:33.005 24317-24317/com.seniorlibs.thirdlib D/ RxFunctionActivity: onError：对Error事件作出响应
         * 03-22 14:49:33.006 24317-24317/com.seniorlibs.thirdlib E/ RxFunctionActivity: doFinally:
         * 03-22 14:49:33.006 24317-24317/com.seniorlibs.thirdlib E/ RxFunctionActivity: doAfterTerminate:
         */
    }

    /**
     * 遇到错误时，发送1个特殊事件 & 正常终止，可捕获在它之前发生的异常
     * <p>
     * 场景：发送事件过程中，遇到错误时的处理机制
     *
     * @param view
     */
    public void onErrorReturn(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Throwable("发生错误了"));
            }
        })
                .onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(@NonNull Throwable throwable) throws Exception {
                        // 捕捉错误异常
                        LogUtils.e(TAG, "在onErrorReturn处理了错误: " + throwable.toString());

                        // 发生错误事件后，发送一个"666"事件，最终正常结束
                        return 666;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "对Complete事件作出响应");
                    }
                });

        /**
         * 03-22 15:01:27.956 24847-24847/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 15:01:27.956 24847-24847/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 15:01:27.956 24847-24847/com.seniorlibs.thirdlib E/ RxFunctionActivity: 在onErrorReturn处理了错误: java.lang.Throwable: 发生错误了
         * 03-22 15:01:27.956 24847-24847/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件666
         * 03-22 15:01:27.957 24847-24847/com.seniorlibs.thirdlib D/ RxFunctionActivity: 对Complete事件作出响应
         */
    }

    /**
     * 遇到错误时，发送1个新的Observable
     * <p>
     * 场景：onErrorResumeNext()拦截的错误 = Throwable；若需拦截Exception请用onExceptionResumeNext()
     *     若onErrorResumeNext()拦截的错误 = Exception，则会将错误传递给观察者的onError方法
     *
     * @param view
     */
    public void onErrorResumeNext(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Throwable("发生错误了"));
            }
        })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
                    @Override
                    public ObservableSource<? extends Integer> apply(@NonNull Throwable throwable) throws Exception {
                        // 1. 捕捉错误异常
                        LogUtils.e(TAG, "在onErrorResumeNext处理了错误: " + throwable.toString());
                        // 2. 发生错误事件后，发送一个新的被观察者 & 发送事件序列
                        return Observable.just(11, 22);

                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "对Complete事件作出响应");
                    }
                });

        /**
         * 03-22 15:01:45.754 24847-24847/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 15:01:45.754 24847-24847/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 15:01:45.754 24847-24847/com.seniorlibs.thirdlib E/ RxFunctionActivity: 在onErrorReturn处理了错误: java.lang.Throwable: 发生错误了
         * 03-22 15:01:45.757 24847-24847/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件11
         * 03-22 15:01:45.757 24847-24847/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件22
         * 03-22 15:01:45.757 24847-24847/com.seniorlibs.thirdlib D/ RxFunctionActivity: 对Complete事件作出响应
         */
    }

    /**
     * 遇到错误时，发送1个旧的Observable
     * <p>
     * 场景：若onErrorResumeNext()拦截的错误 = Exception；若需拦截Throwable请用onErrorResumeNext()
     * 若onExceptionResumeNext()拦截的错误 = Throwable，则会将错误传递给观察者的onError方法
     *
     * @param view
     */
    public void onExceptionResumeNext(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
            }
        })
                .onExceptionResumeNext(new Observable<Integer>() {
                    @Override
                    protected void subscribeActual(Observer<? super Integer> observer) {
                        // 1. 捕捉错误异常
                        LogUtils.e(TAG, "在onExceptionResumeNext了捕捉错误异常: ");
                        // 2. 发生错误事件后，发送一个旧的被观察者 & 发送事件序列
                        observer.onNext(11);
                        observer.onNext(22);
                        observer.onComplete();
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "对Complete事件作出响应");
                    }
                });

        /**
         * 03-22 15:08:10.888 25385-25385/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 15:08:10.888 25385-25385/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 15:08:10.888 25385-25385/com.seniorlibs.thirdlib E/ RxFunctionActivity: 在onExceptionResumeNext了捕捉错误异常:
         * 03-22 15:08:10.888 25385-25385/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件11
         * 03-22 15:08:10.888 25385-25385/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件22
         * 03-22 15:08:10.888 25385-25385/com.seniorlibs.thirdlib D/ RxFunctionActivity: 对Complete事件作出响应
         */
    }

    /**
     * 重试，即当出现错误时，让被观察者重新发射数据（若一直错误，则一直重新发送）
     * <p>
     * 场景：接收到 onError()时，重新订阅 & 发送事件；Throwable 和 Exception都可拦截
     *
     * @param view
     */
    public void retry(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
                e.onNext(3);
            }
        })
                // 遇到错误时，让被观察者重新发射数据（若一直错误，则一直重新发送）
                .retry()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "对Complete事件作出响应");
                    }
                });

        /**
         * 03-22 15:27:10.031 27584-27584/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 15:27:10.031 27584-27584/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 15:27:10.031 27584-27584/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 15:27:10.031 27584-27584/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 15:27:10.031 27584-27584/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 15:27:10.031 27584-27584/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 15:27:10.031 27584-27584/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 15:27:10.031 27584-27584/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 15:27:10.031 27584-27584/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 15:27:10.031 27584-27584/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 15:27:10.031 27584-27584/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * ......
         */
    }

    /**
     * 重试，即当出现错误时，让被观察者重新发射数据
     * 出现错误时，让被观察者重新发送数据（具备重试次数限制参数 = 重试次数）
     * <p>
     * 场景：接收到 onError()时，重新订阅 & 发送事件；Throwable 和 Exception都可拦截
     *
     * @param view
     */
    public void retryTime(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
                e.onNext(3);
            }
        })
                // 设置重试次数 = 3次
                .retry(3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "对Complete事件作出响应");
                    }
                });

        /**
         * 03-22 15:28:16.209 27709-27709/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 15:28:16.209 27709-27709/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 15:28:16.209 27709-27709/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 15:28:16.210 27709-27709/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 15:28:16.210 27709-27709/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 15:28:16.210 27709-27709/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 15:28:16.210 27709-27709/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 15:28:16.210 27709-27709/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 15:28:16.210 27709-27709/com.seniorlibs.thirdlib D/ RxFunctionActivity: 对Error事件作出响应
         */
    }

    /**
     * 重试，即当出现错误时，让被观察者重新发射数据
     * 出现错误后，判断是否需要重新发送数据（若需要重新发送& 持续遇到错误，则持续重试）。参数 = 判断逻辑
     * <p>
     * 场景：接收到 onError()时，重新订阅 & 发送事件；Throwable 和 Exception都可拦截
     *
     * @param view
     */
    public void retryPredicate(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
                e.onNext(3);
            }
        })
                // 拦截错误后，判断是否需要重新发送请求
                .retry(new Predicate<Throwable>() {
                    @Override
                    public boolean test(@NonNull Throwable throwable) throws Exception {
                        // 捕获异常
                        LogUtils.e(TAG, "retry错误: " + throwable.toString());

                        // 返回false：不重新发送，调用onError结束；返回true：重新发送请求
                        return true;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "对Complete事件作出响应");
                    }
                });

        /**
         * 03-22 15:31:52.164 27709-27709/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 15:31:52.164 27709-27709/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 15:31:52.164 27709-27709/com.seniorlibs.thirdlib E/ RxFunctionActivity: retry错误: java.lang.Exception: 发生错误了
         * 03-22 15:31:52.164 27709-27709/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 15:31:52.164 27709-27709/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 15:31:52.164 27709-27709/com.seniorlibs.thirdlib E/ RxFunctionActivity: retry错误: java.lang.Exception: 发生错误了
         * 03-22 15:31:52.164 27709-27709/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 15:31:52.165 27709-27709/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 15:31:52.165 27709-27709/com.seniorlibs.thirdlib E/ RxFunctionActivity: retry错误: java.lang.Exception: 发生错误了
         * ......
         */
    }

    /**
     * 重试，即当出现错误时，让被观察者重新发射数据
     * 出现错误后，判断是否需要重新发送数据（若需要重新发送 & 持续遇到错误，则持续重试）。参数 = 判断逻辑（传入当前重试次数 & 异常错误信息）
     * <p>
     * 场景：接收到 onError()时，重新订阅 & 发送事件；Throwable 和 Exception都可拦截
     *
     * @param view
     */
    public void retryBiPredicate(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
                e.onNext(3);
            }
        })

                // 拦截错误后，判断是否需要重新发送请求
                .retry(new BiPredicate<Integer, Throwable>() {
                    @Override
                    public boolean test(@NonNull Integer integer, @NonNull Throwable throwable) throws Exception {
                        // 捕获异常
                        LogUtils.e(TAG, "异常错误 =  " + throwable.toString());

                        // 获取当前重试次数
                        LogUtils.e(TAG, "当前重试次数 =  " + integer);

                        // 返回false：不重新发送，调用onError结束；返回true：重新发送请求
                        return true;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "对Complete事件作出响应");
                    }
                });

        /**
         * 03-22 15:33:06.241 27910-27910/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 15:33:06.241 27910-27910/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 15:33:06.241 27910-27910/com.seniorlibs.thirdlib E/ RxFunctionActivity: 异常错误 =  java.lang.Exception: 发生错误了
         * 03-22 15:33:06.241 27910-27910/com.seniorlibs.thirdlib E/ RxFunctionActivity: 当前重试次数 =  133887
         * 03-22 15:33:06.241 27910-27910/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 15:33:06.241 27910-27910/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 15:33:06.241 27910-27910/com.seniorlibs.thirdlib E/ RxFunctionActivity: 异常错误 =  java.lang.Exception: 发生错误了
         * 03-22 15:33:06.241 27910-27910/com.seniorlibs.thirdlib E/ RxFunctionActivity: 当前重试次数 =  133888
         * ......
         */
    }

    /**
     * 重试，即当出现错误时，让被观察者重新发射数据
     * 出现错误后，判断是否需要重新发送数据（具备重试次数限制）。参数 = 设置重试次数 & 判断逻辑
     * <p>
     * 场景：接收到 onError()时，重新订阅 & 发送事件；Throwable 和 Exception都可拦截
     *
     * @param view
     */
    public void retryTimePredicate(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
                e.onNext(3);
            }
        })
                // 拦截错误后，判断是否需要重新发送请求
                .retry(3, new Predicate<Throwable>() {
                    @Override
                    public boolean test(@NonNull Throwable throwable) throws Exception {
                        // 捕获异常
                        LogUtils.e(TAG, "retry错误: " + throwable.toString());

                        // 返回false：不重新发送，调用onError结束；返回true：重新发送请求
                        return true;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "对Complete事件作出响应");
                    }
                });

        /**
         * 03-22 15:34:49.368 28056-28056/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 15:34:49.369 28056-28056/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 15:34:49.369 28056-28056/com.seniorlibs.thirdlib E/ RxFunctionActivity: retry错误: java.lang.Exception: 发生错误了
         * 03-22 15:34:49.369 28056-28056/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 15:34:49.369 28056-28056/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 15:34:49.369 28056-28056/com.seniorlibs.thirdlib E/ RxFunctionActivity: retry错误: java.lang.Exception: 发生错误了
         * 03-22 15:34:49.369 28056-28056/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 15:34:49.369 28056-28056/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 15:34:49.369 28056-28056/com.seniorlibs.thirdlib E/ RxFunctionActivity: retry错误: java.lang.Exception: 发生错误了
         * 03-22 15:34:49.369 28056-28056/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 15:34:49.369 28056-28056/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 15:34:49.369 28056-28056/com.seniorlibs.thirdlib D/ RxFunctionActivity: 对Error事件作出响应
         */
    }


    /**
     * 重试，即当出现错误时，让被观察者重新发射数据
     * 出现错误后，判断是否需要重新发送数据（若需要重新发送 & 持续遇到错误，则持续重试）。参数 = 判断逻辑
     * <p>
     * 具体使用类似于retry（Predicate predicate），唯一区别：返回 true 则不重新发送数据事件
     * <p>
     * 场景：接收到 onError()时，重新订阅 & 发送事件；Throwable 和 Exception都可拦截
     *
     * @param view
     */
    public void retryUntil(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
                e.onNext(3);
                e.onComplete();
            }
        })
                // 拦截错误后，判断是否需要重新发送请求
                .retryUntil(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() throws Exception {
                        // 捕获异常
                        LogUtils.e(TAG, "repeatUntil错误: ");

                        // 返回false：重新发送；返回true：不重新发送请求，调用onError结束
                        return true;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "对Complete事件作出响应");
                    }
                });
        /**
         * 返回false：重新发送
         * 03-22 16:10:44.300 9523-9523/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 16:10:44.300 9523-9523/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 16:10:44.300 9523-9523/com.seniorlibs.thirdlib E/ RxFunctionActivity: repeatUntil错误:
         * 03-22 16:10:44.300 9523-9523/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 16:10:44.300 9523-9523/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 16:10:44.300 9523-9523/com.seniorlibs.thirdlib E/ RxFunctionActivity: repeatUntil错误:
         * 03-22 16:10:44.300 9523-9523/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 16:10:44.300 9523-9523/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 16:10:44.300 9523-9523/com.seniorlibs.thirdlib E/ RxFunctionActivity: repeatUntil错误:
         *
         * 返回true：不重新发送请求，调用onError结束
         * 03-22 16:11:37.528 9866-9866/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 16:11:37.528 9866-9866/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 16:11:37.528 9866-9866/com.seniorlibs.thirdlib E/ RxFunctionActivity: repeatUntil错误:
         * 03-22 16:11:37.528 9866-9866/com.seniorlibs.thirdlib D/ RxFunctionActivity: 对Error事件作出响应
         */
    }

    /**
     * 遇到错误时，将发生的错误传递给一个新的被观察者（Observable），并决定是否需要重新订阅原始被观察者（Observable）& 发送事件
     * <p>
     *
     * @param view
     */
    public void retryWhen(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
                e.onNext(3);
            }
        })
                // 遇到error事件才会回调
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Observable<Throwable> throwableObservable) throws Exception {
                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(@NonNull Throwable throwable) throws Exception {
                                // 1. 若返回的Observable发送的事件 = Error事件，则原始的Observable不重新发送事件
//                                return Observable.error(new Throwable("retryWhen终止啦"));

                                // 2. 若返回的Observable发送的事件 = Next事件 ，则原始的Observable重新发送事件（若持续遇到错误，则持续重试）
                                 return Observable.just(1);
                            }
                        });

                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Integer value) {
                        LogUtils.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "对Error事件作出响应" + e.toString());
                        // 获取异常错误信息
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "对Complete事件作出响应");
                    }
                });

        /**
         * 1. 若返回的Observable发送的事件 = Error事件，则原始的Observable不重新发送事件
         * 03-22 16:16:30.927 10220-10220/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 16:16:30.927 10220-10220/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 16:16:30.930 10220-10220/com.seniorlibs.thirdlib D/ RxFunctionActivity: 对Error事件作出响应java.lang.Throwable: retryWhen终止啦
         *
         * 2. 若返回的Observable发送的事件 = Next事件 ，则原始的Observable重新发送事件（若持续遇到错误，则持续重试）
         * 03-22 16:17:14.413 10513-10513/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 16:17:14.413 10513-10513/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 16:17:14.413 10513-10513/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 16:17:14.413 10513-10513/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 16:17:14.413 10513-10513/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 16:17:14.413 10513-10513/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * ......
         */
    }

    /**
     * 无条件地、重复发送 被观察者事件
     * <p>
     * 场景：具备重载方法，可设置重复创建次数
     *
     * @param view
     */
    public void repeat(View view) {
        Observable.just(1, 2, 3, 4)
                // 重复创建次数 = 3次
                .repeat(3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "对Complete事件作出响应");
                    }
                });

        /**
         * 03-22 16:19:30.042 12208-12208/com.seniorlibs.thirdlib D/ RxFunctionActivity: 开始采用subscribe连接
         * 03-22 16:19:30.044 12208-12208/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 16:19:30.044 12208-12208/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 16:19:30.045 12208-12208/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件3
         * 03-22 16:19:30.045 12208-12208/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件4 1次
         * 03-22 16:19:30.045 12208-12208/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 16:19:30.045 12208-12208/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 16:19:30.045 12208-12208/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件3
         * 03-22 16:19:30.045 12208-12208/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件4 2次
         * 03-22 16:19:30.045 12208-12208/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 16:19:30.045 12208-12208/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 16:19:30.045 12208-12208/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件3
         * 03-22 16:19:30.045 12208-12208/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件4 3次
         * 03-22 16:19:30.045 12208-12208/com.seniorlibs.thirdlib D/ RxFunctionActivity: 对Complete事件作出响应
         */
    }

    /**
     * 有条件地、重复发送 被观察者事件
     * <p>
     *
     * @param view
     */
    public void repeatWhen(View view) {
        Observable.just(1,2,4).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(@NonNull Observable<Object> objectObservable) throws Exception {
                return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Object throwable) throws Exception {
                        // 情况1：若新被观察者（Observable）返回1个Complete() / Error()事件，则不重新发送原来的 Observable
//                        return Observable.empty(); // Observable.empty() = 发送Complete事件，但不会回调观察者的onComplete（）

                        // 情况2：若新被观察者（Observable）返回其余事件，则重新发送原来的Observable
                         return Observable.just(8);
                    }
                });
            }
        })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "对Error事件作出响应：" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "对Complete事件作出响应");
                    }
                });

        /**
         * 情况1：若新被观察者（Observable）返回1个Complete() / Error()事件，则不重新发送原来的 Observable
         * 03-22 16:23:30.834 12768-12768/com.seniorlibs.thirdlib D/ RxFunctionActivity: 开始采用subscribe连接
         * 03-22 16:23:30.837 12768-12768/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 16:23:30.837 12768-12768/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 16:23:30.837 12768-12768/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件4
         *
         * 情况2：若新被观察者（Observable）返回其余事件，则重新发送原来的Observable
         * 03-22 16:25:30.449 13054-13054/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 16:25:30.449 13054-13054/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 16:25:30.449 13054-13054/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件4
         * 03-22 16:25:30.449 13054-13054/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 16:25:30.449 13054-13054/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 16:25:30.449 13054-13054/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件4
         * 03-22 16:25:30.449 13054-13054/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件1
         * 03-22 16:25:30.449 13054-13054/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件2
         * 03-22 16:25:30.449 13054-13054/com.seniorlibs.thirdlib D/ RxFunctionActivity: 接收到了事件4
         * ......
         */
    }
}
