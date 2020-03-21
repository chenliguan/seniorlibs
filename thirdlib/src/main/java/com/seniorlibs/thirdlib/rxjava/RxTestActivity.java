package com.seniorlibs.thirdlib.rxjava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.seniorlibs.thirdlib.R;
import com.seniorlibs.thirdlib.retrofit.GankActivity;

import org.openjdk.jol.info.ClassLayout;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/3/15
 * Mender:
 * Modify:
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
     * rxCreate
     *
     * @param view
     */
    public void rxCreate(View view) {
        RxCreateActivity.actionStart(this);
    }

    /**
     * rxConditions
     *
     * @param view
     */
    public void rxConditions(View view) {
        RxConditionsActivity.actionStart(this);
    }
}
