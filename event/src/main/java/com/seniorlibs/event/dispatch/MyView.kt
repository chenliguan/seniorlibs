package com.seniorlibs.event.dispatch

import android.content.Context
import android.view.MotionEvent
import android.view.View
import com.seniorlibs.baselib.utils.LogUtils

class MyView(context: Context?) : View(context) {

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.i(DispatchActivity.TAG,"MyView dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.i(DispatchActivity.TAG,"MyView onTouchEvent")
        return super.onTouchEvent(event)
    }
}