package com.seniorlibs.algorithm.db

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
        findViewById<View>(R.id.btn_max_sub_array).setOnClickListener(this)
        findViewById<View>(R.id.btn_max_product).setOnClickListener(this)
        findViewById<View>(R.id.btn_coin_change).setOnClickListener(this)
        findViewById<View>(R.id.btn_maximal_square).setOnClickListener(this)
        findViewById<View>(R.id.btn_count_sub_strings).setOnClickListener(this)
        findViewById<View>(R.id.btn_longest_palindrome).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_climb_stairs -> {
                LogUtils.e(TAG, "70. 爬楼梯1：${fun1(5)}")
                LogUtils.e(TAG, "70. 爬楼梯2：${fun2(5)}")
                LogUtils.e(TAG, "70. 爬楼梯3：${fun3(5)}")
                LogUtils.e(TAG, "70. 爬楼梯4：${fun4(5)}")
                LogUtils.e(TAG, "70. 爬楼梯5：${fun5(5)}")
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
            R.id.btn_min_distance -> {
                LogUtils.e(TAG, "583. 两个字符串的删除操作：${minDistance("ace", "abcde")}")
            }
            R.id.btn_minimum_delete_sum -> {
                LogUtils.e(TAG, "712. 两个字符串的最小ASCII删除和：${minimumDeleteSum("ace", "abcde")}")
            }
            R.id.btn_minimum_total -> {
                val res = listOf(listOf(2), listOf(3, 4), listOf(6, 5, 7), listOf(4, 1, 8, 3))
                LogUtils.e(TAG, "120. 三角形最小路径和：${minimumTotal4(res)}")
                LogUtils.e(TAG, "120. 三角形最小路径和：${minimumTotal1(res)}")
                LogUtils.e(TAG, "120. 三角形最小路径和：${minimumTotal2(res)}")
                LogUtils.e(TAG, "120. 三角形最小路径和：${minimumTotal3(res)}")
            }
            R.id.btn_max_sub_array -> {
                LogUtils.e(TAG, "53. 最大子序和：${maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4))}")
            }
            R.id.btn_max_product -> {
                LogUtils.e(TAG, "152. 乘积最大子数组：${maxProduct(intArrayOf(2, 3, -2, 4, -1))}")
            }
            R.id.btn_coin_change -> {
                LogUtils.e(TAG, "322. 零钱兑换：${coinChange(intArrayOf(1, 2, 5), 11)}")
            }
            R.id.btn_maximal_square -> {
                val paths = arrayOf(
                    charArrayOf('1', '0', '1', '0', '0'), charArrayOf('1', '0', '1', '1', '1'),
                    charArrayOf('1', '1', '1', '1', '1'), charArrayOf('1', '0', '0', '1', '0')
                )
                LogUtils.e(TAG, "221. 最大正方形1：${maximalSquare(paths)}")
                LogUtils.e(TAG, "221. 最大正方形2：${maximalSquare2(paths)}")
            }
            R.id.btn_count_sub_strings -> {
                LogUtils.e(TAG, "647. 回文子串：${countSubstrings("baba")}")
            }
            R.id.btn_longest_palindrome -> {
                LogUtils.e(TAG, "5. 最长回文子串：${longestPalindrome("baba")}")
            }
            else -> {
            }
        }
    }


    /**
     * 70. 爬楼梯 方法一：暴力递归(自顶向下)
     *
     * 时间复杂度：O(2^n)。树形递归的大小为2^n；
     * 空间复杂度：O(n)。递归树的深度可以达到n
     *
     * https://leetcode-cn.com/problems/climbing-stairs/solution/70-pa-lou-ti-by-chen-li-guan/
     * @param n
     * @return
     */
    fun fun1(n: Int): Int {
        if (n <= 1) return 1
        if (n == 2) return 2

        return fun1(n - 1) + fun1(n - 2)
    }

    /**
     * 70. 爬楼梯  解法二：备忘录递归(自顶向下)，解法一递归的升级版（记住）
     *
     * 时间复杂度：O(n)。树形递归的大小可以达到 n；
     * 空间复杂度：O(n)。递归树的深度可以达到 n
     *
     * https://leetcode-cn.com/problems/climbing-stairs/solution/70-pa-lou-ti-by-chen-li-guan/
     * @param n
     * @return
     */
    fun fun2(n: Int): Int {
        if (n <= 1) return 1
        if (n == 2) return 2

        val cache = IntArray(n + 1)
        cache[0] = 0
        cache[1] = 1

        return funs(n, cache)
    }

    private fun funs(n: Int, cache : IntArray): Int {
        // 如果 N 对应的楼梯数存在，则返回
        if (cache[n] != 0) return cache[n]

        // 计算 N 对应的楼梯数为 memoize(N-1) + memoize(N-2)
        cache[n] = funs(n - 1, cache) + funs(n - 2, cache)

        return cache[n]
    }

    /**
     * 70. 爬楼梯  解法2.1：备忘录递归(自顶向下)，解法二递归的升级版
     *
     * 时间复杂度：O(n)。树形递归的大小可以达到 n；
     * 空间复杂度：O(n)。递归树的深度可以达到 n
     *
     * https://leetcode-cn.com/problems/climbing-stairs/solution/70-pa-lou-ti-by-chen-li-guan/
     * @param n
     * @return
     */
    fun fun3(n: Int): Int {
        return funss(n, 1, 2)
    }

    fun funss(n: Int, first: Int, second: Int): Int {
        if (n <= 1) return first
        if (n == 2) return second

        // 5 -> 4 -> 3 (2->1)
        return funss(n - 1, second, first + second)
    }

    /**
     * 70. 爬楼梯  解法三：动态规划(自底向上)，解法二备忘录存储技巧的升级（记住）
     *
     * 时间复杂度：O(n)。循环执行n次，每次花费常数的时间代价；
     * 空间复杂度：O(n)。用了n空间的数组辅助，空间复杂度为。
     *
     * base case：
     *    f(0) = 1
     *    f(1) = 1
     *    f(2) = 2
     *
     * DP方程：dp(n) = dp(n-1) + dp(n-2)
     *
     * https://leetcode-cn.com/problems/climbing-stairs/solution/70-pa-lou-ti-by-chen-li-guan/
     * @param n
     * @return
     */
    fun fun4(n: Int): Int {
        if (n <= 1) return 1
        if (n == 2) return 2

        val dp = IntArray(n + 1)
        // base case
        dp[1] = 1
        dp[2] = 2

        // dp方程：f(n) = f(n-1) + f(n-2)
        for (i in 3 until n + 1) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }

        return dp[n]
    }

    /**
     * 70. 爬楼梯  方法四：动态规划优化(自底向上)，斐波那契数。数组当前值是依赖他前面两个值的（前两个除外），我们只需要用两个临时变量即可，不需要申请一个数组
     *
     * 时间复杂度：O(n)。循环执行n次，每次花费常数的时间代价；
     * 空间复杂度：O(1)。只用了常数个变量作为辅助空间。
     *
     * @param n
     * @return
     */
    fun fun5(n: Int): Int {
        if (n <= 1) return 1
        if (n == 2) return 2

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
     * 509. 斐波那契数  解法一：暴力递归(自顶向下)
     *
     * 时间复杂度：O(2^N)。这是计算斐波那契数最慢的方法。因为它需要指数的时间。
     * 空间复杂度：O(N)，在堆栈中需要与 N 成正比的空间大小。该堆栈跟踪 fib(N) 的函数调用，随着堆栈的不断增长如果没有足够的内存则会导致 StackOverflowError。
     *
     * https://leetcode-cn.com/problems/fibonacci-number/solution/509-fei-bo-na-qi-shu-by-chen-li-guan/
     * @param n
     * @return
     */
    fun fib1(n: Int): Int {
        if (n <= 0) return 0
        if (n == 1 || n == 2) return 1

        return fib1(n - 1) + fib1(n - 2)
    }

    /**
     * 509. 斐波那契数   解法二：备忘录递归(自顶向下)，解法一递归的升级版（记住）
     *
     * 思想：比解法一多了个"备忘录"储存，"剪枝"处理技巧，可以去除重复的调用计算
     *
     * 时间复杂度：O(n)。循环执行n次，每次花费常数的时间代价；
     * 空间复杂度：O(n)，内存中使用的堆栈大小
     *
     * https://leetcode-cn.com/problems/fibonacci-number/solution/509-fei-bo-na-qi-shu-by-chen-li-guan/
     * @param n
     * @return
     */
    private var cache = IntArray(31)

    fun fib2(n: Int): Int {
        if (n <= 0) return 0
        if (n == 1 || n == 2) return 1

        cache[0] = 0
        cache[1] = 1
        return fibs(n)
    }

    private fun fibs(n: Int): Int {
        // 如果 N 对应的斐波那契数存在，则返回
        if (cache[n] != 0) return cache[n]

        // 计算 N 对应的斐波那契数为 fb(N-1) + fb(N-2)
        cache[n] = fibs(n - 1) + fibs(n - 2)
        return cache[n]
    }

    /**
     * 509. 斐波那契数  解法三：动态规划(自底向上)，解法二备忘录存储技巧的升级（记住）
     *
     * 思想：自底向上通过迭代计算子问题并存储已计算的值，通过已计算的值进行计算，减少递归带来的重复计算
     *
     * 时间复杂度：O(n)。循环执行n次，每次花费常数的时间代价；
     * 空间复杂度：O(n)，使用了空间大小为 N 的数组
     *
     * base case：
     *    f(0) = 0
     *    f(1) = 1
     *    f(2) = 1
     *
     * DP方程：dp(n) = dp(n-1) + dp(n-2)
     *
     * https://leetcode-cn.com/problems/fibonacci-number/solution/509-fei-bo-na-qi-shu-by-chen-li-guan/
     * @param n
     * @return
     */
    fun fib3(n: Int): Int {
        if (n == 0) return 0
        if (n == 1) return 1

        val size = n + 1
        val dp = IntArray(size)
        // base case
        dp[0] = 0
        dp[1] = 1

        // dp
        for (i in 2 until size) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }

        return dp[n]
    }

    /**
     * 509. 斐波那契数   解法四：动态规划(自底向上)
     *
     * 解法三的优化，利用(状态压缩技巧)，其实状态只跟前一个数和当前数有关，不需要像第三种解法那样用一个数组进行存储，只需用两个值存储即可
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(1)，仅仅使用了 current，prev1，prev2
     *
     * @param n
     * @return
     */
    fun fib4(n: Int): Int {
        if (n <= 0) return 0
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
     * base case：定义 dp 数组并初始化第 0 行和第 0 列 的值为 1
     *    f(0) = 1
     *    f(1) = 1
     *    f(2) = 2
     *
     * DP方程：dp(i,j) = dp(i-1, j) + dp(i, j-1)
     *
     * https://leetcode-cn.com/problems/unique-paths/solution/62-bu-tong-lu-jing-by-chen-li-guan/
     * @param m
     * @param n
     * @return
     */
    fun uniquePaths(m: Int, n: Int): Int {
        if (m == 0 || n == 0) return 0

        // base case：定义 dp 数组并初始化第 0 行和第 0 列 的值为 1
        val dp = Array(m) { IntArray(n) }
        for (i in 0 until m) dp[i][0] = 1
        for (j in 0 until n) dp[0][j] = 1

        // dp：根据状态转移方程 dp[i][j] = dp[i - 1][j] + dp[i][j - 1] 进行递推。
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

        // base case：定义 dp 数组并初始化第 1 列。
        val dp = IntArray(n)
        for (j in 0 until n) dp[j] = 1

        // dp：根据状态转移方程 dp[j] = [j] + [j - 1] 进行递推。
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
        if (obstacleGrid.isEmpty() || obstacleGrid[0].isEmpty()) return 0

        val m: Int = obstacleGrid.size
        val n: Int = obstacleGrid[0].size

        // base case：定义 dp 数组并初始化第 0 行和第 0 列
        val dp = Array(m) { IntArray(n) }
        // 一旦遇到值为1的情况，后面的都不会被赋值成1了，都是默认值0
        for (i in 0 until m) {
            if (obstacleGrid[i][0] == 1) break
            dp[i][0] = 1
        }
        for (j in 0 until n) {
            if (obstacleGrid[0][j] == 1) break
            dp[0][j] = 1
        }

        // dp：根据状态转移方程 dp[i][j] = dp[i - 1][j] + dp[i][j - 1] 进行递推
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
     * 63. 不同路径 II  解法二：动态规划(自底向上)，空间优化
     *
     * 时间复杂度：O(mn)，其中m和n分别为行数和列数；
     * 空间复杂度：O(n)，使用了空间大小为n的数组
     *
     * https://leetcode-cn.com/problems/unique-paths-ii/solution/63-bu-tong-lu-jing-ii-by-chen-li-guan-2/
     * @param obstacleGrid
     * @return
     */
    fun uniquePathsWithObstacles1(obstacleGrid: Array<IntArray>): Int {
        if (obstacleGrid.isEmpty() || obstacleGrid[0].isEmpty()) return 0

        val m: Int = obstacleGrid.size
        val n: Int = obstacleGrid[0].size

        // base case：定义 dp 数组并初始化第 1 列
        val dp = IntArray(n)
        dp[0] = if (obstacleGrid[0][0] == 0) 1 else 0

        // db：dp[j] = dp[j] + dp[j - 1]
        for (i in 0 until m) {
            for (j in 0 until n) {
                // 处理[0][1]的情况，从0开始
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0
                    continue
                }

                if (j > 0 && obstacleGrid[i][j] == 0) {
                    dp[j] = dp[j] + dp[j - 1]
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
     * @param s1
     * @param s2
     * @return
     */
    fun longestCommonSubsequence(s1: String, s2: String): Int {
        if (s1.isEmpty() || s2.isEmpty()) return 0

        val m: Int = s1.length + 1
        val n: Int = s2.length + 1
        // dp数组宽高是s1.length+1和s2.length+1
        val dp = Array(m) { IntArray(n) }

        // base case:  dp默认0行或0列是0，所以不需要设置了
//        dp[0][..] = dp[..][0] = 0

        // 外层 for 循环在遍历s1所有状态的所有取值
        for (i in 1 until m) {
            // 外层 for 循环在遍历s2所有状态的所有取值
            for (j in 1 until n) {
                // dp默认0行或0列是0，真实存值是从1行和1列开始，而s1和s2还是从0行或0列开始；现在i和j从1开始，所以s1要减1
                if (s1[i - 1] == s2[j - 1]) {
                    // 如果s1[i-1] == s2[j-1]，说明这个字符 s1[i-1]和s2[j-1] 一定在 最长公共子序列长度 中
                    dp[i][j] = 1 + dp[i - 1][j - 1]
                } else {
                    // 如果s1[i-1] != s2[j-1]，s1[i-1] 和 s2[j-1] 至少有一个不在最长公共子序列长度 中，穷举三种情况的结果，取其中的最大结果
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j])
                }
            }
        }

        return dp[m - 1][n - 1]
    }

    /**
     * 583. 两个字符串的删除操作 解法一：动态规划（自底向上）
     *
     * 思路：最后这两个字符串会被删成的结果不就是它俩的最长公共子序列！
     * 先求出 最大的公共子序列值，两个字符串的删除操作次数 = 两个字符串的总长度 - 2 * 最大的公共子序列值。
     *
     * 时间复杂度：O(mn)，其中m和n分别为行数和列数；
     * 空间复杂度：O(mn)，使用了空间大小为mn的数组
     *
     * @param s1
     * @param s2
     * @return
     */
    fun minDistance(s1: String, s2: String): Int {
        if (s1.isEmpty() || s2.isEmpty()) return 0

        // 那么，要计算删除的次数，就可以通过最长公共子序列的长度推导出来
        val lcs = longestCommonSubsequence(s1, s2)
        return s1.length - lcs + s2.length - lcs
    }

    /**
     * 712. 两个字符串的最小ASCII删除和 解法一：动态规划（自底向上）
     *
     * 思路：先求出 最大的公共子序列对应的ASCII值，两个字符串的最小ASCII删除和 = 两个字符串的总ASCII值 - 2 * 最大的公共子序列对应的ASCII值。
     *
     * 时间复杂度：O(mn)，其中m和n分别为行数和列数；
     * 空间复杂度：O(mn)，使用了空间大小为mn的数组
     *
     * @param s1
     * @param s2
     * @return
     */
    fun minimumDeleteSum(s1: String, s2: String): Int {
        // 求出 最大的公共子序列对应的ASCII值，利用两个字符串的总ASCII值 - 2 * 最大的公共子序列对应的ASCII值
        if (s1.isEmpty()) return getASCII(s2.toCharArray())
        if (s2.isEmpty()) return getASCII(s1.toCharArray())

        val m: Int = s1.length + 1
        val n: Int = s2.length + 1
        // dp数组宽高是s1.length+1和s2.length+1
        val dp = Array(m) { IntArray(n) }

        // base case:  dp默认0行或0列是0，所以不需要设置了
//        dp[0][..] = dp[..][0] = 0

        // 外层 for 循环在遍历s1所有状态的所有取值
        for (i in 1 until m) {
            // 外层 for 循环在遍历s2所有状态的所有取值
            for (j in 1 until n) {
                // dp默认0行或0列是0，真实存值是从1行和1列开始，而s1和s2还是从0行或0列开始；现在i和j从1开始，所以s1要减1
                if (s1[i - 1] == s2[j - 1]) {
                    // 如果s1[i-1] == s2[j-1]，说明这个字符 s1[i-1]和s2[j-1] 一定在 最长公共子序列长度 中，累加ASCII值
                    dp[i][j] = s1.codePointAt(i - 1) + dp[i - 1][j - 1]
                } else {
                    // 如果s1[i-1] != s2[j-1]，s1[i-1] 和 s2[j-1] 至少有一个不在最长公共子序列长度 中，穷举三种情况的结果，取其中的最大结果
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j])
                }
            }
        }

        return getASCII(s1.toCharArray()) + getASCII(s2.toCharArray()) - 2 * dp[m - 1][n - 1]
    }

    fun getASCII(charArr: CharArray): Int {
        var ret = 0
        for (c in charArr) {
            ret += c.toInt()
        }
        return ret
    }


    /**
     * 120. 三角形最小路径和  解法一：暴力递归(自顶向下)，导致 超时
     * 思路：与该点相邻两点到底边的最小路径和中的较小值，再加上该点本身的值
     *
     * 时间复杂度：O(2^n)，它需要指数的时间。n为三角形的行数。
     * 空间复杂度：O(mn)，在堆栈中需要与 n 成正比的空间大小。n为三角形的行数。
     *
     * @param triangle
     * @return
     */
    fun minimumTotal1(triangle: List<List<Int>>): Int {
        return dfs(triangle, 0, 0)
    }

    private fun dfs(triangle: List<List<Int>>, i: Int, j: Int): Int {
        if (i == triangle.size) return 0

        return Math.min(dfs(triangle, i + 1, j), dfs(triangle, i + 1, j + 1)) + triangle[i][j]
    }

    /**
     * 120. 三角形最小路径和  解法二：备忘录递归(自顶向下)，解法一递归的升级版（记住）
     *
     * 思路：与该点相邻两点到底边的最小路径和中的较小值，再加上该点本身的值。比解法一多了个"备忘录"储存，"剪枝"处理技巧，可以去除重复的调用计算
     *
     * 时间复杂度：O(n^2)，n为三角形的行数。
     * 空间复杂度：O(n^2)，n为三角形的行数。
     *
     * @param triangle
     * @return
     */
    fun minimumTotal2(triangle: List<List<Int>>): Int {
        val res = Array(triangle.size) { IntArray(triangle.size) }
        for (i in triangle.indices) {
            for (j in triangle.indices) {
                res[i][j] = -1
            }
        }

        return dfs1(triangle, res,0, 0)
    }

    private fun dfs1(triangle: List<List<Int>>, res: Array<IntArray>, i: Int, j: Int): Int {
        if (i == triangle.size) return 0

        if (res[i][j] != -1) return res[i][j]

        // 该点相邻两点到底边的最小路径和中的较小值，再加上该点本身的值
        res[i][j] = Math.min(dfs1(triangle, res,i + 1, j), dfs1(triangle, res,i + 1, j + 1)) + triangle[i][j]

        return res[i][j]
    }

    /**
     * 120. 三角形最小路径和  解法一：动态规划（自底向上）（记住）
     *
     * 思路：与该点相邻两点到底边的最小路径和中的较小值，再加上该点本身的值。从三角形的最后一行-->往上，每一行的最右-->左 开始递推
     *
     * 步骤：计算dp[i][j]时，只用到了下一行的dp[i + 1][j]和dp[i + 1][j + 1]。当计算三角形的最后一行时，需要额外一行辅助计算(默认值时0)，因此数组长度是n + 1。
     *
     * 注意：本题的（自底向上）和其他题的区别，是从第n-1行开始计算，所以要倒着来。
     *
     * 时间复杂度：O(n^2)，n为三角形的行数。
     * 空间复杂度：O(n^2)，n为三角形的行数。
     *
     * base case：定义dp二维数组，大小是n+1，并初始化值为0
     * DP方程：dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j]
     *
     * https://leetcode-cn.com/problems/triangle/solution/120-san-jiao-xing-zui-xiao-lu-jing-he-by-chen-li-g/
     * @param triangle
     * @return
     */
    fun minimumTotal3(triangle: List<List<Int>>): Int {
        val n = triangle.size
        // base case：dp[i][j]表示从点(i, j)到底边的最小路径和
        val dp = Array(n + 1) { IntArray(n + 1) }

        // DP方程：从三角形的最后一行-->往上，每一行的最右-->左 开始递推
        for (i in n - 1 downTo 0) {
            for (j in triangle[i].size - 1 downTo 0) {
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
    fun minimumTotal4(triangle: List<List<Int>>): Int {
        val n = triangle.size
        val dp = IntArray(n + 1)
        for (i in n - 1 downTo 0) {
            for (j in triangle[i].size - 1 downTo 0) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle[i][j]
            }
        }
        return dp[0]
    }


    /**
     * 53. 最大子序和（最大子数组）   解法一：动态规划
     *
     * 思想：数组nums，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * base case：dp = nums[0]
     *
     * DP方程：dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
     *
     * 时间复杂度：O(n)，其中n为nums数组的长度，只需要遍历一遍数组即可求得答案。
     * 空间复杂度：O(1)，只需要常数空间存放若干变量。
     *
     * https://leetcode-cn.com/problems/maximum-subarray/solution/53-zui-da-zi-xu-he-by-chen-li-guan/
     * @param nums
     * @return
     */
    fun maxSubArray(nums: IntArray): Int {
        val n = nums.size
        if (n == 0) return 0

        val dp = IntArray(n)
        // base case 第一个元素前面没有子数组
        dp[0] = nums[0]

        // dp状态转移方程
        for (i in 1 until n) {
            // 要么自成一派，要么和前面的子数组合并
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1])
        }

        // 计算最大子序和
        var res = Int.MIN_VALUE
        for (i in 0 until n) {
            res = Math.max(res, dp[i])
        }

        return res
    }

    /**
     * 152. 乘积最大子数组  解法一：动态规划
     *
     * DP方程：imax = max(imax * num[i], num[i])， imin = min(imin * num[i], num[i]), db = max(db, imax)
     *
     * 时间复杂度：程序一次循环遍历了nums，故时间复杂度为O(n)。
     * 空间复杂度：优化后只使用常数个临时变量作为辅助空间，与n无关，故空间复杂度为O(1)。
     *
     * @param nums
     * @return
     */
    fun maxProduct(nums: IntArray): Int {
        var db = Int.MIN_VALUE
        var imax = 1
        var imin = 1
        for (x in nums) {
            if (x < 0) {
                val tmp = imax
                imax = imin
                imin = tmp
            }

            imax = Math.max(imax * x, x)
            imin = Math.min(imin * x, x)
            db = Math.max(db, imax)
        }
        return db
    }


    /**
     * 322. 零钱兑换   方式1：动态规划
     *
     * 时间复杂度：O(Sn)，其中S是金额，n是面额数。一共需要计算O(S)个状态；对于每个状态，每次需要枚举n个面额来转移状态，所以一共需要O(Sn)的时间复杂度；
     * 空间复杂度：O(S)，DP数组需要开长度为总金额S的空间
     *
     * base case：dp[0] = 0
     * DP方程：dp[i] = Math.min(dp[i], i + dp[i - coin])，coin ∈ coins、n > 0
     *
     * https://leetcode-cn.com/problems/coin-change/solution/322-ling-qian-dui-huan-by-chen-li-guan/
     * @param coins
     * @param amount
     * @return
     */
    fun coinChange(coins: IntArray, amount: Int): Int {
        // 凑成amount金额的数量最多只可能等于amount（全用1元面值的），所以初始化为amount + 1就相当于初始化为正无穷，便于后续取最小值。
        val n = amount + 1
        val dp = IntArray(n)
        Arrays.fill(dp, Int.MAX_VALUE)

        // base case
        dp[0] = 0

        // 状态 1...amount -> i：外层 for 循环在遍历所有状态的所有取值
        for (i in 1 until n) {
            // 选择：选择哪种硬币的面值 或 不选
            for (coin in coins) {
                // 子问题无解，跳过
                if (i - coin < 0) continue
                // dp动态转移方程：求所有选择的最小值
                if (dp[i - coin] < Int.MAX_VALUE) dp[i] = Math.min(dp[i], 1 + dp[i - coin])
            }
        }

        // 没有任何一种硬币组合能组成总金额，返回-1
        return if (dp[amount] < Int.MAX_VALUE) dp[amount] else -1
    }


    /**
     * 221. 最大正方形 解法一：动态规划
     *
     * 思想：以(i,j)为根据点，它最大的正方形的边长取决于(上, 左, 左上)边长最短的那一个
     *
     * 时间复杂度：O(mn)，其中m和n分别为行数和列数；
     * 空间复杂度：O(mn)，使用了空间大小为mn的数组
     *
     * base case：第 0 行和第 0 列，同步 matrix
     *
     * DP方程：dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1
     *
     * https://leetcode-cn.com/problems/maximal-square/solution/221-zui-da-zheng-fang-xing-by-chen-li-guan/
     * @param matrix
     * @return
     */
    fun maximalSquare(matrix: Array<CharArray>): Int {
        if (matrix.isEmpty() || matrix[0].isEmpty()) return 0

        val m = matrix.size
        val n = matrix[0].size
        var max = 0

        // 初始化base case：定义 dp 数组，相当于已经预处理新增第一行、第一行均为0
        val dp = Array(m + 1) { IntArray(n + 1) }

        // 根据状态转移方程 dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1 进行递推
        for (i in 1..m) {
            for (j in 1..n) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1
                    max = Math.max(dp[i][j], max)
                } else {
                    dp[i][j] = 0
                }
            }
        }
        return max * max
    }

    /**
     * 221. 最大正方形 解法一：动态规划（空间优化）
     *
     * 思路：增加 northwest 西北角解决"左上角"的问题
     *
     * 时间复杂度：O(mn)，其中m和n分别为行数和列数；
     * 空间复杂度：O(n)，使用了空间大小为n的数组
     *
     * base case：第 0 行和第 0 列，同步 matrix
     *
     * DP方程：dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1
     *
     * https://leetcode-cn.com/problems/maximal-square/solution/221-zui-da-zheng-fang-xing-by-chen-li-guan/
     * @param matrix
     * @return
     */
    fun maximalSquare2(matrix: Array<CharArray>): Int {
        if (matrix.isEmpty() || matrix[0].isEmpty()) return 0

        val m = matrix.size
        val n = matrix[0].size
        var max = 0

        // 初始化base case：定义 dp 数组，相当于已经预处理新增第一行为0
        val dp = IntArray(n + 1)
        // 西北角/左上角
        var northWest = dp[0]

        // 根据状态转移方程 dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1 进行递推
        for (i in 1..m) {
            for (j in 1..n) {
                // 提前一轮赋值，在下一轮循环使用 对应dp[i-1]；赋值的是为改变值前的dp[j]，也就是改变值后中上一行的dp[j-1]；结果就是dp[i-1][j-1]
                val temp = dp[j]
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(dp[j - 1], Math.min(dp[j], northWest)) + 1
                    max = Math.max(dp[j], max)
                } else {
                    dp[j] = 0
                }
                northWest = temp
            }
        }
        return max * max
    }


    /**
     * 647. 回文子串  动态规划
     *
     * "回文串”是一个正读和反读都一样的字符串，比如“level”或者“noon”等等就是回文串、
     * "abc"："a", "b", "c"
     * "aaa"："a", "a", "a", "aa", "aa", "aaa"
     * "baba"："b", "a", "b", "a", "bab", "aba"
     * "leveel"："l", "e", "v", "e", "e", "l", "l", "le"
     *
     * 思路：
     * base case；只有一个字母的时候肯定是回文子串
     *      for (i in 0 until n) dp[i][i] = true
     *
     * dp：
     * 如果s[i]==s[j]，说明只要dp[i+1][j-1]是回文子串，那么dp[i][j]也是回文子串；
     * 如果s[i]!=s[j]，说明dp[i][j]必定不是回文子串。
     *
     * if(s.charAt(i) == s.charAt(j)){
     *    dp[i][j] = dp[i+1][j-1]
     * } else {
     *    dp[i][j] = false;
     * }
     *
     * 时间复杂度：O(n^2)，
     * 空间复杂度：O(n^2)：在填表的过程中，只参考了右上方的数值。事实上可以优化，但是增加了代码编写和理解的难度，丢失可读和可解释性，在这里不优化空间。
     *
     * https://leetcode-cn.com/problems/palindromic-substrings/solution/647-hui-wen-zi-chuan-by-chen-li-guan/
     * @param s
     * @return
     */
    fun countSubstrings(s: String): Int {
        if (s.isEmpty()) return 0

        // base case：只有一个字母的时候肯定是回文子串，数量是s.length
        val n = s.length
        var count = s.length
        val dp = Array(n) { BooleanArray(n) }
        // 单个字符
        for (i in 0 until n) dp[i][i] = true

        // 为什么从右下角遍历：因为在填dp表时，(i, j) 位置的值依赖于（i+1,j-1），也就是当前位置的左下方。
        // 显然如果从上往下遍历，左下方的值就完全没有初始化，当然当前位置也会是错误的。但是从右下角遍历就保证了左下方的所有值都已经计算好了。
        // db：j>=i，所以只用填右半张表，左半默认false
        for (i in n - 2 downTo 0) {
            for (j in i + 1 until n) {
                if (s[i] == s[j]) {
                    if (j - i < 3) {
                        // j - i == 1：中间没有字符，一定是回文子串。如：aaa->aa(i=0,j=1),aa(i=1,j=2)
                        // j - i == 2：中间只有1个字符，即去掉两头，剩下中间部分只有1个字符，显然是回文。如：baba->bab(i=0,j=2),aba(i=1,j=3)
                        // 所以：s[i] == s[j]成立和j - i < 3前提下，直接可以下结论：dp[i][j] = true
                        dp[i][j] = true
                    } else {
                        // j - i >= 3：多于3个字符
                        dp[i][j] = dp[i + 1][j - 1]
                    }
                } else {
                    dp[i][j] = false
                }

                if (dp[i][j]) {
                    count++
                }
            }
        }
        return count
    }

    /**
     * 5. 最长回文子串
     *
     * 时间复杂度：O(n^2)，
     * 空间复杂度：O(n^2)：在填表的过程中，只参考了右上方的数值。事实上可以优化，但是增加了代码编写和理解的难度，丢失可读和可解释性，在这里不优化空间。
     *
     */
    fun longestPalindrome(s: String): String {
        if (s.length < 2) return s

        // base case：只有一个字母的时候肯定是回文子串，数量是s.length
        val n = s.length
        var maxLen = 1
        var begin = 0
        val dp = Array(n) { BooleanArray(n) }
        for (i in 0 until n) dp[i][i] = true

        // db
        for (i in n - 2 downTo 0) {
            for (j in i + 1 until n) {
                if (s[i] == s[j]) {
                    if (j - i < 3) {
                        dp[i][j] = true
                    } else {
                        dp[i][j] = dp[i + 1][j - 1]
                    }
                } else {
                    dp[i][j] = false
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1
                    begin = i
                }
            }
        }
        return s.substring(begin, begin + maxLen)
    }
}
