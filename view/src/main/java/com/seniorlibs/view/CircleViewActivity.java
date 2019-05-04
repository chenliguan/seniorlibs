package com.seniorlibs.view;

import android.app.Activity;
import android.os.Bundle;

/**
 * 测试自定义view-CircleView
 */
public class CircleViewActivity extends Activity {

    private static final String TAG = "CircleViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_view);
        initView();
    }

    private void initView() {

    }
}
