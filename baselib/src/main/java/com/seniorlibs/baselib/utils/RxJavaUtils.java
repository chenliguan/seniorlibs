package com.seniorlibs.baselib.utils;

import io.reactivex.ObservableEmitter;
import io.reactivex.disposables.Disposable;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/12 0019.
 * Mender:
 * Modify:
 * Description: RxJava相关工具类
 */
public class RxJavaUtils {

    public static final String TAG = "RxJavaUtils";

    /**
     * RxJava 字段
     */
    public static final String MESSAGE_ON_ERROR = "onError";
    public static final String MESSAGE_INVALID = "invalid";
    public static final String MESSAGE_RETRY = "retry";
    public static final String MESSAGE_DISPOSED = "disposed";

    /**
     * Observable disposed回调
     *
     * @param emitter
     * @return
     */
    public static <T> boolean onErrorDispose(ObservableEmitter<T> emitter) {
        if (emitter.isDisposed()) {
            emitter.onError(new Exception(MESSAGE_DISPOSED));
            return true;
        }
        return false;
    }

    /**
     * Rx失败回调
     *
     * @param emitter
     * @return
     */
    public static <T> boolean onRxError(ObservableEmitter<T> emitter, String message) {
        if (onErrorDispose(emitter)) {
            return true;
        }

        emitter.onError(new Exception(message));
        return false;
    }

    /**
     * 销毁被观察者Subscribe
     */
    public static void removeSubscribe(Disposable subscribe) {
        if (subscribe != null) {
            subscribe.dispose();
        }
        LogUtils.e(TAG, "销毁被观察者Subscribe:" + subscribe);
    }

    /**
     * onNextComplete
     *
     * @param emitter
     * @param t
     * @param <T>
     */
    public static <T> void onNextComplete(ObservableEmitter<T> emitter, T t) {
        if (!emitter.isDisposed()) {
            emitter.onNext(t);
            emitter.onComplete();
        }
    }

    /**
     * onComplete
     *
     * @param emitter
     * @param <T>
     */
    public static <T> boolean onComplete(ObservableEmitter<T> emitter) {
        if (emitter.isDisposed()) {
            emitter.onComplete();
            return true;
        }
        return false;
    }


    /**
     * 添加AutoDispose解决RxJava内存泄漏，避免手动写RxJava的取消，更加优雅
     * https://blog.csdn.net/mq2553299/article/details/79418068
     * https://github.com/uber/AutoDispose
     */
//    public static <T> AutoDisposeConverter<T> bindLifecycle(LifecycleOwner lifecycleOwner) {
//        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner));
//    }
}
