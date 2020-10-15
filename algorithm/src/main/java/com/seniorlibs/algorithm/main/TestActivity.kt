package com.seniorlibs.algorithm.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.array.ArrayActivity
import com.seniorlibs.algorithm.db.DbActivity
import com.seniorlibs.baselib.utils.LogUtils
import java.util.*
import kotlin.collections.ArrayList

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/9/26
 * Mender:
 * Modify:
 * Description: 混合测试
 */
class TestActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "TestActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, TestActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_remove_duplicates).setOnClickListener(this)
        findViewById<View>(R.id.btn_move_zeroes).setOnClickListener(this)
        findViewById<View>(R.id.btn_three_sum).setOnClickListener(this)
        findViewById<View>(R.id.btn_four_sum).setOnClickListener(this)
        findViewById<View>(R.id.btn_bracket_generate).setOnClickListener(this)
        findViewById<View>(R.id.btn_rob_two).setOnClickListener(this)
        findViewById<View>(R.id.btn_unique_paths_with_obstacles).setOnClickListener(this)
        findViewById<View>(R.id.btn_max_depth).setOnClickListener(this)
        findViewById<View>(R.id.btn_min_depth).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_remove_duplicates -> {
                LogUtils.e(TAG, "26. 删除排序数组中的重复项：${removeDuplicates(intArrayOf(1, 1, 2))}")
            }
            R.id.btn_move_zeroes -> {
                LogUtils.e(TAG, "283. 移动零：${moveZeroes(intArrayOf(0, 1, 0, 3, 12))}")
            }
            R.id.btn_three_sum -> {
                val nums: IntArray = intArrayOf(-4, -1, -1, -1, 0, -1, 1, 2)
                LogUtils.d(TAG, "15. 三数之和：${threeSum(nums)}")
            }
            R.id.btn_four_sum -> {
                val nums: IntArray = intArrayOf(1, 0, -1, 0, -2, 2)
                LogUtils.d(TAG, "18. 四数之和：${fourSum(nums, 0)}")
            }
            R.id.btn_bracket_generate -> {
                list.clear()
                LogUtils.e(TAG, "22. 括号生成：${generateParenthesis(3)}")
            }
            R.id.btn_rob_two -> {
                LogUtils.e(TAG, "213. 打家劫舍 II：${rob(intArrayOf(2, 7, 9, 3, 1))}")
            }
            R.id.btn_unique_paths -> {
                LogUtils.e(TAG, "62. 不同路径：${uniquePaths(3, 7)}")
            }
            R.id.btn_unique_paths_with_obstacles -> {
//                val paths = arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 0), intArrayOf(0, 0, 0))
                val paths = arrayOf(intArrayOf(0), intArrayOf(1))
                LogUtils.e(TAG, "63. 不同路径 II：${uniquePathsWithObstacles(paths)}")
            }
            R.id.btn_max_depth -> {
                val node = TreeNode(1)
                val node1 = TreeNode(5)
                node.left = node1
                val node2 = TreeNode(8)
                node1.left = node2
                val node3 = TreeNode(10)
                node2.left = node3
                node1.right = TreeNode(4)
                LogUtils.e(TAG, "104. 二叉树的最大深度：${maxDepth(node)}") // 4
                LogUtils.e(TAG, "104. 二叉树的最大深度1：${maxDepth1(node)}") // 4
            }
            R.id.btn_min_depth -> {
                val node = TreeNode(1)
                val node1 = TreeNode(5)
                node.left = node1
                val node2 = TreeNode(8)
                node1.left = node2
                val node3 = TreeNode(10)
                node2.left = node3
                node1.right = TreeNode(4)
                LogUtils.e(TAG, "111. 二叉树的最小深度：${minDepth(node)}") // 3
                LogUtils.e(TAG, "111. 二叉树的最小深度1：${minDepth1(node)}") // 3
            }
            else -> {
            }
        }
    }

    /**
     * 104. 二叉树的最大深度 DFS
     *
     * @param node
     * @return
     */
    private fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0

        val left = maxDepth(root.left)

        val right = maxDepth(root.right)

        return Math.max(left, right) + 1
    }

    /**
     * 104. 二叉树的最大深度 BFS
     *
     * @param node
     * @return
     */
    private fun maxDepth1(root: TreeNode?): Int {
        if (root == null) return 0

        val queue : Queue<TreeNode> = LinkedList<TreeNode>()
        var level = 0
        queue.offer(root)

        var size = queue.size
        while (size > 0) {
            level++
            for (i in 0 until size) {
                val node = queue.poll()
                if (node.left != null) queue.offer(node.left)
                if (node.right != null) queue.offer(node.right)
            }
            size = queue.size
        }

        return level
    }

    /**
     * 111. 二叉树的最小深度
     *
     * @param node
     * @return
     */
    private fun minDepth(root: TreeNode?): Int {
        if (root == null) return 0

        val left = minDepth(root.left)

        val right = minDepth(root.right)

        return if (left == 0 || right == 0) { left + right + 1 } else { Math.min(left, right) + 1 }
    }

    /**
     * 111. 二叉树的最小深度
     *
     * @param node
     * @return
     */
    private fun minDepth1(root: TreeNode?): Int {
        if (root == null) return 0

        val queue : Queue<TreeNode> = LinkedList<TreeNode>()
        var level = 0
        queue.offer(root)

        var size = queue.size
        while (size > 0) {
            level++
            for (i in 0 until size) {
                val node = queue.poll()
                if (node.left == null && node.right == null) return level

                if (node.left != null) queue.offer(node.left)
                if (node.right != null) queue.offer(node.right)
            }
            size = queue.size
        }

        return level
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    /**
     * 26. 删除排序数组中的重复项
     */
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.size < 2) return nums.size

        var i = 0  // 不重复指针-慢指针
        var j = 1 // 快指针

        while (j < nums.size) {
            if (nums[i] != nums[j]) { // 不重复项才需要操作
                i++ // 1.移动不重复指针指向下一个待填充位置： 避免：如果没有重复数据，q指向的元素原地复制了一遍
                if (i != j) { // 2.判断当前指针和不重复指针的差异，如果不等，说明不重复指针和快指针之间都是某个相等数字（否则，当前项是没移动过的，不能清空）
                    nums[i] = nums[j]  // 3.把当前的值赋予不重复指针指针
                }
            }

            j++ // 4.移动快指针指向下一个待填充位置
        }

        // 返回不重复指针+1位置
        return i + 1
    }

    /**
     * 283. 移动零
     *
     * @param nums
     */
    fun moveZeroes(nums: IntArray): Unit {
        var i = 0 // 非零指针-慢指针
        var j = 0  // 快指针

        while (j < nums.size) {
            if (nums[j] != 0) {  // 非零项才需要操作
                if (i != j) {  // 1.判断当前指针和非零指针的差异，如果不等，说明非零指针和快指针之间都是零（否则，当前项是没移动过的，不能清空）
                    nums[i] = nums[j]  // 2.把 当前指针 赋值给 非零指针
                    nums[j] = 0   // 把 当前指针 赋值为 0
                }

                i++
            }

            j++ // 3.移动非零指针指向下一个待填充位置
        }
    }

    /**
     * 15. 三数之和
     * 判断nums中是否存在三个元素a，b，c，使得a+b+c=0，转化为->a+b=-c
     *
     * @param nums
     * @return
     */
    private fun threeSum(nums: IntArray): List<List<Int>> {
        val res = mutableListOf<MutableList<Int>>()
        if (nums.size < 3) return res

        // 1 排序
        Arrays.sort(nums)

        // 2 k
        for (k in 0 until nums.size - 2) {
            if (k > 0 && nums[k] == nums[k - 1]) continue

            var i = k + 1
            var j = nums.size - 1

            /* 获取当前最小值，如果最小值比目标值大，说明整个循环越来越大的值根本没戏 */
            val min1 = nums[k] + nums[i] + nums[i + 1]
            if (min1 > 0) {
                break
            }
            /* 获取当前最大值，如果最大值比目标值小，说明当前这一轮循环后面越来越小的值根本没戏，但是不能break，下一轮f+还存在==target */
            val max1 = nums[k] + nums[nums.size - 1] + nums[nums.size - 2]
            if (max1 < 0) {
                continue
            }

            // 3 i < j
            while (i < j) {
                val sum = nums[k] + nums[i] + nums[j]

                when {
                    sum < 0 -> {
                        while (i < j && nums[i] == nums[++i]) {
                        }
                    }
                    sum > 0 -> {
                        while (i < j && nums[j] == nums[--j]) {
                        }
                    }
                    sum == 0 -> {
                        res.add(mutableListOf(nums[k], nums[i], nums[j]))
                        while (i < j && nums[i] == nums[++i]) {
                        }
                        while (i < j && nums[j] == nums[--j]) {
                        }
                    }
                }
            }
        }

        return res
    }

    /**
     * 18. 四数之和
     *
     * @param nums
     * @param target
     * @return
     */
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        val res = mutableListOf<MutableList<Int>>()
        if (nums.size < 4) return res

        // 1 排序
        Arrays.sort(nums)

        // 2 f
        for (f in 0 until nums.size - 3) {
            if (f > 0 && nums[f] == nums[f - 1]) continue

            /* 获取当前最小值，如果最小值比目标值大，说明整个循环越来越大的值根本没戏 */
            val min = nums[f] + nums[f + 1] + nums[f + 2] + nums[f + 3]
            if (min > target) {
                break
            }
            /* 获取当前最大值，如果最大值比目标值小，说明当前这一轮循环后面越来越小的值根本没戏，但是不能break，下一轮f+还存在==target */
            val max = nums[f] + nums[nums.size - 1] + nums[nums.size - 2] + nums[nums.size - 3]
            if (max < target) {
                continue
            }

            // 3 k
            for (k in f + 1 until nums.size - 2) {
                if (k > f + 1 && nums[k] == nums[k - 1]) continue

                var i = k + 1
                var j = nums.size - 1

                /* 获取当前最小值，如果最小值比目标值大，说明整个循环越来越大的值根本没戏 */
                val min1 = nums[f] + nums[k] + nums[k + 1] + nums[k + 2]
                if (min1 > target) {
                    break
                }
                /* 获取当前最大值，如果最大值比目标值小，说明当前这一轮循环后面越来越小的值根本没戏，但是不能break，下一轮f+还存在==target */
                val max1 = nums[f] + nums[k] + nums[nums.size - 1] + nums[nums.size - 2]
                if (max1 < target) {
                    continue
                }

                // 4 i < j
                while (i < j) {
                    val sum = nums[f] + nums[k] + nums[i] + nums[j]

                    when {
                        sum < target -> {
                            while (i < j && nums[i] == nums[++i]) {
                            }
                        }
                        sum > target -> {
                            while (i < j && nums[j] == nums[--j]) {
                            }
                        }
                        sum == target -> {
                            res.add(mutableListOf(nums[f], nums[k], nums[i], nums[j]))
                            while (i < j && nums[i] == nums[++i]) {
                            }
                            while (i < j && nums[j] == nums[--j]) {
                            }
                        }
                    }
                }
            }
        }

        return res
    }

    /**
     * 22. 括号生成
     *
     * 时间复杂度：O(4^n)，n值对应的决策树有2*n层，节点个数是1,2,4,8......，应该有2^{2n} - 1个节点，每个节点代表一个子问题，需要用O(1)O(1)时间解决，时间复杂度为O(2^{2n} - 1) = O(4^n)
     * 空间复杂度：O(n)，除了答案数组之外，需要的空间取决于递归栈的深度，每一层递归函数需要O(1)的空间，最多递归2n层，因此空间复杂度为O(n)。
     *
     * https://leetcode-cn.com/problems/generate-parentheses/
     * @param n
     * @return
     */
    fun generateParenthesis(n: Int): List<String> {
        if (n == 0) return list

        generate(0, 0, n, "")

        return list
    }

    val list = mutableListOf<String>()

    private fun generate(left: Int, right: Int, n: Int, s: String) {
        // 1.递归终结条件
        if (left > n || left < right) {
            return
        }

        // 2.处理当前层逻辑
        if (left == n && right == n) {
            list.add(s)
        }

        // 3. 下探到下一层
        if (left < n) {
            generate(left + 1, right, n, s + '(')
        }

        if (left > right) {
            generate(left, right + 1, n, s + ')')
        }

        // 4. 恢复状态
    }


    /**
     * 213. 打家劫舍 II
     *
     * @param nums
     * @return
     */
    fun rob(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums[0]

        return Math.max(
            rob1(Arrays.copyOfRange(nums, 0, nums.size - 1)),
            rob1(Arrays.copyOfRange(nums, 1, nums.size))
        )
    }

    fun rob1(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums[0]

        var pre = nums[0]
        var cur = Math.max(nums[0], nums[1])
        var temp = 0
        for (i in 2 until nums.size) {
            temp = cur
            cur = Math.max(pre + nums[i], cur)
            pre = temp
        }
        return cur
    }

    /**
     * 62. 不同路径
     *
     * @param m
     * @param n
     * @return
     */
    fun uniquePaths(m: Int, n: Int): Int {
        if (m == 0 || n == 0) return 0

        // base case
        val dp = IntArray(n)
        for (j in 0 until n) {
            dp[j] = 1
        }

        // dp
        for (i in 1 until m) {
            for (j in 1 until n) {
                dp[j] = dp[j] + dp[j - 1]
            }
        }

        return dp[n - 1]
    }


    /**
     * 63. 不同路径 II
     *
     * @param obstacleGrid
     * @return
     */
    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        if (obstacleGrid.isEmpty() || obstacleGrid[0].isEmpty()) return 0

        // base case
        val m = obstacleGrid.size
        val n = obstacleGrid[0].size

        // base case
        val dp = IntArray(n)
        dp[0] = if (obstacleGrid[0][0] == 0) 1 else 0

        // db
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0
                    continue
                }

                if (j > 1 && obstacleGrid[i][j] == 0) {
                    dp[j] = dp[j] + dp[j - 1]
                }
            }
        }

        return dp[n - 1]
    }
}
