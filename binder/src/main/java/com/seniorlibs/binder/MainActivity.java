package com.seniorlibs.binder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.seniorlibs.binder.aidl.Book;
import com.seniorlibs.binder.aidl.BookManagerActivity;
import com.seniorlibs.binder.binderpool.BinderPoolActivity;
import com.seniorlibs.binder.customaidl.MyAidlManagerActivity;
import com.seniorlibs.binder.file.SecondActivity;
import com.seniorlibs.binder.manager.UserManager;
import com.seniorlibs.binder.messenger.MessengerActivity;
import com.seniorlibs.binder.model.User;
import com.seniorlibs.binder.provider.ProviderActivity;
import com.seniorlibs.binder.socket.TCPClientActivity;
import com.seniorlibs.binder.utils.MyConstants;
import com.seniorlibs.binder.utils.MyUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserManager.sUserId = 2;
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(MyUtils.TAG, "UserManage.sUserId=" + UserManager.sUserId);
        persistToFile();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Intent intent1 = new Intent();
                intent1.setClass(MainActivity.this, SecondActivity.class);
                User user = new User(0, "jake", true);
                user.book = new Book();
                intent1.putExtra("extra_user", (Serializable) user);
                startActivity(intent1);
                break;
            case R.id.button2:
                Intent intent2 = new Intent();
                intent2.setClass(MainActivity.this, MessengerActivity.class);
                startActivity(intent2);
                break;
            case R.id.button3:
                Intent intent3 = new Intent();
                intent3.setClass(MainActivity.this, BookManagerActivity.class);
                startActivity(intent3);
                break;
            case R.id.button4:
                Intent intent4 = new Intent();
                intent4.setClass(MainActivity.this, MyAidlManagerActivity.class);
                startActivity(intent4);
            case R.id.button5:
                Intent intent5 = new Intent();
                intent5.setClass(MainActivity.this, ProviderActivity.class);
                startActivity(intent5);
                break;
            case R.id.button6:
                Intent intent6 = new Intent();
                intent6.setClass(MainActivity.this, TCPClientActivity.class);
                startActivity(intent6);
                break;
            case R.id.button7:
                Intent intent7 = new Intent();
                intent7.setClass(MainActivity.this, BinderPoolActivity.class);
                startActivity(intent7);
                break;
            case R.id.button8:
                Intent intent8 = new Intent();
                // 传递Bean中的数据
                intent8.putExtra("bean", new Bean());
                intent8.setClass(MainActivity.this, BinderPoolActivity.class);
                startActivity(intent8);
                break;
            default:
                break;
        }
    }

    static class Bean implements Serializable {
        private transient byte[] data = new byte[ 1024* 1024];
        String str = "data string";
    }

    /**
     * 文件共享,把对象写入到文件中
     */
    private void persistToFile() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                User user = new User(1, "hello world", false);
                File dir = new File(MyConstants.CHAPTER_2_PATH);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File cachedFile = new File(MyConstants.CACHE_FILE_PATH);
                ObjectOutputStream objectOutputStream = null;
                try {
                    objectOutputStream = new ObjectOutputStream(new FileOutputStream(cachedFile));
                    objectOutputStream.writeObject(user);
                    Log.d(MyUtils.TAG, "persist user:" + user);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    MyUtils.close(objectOutputStream);
                }
            }
        }).start();
    }
}
