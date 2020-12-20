package com.seniorlibs.algorithm.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.db.DbActivity
import com.seniorlibs.baselib.utils.LogUtils
import java.util.*

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/9/26
 * Mender:
 * Modify:
 * Description: 混合测试
 */
class AlgorithmTestActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "AlgorithmTestActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, AlgorithmTestActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_algorithm_test)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_1).setOnClickListener(this)

    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_1 -> {
                LogUtils.e(DbActivity.TAG, "509. 斐波那契数：${fib1(5)}")
                LogUtils.e(DbActivity.TAG, "509. 斐波那契数：${fib2(5)}")
                LogUtils.e(DbActivity.TAG, "509. 斐波那契数：${fib3(5)}")
//                LogUtils.e(DbActivity.TAG, "509. 斐波那契数：${fib4(5)}")
            }
            else -> {
            }
        }
    }

    fun fib1(n: Int): Int {
        if (n == 0) return 0
        if (n == 1) return 1

        return fib1(n - 1) + fib1(n - 2)
    }

    val cache = IntArray(31)

    fun fib2(n: Int): Int {
        if (n == 0) return 0
        if (n == 1) return 1

        cache[0] = 0
        cache[1] = 1

        return fib21(n)
    }

    fun fib21(n: Int): Int {
        if (n == 0) return 0
        if (n == 1) return 1

        if (cache[n] != 0) return cache[n]

        cache[n] = fib21(n - 1) + fib21(n - 2)
        return cache[n]
    }

    fun fib3(n: Int): Int {
        if (n == 0) return 0
        if (n == 1) return 1

        val dp = IntArray(31)

        dp[0] = 0
        dp[1] = 1

        for (i in 2 until n + 1) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }

        return dp[n]
    }
}
