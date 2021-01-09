package com.seniorlibs.event.cancel

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton
import com.seniorlibs.event.utils.EventUtils.getAction
import com.seniorlibs.baselib.utils.LogUtils

class MyButton(context: Context, attrs: AttributeSet) : AppCompatButton(context, attrs) {

    /**
     * ACTION_DOWN = 0
     * ACTION_UP = 1
     * ACTION_MOVE = 2
     * ACTION_CANCEL = 3
     */

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        LogUtils.i(CancelActivity.TAG, "MyButton dispatchTouchEvent action：" + getAction(event.action))
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        LogUtils.i(CancelActivity.TAG, "MyButton onTouchEvent action：" + getAction(event.action))
        return super.onTouchEvent(event)
    }
}