package com.seniorlibs.event.dispatch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.g.event.utils.EventUtils
import com.seniorlibs.baselib.utils.LogUtils
import com.seniorlibs.event.intercept.InterceptActivity

class MyViewGroup(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    /**
     * ViewGroup: dispatchTouchEvent
     * 1、去判断是否需要拦截事件
     * 2、在当前ViewGroup中找到用户真正点击的View
     * 3、分发事件到View.上
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.i(DispatchActivity.TAG,"MyViewGroup dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                LogUtils.i(
                    DispatchActivity.TAG,
                    "MyViewGroup onTouchEvent " + EventUtils.getAction(event.action)
                )
            }
            MotionEvent.ACTION_MOVE -> {
                LogUtils.i(
                    DispatchActivity.TAG,
                    "MyViewGroup onTouchEvent " + EventUtils.getAction(event.action)
                )
            }
            MotionEvent.ACTION_UP -> {
                LogUtils.i(
                    DispatchActivity.TAG,
                    "MyViewGroup onTouchEvent " + EventUtils.getAction(event.action)
                )
            }
            else -> {
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.i(DispatchActivity.TAG,"MyViewGroup onInterceptTouchEvent")
        return super.onInterceptTouchEvent(ev)
    }
}