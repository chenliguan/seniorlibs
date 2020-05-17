package com.seniorlibs.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

/**
 * 如果有背景色，走onDraw()方法；如果没有背景色，不走onDraw()方法
 */
public class MyViewGroup extends ViewGroup {
    private static final String TAG = "MyViewGroup";
    public MyViewGroup(Context context) {
        super(context);
    }
    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw:执行了");
    }
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.d(TAG, "dispatchDraw:执行了");
    }
}
