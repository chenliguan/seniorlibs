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
     * 494. 目标和  状态压缩  备注：这是 0-1背包问题的分割等和子集 和 完全背包问题的零钱兑换 II 的聚合题目
     *
     * 时间复杂度：O(n * m)，其中 n 是数组的长度，m 是整个数组的元素和 + 目标值 和的一半
     * 空间复杂度：O(n)，
     *
     * @param nums
     * @param S
     * @return
     */
    fun findTargetSumWays1(nums: IntArray, target: Int): Int {
        var sum = 0
        for (num in nums) sum += num

        if (sum < target || (sum + target) % 2 != 0) return 0

        val j = (sum + target) / 2
        return subsets1(nums, j)
    }

    /**
     * 计算 nums 中有几个子集的和为 sum
     *
     * @param nums
     * @param sum
     * @return
     */
    fun subsets1(nums: IntArray, sum: Int): Int {
        val m = nums.size + 1
        val n = sum + 1

        val dp = Array(m) { IntArray(n) }

        // base case
        dp[0][0] = 1
        for (j in 1 until n) dp[0][j] = 0

        // dp
        for (i in 1 until m) {
            for (j in 0 until n) {
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j]
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]]
                }
            }
        }

        return dp[m - 1][n - 1]
    }
}









