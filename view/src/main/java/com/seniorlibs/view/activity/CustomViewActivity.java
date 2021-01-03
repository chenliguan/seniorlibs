package com.seniorlibs.view.activity;

import android.app.Activity;
import android.os.Bundle;

import com.seniorlibs.view.R;
import com.seniorlibs.view.widget.PieImageView;

/**
 * 测试自定义view
 */
public class CustomViewActivity extends Activity {

    private static final String TAG = "CustomViewActivity";

    private PieImageView pieImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        initView();
    }

    private void initView() {
        pieImageView = findViewById(R.id.pie_image_view);
        pieImageView.setProgress(45);
    }
}
