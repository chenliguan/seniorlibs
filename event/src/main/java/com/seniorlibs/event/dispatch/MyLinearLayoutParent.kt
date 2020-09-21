package com.seniorlibs.event.dispatch

import android.content.Context
import android.view.MotionEvent
import android.widget.LinearLayout
import com.seniorlibs.baselib.utils.LogUtils

class MyLinearLayoutParent(context: Context?) : LinearLayout(context) {

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.i(DispatchActivity.TAG,"MyLinearLayoutParent dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.i(DispatchActivity.TAG,"MyLinearLayoutParent onTouchEvent")
        return super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.i(DispatchActivity.TAG,"MyLinearLayoutParent onInterceptTouchEvent")
        return super.onInterceptTouchEvent(ev)
    }
}