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
                LogUtils.e(TAG, "5. 最长回文子串：${longestPalindrome("abbc")}")
            }
            else -> {
            }
        }
    }


    fun longestPalindrome(s: String): String {
        val n = s.length
        var begin = 0
        var maxLen = 1
        val dp = Array(n) { kotlin.BooleanArray(n)}

        // base case
        for (i in 0 until n) dp[i][i] = true

        // 反着遍历
        for (i in n - 1 downTo 0) {
            for (j in i + 1 until n) {
                if (j - i < 2) {
                    dp[i][j] = s[i] == s[j]
                } else {
                    dp[i][j] = dp[i + 1][j - 1] && (s[i] == s[j])
                }

                // 记录
                if (dp[i][j] && (j - i + 1 > maxLen)) {
                    maxLen = j - i + 1
                    begin = i
                }
            }
        }

        return s.substring(begin, begin + maxLen)
    }

}
