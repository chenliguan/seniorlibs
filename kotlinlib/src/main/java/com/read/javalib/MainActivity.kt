package com.read.javalib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.View
import com.read.javalib.generic.GenericTest

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/4/1
 * Mender:
 * Modify:
 * Description: 测试kotlin
 */
class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "javalib + MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * 泛型测试
     *
     * @param view
     */
    fun textGeneric(view: View?) {
        val genericTest = GenericTest()
        genericTest.genericErasure()
    }
}