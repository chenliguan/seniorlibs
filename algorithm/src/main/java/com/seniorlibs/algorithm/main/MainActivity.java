package com.seniorlibs.algorithm.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.seniorlibs.algorithm.R;
import com.seniorlibs.algorithm.linked.LinkedActivity;
import com.seniorlibs.algorithm.stack.getmin.IStackGetMin;
import com.seniorlibs.algorithm.stack.getmin.StackGetMinOfficialA;
import com.seniorlibs.algorithm.stack.getmin.StackGetMinOfficialB;
import com.seniorlibs.algorithm.stack.twostacksqueue.ITwoStackQueue;
import com.seniorlibs.algorithm.stack.twostacksqueue.TwoStackQueueMy;
import com.seniorlibs.algorithm.stack.twostacksqueue.TwoStackQueueOfficial;
import com.seniorlibs.algorithm.string.anagram.AnagramMy;

/**
 * 主页
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
        findViewById(R.id.anagram_my).setOnClickListener(this);
        findViewById(R.id.btn_linked).setOnClickListener(this);
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
            case R.id.anagram_my:
                AnagramMy anagramMy = new AnagramMy();
                Log.d(TAG, "判断两个字符串是否互为变形词 anagramMy1：" + anagramMy.isAnagram("abcd", "abdc"));
                Log.d(TAG, "判断两个字符串是否互为变形词 anagramMy2：" + anagramMy.isAnagram("abcd", "acdb"));
                Log.d(TAG, "判断两个字符串是否互为变形词 anagramMy3：" + anagramMy.isAnagram("abcd", "abbd"));
                Log.d(TAG, "判断两个字符串是否互为变形词 anagramMy4：" + anagramMy.isAnagram("abcd", "axcd"));
                Log.d(TAG, "判断两个字符串是否互为变形词 anagramMy5：" + anagramMy.isAnagram("aaa", "aaaa"));
                break;
            case R.id.btn_linked:
                LinkedActivity.actionStart(MainActivity.this);
                break;
            default:
                break;
        }
    }
}
