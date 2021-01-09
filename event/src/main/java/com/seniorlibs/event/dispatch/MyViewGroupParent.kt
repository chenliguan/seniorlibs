package com.seniorlibs.event.dispatch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import com.seniorlibs.event.utils.EventUtils
import com.seniorlibs.baselib.utils.LogUtils

/**
 * 暂不使用
 */
class MyViewGroupParent(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    private val TAG = "DispatchActivityTag"

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        LogUtils.i(TAG,"ViewGroupParent ：dispatchTouchEvent --> ${EventUtils.getAction(event.action)}")
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        LogUtils.i(TAG, "ViewGroupParent ：onTouchEvent --> ${EventUtils.getAction(event.action)}")
        return super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        LogUtils.i(TAG, "ViewGroupParent ：onInterceptTouchEvent --> ${EventUtils.getAction(event.action)}")
        return super.onInterceptTouchEvent(event)
    }
}