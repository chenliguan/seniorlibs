package com.seniorlibs.thirdlib;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "ThirdLib + MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRxJava = findViewById(R.id.btn_rx_java);
        btnRxJava.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rx_java:
                RxJavaActivity.actionStart(MainActivity.this);
                break;
            default:
                break;
        }
    }

}
