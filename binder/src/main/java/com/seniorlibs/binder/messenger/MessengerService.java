package com.seniorlibs.binder.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.seniorlibs.binder.utils.MyConstants;
import com.seniorlibs.binder.utils.MyUtils;

/**
 * 服务端进程
 */
public class MessengerService extends Service {

    private Messenger mMessenger;

    @Override
    public void onCreate() {
        super.onCreate();

        // A1、创建一个Handler并通过它来创建一个Messenger对象
        mMessenger = new Messenger(new MessengerHandler());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(MyUtils.TAG, "onStartCommand flags:" + flags + " startId:" + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * A2、onBind中返回这个Messenger对象底层的Binder
     *
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(MyUtils.TAG, "onBind");
        return mMessenger.getBinder();
    }

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            if (message.what == MyConstants.MSG_FROM_CLIENT) {
                // A3、收到客户端的消息
                Log.i(MyUtils.TAG, "receive msg from Client:" + message.getData().getString("msg"));

                // B1、把这个Messenger对象通过Message的bundle参数回复给客户端
                Messenger client = message.replyTo;
                Message relpyMessage = Message.obtain(null, MyConstants.MSG_FROM_SERVICE);
                Bundle bundle = new Bundle();
                bundle.putString("reply", "嗯，你的消息我已经收到，稍后会回复你。");
                relpyMessage.setData(bundle);
                try {
                    client.send(relpyMessage);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                super.handleMessage(message);
            }
        }
    }
}
