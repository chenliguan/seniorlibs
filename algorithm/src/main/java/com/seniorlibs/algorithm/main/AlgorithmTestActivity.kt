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
                val grid = arrayOf(
                    charArrayOf('X','X','X','X'),
                    charArrayOf('X','O','O','X'),
                    charArrayOf('X','X','O','X'),
                    charArrayOf('X','O','X','X')
                )
                LogUtils.e(TAG, "130. 被围绕的区域——方法一：深度优先遍历DFS：${solve(grid)}")
            }
            else -> {
            }
        }
    }


    /**
     * 130. 被围绕的区域
     *
     * 边界上的 O 要特殊处理，只要把边界上的 O 特殊处理了，那么剩下的 O 替换成 X 就可以了。问题转化为，如何寻找和边界联通的 O。
     *
     * 时间复杂度：O(mn)，其中m和n分别为行数和列数；
     * 空间复杂度：O(mn)，在最坏情况下，整个网格均为陆地，深度优先搜索的深度达到mn
     *
     * @param board
     */
    fun solve(board: Array<CharArray>) {
        if (board.isEmpty()) return

        val m = board.size
        val n = board[0].size

        for (i in 0 until m) {
            for (j in 0 until n) {
                val isEdge = (i == 0 || j == 0 || i == m - 1 || j == n - 1)
                if (isEdge && board[i][j] == 'O') {
                    dfs(board, i, j)
                }
            }
        }

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X'
                }

                if (board[i][j] == '#') {
                    board[i][j] = 'O'
                }
            }
        }

    }

    private fun dfs(board: Array<CharArray>, i: Int, j: Int) {
        if (i >= 0 && j >= 0 && i <= board.size - 1 && j <= board[0].size - 1 && board[i][j] == 'O') {
            board[i][j] = '#'
            dfs(board, i - 1, j)
            dfs(board, i + 1, j)
            dfs(board, i, j - 1)
            dfs(board, i, j + 1)
        }
    }

}









