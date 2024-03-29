package com.seniorlibs.lifecycle.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.seniorlibs.lifecycle.R;
import com.seniorlibs.lifecycle.allowtaskreparenting.FirstA;
import com.seniorlibs.lifecycle.flag.ActivityFlagA;
import com.seniorlibs.lifecycle.service.ServiceTestActivity;
import com.seniorlibs.lifecycle.singletask.ActivityA;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            String test = savedInstanceState.getString("extra_test");
            Log.d(TAG, "[onCreate]restore extra_test:" + test);
        }

        initEvent();
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                // Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.setAction(Intent.ACTION_VIEW);
                intent.setAction("com.ryg.charpter_1.c");
                intent.putExtra("time", System.currentTimeMillis());
                intent.addCategory("com.ryg.category.c");
                intent.setDataAndType(Uri.parse("file://abc"), "text/plain");
                startActivity(intent);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setText("测试singleTask，从下一个ActivityA开始，现在跳转ActivityA");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ActivityA.class);
                startActivity(intent);
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setText("测试标志位，从下一个ActivityFlagA开始，现在跳转ActivityFlagA");
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ActivityFlagA.class);
                startActivity(intent);
            }
        });

        Button btnService = findViewById(R.id.btn_service);
        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ServiceTestActivity.class);
                startActivity(intent);
            }
        });

        Button btnAllowTaskReparenting = findViewById(R.id.btn_allowTaskReparenting);
        btnAllowTaskReparenting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, FirstA.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent, time=" + intent.getLongExtra("time", 0));
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        super.onStart();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged, newOrientation:" + newConfig.orientation);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String content = "test";
        Log.d(TAG, "onSaveInstanceState extra_test:" + content);
        outState.putString("extra_test", content);
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Log.d(TAG, "onRestoreInstanceState");
        String test = savedInstanceState.getString("extra_test");
        Log.d(TAG, "[onRestoreInstanceState]restore extra_test:" + test);
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }
    
    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }
    
    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }
}
