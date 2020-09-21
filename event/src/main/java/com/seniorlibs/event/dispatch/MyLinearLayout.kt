package com.seniorlibs.event.dispatch

import android.content.Context
import android.view.MotionEvent
import android.widget.LinearLayout
import com.seniorlibs.baselib.utils.LogUtils

class MyLinearLayout(context: Context?) : LinearLayout(context) {

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.i(DispatchActivity.TAG,"MyLinearLayout dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.i(DispatchActivity.TAG,"MyLinearLayout onTouchEvent")
        return super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.i(DispatchActivity.TAG,"MyLinearLayout onInterceptTouchEvent")
        return super.onInterceptTouchEvent(ev)
    }
}