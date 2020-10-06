package com.seniorlibs.event.cancel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.seniorlibs.baselib.utils.LogUtils
import com.seniorlibs.event.R

class CancelActivity : AppCompatActivity() {

    companion object {
        val TAG = "Cancel"
    }

    /**
    Cancel: MyScrollView onInterceptTouchEvent 是否拦截：false
    Cancel: MyButton dispatchTouchEvent action：MotionEvent.ACTION_DOWN  // 按下
    Cancel: MyButton onTouchEvent action：MotionEvent.ACTION_DOWN
    Cancel: CancelActivity dispatchTouchEvent
    Cancel: MyScrollView dispatchTouchEvent

    Cancel: MyScrollView onInterceptTouchEvent 是否拦截：false
    Cancel: MyButton dispatchTouchEvent action：MotionEvent.ACTION_MOVE  // 移动
    Cancel: MyButton onTouchEvent action：MotionEvent.ACTION_MOVE
    Cancel: CancelActivity dispatchTouchEvent
    Cancel: MyScrollView dispatchTouchEvent

    Cancel: MyScrollView onInterceptTouchEvent 是否拦截：false
    Cancel: MyButton dispatchTouchEvent action：MotionEvent.ACTION_MOVE  // 移动
    Cancel: MyButton onTouchEvent action：MotionEvent.ACTION_MOVE
    Cancel: CancelActivity dispatchTouchEvent
    Cancel: MyScrollView dispatchTouchEvent

    // ...... 重复多次 MotionEvent.ACTION_MOVE // 移动

    Cancel: MyScrollView onInterceptTouchEvent 是否拦截：true
    Cancel: MyButton dispatchTouchEvent action：MotionEvent.ACTION_CANCEL  // 取消
    Cancel: MyButton onTouchEvent action：MotionEvent.ACTION_CANCEL
    Cancel: CancelActivity dispatchTouchEvent
    Cancel: MyScrollView dispatchTouchEvent

    Cancel: MyScrollView onTouchEvent
    Cancel: CancelActivity dispatchTouchEvent

    Cancel: MyScrollView dispatchTouchEvent
    Cancel: MyScrollView onTouchEvent

    // ...... 重复多次 onTouchEvent -> dispatchTouchEvent
    **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancel)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.i(TAG,"CancelActivity dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.i(TAG,"CancelActivity onTouchEvent")
        return super.onTouchEvent(event)
    }
}
