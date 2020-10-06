package com.seniorlibs.event.dispatch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import com.seniorlibs.baselib.utils.LogUtils

class MyView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.i(DispatchActivity.TAG,"MyView dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.i(DispatchActivity.TAG,"MyView onTouchEvent")
        return super.onTouchEvent(event)
    }
}