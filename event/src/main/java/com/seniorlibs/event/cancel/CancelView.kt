package com.seniorlibs.event.cancel

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.TextView
import com.seniorlibs.baselib.utils.LogUtils
import com.seniorlibs.event.utils.EventUtils.getAction

class CancelView(context: Context, attrs: AttributeSet) : TextView(context, attrs) {
    val TAG = "CancelActivity"

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        LogUtils.i(TAG, "CancelView ：dispatchTouchEvent --> ${getAction(event.action)}")
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        LogUtils.i(TAG, "CancelView ：onTouchEvent --> ${getAction(event.action)}")
        return true
    }
}