package com.seniorlibs.thirdlib.eventbus;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/5/24
 * Mender:
 * Modify:
 * Description: 定义事件
 */
public class MessageEvent {

    public final String message;

    public MessageEvent(String message) {
        this.message = message;
    }
}
