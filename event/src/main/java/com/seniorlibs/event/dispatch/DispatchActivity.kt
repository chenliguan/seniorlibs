package com.seniorlibs.event.dispatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.seniorlibs.event.utils.EventUtils
import com.seniorlibs.baselib.utils.LogUtils
import com.seniorlibs.event.R

class DispatchActivity : AppCompatActivity() {
    private val TAG = "DispatchActivityTag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dispatch)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        LogUtils.i(TAG,"Activity ：dispatchTouchEvent --> ${EventUtils.getAction(event.action)}")
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        LogUtils.i(TAG, "Activity ：onTouchEvent --> ${EventUtils.getAction(event.action)}")
        return super.onTouchEvent(event)
    }
}
