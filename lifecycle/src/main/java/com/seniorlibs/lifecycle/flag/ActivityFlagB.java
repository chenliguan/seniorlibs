package com.seniorlibs.lifecycle.flag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.seniorlibs.lifecycle.R;
import com.seniorlibs.lifecycle.singletask.ActivityC;

public class ActivityFlagB extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitya);

        Button button = (Button) findViewById(R.id.btn_click);
        button.setText("我是ActivityFlagB，跳转ActivityFlagC");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setClass(ActivityFlagB.this, ActivityFlagC.class);
                startActivity(intent);
            }
        });
    }
}
