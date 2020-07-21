package com.seniorlibs.thirdlib.rxjava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.seniorlibs.baselib.utils.LogUtils;
import com.seniorlibs.thirdlib.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/3/15
 * Mender:
 * Modify: https://www.jianshu.com/p/c3a930a03855
 * Description: 过滤操作符
 */
public class RxFilterActivity extends AppCompatActivity {

    private static final String TAG = " RxFilterActivity";

    public static void actionStart(Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, RxFilterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_filter);
    }

    /**
     * 根据 指定条件 过滤事件
     */

    /**
     * 过滤 特定条件的事件
     *
     * @param view
     */
    public void filter(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
            }
        }).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                // 返回true，则继续发送；返回false，则不发送（即过滤）
                return integer > 3; // 本例子 = 过滤了整数≤3的事件
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogUtils.d(TAG, "获取到的事件元素是： " + integer);
            }
        });

        /**
         * 03-22 17:31:54.251 16956-16956/com.seniorlibs.thirdlib D/ RxFilterActivity: 获取到的事件元素是： 4
         * 03-22 17:31:54.251 16956-16956/com.seniorlibs.thirdlib D/ RxFilterActivity: 获取到的事件元素是： 5
         */
    }

    /**
     * 过滤 特定数据类型的数据
     *
     * @param view
     */
    public void ofType(View view) {
        Observable.just(1, "Carson", 3, "Ho", 5)
                // 筛选出 整型数据
                .ofType(Integer.class)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d(TAG, "获取到的整型事件元素是： " + integer);
                    }
                });

        /**
         * 03-22 17:32:04.973 16956-16956/com.seniorlibs.thirdlib D/ RxFilterActivity: 获取到的整型事件元素是： 1
         * 03-22 17:32:04.973 16956-16956/com.seniorlibs.thirdlib D/ RxFilterActivity: 获取到的整型事件元素是： 3
         * 03-22 17:32:04.973 16956-16956/com.seniorlibs.thirdlib D/ RxFilterActivity: 获取到的整型事件元素是： 5
         */
    }

    /**
     * 跳过某个事件
     *
     * @param view
     */
    public void skip(View view) {
        // 根据顺序跳过数据项
        Observable.just(1, 2, 3, 4, 5)
                // 跳过正序的前1项
                .skip(1)
                // 跳过正序的后2项
                .skipLast(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d(TAG, "获取到的整型事件元素是： " + integer);
                    }
                });

        /**
         * 03-22 17:32:15.742 16956-16956/com.seniorlibs.thirdlib D/ RxFilterActivity: 获取到的整型事件元素是： 2
         * 03-22 17:32:15.743 16956-16956/com.seniorlibs.thirdlib D/ RxFilterActivity: 获取到的整型事件元素是： 3
         */
    }

    /**
     * 跳过某个事件
     *
     * @param view
     */
    public void skipLast(View view) {
        // 根据时间跳过数据项：发送数据0-5，每隔1s发送一次，每次递增1；第1次发送延迟0s
        Observable.intervalRange(0, 5, 0, 1, TimeUnit.SECONDS)
                // 跳过第1s发送的数据
                .skip(1, TimeUnit.SECONDS)
                // 跳过最后1s发送的数据
                .skipLast(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long along) throws Exception {
                        LogUtils.d(TAG, "获取到的整型事件元素是： " + along);
                    }
                });

        /**
         * 03-22 17:32:51.445 16956-18984/com.seniorlibs.thirdlib D/ RxFilterActivity: 获取到的整型事件元素是： 1
         * 03-22 17:32:52.445 16956-18984/com.seniorlibs.thirdlib D/ RxFilterActivity: 获取到的整型事件元素是： 2
         * 03-22 17:32:53.445 16956-18984/com.seniorlibs.thirdlib D/ RxFilterActivity: 获取到的整型事件元素是： 3
         */
    }

    /**
     * 过滤事件序列中重复的事件
     *
     * @param view
     */
    public void distinct(View view) {
        // 过滤事件序列中重复的事件
        Observable.just(1, 2, 3, 1, 2)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d(TAG, "不重复的整型事件元素是： " + integer);
                    }
                });

        /**
         * 03-22 17:33:18.592 16956-16956/com.seniorlibs.thirdlib D/ RxFilterActivity: 不重复的整型事件元素是： 1
         * 03-22 17:33:18.592 16956-16956/com.seniorlibs.thirdlib D/ RxFilterActivity: 不重复的整型事件元素是： 2
         * 03-22 17:33:18.592 16956-16956/com.seniorlibs.thirdlib D/ RxFilterActivity: 不重复的整型事件元素是： 3
         */
    }

    /**
     * 过滤事件序列中连续重复的事件
     *
     * @param view
     */
    public void distinctUntilChanged(View view) {
        // 过滤事件序列中 连续重复的事件：下面序列中，连续重复的事件 = 3、4
        Observable.just(1, 2, 3, 1, 2, 3, 3, 4, 4)
                .distinctUntilChanged()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d(TAG, "不连续重复的整型事件元素是： " + integer);
                    }
                });

        /**
         * 03-22 17:33:43.543 16956-16956/com.seniorlibs.thirdlib D/ RxFilterActivity: 不连续重复的整型事件元素是： 1
         * 03-22 17:33:43.543 16956-16956/com.seniorlibs.thirdlib D/ RxFilterActivity: 不连续重复的整型事件元素是： 2
         * 03-22 17:33:43.544 16956-16956/com.seniorlibs.thirdlib D/ RxFilterActivity: 不连续重复的整型事件元素是： 3
         * 03-22 17:33:43.544 16956-16956/com.seniorlibs.thirdlib D/ RxFilterActivity: 不连续重复的整型事件元素是： 1
         * 03-22 17:33:43.544 16956-16956/com.seniorlibs.thirdlib D/ RxFilterActivity: 不连续重复的整型事件元素是： 2
         * 03-22 17:33:43.544 16956-16956/com.seniorlibs.thirdlib D/ RxFilterActivity: 不连续重复的整型事件元素是： 3
         * 03-22 17:33:43.544 16956-16956/com.seniorlibs.thirdlib D/ RxFilterActivity: 不连续重复的整型事件元素是： 4
         */
    }


    /**
     * 根据 指定事件数量 过滤事件
     */

    /**
     * 指定观察者最多能接收到的事件数量
     * 可理解为：被观察者还是发送了5个事件，只是因为操作符的存在拦截了3个事件，最终观察者接收到的是2个事件
     *
     * @param view
     */
    public void take(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
            }

            // 指定了观察者只能接收2个事件
        }).take(2)
                .subscribe(new Observer<Integer>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.d(TAG, "过滤后得到的事件是：" + value);
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
         * 03-22 17:46:46.143 19826-19826/com.seniorlibs.thirdlib D/ RxFilterActivity: 开始采用subscribe连接
         * 03-22 17:46:46.143 19826-19826/com.seniorlibs.thirdlib D/ RxFilterActivity: 过滤后得到的事件是：1
         * 03-22 17:46:46.143 19826-19826/com.seniorlibs.thirdlib D/ RxFilterActivity: 过滤后得到的事件是：2
         * 03-22 17:46:46.144 19826-19826/com.seniorlibs.thirdlib D/ RxFilterActivity: 对Complete事件作出响应
         */
    }

    /**
     * 指定观察者只能接受被观察者发送的3个事件
     *
     * @param view
     */
    public void takeLast(View view) {
        Observable.just(1, 2, 3, 4, 5)
                // 指定观察者只能接受被观察者发送的3个事件
                .takeLast(3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.d(TAG, "过滤后得到的事件是：" + value);
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
         * 03-22 17:47:01.320 19826-19826/com.seniorlibs.thirdlib D/ RxFilterActivity: 开始采用subscribe连接
         * 03-22 17:47:01.320 19826-19826/com.seniorlibs.thirdlib D/ RxFilterActivity: 过滤后得到的事件是：3
         * 03-22 17:47:01.320 19826-19826/com.seniorlibs.thirdlib D/ RxFilterActivity: 过滤后得到的事件是：4
         * 03-22 17:47:01.321 19826-19826/com.seniorlibs.thirdlib D/ RxFilterActivity: 过滤后得到的事件是：5
         * 03-22 17:47:01.321 19826-19826/com.seniorlibs.thirdlib D/ RxFilterActivity: 对Complete事件作出响应
         */
    }


    /**
     * 根据 指定时间 过滤事件
     */

    /**
     * 在某段时间内，只发送该段时间内第1次事件
     *
     * @param view
     */
    public void throttleFirst(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // 隔段事件发送时间
                e.onNext(1);
                Thread.sleep(500);

                e.onNext(2);
                Thread.sleep(400);

                e.onNext(3);
                Thread.sleep(100); // 1s

                e.onNext(4);
                Thread.sleep(500);

                e.onNext(5);
                Thread.sleep(400);

                e.onNext(6);
                Thread.sleep(100); // 1s

                e.onNext(7);
                Thread.sleep(500);
                e.onNext(8);

                Thread.sleep(400);
                e.onNext(9);

                Thread.sleep(100); // 1s
                e.onComplete();
            }

            // 每1秒中采用数据
        }).throttleFirst(1, TimeUnit.SECONDS)
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
         * 03-22 17:47:12.783 19826-19826/com.seniorlibs.thirdlib D/ RxFilterActivity: 开始采用subscribe连接
         * 03-22 17:47:12.784 19826-19826/com.seniorlibs.thirdlib D/ RxFilterActivity: 接收到了事件1
         * 03-22 17:47:13.788 19826-19826/com.seniorlibs.thirdlib D/ RxFilterActivity: 接收到了事件4
         * 03-22 17:47:14.790 19826-19826/com.seniorlibs.thirdlib D/ RxFilterActivity: 接收到了事件7
         * 03-22 17:47:15.791 19826-19826/com.seniorlibs.thirdlib D/ RxFilterActivity: 对Complete事件作出响应
         */
    }

    /**
     * 在某段时间内，只发送该段时间内最后1次事件
     *
     * @param view
     */
    public void throttleLast(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // 隔段事件发送时间
                e.onNext(1);
                Thread.sleep(500);

                e.onNext(2);
                Thread.sleep(400);

                e.onNext(3);
                Thread.sleep(100); // 1s

                e.onNext(4);
                Thread.sleep(500);

                e.onNext(5);
                Thread.sleep(400);

                e.onNext(6);
                Thread.sleep(100); // 1s

                e.onNext(7);
                Thread.sleep(500);
                e.onNext(8);

                Thread.sleep(400);
                e.onNext(9);

                Thread.sleep(100); // 1s
                e.onComplete();
            }

            // 每1秒中采用数据
        }).throttleLast(1, TimeUnit.SECONDS)
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
         * 03-22 17:49:34.293 19826-19826/com.seniorlibs.thirdlib D/ RxFilterActivity: 开始采用subscribe连接
         * 03-22 17:49:35.294 19826-20203/com.seniorlibs.thirdlib D/ RxFilterActivity: 接收到了事件3
         * 03-22 17:49:36.294 19826-20203/com.seniorlibs.thirdlib D/ RxFilterActivity: 接收到了事件6
         * 03-22 17:49:37.294 19826-20203/com.seniorlibs.thirdlib D/ RxFilterActivity: 接收到了事件9
         * 03-22 17:49:37.298 19826-19826/com.seniorlibs.thirdlib D/ RxFilterActivity: 对Complete事件作出响应
         */
    }

    /**
     * 在某段时间内，只发送该段时间内最新（最后）1次事件
     * 与throttleLast()操作符类似
     *
     * @param view
     */
    public void sample(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // 隔段事件发送时间
                e.onNext(1);
                Thread.sleep(500);

                e.onNext(2);
                Thread.sleep(400);

                e.onNext(3);
                Thread.sleep(100); // 1s

                e.onNext(4);
                Thread.sleep(500);

                e.onNext(5);
                Thread.sleep(400);

                e.onNext(6);
                Thread.sleep(100); // 1s

                e.onNext(7);
                Thread.sleep(500);
                e.onNext(8);

                Thread.sleep(400);
                e.onNext(9);

                Thread.sleep(100); // 1s
                e.onComplete();
            }

            // 每1秒中采用数据
        }).sample(1, TimeUnit.SECONDS)
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
         * 03-22 17:49:34.293 19826-19826/com.seniorlibs.thirdlib D/ RxFilterActivity: 开始采用subscribe连接
         * 03-22 17:49:35.294 19826-20203/com.seniorlibs.thirdlib D/ RxFilterActivity: 接收到了事件3
         * 03-22 17:49:36.294 19826-20203/com.seniorlibs.thirdlib D/ RxFilterActivity: 接收到了事件6
         * 03-22 17:49:37.294 19826-20203/com.seniorlibs.thirdlib D/ RxFilterActivity: 接收到了事件9
         * 03-22 17:49:37.298 19826-19826/com.seniorlibs.thirdlib D/ RxFilterActivity: 对Complete事件作出响应
         */
    }

    /**
     * 发送数据事件时，若2次发送事件的间隔＜指定时间，就会丢弃前一次的数据，直到指定时间内都没有新数据发射时才会发送后一次的数据
     *
     * @param view
     */
    public void throttleWithTimeout(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // 隔段事件发送时间
                e.onNext(1);
                Thread.sleep(500);
                e.onNext(2);         // 1和2之间的间隔小于指定时间1s，所以前1次数据（1）会被抛弃，2会被保留
                Thread.sleep(1500);
                e.onNext(3);         // 因为2和3之间的间隔大于指定时间1s，所以之前被保留的2事件将发出
                Thread.sleep(1500);
                e.onNext(4);         // 因为3和4之间的间隔大于指定时间1s，所以3事件将发出
                Thread.sleep(500);
                e.onNext(5);         // 因为4和5之间的间隔小于指定时间1s，所以前1次数据（4）会被抛弃，5会被保留
                Thread.sleep(500);
                e.onNext(6);         // 因为5和6之间的间隔小于指定时间1s，所以前1次数据（5）会被抛弃，6会被保留
                Thread.sleep(1500);

                e.onNext(7);        // 因为6和7之间的间隔大于指定时间1s，所以之前被保留的6事件将发出
                Thread.sleep(400);
                e.onNext(8);        // 因为7和8之间的间隔小于指定时间1s，所以前1次数据（7）会被抛弃，8会被保留
                Thread.sleep(400);
                e.onNext(9);        // 因为8和9之间的间隔小于指定时间1s，所以前1次数据（8）会被抛弃，9会被保留
                Thread.sleep(400);
                e.onNext(10);       // 因为9和10之间的间隔小于指定时间1s，所以前1次数据（9）会被抛弃，10会被保留
                Thread.sleep(400);

                e.onComplete();
            }

            // 每1秒中采用数据
        }).throttleWithTimeout(1, TimeUnit.SECONDS)
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
         * 03-22 17:57:27.575 21149-21149/com.seniorlibs.thirdlib D/ RxFilterActivity: 开始采用subscribe连接
         * 03-22 17:57:29.078 21149-21266/com.seniorlibs.thirdlib D/ RxFilterActivity: 接收到了事件2
         * 03-22 17:57:30.578 21149-21266/com.seniorlibs.thirdlib D/ RxFilterActivity: 接收到了事件3
         * 03-22 17:57:33.080 21149-21266/com.seniorlibs.thirdlib D/ RxFilterActivity: 接收到了事件6
         * 03-22 17:57:35.183 21149-21149/com.seniorlibs.thirdlib D/ RxFilterActivity: 接收到了事件10
         * 03-22 17:57:35.183 21149-21149/com.seniorlibs.thirdlib D/ RxFilterActivity: 对Complete事件作出响应
         */
    }

    /**
     * 发送数据事件时，若2次发送事件的间隔<指定时间，就会丢弃前一次的数据，直到指定时间内都没有新数据发射时才会发送后一次的数据
     *
     * @param view
     */
    public void debounce(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // 隔段事件发送时间
                e.onNext(1);
                Thread.sleep(500);
                e.onNext(2);         // 1和2之间的间隔小于指定时间1s，所以前1次数据（1）会被抛弃，2会被保留
                Thread.sleep(1500);
                e.onNext(3);         // 因为2和3之间的间隔大于指定时间1s，所以之前被保留的2事件将发出
                Thread.sleep(1500);
                e.onNext(4);         // 因为3和4之间的间隔大于指定时间1s，所以3事件将发出
                Thread.sleep(500);
                e.onNext(5);         // 因为4和5之间的间隔小于指定时间1s，所以前1次数据（4）会被抛弃，5会被保留
                Thread.sleep(500);
                e.onNext(6);         // 因为5和6之间的间隔小于指定时间1s，所以前1次数据（5）会被抛弃，6会被保留
                Thread.sleep(1500);

                e.onNext(7);        // 因为6和7之间的间隔大于指定时间1s，所以之前被保留的6事件将发出
                Thread.sleep(400);
                e.onNext(8);        // 因为7和8之间的间隔小于指定时间1s，所以前1次数据（7）会被抛弃，8会被保留
                Thread.sleep(400);
                e.onNext(9);        // 因为8和9之间的间隔小于指定时间1s，所以前1次数据（8）会被抛弃，9会被保留
                Thread.sleep(400);
                e.onNext(10);       // 因为9和10之间的间隔小于指定时间1s，所以前1次数据（9）会被抛弃，10会被保留
                Thread.sleep(400);

                e.onComplete();
            }

            // 每1秒中采用数据
        }).debounce(1, TimeUnit.SECONDS)
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
         * 03-22 17:59:41.986 21500-21500/com.seniorlibs.thirdlib D/ RxFilterActivity: 开始采用subscribe连接
         * 03-22 17:59:43.490 21500-21580/com.seniorlibs.thirdlib D/ RxFilterActivity: 接收到了事件2
         * 03-22 17:59:44.990 21500-21580/com.seniorlibs.thirdlib D/ RxFilterActivity: 接收到了事件3
         * 03-22 17:59:47.492 21500-21580/com.seniorlibs.thirdlib D/ RxFilterActivity: 接收到了事件6
         * 03-22 17:59:49.595 21500-21500/com.seniorlibs.thirdlib D/ RxFilterActivity: 接收到了事件10
         * 03-22 17:59:49.595 21500-21500/com.seniorlibs.thirdlib D/ RxFilterActivity: 对Complete事件作出响应
         */
    }


    /**
     * 根据 指定事件位置 过滤事件
     */

    /**
     * 获取第1个元素
     *
     * @param view
     */
    public void firstElement(View view) {
        Observable.just(1, 2, 3, 4, 5)
                .firstElement()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept( Integer integer) throws Exception {
                        LogUtils.d(TAG,"获取到的第一个事件是： "+ integer);
                    }
                });

        /**
         * 03-22 18:07:22.922 22192-22192/com.seniorlibs.thirdlib D/ RxFilterActivity: 获取到的第一个事件是： 1
         */
    }

    /**
     * 获取最后1个元素
     *
     * @param view
     */
    public void lastElement(View view) {
        Observable.just(1, 2, 3, 4, 5)
                .lastElement()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept( Integer integer) throws Exception {
                        LogUtils.d(TAG,"获取到的最后1个事件是： "+ integer);
                    }
                });

        /**
         * 03-22 18:07:37.187 22192-22192/com.seniorlibs.thirdlib D/ RxFilterActivity: 获取到的最后1个事件是： 5
         */
    }

    /**
     * 指定接收某个元素（通过 索引值 确定）
     * 允许越界，即获取的位置索引 > 发送事件序列长度
     *
     * @param view
     */
    public void elementAt(View view) {
        // 使用1：获取位置索引 = 2 的 元素
        // 位置索引从0开始
        Observable.just(1, 2, 3, 4, 5)
                .elementAt(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d(TAG, "获取到的事件元素是： " + integer);
                    }
                });

        /**
         * 03-22 18:07:55.568 22192-22192/com.seniorlibs.thirdlib D/ RxFilterActivity: 获取到的事件元素是： 3
         */

        // 使用2：获取的位置索引 > 发送事件序列长度时，设置默认参数
        Observable.just(1, 2, 3, 4, 5)
                .elementAt(6, 10)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d(TAG, "获取到的事件元素是： " + integer);
                    }
                });

        /**
         * 03-22 18:07:55.569 22192-22192/com.seniorlibs.thirdlib D/ RxFilterActivity: 获取到的事件元素是： 10
         */
    }

    /**
     * 在elementAt()的基础上，当出现越界情况（即获取的位置索引 > 发送事件序列长度）时，即抛出异常
     *
     * @param view
     */
    public void elementAtOrError(View view) {
        Observable.just(1, 2, 3, 4, 5)
                .elementAtOrError(6)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept( Integer integer) throws Exception {
                        LogUtils.d(TAG,"获取到的事件元素是： "+ integer);
                    }
                });

        /**
         * 03-22 18:08:35.515 22192-22192/com.seniorlibs.thirdlib E/AndroidRuntime: FATAL EXCEPTION: main
         *     Process: com.seniorlibs.thirdlib, PID: 22192
         *     io.reactivex.exceptions.OnErrorNotImplementedException: The exception was not handled due to missing onError handler in the subscribe() method call. Further reading: https://github.com/ReactiveX/RxJava/wiki/Error-Handling | java.util.NoSuchElementException
         *         at io.reactivex.internal.functions.Functions$OnErrorMissingConsumer.accept(Functions.java:704)
         *         at io.reactivex.internal.functions.Functions$OnErrorMissingConsumer.accept(Functions.java:701)
         *         at io.reactivex.internal.observers.ConsumerSingleObserver.onError(ConsumerSingleObserver.java:46)
         *         at io.reactivex.internal.operators.observable.ObservableElementAtSingle$ElementAtObserver.onComplete(ObservableElementAtSingle.java:115)
         *         at io.reactivex.internal.operators.observable.ObservableFromArray$FromArrayDisposable.run(ObservableFromArray.java:111)
         *         at io.reactivex.internal.operators.observable.ObservableFromArray.subscribeActual(ObservableFromArray.java:37)
         *         at io.reactivex.Observable.subscribe(Observable.java:12267)
         *         at io.reactivex.internal.operators.observable.ObservableElementAtSingle.subscribeActual(ObservableElementAtSingle.java:37)
         *         at io.reactivex.Single.subscribe(Single.java:3603)
         *         at io.reactivex.Single.subscribe(Single.java:3589)
         *         at io.reactivex.Single.subscribe(Single.java:3559)
         *         at com.seniorlibs.thirdlib.rxjava.RxFilterActivity.elementAtOrError(RxFilterActivity.java:749)
         *         at java.lang.reflect.Method.invoke(Native Method)
         */
    }
}

