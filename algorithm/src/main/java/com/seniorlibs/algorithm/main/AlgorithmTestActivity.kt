package com.seniorlibs.algorithm.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
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
                LogUtils.e(TAG, "322. 零钱兑换：${coinChange(intArrayOf(1, 2, 5), 11)}")
            }
            else -> {
            }
        }
    }

    fun coinChange(coins: IntArray, amount: Int): Int {
        val dp = IntArray(amount + 1)
        Arrays.fill(dp, amount + 1)

        dp[0] = 0

        for (i in dp.indices) {
            for (coin in coins) {
                if (i < coin) continue

                dp[i] = Math.min(dp[i], 1 + dp[i - coin])
            }
        }

        return if (dp[amount] > amount) -1 else dp[amount]
    }
}
