package com.read.kotlinlib.memory.listener;

import java.util.ArrayList;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/2/28.
 * Mender:
 * Modify:
 * Description: 模拟内存泄露
 */
public class CallBackManager {

    public static ArrayList<CallBack> sCallBacks = new ArrayList<>();

    public static void addCallBack(CallBack callBack) {
        sCallBacks.add(callBack);
    }

    public static void removeCallBack(CallBack callBack) {
        sCallBacks.remove(callBack);
    }
}
