package com.seniorlibs.binder.customaidl;

import android.os.IInterface;

/**
 * Author: chen
 * Version: 1.0
 * Date: 2019/11/17.
 * Mender:
 * Modify:
 * Description: IInterface接口
 */
public interface IMyAidlManager extends IInterface {

    public static final java.lang.String DESCRIPTOR = "com.seniorlibs.binder.customaidl.IMyAidlManager";

    // 在这个类里面我们会发现有2个标识：用来区分两个方法，到底你远程请求哪个方法的唯一标识，代码定位到代理类的结尾
    static final int TRANSACTION_basicTypes = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_add = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);

    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, java.lang.String aString) throws android.os.RemoteException;

    public int add(int num1, int num2) throws android.os.RemoteException;
}
