package com.seniorlibs.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;

/**
 * 测试测量结果
 */
public class TestActivity extends Activity implements OnClickListener {

    private static final String TAG = "NotificationTestActivity";

    private Button view;
    private View mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
        beforeMeasureView();
        measureView();
    }

    private void beforeMeasureView() {
        Log.d(TAG, "beforeMeasureView measure width= " + mButton2.getMeasuredWidth() + " height= " + mButton2.getMeasuredHeight());
        Log.d(TAG, "beforeMeasureView layout width= " + mButton2.getWidth() + " height= " + mButton2.getHeight());
    }

    private void initView() {
        view = findViewById(R.id.button1);
        view.setOnClickListener(this);
        mButton2 = findViewById(R.id.button2);
    }

    private void measureView() {
        int widthMeasureSpec = MeasureSpec.makeMeasureSpec((1 << 30) - 1, MeasureSpec.AT_MOST);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(100, MeasureSpec.EXACTLY);
        view.measure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "measureView, width= " + view.getMeasuredWidth() + " height= " + view.getMeasuredHeight());
    }

    @Override
    protected void onStart() {
        super.onStart();

        view.post(new Runnable() {
            @Override
            public void run() {
                int width = view.getMeasuredWidth();
                int height = view.getMeasuredHeight();

                Log.d(TAG, "view.post(new Runnable()), width= " + width + " height= " + height);
            }
        });

        ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int width = view.getMeasuredWidth();
                int height = view.getMeasuredHeight();

                Log.d(TAG, "ViewTreeObserver, width= " + width + " height= " + height);
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            int width = view.getMeasuredWidth();
            int height = view.getMeasuredHeight();
            Log.d(TAG, "onWindowFocusChanged, width= " + width + " height= " + height);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == view) {
            Log.d(TAG, "measure width= " + mButton2.getMeasuredWidth() + " height= " + mButton2.getMeasuredHeight());
            Log.d(TAG, "layout width= " + mButton2.getWidth() + " height= " + mButton2.getHeight());
        }
    }

}
