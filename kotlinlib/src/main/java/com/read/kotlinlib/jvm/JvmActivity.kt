package com.read.kotlinlib.jvm

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
 * Date: 2021/2/17
 * Mender:
 * Modify:
 * Description: 虚拟机
 */
class JvmActivity : AppCompatActivity() {

    companion object {

        const val TAG = "kotlin + JvmActivity : "

        fun actionStart(context: Context?) {
            if (context == null) {
                return
            }
            val intent = Intent(context, JvmActivity::class.java)
            context.startActivity(intent)
        }
    }

    object SingletonHolder {
        val INSTANCE = {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jvm)
    }

    /**
     * 测试栈溢出
     *
     * @param view
     */
    fun testStackOver(view: View?) {
        StackOver.main()
    }

    /**
     * 测试内存溢出
     *
     * @param view
     */
    fun testOomError(view: View?) {
        OomError.main()
    }


    /**
     * 验证虚拟机栈（栈帧中的局部变量）中引用的对象作为 GC Root
     *
     * @param view
     */
    fun testGCRootLocalVariable(view: View?) {
        GCRootLocalVariable.main()
    }

    /**
     * 测试成员变量是否可作为 GC Root
     *
     * @param view
     */
    fun testGCRootClassVariable(view: View?) {
        GCRootClassVariable.main()
    }

    /**
     * 验证方法区中的静态变量引用的对象作为 GC Root
     *
     * @param view
     */
    fun testGCRootStaticVariable(view: View?) {
        GCRootStaticVariable.main()
    }

    /**
     * 验证活跃线程作为 GC Root
     *
     * @param view
     */
    fun testGCRootThread(view: View?) {
        GCRootThread.main()
    }

    /**
     * 测试 MinorGC
     *
     * @param view
     */
    fun testMinorGCTest(view: View?) {
        MinorGCTest.main()
    }
}