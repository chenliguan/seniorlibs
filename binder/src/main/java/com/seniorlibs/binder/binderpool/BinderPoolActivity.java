package com.seniorlibs.binder.binderpool;

import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.seniorlibs.binder.R;
import com.seniorlibs.binder.aidl.ICompute;
import com.seniorlibs.binder.aidl.ISecurityCenter;

public class BinderPoolActivity extends Activity {

    public static final String TAG = "TAG-BinderPoolActivity";

    private ISecurityCenter mSecurityCenter;
    private ICompute mCompute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_pool);
        new Thread(new Runnable() {

            @Override
            public void run() {
                doWork();
            }
        }).start();
    }

    private void doWork() {
        BinderPool binderPool = BinderPool.getInsance(BinderPoolActivity.this);
        IBinder securityBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER);
        mSecurityCenter = SecurityCenterImpl.asInterface(securityBinder);

        Log.d(TAG, "visit ISecurityCenter + mSecurityCenter: " + mSecurityCenter.toString());
        String msg = "helloworld-安卓";
        Log.d(TAG, "content:" + msg);
        try {
            String password = mSecurityCenter.encrypt(msg);
            Log.d(TAG, "encrypt:" + password);
            Log.d(TAG, "decrypt:" + mSecurityCenter.decrypt(password));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "visit ICompute + mSecurityCenter: " + mSecurityCenter.toString());
        IBinder computeBinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
        mCompute = ComputeImpl.asInterface(computeBinder);
        try {
            Log.d(TAG, "3 + 5 = " + mCompute.add(3, 5));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
