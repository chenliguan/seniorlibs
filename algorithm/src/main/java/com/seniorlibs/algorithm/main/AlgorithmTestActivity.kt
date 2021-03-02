package com.seniorlibs.algorithm.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.db.DbActivity
import com.seniorlibs.algorithm.db.DbActivity2
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

        lifecycle
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
                LogUtils.e(TAG, "121. 买卖股票的最佳时机 11：${maxProfit11(intArrayOf(7, 1, 5, 3, 6, 4))}")
                LogUtils.e(TAG, "121. 买卖股票的最佳时机 12：${maxProfit12(intArrayOf(7, 1, 5, 3, 6, 4))}")
            }
            else -> {
            }
        }
    }



    fun maxProfit11(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        val m = prices.size
        val dp = Array(m) {IntArray(2)}

        // base case
        dp[0][0] = 0
        dp[0][1] = -prices[0]

        // dp
        for (i in 1 until m) {
            // 今天不持有股：昨天没有股，今天选择休息；昨天有股，今天选择卖出；（现金增加）
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])

            // 今天持有股：昨天有股，今天选择休息；昨天没有股，今天选择买入；（现金减少）
            dp[i][1] = Math.max(dp[i - 1][1], 0 - prices[i])
        }

        return dp[m - 1][0]
    }

    fun maxProfit12(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        val m = prices.size

        // base case
        var dp_0_0 = 0
        var dp_0_1 = -prices[0]

        // dp
        for (i in 1 until m) {
            // 今天不持有股：昨天没有股，今天选择休息；昨天有股，今天选择卖出；（现金增加）
            dp_0_0 = Math.max(dp_0_0, dp_0_1 + prices[i])

            // 今天持有股：昨天有股，今天选择休息；昨天没有股，今天选择买入；（现金减少）
            dp_0_1 = Math.max(dp_0_1, 0 - prices[i])
        }

        return dp_0_0
    }
}









