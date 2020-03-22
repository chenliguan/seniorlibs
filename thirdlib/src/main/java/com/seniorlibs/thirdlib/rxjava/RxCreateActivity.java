package com.seniorlibs.thirdlib.rxjava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.seniorlibs.baselib.utils.LogUtils;
import com.seniorlibs.thirdlib.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/3/15
 * Mender:
 * Modify: https://www.jianshu.com/p/e19f8ed863b1
 * Description: 创建操作符
 */
public class RxCreateActivity extends AppCompatActivity {

    private static final String TAG = " RxCreateActivity";

    public static void actionStart(Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, RxCreateActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_create);
    }

    /**
     * 快速创建
     */

    /**
     * 完整创建1个被观察者对象
     *
     * @param view
     */
    public void create(View view) {
        // 1. 通过create()创建被观察者对象
        Observable.create(new ObservableOnSubscribe<Integer>() {
            // 2. 在复写的subscribe()里定义需要发送的事件
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
            // 至此，一个被观察者对象（Observable）就创建完毕

        }).subscribe(new Observer<Integer>() {
            // 以下步骤仅为展示一个完整demo，可以忽略
            // 3. 通过通过订阅（subscribe）连接观察者和被观察者
            // 4. 创建观察者 & 定义响应事件的行为

            // 默认最先调用复写的 onSubscribe（）
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
         * 03-15 21:09:49.455 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 开始采用subscribe连接
         * 03-15 21:09:49.455 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件1
         * 03-15 21:09:49.455 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件2
         * 03-15 21:09:49.455 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件3
         * 03-15 21:09:49.455 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 对Complete事件作出响应
         */
    }

    /**
     * 快速创建1个被观察者对象（Observable），发送事件的特点：直接发送传入的事件
     * <p>
     * 场景：快速创建被观察者对象（Observable） & 发送10个以下事件
     *
     * @param view
     */
    public void just(View view) {
        // 1. 创建时传入整型1、2、3、4
        // 在创建后就会发送这些对象，相当于执行了onNext(1)、onNext(2)、onNext(3)、onNext(4)
        Observable.just(1, 2, 3, 4)
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

        Observable.just(5, 6, 7, 8)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer value) throws Exception {
                        LogUtils.d(TAG, "接收到了事件" + value);
                    }
                });

        /**
         * 03-15 21:09:11.654 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 开始采用subscribe连接
         * 03-15 21:09:11.654 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件1
         * 03-15 21:09:11.654 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件2
         * 03-15 21:09:11.654 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件3
         * 03-15 21:09:11.654 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件4
         * 03-15 21:09:11.654 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 对Complete事件作出响应
         *
         * 03-15 21:09:11.654 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件5
         * 03-15 21:09:11.654 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件6
         * 03-15 21:09:11.654 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件7
         * 03-15 21:09:11.655 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件8
         */
    }

    /**
     * 创建被观察者对象（Observable）时传入数组，在创建后就会将该数组转换成Observable & 发送该对象中的所有数据
     * <p>
     * 场景：（1）快速创建 被观察者对象（Observable） & 发送10个以上事件（数组形式）
     * （2）数组元素遍历
     *
     * @param view
     */
    public void fromArray(View view) {
        Integer[] items = {0, 1, 2, 3, 4};
        Observable.fromArray(items)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.d(TAG, "接收到了事件" + value);
                        LogUtils.e(TAG, "数组中的元素 = " + value);
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
         * 03-15 21:08:45.273 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 开始采用subscribe连接
         * 03-15 21:08:45.273 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件0
         * 03-15 21:08:45.273 23839-23839/com.seniorlibs.thirdlib E/ RxCreateActivity: 数组中的元素 = 0
         * 03-15 21:08:45.273 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件1
         * 03-15 21:08:45.273 23839-23839/com.seniorlibs.thirdlib E/ RxCreateActivity: 数组中的元素 = 1
         * 03-15 21:08:45.273 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件2
         * 03-15 21:08:45.273 23839-23839/com.seniorlibs.thirdlib E/ RxCreateActivity: 数组中的元素 = 2
         * 03-15 21:08:45.273 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件3
         * 03-15 21:08:45.274 23839-23839/com.seniorlibs.thirdlib E/ RxCreateActivity: 数组中的元素 = 3
         * 03-15 21:08:45.274 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件4
         * 03-15 21:08:45.274 23839-23839/com.seniorlibs.thirdlib E/ RxCreateActivity: 数组中的元素 = 4
         * 03-15 21:08:45.274 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 对Complete事件作出响应
         */
    }

    /**
     * 快速创建1个被观察者对象（Observable）,直接发送 传入的集合List数据
     * <p>
     * 场景：（1）快速创建 被观察者对象（Observable） & 发送10个以上事件（集合形式）
     * （2）集合元素遍历
     *
     * @param view
     */
    public void fromIterable(View view) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Observable.fromIterable(list)
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

        list.add(4);
        list.add(5);
        Observable.fromIterable(list)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer value) throws Exception {
                        LogUtils.d(TAG, "遍历集合中的数据元素 = " + value);
                    }
                });

        /**
         * 03-15 21:08:29.467 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 开始采用subscribe连接
         * 03-15 21:08:29.467 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件1
         * 03-15 21:08:29.467 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件2
         * 03-15 21:08:29.467 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件3
         * 03-15 21:08:29.467 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 对Complete事件作出响应
         *
         * 03-15 21:08:29.470 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 遍历集合中的数据元素 = 1
         * 03-15 21:08:29.470 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 遍历集合中的数据元素 = 2
         * 03-15 21:08:29.470 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 遍历集合中的数据元素 = 3
         * 03-15 21:08:29.470 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 遍历集合中的数据元素 = 4
         * 03-15 21:08:29.470 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 遍历集合中的数据元素 = 5
         */
    }

    /**
     * 仅发送Complete事件，直接通知完成
     * <p>
     * 场景：发送空事件，结束本次事件流
     *
     * @param view
     */
    public void empty(View view) {
        Observable.empty()
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Object value) {
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
         * 03-15 21:08:18.572 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 开始采用subscribe连接
         * 03-15 21:08:18.572 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 对Complete事件作出响应
         */
    }

    /**
     * 仅发送Error事件，直接通知异常，可自定义异常
     * <p>
     * 场景：发送异常事件，直接调用onError（）
     *
     * @param view
     */
    public void error(View view) {
        Observable.error(new RuntimeException("抛异常了"))
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Object value) {
                        LogUtils.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "对Error事件作出响应 = " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "对Complete事件作出响应");
                    }
                });

        /**
         * 03-15 21:08:01.267 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 开始采用subscribe连接
         * 03-15 21:08:01.267 23839-23839/com.seniorlibs.thirdlib E/ RxCreateActivity: 对Error事件作出响应 = java.lang.RuntimeException: 抛异常了
         */
    }

    /**
     * 不发送任何事件
     * <p>
     * 场景：观察者接收后什么都不调用
     *
     * @param view
     */
    public void never(View view) {
        Observable.never()
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Object value) {
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
         * 03-15 21:07:51.729 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 开始采用subscribe连接
         */
    }


    /**
     * 延迟创建
     */

    /**
     * 直到有观察者（Observer）订阅时，才动态创建新的被观察者对象（Observable） & 发送事件，确保Observable对象里的数据是最新的
     * <p>
     * 场景：动态创建被观察者对象（Observable） & 获取最新的Observable对象数据
     *
     * @param view
     */
    private Integer mDeferNum;

    public void defer(View view) {
        // 1. 第1次对i赋值
        mDeferNum = 10;
        // 2. 通过defer定义被观察者对象，注：此时被观察者对象还没创建
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.just(mDeferNum);
            }
        });
        // 3. 第2次对i赋值
        mDeferNum = 15;
        // 4. 观察者开始订阅，注：此时才会调用defer()创建被观察者对象（Observable）
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d(TAG, "开始采用subscribe连接");
            }

            @Override
            public void onNext(Integer value) {
                LogUtils.d(TAG, "接收到的整数是" + value);
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
         * 03-15 20:59:07.705 20867-20867/com.seniorlibs.thirdlib D/ RxCreateActivity: 开始采用subscribe连接
         * 03-15 20:59:07.705 20867-20867/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到的整数是15
         * 03-15 20:59:07.705 20867-20867/com.seniorlibs.thirdlib D/ RxCreateActivity: 对Complete事件作出响应
         */
    }

    /**
     * 延迟指定时间后，发送1个数值（Long类型）。本质 = 延迟指定时间后，调用一次onNext(0)
     * 注：timer操作符默认运行在一个新线程上，也可自定义线程调度器（第3个参数）：timer(long,TimeUnit,Scheduler)
     * <p>
     * 场景：延迟指定事件，发送一个数值，一般用于检测
     *
     * @param view
     */
    public void timer(View view) {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long value) {
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
         * 03-15 20:58:25.884 20867-20867/com.seniorlibs.thirdlib D/ RxCreateActivity: 开始采用subscribe连接
         * 03-15 20:58:27.887 20867-23655/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件：0
         * 03-15 20:58:27.888 20867-23655/com.seniorlibs.thirdlib D/ RxCreateActivity: 对Complete事件作出响应
         */
    }

    /**
     * 每隔指定时间，就发送事件。发送的事件序列 = 从0开始、无限递增1的的整数序列
     * 注：interval默认在computation调度器上执行，也可自定义指定线程调度器（第3个参数）：interval(long,TimeUnit,Scheduler)
     * <p>
     *
     * @param view
     */
    public void interval(View view) {
        // 参数1 = 第1次延迟时间；参数2 = 间隔时间数字；参数3 = 时间单位；
        Observable.interval(3, 1, TimeUnit.SECONDS)
                // 该例子发送的事件序列特点：延迟3s后发送事件，每隔1秒产生1个数字（从0开始递增1，无限个）
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long value) {
                        LogUtils.d(TAG, "接收到了事件 ：" + value);
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
         * 03-15 20:59:24.621 20867-20867/com.seniorlibs.thirdlib D/ RxCreateActivity: 开始采用subscribe连接
         * 03-15 20:59:27.622 20867-23690/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件 ：0（延迟3秒）
         * 03-15 20:59:28.621 20867-23690/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件 ：1（间隔1秒）（从0开始递增1，无限个）
         * 03-15 20:59:29.622 20867-23690/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件 ：2
         * 03-15 20:59:30.622 20867-23690/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件 ：3
         * 03-15 20:59:31.621 20867-23690/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件 ：4
         * 03-15 20:59:32.621 20867-23690/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件 ：5
         * ...
         * ...
         * ...
         */
    }

    /**
     * 每隔指定时间就发送事件，可指定发送的数据的数量。
     * 发送的事件序列 = 从0开始、无限递增1的的整数序列，作用类似于interval()但可指定发送的数据的数量
     * <p>
     *
     * @param view
     */
    public void intervalRange(View view) {
        // 参数1 = 事件序列起始点；参数2 = 事件数量；参数3 = 第1次事件延迟发送时间；参数4 = 间隔时间数字；参数5 = 时间单位
        Observable.intervalRange(3, 10, 2, 1, TimeUnit.SECONDS)
                // 该例子发送的事件序列特点：
                // 1. 从3开始，一共发送10个事件；
                // 2. 第1次延迟2s发送，之后每隔1秒产生1个数字（从3开始递增1）
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long value) {
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
         * 03-15 21:01:30.522 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 开始采用subscribe连接
         * 03-15 21:01:32.524 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件：3（从3开始）（第1次延迟2s发送）
         * 03-15 21:01:33.524 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件：4（间隔1秒）（从3开始递增1）
         * 03-15 21:01:34.524 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件：5
         * 03-15 21:01:35.523 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件：6
         * 03-15 21:01:36.524 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件：7
         * 03-15 21:01:37.524 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件：8
         * 03-15 21:01:38.523 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件：9
         * 03-15 21:01:39.523 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件：10
         * 03-15 21:01:40.523 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件：11
         * 03-15 21:01:41.524 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件：12（一共发送10个事件）
         * 03-15 21:01:41.524 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 对Complete事件作出响应
         */
    }

    /**
     * 连续发送1个事件序列，可指定范围。从0开始、无限递增1的的整数序列，作用类似于intervalRange()，但区别在于：无延迟发送事件
     * 注：若设置为负数，则会抛出异常
     * <p>
     * 场景：观察者接收后什么都不调用
     *
     * @param view
     */
    public void range(View view) {
        // 参数1 = 事件序列起始点；参数2 = 事件数量；
        Observable.range(3, 10)
                // 该例子发送的事件序列特点：从3开始发送，每次发送事件递增1，一共发送10个事件
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
         * 03-15 21:01:30.522 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 开始采用subscribe连接
         * 03-15 21:01:32.524 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件：3
         * 03-15 21:01:33.524 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件：4
         * 03-15 21:01:34.524 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件：5
         * 03-15 21:01:35.523 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件：6
         * 03-15 21:01:36.524 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件：7
         * 03-15 21:01:37.524 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件：8
         * 03-15 21:01:38.523 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件：9
         * 03-15 21:01:39.523 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件：10
         * 03-15 21:01:40.523 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件：11
         * 03-15 21:01:41.524 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件：12
         * 03-15 21:01:41.524 23839-24077/com.seniorlibs.thirdlib D/ RxCreateActivity: 对Complete事件作出响应
         */
    }

    /**
     * 类似于range（），区别在于该方法支持数据类型 = Long
     * <p>
     * 场景：观察者接收后什么都不调用
     *
     * @param view
     */
    public void rangeLong(View view) {
        // 参数1 = 事件序列起始点；参数2 = 事件数量；
        Observable.rangeLong(3, 10)
                // 该例子发送的事件序列特点：从3开始发送，每次发送事件递增1，一共发送10个事件
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long value) {
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
         * 03-15 21:05:21.174 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 开始采用subscribe连接
         * 03-15 21:05:21.174 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件3
         * 03-15 21:05:21.174 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件4
         * 03-15 21:05:21.175 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件5
         * 03-15 21:05:21.175 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件6
         * 03-15 21:05:21.175 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件7
         * 03-15 21:05:21.175 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件8
         * 03-15 21:05:21.175 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件9
         * 03-15 21:05:21.175 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件10
         * 03-15 21:05:21.175 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件11
         * 03-15 21:05:21.175 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 接收到了事件12
         * 03-15 21:05:21.175 23839-23839/com.seniorlibs.thirdlib D/ RxCreateActivity: 对Complete事件作出响应
         */
    }
}















