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
        findViewById<View>(R.id.btn_max_sub_array).setOnClickListener(this)
        findViewById<View>(R.id.btn_max_product).setOnClickListener(this)
        findViewById<View>(R.id.btn_length_of_LIS).setOnClickListener(this)

        findViewById<View>(R.id.btn_rob).setOnClickListener(this)
        findViewById<View>(R.id.btn_rob_2).setOnClickListener(this)

        findViewById<View>(R.id.btn_max_profit_1).setOnClickListener(this)
        findViewById<View>(R.id.btn_max_profit_2).setOnClickListener(this)
        findViewById<View>(R.id.btn_max_profit_3).setOnClickListener(this)
        findViewById<View>(R.id.btn_max_profit_4).setOnClickListener(this)
        findViewById<View>(R.id.btn_max_profit_5).setOnClickListener(this)
        findViewById<View>(R.id.btn_max_profit_6).setOnClickListener(this)

        findViewById<View>(R.id.btn_unique_paths).setOnClickListener(this)
        findViewById<View>(R.id.btn_unique_paths_with_obstacles).setOnClickListener(this)
        findViewById<View>(R.id.btn_min_path_sum).setOnClickListener(this)
        findViewById<View>(R.id.btn_minimum_total).setOnClickListener(this)

        findViewById<View>(R.id.btn_longest_palindrome_sub_seq).setOnClickListener(this)
        findViewById<View>(R.id.btn_count_sub_strings).setOnClickListener(this)
        findViewById<View>(R.id.btn_longest_palindrome).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_max_sub_array -> {
                LogUtils.e(
                        TAG,
                        "53. 最大子序和：${maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4))}"
                )
            }
            R.id.btn_max_product -> {
                LogUtils.e(TAG, "152. 乘积最大子数组：${maxProduct(intArrayOf(2, 3, -2, 4, -1))}")
            }

            R.id.btn_length_of_LIS -> {
                LogUtils.e(TAG, "300. 最长递增子序列：${lengthOfLIS(intArrayOf(10, 9, 2, 5, 3, 7, 101, 18))}")
            }


            R.id.btn_rob -> {
                LogUtils.e(TAG, "198. 打家劫舍11：${rob11(intArrayOf(2, 7, 9, 3, 1))}")
                LogUtils.e(TAG, "198. 打家劫舍12：${rob12(intArrayOf(2, 7, 9, 3, 1))}")
                LogUtils.e(TAG, "198. 打家劫舍21：${rob21(intArrayOf(2, 7, 9, 3, 1))}")
                LogUtils.e(TAG, "198. 打家劫舍22：${rob22(intArrayOf(2, 7, 9, 3, 1))}")
            }
            R.id.btn_rob_2 -> {
                LogUtils.e(TAG, "213. 打家劫舍 II：${robII(intArrayOf(2, 7, 9, 3, 1))}")
            }
            R.id.btn_max_profit_1 -> {
                LogUtils.e(TAG, "121. 买卖股票的最佳时机 11：${maxProfit11(intArrayOf(7, 1, 5, 3, 6, 4))}")
                LogUtils.e(TAG, "121. 买卖股票的最佳时机 12：${maxProfit12(intArrayOf(7, 1, 5, 3, 6, 4))}")
            }
            R.id.btn_max_profit_2 -> {
                LogUtils.e(TAG, "122. 买卖股票的最佳时机 21：${maxProfit21(intArrayOf(7, 1, 5, 3, 6, 4))}")
                LogUtils.e(TAG, "122. 买卖股票的最佳时机 22：${maxProfit22(intArrayOf(7, 1, 5, 3, 6, 4))}")
            }
            R.id.btn_max_profit_3 -> {
                LogUtils.e(TAG, "123. 买卖股票的最佳时机 31：${maxProfit31(intArrayOf(7, 1, 5, 3, 6, 4))}")
                LogUtils.e(TAG, "123. 买卖股票的最佳时机 32：${maxProfit31(intArrayOf(7, 1, 5, 3, 6, 4))}")
            }
            R.id.btn_max_profit_4 -> {
                LogUtils.e(TAG, "188. 买卖股票的最佳时机 41：${maxProfit41(2, intArrayOf(7, 1, 5, 3, 6, 4))}")
            }
            R.id.btn_max_profit_5 -> {
                LogUtils.e(
                        TAG,
                        "309. 买卖股票的最佳时机含冷冻期 51：${maxProfit51(intArrayOf(7, 1, 5, 3, 6, 4))}"
                )
                LogUtils.e(
                        TAG,
                        "309. 买卖股票的最佳时机含冷冻期 52：${maxProfit52(intArrayOf(7, 1, 5, 3, 6, 4))}"
                )
            }
            R.id.btn_max_profit_6 -> {
                LogUtils.e(
                        TAG,
                        "714. 买卖股票的最佳时机含手续费 61：${maxProfit61(intArrayOf(7, 1, 5, 3, 6, 4), 1)}"
                )
                LogUtils.e(
                        TAG,
                        "714. 买卖股票的最佳时机含手续费 62：${maxProfit62(intArrayOf(7, 1, 5, 3, 6, 4), 1)}"
                )
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
            R.id.btn_min_path_sum -> {
                val paths = arrayOf(intArrayOf(1, 3, 1), intArrayOf(1, 5, 1), intArrayOf(4, 2, 1))
                LogUtils.e(TAG, "64. 最小路径和：${minPathSum(paths)}")
            }
            R.id.btn_minimum_total -> {
                val res = listOf(listOf(2), listOf(3, 4), listOf(6, 5, 7), listOf(4, 1, 8, 3))
                LogUtils.e(TAG, "120. 三角形最小路径和：${minimumTotal(res)}")
                LogUtils.e(TAG, "120. 三角形最小路径和：${minimumTotal1(res)}")
                LogUtils.e(TAG, "120. 三角形最小路径和：${minimumTotal2(res)}")
                LogUtils.e(TAG, "120. 三角形最小路径和：${minimumTotal3(res)}")
            }

            R.id.btn_longest_palindrome_sub_seq -> {
                LogUtils.e(TAG, "516. 最长回文子序列：${longestPalindromeSubseq("bbcbab")}")
            }
            R.id.btn_count_sub_strings -> {
                LogUtils.e(TAG, "647. 回文子串：${countSubstrings("abbc")}")
            }
            R.id.btn_longest_palindrome -> {
                LogUtils.e(TAG, "5. 最长回文子串：${longestPalindrome("abbc")}")
            }
            else -> {
            }
        }
    }




    /**
     * 53. 最大子序和（最大子数组）   解法一：动态规划
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
     * 152. 乘积最大子数组  解法：动态规划
     *
     * 时间复杂度：程序一次循环遍历了nums，故时间复杂度为O(n)。
     * 空间复杂度：优化后只使用常数个临时变量作为辅助空间，与n无关，故空间复杂度为O(1)。
     *
     * https://leetcode-cn.com/problems/maximum-product-subarray/solution/152-cheng-ji-zui-da-zi-shu-zu-by-chen-li-7gd0/
     * @param nums
     * @return
     */
    fun maxProduct(nums: IntArray): Int {
        val n = nums.size
        if (n == 0) return 0

        // 当选中第 i 状态时，以 nums[i] 结尾的连续子数组的最值，计算最大值还是最小值由 j 来表示，j 就两个状态值；
        // dp[i] 有两种「选择」，要么自成一派，要么和前面的子数组乘积；
        val dp = Array(n) { IntArray(2) }

        // base case，由于 nums[i] 必须被选取，那么
        dp[0][0] = nums[0]
        dp[0][1] = nums[0]

        // dp 方程
        for (i in 1 until n) {
            if (nums[i] >= 0) {
                // 正数 * 前面的最小值 依然是最小值；
                dp[i][0] = Math.min(nums[i], nums[i] * dp[i - 1][0])
                // 正数 * 前面的最大值 依然是最大值；（同一个正数）
                dp[i][1] = Math.max(nums[i], nums[i] * dp[i - 1][1])
            } else {
                // 负数 * 前面的最大值 变成了最小值；
                dp[i][0] = Math.min(nums[i], nums[i] * dp[i - 1][1])
                // 负数 * 前面的最小值 变成最大值；（同一个负数）
                dp[i][1] = Math.max(nums[i], nums[i] * dp[i - 1][0])
            }
        }

        // 只关心最大值，需要遍历 dp[i][1]，计算最大的值
        var res = Int.MIN_VALUE
        for (i in 0 until n) {
            res = Math.max(res, dp[i][1])
        }
        return res
    }


    /**
     * 300. 最长递增子序列（不连续）  解法：动态规划
     *
     * 时间复杂度：O(N^2)，这里 N 是数组的长度，写了两个 for 循环；
     * 空间复杂度：O(N)，要使用和输入数组长度相等的状态数组，因此空间复杂度是 O(N)。
     *
     * https://leetcode-cn.com/problems/longest-increasing-subsequence/
     * @param nums
     * @return
     */
    fun lengthOfLIS(nums: IntArray): Int {
        val n = nums.size
        if (n == 0) return 0

        // dp定义：表示以 nums[i] 结尾的「上升子序列」的长度。也就是等于下标 i 之前严格小于 nums[i] 的状态值的最大者 +1。
        val dp = IntArray(n)

        // base case：每个数字本身长度就是 1
        for (i in 0 until n) dp[i] = 1

        // dp方程
        for (i in 0 until n) {
            for (j in 0 until i) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1)
                }
            }
        }

        // 只关心最大值，需要遍历 dp[i]，计算最大的值
        var res = 0
        for (i in 0 until n) {
            res = Math.max(res, dp[i])
        }
        return res
    }


    /**
     * 198. 打家劫舍  解法1.1：动态规划（记住）
     *
     * 时间复杂度：O(n)，其中n是数组长度。只需要对数组遍历一次；
     * 空间复杂度：O(n)，使用数组存储整个数组的结果，因此空间复杂度是O(n)；
     *
     * https://leetcode-cn.com/problems/house-robber/solution/198-da-jia-jie-she-by-chen-li-guan/
     * @param nums
     * @return
     */
    fun rob11(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums[0]

        // dp 定义：前 i 间房屋可偷窃最高的金额
        val dp = IntArray(nums.size)

        // base case：dp[0]=只有一间房屋，则偷窃该房屋；
        //            dp[1]=只有两间房屋，选择其中金额较高的房屋进行偷窃
        dp[0] = nums[0]
        dp[1] = Math.max(nums[0], nums[1])

        // dp方程
        for (i in 2 until nums.size) {
            // 对于第i(i>2)间房屋，有两个选项：
            // 1.偷第i间房屋，就不能偷第i−1间房屋，总金额为：第i间房屋的金额 + 前i−2间房屋的总金额
            // 2.不偷第i间房屋，偷窃总金额为：前i−1间房屋的总金额
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1])
        }
        return dp[nums.size - 1]
    }

    /**
     * 198. 打家劫舍  解法1.2：动态规划（空间优化）（记住）
     *
     * 时间复杂度：O(n)，其中n是数组长度。只需要对数组遍历一次；
     * 空间复杂度：O(1)，分别使用两个滚动变量，将一维数组状态优化到常数大小。
     *
     * https://leetcode-cn.com/problems/house-robber/solution/198-da-jia-jie-she-by-chen-li-guan/
     * @param nums
     * @return
     */
    fun rob12(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums[0]

        // base case
        var pre = nums[0]
        var cur = Math.max(nums[0], nums[1])

        // dp
        for (i in 2 until nums.size) {
            // 记录当前 i-1 时的值 temp = cur，在下一次遍历时就等于 i-2 的值了
            val temp = cur
            cur = Math.max(nums[i] + pre, cur)
            pre = temp
        }
        return cur
    }

    /**
     * 198. 打家劫舍  解法2.1：动态规划（思路优化）
     * 1、思路：按二维的0/1背包问题思路结局，更加通用
     * 2、
     * 时间复杂度：O(n)，其中n是数组长度。只需要对数组遍历一次；
     * 空间复杂度：O(n)，使用数组存储整个数组的结果，因此空间复杂度是O(n)；
     * 3、https://leetcode-cn.com/problems/house-robber/solution/198-da-jia-jie-she-by-chen-li-guan/
     * @param nums
     * @return
     */
    fun rob21(nums: IntArray): Int {
        if (nums.isEmpty()) return 0

        val dp = Array(nums.size) { IntArray(2) }

        // base case：dp[0][0] = 0，第1天不偷窃房屋；dp[0][1]，第1天偷窃房屋
        dp[0][0] = 0
        dp[0][1] = nums[0]

        // dp方程：
        for (i in 1 until nums.size) {
            // 1.第i天没偷房子，选择 第i-1天没偷房子 和 第i-1天偷了房子 中金额最高的
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1])
            // 2.第i天偷了房子，第i-1天没偷房子+第i天偷房子的金额(第i-1天不能偷房子)
            dp[i][1] = dp[i - 1][0] + nums[i]
        }

        // 3.第nums.size天选择偷或不偷时，选择其中金额最高的方式
        return Math.max(dp[nums.size - 1][0], dp[nums.size - 1][1])
    }

    /**
     * 198. 打家劫舍  解法2.2：动态规划（空间优化）
     * 1、思路：新状态和相邻的一个状态的 偷 或 不偷 都有关，其实不用整个 dp 数组，需要变量储存相邻的那个状态+临时变量储存第i-1天没偷房子的旧状态
     * 2、
     * 时间复杂度：O(n)，其中n是数组长度。只需要对数组遍历一次；
     * 空间复杂度：O(1)，分别使用两个滚动变量，将一维数组状态优化到常数大小。
     * 3、https://leetcode-cn.com/problems/house-robber/solution/198-da-jia-jie-she-by-chen-li-guan/
     * @param nums
     * @return
     */
    fun rob22(nums: IntArray): Int {
        if (nums.isEmpty()) return 0

        // base case：
        // dp[0][0] = 0        --> dp[i][0] = 0
        // dp[0][1] = nums[0]  --> dp[i][1] = nums[0]
        var dp_i_0 = 0
        var dp_i_1 = nums[0]

        // dp方程：
        for (i in 1 until nums.size) {
            // 需要临时变量储存第i-1天没偷房子的旧状态，因为dp_i_1需要旧的dp_i_0状态
            val temp = dp_i_0
            // dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1])
            dp_i_0 = Math.max(temp, dp_i_1)
            // dp[i][1] = dp[i - 1][0] + nums[i]
            dp_i_1 = temp + nums[i]
        }

        // return Math.max(dp[nums.size - 1][0], dp[nums.size - 1][1])
        return Math.max(dp_i_0, dp_i_1)
    }


    /**
     * 213. 打家劫舍 II  解法一：动态规划（空间优化）
     * 思想：环状排列意味着第一个房子和最后一个房子中只能选择一个偷窃，因此可以把此环状排列房间问题约化为两个单排排列房间子问题：
     *  1.在不偷窃第一个房子的情况下（即nums[0]），最大金额是p1；
     *  2.在不偷窃最后一个房子的情况下（即nums[n-1]），最大金额是p2；
     *  3.综合偷窃最大金额： 为以上两种情况的较大值，即 max(p1, p2)。
     *
     * 时间复杂度：O(n)，其中n是数组长度。只需要对数组遍历一次；
     * 空间复杂度：O(1)，不使用滚动数组，只存储前两间房屋的最高总金额，而不需要存储整个数组的结果，因此空间复杂度是O(1)；
     *
     * https://leetcode-cn.com/problems/house-robber-ii/solution/213-da-jia-jie-she-ii-by-chen-li-guan/
     * @param nums
     * @return
     */
    fun robII(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums[0]

        return Math.max(
                // 不偷第一个房子
                rob11(Arrays.copyOfRange(nums, 1, nums.size)),
                // 不偷最后一个房子
                rob11(Arrays.copyOfRange(nums, 0, nums.size - 1))
        )
    }



    /**
    1、用一个三维数组就可以装下这几种状态的全部组合：dp[i][k][0 or 1] (0<=i<=n-1,1<=k<=K)
     * i为天数
     * k为最多交易次数
     * [0,1]为是否持有股票
    总状态数: n*K*2种状态

    for 0 <= i < n:
    for 1 <= k <= K:
    for s in {0, 1}:
    dp[i][k][s] = max(buy, sell, rest)

    2、状态转移框架分析

    // 今天没持有股，有两种情况：(1)昨天不持股，今天选择休息；(2)昨天持股，今天选择卖出股票（现金数增加）
    dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
    没持有股票 = max(   选择 休息 ,     选择 卖出      )

    // 今天持有股，有两种情况：(1)昨天持股，今天选择休息；(2)昨天不持股，今天选择买入股票。注意：只允许交易一次，因此手上的现金数就是当天的股价的相反数
    dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
    持有股票   = max(   选择 休息 ,     选择 买入      )

    dp[-1][k][0] = 0          // 因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0 。
    dp[-1][k][1] = -infinity  // 还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能。
    dp[i][0][0] = 0           // 因为 k 是从 1 开始的，所以 k = 0 意味着根本不允许交易，这时候利润当然是 0 。
    dp[i][0][1] = -infinity   // 不允许交易的情况下，是不可能持有股票的，用负无穷表示这种不可能。

    3、总结一下 base case 和  DP方程
    base case：
    dp[-1][k][0] = dp[i][0][0] = 0
    dp[-1][k][1] = dp[i][0][1] = -infinity

    DP方程：
    dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
    dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     */

    /**
     * 121. 买卖股票的最佳时机 1 -- 最低点买入，最高点卖出 核心：k=1  解法1：动态规划
     * 1、思路：k都是1，不会改变，即k对状态转移已经没有影响了，可以进行进一步化简去掉所有 k
     *         (1)可以买入代表之前最多买入了0次，所以第二个状态转移方程是 dp[i-1][0][0]。--> 必须满足：手上无股票买入
     *         (2)第二个状态转移方程利用了 dp[i-1][1-1][0] = dp[i][0][0] = 0，理解为只允许交易一次，所以买入前利润都是0
     *    注意：你不能在买入股票前卖出股票。
     *
     * // base case：
     * dp[i][0][0] = dp[i][0] --> dp[0][0] = 0
     * dp[i][0][1] = dp[i][1] --> dp[0][1] = -prices[0]
     *
     * // dp方程：
     * dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])   --> dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])
     * dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][1-1][0] - prices[i]) --> dp[i][1] = Math.max(dp[i - 1][1], 0 - prices[i])
     *
     * 2、
     * 时间复杂度：O(n)，这里 n 表示股价数组的长度；
     * 空间复杂度：O(n)，虽然是二维数组，但是第二维是常数，与问题规模无关。
     *
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/121-mai-mai-gu-piao-de-zui-jia-shi-ji-1-by-chen-li/
     * @param prices
     * @return
     */
    fun maxProfit11(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        val m = prices.size
        val dp = Array(m) { IntArray(2) }

        // base case：
        dp[0][0] = 0
        dp[0][1] = -prices[0]

        // dp方程：
        for (i in 1 until m) {
            // 今天没持有股，有两种情况：(1)昨天不持股，今天选择休息；(2)昨天持股，今天选择卖出股票（现金数增加）
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])

            // 今天持有股，有两种情况：(1)昨天持股，今天选择休息；(2)昨天不持股，今天选择买入股票（现金数减少）
            dp[i][1] = Math.max(dp[i - 1][1], 0 - prices[i])
        }

        return dp[m - 1][0]
    }


    /**
     * 121. 买卖股票的最佳时机 1 -- 最低点买入，最高点卖出 解法2：动态规划（空间优化 -- 考虑使用「滚动变量」（「滚动数组」技巧））
     * 1、思路：第 i 天的最大收益只和第 i - 1 天的最大收益相关，其实不用整个 dp 数组，只需要一个变量储存相邻的那个状态就足够了
     *
     * 2、
     * 时间复杂度：O(n)，这里 n 表示股价数组的长度；
     * 空间复杂度：1，虽然是二维数组，但是第二维是常数，与问题规模无关。
     *
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/121-mai-mai-gu-piao-de-zui-jia-shi-ji-1-by-chen-li/
     * @param prices
     * @return
     */
    fun maxProfit12(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        // base case:
        // dp[0][0] = 0           --> dp[i][0] = 0
        // dp[0][1] = -prices[0]  --> dp[i][1] = -prices[0]
        var dp_i_0 = 0
        var dp_i_1 = -prices[0]

        // dp方程：
        for (i in 1 until prices.size) {
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i])
            // dp[i][1] = max(dp[i-1][1], 0 - prices[i])
            dp_i_1 = Math.max(dp_i_1, -prices[i])
        }

        // return dp[prices.size - 1][0]
        return dp_i_0
    }

    /**
     * 122. 买卖股票的最佳时机 2 -- 跌了最低点买入，涨了最高点卖出  核心：k为正无穷  解法1：动态规划
     * 1、思路：如果k为正无穷
     *         (1)可以买入代表之前买入了k-1次，所以第二个状态转移方程是 dp[i-1][k-1][0]
     *         (2)所以就可以认为k和k-1是一样的，所以第二个状态转移方程利用了 dp[i-1][k-1][0] = dp[i-1][k][0]
     *    注意：你不能在买入股票前卖出股票。--> 必须满足：手上无股票且不在冷冻期（1天）买入
     *
     * // base case：
     * dp[i][0][0] = dp[i][0] --> dp[0][0] = 0
     * dp[i][0][1] = dp[i][1] --> dp[0][1] = -prices[0]
     *
     * // dp方程：
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])   --> dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]) --> dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i])
     *
     * 2、
     * 时间复杂度：O(n)，这里 n 表示股价数组的长度；
     * 空间复杂度：O(n)，虽然是二维数组，但是第二维是常数，与问题规模无关。
     *
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/122-mai-mai-gu-piao-de-zui-jia-shi-ji-1-by-chen-li/
     * @param prices
     * @return
     */
    fun maxProfit21(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        val m = prices.size
        val dp = Array(prices.size) { IntArray(2) }

        // base case：
        dp[0][0] = 0
        dp[0][1] = -prices[0]

        // dp方程：
        for (i in 1 until m) {
            // 今天没持有股，有两种情况：(1)昨天不持股，今天选择休息；(2)昨天持股，今天选择卖出股票（现金数增加）
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])

            // 今天持有股，有两种情况：(1)昨天持股，今天选择休息；(2)昨天不持股，今天选择买入股票（现金数减少）
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i])
        }

        return dp[m - 1][0]
    }

    /**
     * 122. 买卖股票的最佳时机 2 -- 跌到最低点买入，涨到最高点卖出  核心：k为正无穷  解法2：动态规划（空间优化）
     * 1、思路：第 i 天的最大收益只和第 i - 1 天的最大收益相关，其实不用整个 dp 数组，只需要一个变量储存相邻的那个状态就足够了
     *
     * 2、
     * 时间复杂度：O(n)，这里 n 表示股价数组的长度；
     * 空间复杂度：1，虽然是二维数组，但是第二维是常数，与问题规模无关。
     *
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/122-mai-mai-gu-piao-de-zui-jia-shi-ji-1-by-chen-li/
     * @param prices
     * @return
     */
    fun maxProfit22(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        // base case:
        // dp[0][0] = 0           --> dp[i][0] = 0
        // dp[0][1] = -prices[0]  --> dp[i][1] = -prices[0]
        var dp_i_0 = 0
        var dp_i_1 = -prices[0]

        // dp方程：
        for (i in 1 until prices.size) {
            val temp = dp_i_0
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_i_0 = Math.max(temp, dp_i_1 + prices[i])
            // dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
            dp_i_1 = Math.max(dp_i_1, temp - prices[i])
        }

        // return dp[prices.size - 1][0]
        return dp_i_0
    }


    /**
     * 309. 买卖股票的最佳时机含冷冻期 5 核心：k 为正无穷但有冷却时间  解法1：动态规划
     * 1、思路：由于具有相同的 k 值，因此情况五和情况二非常相似，不同之处在于情况五有「冷却时间-1天」的限制--卖出股票后，你无法在第二天买入股票 (即冷冻期为1天)，只能在第三天买入股票
     *         所以，如果要在第i天买入股票，必须满足：手上无股票且不在冷冻期买入 --> 第二个状态转移方程中就不能使用 dp[i-1][0]，而应该使用 dp[i-2][0]，其他不变
     *
     * 2、
     * 时间复杂度：O(n)，这里 n 表示股价数组的长度；
     * 空间复杂度：O(n)，虽然是二维数组，但是第二维是常数，与问题规模无关。
     *
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/309-zui-jia-mai-mai-gu-piao-shi-ji-han-leng-don-41/
     * @param prices
     * @return
     */
    fun maxProfit51(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        val m = prices.size
        val dp = Array(m) { IntArray(2) }

        // base case：
        dp[0][0] = 0
        dp[0][1] = -prices[0]

        // dp方程：
        for (i in 1 until m) {
            // 今天没持有股，有两种情况：(1)昨天不持股，今天选择休息；(2)昨天持股，今天选择卖出股票（现金数增加）
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])

            // 今天持有股，有两种情况：(1)昨天持股，今天选择休息；(2)前天不持股，今天选择买入股票（现金数减少）
            dp[i][1] = Math.max(dp[i - 1][1], (if (i >= 2) dp[i - 2][0] else 0) - prices[i])
        }

        return dp[m - 1][0]
    }


    /**
     * 309. 买卖股票的最佳时机含冷冻期 5 核心：k 为正无穷但有冷却时间  解法2：动态规划（空间优化）
     * 1、思路：第 i 天的最大收益只和第 i - 1 天和第 i - 2 天的最大收益相关，其实不用整个 dp 数组，只需要一个变量储存相邻的那个状态就足够了
     *
     * 2、
     * 时间复杂度：O(n)，这里 n 表示股价数组的长度；
     * 空间复杂度：1，虽然是二维数组，但是第二维是常数，与问题规模无关。
     *
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/309-zui-jia-mai-mai-gu-piao-shi-ji-han-leng-don-41/
     * @param prices
     * @return
     */
    fun maxProfit52(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        // base case:
        // dp[0][0] = 0           --> dp[i][0] = 0
        // dp[0][1] = -prices[0]  --> dp[i][1] = -prices[0]
        var dp_i_0 = 0
        var dp_i_1 = -prices[0]
        // 代表 dp[i-2][0]
        var dp_i_2_0 = 0

        // dp方程：
        for (i in 1 until prices.size) {
            // 记录 dp[i-1][0]
            val temp = dp_i_0

            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_i_0 = Math.max(temp, dp_i_1 + prices[i])
            // dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
            dp_i_1 = Math.max(dp_i_1, dp_i_2_0 - prices[i])

            // 记录 dp[i-2][0]
            dp_i_2_0 = temp
        }

        // return dp[prices.size - 1][0]
        return dp_i_0
    }

    /**
     * 714. 买卖股票的最佳时机含手续费 6  核心：k 为正无穷但有手续费   解法1：动态规划（空间优化）（了解）
     * 1、思路：由于具有相同的 k 值，因此情况六和情况二非常相似，不同之处在于情况六有「手续费」，因此在每次买入或卖出股票之后的收益需要扣除手续费
     *
     * 2、
     * 时间复杂度：O(n)，这里 n 表示股价数组的长度；
     * 空间复杂度：O(n)，虽然是二维数组，但是第二维是常数，与问题规模无关。
     *
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/solution/714-mai-mai-gu-piao-de-zui-jia-shi-ji-han-shou-1-7/
     * @param prices
     * @return
     */
    fun maxProfit61(prices: IntArray, fee: Int): Int {
        if (prices.isEmpty()) return 0

        val m = prices.size
        val dp = Array(m) { IntArray(2) }

        // base case：
        dp[0][0] = 0
        dp[0][1] = -prices[0] - fee

        // dp方程：
        for (i in 1 until m) {
            // 今天没持有股，有两种情况：(1)昨天不持股，今天选择休息；(2)昨天持股，今天选择卖出股票（现金数增加）
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])

            // 今天持有股，有两种情况：(1)昨天持股，今天选择休息；(2)昨天不持股，今天选择买入股票（现金数减少）
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee)
        }

        return dp[m - 1][0]
    }

    /**
     * 714. 买卖股票的最佳时机含手续费 6  核心：k 为正无穷但有手续费   解法1：动态规划（了解）
     * 1、思路：第 i 天的最大收益只和第 i - 1 天的最大收益相关，其实不用整个 dp 数组，只需要一个变量储存相邻的那个状态就足够了
     *
     * 2、
     * 时间复杂度：O(n)，这里 n 表示股价数组的长度；
     * 空间复杂度：1，虽然是二维数组，但是第二维是常数，与问题规模无关。
     *
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/solution/714-mai-mai-gu-piao-de-zui-jia-shi-ji-han-shou-1-7/
     * @param prices
     * @return
     */
    fun maxProfit62(prices: IntArray, fee: Int): Int {
        if (prices.isEmpty()) return 0

        // base case:
        // dp[0][0] = 0           --> dp[i][0] = 0
        // dp[0][1] = -prices[0]  --> dp[i][1] = -prices[0] - fee
        var dp_i_0 = 0
        var dp_i_1 = -prices[0] - fee

        // dp方程：
        for (i in 1 until prices.size) {
            val temp = dp_i_0
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_i_0 = Math.max(temp, dp_i_1 + prices[i])
            // dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i]) - fee
            dp_i_1 = Math.max(dp_i_1, temp - prices[i] - fee)
        }

        // return dp[prices.size - 1][0]
        return dp_i_0
    }


    /**
     * 123. 买卖股票的最佳时机 3  核心：k=2  解法1：动态规划  （困难）
     * 1、思路：之前的解法，都在穷举所有状态，只是之前的题目中 k 都被化简掉了。这道题由于没有消掉 k 的影响，所以必须要对 k 进行穷举
     *
     * // base case：
     *
     * // dp方程：
     * // 第二个状态转移方程利用了
     *
     * 2、
     * 时间复杂度：O(n)，这里 n 表示股价数组的长度；
     * 空间复杂度：O(n)，虽然是三维数组，但是第三维是常数，与问题规模无关。
     *
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/solution/123-mai-mai-gu-piao-de-zui-jia-shi-ji-iii-by-chen-/
     * @param prices
     * @return
     */
    fun maxProfit31(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        val maxK = 2

        // base case：
        val dp = Array(prices.size) { Array(maxK + 1) { IntArray(2) } }
        for (k in 1 until maxK + 1) {
            dp[0][k][0] = 0
            dp[0][k][1] = -prices[0]
        }

        // dp方程：
        for (i in 1 until prices.size) {
            for (k in 1 until maxK + 1) {
                // 今天没持有股，有两种情况：(1)昨天不持股，今天选择休息；(2)昨天持股，今天选择卖出股票（现金数增加）
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i])

                // 今天持有股，有两种情况：(1)昨天持股，今天选择休息；(2)昨天不持股，今天选择买入股票（现金数减少）
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i])
            }
        }

        return dp[prices.size - 1][maxK][0]
    }

    /**
     * 124. 买卖股票的最佳时机 4  核心：k为任意值  解法1：  （困难）
     * 1、思路：如果k为正无穷
     *         (1)可以买入代表之前买入了k-1次，所以第二个状态转移方程是 dp[i-1][k-1][0]
     *         (2)所以就可以认为k和k-1是一样的，所以第二个状态转移方程利用了 dp[i-1][k-1][0] = dp[i-1][k][0]
     *    注意：你不能在买入股票前卖出股票。
     *
     *    本题和3的区别：一次交易由买入和卖出构成，至少需要两天。所以说有效的限制 k 应该不超过 n/2，如果超过，就没有约束作用了，相当于 k = 正无穷 --> 买卖股票的最佳时机 2
     *
     * 2、
     * 时间复杂度：O(nk)，这里n表示股价数组的长度，k是最多可以完成交易的次数；
     * 空间复杂度：O(nk)，三维dp数组的大小，第3维是常数，故忽略
     *
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/solution/188-mai-mai-gu-piao-de-zui-jia-shi-ji-iv-by-chen-l/
     * @param prices
     * @return
     */
    fun maxProfit41(maxK: Int, prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        // 一次交易由买入和卖出构成，至少需要两天。所以说有效的限制 k 应该不超过 n/2，如果超过，就没有约束作用了，相当于 k = 正无穷 --> 买卖股票的最佳时机 2
        if (maxK >= prices.size / 2) return maxProfit21(prices)

        // base case：
        val dp = Array(prices.size) { Array(maxK + 1) { IntArray(2) } }
        for (k in 1 until maxK + 1) {
            dp[0][k][0] = 0
            dp[0][k][1] = -prices[0]
        }

        // dp方程：
        for (i in 1 until prices.size) {
            for (k in 1 until maxK + 1) {
                // 今天没持有股，有两种情况：(1)昨天不持股，今天选择休息；(2)昨天持股，今天选择卖出股票（现金数增加）
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i])

                // 今天持有股，有两种情况：(1)昨天持股，今天选择休息；(2)昨天不持股，今天选择买入股票（现金数减少）
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i])
            }
        }

        return dp[prices.size - 1][maxK][0]
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

        // dp 定义：向下走 m 步，以及向右走 n 步，的不同路径和
        val dp = Array(m) { IntArray(n) }

        // base case：
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

        // dp 定义：向下走 m 步，以及向右走 n 步，的不同路径和
        val dp = IntArray(n)

        // base case：
        for (j in 0 until n) dp[j] = 1

        // dp 方程：
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
     * @param grid
     * @return
     */
    fun uniquePathsWithObstacles(grid: Array<IntArray>): Int {
        if (grid.isEmpty() || grid[0].isEmpty()) return 0

        val m = grid.size
        val n = grid[0].size

        // dp 定义：向下走 m 步，以及向右走 n 步，的不同路径和
        val dp = Array(m) { IntArray(n) }

        // base case：一旦遇到值为1的情况，后面的都不会被赋值成1了，都是默认值0
        for (i in 0 until m) {
            if (grid[i][0] == 1) break
            dp[i][0] = 1
        }
        for (j in 0 until n) {
            if (grid[0][j] == 1) break
            dp[0][j] = 1
        }

        // dp 方程：
        for (i in 1 until m) {
            for (j in 1 until n) {
                // 1是障碍物，0是非障碍物，0才往下迭代
                if (grid[i][j] == 0) {
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
     * @param grid
     * @return
     */
    fun uniquePathsWithObstacles1(grid: Array<IntArray>): Int {
        if (grid.isEmpty() || grid[0].isEmpty()) return 0

        val m: Int = grid.size
        val n: Int = grid[0].size

        // base case：定义 dp 数组并初始化第 1 列
        val dp = IntArray(n)
        dp[0] = if (grid[0][0] == 0) 1 else 0

        // db：
        for (i in 0 until m) {
            for (j in 1 until n) {
                // 处理[0][1]的情况，从0开始
                if (grid[i][j] == 1) {
                    dp[j] = 0
                    continue
                }

                if (grid[i][j] == 0) {
                    dp[j] = dp[j] + dp[j - 1]
                }
            }
        }

        return dp[n - 1]
    }


    /**
     * 64. 最小路径和
     *
     * 时间复杂度：O(mn)；
     * 空间复杂度：O(mn)；
     *
     * https://leetcode-cn.com/problems/minimum-path-sum/solution/64-zui-xiao-lu-jing-he-by-chen-li-guan-51x8/
     * @param grid
     * @return
     */
    fun minPathSum(grid: Array<IntArray>): Int {
        if (grid.isEmpty() || grid[0].isEmpty()) return 0

        val m = grid.size
        val n = grid[0].size

        // dp定义：向下走m步，以及向右走n步，路径上的数字最小和
        val dp = Array(m) { IntArray(n) }

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


    /**
     * 120. 三角形最小路径和  解法一：动态规划（自底向上）（记住）
     *
     * 注意：当计算三角形的最后一行时，需要额外一行辅助计算(默认值时0)，因此数组长度是n + 1。自底往上，计算的结果是dp[0][0]，因此是倒序遍历。
     *
     * 时间复杂度：O(n^2)，n为三角形的行数。
     * 空间复杂度：O(n^2)，n为三角形的行数。
     *
     * https://leetcode-cn.com/problems/triangle/solution/120-san-jiao-xing-zui-xiao-lu-jing-he-by-chen-li-g/
     * @param triangle
     * @return
     */
    fun minimumTotal(triangle: List<List<Int>>): Int {
        val n = triangle.size + 1

        // dp的定义：自底往上走到相邻的结点上 ———— 正下方(i+1,j) 和 右下方(i+1,j+1) 的最小路径和
        val dp = Array(n) { IntArray(n) }

        // base case：辅助空间的元素都初始化为 0
//        for (j in 0 until n) {
//            dp[j][0] = 0
//        }

        // 从最后一行开始向第一行走，即从下到上
        for (i in n - 2 downTo 0) {
            // 从第一列向最后一列走，即从左到右
            for (j in 0 until triangle[i].size) {
                // 求下一层相邻的结点上 ———— 正下方(i+1,j) 和 右下方(i+1,j+1) 的最小路径和。因此需要添加辅助行
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j]
            }
        }

        // 自底往上，最终值时 dp[0][0]
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
        val n = triangle.size + 1

        // dp的定义：向下走到相邻的结点上 ———— 正下方(i+1,j) 和 右下方(i+1,j+1) 的最小路径和
        val dp = IntArray(n)

        // base case：
        // for (j in 0 until n) {
        //     dp[j][0] = 0
        // }

        // dp的定义：三角形
        for (i in n - 2 downTo 0) {
            for (j in 0 until triangle[i].size) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle[i][j]
            }
        }
        return dp[0]
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
    fun minimumTotal2(triangle: List<List<Int>>): Int {
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
    fun minimumTotal3(triangle: List<List<Int>>): Int {
        val res = Array(triangle.size) { IntArray(triangle.size) }
        for (i in triangle.indices) {
            for (j in triangle.indices) {
                res[i][j] = -1
            }
        }

        return dfs1(triangle, res, 0, 0)
    }

    private fun dfs1(triangle: List<List<Int>>, res: Array<IntArray>, i: Int, j: Int): Int {
        if (i == triangle.size) return 0

        if (res[i][j] != -1) return res[i][j]

        // 该点相邻两点到底边的最小路径和中的较小值，再加上该点本身的值
        res[i][j] = Math.min(
                dfs1(triangle, res, i + 1, j),
                dfs1(triangle, res, i + 1, j + 1)
        ) + triangle[i][j]

        return res[i][j]
    }



    /**
     * 516. 最长回文子序列
     *
     * 时间复杂度：O(n^2)，
     * 空间复杂度：O(n^2)：在填表的过程中，只参考了右上方的数值。事实上可以优化，但是增加了代码编写和理解的难度，丢失可读和可解释性，在这里不优化空间。
     *
     * https://leetcode-cn.com/problems/longest-palindromic-subsequence/solution/516-zui-chang-hui-wen-zi-xu-lie-by-chen-vz944/
     * @param s
     * @return
     */
    fun longestPalindromeSubseq(s: String): Int {
        val n = s.length
        // dp 数组全部初始化为 0
        val dp = Array(n) { IntArray(n) }

        // base case
        for (i in 0 until n) dp[i][i] = 1

        // 反着遍历保证正确的状态转移
        for (i in n - 1 downTo 0) {
            for (j in i + 1 until n) {
                // 状态转移方程 db
                if (s[i] == s[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2
                    LogUtils.d(TAG, "s[i]：" + s[i] + "  i：" + i)
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1])
                }
            }
        }

        // 整个 s 的最长回文子串长度
        return dp[0][n - 1]
    }

    /**
     * 647. 回文子串（个数）  动态规划
     *
     * "回文串”是一个正读和反读都一样的字符串，比如“level”或者“noon”等等就是回文串、
     * "abc"："a", "b", "c"
     * "aaa"："a", "a", "a", "aa", "aa", "aaa"
     * "baba"："b", "a", "b", "a", "bab", "aba"
     * "leveel"："l", "e", "v", "e", "e", "l", "l", "le"
     *
     * 时间复杂度：O(n^2)，
     * 空间复杂度：O(n^2)：在填表的过程中，只参考了右上方的数值。事实上可以优化，但是增加了代码编写和理解的难度，丢失可读和可解释性，在这里不优化空间。
     *
     * https://leetcode-cn.com/problems/palindromic-substrings/solution/647-hui-wen-zi-chuan-by-chen-li-guan/
     * @param s
     * @return
     */
    fun countSubstrings(s: String): Int {
        val n = s.length
        // 记录回文子串数量
        var count = s.length
        // 第一维参数表示起始坐标，第二维参数表示终点坐标
        val dp = Array(n) { BooleanArray(n) }

        // base case：只有一个字母的时候肯定是回文子串，数量是 s.length，eg："aaa"："a", "a", "a" 3个
        for (i in 0 until n) dp[i][i] = true

        // 反着遍历保证正确的状态转移
        for (i in n - 1 downTo 0) {
            for (j in i + 1 until n) {
                // 状态转移方程 db
                if (j - i < 2) {
                    // 子字符串长度小于 2 的时候单独处理
                    // j - i == 0：一个字符，一定是回文子串。如：a(i=1,j=1)=true; j - i == 1：中间只有1个字符，如：aa(i=0,j=1)=true; ab=false
                    dp[i][j] = s[i] == s[j]
                } else {
                    // 假设子串 s[i+1..j-1] 是/否回文子串，如果 s[i] == s[j]，那更大规模的 s[i..j] 也是/否回文子串
                    dp[i][j] = dp[i + 1][j - 1] && (s[i] == s[j])
                }

                if (dp[i][j]) {
                    // 如果 s[i..j] 是回文子串，count + 1
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
     * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/5-zui-chang-hui-wen-zi-chuan-by-chen-li-guan/
     */
    fun longestPalindrome(s: String): String {
        // base case：只有一个字母的时候肯定是回文子串，数量是s.length
        val n = s.length
        // 记录最长回文子串最长长度
        var maxLen = 1
        // 记录最长回文子串起始位置
        var begin = 0
        // 第一维参数表示起始坐标，第二维参数表示终点坐标
        val dp = Array(n) { BooleanArray(n) }

        // base case
        for (i in 0 until n) dp[i][i] = true

        // 反着遍历保证正确的状态转移
        for (i in n - 1 downTo 0) {
            for (j in i + 1 until n) {
                // 状态转移方程 db
                if (j - i < 2) {
                    // 子字符串长度小于 2 的时候单独处理（因为i>j的dp是false，计算结果也是false）
                    // j - i == 0：一个字符，一定是回文子串。如：a(i=1,j=1)=true; j - i == 1：中间只有1个字符，如：aa(i=0,j=1)=true; ab=false
                    dp[i][j] = s[i] == s[j]
                } else {
                    // 假设子串 s[i+1..j-1] 是/否回文子串，如果 s[i] == s[j]，那更大规模的 s[i..j] 也是/否回文子串
                    dp[i][j] = dp[i + 1][j - 1] && (s[i] == s[j])
                }

                if (dp[i][j] && (j - i + 1) > maxLen) {
                    // 如果 s[i..j] 是回文子串，并且长度大于 max，则刷新最长回文子串
                    maxLen = j - i + 1
                    begin = i
                }
            }
        }

        return s.substring(begin, begin + maxLen)
    }

}
