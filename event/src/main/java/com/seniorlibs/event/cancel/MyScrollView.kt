package com.seniorlibs.event.cancel

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView
import com.seniorlibs.baselib.utils.LogUtils

class MyScrollView(context: Context, attrs: AttributeSet) : ScrollView(context, attrs) {

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.i(CancelActivity.TAG,"MyScrollView dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.i(CancelActivity.TAG,"MyScrollView onTouchEvent")
        return super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        val result = super.onInterceptTouchEvent(ev)
        LogUtils.e(CancelActivity.TAG,"MyScrollView onInterceptTouchEvent 是否拦截：$result")
        return result
    }
}