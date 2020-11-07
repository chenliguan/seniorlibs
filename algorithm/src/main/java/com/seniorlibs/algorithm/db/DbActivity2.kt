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
        findViewById<View>(R.id.btn_rob).setOnClickListener(this)
        findViewById<View>(R.id.btn_rob_2).setOnClickListener(this)
        findViewById<View>(R.id.btn_max_profit_1).setOnClickListener(this)
        findViewById<View>(R.id.btn_max_profit_2).setOnClickListener(this)
        findViewById<View>(R.id.btn_max_profit_3).setOnClickListener(this)
        findViewById<View>(R.id.btn_max_profit_4).setOnClickListener(this)
        findViewById<View>(R.id.btn_max_profit_5).setOnClickListener(this)
        findViewById<View>(R.id.btn_max_profit_6).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_rob -> {
                LogUtils.e(TAG, "198. 打家劫舍11：${rob11(intArrayOf(2, 7, 9, 3, 1))}")
                LogUtils.e(TAG, "198. 打家劫舍12：${rob12(intArrayOf(2, 7, 9, 3, 1))}")
                LogUtils.e(TAG, "198. 打家劫舍21：${rob21(intArrayOf(2, 7, 9, 3, 1))}")
                LogUtils.e(TAG, "198. 打家劫舍22：${rob22(intArrayOf(2, 7, 9, 3, 1))}")
            }
            R.id.btn_rob_2 -> {
                LogUtils.e(TAG, "213. 打家劫舍 II：${robTwo(intArrayOf(2, 7, 9, 3, 1))}")
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
                LogUtils.e(TAG, "188. 买卖股票的最佳时机 41：${maxProfit41(intArrayOf(7, 1, 5, 3, 6, 4))}")
                LogUtils.e(TAG, "188. 买卖股票的最佳时机 42：${maxProfit41(intArrayOf(7, 1, 5, 3, 6, 4))}")
            }
            R.id.btn_max_profit_5 -> {
                LogUtils.e(TAG, "309. 买卖股票的最佳时机含冷冻期 51：${maxProfit51(intArrayOf(7, 1, 5, 3, 6, 4))}")
                LogUtils.e(TAG, "309. 买卖股票的最佳时机含冷冻期 52：${maxProfit52(intArrayOf(7, 1, 5, 3, 6, 4))}")
            }
            R.id.btn_max_profit_6 -> {
                LogUtils.e(TAG, "714. 买卖股票的最佳时机含手续费 61：${maxProfit61(intArrayOf(7, 1, 5, 3, 6, 4))}")
                LogUtils.e(TAG, "714. 买卖股票的最佳时机含手续费 62：${maxProfit61(intArrayOf(7, 1, 5, 3, 6, 4))}")
            }
            else -> {
            }
        }
    }


    /**
     * 198. 打家劫舍  解法1.1：动态规划（记住）
     * 1、思想：对于第i(i>2)间房屋，有两个选项：
     *  1.偷第i间房屋，就不能偷第i−1间房屋，偷窃总金额为：前i−2间房屋的最高总金额 + 第i间房屋的金额 之和；
     *  2.不偷第i间房屋，偷窃总金额为：前i−1间房屋的最高总金额。
     * base case：
     *    dp[0] = nums[0]               // 只有1间房屋，则偷窃该房屋
     *    dp[1] = max(nums[0],nums[1])  // 只有2间房屋，选择其中金额较高的房屋进行偷窃
     * DP方程：val a = dp[i - 2] + nums[i]
     *        val b = dp[i - 1]
     *        dp[i] = Math.max(a, b)
     * 2、
     * 时间复杂度：O(n)，其中n是数组长度。只需要对数组遍历一次；
     * 空间复杂度：O(n)，使用数组存储整个数组的结果，因此空间复杂度是O(n)；
     * 3、https://leetcode-cn.com/problems/house-robber/solution/198-da-jia-jie-she-by-chen-li-guan/
     * @param nums
     * @return
     */
    fun rob11(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums[0]

        // base case：dp[0]=只有一间房屋，则偷窃该房屋；dp[1]=只有两间房屋，选择其中金额较高的房屋进行偷窃
        val dp = IntArray(nums.size)
        dp[0] = nums[0]
        dp[1] = Math.max(nums[0], nums[1])

        // dp方程：
        for (i in 2 until nums.size) {
            // 1.偷第i间房屋，就不能偷第i−1间房屋，偷窃总金额为：前i−2间房屋的最高总金额 + 第i间房屋的金额 之和
            // 2.不偷第i间房屋，偷窃总金额为：前i−1间房屋的最高总金额
            // 3.选择其中金额较高的房屋进行偷窃
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1])
        }
        return dp[nums.size - 1]
    }

    /**
     * 198. 打家劫舍  解法1.2：动态规划（空间优化）
     * 1、思想：对于第k(k>2)间房屋，有两个选项：
     *  1.偷窃第k间房屋，那么就不能偷窃第k−1间房屋，偷窃总金额为前k−2间房屋的最高总金额与第k间房屋的金额之和；
     *  2.不偷窃第k间房屋，偷窃总金额为前k−1间房屋的最高总金额。
     * base case：
     *    pre = nums[0]                // 只有一间房屋，则偷窃该房屋
     *    cur = max(nums[0], nums[1])  // 只有两间房屋，选择其中金额较高的房屋进行偷窃
     * DP方程：cur = max(pre + nums[i], cur)
     * 2、
     * 时间复杂度：O(n)，其中n是数组长度。只需要对数组遍历一次；
     * 空间复杂度：O(1)，分别使用两个滚动变量，将一维数组状态优化到常数大小。
     * 3、https://leetcode-cn.com/problems/house-robber/solution/198-da-jia-jie-she-by-chen-li-guan/
     * @param nums
     * @return
     */
    fun rob12(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums[0]

        // base case
        var dp_i_0 = nums[0]
        var dp_i_1 = Math.max(nums[0], nums[1])

        // dp
        for (i in 2 until nums.size) {
            val temp = dp_i_1
            dp_i_1 = Math.max(dp_i_0 + nums[i], dp_i_1)
            dp_i_0 = temp
        }
        return dp_i_1
    }

    /**
     * 198. 打家劫舍  解法2.1：动态规划（思路优化）（记住）
     * 1、思路：按二维的0/1背包问题思路结局，更加通用
     * 2、
     * 时间复杂度：O(n)，其中n是数组长度。只需要对数组遍历一次；
     * 空间复杂度：O(n)，使用数组存储整个数组的结果，因此空间复杂度是O(n)；
     *
     * @param nums
     * @return
     */
    fun rob21(nums: IntArray): Int {
        if (nums.isEmpty()) return 0

        // base case：dp[0][0] = 0，第1天不偷窃房屋；dp[0][1]，第1天偷窃房屋
        val dp = Array(nums.size) { IntArray(2) }
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
     * 1、思想：环状排列意味着第一个房子和最后一个房子中只能选择一个偷窃，因此可以把此环状排列房间问题约化为两个单排排列房间子问题：
     *  1.在不偷窃第一个房子的情况下（即nums[0]），最大金额是p1；
     *  2.在不偷窃最后一个房子的情况下（即nums[n-1]），最大金额是p2；
     *  3.综合偷窃最大金额： 为以上两种情况的较大值，即 max(p1, p2)。
     * base case：
     *    pre = 0            // pre(0) -- cur(1) -- num(2)
     *    cur = 0
     * DP方程：cur = max(pre + num, cur)
     * 2、
     * 时间复杂度：O(n)，其中n是数组长度。只需要对数组遍历一次；
     * 空间复杂度：O(1)，不使用滚动数组，只存储前两间房屋的最高总金额，而不需要存储整个数组的结果，因此空间复杂度是O(1)；
     * 3、https://leetcode-cn.com/problems/house-robber-ii/solution/213-da-jia-jie-she-ii-by-chen-li-guan/
     * @param nums
     * @return
     */
    fun robTwo(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums[0]

        return Math.max(
            rob11(Arrays.copyOfRange(nums, 1, nums.size)),
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
    dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
    没持有股票 = max(   选择 休息 ,     选择 卖出      )
    解释：今天我没有持有股票，有两种可能：
    (1)我昨天就没有持有，然后今天选择 休息，所以我今天还是没有持有；
    (2)我昨天持有股票，但今天我选择 卖出了，所以我今天没有持有股票了。

    dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
    持有股票   = max(   选择 休息 ,     选择 买入      )
    解释：今天我持有着股票，有两种可能：
    (1)我昨天持有股票，然后今天选择 休息，所以我今天还持有着股票；
    (2)我昨天就没有持有，但今天我选择 买入，所以今天我就持有股票了。

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
     * 1、思路：k都是1，不会改变，即k对状态转移已经没有影响了，可以进行进一步化简去掉所有 k。
     *    注意：你不能在买入股票前卖出股票。
     *
     * // base case：
     * dp[i][0][0] = dp[i][0] --> dp[0][0] = 0
     * dp[i][0][1] = dp[i][1] --> dp[0][1] = -prices[0]
     *
     * // dp方程：
     * dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])   --> dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])
     * // 第二个状态转移方程利用了 dp[i-1][1-1][0] = dp[i][0][0] = 0  --> k = 0 意味着根本不允许交易，这时利润是 0。也可理解为：只允许交易一次，所以买入前利润都是0
     * dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][1-1][0] - prices[i]) --> dp[i][1] = Math.max(dp[i - 1][1], 0 - prices[i])
     *
     * 2、
     * 时间复杂度：O(n)，这里 n 表示股价数组的长度；
     * 空间复杂度：O(n)，虽然是二维数组，但是第二维是常数，与问题规模无关。
     *
     * @param prices
     * @return
     */
    fun maxProfit11(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        // base case：
        val dp = Array(prices.size) { IntArray(2) }
        dp[0][0] = 0
        dp[0][1] = -prices[0]

        // dp方程：
        for (i in 1 until prices.size) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])
            dp[i][1] = Math.max(dp[i - 1][1], 0 - prices[i])
        }

        return dp[prices.size - 1][0]
    }


    /**
     * 121. 买卖股票的最佳时机 1 -- 最低点买入，最高点卖出 解法2：动态规划（空间优化 -- 考虑使用「滚动变量」（「滚动数组」技巧））
     * 1、思路：第 i 天的最大收益只和第 i - 1 天的最大收益相关，其实不用整个 dp 数组，只需要一个变量储存相邻的那个状态就足够了
     * 2、
     * 时间复杂度：O(n)，这里 n 表示股价数组的长度；
     * 空间复杂度：1，虽然是二维数组，但是第二维是常数，与问题规模无关。
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
     * 1、思路：如果 k 为正无穷，那么就可以认为 k 和 k - 1 是一样的，因此有dp[i-1][k-1][0] = dp[i-1][k][0] 和 dp[i-1][k-1][1] = dp[i-1][k][1]
     *    注意：你不能在买入股票前卖出股票。
     *
     * // base case：
     * dp[i][0][0] = dp[i][0] --> dp[0][0] = 0
     * dp[i][0][1] = dp[i][1] --> dp[0][1] = -prices[0]
     *
     * // dp方程：
     * dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])   --> dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])
     * // 第二个状态转移方程利用了 dp[i-1][k-1][0] = dp[i-1][k][0] = 0
     * dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][1-1][0] - prices[i]) --> dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i])
     *
     * 2、
     * 时间复杂度：O(n)，这里 n 表示股价数组的长度；
     * 空间复杂度：O(n)，虽然是二维数组，但是第二维是常数，与问题规模无关。
     *
     * @param prices
     * @return
     */
    fun maxProfit21(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        // base case：
        val dp = Array(prices.size) { IntArray(2) }
        dp[0][0] = 0
        dp[0][1] = -prices[0]

        // dp方程：
        for (i in 1 until prices.size) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i])
        }

        return dp[prices.size - 1][0]
    }

    /**
     * 122. 买卖股票的最佳时机 2 -- 跌了最低点买入，涨了最高点卖出  核心：k为正无穷  解法2：动态规划（空间优化）
     * 1、思路：第 i 天的最大收益只和第 i - 1 天的最大收益相关，其实不用整个 dp 数组，只需要一个变量储存相邻的那个状态就足够了
     * 2、
     * 时间复杂度：O(n)，这里 n 表示股价数组的长度；
     * 空间复杂度：1，虽然是二维数组，但是第二维是常数，与问题规模无关。
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
     * 123. 买卖股票的最佳时机 3  核心：k=2  解法1：动态规划
     * 1、思路：
     *
     * // base case：

     * // dp方程：
     * // 第二个状态转移方程利用了
     *
     * 2、
     * 时间复杂度：O(n)，这里 n 表示股价数组的长度；
     * 空间复杂度：O(n)，虽然是二维数组，但是第二维是常数，与问题规模无关。
     *
     * @param prices
     * @return
     */
    fun maxProfit31(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        return 0
    }

    /**
     * 124. 买卖股票的最佳时机 4  解法1：
     *
     * @param prices
     * @return
     */
    fun maxProfit41(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        return 0
    }

    /**
     * 309. 买卖股票的最佳时机含冷冻期 5 核心：k 为正无穷但有冷却时间  解法1：动态规划
     * 1、思路：由于具有相同的 k 值，因此情况五和情况二非常相似，不同之处在于情况五有「冷却时间-1天」的限制。
     *         如果要在第 i 天买入股票，第二个状态转移方程中就不能使用 dp[i-1][0]，而应该使用 dp[i-2][0]，其他不变
     *
     * 2、
     * 时间复杂度：O(n)，这里 n 表示股价数组的长度；
     * 空间复杂度：O(n)，虽然是二维数组，但是第二维是常数，与问题规模无关。
     *
     * @param prices
     * @return
     */
    fun maxProfit51(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        // base case：
        val dp = Array(prices.size) { IntArray(2) }
        dp[0][0] = 0
        dp[0][1] = -prices[0]

        // dp方程：
        for (i in 1 until prices.size) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])
            dp[i][1] = Math.max(dp[i - 1][1], (if (i >= 2) dp[i - 2][0] else 0) - prices[i])
        }

        return dp[prices.size - 1][0]
    }

    /**
     * 309. 买卖股票的最佳时机含冷冻期 5 核心：k 为正无穷但有冷却时间  解法2：动态规划（空间优化）
     * 1、思路：第 i 天的最大收益只和第 i - 1 天的最大收益相关，其实不用整个 dp 数组，只需要一个变量储存相邻的那个状态就足够了
     * 2、
     * 时间复杂度：O(n)，这里 n 表示股价数组的长度；
     * 空间复杂度：1，虽然是二维数组，但是第二维是常数，与问题规模无关。
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
        var dp_pre_0 = 0

        // dp方程：
        for (i in 1 until prices.size) {
            // 记录 dp[i-1][0]
            val temp = dp_i_0

            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_i_0 = Math.max(temp, dp_i_1 + prices[i])
            // dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i])

            // 记录 dp[i-2][0]
            dp_pre_0 = temp
        }

        // return dp[prices.size - 1][0]
        return dp_i_0
    }

    /**
     * 714. 买卖股票的最佳时机含手续费  解法1：
     *
     * @param prices
     * @return
     */
    fun maxProfit61(prices: IntArray): Int {
        if (prices.isEmpty()) return 0

        return 0
    }
}
