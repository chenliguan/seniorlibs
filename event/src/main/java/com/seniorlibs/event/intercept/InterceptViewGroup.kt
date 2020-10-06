package com.seniorlibs.event.intercept

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import com.g.event.utils.EventUtils
import com.seniorlibs.baselib.utils.LogUtils

class InterceptViewGroup(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    // 点击了InterceptView上，事件没传递到InterceptView，在InterceptViewGroup这一层被拦截，打印结果如下

    // onTouchEvent返回true
    /**
    InterceptActivity dispatchTouchEvent
    InterceptViewGroup dispatchTouchEvent
    InterceptViewGroup onInterceptTouchEvent                 // 拦截
    InterceptViewGroup onTouchEvent MotionEvent.ACTION_DOWN  // 多次调用InterceptViewGroup的onTouchEvent
    InterceptActivity dispatchTouchEvent
    InterceptViewGroup dispatchTouchEvent
    InterceptViewGroup onTouchEvent MotionEvent.ACTION_MOVE
    InterceptActivity dispatchTouchEvent
    InterceptViewGroup dispatchTouchEvent
    InterceptViewGroup onTouchEvent MotionEvent.ACTION_UP
     */

    // onTouchEvent返回false
    /**
    InterceptActivity dispatchTouchEvent
    InterceptViewGroup dispatchTouchEvent
    InterceptViewGroup onInterceptTouchEvent                 // 拦截
    InterceptViewGroup onTouchEvent MotionEvent.ACTION_DOWN  // 只调用一次InterceptViewGroup的onTouchEvent
    InterceptActivity onTouchEvent                           // 多次调用Activity.onTouchEvent
    InterceptActivity dispatchTouchEvent
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

//        return super.onTouchEvent(event)
        // true表示事件交由InterceptViewGroup消费，多次调用ViewGroup的onTouchEvent；
        // false表示不消费，只调用一次ViewGroup的onTouchEvent；然后，回传给上层的Activity，多次调用Activity.onTouchEvent
        return true
    }
}