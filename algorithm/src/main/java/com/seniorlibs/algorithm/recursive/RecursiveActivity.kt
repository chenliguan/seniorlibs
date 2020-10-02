package com.seniorlibs.algorithm.recursive

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.baselib.utils.LogUtils
import java.util.*
import kotlin.collections.ArrayList


/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/9/10
 * Mender:
 * Modify:
 * Description: 递归
 */
class RecursiveActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "RecursiveActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, RecursiveActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recursive)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_climb_stairs).setOnClickListener(this)
        findViewById<View>(R.id.btn_fib).setOnClickListener(this)
        findViewById<View>(R.id.btn_unique_paths).setOnClickListener(this)
        findViewById<View>(R.id.btn_unique_paths_with_obstacles).setOnClickListener(this)
        findViewById<View>(R.id.btn_bracket_generate).setOnClickListener(this)
        findViewById<View>(R.id.btn_my_pow).setOnClickListener(this)
        findViewById<View>(R.id.btn_subsets).setOnClickListener(this)
        findViewById<View>(R.id.btn_longest_common_sub_sequence).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_climb_stairs -> {
                LogUtils.e(TAG, "70. 爬楼梯1：${climbStairs(5)}")
                LogUtils.e(TAG, "70. 爬楼梯2：${climbStairs1(5)}")
                LogUtils.e(TAG, "70. 爬楼梯3：${climbStairs3(5)}")
                LogUtils.e(TAG, "70. 爬楼梯4：${climbStairs4(5)}")
            }
            R.id.btn_fib -> {
                LogUtils.e(TAG, "509. 斐波那契数：${fib1(5)}")
                LogUtils.e(TAG, "509. 斐波那契数：${fib2(5)}")
                LogUtils.e(TAG, "509. 斐波那契数：${fib3(5)}")
                LogUtils.e(TAG, "509. 斐波那契数：${fib4(5)}")
            }
            R.id.btn_unique_paths -> {
                LogUtils.e(TAG, "62. 不同路径：${uniquePaths(3, 7)}")
                LogUtils.e(TAG, "62. 不同路径：${uniquePaths1(3, 7)}")
            }
            R.id.btn_unique_paths_with_obstacles -> {
                val paths = arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 0), intArrayOf(0, 0, 0))
                LogUtils.e(TAG, "63. 不同路径 II：${uniquePathsWithObstacles(paths)}")
                LogUtils.e(TAG, "63. 不同路径 II：${uniquePathsWithObstacles1(paths)}")
            }
            R.id.btn_longest_common_sub_sequence -> {
                LogUtils.e(TAG, "1143. 最长公共子序列：${longestCommonSubsequence("ace", "abcde")}")
            }
            R.id.btn_bracket_generate -> {
                list.clear()
                LogUtils.e(TAG, "22. 括号生成：${generateParenthesis(3)}")
            }
            R.id.btn_my_pow -> {
                list.clear()
                LogUtils.e(TAG, "50. Pow(x, n)：${myPow(2.00000, 10)}")
            }
            R.id.btn_subsets -> {
                listI.clear()
                res.clear()
                LogUtils.e(TAG, "78. 子集：${subsets(intArrayOf(1, 2, 3))}")
            }

            else -> {
            }
        }
    }

    /**
     * 动态规划
     */

    /**
     * 70. 爬楼梯  方法一：动态规划
     *
     * 时间复杂度：循环执行n次，每次花费常数的时间代价，时间复杂度为 O(n)；
     * 空间复杂度：用了n空间的数组辅助，空间复杂度为 O(n)。
     *
     * @param n
     * @return
     */
    fun climbStairs(n: Int): Int {
        if (n <= 2) return 2

        val res = IntArray(n + 1)

        res[1] = 1
        res[2] = 2

        for (i in 3 until n + 1) {
            res[i] = res[i - 1] + res[i - 2]
        }

        return res[n]
    }

    /**
     * 70. 爬楼梯  方法二：动态规划优化，斐波那契数。数组当前值是依赖他前面两个值的（前两个除外），我们只需要用两个临时变量即可，不需要申请一个数组
     *
     * 时间复杂度：循环执行n次，每次花费常数的时间代价，时间复杂度为 O(n)；
     * 空间复杂度：只用了常数个变量作为辅助空间，空间复杂度为 O(1)。
     *
     * @param n
     * @return
     */
    fun climbStairs1(n: Int): Int {
        if (n <= 2) return n

        var first = 1
        var second = 2
        var sum = 0

        for (i in 3 until n + 1) {
            sum = first + second
            first = second
            second = sum
        }

        return sum
    }

    /**
     * 70. 爬楼梯 方法三：暴力递归
     *
     * 时间复杂度：O(2^n)。树形递归的大小为2^n；
     * 空间复杂度：O(n)。递归树的深度可以达到n
     *
     * @param n
     * @return
     */
    fun climbStairs3(n: Int): Int {
        if (n <= 2) return n

        return climbStairs3(n - 1) + climbStairs3(n - 2)
    }

    /**
     * 70. 爬楼梯  方法三：记忆化递归
     *
     * 时间复杂度：O(n)。树形递归的大小可以达到 n；
     * 空间复杂度：O(n)。递归树的深度可以达到 n
     *
     * @param n
     * @return
     */
    fun climbStairs4(n: Int): Int {

        return climbStair(n, 1, 2)
    }

    fun climbStair(n: Int, first: Int, second: Int): Int {
        if (n <= 1) return first
        if (n == 2) return second

        return climbStair(n - 1, second, first + second)  // 5 -> 4 -> 3 (2->1)
    }


    /**
     * 509. 斐波那契数  解法一：暴力递归(自顶向下)
     *
     * 时间复杂度：O(2^N)。这是计算斐波那契数最慢的方法。因为它需要指数的时间。
     * 空间复杂度：O(N)，在堆栈中需要与 N 成正比的空间大小。该堆栈跟踪 fib(N) 的函数调用，随着堆栈的不断增长如果没有足够的内存则会导致 StackOverflowError。
     *
     * @param n
     * @return
     */
    fun fib1(n: Int): Int {
        if (n < 2) return n

        return fib1(n - 1) + fib1(n - 2)
    }

    /**
     * 509. 斐波那契数   解法二：备忘录递归(自顶向下)，解法一递归的升级版
     *
     * 思想：比解法一多了个"备忘录"储存，"剪枝"处理技巧，可以去除重复的调用计算
     *
     * 时间复杂度：O(N)。
     * 空间复杂度：O(N)，内存中使用的堆栈大小
     *
     * @param n
     * @return
     */
    private var cache = arrayOfNulls<Int>(31)

    fun fib2(n: Int): Int {
        if (n < 2) return n

        cache[0] = 0
        cache[1] = 1
        return memoize(n)
    }

    private fun memoize(n: Int): Int {
        if (cache[n] != null) {
            return cache[n]!!   // 如果 N 对应的斐波那契数存在，则返回
        }

        cache[n] = memoize(n - 1) + memoize(n - 2)  // 计算 N 对应的斐波那契数为 memoize(N-1) + memoize(N-2)
        return memoize(n)
    }

    /**
     * 509. 斐波那契数    解法三：动态规划(自底向上)，解法二备忘录存储技巧的升级
     *
     * 思想：自底向上通过迭代计算子问题并存储已计算的值，通过已计算的值进行计算，减少递归带来的重复计算
     *
     * 时间复杂度：O(N)。
     * 空间复杂度：O(N)，使用了空间大小为 N 的数组
     *
     * @param n
     * @return
     */
    fun fib3(n: Int): Int {
        if (n < 2) return n

        val res = IntArray(n + 1)

        res[0] = 0
        res[1] = 1

        for (i in 2 until n + 1) {
            res[i] = res[i - 1] + res[i - 2]
        }

        return res[n]
    }

    /**
     * 509. 斐波那契数   解法四：动态规划(自底向上)
     *
     * 解法三的优化，利用(状态压缩技巧)，其实状态只跟前一个数和当前数有关，不需要像第三种解法那样用一个数组进行存储，只需用两个值存储即可
     *
     * 时间复杂度：O(N)。
     * 空间复杂度：O(1)，仅仅使用了 current，prev1，prev2
     *
     * @param n
     * @return
     */
    fun fib4(n: Int): Int {
        if (n == 0) return 0
        if (n == 1 || n == 2) return 1

        var pre = 1   // fib(1)
        var cur = 1   // fib(2)

        for (i in 3 until n + 1) {
            val sum = pre + cur
            pre = cur
            cur = sum
        }

        return cur
    }

    /**
     * 62. 不同路径  解法一：动态规划(自底向上)
     *
     * 时间复杂度：O(mn)，其中m和n分别为行数和列数；
     * 空间复杂度：O(mn)，使用了空间大小为mn的数组
     *
     * https://leetcode-cn.com/problems/unique-paths/solution/62-bu-tong-lu-jing-by-chen-li-guan/
     * @param m
     * @param n
     * @return
     */
    fun uniquePaths(m: Int, n: Int): Int {
        if (m == 0 || n == 0) return 0

        val dp = Array(m) { IntArray(n) }
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1   // 0列 和 0行
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
                }
            }
        }
        return dp[m - 1][n - 1]
    }

    /**
     * 62. 不同路径  解法二：动态规划(自底向上)
     *
     * 解法一的优化，利用(状态压缩技巧)，其实状态只跟最近一行有关，不需要像第一种解法那样用两个数组进行存储，只需用一个数组存储即可
     *
     * 时间复杂度：O(mn)，其中m和n分别为行数和列数；
     * 空间复杂度：O(n)，使用了空间大小为n的数组
     *
     * https://leetcode-cn.com/problems/unique-paths/solution/62-bu-tong-lu-jing-by-chen-li-guan/
     * @param m
     * @param n
     * @return
     */
    fun uniquePaths1(m: Int, n: Int): Int {
        if (m == 0 || n == 0) return 0

        val dp = IntArray(n)
        Arrays.fill(dp, 1)
        for (i in 1 until m) {
            for (j in 1 until n) {
                dp[j] += dp[j - 1]
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
        if (obstacleGrid.isEmpty()) return 0

        // 定义 dp 数组并初始化第 1 行和第 1 列。
        val m = obstacleGrid.size
        val n: Int = obstacleGrid[0].size
        val dp = Array(m) { IntArray(n) }

        var i = 0
        while (i < m && obstacleGrid[i][0] == 0) {
            dp[i++][0] = 1
        }
        var j = 0
        while (j < n && obstacleGrid[0][j] == 0) {
            dp[0][j++] = 1
        }

        // 根据状态转移方程 dp[i][j] = dp[i - 1][j] + dp[i][j - 1] 进行递推。
        for (i in 1 until m) {
            for (j in 1 until n) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
                }
            }
        }
        return dp[m - 1][n - 1]
    }

    /**
     * 63. 不同路径 II
     *
     * @param obstacleGrid
     * @return
     */
    fun uniquePathsWithObstacles1(obstacleGrid: Array<IntArray>): Int {
        if (obstacleGrid.isEmpty()) return 0

        val n = obstacleGrid.size
        val m: Int = obstacleGrid[0].size
        val dp = IntArray(m)

        dp[0] = if (obstacleGrid[0][0] == 0) 1 else 0
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0
                    continue
                }
                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                    dp[j] += dp[j - 1]
                }
            }
        }
        return dp[m - 1]
    }


    /**
     * 1143. 最长公共子序列  方法1：动态规划（自底向上）
     *
     * 时间复杂度：O(mn)，其中m和n分别为行数和列数；
     * 空间复杂度：O(mn)，使用了空间大小为mn的数组
     *
     * @param text1
     * @param text2
     * @return
     */
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        val m: Int = text1.length
        val n: Int = text2.length
        val c1: CharArray = text1.toCharArray()
        val c2: CharArray = text2.toCharArray()
        val dp = Array(m + 1) { IntArray(n + 1) }

        /* dp默认0行或0列是0 */
        for (i in 1..m) {
            for (j in 1..n) {
                if (c1[i - 1] == c2[j - 1]) {
                    /* 如果末端相同，去找它们各往后退一格的值加1即可 */
                    dp[i][j] = dp[i - 1][j - 1] + 1
                } else {
                    /* 如果末端不同，要么是text1往后退一格，要么是text2往前退一格，两个的最大值 */
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }
        return dp[m][n]
    }


    /**
     * 120. 三角形最小路径和
     *
     * @param triangle
     * @return
     */
    fun minimumTotal(triangle: List<List<Int>>): Int {
        val n = triangle.size
        // dp[i][j] 表示从点 (i, j) 到底边的最小路径和。
        val dp = Array(n + 1) { IntArray(n + 1) }
        // 从三角形的最后一行开始递推。
        for (i in n - 1 downTo 0) {
            for (j in 0..i) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j]
            }
        }
        return dp[0][0]
    }

    /**
     * 回溯算法关键在于:不合适就退回上一步，然后通过约束条件, 减少时间复杂度。
     *
     * DFS是一个劲的往某一个方向搜索，而回溯算法建立在DFS基础之上的，但不同的是在搜索过程中，达到结束条件后，恢复状态，回溯上一层，再次搜索。
     * 因此回溯算法与 DFS 的区别就是有无状态重置
     */

    /**
     * 22. 括号生成
     *
     * 思路：left随时可以加，只要别用完(n) ; right必须之前有左括号，左个数>右个数
     *
     * 时间复杂度：O(4^n)，n值对应的决策树有2*n层，节点个数是1,2,4,8......，应该有2^{2n} - 1个节点，每个节点代表一个子问题，需要用O(1)O(1)时间解决，时间复杂度为O(2^{2n} - 1) = O(4^n)
     * 空间复杂度：O(n)，除了答案数组之外，需要的空间取决于递归栈的深度，每一层递归函数需要O(1)的空间，最多递归2n层，因此空间复杂度为O(n)。
     *
     * https://leetcode-cn.com/problems/generate-parentheses/
     * @param n
     * @return
     */
    fun generateParenthesis(n: Int): List<String> {
        generate(0, 0, n, "")
        return list
    }

    val list: MutableList<String> = mutableListOf()

    private fun generate(left: Int, right: Int, n: Int, s: String) {
        // 1.递归终结条件（最先写） // 肯定不合法，提前结束，即“剪枝”
        if (left > n || left < right) {
            return
        }
        if (left == n && right == n) {
            // 2.处理当前层逻辑
            list.add(s)
            return
        }

        // 3.下探到下一层
        if (left < n) {  // left随时可以加，只要别用完(n)
            generate(left + 1, right, n, s + "(")
        }

        if (left > right) { // right必须之前有左括号，左个数>右个数
            generate(left, right + 1, n, s + ")")
        }

        // 4.清理恢复当前层
    }


    var listI: MutableList<Int> = ArrayList()
    var res: MutableList<List<Int>> = ArrayList()

    /**
     * 78. 子集--方法一：回溯
     *
     * 回溯思想：找到一个子集，结束了递归，要撤销当前的选择（从list中删掉），回到选择前的状态，做另一个选择：不选当前的数，往下递归，继续生成子集。
     *          回退到上一步，把路走全，才能在包含解的空间树中，回溯出所有的解。
     *
     * 时间复杂度：O(n×2^n)。一共2^n个状态，每种状态需要O(n)的时间来构造子集；
     * 空间复杂度：O(n)。临时数组t的空间代价是O(n)，递归时栈空间的代价为O(n)。
     *
     * @param nums
     * @return
     */
    fun subsets(nums: IntArray): List<List<Int>>? {
        dfs(0, nums)
        return res
    }

    fun dfs(cur: Int, nums: IntArray) {
        // 1.递归终结条件（最先写）
        if (cur == nums.size) {       // 指针越界，记录答案
            res.add(ArrayList(listI))
            return
        }

        listI.add(nums[cur])                  // 选择当前位置元素

        dfs(cur + 1, nums)               // 3.下探到下一层（类似左子树）

        listI.removeAt(listI.size - 1) // 递归结束，撤销选择，不选择当前位置元素

        dfs(cur + 1, nums)               // 3.下探到下一层（类似右子树）
    }


    /**
     * 50. Pow(x, n)，计算x的n次幂函数，-100.0 < x < 100.0。
     * 输入: x=2.00000，n=10，输出: 2.0^10 = 1024.00000。  输入: 2.00000，-2，输出: 0.25000，解释: 2^-2 = 1/2^2 = 1/4 = 0.25
     *
     * 方法一：分治：根据重复性的构造和分解
     *
     * 思想：二分思想递归，复杂度是对数级的。
     *
     * 时间复杂度：O(logn)，即为递归的层数;
     * 空间复杂度：O(logn)，即为递归的层数，这是由于递归的函数调用会使用栈空间。
     *
     * @param x
     * @param n
     * @return
     */
    fun myPow(x: Double, n: Int): Double {
        val N = n.toLong()

        // 2.0^10 = 1024.00000；2^-2 = 1/2^--2 = 1/2^2 = 1/4 = 0.2
        return if (N >= 0) quickMul(x, N) else 1.0 / quickMul(x, -N)
    }

    fun quickMul(x: Double, n: Long): Double {
        // 1.递归终结条件（最先写）
        if (n == 0L) {
            return 1.0
        }

        // 2.处理当前层逻辑

        // 3.调用函数下探到下一层，解决更细节的子问题
        val subResult = quickMul(x, n / 2)

        // 4.将子问题的解的合并，产生最终结果
        return if (n % 2 == 0L) subResult * subResult else subResult * subResult * x

        // 5.清理恢复当前层
    }

}
