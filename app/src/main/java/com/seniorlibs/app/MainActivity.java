package com.seniorlibs.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.seniorlibs.baselib.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

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
