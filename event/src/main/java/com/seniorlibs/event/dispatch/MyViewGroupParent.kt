package com.seniorlibs.event.dispatch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.seniorlibs.baselib.utils.LogUtils

class MyViewGroupParent(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.i(DispatchActivity.TAG, "MyViewGroupParent dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.i(DispatchActivity.TAG, "MyViewGroupParent onTouchEvent")
        return super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.i(DispatchActivity.TAG, "MyViewGroupParent onInterceptTouchEvent")
        return super.onInterceptTouchEvent(ev)
    }
}