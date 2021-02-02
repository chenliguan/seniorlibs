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
        findViewById<View>(R.id.btn_can_partition).setOnClickListener(this)
        findViewById<View>(R.id.btn_change_II).setOnClickListener(this)
        findViewById<View>(R.id.btn_coin_change).setOnClickListener(this)
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
                LogUtils.e(TAG, "213. 打家劫舍 II：${robII(intArrayOf(2, 7, 9, 3, 1))}")
            }
            R.id.btn_can_partition -> {
                LogUtils.e(TAG, "416. 分割等和子集：${canPartition(intArrayOf(1, 5, 11, 5))}")
                LogUtils.e(TAG, "416. 分割等和子集1：${canPartition1(intArrayOf(1, 5, 11, 5))}")
            }
            R.id.btn_find_target_sum_ways -> {
                LogUtils.e(TAG, "494. 目标和：${findTargetSumWays(intArrayOf(1,1,1,1,1), 3)}")
                LogUtils.e(TAG, "494. 目标和1：${findTargetSumWays1(intArrayOf(1,1,1,1,1), 3)}")
            }
            R.id.btn_change_II -> {
                LogUtils.e(TAG, "518. 零钱兑换 II：${changeII(5, intArrayOf(1, 2, 5))}")
                LogUtils.e(TAG, "518. 零钱兑换 II 2：${changeII2(5, intArrayOf(1, 2, 5))}")
            }
            R.id.btn_coin_change -> {
                LogUtils.e(TAG, "322. 零钱兑换：${coinChange(intArrayOf(1, 2, 5), 11)}")
                LogUtils.e(TAG, "322. 零钱兑换 1：${coinChange1(intArrayOf(1, 2, 5), 11)}")
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
            else -> {
            }
        }
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
     * 416. 分割等和子集   动态规划
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
     * 494. 目标和  备注：这是 0-1背包问题的分割等和子集 和 完全背包问题的零钱兑换 II 的聚合题目
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
        val dp =  IntArray(n)

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
        // dp[0][1...j] = n，表示凑成总金额为 n 所需的最多的硬币个数是 n = amount + 1，方便求最小值。（n 表示全部使用面值 1 的硬币进行换，是不可能达到的换取数量）
        for (j in 1 until n) dp[0][j] = n

        // dp 方程
        for (i in 1 until m) {
            for (j in 1 until n) {
                if (j - coins[i - 1] < 0) {
                    // 背包容量装不下当前硬币 coins[i]，选择不装入
                    dp[i][j] = dp[i - 1][j]
                } else {
                    // 背包容量装得下。dp[i][j] 是「凑成总金额为 n 所需的最少的硬币个数」，值是以下 选择装和不装 结果的最小值
                    // 选择不装入 coins[i] 这个面值的硬币，继承之前的结果，所以状态和不使用第i个硬币相同
                    // 选择装入 coins[i] 这个面值的硬币，那么关注如何剩余重量 j - coins[i-1]。
                    //    如果这个面值的硬币可以装满 剩余重量 [j-coins[i-1]；那么把这个面值的硬币装进去，也可装满背包 j 的总重量。硬币数 + 1
                    dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i][j - coins[i - 1]])
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
        for (i in 1 until m) {
            for (j in 1 until n) {
                if (j - coins[i - 1] >= 0) {
                    // 背包容量装得下。dp[i][j] 是「凑成总金额为 n 所需的最少的硬币个数」，值是以下 选择装和不装 结果的最小值
                    // 选择不装入 coins[i] 这个面值的硬币，继承之前的结果，所以状态和不使用第i个硬币相同
                    // 选择装入 coins[i] 这个面值的硬币，那么关注如何剩余重量 j - coins[i-1]。
                    //    如果这个面值的硬币可以装满 剩余重量 [j-coins[i-1]；那么把这个面值的硬币装进去，也可装满背包 j 的总重量。硬币数 + 1
                    dp[j] = Math.min(dp[j], 1 + dp[j - coins[i - 1]])
                }

            }
        }

        // 没有任何一种硬币组合能组成总金额，返回-1
        if (dp[n - 1] == amount + 1) dp[n - 1] = -1

        // 前 m - 1 个硬币的面值，当背包容量为 n - 1 时，所需的最少的硬币个数
        return dp[n - 1]
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

        // base case：
        val dp = Array(prices.size) { IntArray(2) }
        dp[0][0] = 0
        dp[0][1] = -prices[0]

        // dp方程：
        for (i in 1 until prices.size) {
            // 今天没持有股，有两种情况：(1)昨天不持股，今天选择休息；(2)昨天持股，今天选择卖出股票（现金数增加）
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])

            // 今天持有股，有两种情况：(1)昨天持股，今天选择休息；(2)昨天不持股，今天选择买入股票（现金数减少）
            dp[i][1] = Math.max(dp[i - 1][1], 0 - prices[i])
        }

        return dp[prices.size - 1][0]
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

        // base case：
        val dp = Array(prices.size) { IntArray(2) }
        dp[0][0] = 0
        dp[0][1] = -prices[0]

        // dp方程：
        for (i in 1 until prices.size) {
            // 今天没持有股，有两种情况：(1)昨天不持股，今天选择休息；(2)昨天持股，今天选择卖出股票（现金数增加）
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])

            // 今天持有股，有两种情况：(1)昨天持股，今天选择休息；(2)昨天不持股，今天选择买入股票（现金数减少）
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i])
        }

        return dp[prices.size - 1][0]
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
     * 123. 买卖股票的最佳时机 3  核心：k=2  解法1：动态规划
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
     * 124. 买卖股票的最佳时机 4  核心：k为任意值  解法1：
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

        // base case：
        val dp = Array(prices.size) { IntArray(2) }
        dp[0][0] = 0
        dp[0][1] = -prices[0]

        // dp方程：
        for (i in 1 until prices.size) {
            // 今天没持有股，有两种情况：(1)昨天不持股，今天选择休息；(2)昨天持股，今天选择卖出股票（现金数增加）
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])

            // 今天持有股，有两种情况：(1)昨天持股，今天选择休息；(2)前天不持股，今天选择买入股票（现金数减少）
            dp[i][1] = Math.max(dp[i - 1][1], (if (i > 1) dp[i - 2][0] else 0) - prices[i])
        }

        return dp[prices.size - 1][0]
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
     * 714. 买卖股票的最佳时机含手续费 6  核心：k 为正无穷但有手续费   解法1：动态规划（空间优化）
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

        // base case：
        val dp = Array(prices.size) { IntArray(2) }
        dp[0][0] = 0
        dp[0][1] = -prices[0] - fee

        // dp方程：
        for (i in 1 until prices.size) {
            // 今天没持有股，有两种情况：(1)昨天不持股，今天选择休息；(2)昨天持股，今天选择卖出股票（现金数增加）
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])

            // 今天持有股，有两种情况：(1)昨天持股，今天选择休息；(2)昨天不持股，今天选择买入股票（现金数减少）
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee)
        }

        return dp[prices.size - 1][0]
    }

    /**
     * 714. 买卖股票的最佳时机含手续费 6  核心：k 为正无穷但有手续费   解法1：动态规划
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
}
