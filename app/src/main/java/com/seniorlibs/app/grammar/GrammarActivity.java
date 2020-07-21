package com.seniorlibs.app.grammar;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.seniorlibs.app.R;

public class GrammarActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);

        Father father = new Son();
        father.talking();
    }
}
