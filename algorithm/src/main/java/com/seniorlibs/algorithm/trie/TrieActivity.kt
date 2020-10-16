package com.seniorlibs.algorithm.trie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.baselib.utils.LogUtils

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/10/16
 * Mender:
 * Modify:
 * Description: 字典树
 */
class TrieActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "TrieActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, TrieActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trie)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_achieve_trie).setOnClickListener(this)
        findViewById<View>(R.id.btn_exist).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_achieve_trie -> {
                val trie = Trie()
                LogUtils.d(TAG, "208. 实现 Trie (前缀树) + 插入：${trie.insert("apple")}")
                LogUtils.d(TAG, "208. 实现 Trie (前缀树) + 查找：${trie.search("apple")}")
                LogUtils.d(TAG, "208. 实现 Trie (前缀树) + startsWith：${trie.startsWith("app")}")
            }
            R.id.btn_exist -> {
                val paths = arrayOf(
                    charArrayOf('A', 'B', 'C', 'E'),
                    charArrayOf('S', 'F', 'C', 'S'),
                    charArrayOf('A', 'D', 'E', 'E')
                )
                LogUtils.d(TAG, "79. 单词搜索：${exist(paths, "ABCCED")}")
            }
            else -> {
            }
        }
    }

    /**
     * 79. 单词搜索  解法 dfs回溯算法
     *
     * 时间复杂度：O(mn*3^h)，其中m, n为网格的长度与宽度，h为字符串word的长度。在每次调用函数dfs时，
     * 除了第一次可以进入4个分支以外，其余最多会进入3个分支（每个位置只能走一次，走过的分支没法走回去）。
     * 由于单词长为h，故时间复杂度是O(3^h)，而且还要执行O(mn)次检查。然而，由于剪枝的存在，
     * 在遇到不匹配或已访问的字符时会提前退出，终止递归流程。因此，实际的时间复杂度会远远小于O(mn*3^h)。
     *
     * 空间复杂度：O(min(h, n))。额外开辟了O(n)的数组，同时栈的深度最大为O(min(h, n))
     *
     * @param board
     * @param word
     * @return
     */
    fun exist(board: Array<CharArray>, word: String): Boolean {
        val words = word.toCharArray()
        for (i in board.indices) {
            for (j in board[0].indices) {
                // 从[i,j]这个坐标开始查找
                if (dfs(board, words, i, j, 0)) return true
            }
        }
        return false
    }

    fun dfs(board: Array<CharArray>, word: CharArray, i: Int, j: Int, index: Int): Boolean {
        // 边界的判断，如果越界直接返回false。index表示的是查找到字符串word的第几个字符，
        // 如果这个字符不等于board[i][j]，说明验证这个坐标路径是走不通的，直接返回false
        if (i >= board.size || i < 0 || j >= board[0].size || j < 0 || board[i][j] != word[index]) return false
        // 如果word的每个字符都查找完了，直接返回true
        if (index == word.size - 1) return true

        // 把当前坐标的值保存下来，为了在最后复原
        val tmp = board[i][j]
        // 然后修改当前坐标的值
        board[i][j] = '.'
        // 走递归，沿着当前坐标的上下左右4个方向查找
        val res = dfs(board, word, i + 1, j, index + 1)
                || dfs(board, word, i - 1, j, index + 1)
                || dfs(board, word, i, j + 1, index + 1)
                || dfs(board, word, i, j - 1, index + 1)
        // 递归之后再把当前的坐标复原
        board[i][j] = tmp
        return res
    }
}
