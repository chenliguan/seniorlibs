package com.seniorlibs.thirdlib.rxjava;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.seniorlibs.baselib.utils.LogUtils;
import com.seniorlibs.thirdlib.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/3/15
 * Mender:
 * Modify: https://www.jianshu.com/p/954426f90325
 * Description: 条件操作符
 */
public class RxConditionsActivity extends AppCompatActivity {

    private static final String TAG = " RxConditionsActivity";

    public static void actionStart(Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, RxConditionsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_conditions);
    }

    /**
     * 判断发送的每项数据是否都满足设置的函数条件
     * 若满足返回true；否则返回false
     *
     * @param view
     */
    public void all(View view) {
        Observable.just(1, 2, 3, 4, 5, 6)
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        // 该函数用于判断Observable发送的10个数据是否都满足integer<=10
                        return (integer <= 10);
                    }
                }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                LogUtils.d(TAG, "result is " + aBoolean);
            }
        });

        /**
         * 因为所有数据都满足函数内条件 （每项数据<=10）
         * 03-22 18:15:07.017 25021-25021/com.seniorlibs.thirdlib D/ RxConditionsActivity: result is true
         */
    }

    /**
     * 判断发送的每项数据是否满足 设置函数条件
     * 若发送的数据满足该条件，则发送该项数据；否则不发送
     *
     * @param view
     */
    public void takeWhile(View view) {
        // 1. 每1s发送1个数据 = 从0开始，递增1，即0、1、2、3
        Observable.interval(1, TimeUnit.SECONDS)
                // 2. 通过takeWhile传入一个判断条件
                .takeWhile(new Predicate<Long>() {
                    @Override
                    public boolean test(Long integer) throws Exception {
                        // 当发送的数据满足<3时，才发送Observable的数据
                        return (integer < 3);
                    }
                }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                LogUtils.d(TAG, "发送了事件 " + aLong);
            }
        });

        /**
         * 03-22 18:19:18.587 26947-27108/com.seniorlibs.thirdlib D/ RxConditionsActivity: 发送了事件 0
         * 03-22 18:19:19.587 26947-27108/com.seniorlibs.thirdlib D/ RxConditionsActivity: 发送了事件 1
         * 03-22 18:19:20.587 26947-27108/com.seniorlibs.thirdlib D/ RxConditionsActivity: 发送了事件 2
         */
    }

    /**
     * 判断发送的每项数据是否满足 设置函数条件
     * 直到该判断条件 = false时，才开始发送Observable的数据
     *
     * @param view
     */
    public void skipWhile(View view) {
        Observable.interval(1, TimeUnit.SECONDS)
                // 通过skipWhile（）设置判断条件
                .skipWhile(new Predicate<Long>() {
                    @Override
                    public boolean test(Long integer) throws Exception {
                        // 直到判断条件不成立 = false = 发射的数据≥5，才开始发送数据
                        return (integer < 5);
                    }
                }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                LogUtils.d(TAG, "发送了事件 " + aLong);
            }
        });

        /**
         * 03-22 18:22:36.050 27407-27492/com.seniorlibs.thirdlib D/ RxConditionsActivity: 发送了事件 5
         * 03-22 18:22:37.049 27407-27492/com.seniorlibs.thirdlib D/ RxConditionsActivity: 发送了事件 6
         * 03-22 18:22:38.049 27407-27492/com.seniorlibs.thirdlib D/ RxConditionsActivity: 发送了事件 7
         * 03-22 18:22:39.049 27407-27492/com.seniorlibs.thirdlib D/ RxConditionsActivity: 发送了事件 8
         * 03-22 18:22:40.049 27407-27492/com.seniorlibs.thirdlib D/ RxConditionsActivity: 发送了事件 9
         * 03-22 18:22:41.049 27407-27492/com.seniorlibs.thirdlib D/ RxConditionsActivity: 发送了事件 10
         * ......
         */
    }

    /**
     * 执行到某个条件时，停止发送事件
     *
     * @param view
     */
    public void takeUntil(View view) {
        // 1. 每1s发送1个数据 = 从0开始，递增1，即0、1、2、3、4
        Observable.interval(1, TimeUnit.SECONDS)
                // 2. 通过takeUntil的Predicate传入判断条件
                .takeUntil(new Predicate<Long>() {
                    @Override
                    public boolean test(Long integer) throws Exception {
                        // 返回true时，就停止发送事件
                        // 当发送的数据满足>3时，就停止发送Observable的数据
                        return (integer > 3);
                    }
                }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Long value) {
                LogUtils.d(TAG, "发送了事件 " + value);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });

        /**
         * 03-22 18:25:57.007 27737-27858/com.seniorlibs.thirdlib D/ RxConditionsActivity: 发送了事件 0
         * 03-22 18:25:58.006 27737-27858/com.seniorlibs.thirdlib D/ RxConditionsActivity: 发送了事件 1
         * 03-22 18:25:59.006 27737-27858/com.seniorlibs.thirdlib D/ RxConditionsActivity: 发送了事件 2
         * 03-22 18:26:00.006 27737-27858/com.seniorlibs.thirdlib D/ RxConditionsActivity: 发送了事件 3
         * 03-22 18:26:01.006 27737-27858/com.seniorlibs.thirdlib D/ RxConditionsActivity: 发送了事件 4
         */

        // （原始）第1个Observable：每隔1s发送1个数据 = 从0开始，每次递增1
        Observable.interval(1, TimeUnit.SECONDS)
                // 第2个Observable：延迟5s后开始发送1个Long型数据
                .takeUntil(Observable.timer(4, TimeUnit.SECONDS))
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.e(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long value) {
                        LogUtils.e(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e(TAG, "对Complete事件作出响应");
                    }
                });

        /**
         * 当第 5s 时，第2个 Observable 开始发送数据，于是（原始）第1个 Observable 停止发送数据
         *
         * 03-22 18:25:57.007 27737-27858/com.seniorlibs.thirdlib D/ RxConditionsActivity: 开始采用subscribe连接
         * 03-22 18:25:57.007 27737-27858/com.seniorlibs.thirdlib D/ RxConditionsActivity: 发送了事件 0
         * 03-22 18:25:58.006 27737-27858/com.seniorlibs.thirdlib D/ RxConditionsActivity: 发送了事件 1
         * 03-22 18:25:59.006 27737-27858/com.seniorlibs.thirdlib D/ RxConditionsActivity: 发送了事件 2
         * 03-22 18:26:01.006 27737-27858/com.seniorlibs.thirdlib D/ RxConditionsActivity: 对Complete事件作出响应
         */
    }

    /**
     * 等到 skipUntil() 传入的Observable开始发送数据，（原始）第1个Observable的数据才开始发送数据
     *
     * @param view
     */
    public void skipUntil(View view) {
        // （原始）第1个Observable：每隔1s发送1个数据 = 从0开始，每次递增1
        Observable.interval(1, TimeUnit.SECONDS)
                // 第2个Observable：延迟5s后开始发送1个Long型数据
                .skipUntil(Observable.timer(5, TimeUnit.SECONDS))
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
         * 5s后，skipUntil()传入的Observable开始发送数据，（原始）第1个Observable的数据才开始发送
         *
         * 03-22 18:36:04.701 29492-29492/com.seniorlibs.thirdlib D/ RxConditionsActivity: 开始采用subscribe连接
         * 03-22 18:36:09.707 29492-29610/com.seniorlibs.thirdlib D/ RxConditionsActivity: 接收到了事件4 5s后
         * 03-22 18:36:10.707 29492-29610/com.seniorlibs.thirdlib D/ RxConditionsActivity: 接收到了事件5
         * 03-22 18:36:11.707 29492-29610/com.seniorlibs.thirdlib D/ RxConditionsActivity: 接收到了事件6
         * 03-22 18:36:12.707 29492-29610/com.seniorlibs.thirdlib D/ RxConditionsActivity: 接收到了事件7
         * 03-22 18:36:13.707 29492-29610/com.seniorlibs.thirdlib D/ RxConditionsActivity: 接收到了事件8
         * 03-22 18:36:14.707 29492-29610/com.seniorlibs.thirdlib D/ RxConditionsActivity: 接收到了事件9
         * 03-22 18:36:15.707 29492-29610/com.seniorlibs.thirdlib D/ RxConditionsActivity: 接收到了事件10
         * ......
         */
    }

    /**
     * 判定两个Observables需要发送的数据是否相同。若相同返回 true；否则返回 false
     *
     * @param view
     */
    public void sequenceEqual(View view) {
        Observable.sequenceEqual(
                Observable.just(4, 5, 6),
                Observable.just(4, 5, 6)
        )
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        LogUtils.d(TAG, "2个Observable是否相同：" + aBoolean);
                    }
                });

        /**
         * 03-22 18:39:19.168 29893-29893/com.seniorlibs.thirdlib D/ RxConditionsActivity: 2个Observable是否相同：true
         */
    }

    /**
     * 判断发送的数据中是否包含指定数据。若包含，返回 true；否则，返回 false
     * 内部实现 = exists()
     *
     * @param view
     */
    public void contains(View view) {
        Observable.just(1, 2, 3, 4, 5, 6)
                .contains(4)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        LogUtils.d(TAG, "result is " + aBoolean);
                    }
                });

        /**
         * 03-22 18:47:31.060 31929-31929/com.seniorlibs.thirdlib D/ RxConditionsActivity: result is true
         */
    }

    /**
     * 判断发送的数据是否为空。若为空，返回 true；否则，返回 false
     * 内部实现 = exists()
     *
     * @param view
     */
    public void isEmpty(View view) {
        Observable.just(1, 2, 3, 4, 5, 6)
                // 判断发送的数据中是否为空
                .isEmpty()
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        LogUtils.d(TAG, "result is " + aBoolean);
                    }
                });

        /**
         * 03-22 18:47:37.987 31929-31929/com.seniorlibs.thirdlib D/ RxConditionsActivity: result is false
         */
    }

    /**
     * 当需要发送多个 Observable时，只仅发送先发送数据的Observable的数据，而其余 Observable则被丢弃。
     *
     * @param view
     */
    public void amb(View view) {
        // 设置2个需要发送的Observable & 放入到集合中
        List<ObservableSource<Integer>> list = new ArrayList<>();
        // 第1个Observable延迟1秒发射数据
        list.add(Observable.just(1, 2, 3).delay(1, TimeUnit.SECONDS));
        // 第2个Observable正常发送数据
        list.add(Observable.just(4, 5, 6));

        // 一共需要发送2个Observable的数据，但由于使用了amb()，所以仅发送先发送数据的Observable。即第二个（因为第1个延时了）
        Observable.amb(list).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogUtils.d(TAG, "接收到了事件 " + integer);
            }
        });

        /**
         * 03-22 18:47:50.951 31929-31929/com.seniorlibs.thirdlib E/ RxConditionsActivity: 接收到了事件 4
         * 03-22 18:47:50.951 31929-31929/com.seniorlibs.thirdlib E/ RxConditionsActivity: 接收到了事件 5
         * 03-22 18:47:50.951 31929-31929/com.seniorlibs.thirdlib E/ RxConditionsActivity: 接收到了事件 6
         */
    }

    /**
     * 在不发送任何有效事件（ Next事件）、仅发送了 Complete 事件的前提下，发送一个默认值
     *
     * @param view
     */
    public void defaultIfEmpty(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // 不发送任何有效事件
                //  e.onNext(1);
                //  e.onNext(2);

                // 仅发送Complete事件
                e.onComplete();
            }

            // 若仅发送了Complete事件，默认发送值 = 10
        }).defaultIfEmpty(10)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.d(TAG, "接收到了事件"+ value  );
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
         * 03-22 18:48:31.359 31929-31929/com.seniorlibs.thirdlib D/ RxConditionsActivity: 开始采用subscribe连接
         * 03-22 18:48:31.359 31929-31929/com.seniorlibs.thirdlib D/ RxConditionsActivity: 接收到了事件10
         * 03-22 18:48:31.359 31929-31929/com.seniorlibs.thirdlib D/ RxConditionsActivity: 对Complete事件作出响应
         */
    }

}

