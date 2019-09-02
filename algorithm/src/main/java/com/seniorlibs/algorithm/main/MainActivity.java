package com.seniorlibs.algorithm.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.seniorlibs.algorithm.R;
import com.seniorlibs.algorithm.stack.getmin.IStackGetMin;
import com.seniorlibs.algorithm.stack.getmin.StackGetMinOfficialA;
import com.seniorlibs.algorithm.stack.getmin.StackGetMinOfficialB;

/**
 * 主页
 */
public class MainActivity extends Activity implements View.OnClickListener {

    public final static String TAG = "main_activity_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        Button btnStackGetMin = findViewById(R.id.btn_stack_get_min);
        btnStackGetMin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_stack_get_min:
                IStackGetMin<Integer> stackGetMin = new StackGetMinOfficialA();
                stackGetMin.addData();
                Log.d(TAG, "getMin功能获取的最小的值：" + stackGetMin.getMin());
                break;
            default:
                break;
        }
    }
}
