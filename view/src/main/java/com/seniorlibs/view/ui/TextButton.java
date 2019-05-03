package com.seniorlibs.view.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextButton extends TextView {

    private static final String TAG = "TestButton";

    public TextButton(Context context) {
        this(context, null);
    }

    public TextButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

}
