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
                LogUtils.e(TAG, "152. 乘积最大子数组：${maxProduct(intArrayOf(2, 3, -2, 4, -1))}")
            }
            else -> {
            }
        }
    }


    /**
     * 152. 乘积最大子数组  解法一：动态规划
     *
     * DP方程：imax = max(imax * num[i], num[i])， imin = min(imin * num[i], num[i]), db = max(db, imax)
     *
     * 时间复杂度：程序一次循环遍历了nums，故时间复杂度为O(n)。
     * 空间复杂度：优化后只使用常数个临时变量作为辅助空间，与n无关，故空间复杂度为O(1)。
     *
     * @param nums
     * @return
     */
    fun maxProduct(nums: IntArray): Int {
        val n = nums.size
        if (n == 0) return 0

        // dp定义
        val dp = Array(n) { IntArray(2) }

        // base case
        dp[0][0] = nums[0]
        dp[0][1] = nums[0]

        // dp方程
        for (i in 1 until n) {
            if (nums[i] >= 0) {
                // 正数 * 最小值 = 最小值
                dp[i][0] = Math.min(nums[i], nums[i] * dp[i - 1][0])
                // 正数 * 最大值 = 最大值
                dp[i][1] = Math.max(nums[i], nums[i] * dp[i - 1][1])
            } else {
                // 负数 * 最大值 = 最小值
                dp[i][0] = Math.min(nums[i], nums[i] * dp[i - 1][1])
                // 负数 * 最小值 = 最大值
                dp[i][1] = Math.max(nums[i], nums[i] * dp[i - 1][0])
            }
        }

        // 最大值
        var max = Int.MIN_VALUE
        for (i in 0 until n) {
            max = Math.max(max, dp[i][1])
        }

        return max
    }
}









