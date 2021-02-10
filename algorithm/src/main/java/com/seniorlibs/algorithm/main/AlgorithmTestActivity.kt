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
                LogUtils.e(TAG, "300. 最长递增子序列：${lengthOfLIS(intArrayOf(10,9,2,5,3,7,101,18))}")
            }
            else -> {
            }
        }
    }



    /**
     * 300. 最长递增子序列  解法：动态规划
     *
     * 时间复杂度：O(N^2)，这里 NN 是数组的长度，写了两个 for 循环；
     * 空间复杂度：O(N)，要使用和输入数组长度相等的状态数组，因此空间复杂度是 O(N)。
     *
     * https://leetcode-cn.com/problems/longest-increasing-subsequence/
     * @param nums
     * @return
     */
    fun lengthOfLIS(nums: IntArray): Int {
        val n = nums.size
        if (n == 0) return 0

        // dp定义：表示以nums[i]结尾的最长递增子序列
        val dp = IntArray(n)

        // base case
        for (i in 0 until n) dp[i] = 1

        // dp方程
        for (i in 0 until n) {
            for (j in 0 until i) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1)
                }
            }
        }

        // 计算最大值
        var res = Int.MIN_VALUE
        for (i in 0 until n) {
            res = Math.max(res, dp[i])
        }

        return res
    }
}









