package com.seniorlibs.algorithm.trie

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
        findViewById<View>(R.id.btn_num_is_lands).setOnClickListener(this)
        findViewById<View>(R.id.btn_find_circle_num).setOnClickListener(this)
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
                LogUtils.d(TAG, "79. 单词搜索：${exist(paths, "BCCED")}")
            }
            R.id.btn_num_is_lands -> {
                // 二维数组
//                val result = Array(10) { i ->
//                    Array(8) { j ->
//                        "the String at position $i, $j" // provide some initial value based on i and j
//                    }
//                }

                val grid: Array<CharArray> = Array(4) { charArrayOf() }
                grid[0] = charArrayOf('1', '1', '1', '1', '0')
                grid[1] = charArrayOf('1', '1', '0', '1', '0')
                grid[2] = charArrayOf('1', '1', '0', '0', '0')
                grid[3] = charArrayOf('0', '0', '0', '0', '0')

                LogUtils.e(TAG, "200. 岛屿数量——方法一：深度优先遍历DFS：${numIslands(grid)}")

                val grid1: Array<CharArray> = Array(4) { charArrayOf() }
                grid1[0] = charArrayOf('1', '1', '1', '1', '0')
                grid1[1] = charArrayOf('1', '1', '0', '1', '0')
                grid1[2] = charArrayOf('1', '1', '0', '0', '0')
                grid1[3] = charArrayOf('0', '0', '0', '0', '0')
                LogUtils.e(TAG, "200. 岛屿数量——方法二：广度优先遍历BFS：${numIslands1(grid1)}")
            }
            R.id.btn_find_circle_num -> {
                val grid: Array<IntArray> = Array(3) { intArrayOf() }
                grid[0] = intArrayOf(1,1,0)
                grid[1] = intArrayOf(1,1,0)
                grid[2] = intArrayOf(0,0,1)
                val unionFind = FindCircleNum()
                LogUtils.e(TAG, "547. 朋友圈——方法三：并查集（最优解）：${unionFind.findCircleNum(grid)}")

                val grid1: Array<IntArray> = Array(3) { intArrayOf() }
                grid1[0] = intArrayOf(1,1,0)
                grid1[1] = intArrayOf(1,1,0)
                grid1[2] = intArrayOf(0,0,1)
                LogUtils.e(TAG, "547. 朋友圈——方法一：深度优先遍历DFS：${findCircleNum2(grid1)}")

                val grid2: Array<IntArray> = Array(3) { intArrayOf() }
                grid2[0] = intArrayOf(1,1,0)
                grid2[1] = intArrayOf(1,1,0)
                grid2[2] = intArrayOf(0,0,1)
                LogUtils.e(TAG, "547. 朋友圈——方法二：广度优先遍历BFS：${findCircleNum3(grid2)}")
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
        if (board.isEmpty() || board[0].isEmpty() || word.isEmpty()) return false

        val words = word.toCharArray()
        for (i in board.indices) {
            for (j in board[0].indices) {
                // 从[i,j]这个坐标开始查找，只要有一处返回true，说明网格中能够找到相应的单词，否则不能找到
                if (dfs(board, words, i, j, 0)) return true
            }
        }
        return false
    }

    fun dfs(board: Array<CharArray>, words: CharArray, i: Int, j: Int, index: Int): Boolean {
        // 如果越界直接返回false。index表示的是查找到字符串word的第几个字符， 如果这个字符不等于board[i][j]，说明这个坐标路径是走不通的，直接返回false
        if (i < 0 || i >= board.size || j < 0 || j >= board[0].size || board[i][j] != words[index]) return false

        // 如果word的每个字符都查找完了，直接返回true
        if (index == words.size - 1) return true

        // 把当前坐标的值保存下来，为了在最后复原
        val tmp = board[i][j]
        // 然后修改当前坐标的值
        board[i][j] = '.'
        // 走递归，沿着当前坐标的上下左右4个方向查找
        val res = dfs(board, words, i + 1, j, index + 1)
                || dfs(board, words, i - 1, j, index + 1)
                || dfs(board, words, i, j + 1, index + 1)
                || dfs(board, words, i, j - 1, index + 1)
        // 递归之后再把当前的坐标复原
        board[i][j] = tmp
        return res
    }

    /**
     * 200. 岛屿数量——方法一：深度优先遍历DFS
     *
     * 目标：是找到矩阵中 “岛屿的数量” ，上下左右相连的 1 都被认为是连续岛屿。
     * 思想：遍历整个矩阵，当遇到 grid[i][j] == '1' 时，从此点开始做深度优先搜索 dfs，岛屿数 count + 1 且在深度优先搜索中删除此岛屿。
     *
     * 步骤：1.从岛屿中的某一点 (i, j)向此点的上下左右 (i+1,j),(i-1,j),(i,j+1),(i,j-1) 做深度搜索；
     *      2.终止条件：(i, j) 越过矩阵边界; grid[i][j] == 0，代表此分支已越过岛屿边界；
     *      3.搜索岛屿的同时，执行grid[i][j] = '0'，即将岛屿所有节点删除，以免之后重复搜索相同岛屿。
     *
     * 时间复杂度：O(mn)，其中m和n分别为行数和列数；
     * 空间复杂度：O(mn)，在最坏情况下，整个网格均为陆地，深度优先搜索的深度达到mn
     *
     * https://leetcode-cn.com/problems/number-of-islands/solution/200-dao-yu-shu-liang-dfsbfs-by-chen-li-guan/
     * @param grid
     * @return
     */
    fun numIslands(grid: Array<CharArray>): Int {
        var count = 0
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j)
                    count++
                }
            }
        }
        return count
    }

    private fun dfs(grid: Array<CharArray>, i: Int, j: Int) {
        if (i >= 0 && i < grid.size && j >= 0 && j < grid[0].size && grid[i][j] == '1') {
            grid[i][j] = '0'
            dfs(grid, i + 1, j)
            dfs(grid, i, j + 1)
            dfs(grid, i - 1, j)
            dfs(grid, i, j - 1)
        }
    }

    /**
     * 200. 岛屿数量——方法二：广度优先遍历BFS
     *
     * 步骤：1.借用一个队列 queue，判断队列首部节点 (i, j) 是否未越界且为1：
     *       （1）若是则置零（删除岛屿节点），并将此节点上下左右节点 (i+1,j),(i-1,j),(i,j+1),(i,j-1)加入队列；
     *       （2）若不是则跳过此节点；
     *      2.循环 poll 队列首节点，直到整个队列为空，此时已经遍历完此岛屿。
     *
     * 时间复杂度：O(mn)，其中m和n分别为行数和列数。
     * 空间复杂度：O(mn)，在最坏情况下，整个网格均为陆地，深度优先搜索的深度达到mn
     *
     * https://leetcode-cn.com/problems/number-of-islands/solution/200-dao-yu-shu-liang-dfsbfs-by-chen-li-guan/
     * @param grid
     * @return
     */
    fun numIslands1(grid: Array<CharArray>): Int {
        var count = 0
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j)
                    count++
                }
            }
        }
        return count
    }

    private fun bfs(grid: Array<CharArray>, i1: Int, j1: Int) {
        var i = i1
        var j = j1

        val queue: Queue<IntArray> = LinkedList()
        queue.offer(intArrayOf(i, j))
        while (!queue.isEmpty()) {
            val cur = queue.poll()
            i = cur[0]
            j = cur[1]

            if (i >= 0 && i < grid.size && j >= 0 && j < grid[0].size && grid[i][j] == '1') {
                grid[i][j] = '0'
                queue.offer(intArrayOf(i + 1, j))
                queue.offer(intArrayOf(i - 1, j))
                queue.offer(intArrayOf(i, j + 1))
                queue.offer(intArrayOf(i, j - 1))
            }
        }
    }


    /**
     * 547. 朋友圈——方法2：深度优先遍历DFS
     *
     * 题解：M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系
     * 思路：从一个特定点开始，访问所有邻接的节点。然后对于这些邻接节点，我们依然通过访问邻接节点的方式，知道访问所有可以到达的节点。因此，我们按照一层一层的方式访问节点
     *
     * 时间复杂度：O(n^2)，整个矩阵都要被遍历，大小为n^2；
     * 空间复杂度：O(n)，visited数组的大小。
     *
     * @param M
     * @return
     */
    fun findCircleNum2(M: Array<IntArray>): Int {
        // 标记学生是否被访问过
        val visited = BooleanArray(M.size)
        var count = 0
        for (i in M.indices) {
            if (!visited[i]) {
                // 从i层遍历：i：0,1,2....
                dfs(M, visited, i)
                count++
            }
        }
        return count
    }

    fun dfs(M: Array<IntArray>, visited: BooleanArray, i: Int) {
        for (j in M.indices) {
            // i和j是朋友，且j未被访问过
            if (M[i][j] == 1 && !visited[j]) {
                visited[j] = true
                // 递归下一层时，把j赋值给i，例如：j=1 -> i=1,从第1层遍历；j=3 -> i=3,从第3层遍历（即是：对角线，因为对角线是否互为朋友）
                dfs(M, visited, j)
            }
        }
    }

    /**
     * 547. 朋友圈——方法3：广度优先遍历BFS（太复杂，先不考虑）
     *
     * 时间复杂度：O(n^2)，整个矩阵都要被遍历，大小为n^2；
     * 空间复杂度：O(n)，visited数组的大小。
     *
     * @param M
     * @return
     */
    fun findCircleNum3(M: Array<IntArray>): Int {
        var count = 0
        if (M.isEmpty()) return count

        val visited = BooleanArray(M.size)
        // 1 创建一个队列
        val queue: Queue<Int> = LinkedList()

        // 2.1 遍历每层
        for (i in M.indices) {
            // 2.2 将未被遍历的同学i放入队列
            if (!visited[i]) {
                queue.offer(i)

                // 3.1 遍历每一层前，存下当前队列的长度
                var size = queue.size
                while (size > 0) {
                    // 3.2 取出同学i，标记为已访问
                    val s = queue.poll()
                    visited[s] = true

                    // 4 将这一层未被访问的同学全加入到对列中
                    for (j in M.indices) {
                        if (M[s][j] == 1 && !visited[j]) queue.offer(j)
                    }

                    // 4 修改size，继续按层遍历新加入队列的同学
                    size = queue.size
                }
                // 5 赋值到count中
                count++
            }
        }
        return count
    }
}
