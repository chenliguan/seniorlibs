package com.seniorlibs.app.grammar;

import android.app.Activity;
import android.os.Bundle;

import com.seniorlibs.app.R;

public class GrammarActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);

        Father father=new Son();
        father.talking();
    }
}
