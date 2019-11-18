package com.seniorlibs.binder.customaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Author: 陈李冠
 * Version: 1.0
 * Date: 2019/11/17.
 * Mender:
 * Modify:
 * Description: 服务端---服务类
 */
public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    private IBinder iBinder = new MyAidlManagerImpl() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
        @Override
        public int add(int num1, int num2) throws RemoteException {
            Log.i("TAG", "从客户端发来的AIDL请求:num1->" + num1 + "::num2->" + num2);
            return num1 + num2;
        }
    };
}
