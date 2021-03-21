package com.seniorlibs.view.remoteviews;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.seniorlibs.view.R;

public class DemoActivity_1 extends AppCompatActivity {
    private static final String TAG = "DemoActivity_1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_1);
        initView();
    }

    private void initView() {
        Toast.makeText(this, getIntent().getStringExtra("sid"), Toast.LENGTH_SHORT).show();
    }
}
