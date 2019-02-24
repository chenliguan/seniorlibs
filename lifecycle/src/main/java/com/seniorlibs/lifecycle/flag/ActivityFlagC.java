package com.seniorlibs.lifecycle.flag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.seniorlibs.lifecycle.R;
import com.seniorlibs.lifecycle.singletask.ActivityC;

public class ActivityFlagC extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitya);

        Button button = (Button) findViewById(R.id.btn_click);
        button.setText("我是ActivityFlagC，跳转ActivityFlagA");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setClass(ActivityFlagC.this, ActivityFlagA.class);
                startActivity(intent);
            }
        });
    }
}
