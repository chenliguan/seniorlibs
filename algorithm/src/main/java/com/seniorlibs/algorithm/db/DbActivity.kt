package com.seniorlibs.algorithm.db

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
 * Date: 2020/9/10
 * Mender:
 * Modify:
 * Description: 动态规划
 */
class DbActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "DbActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, DbActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_climb_stairs).setOnClickListener(this)
        findViewById<View>(R.id.btn_fib).setOnClickListener(this)
        findViewById<View>(R.id.btn_unique_paths).setOnClickListener(this)
        findViewById<View>(R.id.btn_unique_paths_with_obstacles).setOnClickListener(this)
        findViewById<View>(R.id.btn_longest_common_sub_sequence).setOnClickListener(this)
        findViewById<View>(R.id.btn_minimum_total).setOnClickListener(this)
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
//                val paths = arrayOf(intArrayOf(0), intArrayOf(1))
                LogUtils.e(TAG, "63. 不同路径 II：${uniquePathsWithObstacles(paths)}")
                LogUtils.e(TAG, "63. 不同路径 II：${uniquePathsWithObstacles1(paths)}")
            }
            R.id.btn_longest_common_sub_sequence -> {
                LogUtils.e(TAG, "1143. 最长公共子序列：${longestCommonSubsequence("ace", "abcde")}")
            }
            R.id.btn_minimum_total -> {
                val res = listOf(listOf(2), listOf(3, 4), listOf(6, 5, 7), listOf(4, 1, 8, 3))
                LogUtils.e(TAG, "120. 三角形最小路径和：${minimumTotal(res)}")
                LogUtils.e(TAG, "120. 三角形最小路径和：${minimumTotal1(res)}")
                LogUtils.e(TAG, "120. 三角形最小路径和：${minimumTotal2(res)}")
                LogUtils.e(TAG, "120. 三角形最小路径和：${minimumTotal3(res)}")
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

        // 定义 dp 数组并初始化第 0 行和第 0 列。
        val dp = Array(m) { IntArray(n) }
        var i = 0
        while (i < m) {
            dp[i++][0] = 1
        }
        var j = 0
        while (j < n) {
            dp[0][j++] = 1
        }

        // 根据状态转移方程 dp[i][j] = dp[i - 1][j] + dp[i][j - 1] 进行递推。
        for (i in 1 until m) {
            for (j in 1 until n) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
            }
        }
        return dp[m - 1][n - 1]
    }

    /**
     * 62. 不同路径  解法二：动态规划(自底向上)，空间优化
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

        // 定义 dp 数组并初始化第 1 列。
        val dp = IntArray(n)
        var j = 0
        while (j < n) {
            dp[j++] = 1
        }

        for (i in 1 until m) {
            for (j in 1 until n) {
                dp[j] += dp[j - 1]
            }
        }
        return dp[n - 1]
    }


    /**
     * 63. 不同路径 II  解法一：动态规划(自底向上)
     *
     * 时间复杂度：O(mn)，其中m和n分别为行数和列数；
     * 空间复杂度：O(mn)，使用了空间大小为mn的数组
     *
     * https://leetcode-cn.com/problems/unique-paths-ii/solution/63-bu-tong-lu-jing-ii-by-chen-li-guan-2/
     * @param obstacleGrid
     * @return
     */
    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        if (obstacleGrid.isEmpty()) return 0

        val m: Int = obstacleGrid.size
        val n: Int = obstacleGrid[0].size

        // 定义 dp 数组并初始化第 0 行和第 0 列。
        val dp = Array(m) { IntArray(n) }
        var i = 0
        // 一旦遇到值为1的情况，后面的都不会被赋值成1了，都是默认值0
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
                // 1是障碍物，0是非障碍物，0才往下迭代
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
                }
            }
        }
        return dp[m - 1][n - 1]
    }

    /**
     * 63. 不同路径 II  解法二：动态规划(自底向上)，空间优化  TODO???
     *
     * 时间复杂度：O(mn)，其中m和n分别为行数和列数；
     * 空间复杂度：O(n)，使用了空间大小为n的数组
     *
     * https://leetcode-cn.com/problems/unique-paths-ii/solution/63-bu-tong-lu-jing-ii-by-chen-li-guan-2/
     * @param obstacleGrid
     * @return
     */
    fun uniquePathsWithObstacles1(obstacleGrid: Array<IntArray>): Int {
        if (obstacleGrid.isEmpty()) return 0

        val m: Int = obstacleGrid.size
        val n: Int = obstacleGrid[0].size

        // 定义 dp 数组并初始化第 1 列。
        val dp = IntArray(n)
        dp[0] = if (obstacleGrid[0][0] == 0) 1 else 0

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0
                    continue
                }

                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                    dp[j] += dp[j - 1]
                }
            }
        }
        return dp[n - 1]
    }


    /**
     * 1143. 最长公共子序列  解法一：动态规划（自底向上）
     *
     * 时间复杂度：O(mn)，其中m和n分别为行数和列数；
     * 空间复杂度：O(mn)，使用了空间大小为mn的数组
     *
     * https://leetcode-cn.com/problems/longest-common-subsequence/solution/1143-zui-chang-gong-gong-zi-xu-lie-by-chen-li-guan/
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
     * 120. 三角形最小路径和  解法一：暴力递归(自顶向下)，导致 超时
     *
     * 时间复杂度：O(2^n)，它需要指数的时间。n为三角形的行数。
     * 空间复杂度：O(mn)，在堆栈中需要与 n 成正比的空间大小。n为三角形的行数。
     *
     * @param triangle
     * @return
     */
    fun minimumTotal2(triangle: List<List<Int>>): Int {
        return dfs(triangle, 0, 0)
    }

    private fun dfs(triangle: List<List<Int>>, i: Int, j: Int): Int {
        if (i == triangle.size) {
             return 0
        }
        return Math.min(dfs(triangle, i + 1, j), dfs(triangle, i + 1, j + 1)) + triangle[i][j]
    }

    /**
     * 120. 三角形最小路径和  解法二：备忘录递归(自顶向下)，解法一递归的升级版
     * 思想：比解法一多了个"备忘录"储存，"剪枝"处理技巧，可以去除重复的调用计算
     *
     * 时间复杂度：O(n^2)，n为三角形的行数。
     * 空间复杂度：O(n^2)，n为三角形的行数。
     *
     * @param triangle
     * @return
     */
    private lateinit var res2: Array<Array<Int?>>

    fun minimumTotal3(triangle: List<List<Int>>): Int {
        res2 = Array(triangle.size) { arrayOfNulls<Int>(triangle.size) }

        return dfs1(triangle, 0, 0)
    }

    private fun dfs1(triangle: List<List<Int>>, i: Int, j: Int): Int {
        if (i == triangle.size) {
            return 0
        }

        if (res2[i][j] != null) {
            return res2[i][j]!!
        }

        return Math.min(dfs1(triangle, i + 1, j), dfs1(triangle, i + 1, j + 1)) + triangle[i][j]
    }

    /**
     * 120. 三角形最小路径和  解法一：动态规划（自底向上）
     * 在实际递推中发现，计算dp[i][j] 时，只用到了下一行的dp[i + 1][j]和dp[i + 1][j + 1]。因此dp数组不需要定义n行，只要定义1行就阔以啦.
     *
     * 时间复杂度：O(n^2)，n为三角形的行数。
     * 空间复杂度：O(n^2)，n为三角形的行数。
     *
     * @param triangle
     * @return
     */
    fun minimumTotal(triangle: List<List<Int>>): Int {
        val n = triangle.size
        // dp[i][j] 表示从点(i, j)到底边的最小路径和。
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
     * 120. 三角形最小路径和  解法二：动态规划(自底向上)，空间优化
     *
     * 时间复杂度：O(n^2)，n为三角形的行数。
     * 空间复杂度：O(n)，n为三角形的行数。
     *
     * @param triangle
     * @return
     */
    fun minimumTotal1(triangle: List<List<Int>>): Int {
        val n = triangle.size
        val dp = IntArray(n + 1)
        for (i in n - 1 downTo 0) {
            for (j in 0..i) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle[i][j]
            }
        }
        return dp[0]
    }
}