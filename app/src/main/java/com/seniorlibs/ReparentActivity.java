package com.seniorlibs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.seniorlibs.app.R;
import com.seniorlibs.baselib.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/1/11
 * Mender:
 * Modify:
 * Description: 测试 Activity 在各个 Task 中间转移的特性
 */
public class ReparentActivity extends AppCompatActivity {

    private static final String TAG = "ReparentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
