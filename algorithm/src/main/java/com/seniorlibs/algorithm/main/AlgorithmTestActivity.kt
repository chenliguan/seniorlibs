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
                LogUtils.e(TAG, "516. 最长回文子序列：${longestPalindromeSubseq("bbbab")}")
            }
            else -> {
            }
        }
    }


    fun longestPalindromeSubseq(s: String): Int {
        val m = s.length

        val dp = Array(m) { IntArray(m) }

        // base case
        for (i in 0 until m) dp[i][i] = 1

        for (i in m - 1 downTo 0) {
            for (j in i + 1 until m) {
                if (s[i] == s[j]) {
                    dp[i][j] = dp[i+1][j-1] + 2
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1])
                }
            }
        }

        return dp[0][m-1]
    }
}
