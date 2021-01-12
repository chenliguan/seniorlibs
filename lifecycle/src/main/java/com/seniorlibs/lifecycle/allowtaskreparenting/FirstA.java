package com.seniorlibs.lifecycle.allowtaskreparenting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.seniorlibs.lifecycle.R;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/1/11
 * Mender:
 * Modify:
 * Description: 测试 Activity 在各个 Task 中间转移的特性
 */
public class FirstA extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button button = findViewById(R.id.btn_click);
        button.setText("我是FirstA，跳转FirstB");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FirstA.this, FirstB.class);
                startActivity(intent);
            }
        });
    }
}
