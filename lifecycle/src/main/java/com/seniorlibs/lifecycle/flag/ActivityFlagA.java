package com.seniorlibs.lifecycle.flag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.seniorlibs.lifecycle.R;
import com.seniorlibs.lifecycle.singletask.ActivityC;

public class ActivityFlagA extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitya);

        Log.e("ActivityFlagA-Flag", "onCreate");

        Button button = (Button) findViewById(R.id.btn_click);
        button.setText("我是ActivityFlagA，跳转ActivityFlagB");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setClass(ActivityFlagA.this, ActivityFlagB.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Log.e("ActivityFlagA-Flag", "onNewIntent");
    }
}
