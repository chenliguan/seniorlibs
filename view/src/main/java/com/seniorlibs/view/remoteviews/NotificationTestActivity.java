package com.seniorlibs.view.remoteviews;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.seniorlibs.view.R;

public class NotificationTestActivity extends Activity implements OnClickListener {

    private static final String TAG = "NotificationActivity";

    private Button mBtnDefault;
    private Button mBtnUpdateDefault;
    private Button mBtnProgress;
    private Button mBtnCustom;
    private Button mBtnTestPendingIntentFlags;

    private int mProgress = 0;

    private static int NOTIFICATION_DEFAULT_ID = 101;
    private static int NOTIFICATION_CUSTOM_ID = 102;
    private static int NOTIFICATION_PROGRESS_ID = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_test);
        initView();
    }

    private void initView() {
        mBtnDefault = findViewById(R.id.btn_default);
        mBtnDefault.setOnClickListener(this);
        mBtnUpdateDefault = findViewById(R.id.btn_update_default);
        mBtnUpdateDefault.setOnClickListener(this);
        mBtnProgress = findViewById(R.id.btn_progress);
        mBtnProgress.setOnClickListener(this);
        mBtnCustom = findViewById(R.id.btn_custom);
        mBtnCustom.setOnClickListener(this);
        mBtnTestPendingIntentFlags = findViewById(R.id.btn_test_pending_intent_flags);
        mBtnTestPendingIntentFlags.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onClick(View v) {
        if (v == mBtnDefault) {
            sendDefaultNotification();

        } else if (v == mBtnUpdateDefault) {
            updateDefaultNotification();

        } else if (v == mBtnProgress) {
            sendProgressNotification();

        } else if (v == mBtnCustom) {
            sendCustomNotification();
        } else if (v == mBtnTestPendingIntentFlags) {
            testPendingIntentFlags();
        }
    }

    /**
     * 发送默认通知
     */
    private void sendDefaultNotification() {
        // 1、获取NotificationManager通知管理类
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        /**
         * 2、使用Notification.Builder构建器构建Notification对象，必要属性有三项
         *  小图标，通过setSmallIcon()方法设置
         *  标题，通过setContentTitle()方法设置
         *  内容，通过setContentText()方法设置
         */
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Notification练习");
        builder.setContentText("this is notification.");
        builder.setTicker("滚动消息......");
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
        builder.setWhen(System.currentTimeMillis());
        builder.setOngoing(true);
//        // 设置通知可以自动取消：builder.setAutoCancel(true)
//        notification.flags = Notification.FLAG_AUTO_CANCEL;
//        // 让通知常驻通知栏：builder.setOngoing(true)
//        notification.flags = Notification.FLAG_NO_CLEAR;

        // 设置点击通知执行意图
        Intent intent = new Intent(this, DemoActivity_2.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(pendingIntent);

        // Android 8.0不能弹出通知解决方法
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "001";
            NotificationChannel channel = new NotificationChannel(channelId, "my_channel", NotificationManager.IMPORTANCE_DEFAULT);
            // 是否在桌面icon右上角展示小红点
            channel.enableLights(true);
            // 小红点颜色
            channel.setLightColor(Color.GREEN);
            // 是否在久按桌面图标时显示此渠道的通知
            channel.setShowBadge(true);
            manager.createNotificationChannel(channel);

            // Notification.Builder需要多设置一个
            builder.setChannelId(channelId);
        }

        Notification notification = builder.build();
        // 3、调用manager.notify()发出通知
        manager.notify(NOTIFICATION_DEFAULT_ID, notification);
    }

    /**
     * 更新通知界面内容
     */
    private void updateDefaultNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("音乐下载")
                .setContentText("下载进度:" + mProgress + "%")
                .setSmallIcon(R.drawable.ic_launcher);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String channelId = "002";
            NotificationChannel channel = new NotificationChannel(channelId, "download_channel", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }
        Notification notification = builder.build();
        manager.notify(NOTIFICATION_DEFAULT_ID, notification);
        mProgress += 10;
    }

    /**
     * 实现进度条的通知
     */
    private void sendProgressNotification() {
        final NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        final Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("进度")
                .setContentText("进度...")
                .setProgress(100, 10, true);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String channelId = "003";
            NotificationChannel channel = new NotificationChannel(channelId, "download_channel", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }

        Notification notification = builder.build();
        manager.notify(NOTIFICATION_PROGRESS_ID, notification);
        // 每隔1秒更新进度条进度，启动工作线程
        new Thread() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        for (int i = 1; i <= 10; i++) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            // 发通知：带有进度条
                            builder.setProgress(100, i * 10, false);
                            Notification n = builder.build();
                            manager.notify(NOTIFICATION_PROGRESS_ID, n);
                        }

                        // 更新通知内容
                        manager.cancel(NOTIFICATION_PROGRESS_ID);  // 清除通知
                        builder.setProgress(0, 0, false); // 取消进度条
                        builder.setContentText("音乐下载完毕");
                        Notification n = builder.build();
                        manager.notify(NOTIFICATION_PROGRESS_ID, n);
                    }
                }, 2000);
            }
        }.start();
    }

    /**
     * 发送自定义通知
     */
    private void sendCustomNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setTicker("hello world");
        builder.setWhen(System.currentTimeMillis());
        builder.setOngoing(true);

        // 设置整个通知栏点击通知执行意图
        Intent intent = new Intent(this, DemoActivity_1.class);
        intent.putExtra("sid", "" + NOTIFICATION_CUSTOM_ID);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        System.out.println(pendingIntent);
        builder.setContentIntent(pendingIntent);

        // 给RemoteViews中的TextView设置文本内容
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);
        remoteViews.setTextViewText(R.id.msg, "自定义通知: " + NOTIFICATION_CUSTOM_ID);
        remoteViews.setImageViewResource(R.id.icon, R.drawable.icon1);
        // 给RemoteViews中的TextView添加点击事件，TextView添加点击意图
        PendingIntent openActivity2PendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, DemoActivity_2.class), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.open_activity2, openActivity2PendingIntent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setCustomContentView(remoteViews);
        } else {
            builder.setContent(remoteViews);
        }

        // Android 8.0不能弹出通知解决方法
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "005";
            NotificationChannel channel = new NotificationChannel(channelId, "download_channel", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }

        Notification notification = builder.build();
        manager.notify(NOTIFICATION_CUSTOM_ID, notification);
    }

    /**
     * 测试PendingIntent的flags参数
     */
    private void testPendingIntentFlags() {
        Intent intentA = new Intent(this, ReceivePendingIntentFlagsActivity.class);
        intentA.putExtra("name", "AAA");
        Intent intentB = new Intent(this, ReceivePendingIntentFlagsActivity.class);
        intentB.putExtra("name", "BBB");
        PendingIntent p1 = PendingIntent.getActivity(this, 0, intentA, PendingIntent.FLAG_ONE_SHOT);
        PendingIntent p2 = PendingIntent.getActivity(this, 0, intentB, PendingIntent.FLAG_ONE_SHOT);

        Log.e(TAG, "两个intent是否相等（equals）" + intentA.equals(intentB));//false
        Log.e(TAG, "两个PendingIntent是否相等：" + p1.equals(p2)); // true
        Log.e(TAG, "两个intent是否相等（filterEquals）：" + intentA.filterEquals(intentB)); // true

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder1 = new Notification.Builder(this);
        builder1.setContentTitle("title");
        builder1.setContentText("Content Text");
        builder1.setSmallIcon(R.drawable.ic_launcher);
        builder1.setOngoing(true);
        builder1.setContentIntent(p1);
        // Android 8.0不能弹出通知解决方法
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "006";
            NotificationChannel channel = new NotificationChannel(channelId, "download_channel", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
            builder1.setChannelId(channelId);
        }
        Notification notification1 = builder1.build();
        manager.notify(1, notification1);

        Notification.Builder builder2 = new Notification.Builder(this);
        builder2.setContentTitle("title2");
        builder2.setContentText("Content Text2");
        builder2.setSmallIcon(R.drawable.ic_yellow);
        builder2.setOngoing(true);
        builder2.setContentIntent(p2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "007";
            NotificationChannel channel = new NotificationChannel(channelId, "download_channel", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
            builder2.setChannelId(channelId);
        }
        Notification notification2 = builder2.build();
        manager.notify(2, notification2);
    }

    /**
     * 清除通知
     */
    private void clearNotification() {
        // 单例的系统服务
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(NOTIFICATION_DEFAULT_ID);
    }
}
