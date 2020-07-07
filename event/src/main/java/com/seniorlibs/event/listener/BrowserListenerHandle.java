package com.seniorlibs.event.listener;

/**
 * Author: 陈李冠
 * Version: 1.28
 * Date: 2020/7/7.
 * Mender:
 * Modify:
 * Description: 全局监听器接口处理类
 */
public class BrowserListenerHandle {

    private static IBrowserListener mBrowserListener;

    public BrowserListenerHandle(IBrowserListener browserListener) {
        mBrowserListener = browserListener;
    }

    public static IBrowserListener getBrowserListener() {
        return mBrowserListener;
    }

    public void cancel() {
        mBrowserListener = null;
    }
}
