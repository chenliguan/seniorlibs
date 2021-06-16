package com.seniorlibs;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.seniorlibs.app.R;
import com.seniorlibs.baselib.utils.LogUtils;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/1/11
 * Mender:
 * Modify:
 * Description: 测试 Activity 在各个 Task 中间转移的特性
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ReparentActivity";

    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        v = findViewById(R.id.tv_click);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("AutoTrackHelper", "MainActivity点击");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
