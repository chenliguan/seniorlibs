package com.seniorlibs.thirdlib.rxjava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.seniorlibs.thirdlib.R;
import com.seniorlibs.thirdlib.retrofit.GankActivity;

import org.openjdk.jol.info.ClassLayout;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/3/15
 * Mender:
 * Modify: https://www.jianshu.com/p/cd984dd5aae8
 * Description: RxJava测试
 */
public class RxTestActivity extends AppCompatActivity {

    private static final String TAG = "RxTestActivity";

    public static void actionStart(Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, RxTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_test);
    }

    /**
     * rxJava
     *
     * @param view
     */
    public void rxJava(View view) {
        RxJavaActivity.actionStart(this);
    }

    /**
     * 创建操作符：rxCreate
     *
     * @param view
     */
    public void rxCreate(View view) {
        RxCreateActivity.actionStart(this);
    }

    /**
     * 变换操作符：rxMap
     *
     * @param view
     */
    public void rxMap(View view) {
        RxMapActivity.actionStart(this);
    }

    /**
     * 组合/合并操作符：rxMerge
     *
     * @param view
     */
    public void rxMerge(View view) {
        RxMergeActivity.actionStart(this);
    }

    /**
     * 功能性操作符：rxFunction
     *
     * @param view
     */
    public void rxFunction(View view) {
        RxFunctionActivity.actionStart(this);
    }

    /**
     * 过滤操作符：rxFilter
     *
     * @param view
     */
    public void rxFilter(View view) {
        RxFilterActivity.actionStart(this);
    }

    /**
     * 条件/布尔操作符：rxConditions
     *
     * @param view
     */
    public void rxConditions(View view) {
        RxConditionsActivity.actionStart(this);
    }
}
