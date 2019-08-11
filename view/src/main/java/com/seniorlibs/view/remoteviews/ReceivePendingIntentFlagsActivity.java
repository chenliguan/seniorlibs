package com.seniorlibs.view.remoteviews;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.seniorlibs.view.R;

/**
 * 接受测试PendingIntent的flags参数
 */
public class ReceivePendingIntentFlagsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_pending_intent_flags);

        TextView tvReceive = findViewById(R.id.tv_receive);
        tvReceive.setText(getIntent().getStringExtra("name"));
    }
}
