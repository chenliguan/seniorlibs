package com.seniorlibs.event.dispatch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import com.g.event.utils.EventUtils
import com.seniorlibs.baselib.utils.LogUtils

class MyView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    // onTouchEvent返回false：false表示View不消费事件，只调用一次View的onTouchEvent；然后，回传给父容器，多次调用ViewGroup.onTouchEvent
    /**
    // 点击：按下->抬起
    DispatchActivity dispatchTouchEvent
    MyViewGroupParent dispatchTouchEvent
    MyViewGroupParent onInterceptTouchEv
    MyViewGroup dispatchTouchEvent
    MyViewGroup onInterceptTouchEvent
    MyView dispatchTouchEvent
    MyView onTouchEvent  MotionEvent.ACTION_DOWN
    MyViewGroup onTouchEvent  MotionEvent.ACTION_DOWN
    MyViewGroupParent onTouchEvent  MotionEvent.ACTION_DOWN
    DispatchActivity onTouchEvent  MotionEvent.ACTION_DOWN
    // 滑动：按下->滑动->抬起 +
    DispatchActivity dispatchTouchEvent
    DispatchActivity onTouchEvent MotionEvent.ACTION_MOVE
    DispatchActivity dispatchTouchEvent
    DispatchActivity onTouchEvent MotionEvent.ACTION_UP
    // ...... 重复多次  dispatchTouchEvent  -> onTouchEvent
     */

    // 设置MyView的clickable="true"后，默认onTouchEvent返回true
    // onTouchEvent返回true：true表示事件交由View消费事件，多次调用View的onTouchEvent
    /**
    DispatchActivity dispatchTouchEvent
    MyViewGroupParent dispatchTouchEvent
    MyViewGroupParent onInterceptTouchEvent
    MyViewGroup dispatchTouchEvent
    MyViewGroup onInterceptTouchEvent
    MyView dispatchTouchEvent
    MyView onTouchEvent MotionEvent.ACTION_DOWN
    DispatchActivity dispatchTouchEvent
    MyViewGroupParent dispatchTouchEvent
    MyViewGroupParent onInterceptTouchEvent
    MyViewGroup dispatchTouchEvent
    MyViewGroup onInterceptTouchEvent
    MyView dispatchTouchEvent
    MyView onTouchEvent MotionEvent.ACTION_MOVE
    DispatchActivity dispatchTouchEvent
    MyViewGroupParent dispatchTouchEvent
    MyViewGroupParent onInterceptTouchEvent
    MyViewGroup dispatchTouchEvent
    MyViewGroup onInterceptTouchEvent
    MyView dispatchTouchEvent
    MyView onTouchEvent MotionEvent.ACTION_UP
    // ...... 重复多次
    */

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.i(DispatchActivity.TAG,"MyView dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                LogUtils.i(
                    DispatchActivity.TAG,
                    "MyView onTouchEvent " + EventUtils.getAction(event.action)
                )
            }
            MotionEvent.ACTION_MOVE -> {
                LogUtils.i(
                    DispatchActivity.TAG,
                    "MyView onTouchEvent " + EventUtils.getAction(event.action)
                )
            }
            MotionEvent.ACTION_UP -> {
                LogUtils.i(
                    DispatchActivity.TAG,
                    "MyView onTouchEvent " + EventUtils.getAction(event.action)
                )
            }
            else -> {
            }
        }

        return super.onTouchEvent(event)
        // true表示事件交由View消费，多次调用View的onTouchEvent；
        // false表示View不消费，只调用一次View的onTouchEvent；然后，回传给父容器，多次调用ViewGroup.onTouchEvent
//        return false
    }
}