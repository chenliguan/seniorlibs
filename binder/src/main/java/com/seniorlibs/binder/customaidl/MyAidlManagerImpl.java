package com.seniorlibs.binder.customaidl;

import android.os.Binder;

/**
 * Author: chen
 * Version: 1.0
 * Date: 2019/11/17.
 * Mender:
 * Modify:
 * Description: IInterface接口实现类
 */
public abstract class MyAidlManagerImpl extends Binder implements IMyAidlManager {

    public MyAidlManagerImpl() {
        this.attachInterface(this, DESCRIPTOR);
    }

    /**
     * 将服务端的Binder对象转换成客户端所需的AIDL接口类型的对象，obj是当前服务端的Binder对象
     */
    public static IMyAidlManager asInterface(android.os.IBinder obj) {
        if ((obj == null)) {
            return null;
        }

        // 如果客户端和服务端位于同一进程，那么此方法返回的就是服务端的对象本身，下面方法转换
        android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (((iin != null) && (iin instanceof IMyAidlManager))) {
            return ((IMyAidlManager) iin);
        }
        // 如果客户端和服务端位不位于同一进程，返回的是封装后的MyAidlManagerImpl.Proxy(obj)
        return new MyAidlManagerImpl.Proxy(obj);
    }

    /**
     * 当前服务端的Binder对象
     */
    @Override
    public android.os.IBinder asBinder() {
        return this;
    }

    /**
     * 运行在服务端的Binder线程池中，当客户端发起跨进程请求时，远程请求会通过系统底层封装后交由此方法来处理
     */
    @Override
    public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_basicTypes: {
                data.enforceInterface(DESCRIPTOR);
                int _arg0;
                _arg0 = data.readInt();
                long _arg1;
                _arg1 = data.readLong();
                boolean _arg2;
                _arg2 = (0 != data.readInt());
                float _arg3;
                _arg3 = data.readFloat();
                double _arg4;
                _arg4 = data.readDouble();
                java.lang.String _arg5;
                _arg5 = data.readString();
                this.basicTypes(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
                reply.writeNoException();
                return true;
            }
            case TRANSACTION_add: {
                data.enforceInterface(DESCRIPTOR);
                int _arg0;
                _arg0 = data.readInt();
                int _arg1;
                _arg1 = data.readInt();
                int _result = this.add(_arg0, _arg1);
                reply.writeNoException();
                reply.writeInt(_result);
                return true;
            }
        }
        return super.onTransact(code, data, reply, flags);
    }

    /**
     * 客户端
     */
    private static class Proxy implements IMyAidlManager {
        private android.os.IBinder mRemote;

        Proxy(android.os.IBinder remote) {
            mRemote = remote;
        }

        @Override
        public android.os.IBinder asBinder() {
            return mRemote;
        }

        public java.lang.String getInterfaceDescriptor() {
            return DESCRIPTOR;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, java.lang.String aString) throws android.os.RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                _data.writeInt(anInt);
                _data.writeLong(aLong);
                _data.writeInt(((aBoolean) ? (1) : (0)));
                _data.writeFloat(aFloat);
                _data.writeDouble(aDouble);
                _data.writeString(aString);
                mRemote.transact(TRANSACTION_basicTypes, _data, _reply, 0);
                _reply.readException();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }

        @Override
        public int add(int num1, int num2) throws android.os.RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            int _result;
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                _data.writeInt(num1);
                _data.writeInt(num2);
                mRemote.transact(TRANSACTION_add, _data, _reply, 0);
                _reply.readException();
                _result = _reply.readInt();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
            return _result;
        }
    }
}
