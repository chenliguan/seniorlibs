package com.seniorlibs.event.dispatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.seniorlibs.baselib.utils.LogUtils
import com.seniorlibs.event.R

class DispatchActivity : AppCompatActivity() {

    companion object {
        val TAG = "Dispatch"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dispatch)
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.i(TAG,"DispatchActivity dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.i(TAG,"DispatchActivity onTouchEvent")
        return super.onTouchEvent(event)
    }
}
