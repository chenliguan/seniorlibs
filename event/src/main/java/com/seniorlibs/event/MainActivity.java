package com.seniorlibs.event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.seniorlibs.baselib.utils.LogUtils;
import com.seniorlibs.event.cancel.CancelActivity;
import com.seniorlibs.event.dispatch.DispatchActivity;
import com.seniorlibs.event.intercept.InterceptActivity;
import com.seniorlibs.event.listener.IBrowserListener;
import com.seniorlibs.event.listener.BrowserListenerHandle;

public class MainActivity extends Activity {

    private static final String TAG = "EventMainActivity";

    /**
     * 事件默认传递流程：(递归思想)
     *
     * // 点击：按下->抬起
     * Dispatch: DispatchActivity dispatchTouchEvent
     * Dispatch: MyViewGroupParent dispatchTouchEvent
     * Dispatch: MyViewGroupParent onInterceptTouchEv
     * Dispatch: MyViewGroup dispatchTouchEvent
     * Dispatch: MyViewGroup onInterceptTouchEvent
     * Dispatch: MyView dispatchTouchEvent
     * Dispatch: MyView onTouchEvent
     * Dispatch: MyViewGroup onTouchEvent
     * Dispatch: MyViewGroupParent onTouchEvent
     * Dispatch: DispatchActivity onTouchEvent
     * Dispatch: DispatchActivity dispatchTouchEvent
     * Dispatch: DispatchActivity onTouchEvent
     *
     * // 滑动：按下->滑动->抬起
     * +
     * DispatchActivity dispatchTouchEvent
     * DispatchActivity onTouchEvent
     * DispatchActivity dispatchTouchEvent
     * DispatchActivity onTouchEvent
     * DispatchActivity dispatchTouchEvent
     * DispatchActivity onTouchEvent
     * ......
     *
     * 总结：同一个事件序列，如果子View (ViewGroup) 没有处理该事件(没有消费事件)，那么同一事件序列的后续事件就不会再传递到子View中。
     * 重复这2步：DispatchActivity: dispatchTouchEvent -> DispatchActivity: onTouchEvent
     */
    private BrowserListenerHandle mBlHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        initBsListener();
    }

    /**
     * 调用以处理触摸屏事件。您可以重写此方法，以在将所有触摸屏事件发送到窗口之前对其进行拦截。对于触摸屏事件，请务必调用此实现应该正常处理。
     *
     * @param ev 触摸屏事件。
     * @return boolean 如果消耗了此事件，则返回true。
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    private void initData() {

    }

    private void initView() {

    }

    /**
     * 初始化全局监听器接口处理类
     */
    private void initBsListener() {
        if (mBlHandle == null) {
            mBlHandle = new BrowserListenerHandle(new IBrowserListener() {
                @Override
                public void openUrl(String url) {
                    LogUtils.e(TAG, "openUrl：" + url);
                }

                @Override
                public String getJs() {
                    return "getJsGetJs";
                }

                @Override
                public void gotoBookShelf() {
                    LogUtils.e(TAG, "gotoBookShelf");
                }

                @Override
                public void share(String title, String url, String description, int shareType, String imageUrl) {
                    LogUtils.e(TAG, "share + title：" + title + " url：" + url + " description：" + description + " shareType：" + shareType + " imageUrl：" + imageUrl);
                }
            });
        }
    }

    /**
     * 基本按钮监听
     *
     * @param v
     */
    public void onButtonClick(View v) {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

    /**
     * 滑动冲突场景1-外部拦截
     *
     * @param v
     */
    public void onOutIntercept(View v) {
        Intent intent = new Intent(this, OutInterceptActivityActivity.class);
        startActivity(intent);
    }

    /**
     * 滑动冲突场景2-内部拦截
     *
     * @param v
     */
    public void onInternalIntercept(View v) {
        Intent intent = new Intent(this, InInterceptActivity.class);
        startActivity(intent);
    }

    /**
     * 简单的拦截
     *
     * @param v
     */
    public void onIntercept(View v) {
        Intent intent = new Intent(this, InterceptActivity.class);
        startActivity(intent);
    }

    /**
     * 全局监听器接口处理
     *
     * @param v
     */
    public void onBsListenerClick(View v) {
        if (BrowserListenerHandle.getBrowserListener() != null) {
            BrowserListenerHandle.getBrowserListener().openUrl("https://blog.csdn.net/xxfen_/article/details/80361939");

            LogUtils.e(TAG, "BsListenerHandle.getBsListener().gotoBookShelf()：" + BrowserListenerHandle.getBrowserListener().getJs());
        } else {
            LogUtils.e(TAG, "BsListenerHandle.getBsListener()：" + BrowserListenerHandle.getBrowserListener());
        }
    }

    /**
     * 事件分发
     *
     * @param v
     */
    public void onDispatchClick(View v) {
        Intent intent = new Intent(this, DispatchActivity.class);
        startActivity(intent);
    }

    /**
     * 验证cancel事件
     *
     * @param v
     */
    public void onCancelClick(View v) {
        Intent intent = new Intent(this, CancelActivity.class);
        startActivity(intent);
    }
}
