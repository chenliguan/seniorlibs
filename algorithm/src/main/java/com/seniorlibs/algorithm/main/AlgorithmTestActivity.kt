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
                LogUtils.e(TAG, "72. 编辑距离：${minDistance("horse", "ros")}")
            }
            else -> {
            }
        }
    }


    fun minDistance(s1: String, s2: String): Int {

        val m = s1.length + 1
        val n = s2.length + 1

        val dp = Array(m) { IntArray(n) }

        // base case
        for (i in 0 until m) dp[i][0] = i
        for (j in 0 until n) dp[0][j] = j

        // dp
        for (i in 1 until m) {
            for (j in 1 until n) {
                if (s1[i] == s2[j]) {
                    // s1[i]和s2[j]都跳过，啥都不做
                    dp[i][j] = dp[i-1][j-1]
                } else {
                    // 操作数 + 1
                    dp[i][j] = min(
                        // s1中插入一个和s2[j]一样的字符，j前移
                        dp[i][j - 1] + 1,
                        // 删除s1[i]，和s2[j]匹配，i前移
                        dp[i - 1][j] + 1,
                        // 替换s1[i]为s2[j]，i,j一起前移
                        dp[i - 1][j - 1] + 1
                    )
                }
            }
        }

        return dp[m - 1][n - 1]
    }

    fun min(a : Int, b : Int, c : Int) : Int {
        return Math.min(a, Math.min(b, c))
    }
}









