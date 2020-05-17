package com.seniorlibs.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.seniorlibs.view.R;
import com.seniorlibs.view.remoteviews.RemoteViewsMainActivity;

public class MainActivity extends Activity {
    private static final String TAG = "RemoteViewsMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * View基础-测试测量结果
     *
     * @param v
     */
    public void button1(View v) {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

    /**
     * 继承于View和特定的ViewGroup(RelativeLayout)
     *
     * @param v
     */
    public void button2(View v) {
        Intent intent = new Intent(this, CustomViewActivity.class);
        startActivity(intent);
    }

    /**
     * 继承于ViewGroup派生特殊Layout-HorizontalScrollViewEx
     *
     * @param v
     */
    public void button3(View v) {
        Intent intent = new Intent(this, ScrollViewExActivity.class);
        startActivity(intent);
    }

    /**
     * RemoteViews
     *
     * @param v
     */
    public void button4(View v) {
        Intent intent = new Intent(this, RemoteViewsMainActivity.class);
        startActivity(intent);
    }

    /**
     * App页面置灰
     *
     * @param v
     */
    public void button5(View v) {
        Intent intent = new Intent(this, PutAshActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.d(TAG, "onWindowFocusChanged, focus:" + hasFocus);
        super.onWindowFocusChanged(hasFocus);
    }
}
