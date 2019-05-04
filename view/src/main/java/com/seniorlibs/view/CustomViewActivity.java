package com.seniorlibs.view;

import android.app.Activity;
import android.os.Bundle;

/**
 * 测试自定义view
 */
public class CustomViewActivity extends Activity {

    private static final String TAG = "CustomViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        initView();
    }

    private void initView() {

    }
}
