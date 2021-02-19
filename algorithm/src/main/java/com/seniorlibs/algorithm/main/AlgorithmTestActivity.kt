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
                val paths = arrayOf(
                    charArrayOf('1', '0', '1', '0', '0'), charArrayOf('1', '0', '1', '1', '1'),
                    charArrayOf('1', '1', '1', '1', '1'), charArrayOf('1', '0', '0', '1', '0')
                )
                LogUtils.e(TAG, "221. 最大正方形1：${maximalSquare(paths)}")
            }
            else -> {
            }
        }
    }



    /**
     * 221. 最大正方形 解法一：动态规划
     *
     * 思想：以(i,j)为根据点，它最大的正方形的边长取决于(上, 左, 左上)边长最短的那一个
     *
     * 时间复杂度：O(mn)，其中m和n分别为行数和列数；
     * 空间复杂度：O(mn)，使用了空间大小为mn的数组
     *
     * https://leetcode-cn.com/problems/maximal-square/solution/221-zui-da-zheng-fang-xing-by-chen-li-guan/
     * @param matrix
     * @return
     */
    fun maximalSquare(matrix: Array<CharArray>): Int {
        if (matrix.isEmpty() || matrix[0].isEmpty()) return 0

        val m = matrix.size + 1
        val n = matrix[0].size + 1

        // dp定义
        val dp = Array(m) {IntArray(n)}

        // base case

        // dp方程
        for (i in 1 until m) {
            for (j in 1 until n) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1
                } else {
                    dp[i][j] = 0
                }
            }
        }

        // 计算最大边长
        var res = 0
        for (i in 1 until m) {
            for (j in 1 until n) {
                res = Math.max(res, dp[i][j])
            }
        }

        return res * res
    }
}









