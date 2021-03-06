package com.seniorlibs.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;

import com.seniorlibs.view.R;
import com.seniorlibs.view.recycledview.LayoutActivity;
import com.seniorlibs.view.remoteviews.RemoteViewsMainActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RemoteViewsMainActivity";

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        asyncLayoutInflater();
    }

    /**
     * 异步加载布局
     */
    private void asyncLayoutInflater() {
        new AsyncLayoutInflater(this).inflate(R.layout.activity_main, null, new AsyncLayoutInflater.OnInflateFinishedListener() {
            @Override
            public void onInflateFinished(@NonNull View view, int i, @Nullable ViewGroup viewGroup) {
                setContentView(view);
                mButton = findViewById(R.id.button1);
            }
        });
    }

    /**
     * View基础-测试测量结果
     *
     * @param v
     */
    public void button1(View v) {
        startActivity(new Intent(this, TestActivity.class));
    }

    /**
     * 继承于View和特定的ViewGroup(RelativeLayout)
     *
     * @param v
     */
    public void button2(View v) {
        startActivity(new Intent(this, CustomViewActivity.class));
    }

    /**
     * 继承于ViewGroup派生特殊Layout-HorizontalScrollViewEx
     *
     * @param v
     */
    public void button3(View v) {
        startActivity(new Intent(this, ScrollViewExActivity.class));
    }

    /**
     * RemoteViews
     *
     * @param v
     */
    public void button4(View v) {
        startActivity(new Intent(this, RemoteViewsMainActivity.class));
    }

    /**
     * App页面置灰
     *
     * @param v
     */
    public void button5(View v) {
        startActivity(new Intent(this, PutAshActivity.class));
    }

    /**
     * TabLayout + ViewPager + Fragment
     *
     * @param v
     */
    public void tabVPFragActivity(View v) {
        startActivity(new Intent(this, TabVPFragActivity.class));
    }

    /**
     * TabLayout + Fragment + FragmentTransaction hide/show
     *
     * @param v
     */
    public void tabAddFrgActivity(View v) {
        startActivity(new Intent(this, TabAddFrgActivity.class));
    }

    /**
     * RecyclerView 缓存使用测试
     *
     * @param v
     */
    public void layoutActivity(View v) {
        startActivity(new Intent(this, LayoutActivity.class));
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
