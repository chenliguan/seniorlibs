package com.seniorlibs.thirdlib.eventbus;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.seniorlibs.baselib.utils.LogUtils;
import com.seniorlibs.thirdlib.R;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/5/24
 * Mender:
 * Modify:
 * Description: EventBus测试
 */
public class EventBusActivity extends AppCompatActivity {

    private static final String TAG = "ThirdLib + EventBusActivity";

    public static void actionStart(Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, EventBusActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
    }

    /**
     * 发布普通事件
     *
     * @param view
     */
    public void normalPost(View view) {
        EventBus.getDefault().post(new MessageEvent("normalPost：Hello everyone!"));
    }

    /**
     * 发布粘性事件
     *
     * @param view
     */
    public void postSticky(View view) {
//        粘性事件，在发送事件之后再订阅该事件也能收到。并且，粘性事件会保存在内存中，每次进入都会去内存中查找获取最新的粘性事件，除非你手动解除注册。
        EventBus.getDefault().postSticky(new MessageEvent("postSticky：Hello everyone!"));
    }

    /**
     * 手动获取和删除粘性事件
     *
     * @param view
     */
    public void removeStickyEvent(View view) {
        MessageEvent stickyEvent = EventBus.getDefault().getStickyEvent(MessageEvent.class);
        if(stickyEvent != null) {
            EventBus.getDefault().removeStickyEvent(stickyEvent);
        }
    }

    /**
     * 一个在订阅者排除异常时报错的EventBus发布事件
     *
     * @param view
     */
    public void throwSubscriberException(View view) {
        EventBus eventBus = EventBus.builder().throwSubscriberException(true).installDefaultEventBus();
        eventBus.post(new MessageEvent("throwSubscriberException：Hello everyone!"));
    }

    /**
     * 建一个当发送的事件没有订阅者时不报异常的EventBus
     *
     * @param view
     */
    public void noEvent(View view) {
        EventBus eventBus = EventBus.builder()
                .logNoSubscriberMessages(false)
                .sendNoSubscriberEvent(false)
                .build();
        eventBus.post(new MessageEvent("noEvent：Hello everyone!"));
    }

    /**
     * 注册
     *
     * @param view
     */
    public void register(View view) {
        EventBus.getDefault().register(this);
    }

    /**
     * 反注册
     *
     * @param view
     */
    public void unRegister(View view) {
        EventBus.getDefault().unregister(this);
    }

    /**
     * 当MessageEvent被发布时将调用此方法
     *
     * 默认情况下，EventBus捕获从订阅者方法抛出的异常，并发送不强制要求处理的SubscriberExceptionEvent。
     * Caused by: EventBusException: Subscriber class ...EventBusActivity and its super classes have no public methods with the @Subscribe annotation
     *
     * @param event
     */
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        LogUtils.d(TAG, "接收到EventBus事件：" + event.message);
    }


    /**
     * EventBus 是为已经存在的activity传递消息，而且订阅者必须要注册且不能被注销了，
     * 如果你在onStop里面注销了，栈中虽然有这个activity，但是EventBus并没有被注册，所以也接收不到消息，
     */
//    @Override
//    public void onStop() {
//        EventBus.getDefault().unregister(this);
//        super.onStop();
//    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
