package com.seniorlibs.thirdlib.rxjava.schedulers;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;

/**
 * Enables injection of production implementations for
 */
public class InjectionUtils {

    /**
     * 提供RxJava调度器
     *
     * @return RxJava调度器
     */
    public static BaseSchedulerProvider getScheduler() {
        return SchedulerProvider.getInstance();
    }

    /**
     * 调度器转换
     * @param <T> 泛型
     * @return 观察者转换器
     */
    public static <T> ObservableTransformer<T, T> schedulersTransformer() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(getScheduler().io())
                        .unsubscribeOn(getScheduler().io())
                        .observeOn(getScheduler().ui());
            }
        };
    }

    /**
     * UI调度器转换
     * @param <T> 泛型
     * @return 观察者转换器
     */
    public static <T> ObservableTransformer<T, T> schedulersUI() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(getScheduler().ui())
                        .unsubscribeOn(getScheduler().ui())
                        .observeOn(getScheduler().ui());
            }
        };
    }

    public static <T> SingleTransformer<T, T> singleTransformer() {
        return new SingleTransformer<T, T>(){
            @Override
            public SingleSource<T> apply(Single<T> upstream) {
                return upstream.subscribeOn(getScheduler().io())
                        .unsubscribeOn(getScheduler().io())
                        .observeOn(getScheduler().ui());
            }
        };
    }

    /**
     * 下载线程调度器转换
     * @param <T> 泛型
     * @return 观察者转换器
     */
    public static <T> ObservableTransformer<T, T> downloadTransformer() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(getScheduler().download())
                        .unsubscribeOn(getScheduler().download())
                        .observeOn(getScheduler().ui());
            }
        };
    }

}
