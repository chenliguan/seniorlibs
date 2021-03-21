package com.seniorlibs.view.remoteviews;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.seniorlibs.view.R;

/**
 * 接受测试PendingIntent的flags参数
 */
public class ReceivePendingIntentFlagsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_pending_intent_flags);

        TextView tvReceive = findViewById(R.id.tv_receive);
        tvReceive.setText(getIntent().getStringExtra("name"));
    }
}
