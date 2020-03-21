package com.seniorlibs.thirdlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.seniorlibs.thirdlib.retrofit.GankActivity;
import com.seniorlibs.thirdlib.rxjava.RxCreateActivity;
import com.seniorlibs.thirdlib.rxjava.RxJavaActivity;
import com.seniorlibs.thirdlib.rxjava.RxMapActivity;
import com.seniorlibs.thirdlib.rxjava.RxMergeActivity;
import com.seniorlibs.thirdlib.rxjava.RxTestActivity;

import org.openjdk.jol.info.ClassLayout;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/3/15
 * Mender:
 * Modify:
 * Description: 第三方库测试
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ThirdLib + MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 测试RxJava
     *
     * @param view
     */
    public void textRxJava(View view) {
//        RxTestActivity.actionStart(this);
        RxMergeActivity.actionStart(this);
    }

    /**
     * 测试Retrofit
     *
     * @param view
     */
    public void textRetrofit(View view) {
        GankActivity.actionStart(this);
    }

    /**
     * 测试看内存布局 TODO ???
     *
     * @param view
     */
    public void textClassLayout(View view) {
        Object lock = new Object();
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }
}
