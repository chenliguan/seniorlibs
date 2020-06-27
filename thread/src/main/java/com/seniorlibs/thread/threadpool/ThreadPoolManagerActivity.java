package com.seniorlibs.thread.threadpool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.seniorlibs.thread.R;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/6/27
 * Mender:
 * Modify:
 * Description: 测试线程池页面
 */
public class ThreadPoolManagerActivity extends AppCompatActivity {

    public static final String TAG = "TagThreadPoolManagerActivity";

    public static void actionStart(Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, ThreadPoolManagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool_manager);
    }

    /**
     * 测试线程池的拒绝策略：为什么线程池容量不够抛出异常rejectedExecution？
     *
     * @param view
     */
    public void textRejectedExecution(View view) {
        ThreadPoolManagerTest.textRejectedExecution();
    }

    /**
     *
     *
     * @param view
     */
    public void flatMap(View view) {

    }

    /**
     *
     *
     * @param view
     */
    public void concatMap(View view) {

    }

    /**
     *
     *
     * @param view
     */
    public void buffer(View view) {

    }
}
