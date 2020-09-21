package com.seniorlibs.event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.seniorlibs.baselib.utils.LogUtils;
import com.seniorlibs.event.dispatch.DispatchActivity;
import com.seniorlibs.event.listener.IBrowserListener;
import com.seniorlibs.event.listener.BrowserListenerHandle;

public class MainActivity extends Activity {

    private static final String TAG = "EventMainActivity";

    private BrowserListenerHandle mBlHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        initBsListener();
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
        if (v.getId() == R.id.button1) {
            Intent intent = new Intent(this, TestActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.button2) {
            Intent intent = new Intent(this, DemoActivity_1.class);
            startActivity(intent);
        } else if (v.getId() == R.id.button3) {
            Intent intent = new Intent(this, DemoActivity_2.class);
            startActivity(intent);
        }
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
}
