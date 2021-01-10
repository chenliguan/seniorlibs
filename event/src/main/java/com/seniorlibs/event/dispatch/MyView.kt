package com.seniorlibs.event.dispatch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.seniorlibs.baselib.utils.LogUtils
import com.seniorlibs.event.utils.EventUtils

class MyView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val TAG = "DispatchActivityTag"

    init {
        setOnTouchListener(object : OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
               return false
            }
        })
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        LogUtils.i(TAG, "View ：dispatchTouchEvent --> ${EventUtils.getAction(event.action)}")
        val result = super.dispatchTouchEvent(event)
        LogUtils.i(TAG, "View ：dispatchTouchEvent --> result is $result")
        return result
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        LogUtils.i(TAG, "View ：onTouchEvent --> ${EventUtils.getAction(event.action)}")
//        return false
        return super.onTouchEvent(event)  // 可点击时返回true，否则返回false
    }
}

