package com.seniorlibs.lifecycle.singletask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.seniorlibs.lifecycle.R;

public class ActivityA extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitya);

        Button button = (Button) findViewById(R.id.btn_click);
        button.setText("我是ActivityA，跳转ActivityC");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ActivityA.this, ActivityC.class);
                startActivity(intent);
            }
        });
    }
}
