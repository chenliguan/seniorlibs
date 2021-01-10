package com.seniorlibs.event.cancel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.seniorlibs.baselib.utils.LogUtils
import com.seniorlibs.event.R
import com.seniorlibs.event.utils.EventUtils.getAction

class CancelActivity : AppCompatActivity() {

    val TAG = "CancelActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancel)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        LogUtils.i(TAG,"Activity ：dispatchTouchEvent --> ${getAction(event.action)}")
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        LogUtils.i(TAG,"Activity ：onTouchEvent --> ${getAction(event.action)}")
        return super.onTouchEvent(event)
    }
}
