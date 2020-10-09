package com.seniorlibs.event.intercept

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import com.g.event.utils.EventUtils
import com.seniorlibs.baselib.utils.LogUtils

class InterceptViewGroup(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    // 点击了InterceptView上，事件没传递到InterceptView，在InterceptViewGroup这一层被拦截，打印结果如下
    /**
    InterceptActivity dispatchTouchEvent
    InterceptViewGroup dispatchTouchEvent
    InterceptViewGroup onInterceptTouchEvent                // 拦截
    InterceptViewGroup onTouchEvent MotionEvent.ACTION_DOWN
    InterceptActivity onTouchEvent
    InterceptActivity dispatchTouchEvent
    InterceptActivity onTouchEvent
     */

    // 点击了InterceptView上，事件传递到InterceptView，在InterceptViewGroup这一层不拦截，打印结果如下
    /**
    InterceptActivity dispatchTouchEvent
    InterceptViewGroup dispatchTouchEvent
    InterceptViewGroup onInterceptTouchEvent
    InterceptView dispatchTouchEvent
    InterceptView onTouchEvent
    InterceptViewGroup onTouchEvent MotionEvent.ACTION_DOWN
    InterceptActivity onTouchEvent
    InterceptActivity dispatchTouchEvent
    InterceptActivity onTouchEvent
    */

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.i(InterceptActivity.TAG, "InterceptViewGroup dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.e(InterceptActivity.TAG, "InterceptViewGroup onInterceptTouchEvent")
        super.onInterceptTouchEvent(ev)
        return true  // 拦截
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                LogUtils.i(
                    InterceptActivity.TAG,
                    "InterceptViewGroup onTouchEvent " + EventUtils.getAction(event.action)
                )
            }
            MotionEvent.ACTION_MOVE -> {
                LogUtils.i(
                    InterceptActivity.TAG,
                    "InterceptViewGroup onTouchEvent " + EventUtils.getAction(event.action)
                )
            }
            MotionEvent.ACTION_UP -> {
                LogUtils.i(
                    InterceptActivity.TAG,
                    "InterceptViewGroup onTouchEvent " + EventUtils.getAction(event.action)
                )
            }
            else -> {
            }
        }

        return super.onTouchEvent(event)
    }
}