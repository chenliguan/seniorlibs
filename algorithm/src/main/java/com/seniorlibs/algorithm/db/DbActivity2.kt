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
 * Date: 2020/11/17
 * Mender:
 * Modify:
 * Description: 动态规划2
 */
class DbActivity2 : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "DbActivity2"

        fun actionStart(context: Context) {
            val intent = Intent(context, DbActivity2::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db2)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_climb_stairs).setOnClickListener(this)
        findViewById<View>(R.id.btn_min_cost_climb_stairs).setOnClickListener(this)
        findViewById<View>(R.id.btn_fib).setOnClickListener(this)
        findViewById<View>(R.id.btn_fib_2).setOnClickListener(this)

        findViewById<View>(R.id.btn_can_partition).setOnClickListener(this)
        findViewById<View>(R.id.btn_change_II).setOnClickListener(this)
        findViewById<View>(R.id.btn_coin_change).setOnClickListener(this)
        findViewById<View>(R.id.btn_word_break).setOnClickListener(this)

        findViewById<View>(R.id.btn_longest_common_sub_sequence).setOnClickListener(this)
        findViewById<View>(R.id.btn_min_distance).setOnClickListener(this)
        findViewById<View>(R.id.btn_minimum_delete_sum).setOnClickListener(this)
        findViewById<View>(R.id.btn_num_decodings).setOnClickListener(this)
        findViewById<View>(R.id.btn_find_length).setOnClickListener(this)
        findViewById<View>(R.id.btn_maximal_square).setOnClickListener(this)
        findViewById<View>(R.id.btn_min_edit_distance).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_climb_stairs -> {
                LogUtils.e(DbActivity.TAG, "70. 爬楼梯1：${fun1(5)}")
                LogUtils.e(DbActivity.TAG, "70. 爬楼梯2：${fun2(5)}")
                LogUtils.e(DbActivity.TAG, "70. 爬楼梯3：${fun3(5)}")
                LogUtils.e(DbActivity.TAG, "70. 爬楼梯4：${fun4(5)}")
                LogUtils.e(DbActivity.TAG, "70. 爬楼梯5：${fun5(5)}")
            }
            R.id.btn_min_cost_climb_stairs -> {
                LogUtils.e(DbActivity.TAG, "746. 使用最小花费爬楼梯：${minCostClimbingStairs(intArrayOf(1, 100, 1, 1, 1, 100, 1, 1, 100, 1))}")
            }
            R.id.btn_fib -> {
                LogUtils.e(DbActivity.TAG, "509. 斐波那契数：${fib1(5)}")
                LogUtils.e(DbActivity.TAG, "509. 斐波那契数：${fib2(5)}")
                LogUtils.e(DbActivity.TAG, "509. 斐波那契数：${fib3(5)}")
                LogUtils.e(DbActivity.TAG, "509. 斐波那契数：${fib4(5)}")
            }
            R.id.btn_fib_2 -> {
                LogUtils.e(DbActivity.TAG, "剑指 Offer 10- I. 斐波那契数列：${fib(5)}")
            }


            R.id.btn_can_partition -> {
                LogUtils.e(TAG, "416. 分割等和子集：${canPartition(intArrayOf(1, 5, 11, 5))}")
                LogUtils.e(TAG, "416. 分割等和子集1：${canPartition1(intArrayOf(1, 5, 11, 5))}")
            }
            R.id.btn_find_target_sum_ways -> {
                LogUtils.e(TAG, "494. 目标和：${findTargetSumWays(intArrayOf(1, 1, 1, 1, 1), 3)}")
                LogUtils.e(TAG, "494. 目标和1：${findTargetSumWays1(intArrayOf(1, 1, 1, 1, 1), 3)}")
            }
            R.id.btn_change_II -> {
                LogUtils.e(TAG, "518. 零钱兑换 II：${changeII(5, intArrayOf(1, 2, 5))}")
                LogUtils.e(TAG, "518. 零钱兑换 II 2：${changeII2(5, intArrayOf(1, 2, 5))}")
            }
            R.id.btn_coin_change -> {
                LogUtils.e(TAG, "322. 零钱兑换：${coinChange(intArrayOf(1, 2, 5), 11)}")
                LogUtils.e(TAG, "322. 零钱兑换 1：${coinChange1(intArrayOf(1, 2, 5), 11)}")
            }
            R.id.btn_word_break -> {
                LogUtils.e(TAG, "139. 单词拆分：${wordBreak("leetcode", arrayListOf("leet","code"))}")
            }

            R.id.btn_find_length -> {
                LogUtils.e(DbActivity.TAG, "718. 最长重复子数组：${findLength(intArrayOf(1, 2, 3, 2, 1), intArrayOf(3, 2, 1, 4, 7))}")
            }
            R.id.btn_longest_common_sub_sequence -> {
                LogUtils.e(DbActivity.TAG, "1143. 最长公共子序列：${longestCommonSubsequence("ace", "abcde")}")
            }
            R.id.btn_min_distance -> {
                LogUtils.e(DbActivity.TAG, "583. 两个字符串的删除操作：${minDistance("ace", "abcde")}")
            }
            R.id.btn_minimum_delete_sum -> {
                LogUtils.e(DbActivity.TAG, "712. 两个字符串的最小ASCII删除和：${minimumDeleteSum("ace", "abcde")}")
            }
            R.id.btn_num_decodings -> {
                LogUtils.e(DbActivity.TAG, " 91. 解码方法：${numDecodings("226")}")
            }

            R.id.btn_maximal_square -> {
                val paths = arrayOf(
                        charArrayOf('1', '0', '1', '0', '0'), charArrayOf('1', '0', '1', '1', '1'),
                        charArrayOf('1', '1', '1', '1', '1'), charArrayOf('1', '0', '0', '1', '0')
                )
                LogUtils.e(DbActivity.TAG, "221. 最大正方形1：${maximalSquare(paths)}")
                LogUtils.e(DbActivity.TAG, "221. 最大正方形2：${maximalSquare2(paths)}")
            }

            R.id.btn_min_edit_distance -> {
                LogUtils.e(DbActivity.TAG, "72. 编辑距离：${minEditDistance("horse", "ros")}")
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

    private fun funs(n: Int, cache: IntArray): Int {
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
        if (n == 1) return 1
        if (n == 2) return 2

        val n = n + 1

        val dp = IntArray(n)
        // base case
        // dp[0] = 1
        dp[1] = 1
        dp[2] = 2

        // dp方程：f(n) = f(n-1) + f(n-2)
        for (i in 3 until n) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }

        return dp[n - 1]
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
        if (n == 1) return 1
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
     * 746. 使用最小花费爬楼梯
     * 思想：dp[i]表示的是跳到第i个台阶所需要的的最小花费。如果要跳到第i个台阶，可以从第i-1个台阶跳上来，
     *       也可以从第i-2个台阶跳上来，哪个花费最小，我们就从哪个台阶跳上来。
     *
     * 注意：1.踏上第i级台阶花费cost[i]，直接迈一大步跨过而不踏上去则不用花费。
     *      2. dp 开 n + 1 个空间，所以 dp 的范围是从 1 -> n-1，cost 的范围是从 0 -> n。dp[1]->cost[0]，dp[2]->cost[0]...cost[1]，dp[n]->cost[n-2]...cost[n-1]
     *
     *
     * 时间复杂度：O(n)。循环执行n次，每次花费常数的时间代价；
     * 空间复杂度：O(n)。用了n空间的数组辅助，空间复杂度为。
     *
     * https://leetcode-cn.com/problems/min-cost-climbing-stairs/solution/746-shi-yong-zui-xiao-hua-fei-pa-lou-ti-69t5n/
     * @param cost
     * @return
     */
    fun minCostClimbingStairs(cost: IntArray): Int {
        val n = cost.size + 1

        val dp = IntArray(n)
        // base case
        dp[1] = 0
        dp[2] = Math.min(cost[0], cost[1])

        // dp方程：参考爬楼梯 dp[i] = dp[i - 1] + dp[i - 2]
        for (i in 3 until n) {
            dp[i] = Math.min(cost[i - 1] + dp[i - 1], cost[i - 2] + dp[i - 2])
        }

        return dp[n -1]
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

        val dp = IntArray(n + 1)
        // base case
        dp[0] = 0
        dp[1] = 1

        // dp
        for (i in 2 until n + 1) {
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
     * 剑指 Offer 10- I. 斐波那契数列
     * 区别：答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     * 时间复杂度：O(n)。循环执行n次，每次花费常数的时间代价；
     * 空间复杂度：O(n)，使用了空间大小为 N 的数组
     *
     * https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/solution/jian-zhi-offer-10-i-fei-bo-na-qi-shu-lie-iqtw/
     * @param n
     * @return
     */
    fun fib(n: Int): Int {
        if (n == 0) return 0
        if (n == 1) return 1

        val dp = IntArray(n + 1)
        // base case
        dp[0] = 0
        dp[1] = 1

        // dp
        for (i in 2 until n + 1) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007
        }

        return dp[n]
    }



    /**
     * 416. 分割等和子集   动态规划 （了解）
     *
     * 时间复杂度：O(n * m)，其中 n 是数组的长度，m 是整个数组的元素和的一半，需要计算出所有的状态，每个状态在进行转移时的时间复杂度为 O(1)O(1)。
     * 空间复杂度：O(n * m)，其中 n 是数组的长度。空间复杂度取决于 dp 数组，在不进行空间优化的情况下，空间复杂度是 O(n * m)，
     *                      在进行空间优化的情况下，空间复杂度可以降到 O(m)。
     * @param nums
     * @return
     */
    fun canPartition(nums: IntArray): Boolean {
        var sum = 0
        for (num in nums) sum += num

        // 和为奇数时，不可能划分成两个和相等的集合
        if (sum % 2 != 0) return false

        val m = nums.size + 1
        val n = sum / 2 + 1
        val dp = Array(m) { BooleanArray(n) }

        // base case：
        // dp[0...i][0] = true：表示背包容量为 0 时，背包都是装满的；
        for (i in 0 until m) dp[i][0] = true
        // dp[0][1...j] = false：表示没有物品，背包容量 > 0 时，不可能被装满；
        for (j in 1 until n) dp[0][j] = false

        // dp 方程
        for (i in 1 until m) {
            for (j in 1 until n) {
                if (j - nums[i - 1] < 0) {
                    // 背包容量不足，不能装入第i个物品，所以状态和没装第i个物品相同
                    dp[i][j] = dp[i - 1][j]
                } else {
                    // 背包容量充足：
                    // 选择不装入背包，状态和没装第i个物品相同
                    // 选择装入背包，装了第i个物品，就要看背包的剩余(j - nums[i-1])的重量可否被(i - 1)个物品装满；可以，那么把第i个物品装进去，也可装满背包j的重量
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]]
                }
            }
        }

        // 表示 前 m - 1 个物品是否可以将容量为 n - 1 = sum/2（分割成了两个子集）的背包装满
        return dp[m - 1][n - 1]
    }

    /**
     * 416. 分割等和子集   动态规划-->状态压缩
     *
     * 时间复杂度：O(n * m)，其中 n 是数组的长度，m 是整个数组的元素和的一半，需要计算出所有的状态，每个状态在进行转移时的时间复杂度为 O(1)O(1)。
     * 空间复杂度：O(n * m)，其中 n 是数组的长度。空间复杂度取决于 dp 数组，在不进行空间优化的情况下，空间复杂度是 O(n * m)，
     *                      在进行空间优化的情况下，空间复杂度可以降到 O(m)。
     *
     * https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/416-fen-ge-deng-he-zi-ji-by-chen-li-guan-ahv7/
     * @param nums
     * @return
     */
    fun canPartition1(nums: IntArray): Boolean {
        var sum = 0
        for (num in nums) sum += num

        // 和为奇数时，不可能划分成两个和相等的集合
        if (sum % 2 != 0) return false

        val m = nums.size + 1
        val n = sum / 2 + 1
        val dp = BooleanArray(n)

        // base case：
        // dp[0] = true：表示背包容量为 0 时，背包都是装满的；
        dp[0] = true
        // dp[1...j] = false：表示没有物品，背包容量 > 0 时，不可能被装满；
        for (j in 1 until n) dp[j] = false

        // dp 方程
        for (i in 1 until m) {
            for (j in n - 1 downTo 1) {
                if (j - nums[i - 1] >= 0) {
                    // 背包容量充足：
                    // 选择不装入背包，状态和没装第i个物品相同
                    // 选择装入背包，装了第i个物品，就要看背包的剩余(j - nums[i-1])的重量可否被(i - 1)个物品装满；可以，那么把第i个物品装进去，也可装满背包j的重量
                    dp[j] = dp[j] || dp[j - nums[i - 1]]
                }
            }
        }

        // 表示 前 m - 1 个物品是否可以将容量为 n - 1 = sum/2（分割成了两个子集）的背包装满
        return dp[n - 1]
    }


    /**
     * 494. 目标和  备注：这是 0-1背包问题的分割等和子集 和 完全背包问题的零钱兑换 II 的聚合题目 （了解）
     *
     * 时间复杂度：O(n * m)，其中 n 是数组的长度，m 是整个数组的元素和 + 目标值 和的一半
     * 空间复杂度：O(n * m)，
     *
     * @param nums
     * @param S
     * @return
     */
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        var sum = 0
        for (num in nums) sum += num

        // 和比目标值小 或者 和为奇数时，不可能存在合法的子集划分
        if (sum < target || (sum + target) % 2 != 0) return 0

        // 推出 sum+ = (sum + target) / 2，也就是把原问题转化成：nums 中存在几个+子集，使得+子集中元素的和为 sum+ = (target + sum) / 2 （装满）
        val sumA = (sum + target) / 2
        return subsets(nums, sumA)
    }

    /**
     * 计算 nums 中有几个子集的和为 sum
     *
     * @param nums
     * @param sum
     * @return
     */
    fun subsets(nums: IntArray, sum: Int): Int {
        val m = nums.size + 1
        val n = sum + 1
        val dp = Array(m) { IntArray(n) }

        // base case
        // 同于一般的背包问题，数组中是含有0值的，在考虑第i个数nums[i-1]时，若nums[i-1]=0且j=0,i!=0, dp[i][0]是不为1的。
        // 因为dp[i-1][j-nums[i-1]] = dp[i-1][j]；因此，选或不选nums[i-1]都能将容量为0的背包装满，因此不能将dp[i][0]全部初始化为1，)
        // 只能将dp[0][0]初始化为1；以及for(j in 0 until n)从0开始，因为nums[i-1]会等于0。

        // 区别 零钱兑换 II 中的 dp[i][0] = 1
        dp[0][0] = 1
        // dp[0][1...j] = 0，表示没有物品，容量>0时，没有方法装满
        for (j in 1 until n) dp[0][j] = 0

        // dp 方程
        for (i in 1 until m) {
            for (j in 0 until n) {
                if (j - nums[i - 1] < 0) {
                    // 背包的空间不足，只能选择不装物品 i
                    dp[i][j] = dp[i - 1][j]
                } else {
                    // 两种选择的结果之和
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]]
                }
            }
        }

        // 前 m - 1 个子集，当背包容量为 n - 1 时，有多少种方法装满背包
        return dp[m - 1][n - 1]
    }

    /**
     * 494. 目标和  状态压缩  备注：这是 0-1背包问题的分割等和子集 和 完全背包问题的零钱兑换 II 的聚合题目
     *
     * 时间复杂度：O(n * m)，其中 n 是数组的长度，m 是整个数组的元素和 + 目标值 和的一半
     * 空间复杂度：O(n)，
     *
     * @param nums
     * @param S
     * @return
     */
    fun findTargetSumWays1(nums: IntArray, target: Int): Int {
        var sum = 0
        for (num in nums) sum += num

        // 和比目标值小 或者 和为奇数时，不可能存在合法的子集划分
        if (sum < target || (sum + target) % 2 == 1) return 0

        // 推出 sum+ = (target + sum) / 2，也就是把原问题转化成：nums 中存在几个+子集，使得+子集中元素的和为 sum+ = (target + sum) / 2 （装满）
        val sumA = (target + sum) / 2
        return subsets1(nums, sumA)
    }

    /**
     * 计算 nums 中有几个子集的和为 sum
     *
     * @param nums
     * @param sum
     * @return
     */
    fun subsets1(nums: IntArray, sum: Int): Int {
        val m = nums.size + 1
        val n = sum + 1
        val dp = IntArray(n)

        // base case
        // 同于一般的背包问题，数组中是含有0值的，在考虑第i个数nums[i-1]时，若nums[i-1]=0且j=0,i!=0, dp[i][0]是不为1的。
        // 因为dp[i-1][j-nums[i-1]] = dp[i-1][j]；因此，选或不选nums[i-1]都能将容量为0的背包装满，因此不能将dp[i][0]全部初始化为1，)
        // 只能将dp[0][0]初始化为1；以及for(j in 0 until n)从0开始，因为nums[i-1]会等于0。

        // 区别 零钱兑换 II 中的 dp[i][0] = 1
        dp[0] = 1
        // dp[0][1...j] = 0，表示没有物品，容量>0时，没有方法装满
        for (j in 1 until n) dp[j] = 0

        // dp 方程
        for (i in 1 until m) {
            for (j in n - 1 downTo 0) {
                if (j - nums[i - 1] >= 0) {
                    // 两种选择的结果之和
                    dp[j] = dp[j] + dp[j - nums[i - 1]]
                }
            }
        }

        // 前 m - 1 个子集，当背包容量为 n - 1 时，有多少种方法装满背包
        return dp[n - 1]
    }


    /**
     * 518. 零钱兑换 II 动态规划
     *
     * 时间复杂度 O(n * amount)
     * 空间复杂度 O(n * amount)
     *
     * https://leetcode-cn.com/problems/coin-change-2/solution/518-ling-qian-dui-huan-ii-by-chen-li-gua-zykf/
     * @param amount
     * @param coins
     * @return
     */
    fun changeII(amount: Int, coins: IntArray): Int {
        val m = coins.size + 1
        val n = amount + 1
        val dp = Array(m) { IntArray(n) }

        // base case
        // dp[0...i][0] = 1，表示有硬币面值，容量为0时，存在一个什么都不做的方法装满
        for (i in 0 until m) dp[i][0] = 1
        // dp[0][1...j] = 0，表示没有硬币面值，容量>0时，没有方法装满
        for (j in 1 until n) dp[0][j] = 0

        // dp 方程
        for (i in 1 until m) {
            for (j in 1 until n) {
                if (j - coins[i - 1] < 0) {
                    // 背包容量装不下当前硬币 coins[i]，选择不装入
                    dp[i][j] = dp[i - 1][j]
                } else {
                    // 背包容量装得下。dp[i][j] 是「共有多少种凑法」，值是以下 选择装和不装 的结果之和
                    // 选择不装入 coins[i] 这个面值的硬币，继承之前的结果，所以状态和不使用第i个硬币相同
                    // 选择装入 coins[i] 这个面值的硬币，那么关注如何剩余重量 j - coins[i-1]。
                    //    如果这个面值的硬币可以装满 剩余重量 [j-coins[i-1]；那么把这个面值的硬币装进去，也可装满背包 j 的总重量
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]]
                }
            }
        }

        // 前 m - 1 个硬币的面值，当背包容量为 n - 1 时，有多少种方法装满背包
        return dp[m - 1][n - 1]
    }

    /**
     * 518. 零钱兑换 II 2 动态规划--空间压缩
     *
     * 时间复杂度 O(n * amount)
     * 空间复杂度 O(amount)
     *
     * https://leetcode-cn.com/problems/coin-change-2/solution/518-ling-qian-dui-huan-ii-by-chen-li-gua-zykf/
     * @param amount
     * @param coins
     * @return
     */
    fun changeII2(amount: Int, coins: IntArray): Int {
        val m = coins.size + 1
        val n = amount + 1
        val dp = IntArray(n)

        // base case
        // dp[0] = 1，表示有硬币面值，容量为0时，存在一个什么都不做的方法装满
        dp[0] = 1
        // dp[0][1...j] = 0，表示没有硬币面值，容量>0时，没有方法装满
        for (j in 1 until n) dp[j] = 0

        // dp 方程
        for (i in 1 until m) {
            for (j in 1 until n) {
                if (j - coins[i - 1] >= 0) {
                    // 背包容量装得下。dp[i][j] 是「共有多少种凑法」，值是以下 选择装和不装 的结果之和
                    // 选择不装入 coins[i] 这个面值的硬币，继承之前的结果，所以状态和不使用第i个硬币相同
                    // 选择装入 coins[i] 这个面值的硬币，那么关注如何剩余重量 j - coins[i-1]。
                    //    如果这个面值的硬币可以装满 剩余重量 [j-coins[i-1]；那么把这个面值的硬币装进去，也可装满背包 j 的总重量
                    dp[j] = dp[j] + dp[j - coins[i - 1]]
                }
            }
        }

        // 前 m - 1 个硬币的面值，当背包容量为 n - 1 时，有多少种方法装满背包
        return dp[n - 1]
    }


    /**
     * 322. 零钱兑换   方式：动态规划
     *
     * 时间复杂度：O(Sn)，其中S是金额，n是面额数。一共需要计算O(S)个状态；对于每个状态，每次需要枚举n个面额来转移状态，所以一共需要O(Sn)的时间复杂度；
     * 空间复杂度：O(Sn)，DP数组需要开长度为总金额S n的空间
     *
     * https://leetcode-cn.com/problems/coin-change/solution/322-ling-qian-dui-huan-by-chen-li-guan/
     * @param coins
     * @param amount
     * @return
     */
    fun coinChange(coins: IntArray, amount: Int): Int {
        val m = coins.size + 1
        val n = amount + 1

        // dp[i][j]表示凑成总金额为 n 所需的最少的硬币个数
        val dp = Array(m) { IntArray(n) }

        // base case
        // dp[0...i][0] = 0，表示凑成总金额为 0 所需的最少的硬币个数是 0
        for (i in 0 until m) dp[i][0] = 0
        // dp[0][1...j] = n，表示凑成总金额为 n 所需的最多的硬币个数是 n = amount + 1。对应一维：必须将所有的 dp 赋最大值，因为要找最小值。
        // （n 表示全部使用面值 1 的硬币进行换，是不可能达到的换取数量）
        for (j in 1 until n) dp[0][j] = n

        // dp 方程
        // 从 1->coins.size + 1，表示前 1 个硬币，对应的 coins[] 就是 0 下标
        for (i in 1 until m) {
            // 从 1->amount + 1，表示总金额是 1 起步，因为 0 在 base 已经计算了
            for (j in 1 until n) {
                if (j - coins[i - 1] < 0) {
                    // 背包容量装不下当前硬币 coins[i]，选择不装入
                    dp[i][j] = dp[i - 1][j]
                } else {
                    // 背包容量装得下。dp[i][j] 是「凑成总金额为 n 所需的最少的硬币个数」，值是以下 选择装和不装 结果的最小值
                    // 选择不装入 coins[i] 这个面值的硬币，继承之前的结果，所以状态和不使用第i个硬币相同
                    // 选择装入 coins[i] 这个面值的硬币，那么关注如何剩余重量 j - coins[i-1]。
                    //    如果这个面值的硬币可以装满 剩余重量 [j-coins[i-1]；那么把这个面值的硬币装进去，也可装满背包 j 的总重量。硬币数 + 1
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1)
                }

            }
        }

        // 没有任何一种硬币组合能组成总金额，返回-1
        if (dp[m - 1][n - 1] == n) dp[m - 1][n - 1] = -1

        // 前 m - 1 个硬币的面值，当背包容量为 n - 1 时，所需的最少的硬币个数
        return dp[m - 1][n - 1]
    }

    /**
     * 322. 零钱兑换1   方式：动态规划--状态压缩
     *
     * 时间复杂度：O(Sn)，其中S是金额，n是面额数。一共需要计算O(S)个状态；对于每个状态，每次需要枚举n个面额来转移状态，所以一共需要O(Sn)的时间复杂度；
     * 空间复杂度：O(S)，DP数组需要开长度为总金额S的空间
     *
     * https://leetcode-cn.com/problems/coin-change/solution/322-ling-qian-dui-huan-by-chen-li-guan/
     * @param coins
     * @param amount
     * @return
     */
    fun coinChange1(coins: IntArray, amount: Int): Int {
        val m = coins.size + 1
        val n = amount + 1

        // dp[j]表示凑成总金额为 n 所需的最少的硬币个数
        val dp = IntArray(n)

        // base case
        // dp[0...i][0] = 0，表示凑成总金额为 0 所需的最少的硬币个数是 0
        for (i in 0 until m) dp[0] = 0
        // dp[0][1...j] = n，表示凑成总金额为 n 所需的最多的硬币个数是 n = amount + 1，方便求最小值。（n 表示全部使用面值 1 的硬币进行换，是不可能达到的换取数量）
        for (j in 1 until n) dp[j] = n

        // dp 方程
        // 外层 for 循环在遍历硬币的面值，求所有选择的最小值
        for (i in 1 until m) {
            // 内层 for 循环在遍历所有状态的所有取值
            for (j in 1 until n) {
                if (j - coins[i - 1] >= 0) {
                    // 背包容量装得下。dp[i][j] 是「凑成总金额为 n 所需的最少的硬币个数」，值是以下 选择装和不装 结果的最小值
                    // 选择不装入 coins[i] 这个面值的硬币，继承之前的结果，所以状态和不使用第i个硬币相同
                    // 选择装入 coins[i] 这个面值的硬币，那么关注如何剩余重量 j - coins[i-1]。
                    //    如果这个面值的硬币可以装满 剩余重量 [j-coins[i-1]；那么把这个面值的硬币装进去，也可装满背包 j 的总重量。硬币数 + 1
                    dp[j] = Math.min(dp[j], dp[j - coins[i - 1]] + 1)
                }

            }
        }

        // 没有任何一种硬币组合能组成总金额，返回-1
        if (dp[n - 1] == amount + 1) dp[n - 1] = -1

        // 前 m - 1 个硬币的面值，当背包容量为 n - 1 时，所需的最少的硬币个数
        return dp[n - 1]
    }


    /**
     * 139. 单词拆分
     * 思路：转化为是否可以用 wordDict 中的词组合成字符串 s，完全背包问题，并且为"考虑排列顺序的完全背包问题"，外层循环为 s ，内层循环为选择池 wordDict。
     *
     * 时间复杂度：O(s.length × wordDict.length)；
     * 空间复杂度：O(wordDict.length)；
     *
     * https://leetcode-cn.com/problems/word-break/solution/139-dan-ci-chai-fen-by-chen-li-guan-gjki/
     * @param s
     * @param wordDict
     * @return
     */
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val n = s.length + 1
        // dp[i]：以 i 结尾的字符串是否可以被 wordDict 中组合而成
        val dp = BooleanArray(n)

        // base case：表示空串且合法
        dp[0] = true

        // 遍历背包：字符串 s
        for (i in 1 until n) {
            // 遍历物品：wordDict 中的词
            for(word in wordDict) {
                // 判断 s.substring(i - sz, i) == word：
                // 若不相等，说明 从[i - sz]到i的字符 与 该word 不匹配，继续遍历；若相等，说明匹配。
                val sz = word.length
                if (i - sz >= 0 && s.substring(i - sz, i) == word) {
                    dp[i] = dp[i] || dp[i - sz]
                }
            }
        }

        // 最后返回 dp[s.length]
        return dp[n - 1]
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

        // 初始化base case：定义 dp 数组，相当于已经预处理新增第一行、第一行均为0
        val dp = Array(m) { IntArray(n) }

        // 根据状态转移方程：dp[i][j] 表示以(i,j)为根据点，它最大的正方形的边长取决于(上, 左, 左上)边长最短的那一个
        for (i in 1 until m) {
            for (j in 1 until n) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1
                } else {
                    dp[i][j] = 0
                }
            }
        }

        // 遍历 dp[i][j]，计算最大的值
        var res = 0
        for (i in 1 until m) {
            for (j in 1 until n) {
                res = Math.max(res, dp[i][j])
            }
        }
        return res * res
    }

    /**
     * 221. 最大正方形 解法一：动态规划（空间优化）
     *
     * 思路：增加 northwest 西北角解决"左上角"的问题
     *
     * 时间复杂度：O(mn)，其中m和n分别为行数和列数；
     * 空间复杂度：O(n)，使用了空间大小为n的数组
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
     * 718. 最长重复子数组
     *
     * 区别：本题与公共子序列不同，子序列不一定都是连续的，只要前面有相同的子序列，哪怕当前比较的字符不一样，
     * 那么当前字符串之前的子序列也不会为 0。而子串(子数组)是连续的，若当前比较的字符不相同，则当前位置的最长公共子数组(子串)的长度为 0，即 dp[i][j] = 0(就是没有)
     *
     * 时间复杂度：O(mn)；
     * 空间复杂度：O(mn)；
     *
     * https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/solution/718-zui-chang-zhong-fu-zi-shu-zu-by-chen-j9zz/
     * @param A
     * @param B
     * @return
     */
    fun findLength(A: IntArray, B: IntArray): Int {
        val m = A.size + 1
        val n = B.size + 1

        // dp[i][j]：表示以(i,j)为根据点，第一个数组A前i个元素和数组B前j个元素组成的最长公共子数组(相当于子串)的长度。
        val dp = Array(m) { IntArray(n) }

        // 外层 for 循环在遍历s1所有状态的所有取值
        // s1的前i个子序列 和 s2的前j个子序列
        for (i in 1 until m) {
            for (j in 1 until n) {
                // 若当前两个元素值相同，即 A[i] == B[j]，则说明当前元素可以构成公共子数组，所以还要加上它们的前一个元素构成的最长公共子数组的长度(在原来的基础上加 1)
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1
                } else {
                    // 若当前两个元素值不同，即 A[i] != B[j]，则说明当前元素无法构成公共子数组 (就是：当前元素不能成为公共子数组里的一员)。
                    // 因为公共子数组必须是连续的，而此时的元素值不同，相当于直接断开了，此时状态转移方程：dp[i][j] = 0。
                    dp[i][j] = 0
                }
            }
        }

        // 遍历 dp[i][j]，计算最大的值
        var res = 0
        for (i in 1 until m) {
            for (j in 1 until n) {
                res = Math.max(res, dp[i][j])
            }
        }

        return res
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
        // dp 定义是：s1的前i个子序列 和 s2的前i个子序列 的最长公共子序列长度。因此宽高是s1.length+1和s2.length+1
        val dp = Array(m) { IntArray(n) }

        // base case:  dp默认0行或0列是0，所以不需要设置了
//        dp[0][..] = dp[..][0] = 0

        // 外层 for 循环在遍历s1所有状态的所有取值
        // s1的前i个子序列 和 s2的前j个子序列
        for (i in 1 until m) {
            // 内层 for 循环在遍历s2所有状态的所有取值
            for (j in 1 until n) {
                // dp默认0行或0列是0，真实存值是从1行和1列开始，而s1和s2还是从0行或0列开始；现在i和j从1开始，所以s1要减1
                if (s1[i - 1] == s2[j - 1]) {
                    // 如果s1[i-1] == s2[j-1]，说明这个字符 s1[i-1]和s2[j-1] 一定在 最长公共子序列长度 中
                    dp[i][j] = dp[i - 1][j - 1] + 1
                } else {
                    // 如果s1[i-1] != s2[j-1]，s1[i-1] 和 s2[j-1] 至少有一个不在最长公共子序列长度 中，穷举三种情况的结果，取其中的最大结果
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j])
                }
            }
        }

        return dp[m - 1][n - 1]
    }

    /**
     * 583. 两个字符串的删除操作 解法一：动态规划（自底向上）（了解）
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
     * 712. 两个字符串的最小ASCII删除和 解法一：动态规划（自底向上）（了解）
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
     * 91. 解码方法
     * 题意：一个解码内容为一个 item。
     *
     * 时间复杂度：O(n)；
     * 空间复杂度：O(n)；
     *
     * https://leetcode-cn.com/problems/decode-ways/solution/91-jie-ma-fang-fa-by-chen-li-guan-marj/
     *
     * @param s
     * @return
     */
    fun numDecodings(s: String): Int {
        // dp 定义是：「位置 i 自己能否形成独立 item 」和「位置 i 能够与上一位置（i-1）能否形成 item」
        val dp = IntArray(s.length + 1)

        // base case
        dp[0] = 1

        for (i in 1 until s.length + 1) {
            // 如果当前字符不是0，则至少有一条转移路径，从 dp[i-1] 而来
            if (s[i - 1] != '0') {
                dp[i] = dp[i - 1]
            }

            // 只能由位置 i 的与前一位置（i-1）共同作为一个 item --> dp[i] 由 dp[i - 2] 转移过来
            // 位置 i 既能作为独立 item 也能与上一位置形成 item  --> dp[i] 由 dp[i - 1] + dp[i - 2] 转移过来
            if (i > 1 && s[i - 2] != '0' && ((s[i - 2] - '0') * 10 + (s[i - 1] - '0')) <= 26) {
                dp[i] = dp[i] + dp[i - 2]
            }
        }
        return dp[s.length]
    }



    /**
     * 72. 编辑距离（了解）
     *
     * 时间复杂度 ：O(mn)，其中 mm 为 word1 的长度，nn 为 word2 的长度。
     * 空间复杂度 ：O(mn)，我们需要大小为 O(mn)O(mn) 的 DD 数组来记录状态值。
     *
     * @param word1
     * @param word2
     * @return
     */
    fun minEditDistance(s1: String, s2: String): Int {
        val m = s1.length + 1
        val n = s2.length + 1

        val dp = Array(m) { IntArray(n) }

        // base case
        for (i in 0 until m) dp[i][0] = i
        for (j in 0 until n) dp[0][j] = j

        // dp
        for (i in 1 until m) {
            for (j in 1 until n) {
                if (s1[i - 1] == s2[j - 1]) {
                    // s1[i]和s2[j] 跳过 ，啥都不做
                    dp[i][j] = dp[i - 1][j - 1]
                } else {
                    dp[i][j] = min(
                            // s1中插入一个和s2[j]一样的字符，s2[j]就被匹配了，前移j，继续跟i对比。操作数+1
                            dp[i][j - 1] + 1,
                            // 把s1[i]这个字符删掉，前移i，继续跟j对比。操作数+1
                            dp[i - 1][j] + 1,
                            // 把s1[i]替换成s2[j]，它俩就匹配了，同时前移i，j，继续对比。操作数+1
                            dp[i - 1][j - 1] + 1
                    )
                }
            }
        }

        // 返回 s1[0..m-1] 和 s2[0..n-1] 的最小编辑距离
        return dp[m - 1][n - 1]
    }

    fun min(a: Int, b: Int, c: Int): Int {
        return Math.min(a, Math.min(b, c))
    }
}
