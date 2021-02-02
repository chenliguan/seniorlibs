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
                val paths = arrayOf(intArrayOf(1, 3, 1), intArrayOf(1, 5, 1), intArrayOf(4, 2, 1))
                LogUtils.e(TAG, "64. 最小路径和：${minPathSum(paths)}")
            }
            else -> {
            }
        }
    }


    /**
     * 64. 最小路径和
     *
     * 时间复杂度：O(mn)；
     * 空间复杂度：O(mn)；
     *
     * @param grid
     * @return
     */
    fun minPathSum(grid: Array<IntArray>): Int {
        if (grid.isEmpty() || grid[0].isEmpty()) return 0

        val m = grid.size
        val n = grid[0].size

        // dp定义：向下走m步，以及向右走n步，路径上的数字最小和
        val dp = Array(m) {IntArray(n)}

        // base case
        dp[0][0] = grid[0][0]
        for (i in 1 until m) {
            dp[i][0] = dp[i - 1][0] + grid[i][0]
        }
        for (j in 1 until n) {
            dp[0][j] = dp[0][j - 1] + grid[0][j]
        }

        // dp方程
        for (i in 1 until m) {
            for (j in 1 until n) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]
            }
        }

        return dp[m - 1][n - 1]
    }
}









