package com.seniorlibs.binder.aidl;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.seniorlibs.binder.R;

import java.util.List;

/**
 * 客户端
 */
public class BookManagerActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "TAG-BookManagerActivity";
    private static final int MSG_NEW_BOOK_ARRIVED = 1;
    private static final int MSG_GET_BOOK_RESULT = 2;

    private IBookManager mRemoteBookManager;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MSG_NEW_BOOK_ARRIVED) {
                Log.d(TAG, "接收到监听器的回调方法结果:" + msg.obj);
            } else if (msg.what == MSG_GET_BOOK_RESULT) {
                List<Book> list = (List<Book>) msg.obj;
                Log.i(TAG, "查询书籍列表内容:" + list.getClass().getCanonicalName() + " + book list:" + list.toString());
            } else {
                super.handleMessage(msg);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);

        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);

        // A1、绑定服务
        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * A2、服务回调方法
     */
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // A5、绑定成功后，将服务端返回的Binder对象转成AIDL接口所属的类型对象mRemoteBookManager，接着就可以调用AIDL中的方法了
            mRemoteBookManager = IBookManager.Stub.asInterface(service);

            try {
                // E3、在客户端绑定远程服务成功之后，给Binder设置一个死亡代理
                mRemoteBookManager.asBinder().linkToDeath(mDeathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            mRemoteBookManager = null;

            // E1、解除绑定Binder方法二：在onServiceDisconnected()中重新绑定远程Service
            // TODO:解除绑定，重新绑定远程Service
        }
    };

    /**
     * A6、客户端调用远程服务的方法，AIDL中mRemoteBookManager的方法了，以下分别是:getBookList()和addBook()
     *
     * 注意：被调用远程服务的方法运行在服务端的Binder线程池中，同时客户端线程会被挂起，如果服务端方法执行比较耗时，就会导致客户端线程长时间地阻塞，而如果这个客户端线程是UI线程的话，就会导致客户端ANR。
     * 所以，需要指定AIDL的方法在子线程中执行，如果要访问UI需要使用Handler切换到UI线程去执行。
     * @param v
     */
    @Override
    public void onClick(final View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (mRemoteBookManager == null || mHandler == null) {
                    return;
                }

                if (v.getId() == R.id.button1) {
                    try {
                        List<Book> list = mRemoteBookManager.getBookList();
                        // 切换到UI线程去执行：获取书籍列表内容
                        mHandler.obtainMessage(MSG_GET_BOOK_RESULT, list).sendToTarget();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else if (v.getId() == R.id.button2) {
                    try {
                        Book newBook = new Book(3, "Android进阶");
                        Log.i(TAG, "添加书籍内容:" + newBook);
                        mRemoteBookManager.addBook(newBook);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else if (v.getId() == R.id.button3)  {
                    try {
                        // B3、注册服务端回调客户端的IOnNewBookArrivedListener监听器
                        mRemoteBookManager.registerListener(mOnNewBookArrivedListener);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * B5、添加IOnNewBookArrivedListener.Stub中registerListener()监听器的回调方法，服务端调用此方法后直接回调
     */
    private IOnNewBookArrivedListener mOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {

        @Override
        public void onNewBookArrived(Book newBook) {
            // B5、此方法是在（客户端的）Binder线程池中执行，因此要访问UI需要使用Handler切换到UI线程去执行
            mHandler.obtainMessage(MSG_NEW_BOOK_ARRIVED, newBook).sendToTarget();
        }
    };

    /**
     * E1、解除绑定Binder方法一：给Binder设置Deathleciient监听
     * Deathleciient是一个接口，其内部只有一个方法binderDied，当Binder死亡的时候，系统就会回调binderDied方法
     */
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            // E2、当Binder死亡时，我们就会收到通知，这个时候我们就可以重新绑定远程Service从而恢复连接
            Log.d(TAG, "已经解除绑定Binder的name:" + Thread.currentThread().getName());
            if (mRemoteBookManager == null) {
                return;
            }

            // E2.1、解除绑定Binder
            mRemoteBookManager.asBinder().unlinkToDeath(mDeathRecipient, 0);
            mRemoteBookManager = null;

            // E2.2、TODO:这里重新绑定远程Service
        }
    };

    @Override
    protected void onDestroy() {
        // D3、解除注册监听器，isBinderAlive判断Binder是否死亡
        if (mRemoteBookManager != null && mRemoteBookManager.asBinder().isBinderAlive()) {
            try {
                Log.i(TAG, "解除注册监听器:" + mOnNewBookArrivedListener);
                mRemoteBookManager.unregisterListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        // A7、解绑服务，回收资源
        unbindService(mConnection);
        super.onDestroy();
    }
}
