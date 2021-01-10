package com.seniorlibs.event.cancel

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView
import com.seniorlibs.baselib.utils.LogUtils
import com.seniorlibs.event.utils.EventUtils.getAction

class MyScrollView(context: Context, attrs: AttributeSet) : ScrollView(context, attrs) {
    val TAG = "CancelActivity"

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        LogUtils.i(TAG,"ScrollView ：dispatchTouchEvent --> ${getAction(event.action)}")
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        LogUtils.i(TAG,"ScrollView ：onTouchEvent --> ${getAction(event.action)}")
        return super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        LogUtils.i(TAG, "ScrollView ：onInterceptTouchEvent --> ${getAction(event.action)}")
        val result = super.onInterceptTouchEvent(event)
        LogUtils.e(TAG,"ScrollView ：onInterceptTouchEvent 是否拦截：$result")
        return result
    }
}