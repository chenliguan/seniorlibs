package com.seniorlibs.thirdlib;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.Process;
import android.os.SystemClock;
import android.os.Trace;
import android.util.Log;
import android.util.Printer;
import android.view.View;

import com.seniorlibs.baselib.utils.LogUtils;
import com.seniorlibs.thirdlib.eventbus.EventBusActivity;
import com.seniorlibs.thirdlib.mmkv.SpUtils;
import com.seniorlibs.thirdlib.okhttp.OkHttpActivity;
import com.seniorlibs.thirdlib.retrofit.GankActivity;
import com.seniorlibs.thirdlib.rxjava.RxTestActivity;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: chen
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

        initPermission();
    }

    /**
     * 申请权限
     */
    private void initPermission() {
        // 可以调用trackInstallation()方法记录激活事件，多次调用此方法只会在第一次调用时触发激活事件
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> permissions = new ArrayList<>();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }

            int size = permissions.size();
            if (size > 0) {
                // 6.0 以上，无权限时，先申请权限。
                ActivityCompat.requestPermissions(this, permissions.toArray(new String[size]), 100);
            } else {
                // 6.0 以上，有权限时，直接触发激活事件。
                trackInstallation();
            }

        } else {
            // 6.0 以下，无须申请权限，直接触发激活事件。
            trackInstallation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            // 申请权限结果回调时（无论申请权限成功失败），都需要调用trackInstallation()触发激活事件
            trackInstallation();
        }
    }

    /**
     * 记录激活事件
     */
    private void trackInstallation() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试RxJava
     *
     * @param view
     */
    public void testRxJava(View view) {
        RxTestActivity.actionStart(this);
    }

    /**
     * 测试OkHttp
     *
     * @param view
     */
    public void testOkHttp(View view) {
        OkHttpActivity.actionStart(this);
    }

    /**
     * 测试Retrofit
     *
     * @param view
     */
    public void testRetrofit(View view) {
        GankActivity.actionStart(this);
    }

    /**
     * 测试看内存布局 TODO ???
     *
     * @param view
     */
    public void testClassLayout(View view) {
        Object lock = new Object();
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }

    /**
     * 测试EventBus
     *
     * @param view
     */
    public void testEventBus(View view) {
        EventBusActivity.actionStart(this);
    }

    /**
     * 测试Mmkv
     *
     * @param view
     */
    public void testMmkv(View view) {
        SpUtils.getInstance().write("int-key1", 1000000);
        SpUtils.getInstance().write("int-key11", 10000001);
        LogUtils.d("testMmkv", "MMKV + int-key1：" + SpUtils.getInstance().readInt("int-key1"));
        LogUtils.d("testMmkv", "MMKV + int-key11：" + SpUtils.getInstance().readInt("int-key11"));

        // 添加/更新数据
        SpUtils.getInstance().getMKv().encode("String-key1", "value");
        SpUtils.Companion.getBaseKv().encode("String-key11", "value11");
        // 获取数据
        LogUtils.d("testMmkv", "MMKV + decodeString1：" + SpUtils.getInstance().getMKv().decodeString("String-key1"));
        LogUtils.d("testMmkv", "MMKV + decodeString11：" + SpUtils.Companion.getBaseKv().decodeString("String-key11"));
        // 删除数据
        SpUtils.getInstance().getMKv().removeValueForKey("String-key1");
        SpUtils.Companion.getBaseKv().removeValueForKey("String-key11");
        // 获取数据
        LogUtils.d("testMmkv", "MMKV + decodeString2：" + SpUtils.getInstance().getMKv().decodeString("String-key1"));
        LogUtils.d("testMmkv", "MMKV + decodeString22：" + SpUtils.Companion.getBaseKv().decodeString("String-key11"));
    }
}
