package com.seniorlibs.thirdlib.rxjava;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.seniorlibs.thirdlib.R;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/3/15
 * Mender:
 * Modify:
 * Description: 条件操作符
 */
public class RxConditionsActivity extends AppCompatActivity {

    private static final String TAG = " RxConditionsActivity";

    public static void actionStart(Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, RxConditionsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_conditions);
    }

    /**
     * 判断发送的每项数据是否都满足设置的函数条件。若满足返回true；否则返回false
     *
     * @param view
     */
    public void all(View view) {

    }
}
