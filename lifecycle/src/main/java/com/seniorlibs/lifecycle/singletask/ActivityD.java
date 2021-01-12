package com.seniorlibs.lifecycle.singletask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.seniorlibs.lifecycle.R;

public class ActivityD extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        Button button = (Button) findViewById(R.id.btn_click);
        button.setText("我是ActivityD，跳转ActivityB");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ActivityD.this, ActivityB.class);
                startActivity(intent);
            }
        });
    }
}
