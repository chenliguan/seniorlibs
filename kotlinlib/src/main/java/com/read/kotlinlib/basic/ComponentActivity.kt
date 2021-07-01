package com.read.kotlinlib.basic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.read.kotlinlib.R
import com.read.kotlinlib.model.People
import com.seniorlibs.baselib.utils.LogUtils

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/1/19
 * Mender:
 * Modify:
 * Description: kotlin定义组件化接口
 */
class ComponentActivity : AppCompatActivity() {

    companion object {
        const val TAG = "kotlin + ComponentActivity : "

        fun actionStart(context: Context?) {
            if (context == null) {
                return
            }
            val intent = Intent(context, ComponentActivity::class.java)
            context.startActivity(intent)
        }
    }

    object SingletonHolder {
        val INSTANCE = {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_component)
    }

    /**
     * 测试组件化接口
     *
     * @param view
     */
    fun testComponent(view: View?) {
        Func.setGetNameFun {
            val str1 = " aaaaaaaaaaaaaaaaaaaaaaa "
            val str2 = "ddddddddddddddddddddddddddddddddddddd"
            it + str1 + str2
        }

        LogUtils.d(TAG, "testComponent1 : ${Func.funcGetName}")
        LogUtils.d(TAG, "testComponent2 : ${Func.getName("11111")}")

//        testComponent1 : Function1<java.lang.String, java.lang.String>
//        testComponent2 : 11111 aaaaaaaaaaaaaaaaaaaaaaa ddddddddddddddddddddddddddddddddddddd

        // 如果不调用 Func.setGetNameFun { }，打印如下：
//        testComponent2 : 11111 aaaaaaaaaaaaaaaaaaaaaaa ddddddddddddddddddddddddddddddddddddd

        Func.getBaseFunc()
        Func.mPublicFieLd
        Func.mInternalFieLd
    }
}