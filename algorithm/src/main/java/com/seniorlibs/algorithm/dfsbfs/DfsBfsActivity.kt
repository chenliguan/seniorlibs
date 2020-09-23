package com.seniorlibs.algorithm.dfsbfs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.baselib.utils.LogUtils
import java.util.*

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/9/10
 * Mender:
 * Modify:
 * Description: 深度优先遍历DFS、广度优先遍历BFS
 */
class DfsBfsActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "DfsBfsActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, DfsBfsActivity::class.java)
            context.startActivity(intent)
        }
    }

    private var list: MutableList<Int> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dfs_bfs)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_largest_values).setOnClickListener(this)
        findViewById<View>(R.id.btn_num_is_lands).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_largest_values -> {
                val node = TreeNode(1)
                val node_left = TreeNode(3)
                val node_right = TreeNode(2)
                node.left = node_left
                node.right = node_right

                val node_right1 = TreeNode(3)
                node_left.right = node_right1
                val node_left1 = TreeNode(5)
                node_left.left = node_left1

                val node_right2 = TreeNode(9)
                node_right.right = node_right2

                LogUtils.e(TAG, "515. 在每个树行中找最大值 -- 方法一：BFS广度遍历-迭代：${largestValues(node)}")
                LogUtils.e(TAG, "515. 在每个树行中找最大值 -- 方法二：DFS深度遍历-递归：${largestValues1(node)}")
            }
            R.id.btn_num_is_lands -> {
                // 二维数组
//                val result = Array(10) { i ->
//                    Array(8) { j ->
//                        "the String at position $i, $j" // provide some initial value based on i and j
//                    }
//                }

                val grid: Array<CharArray> = Array(4) { i ->
                    charArrayOf()
                }
                grid[0] = charArrayOf('1', '1', '1', '1', '0')
                grid[1] = charArrayOf('1', '1', '0', '1', '0')
                grid[2] = charArrayOf('1', '1', '0', '0', '0')
                grid[3] = charArrayOf('0', '0', '0', '0', '0')

                LogUtils.e(TAG, "200. 岛屿数量——方法一：深度优先遍历DFS：${numIslands(grid)}")

                val grid1: Array<CharArray> = Array(4) { i ->
                    charArrayOf()
                }
                grid1[0] = charArrayOf('1', '1', '1', '1', '0')
                grid1[1] = charArrayOf('1', '1', '0', '1', '0')
                grid1[2] = charArrayOf('1', '1', '0', '0', '0')
                grid1[3] = charArrayOf('0', '0', '0', '0', '0')
                LogUtils.e(TAG, "200. 岛屿数量——方法二：广度优先遍历BFS：${numIslands1(grid1)}")
            }
            else -> {
            }
        }
    }


    class Node(var `val`: Int) {
        var children = mutableListOf<Node>()
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    /**
     * 515. 在每个树行中找最大值 -- 方法一：BFS广度遍历-迭代：
     *
     * 思想：每次递归的时候都需要带一个 index(表示当前的层数)，也就对应那个田字格子中的第几行，如果 当前值 比 此位置的值 大，就用 当前值 覆盖。
     *
     * 时间复杂度：O(n)，每个点进队出队各一次，故渐进时间复杂度为 O(n)；
     * 空间复杂度：O(n)，队列中元素的个数不超过 nn 个，故渐进空间复杂度为 O(n)。
     *
     * https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/solution/515-zai-mei-ge-shu-xing-zhong-zhao-zui-da-zhi-by-c/
     *
     * @param root
     * @return
     */
    fun largestValues(root: TreeNode?): List<Int> {
        val res: MutableList<Int> = mutableListOf()
        val deque: Deque<TreeNode> = LinkedList()     // 1 创建一个队列，将根节点放入其中

        if (root == null) return res else deque.offer(root)

        while (!deque.isEmpty()) {
            var max: Int = Int.MIN_VALUE
            val size = deque.size     // 2.1 遍历每一层前，存下当前队列的长度

            for (i in 0 until size) {   // 2.2 将这一层的元素全部取出，因为长度已确定，不会遍历新加入的左右节点
                val node = deque.poll()
                if (node?.`val`!! > max) {     // 2.3 记录每层的最大值
                    max = node.`val`
                }

                val left = node.left   // 3 如果节点的左右孩子不为空，放入队列
                val right = node.right
                if (left != null) deque.offer(left)
                if (right != null) deque.offer(right)
            }

            res.add(max)
        }

        return res
    }

    /**
     * 515. 在每个树行中找最大值 -- 方法二：DFS深度遍历-递归：
     *
     * 时间复杂度：O(n)，每个点进队出队各一次，故渐进时间复杂度为 O(n)；
     * 空间复杂度：O(h)，h 是树的高度。
     *
     * https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/solution/515-zai-mei-ge-shu-xing-zhong-zhao-zui-da-zhi-by-c/
     * @param root
     * @return
     */
    fun largestValues1(root: TreeNode?): List<Int> {
        val res: MutableList<Int> = mutableListOf()
        if (root == null) return res

        dfsLargest(1, root, res) // index指第几层，从第1层开始，传入1
        return res
    }

    fun dfsLargest(index: Int, root: TreeNode?, res: MutableList<Int>) {
        if (root == null) return

        if (index > res.size) {
            res.add(index - 1, Int.MIN_VALUE)  // index-1是符合从0开始的集合；res必须先add，否则报空指针
        }
        if (root.`val` > res[index - 1]) {  // 如果 当前值 比 此位置的值 大，就用 当前值 覆盖。
            res[index - 1] = root.`val`
        }

        if (root.left != null) {   // 递归的处理左子树，右子树，同时将层数：index+1
            dfsLargest(index + 1, root.left, res)
        }
        if (root.right != null) {
            dfsLargest(index + 1, root.right, res)
        }
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

    private fun bfs(grid: Array<CharArray>, i: Int, j: Int) {
        var i = i
        var j = j

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
}
