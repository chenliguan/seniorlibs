package com.seniorlibs.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.seniorlibs.view.R;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/11/27.
 * Mender:
 * Modify:
 * Description: onActivityResult()跳转后返回setResult
 */
public class SetResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_result);
    }

    /**
     * setResult
     *
     * @param v
     */
    public void setResult(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(TabVPFragActivity.INTENT_RESULT, "SetResultActivity");
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);

        finish();
    }
}
