package com.read.javalib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.read.javalib.generic.GenericTest;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/4/1
 * Mender:
 * Modify:
 * Description: java测试
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "javalib + MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 泛型测试
     *
     * @param view
     */
    public void textGeneric(View view) {
        GenericTest genericTest = new GenericTest();
        genericTest.genericErasure();
    }
}
