package com.seniorlibs.ipc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.seniorlibs.ipc.aidl.Book;
import com.seniorlibs.ipc.manager.UserManager;
import com.seniorlibs.ipc.model.User;

import java.io.Serializable;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SecondActivity.class);
                User user = new User(0, "jake", true);
                user.book = new Book();
                intent.putExtra("extra_user", (Serializable) user);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        UserManager.sUserId = 2;
        Log.d(TAG, "UserManager.sUserId=" + UserManager.sUserId);

//        persistToFile();
    }
}
