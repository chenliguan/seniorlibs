package com.seniorlibs.lifecycle.singletask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.seniorlibs.lifecycle.R;

public class ActivityB extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        Button button = (Button) findViewById(R.id.btn_click);
        button.setText("1：我是ActivityB，跳转ActivityD（对应后台栈顶）");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ActivityB.this, ActivityD.class);
                startActivity(intent);
            }
        });

        Button button1 = (Button) findViewById(R.id.btn_click1);
        button1.setText("2：我是ActivityB，跳转ActivityC（对应后台栈底）");
        button1.setVisibility(View.VISIBLE);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ActivityB.this, ActivityC.class);
                startActivity(intent);
            }
        });
    }
}
