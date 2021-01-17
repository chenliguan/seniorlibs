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
                LogUtils.e(DbActivity2.TAG, "416. 分割等和子集：${canPartition(intArrayOf(1, 5, 11, 5))}")
                LogUtils.e(DbActivity2.TAG, "416. 分割等和子集1：${canPartition1(intArrayOf(1, 5, 11, 5))}")
            }
            else -> {
            }
        }
    }


    /**
     * 416. 分割等和子集   动态规划
     *
     * 时间复杂度：O(n * m)，其中 n 是数组的长度，m 是整个数组的元素和的一半，需要计算出所有的状态，每个状态在进行转移时的时间复杂度为 O(1)O(1)。
     * 空间复杂度：O(n * m)，其中 n 是数组的长度。空间复杂度取决于 dp 数组，在不进行空间优化的情况下，空间复杂度是 O(n * m)，
     *                      在进行空间优化的情况下，空间复杂度可以降到 O(m)。
     * @param nums
     * @return
     */
    fun canPartition(nums: IntArray): Boolean {
        var sum = 0
        for (num in nums) {
            sum += num
        }

        val m = nums.size + 1
        val n = sum / 2 + 1

        // 和为奇数不可能划分为相等的两个集合
        if (sum % 2 != 0) return false

        val dp = Array(m) { BooleanArray(n) }

        // base case
        // 容量为0，可装满
        for (i in 0 until m) dp[i][0] = true
        // 没有物品，容量>0，都装不满
        for (j in 1 until n) dp[0][j] = false

        // dp方程
        for (i in 1 until m ) {
            for (j in 1 until n) {
                if (j - nums[i - 1] < 0) {
                    // 背包容量不足，不能装入第i个物品，所以状态和没装第i个物品相同
                    dp[i][j] = dp[i - 1][j]
                } else {
                    // 背包容量充足：
                    // 选择不装入背包，状态和没装第i个物品相同
                    // 选择装入背包，装了第i个物品，就要看背包的剩余(j - nums[i-1])的重量可以被(i - 1)个物品装满，那么把第i个物品装进去，也可装满背包j的重量
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]]
                }
            }
        }

        return dp[m - 1][n - 1]
    }

    fun canPartition1(nums: IntArray): Boolean {
        var sum = 0
        for (num in nums) {
            sum += num
        }

        val m = nums.size + 1
        val n = sum / 2 + 1

        // 和为奇数不可能划分为相等的两个集合
        if (sum % 2 != 0) return false

        val dp = BooleanArray(n)

        // base case
        // 没有物品，容量>0，都装不满
        for (j in 1 until n) dp[j] = false
        // 背包容量为 0，都是装满的
        dp[0] = true

        // dp方程
        for (i in 1 until m ) {
            for (j in n - 1 downTo 0) {
                if (j - nums[i - 1] >= 0) {
                    // 背包容量充足：
                    // 选择不装入背包，状态和没装第i个物品相同
                    // 选择装入背包，装了第i个物品，就要看背包的剩余(j - nums[i-1])的重量可以被(i - 1)个物品装满，那么把第i个物品装进去，也可装满背包j的重量
                    dp[j] = dp[j] || dp[j - nums[i - 1]]
                }
            }
        }

        return dp[n - 1]
    }
}









