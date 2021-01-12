package com.seniorlibs.lifecycle.singletask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.seniorlibs.lifecycle.R;

public class ActivityC extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        Button button = (Button) findViewById(R.id.btn_click);
        button.setText("我是ActivityC，跳转ActivityD");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ActivityC.this, ActivityD.class);
                startActivity(intent);
            }
        });
    }
}
