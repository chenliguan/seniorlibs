package com.seniorlibs.thirdlib.rxjava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.seniorlibs.baselib.utils.LogUtils;
import com.seniorlibs.thirdlib.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/3/15
 * Mender:
 * Modify: https://www.jianshu.com/p/904c14d253ba
 * Description: 变换操作符，对事件序列中的事件/整个事件序列进行加工处理（即变换），使得其转变成不同的事件/整个事件序列
 */
public class RxMapActivity extends AppCompatActivity {

    private static final String TAG = " RxMapActivity";

    public static void actionStart(Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, RxMapActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_map);
    }

    /**
     * 对被观察者发送的每1个事件都通过 指定的函数 处理，从而变换成另外一种事件
     * <p>
     * 场景：数据类型转换
     *
     * @param view
     */
    public void map(View view) {
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
                return "使用 Map变换操作符 将事件" + integer + "的参数从 整型：" + integer + " 变换成 字符串类型：" + (integer + "s");
            }
        }).subscribe(new Consumer<String>() {
            // 3. 观察者接收事件时，是接收到变换后的事件 = 字符串类型
            @Override
            public void accept(String s) throws Exception {
                LogUtils.d(TAG, "接收到了事件：" + s);
            }
        });

        /**
         * 03-15 21:36:57.467 27042-27042/com.seniorlibs.thirdlib D/ RxMapActivity: 接收到了事件：使用 Map变换操作符 将事件1的参数从 整型：1 变换成 字符串类型：1s
         * 03-15 21:36:57.467 27042-27042/com.seniorlibs.thirdlib D/ RxMapActivity: 接收到了事件：使用 Map变换操作符 将事件2的参数从 整型：2 变换成 字符串类型：2s
         * 03-15 21:36:57.467 27042-27042/com.seniorlibs.thirdlib D/ RxMapActivity: 接收到了事件：使用 Map变换操作符 将事件3的参数从 整型：3 变换成 字符串类型：3s
         */
    }

    /**
     * 将被观察者发送的事件序列进行 拆分 & 单独转换，再合并成一个新的事件序列，最后再进行发送
     * 原理：
     * （1）将事件序列进行拆分：为事件序列中的每个事件都创建一个Observable对象；
     * （2）转换事件：拆分后的事件经历的转换形式相同；
     * （3）合并成为新的事件序列：将每个Observable都合并到一个新的总的Observable对象；
     * <p>
     * （4）将新合并的事件序列Observable对象发送给观察者（Observer）
     * <p>
     * 场景：无序的将被观察者发送的整个事件序列进行变换
     *
     * @param view
     */
    public void flatMap(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }

        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("我是事件= " + integer + " , 拆分后的子事件= " + integer + "-->" + i);
                    // 通过flatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为1个新的发送3个String的事件
                    // 最终合并，将新合并的事件序列Observable对象发送给观察者（Observer）
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtils.d(TAG, "接收到了事件：" + s);
            }
        });

        /**
         * 03-15 21:39:45.589 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity: 接收到了事件：我是事件= 1 , 拆分后的子事件= 1-->0
         * 03-15 21:39:45.589 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity: 接收到了事件：我是事件= 1 , 拆分后的子事件= 1-->1
         * 03-15 21:39:45.589 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity: 接收到了事件：我是事件= 1 , 拆分后的子事件= 1-->2
         * 03-15 21:39:45.590 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity: 接收到了事件：我是事件= 2 , 拆分后的子事件= 2-->0
         * 03-15 21:39:45.590 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity: 接收到了事件：我是事件= 2 , 拆分后的子事件= 2-->1
         * 03-15 21:39:45.590 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity: 接收到了事件：我是事件= 2 , 拆分后的子事件= 2-->2
         * 03-15 21:39:45.590 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity: 接收到了事件：我是事件= 3 , 拆分后的子事件= 3-->0
         * 03-15 21:39:45.590 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity: 接收到了事件：我是事件= 3 , 拆分后的子事件= 3-->1
         * 03-15 21:39:45.590 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity: 接收到了事件：我是事件= 3 , 拆分后的子事件= 3-->2
         */
    }

    /**
     * 类似FlatMap()操作符。与FlatMap()的区别在于：拆分 & 重新合并生成的事件序列的顺序 = 被观察者旧序列生产的顺序
     * <p>
     * 场景：数据类型转换
     *
     * @param view
     */
    public void concatMap(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }

        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("我是事件= " + integer + " , 拆分后的子事件= " + integer + "-->" + i);
                    // 通过flatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为1个新的发送3个String的事件
                    // 最终合并，将新合并的事件序列Observable对象发送给观察者（Observer）
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtils.d(TAG, "接收到了事件：" + s);
            }
        });

        /**
         * 03-15 21:45:43.978 28389-28389/com.seniorlibs.thirdlib D/ RxMapActivity: 接收到了事件：我是事件= 1 , 拆分后的子事件= 1-->0
         * 03-15 21:45:43.978 28389-28389/com.seniorlibs.thirdlib D/ RxMapActivity: 接收到了事件：我是事件= 1 , 拆分后的子事件= 1-->1
         * 03-15 21:45:43.978 28389-28389/com.seniorlibs.thirdlib D/ RxMapActivity: 接收到了事件：我是事件= 1 , 拆分后的子事件= 1-->2
         * 03-15 21:45:43.979 28389-28389/com.seniorlibs.thirdlib D/ RxMapActivity: 接收到了事件：我是事件= 2 , 拆分后的子事件= 2-->0
         * 03-15 21:45:43.979 28389-28389/com.seniorlibs.thirdlib D/ RxMapActivity: 接收到了事件：我是事件= 2 , 拆分后的子事件= 2-->1
         * 03-15 21:45:43.979 28389-28389/com.seniorlibs.thirdlib D/ RxMapActivity: 接收到了事件：我是事件= 2 , 拆分后的子事件= 2-->2
         * 03-15 21:45:43.979 28389-28389/com.seniorlibs.thirdlib D/ RxMapActivity: 接收到了事件：我是事件= 3 , 拆分后的子事件= 3-->0
         * 03-15 21:45:43.979 28389-28389/com.seniorlibs.thirdlib D/ RxMapActivity: 接收到了事件：我是事件= 3 , 拆分后的子事件= 3-->1
         * 03-15 21:45:43.979 28389-28389/com.seniorlibs.thirdlib D/ RxMapActivity: 接收到了事件：我是事件= 3 , 拆分后的子事件= 3-->2
         */
    }

    /**
     * 定期从被观察者（Obervable）需要发送的事件中，获取一定数量的事件 & 放到缓存区中，最终发送
     * <p>
     * 场景：数据类型转换
     *
     * @param view
     */
    public void buffer(View view) {
        Observable.just(1, 2, 3, 4, 5)
                // 设置缓存区大小 & 步长：缓存区大小 = 每次从被观察者中获取的事件数量；步长 = 每次获取新事件的数量，相当于右移几位
                .buffer(3, 1)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(List<Integer> stringList) {
                        LogUtils.d(TAG, "缓存区里的事件数量 = " + stringList.size());
                        for (Integer value : stringList) {
                            LogUtils.d(TAG, " 接收到了事件：" + value);
                        }
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
         * 03-15 21:42:36.405 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity: 开始采用subscribe连接
         * 03-15 21:42:36.406 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity: 缓存区里的事件数量 = 3
         * 03-15 21:42:36.406 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity:  接收到了事件：1
         * 03-15 21:42:36.406 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity:  接收到了事件：2
         * 03-15 21:42:36.406 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity:  接收到了事件：3
         * 03-15 21:42:36.406 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity: 缓存区里的事件数量 = 3
         * 03-15 21:42:36.406 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity:  接收到了事件：2
         * 03-15 21:42:36.406 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity:  接收到了事件：3
         * 03-15 21:42:36.406 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity:  接收到了事件：4
         * 03-15 21:42:36.406 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity: 缓存区里的事件数量 = 3
         * 03-15 21:42:36.406 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity:  接收到了事件：3
         * 03-15 21:42:36.407 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity:  接收到了事件：4
         * 03-15 21:42:36.407 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity:  接收到了事件：5
         * 03-15 21:42:36.407 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity: 缓存区里的事件数量 = 2
         * 03-15 21:42:36.407 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity:  接收到了事件：4
         * 03-15 21:42:36.407 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity:  接收到了事件：5
         * 03-15 21:42:36.407 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity: 缓存区里的事件数量 = 1
         * 03-15 21:42:36.407 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity:  接收到了事件：5
         * 03-15 21:42:36.407 27840-27840/com.seniorlibs.thirdlib D/ RxMapActivity: 对Complete事件作出响应
         */
    }
}
