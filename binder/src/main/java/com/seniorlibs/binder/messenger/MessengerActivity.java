package com.seniorlibs.binder.messenger;

import com.seniorlibs.binder.R;
import com.seniorlibs.binder.utils.MyConstants;
import com.seniorlibs.binder.utils.MyUtils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/**
 * Messenger在进程间传递数据，是一种轻量级的IPC方案，它的底层实现是AIDL
 * 通过Messenger来传递Message，Message中能使用的载体就只有what、arg1、arg2、Bundle以及replyTo
 *
 * 客户端进程
 */
public class MessengerActivity extends Activity {

    private Messenger mGetReplyMessenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        // A1、绑定服务端的Service
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        // B2、创建一个新的Messenger
        mGetReplyMessenger = new Messenger(new MessengerHandler());
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.d(MyUtils.TAG, "bind service");

            // A2、绑定成功后用服务端返回的IBinder对象创建一个Messenger
            Messenger mServiceMessenger = new Messenger(service);
            Message message = Message.obtain(null, MyConstants.MSG_FROM_CLIENT);
            Bundle data = new Bundle();
            data.putString("msg", "hello, this is client.");
            message.setData(data);
            // B3、把这个Messenger对象通过Message的replyTo参数传递给服务端，服务端通过这个replyTo参数就可以回应客户端
            message.replyTo = mGetReplyMessenger;
            try {
                // A3、通过这个Messenger就可以向服务端发送消息了，发消息类型为Message对象
                mServiceMessenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {

        }
    };

    /**
     * B1、如果需要服务端能够回应客户端，就和服务端一样，我们还需要创建一个Handdler
     */
    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MyConstants.MSG_FROM_SERVICE) {
                Log.i(MyUtils.TAG, "receive msg from Service:" + msg.getData().getString("reply"));
            } else {
                super.handleMessage(msg);
            }
        }
    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }
}
