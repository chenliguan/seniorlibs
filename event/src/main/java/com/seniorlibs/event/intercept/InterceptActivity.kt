package com.seniorlibs.event.intercept

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.seniorlibs.baselib.utils.LogUtils
import com.seniorlibs.event.R

/**
 * 内部拦截
 *
 * @constructor Create empty Intercept activity
 */
class InterceptActivity : AppCompatActivity() {

    companion object {
        val TAG = "InterceptActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intercept)
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.i(TAG,"InterceptActivity dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.i(TAG,"InterceptActivity onTouchEvent")
        return super.onTouchEvent(event)
    }
}
