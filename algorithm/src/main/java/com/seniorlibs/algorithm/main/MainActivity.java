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
import com.seniorlibs.algorithm.stack.twostacksqueue.ITwoStackQueue;
import com.seniorlibs.algorithm.stack.twostacksqueue.TwoStackQueueMy;
import com.seniorlibs.algorithm.stack.twostacksqueue.TwoStackQueueOfficial;

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
        findViewById(R.id.btn_stack_get_min).setOnClickListener(this);
        findViewById(R.id.two_stack_queue).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_stack_get_min:
                IStackGetMin<Integer> stackGetMin = new StackGetMinOfficialA();
                stackGetMin.addData();
                Log.d(TAG, "getMin功能获取的最小的值：" + stackGetMin.getMin());
                break;
            case R.id.two_stack_queue:
                ITwoStackQueue<Integer> twoStackQueue = new TwoStackQueueOfficial();
                twoStackQueue.addData();
                Log.d(TAG, "两个栈实现队列的接口 peek：" + twoStackQueue.peek());
                for (int i = 0; i < 10; i++) {
                    Log.d(TAG, "两个栈实现队列的接口 poll：" + i + " = " + twoStackQueue.poll());
                }
                break;
            default:
                break;
        }
    }
}
