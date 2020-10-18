package com.seniorlibs.event.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * 滑动冲突场景2-内部拦截
 * 子View
 */
public class ListViewEx extends ListView {
    private static final String TAG = "ListViewEx";

    private HorizontalScrollViewEx2 mHorizontalScrollViewEx2;

    // 分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;

    public ListViewEx(Context context) {
        super(context);
    }

    public ListViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewEx(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setHorizontalScrollViewEx2(HorizontalScrollViewEx2 horizontalScrollViewEx2) {
        mHorizontalScrollViewEx2 = horizontalScrollViewEx2;
    }

    /**
     * 核心方法：在dispatchTouchEvent方法中，
     * 修改ListView的dispatchTouchEvent方法中的父容器的拦截逻辑，同时让父拦截MOVE和Up事件即可
     *
     * @param event
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN: {
            mHorizontalScrollViewEx2.requestDisallowInterceptTouchEvent(true);
            break;
        }
        case MotionEvent.ACTION_MOVE: {
            int deltaX = x - mLastX;
            int deltaY = y - mLastY;
            Log.d(TAG, "dx:" + deltaX + " dy:" + deltaY);
            // 在滑动过程中
            // 当水平方向的距离大就判断水平滑动，为了能够水平滑动所以让父容器拦截事件;
            // 当竖直方距离大于就不让父容器拦截，于是事件传递给了ListView，所以ListView能上下滑动，这就解决了冲突了
            if (Math.abs(deltaX) > Math.abs(deltaY)) {
                mHorizontalScrollViewEx2.requestDisallowInterceptTouchEvent(false); // 让父容器拦截
            }
            break;
        }
        case MotionEvent.ACTION_UP: {
            break;
        }
        default:
            break;
        }

        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(event);
    }
}
