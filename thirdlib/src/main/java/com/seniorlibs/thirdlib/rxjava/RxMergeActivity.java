package com.seniorlibs.thirdlib.rxjava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.seniorlibs.baselib.utils.LogUtils;
import com.seniorlibs.thirdlib.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/3/15
 * Mender:
 * Modify: https://www.jianshu.com/p/c2a7c03da16d
 * Description: 组合/合并操作符
 */
public class RxMergeActivity extends AppCompatActivity {

    private static final String TAG = " RxMergeActivity";

    public static void actionStart(Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, RxMergeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_merge);
    }

    /**
     * 组合多个被观察者一起发送数据，合并后按发送顺序串行执行，组合被观察者数量≤4个
     *
     * @param view
     */
    public void concat(View view) {
        Observable.concat(Observable.just(1, 2, 3),
                Observable.just(4, 5, 6),
                Observable.just(7, 8, 9),
                Observable.just(10, 11, 12))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "开始采用subscribe连接");
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
         * 03-15 22:09:31.474 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 开始采用subscribe连接
         * 03-15 22:09:31.475 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：1
         * 03-15 22:09:31.475 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：2
         * 03-15 22:09:31.475 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：3
         * 03-15 22:09:31.475 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：4
         * 03-15 22:09:31.475 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：5
         * 03-15 22:09:31.475 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：6
         * 03-15 22:09:31.475 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：7
         * 03-15 22:09:31.475 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：8
         * 03-15 22:09:31.475 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：9
         * 03-15 22:09:31.475 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：10
         * 03-15 22:09:31.475 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：11
         * 03-15 22:09:31.475 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：12
         * 03-15 22:09:31.476 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 对Complete事件作出响应
         */
    }

    /**
     * 组合多个被观察者一起发送数据，合并后按发送顺序串行执行，组合被观察者数量可>4个
     *
     * @param view
     */
    public void concatArray(View view) {
        Observable.concatArray(Observable.just(1, 2, 3),
                Observable.just(4, 5, 6),
                Observable.just(7, 8, 9),
                Observable.just(10, 11, 12),
                Observable.just(13, 14, 15))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "开始采用subscribe连接");
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
         * 03-15 22:09:57.696 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 开始采用subscribe连接
         * 03-15 22:09:57.697 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：1
         * 03-15 22:09:57.697 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：2
         * 03-15 22:09:57.697 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：3
         * 03-15 22:09:57.697 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：4
         * 03-15 22:09:57.697 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：5
         * 03-15 22:09:57.697 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：6
         * 03-15 22:09:57.697 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：7
         * 03-15 22:09:57.697 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：8
         * 03-15 22:09:57.697 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：9
         * 03-15 22:09:57.697 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：10
         * 03-15 22:09:57.697 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：11
         * 03-15 22:09:57.697 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：12
         * 03-15 22:09:57.697 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：13
         * 03-15 22:09:57.697 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：14
         * 03-15 22:09:57.697 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：15
         * 03-15 22:09:57.697 31101-31101/com.seniorlibs.thirdlib D/ RxMergeActivity: 对Complete事件作出响应
         */
    }

    /**
     * 组合多个被观察者一起发送数据，合并后按时间线并行执行，组合被观察者数量可≤4个
     *
     * @param view
     */
    public void merge(View view) {
        Observable.merge(
                Observable.intervalRange(0, 2, 1, 1, TimeUnit.SECONDS), // 从0开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                Observable.intervalRange(3, 2, 1, 1, TimeUnit.SECONDS)) // 从2开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long value) {
                        LogUtils.d(TAG, "接收到了事件："+ value);
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
         * 03-15 22:19:45.579 32060-32060/com.seniorlibs.thirdlib D/ RxMergeActivity: 开始采用subscribe连接
         * 03-15 22:19:46.581 32060-32135/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：0
         * 03-15 22:19:46.584 32060-32137/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：3 (0、3是并行执行的)
         * 03-15 22:19:47.580 32060-32135/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：1
         * 03-15 22:19:47.581 32060-32137/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：4 (1、4是并行执行的)
         * 03-15 22:19:47.582 32060-32137/com.seniorlibs.thirdlib D/ RxMergeActivity: 对Complete事件作出响应
         */
    }

    /**
     * 组合多个被观察者一起发送数据，合并后按时间线并行执行，组合被观察者数量可>4个
     *
     * @param view
     */
    public void mergeArray(View view) {
        Observable.mergeArray(
                Observable.intervalRange(0, 2, 1, 1, TimeUnit.SECONDS), // 从0开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                Observable.intervalRange(3, 2, 1, 1, TimeUnit.SECONDS), // 从2开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                Observable.intervalRange(6, 2, 1, 1, TimeUnit.SECONDS), // 从3开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                Observable.intervalRange(9, 2, 1, 1, TimeUnit.SECONDS)) // 从4开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long value) {
                        LogUtils.d(TAG, "接收到了事件："+ value);
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
         * 03-15 22:20:05.290 32060-32060/com.seniorlibs.thirdlib D/ RxMergeActivity: 开始采用subscribe连接
         * 03-15 22:20:06.291 32060-32161/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：0
         * 03-15 22:20:06.291 32060-32162/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：3
         * 03-15 22:20:06.292 32060-32163/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：6
         * 03-15 22:20:06.294 32060-32164/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：9 (0、3、6、9是并行执行的)
         * 03-15 22:20:07.291 32060-32161/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：1
         * 03-15 22:20:07.291 32060-32162/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：4
         * 03-15 22:20:07.292 32060-32163/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：7
         * 03-15 22:20:07.294 32060-32164/com.seniorlibs.thirdlib D/ RxMergeActivity: 接收到了事件：10 (1、4、7、10是并行执行的)
         * 03-15 22:20:07.294 32060-32164/com.seniorlibs.thirdlib D/ RxMergeActivity: 对Complete事件作出响应
         */
    }
}
