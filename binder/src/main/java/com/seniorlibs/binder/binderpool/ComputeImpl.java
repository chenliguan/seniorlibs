package com.seniorlibs.binder.binderpool;

import android.os.RemoteException;

import com.seniorlibs.binder.aidl.ICompute;

public class ComputeImpl extends ICompute.Stub {

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

}
