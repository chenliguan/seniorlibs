package com.seniorlibs.algorithm.linkedlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.seniorlibs.algorithm.R;
import com.seniorlibs.baselib.utils.LogUtils;

/**
 * 链表测试
 */
public class LinkedActivity extends AppCompatActivity {

    private static final String TAG = "LinkedActivity";

    public static void actionStart(Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, LinkedActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linked);
        initView();
        initData();
    }

    private void initView() {

    }

    private void initData() {
        // Handler的message复用原理
        message();
    }

    /**
     * Handler的message复用原理
     */
    private void message() {
        Msg msg = new Msg();
        msg.sPool = msg;
        msg.next = new Msg();

        Msg m = msg.sPool;
        LogUtils.d(TAG, "1m：" + m);
        LogUtils.d(TAG, "1sPool：" + msg.sPool);
        LogUtils.d(TAG, "1m.next：" + m.next);

        msg.sPool = m.next;
        LogUtils.e(TAG, "2m：" + m);
        LogUtils.e(TAG, "2sPool：" + msg.sPool);
        LogUtils.e(TAG, "2m.next：" + m.next);

        m.next = null;
        LogUtils.d(TAG, "3m：" + m);
        LogUtils.d(TAG, "3sPool：" + msg.sPool);
        LogUtils.d(TAG, "3m.next：" + m.next);

//        08-09 17:24:15.933 13151-13151/? D/LinkedActivity: 1m：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@29f28fdb
//        08-09 17:24:15.933 13151-13151/? D/LinkedActivity: 1sPool：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@29f28fdb
//        08-09 17:24:15.933 13151-13151/? D/LinkedActivity: 1m.next：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@227e2a78
//        08-09 17:24:15.933 13151-13151/? E/LinkedActivity: 2m：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@29f28fdb
//        08-09 17:24:15.933 13151-13151/? E/LinkedActivity: 2sPool：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@227e2a78
//        08-09 17:24:15.933 13151-13151/? E/LinkedActivity: 2m.next：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@227e2a78
//        08-09 17:24:15.933 13151-13151/? D/LinkedActivity: 3m：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@29f28fdb
//        08-09 17:24:15.933 13151-13151/? D/LinkedActivity: 3sPool：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@227e2a78
//        08-09 17:24:15.933 13151-13151/? D/LinkedActivity: 3m.next：null

        m.next = msg.sPool;
        LogUtils.e(TAG, "4m：" + m);
        LogUtils.e(TAG, "4sPool：" + msg.sPool);
        LogUtils.e(TAG, "4m.next：" + m.next);

        msg.sPool = m;
        LogUtils.d(TAG, "5m：" + m);
        LogUtils.d(TAG, "5sPool：" + msg.sPool);
        LogUtils.d(TAG, "5m.next：" + m.next);

//        08-09 17:24:15.933 13151-13151/? E/LinkedActivity: 4m：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@29f28fdb
//        08-09 17:24:15.933 13151-13151/? E/LinkedActivity: 4sPool：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@227e2a78
//        08-09 17:24:15.934 13151-13151/? E/LinkedActivity: 4m.next：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@227e2a78
//        08-09 17:24:15.934 13151-13151/? D/LinkedActivity: 5m：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@29f28fdb
//        08-09 17:24:15.934 13151-13151/? D/LinkedActivity: 5sPool：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@29f28fdb
//        08-09 17:24:15.934 13151-13151/? D/LinkedActivity: 5m.next：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@227e2a78
    }

    public class Msg {
        public Msg next;
        public Msg sPool;
    }
}
