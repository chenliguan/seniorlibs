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
                LogUtils.e(TAG, "518. 零钱兑换 II：${changeII(5, intArrayOf(1, 2, 5))}")
                LogUtils.e(TAG, "518. 零钱兑换 II 2：${changeII2(5, intArrayOf(1, 2, 5))}")
            }
            else -> {
            }
        }
    }


    /**
     * 518. 零钱兑换 II 动态规划
     *
     * 时间复杂度 O(n * amount)
     * 空间复杂度 O(n * amount)
     *
     * https://leetcode-cn.com/problems/coin-change-2/solution/518-ling-qian-dui-huan-ii-by-chen-li-gua-zykf/
     * @param amount
     * @param coins
     * @return
     */
    fun changeII(amount: Int, coins: IntArray): Int {

        val m = coins.size + 1
        val n = amount + 1

        // dp 定义：只使用前 i 个硬币的面值，当背包容量为 j 时，有 dp[i][j] 种方法装满背包
        val dp = Array(m) { IntArray(n) }

        // base case
        // 有硬币，容量为0时，存在一个方法装满
        for (i in 0 until m) dp[i][0] = 1
        // 没有硬币，容量>0时，没有方法装满
        for (j in 1 until n) dp[0][j] = 0

        // dp 方程
        for (i in 1 until m) {
            for (j in 1 until n) {
                if (j - coins[i - 1] < 0) {
                    // 背包容量装不下第 i 个硬币的面值，不装入，同步前一个硬币的状态
                    dp[i][j] = dp[i - 1][j]
                } else {
                    // 背包容量装得下第 i 个硬币的面值，以下情况的和
                    // 选择不装入，同步前一个硬币的状态
                    // 选择装入，看剩余重量 j - coins[i - 1]，如果i在剩余重量中可以装满，i装入后也可以装满。
                    // 不是 i - 1，因为 完全背包问题，可以重复选
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]]
                }
            }
        }

        // 前 m - 1 个硬币的面值，当背包容量为 n - 1 时，有多少种方法装满背包
        return dp[m - 1][n - 1]
    }

    /**
     * 518. 零钱兑换 II 2 动态规划--空间压缩
     *
     * 时间复杂度 O(n * amount)
     * 空间复杂度 O(amount)
     *
     * https://leetcode-cn.com/problems/coin-change-2/solution/518-ling-qian-dui-huan-ii-by-chen-li-gua-zykf/
     * @param amount
     * @param coins
     * @return
     */
    fun changeII2(amount: Int, coins: IntArray): Int {
        val m = coins.size + 1
        val n = amount + 1

        // dp 定义：只使用前 i 个硬币的面值，当背包容量为 j 时，有 dp[i][j] 种方法装满背包
        val dp = IntArray(n)

        // base case
        // 没有硬币，容量>0时，没有方法装满
        for (j in 1 until n) dp[j] = 0
        // 有硬币，容量为0时，存在一个方法装满
        dp[0] = 1


        // dp 方程
        for (i in 1 until m) {
            for (j in 1 until n) {
                if (j - coins[i - 1] >= 0) {
                    // 背包容量装得下第 i 个硬币的面值，以下情况的和
                    // 选择不装入，同步前一个硬币的状态
                    // 选择装入，看剩余重量 j - coins[i - 1]，如果i在剩余重量中可以装满，i装入后也可以装满。
                    // 不是 i - 1，因为 完全背包问题，可以重复选
                    dp[j] = dp[j] + dp[j - coins[i - 1]]
                }
            }
        }

        // 前 m - 1 个硬币的面值，当背包容量为 n - 1 时，有多少种方法装满背包
        return dp[n - 1]
    }
}









