package com.seniorlibs.algorithm.array

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.baselib.utils.LogUtils
import java.util.*

/**
 * 数组
 */
class ArrayActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "ArrayActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, ArrayActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_array)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_remove_duplicates).setOnClickListener(this)
        findViewById<View>(R.id.btn_move_zeroes).setOnClickListener(this)
        findViewById<View>(R.id.btn_max_area).setOnClickListener(this)
        findViewById<View>(R.id.btn_trap).setOnClickListener(this)
        findViewById<View>(R.id.btn_two_sum).setOnClickListener(this)
        findViewById<View>(R.id.btn_three_sum).setOnClickListener(this)
        findViewById<View>(R.id.btn_three_sum_closest).setOnClickListener(this)
        findViewById<View>(R.id.btn_four_sum).setOnClickListener(this)
        findViewById<View>(R.id.btn_merge).setOnClickListener(this)
        findViewById<View>(R.id.btn_find_repeat_number).setOnClickListener(this)
        findViewById<View>(R.id.btn_merge_interval).setOnClickListener(this)
        findViewById<View>(R.id.btn_can_attend_meetings).setOnClickListener(this)
        findViewById<View>(R.id.btn_min_meeting_rooms).setOnClickListener(this)
        findViewById<View>(R.id.btn_min_sub_arrayLen).setOnClickListener(this)
        findViewById<View>(R.id.btn_rotate).setOnClickListener(this)
        findViewById<View>(R.id.btn_rotate_matrix).setOnClickListener(this)
        findViewById<View>(R.id.btn_majority_element).setOnClickListener(this)
        findViewById<View>(R.id.btn_intersection).setOnClickListener(this)
        findViewById<View>(R.id.btn_find_duplicate).setOnClickListener(this)
        findViewById<View>(R.id.btn_spiral_order).setOnClickListener(this)
        findViewById<View>(R.id.btn_generate_matrix).setOnClickListener(this)
        findViewById<View>(R.id.btn_subarray_sum).setOnClickListener(this)
        findViewById<View>(R.id.btn_next_permutation).setOnClickListener(this)
        findViewById<View>(R.id.btn_next_greater_element3).setOnClickListener(this)
        findViewById<View>(R.id.btn_generate).setOnClickListener(this)
        findViewById<View>(R.id.btn_rand10).setOnClickListener(this)
        findViewById<View>(R.id.btn_nth_ugly_number).setOnClickListener(this)
        findViewById<View>(R.id.btn_largest_number).setOnClickListener(this)
        findViewById<View>(R.id.btn_count_and_say).setOnClickListener(this)
        findViewById<View>(R.id.btn_longest_consecutive).setOnClickListener(this)
        findViewById<View>(R.id.btn_reconstruct_queue).setOnClickListener(this)
        findViewById<View>(R.id.btn_can_finish).setOnClickListener(this)
    }

    override fun onClick(v: View) = when (v.id) {
        R.id.btn_remove_duplicates -> {
            val nums = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
            // 删除排序数组中的重复项
            removeDuplicates(nums)
            LogUtils.d(TAG, "26. 删除排序数组中的重复项：${nums.asList()}")
        }
        R.id.btn_move_zeroes -> {
            val nums = intArrayOf(0, 1, 0, 3, 12, 0, 1, 12, 0, 0, 6)
            // 移动零
            moveZeroes(nums)
            LogUtils.d(TAG, "283. 移动零：${nums.asList()}")
        }
        R.id.btn_max_area -> {
            val nums = intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)
            LogUtils.d(TAG, "11. 盛最多水的容器：${maxArea(nums)}")
        }
        R.id.btn_trap -> {
            val nums = intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)
            LogUtils.d(TAG, "42. 接雨水：${trap(nums)}")
        }
        R.id.btn_two_sum -> {
            val sum = twoSum(intArrayOf(2, 11, 15, 7), 9).asList().toString()
            LogUtils.e(TAG, "1. 两数之和：${sum}")
        }
        R.id.btn_three_sum -> {
            val nums = intArrayOf(-4, -1, -1, -1, 0, -1, 1, 2)
            LogUtils.d(TAG, "15. 三数之和：${threeSum(nums)}")
            LogUtils.d(TAG, "15. 三数之和1：${threeSum1(nums)}")
        }
        R.id.btn_three_sum_closest -> {
            val nums = intArrayOf(0, 4, -5, 2, -2, 4, 2, -1, 4)
            LogUtils.d(TAG, "16. 最接近的三数之和：${threeSumClosest(nums, 8)}")
        }
        R.id.btn_four_sum -> {
            val nums = intArrayOf(0, 4, -5, 2, -2, 4, 2, -1, 4)
            LogUtils.d(TAG, "18. 四数之和：${fourSum(nums, 12)}")
            LogUtils.d(TAG, "18. 四数之和1：${fourSum1(nums, 12)}")
        }
        R.id.btn_merge -> {
            val nums1 = intArrayOf(1, 2, 3, 0, 0, 0)
            val nums2 = intArrayOf(2, 5, 6)
            LogUtils.d(TAG, "88. 合并两个有序数组：${merge(nums1, 3, nums2, 3)}")
        }
        R.id.btn_find_repeat_number -> {
            val nums = intArrayOf(2, 3, 1, 0, 2, 5, 3)
            LogUtils.d(TAG, "03. 数组中重复的数字：${findRepeatNumber(nums)}")
        }
        R.id.btn_merge_interval -> {
            //  [[1,4,7,11,15],[2,5,6,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]
            val nums = arrayOf(
                    intArrayOf(1, 4),
                    intArrayOf(2, 5),
                    intArrayOf(8, 16),
                    intArrayOf(17, 24),
                    intArrayOf(26, 30)
            )
            LogUtils.d(TAG, "56. 合并区间：${mergeInterval(nums)}")
        }
        R.id.btn_can_attend_meetings -> {
            //  [[1,4,7,11,15],[2,5,6,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]
            val nums = arrayOf(
                    intArrayOf(1, 4),
                    intArrayOf(2, 5),
                    intArrayOf(8, 16),
                    intArrayOf(17, 24),
                    intArrayOf(26, 30)
            )
            LogUtils.d(TAG, "252. 会议室：${canAttendMeetings(nums)}")
        }
        R.id.btn_min_meeting_rooms -> {
            //  [[1,4,7,11,15],[2,5,6,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]
            val nums = arrayOf(
                    intArrayOf(1, 4),
                    intArrayOf(2, 5),
                    intArrayOf(8, 16),
                    intArrayOf(17, 24),
                    intArrayOf(26, 30)
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                LogUtils.d(TAG, "253. 会议室 II：${minMeetingRooms(nums)}")
            } else {

            }
        }
        R.id.btn_min_sub_arrayLen -> {
            val nums = intArrayOf(2, 3, 1, 2, 4, 3)
            LogUtils.d(TAG, "209. 长度最小的子数组：${minSubArrayLen(7, nums)}")
        }
        R.id.btn_rotate -> {
            val nums = intArrayOf(1, 2, 3, 4, 5, 6)
            LogUtils.d(TAG, "189. 旋转数组：${rotate(nums, 3)}")
        }
        R.id.btn_rotate_matrix -> {
            val array = arrayOf(
                    intArrayOf(1, 2, 3),
                    intArrayOf(4, 5, 6),
                    intArrayOf(7, 8, 9)
            )
            LogUtils.d(TAG, "01.07. 旋转矩阵 == 48. 旋转图像：${rotateMatrix(array)}")
        }
        R.id.btn_majority_element -> {
            val nums = intArrayOf(1, 2, 2, 2, 5, 6)
            LogUtils.d(TAG, "169. 多数元素：${majorityElement(nums)}")
        }
        R.id.btn_intersection -> {
            val nums1 = intArrayOf(1, 2, 2, 2, 5, 6)
            val nums2 = intArrayOf(2, 5, 6)
            LogUtils.d(TAG, "349. 两个数组的交集：${intersection(nums1, nums2)}")
        }
        R.id.btn_find_duplicate -> {
            val nums = intArrayOf(1, 2, 2, 2, 5, 6)
            LogUtils.d(TAG, "287. 寻找重复数：${findDuplicate(nums)}")
        }
        R.id.btn_spiral_order -> {
            val array = arrayOf(
                    intArrayOf(1, 4, 7),
                    intArrayOf(2, 5, 6),
                    intArrayOf(3, 6, 9)
            )
            LogUtils.d(TAG, "剑指 Offer 29. 顺时针打印矩阵 == 54. 螺旋矩阵：${spiralOrder(array)}")
        }
        R.id.btn_generate_matrix -> {
            LogUtils.d(TAG, "59. 螺旋矩阵 II：${generateMatrix(3)}")
        }
        R.id.btn_subarray_sum -> {
            LogUtils.d(TAG, "560. 和为K的子数组：${subarraySum(intArrayOf(1, 1, 1), 2)}")
        }
        R.id.btn_next_permutation -> {
            LogUtils.d(TAG, "31. 下一个排列：${nextPermutation(intArrayOf(1, 3, 5, 4, 1))}")
        }
        R.id.btn_next_greater_element3 -> {
            LogUtils.d(TAG, "556. 下一个更大元素 3：${nextGreaterElement3(12)}")
        }
        R.id.btn_generate -> {
            LogUtils.d(TAG, "118. 杨辉三角：${generate(5)}")
        }
        R.id.btn_rand10 -> {
            LogUtils.d(TAG, "470. 用 Rand7() 实现 Rand10()：${rand10()}")
        }
        R.id.btn_nth_ugly_number -> {
            LogUtils.d(TAG, "263. 丑数：${isUgly(5)}")
            LogUtils.d(TAG, "264. 丑数 II：${nthUglyNumber(7)}")
        }
        R.id.btn_largest_number -> {
            LogUtils.d(TAG, "179. 最大数：${largestNumber(intArrayOf(3, 30, 34))}")
        }
        R.id.btn_count_and_say -> {
            LogUtils.d(TAG, "38. 外观数列：${countAndSay(5)}")
        }
        R.id.btn_longest_consecutive -> {
            LogUtils.d(TAG, "128. 最长连续序列：${longestConsecutive(intArrayOf(100, 4, 200, 1, 3, 2))}")
        }
        R.id.btn_reconstruct_queue -> {
            // [7,0],[4,4],[7,1],[5,0],[6,1],[5,2]
            val nums = arrayOf(
                    intArrayOf(7, 0),
                    intArrayOf(4, 4),
                    intArrayOf(7, 1),
                    intArrayOf(5, 0),
                    intArrayOf(6, 1),
                    intArrayOf(5, 2)
            )
            LogUtils.d(TAG, "406. 根据身高重建队列：${reconstructQueue(nums)}")
        }
        R.id.btn_can_finish -> {
            val array = arrayOf(
                    intArrayOf(1, 0)
            )
            LogUtils.d(TAG, "207. 课程表：${canFinish(2, array)}")
        }
        else -> {
        }
    }

    private fun initData() {

    }


    /**
     *  26. 删除排序数组中的重复项 - 方法一：快慢指针
     *
     * 时间复杂度：O(n)，假设数组的长度是n，那么i和j分别最多遍历n步。
     * 空间复杂度：O(1)，因为要求在原数组中操作，没有创建新数组。
     *
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/solution/26-shan-chu-pai-xu-shu-zu-zhong-de-zhong-fu-xia-90/
     * @param nums
     * @return
     */
    fun removeDuplicates(nums: IntArray): Int {
        var i = 0  // 不重复指针-慢指针
        var j = 1  // 快指针

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
     * 283. 移动零 - 方法一：快慢指针
     *
     * 时间复杂度：O(n)。代码执行的总操作（数组写入）是非 0 元素的数量。
     * 空间复杂度：O(1)。只使用了常量空间，没有创建新数组。
     *
     * https://leetcode-cn.com/problems/move-zeroes/solution/283-yi-dong-ling-2-by-chen-li-guan/
     * @param nums
     */
    private fun moveZeroes(nums: IntArray): Unit {
        var i = 0  // 非零指针-慢指针
        var j = 0  // 快指针

        while (j < nums.size) {
            if (nums[j] != 0) {  // 非零项才需要操作
                if (i != j) {  // 1.判断当前指针和非零指针的差异，如果不等，说明非零指针和快指针之间都是零（否则，当前项是没移动过的，不能清空）
                    nums[i] = nums[j]  // 2.把 当前指针 赋值给 非零指针
                    nums[j] = 0   // 把 当前指针 赋值为 0
                }

                i++  // 核心：先交换再i++，删除排序数组中的重复项是 先i++再交换。因为它必须处理o位置，而重复项只处理1位置开始
            }

            j++ // 3.移动非零指针指向下一个待填充位置
        }
    }

    /**
     * 11. 盛最多水的容器
     *
     * 时间复杂度 O(N)，双指针遍历一次底边宽度 N。
     * 空间复杂度 O(1)，指针使用常数额外空间。
     *
     * https://leetcode-cn.com/problems/container-with-most-water/solution/11-sheng-zui-duo-shui-de-rong-qi-2-by-chen-li-guan/
     * @param height
     * @return
     */
    private fun maxArea(a: IntArray): Int {
        if (a.isEmpty()) return 0

        var i = 0
        var j = a.size - 1
        var max = 0

        while (i < j) {
            // 1.计算宽/高
            val w = j - i
            val h = Math.min(a[i], a[j])
            // 2.计算区域面积，并更新最大面积
            val area = w * h
            if (area > max) {
                max = area
            }
            // 3.选择左右指针最小的高度，最大指针不动，移动小的指针向中间移动
            if (a[i] < a[j]) i++ else j--
        }

        return max
    }

    /**
     * 42. 接雨水（困难）
     *
     * 时间复杂度： O(n)。
     * 空间复杂度： O(1)。
     *
     * https://leetcode-cn.com/problems/trapping-rain-water/solution/42-jie-yu-shui-by-chen-li-guan-0dsm/
     * @param height
     * @return
     */
    fun trap(height: IntArray): Int {
        if (height.isEmpty()) return 0

        var left = 0
        var right = height.size - 1
        var res = 0

        // l_max是height[0..left]中最高柱子的高度，r_max是height[right..n-1]的最高柱子的高度
        var lMax = height[0]
        var rMax = height[height.size - 1]

        while (left <= right) {
            lMax = Math.max(lMax, height[left])
            rMax = Math.max(rMax, height[right])

            // res += min(lMax, rMax) - height[i]
            if (lMax < rMax) {
                // height[left] 只和 lMax 有关
                res += lMax - height[left]
                left++
            } else {
                res += rMax - height[right]
                right--
            }
        }
        return res
    }


    /**
     * 1. 两数之和
     * 核心思想：让时间复杂度下降，使用空间换时间，通过一个 HashMap 记录 元素值->索引 的映射，减少时间复杂度
     *
     * 时间复杂度：O(n)，把包含有n个元素的列表遍历两次，由于哈希表将查找时间缩短到O(1) ，所以时间复杂度为O(n)。
     * 空间复杂度：O(n)，所需的额外空间取决于哈希表中存储的元素数量，该表中存储了n个元素。
     *
     * https://leetcode-cn.com/problems/two-sum/solution/1liang-shu-zhi-he-by-chen-li-guan/
     * @param nums
     * @param target
     * @return
     */
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val n = nums.size
        val map = mutableMapOf<Int, Int>()

        // 1.将元素映射添加到 map 中：key 是数值，value 是下标
        for (i in 0 until n) {
            map[nums[i]] = i
        }

        for (i in 0 until n) {
            // 2.采用目标值-当前值=另一个值，然后在 map 中查这个值
            val other = target - nums[i]

            // 3.如果表中存在 other，且不是 nums[i] 本身
            if (map.containsKey(other) && map[other] != i) {
                return intArrayOf(i, map[other]!!)
            }
        }

        return intArrayOf()
    }

    /**
     * 15. 三数之和 记忆版-保留最核心
     * 判断nums中是否存在三个元素a，b，c，使得a+b+c=0，转化为->a+b=-c
     *
     * 时间复杂度 O(n^2)：其中固定指针k循环复杂度O(n)，双指针i，j 复杂度O(n)。
     * 空间复杂度 O(1)：指针使用常数大小的额外空间。
     *
     * https://leetcode-cn.com/problems/3sum/solution/15san-shu-zhi-he-by-chen-li-guan/
     * @param a
     * @return
     */
    fun threeSum(nums: IntArray): List<List<Int>> {
        if (nums.isEmpty()) return mutableListOf<MutableList<Int>>()

        /* 核心1：对数组进行从小到大排序 */
        Arrays.sort(nums)

        /* 核心2：第二层循环k，k从f+1开始遍历，最大值是nums.size-2（右边有i/j）；留下i和j */
        val res = mutableListOf<MutableList<Int>>()
        for (k in 0 until nums.size - 2) {
            /* 当k的值与前面的值相等时忽略 */
            if (k > 0 && nums[k] == nums[k - 1]) continue

            /* 定义指针i指向k+1，指针j指向数组末尾，交替向中间移动 */
            var i = k + 1
            var j = nums.size - 1

            /* 核心3：开始i指针和j指针的表演，计算当前和。如果等于目标值，++i并去重，--j并去重；如果当前和大于目标值时--j；如果当前和小于目标值时++i */
            while (i < j) {
                val sum = nums[k] + nums[i] + nums[j]

                if (sum < 0) {
                    /* 实力太弱，把菜鸟那边右移一位，并跳过所有相同的nums[i]（注意：++i必须在前，先计算nums[i+1]） */
                    while (i < j && nums[i] == nums[++i]) {
                    }
                } else if (sum > 0) {
                    /* 实力太强，把大神那边左移一位，并跳过所有相同的nums[j] */
                    while (i < j && nums[j] == nums[--j]) {
                    }
                } else {
                    /* 记录组合[k, i, j]到list */
                    res.add(mutableListOf(nums[k], nums[i], nums[j]))
                    /* 执行++i和--j并跳过所有相同复的nums[i]和nums[j] */
                    while (i < j && nums[i] == nums[++i]) {
                    }
                    while (i < j && nums[j] == nums[--j]) {
                    }
                }
            }
        }
        return res
    }

    /**
     * 16. 最接近的三数之和
     * 题目：找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。
     *
     * 时间复杂度 O(n^2)：其中固定指针k循环复杂度O(n)，双指针i，j 复杂度O(n)。
     * 空间复杂度 O(1)：指针使用常数大小的额外空间。
     *
     * https://leetcode-cn.com/problems/3sum-closest/solution/16-zui-jie-jin-de-san-shu-zhi-he-by-chen-st0v/
     * @param nums
     * @param target
     * @return
     */
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) return 0

        /* 核心1：对数组进行从小到大排序 */
        Arrays.sort(nums)

        /* 核心2：第二层循环k，k从f+1开始遍历，最大值是nums.size-2（右边有i/j）；留下i和j */
        var res = nums[0] + nums[1] + nums[2]
        for (k in 0 until nums.size - 2) {
            /* 当k的值与前面的值相等时忽略 */
            if (k > 0 && nums[k] == nums[k - 1]) continue

            /* 定义指针i指向k+1，指针j指向数组末尾，交替向中间移动 */
            var i = k + 1
            var j = nums.size - 1

            /* 核心3：开始i指针和j指针的表演，计算当前和。如果等于目标值，++i并去重，--j并去重；如果当前和大于目标值时--j；如果当前和小于目标值时++i */
            while (i < j) {
                val sum = nums[i] + nums[j] + nums[k]

                // 关键：多出的步骤，比较当前差值和已有差值哪个大
                if (Math.abs(target - sum) < Math.abs(target - res)) {
                    res = sum
                }

                if (sum < target) {
                    /* 实力太弱，把菜鸟那边右移一位，并跳过所有相同的nums[i]（注意：++i必须在前，先计算nums[i+1]） */
                    while (i < j && nums[i] == nums[++i]) {
                    }
                } else if (sum > target) {
                    /* 实力太强，把大神那边左移一位，并跳过所有相同的nums[j] */
                    while (i < j && nums[j] == nums[--j]) {
                    }
                } else {
                    return res
                }

            }
        }
        return res
    }

    /**
     * 15. 三数之和 优化版-深度剪枝
     * 判断nums中是否存在三个元素a，b，c，使得a+b+c=0，转化为->a+b=-c
     *
     * 时间复杂度 O(n^2)：其中固定指针k循环复杂度O(n)，双指针i，j 复杂度O(n)。
     * 空间复杂度 O(1)：指针使用常数大小的额外空间。
     *
     * https://leetcode-cn.com/problems/3sum/solution/15san-shu-zhi-he-by-chen-li-guan/
     * @param a
     * @return
     */
    fun threeSum1(nums: IntArray): List<List<Int>> {
        val res = mutableListOf<MutableList<Int>>()
        /* 当数组元素小于4个时，直接返回 */
        if (nums.size < 3) return res

        /* 核心1：对数组进行从小到大排序 */
        Arrays.sort(nums)

        /* 核心2：第二层循环k，k从f+1开始遍历，最大值是nums.size-2（右边有i/j）；留下i和j */
        for (k in 0 until nums.size - 2) {
            /* 当k的值与前面的值相等时忽略 */
            if (k > 0 && nums[k] == nums[k - 1]) continue

            /* 定义指针i指向k+1，指针j指向数组末尾，交替向中间移动 */
            var i = k + 1
            var j = nums.size - 1

            /* 获取当前最小值，如果最小值比目标值大，说明整个循环越来越大的值根本没戏 */
            val min = nums[k] + nums[i] + nums[i + 1]
            if (min > 0) {
                break
            }
            /* 获取当前最大值，如果最大值比目标值小，说明当前这一轮循环后面越来越小的值根本没戏，但是不能break，下一轮f+还存在==target */
            val max = nums[k] + nums[nums.size - 1] + nums[nums.size - 2]
            if (max < 0) {
                continue
            }

            /* 核心3：开始i指针和j指针的表演，计算当前和。如果等于目标值，++i并去重，--j并去重；如果当前和大于目标值时--j；如果当前和小于目标值时++i */
            while (i < j) {
                val sum = nums[k] + nums[i] + nums[j]

                if (sum < 0) {
                    /* 实力太弱，把菜鸟那边右移一位，并跳过所有相同的nums[i]（注意：++i必须在前，先计算nums[i+1]） */
                    while (i < j && nums[i] == nums[++i]) {
                    }
                } else if (sum > 0) {
                    /* 实力太强，把大神那边左移一位，并跳过所有相同的nums[j] */
                    while (i < j && nums[j] == nums[--j]) {
                    }
                } else {
                    /* 记录组合[k, i, j]到list */
                    res.add(mutableListOf(nums[k], nums[i], nums[j]))
                    /* 执行++i和--j并跳过所有相同复的nums[i]和nums[j] */
                    while (i < j && nums[i] == nums[++i]) {
                    }
                    while (i < j && nums[j] == nums[--j]) {
                    }
                }
            }
        }

        return res
    }

    /**
     * 18. 四数之和 记忆版-保留最核心
     * 判断nums中是否存在四个元素a，b，c和d ，使得a + b + c + d的值与target相等
     *
     * 时间复杂度 O(n^3)：其中固定指针f循环复杂度O(n)，k循环复杂度O(n)，双指针i，j 复杂度O(n)。
     * 空间复杂度 O(1)：指针使用常数大小的额外空间。
     *
     * https://leetcode-cn.com/problems/4sum/solution/18-si-shu-zhi-he-by-chen-li-guan/
     * @param nums
     * @param target
     * @return
     */
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        if (nums.isEmpty()) return mutableListOf<MutableList<Int>>()

        /* 核心1：对数组进行从小到大排序 */
        Arrays.sort(nums)

        /* 核心2：定义4个指针f/k/i/j，f从0开始遍历，最大值是nums.size-3（右边有k/i/j） */
        val res = mutableListOf<MutableList<Int>>()
        for (f in 0 until nums.size - 3) {
            /* 当k的值与前面的值相等时忽略 */
            if (f > 0 && nums[f] == nums[f - 1]) continue

            /* 核心3：第二层循环k，k从f+1开始遍历，最大值是nums.size-2（右边有i/j）；留下i和j */
            for (k in f + 1 until nums.size - 2) {
                /* 当k的值与前面的值相等时忽略 */
                if (k > f + 1 && nums[k] == nums[k - 1]) continue
                /* 定义指针i指向k+1，指针j指向数组末尾，交替向中间移动 */
                var i = k + 1
                var j = nums.size - 1

                /* 核心4：开始i指针和j指针的表演，计算当前和。如果等于目标值，++i并去重，--j并去重；如果当前和大于目标值时--j；如果当前和小于目标值时++i */
                while (i < j) {
                    val sum = nums[f] + nums[k] + nums[i] + nums[j]
                    if (sum < target) {
                        /* 实力太弱，把菜鸟那边右移一位，并跳过所有相同的nums[i]（注意：++i必须在前，先计算nums[i+1]） */
                        while (i < j && nums[i] == nums[++i]) {
                        }
                    } else if (sum > target) {
                        /* 实力太强，把大神那边左移一位，并跳过所有相同的nums[j] */
                        while (i < j && nums[j] == nums[--j]) {
                        }
                    } else {
                        /* 记录组合[f, k, i, j]到list */
                        res.add(mutableListOf(nums[f], nums[k], nums[i], nums[j]))
                        /* 执行++i和--j并跳过所有相同复的nums[i]和nums[j] */
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
     * 18. 四数之和 优化版-深度剪枝
     * 判断nums中是否存在四个元素a，b，c和d ，使得a + b + c + d的值与target相等
     *
     * 时间复杂度 O(n^3)：其中固定指针f循环复杂度O(n)，k循环复杂度O(n)，双指针i，j 复杂度O(n)。
     * 空间复杂度 O(1)：指针使用常数大小的额外空间。
     *
     * https://leetcode-cn.com/problems/4sum/solution/18-si-shu-zhi-he-by-chen-li-guan/
     * @param nums
     * @param target
     * @return
     */
    fun fourSum1(nums: IntArray, target: Int): List<List<Int>> {
        val res = mutableListOf<MutableList<Int>>()
        /* 当数组元素小于4个时，直接返回 */
        if (nums.size < 4) return res

        /* 核心1：对数组进行从小到大排序 */
        Arrays.sort(nums)
        /* 核心2：定义4个指针f/k/i/j，f从0开始遍历，最大值是nums.size-3（右边有k/i/j） */
        for (f in 0 until nums.size - 3) {
            /* 当f的值与前面的值相等时忽略 */
            if (f > 0 && nums[f] == nums[f - 1]) continue

            /* 获取当前最小值，如果最小值比目标值大，说明整个循环越来越大的值根本没戏 */
            val min = nums[f] + nums[f + 1] + nums[f + 2] + nums[f + 3]
            if (min > target) {
                break
            }
            /* 获取当前最大值，如果最大值比目标值小，说明当前这一轮循环后面越来越小的值根本没戏；但是不能break，下一轮f+还存在==target */
            val max = nums[f] + nums[nums.size - 1] + nums[nums.size - 2] + nums[nums.size - 3]
            if (max < target) {
                continue
            }

            /* 核心3：第二层循环k，k从f+1开始遍历，最大值是nums.size-2（右边有i/j）；留下i和j */
            for (k in f + 1 until nums.size - 2) {
                /* 当k的值与前面的值相等时忽略 */
                if (k > f + 1 && nums[k] == nums[k - 1]) continue

                /* 定义指针i指向k+1，指针j指向数组末尾，交替向中间移动 */
                var i = k + 1
                var j = nums.size - 1

                /* 获取当前最小值，如果最小值比目标值大，说明整个循环越来越大的值根本没戏 */
                val min = nums[f] + nums[k] + nums[i] + nums[i + 1]
                if (min > target) {
                    break
                }
                /* 获取当前最大值，如果最大值比目标值小，说明当前这一轮循环后面越来越小的值根本没戏，但是不能break，下一轮f+还存在==target */
                val max = nums[f] + nums[k] + nums[nums.size - 1] + nums[nums.size - 2]
                if (max < target) {
                    continue
                }

                /* 核心4：开始i指针和j指针的表演，计算当前和。如果等于目标值，++i并去重，--j并去重；如果当前和大于目标值时--j；如果当前和小于目标值时++i */
                while (i < j) {
                    val sum = nums[f] + nums[k] + nums[i] + nums[j]
                    if (sum < target) {
                        /* 实力太弱，把菜鸟那边右移一位，并跳过所有相同的nums[i]（注意：++i必须在前，先计算nums[i+1]） */
                        while (i < j && nums[i] == nums[++i]) {
                        }
                    } else if (sum > target) {
                        /* 实力太强，把大神那边左移一位，并跳过所有相同的nums[j] */
                        while (i < j && nums[j] == nums[--j]) {
                        }
                    } else {
                        /* 记录组合[f, k, i, j]到list */
                        res.add(mutableListOf(nums[f], nums[k], nums[i], nums[j]))
                        /* 执行++i和--j并跳过所有相同复的nums[i]和nums[j] */
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
     * 88. 合并两个有序数组  方法： 双指针 / 从后往前
     * 思路：设置指针 len1 和 len2 分别指向 nums1 和 nums2 的有数字尾部，从尾部值开始比较遍历，
     *       同时设置指针 len 指向 nums1 的最末尾，每次遍历比较值大小之后。
     *       因为 nums1 的空间都集中在后面，所以从后向前处理排序的数据会更好，节省空间
     *
     * 时间复杂度：O(m+n)
     * 空间复杂度：O(m+n)
     *
     * https://leetcode-cn.com/problems/merge-sorted-array/solution/88-he-bing-liang-ge-you-xu-shu-zu-by-chen-li-guan/
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
        var p1 = m - 1
        var p2 = n - 1
        var k = p1 + p2 + 1

        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[k--] = nums1[p1--]
            } else {
                nums1[k--] = nums2[p2--]
            }
        }

        // 合并剩余的元素
        while (p1 >= 0) {
            nums1[k--] = nums1[p1--]
        }

        // 当 p2 大于 0 并且 p1 小于 0 时，此时 nums1 数组所有元素已排列过了，而nums2中还剩下p2个元素，需要对nums1的前p2个赋值为nums2的前p2个（直接将前n个进行覆盖）
        while (p2 >= 0) {
            nums1[k--] = nums2[p2--]
        }
    }

    /**
     * 03. 数组中重复的数字
     *
     * 时间复杂度：O(n)，遍历数组一遍。
     * 空间复杂度：O(n)。不重复的每个元素都可能存入集合。
     *
     * https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/solution/03-shu-zu-zhong-zhong-fu-de-shu-zi-by-ch-v318/
     * @param nums
     * @return
     */
    fun findRepeatNumber(nums: IntArray): Int {
        val set = hashSetOf<Int>()
        var repeat = -1

        for (num in nums) {
            if (!set.add(num)) {
                repeat = num
                break
            }
        }

        return repeat
    }


    /**
     * 56. 合并区间
     *
     * 时间复杂度：O(nlogn)，其中 n 为区间的数量。除去排序的开销，我们只需要一次线性扫描，所以主要的时间开销是排序的 O(nlogn)。
     * 空间复杂度：O(logn)，其中 n 为区间的数量。这里计算的是存储答案之外，使用的额外空间。(logn) 即为排序所需要的空间复杂度。
     *
     * https://leetcode-cn.com/problems/merge-intervals/solution/56-he-bing-qu-jian-by-chen-li-guan-7trv/
     * @param intervals
     * @return
     */
    fun mergeInterval(intervals: Array<IntArray>): Array<IntArray?>? {
        // 先按照区间起始位置排序
        Arrays.sort(intervals) { v1, v2 -> v1[0] - v2[0] }

        // 遍历区间
        val res = Array(intervals.size) { IntArray(2) }
        var x = -1
        for (i in 1 until intervals.size) {
            // 如果结果数组是空的，或者 当前数组的起始位置 > 结果中最后数组的终止位置，
            // 则不合并，直接将当前数组加入结果数组 [15,18] -> [8,10] + [15,18]
            if (x == -1 || intervals[i][0] > res[x][1]) {
                res[++x] = intervals[i]
            } else {
                // 反之将当前数组合并至结果中的 最后数组 [2,6] -> [1,3->6] == [1,6]
                res[x][1] = Math.max(res[x][1], intervals[i][1])
            }
        }

        // 复制指定的数组，以空值截断或填充：x = 2，因此 +1 [[1,6],[8,10],[15,18],[0,0]] -> [[1,6],[8,10],[15,18]
        return Arrays.copyOf(res, x + 1)
    }


    /**
     * 252. 会议室
     *
     * 时间复杂度: O(nlogn)。时间复杂度由排序决定。一旦排序完成，只需要 O(n) 的时间来判断交叠。
     * 空间复杂度: O(1)。没有使用额外空间
     *
     * https://leetcode-cn.com/problems/meeting-rooms/solution/252-hui-yi-shi-by-chen-li-guan-obsi/
     * @param intervals
     * @return
     */
    fun canAttendMeetings(intervals: Array<IntArray>): Boolean {
        // 先按照区间起始位置排序
        Arrays.sort(intervals) { v1, v2 -> v1[0] - v2[0] }

        for (i in 1 until intervals.size) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false
            }
        }

        return true
    }


    /**
     * 253. 会议室 II
     *
     * 时间复杂度：O(NlogN)；
     * 空间复杂度：O(N)；额外空间用于建立 最小堆 。在最坏的情况下，堆需要容纳全部 N 个元素。因此空间复杂度为 O(N)。
     *
     * https://leetcode-cn.com/problems/meeting-rooms-ii/solution/253-hui-yi-shi-ii-by-chen-li-guan-n8yr/
     * @param intervals
     * @return
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun minMeetingRooms(intervals: Array<IntArray>): Int {
        // 先按照区间起始位置排序
        Arrays.sort(intervals) { v1, v2 -> v1[0] - v2[0] }

        // 优先堆队列：按升序用小顶堆保存结束的时间最大的k个元素
        val heap = PriorityQueue(Comparator<Int> { o1, o2 -> o1 - o2 })
        // 添加第一次会议
        heap.add(intervals[0][1])

        // 迭代剩余的间隔
        for (i in 1 until intervals.size) {
            // 如果最早空闲的房间空闲，则将该房间分配给本次会议。
            if (intervals[i][0] >= heap.peek()!!) {
                heap.poll()
            }

            // 如果要分配一个新房间，添加到堆中。如果分配了旧房间，那么也必须添加到具有更新结束时间的堆中
            heap.add(intervals[i][1])
        }

        // 堆的大小告诉我们所有会议所需的最少房间数。
        return heap.size
    }


    /**
     * 209. 长度最小的子数组
     *
     * 时间复杂度：O(n)，其中 nn 是数组的长度。指针 start 和 end 最多各移动 n 次。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/minimum-size-subarray-sum/solution/209-chang-du-zui-xiao-de-zi-shu-zu-by-ch-c2hl/
     * @param target
     * @param nums
     * @return
     */
    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        var left = -1
        // 总数，用于比较目标值
        var sum = 0
        // 最小区间
        var res = Int.MAX_VALUE

        for (i in 0 until nums.size) {
            // 移动右指针，扩大窗口，直到子数组和 >= 目标值 target
            sum += nums[i]

            // 移动左指针，缩小窗口，直到子数组和 < 目标值 target
            while (sum >= target) {
                // 注意：必须满足其和 ≥ target，才会计算结果
                res = Math.min(res, i - left)

                sum -= nums[++left]
            }
        }

        return if (res == Int.MAX_VALUE) 0 else res
    }

    /**
     * 189. 旋转数组
     *
     * 时间复杂度：O(n)。每个元素被翻转两次，一共 n 个元素，因此总时间复杂度为 O(2n)=O(n)
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/rotate-array/solution/189-xuan-zhuan-shu-zu-by-chen-li-guan-mf2l/
     * @param nums
     * @param k
     */
    fun rotate(nums: IntArray, k: Int) {
        var k = k
        // 避免 K 可能会大于最大长度
        k = k % nums.size

        // 首先对整个数组实行翻转，这样子原数组中需要翻转的子数组，就会跑到数组最前面  [1,2,3,4,5,6,7] -> [7,6,5,4,3,2,1]
        reverse(nums, 0, nums.size - 1)
        // 从 k - 1 处分隔数组，左右两数组，各自进行翻转  [7,6,5, 4,3,2,1] -> [5,6,7, 1,2,3,4]
        reverse(nums, 0, k - 1)
        reverse(nums, k, nums.size - 1)
    }

    fun reverse(nums: IntArray, start: Int, end: Int) {
        var start = start
        var end = end

        while (start < end) {
            val temp = nums[start]
            nums[start] = nums[end]
            nums[end] = temp

            start++
            end--
        }
    }


    /**
     * 48. 旋转图像 == 01.07. 旋转矩阵
     *
     * 时间复杂度：O(n^2)。翻转 2 次每次都是操作 n^2 次。
     * 空间复杂度：O(1)。都是原地翻转。
     *
     * https://leetcode-cn.com/problems/rotate-image/solution/48-xuan-zhuan-tu-xiang-0107-xuan-zhuan-j-ewnw/
     * https://leetcode-cn.com/problems/rotate-matrix-lcci/solution/48-xuan-zhuan-tu-xiang-0107-xuan-zhuan-j-lhzk/
     *
     * @param matrix
     */
    fun rotateMatrix(matrix: Array<IntArray>) {
        val n = matrix.size
        // 先以对角线（左上-右下）为轴进行翻转 (0,0)   (0,1)(0,2) (1,0)(2,0)
        for (i in 0 until n - 1) {
            for (j in i + 1 until n) {
                val tmp = matrix[i][j]
                matrix[i][j] = matrix[j][i]
                matrix[j][i] = tmp
            }
        }

        // 再对每一行以中点进行翻转
        val mid = n / 2
        for (i in 0 until n) {
            for (j in 0 until mid) {
                val tmp = matrix[i][j]
                matrix[i][j] = matrix[i][n - 1 - j]
                matrix[i][n - 1 - j] = tmp
            }
        }
    }

//    [1,2,3]
//    [4,5,6]
//    [7,8,9]
//    先以对角线（1-5-9）为轴进行翻转
//    [1,4,7]
//    [2,5,8]
//    [3,6,9]
//    再对每一行以中点进行翻转
//    [7,4,1]
//    [8,5,2]
//    [9,6,3]


    /**
     * 169. 多数元素
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/majority-element/solution/169-duo-shu-yuan-su-by-chen-li-guan-xqnd/
     * @param nums
     * @return
     */
    fun majorityElement(nums: IntArray): Int {
        val map = mutableMapOf<Int, Int>()
        val half = nums.size / 2

        for (num in nums) {
            var n = 0
            if (map.containsKey(num)) {
                n = map[num]!!
            }
            map[num] = n + 1

            if (n + 1 > half) return num
        }
        return -1
    }


    /**
     * 349. 两个数组的交集
     *
     * 时间复杂度：O(m+n)。
     * 空间复杂度：O(m+n)。
     *
     * https://leetcode-cn.com/problems/intersection-of-two-arrays/solution/349-liang-ge-shu-zu-de-jiao-ji-by-chen-l-omcy/
     * @param nums1
     * @param nums2
     * @return
     */
    fun intersection(nums1: IntArray?, nums2: IntArray?): IntArray? {
        if (nums1 == null || nums1.isEmpty() || nums2 == null || nums2.isEmpty()) {
            return IntArray(0)
        }

        val set1 = mutableSetOf<Int>()
        val set2 = mutableSetOf<Int>()

        for (num in nums1) {
            set1.add(num)
        }

        // 遍历 nums2，如果元素在 set1 中存在，加入到 set2 中
        for (num in nums2) {
            if (set1.contains(num)) {
                set2.add(num)
            }
        }

        val res = IntArray(set2.size)
        var index = 0
        for (value in set2) {
            res[index++] = value
        }
        return res
    }


    /**
     * n->f(n)
     * 0->1
     * 1->3
     * 2->4
     * 3->2
     * 类似链表一样的序列：0->1->3->2->4->null
     *
     * n->f(n)
     * 0->1
     * 1->3
     * 2->4
     * 3->2
     * 4->2
     * 类似链表一样的序列：0->1->3->2->4->2->4->2->...，这里 2->4 是一个循环
     */

    /**
     * 287. 寻找重复数
     *
     * 时间复杂度：O(N)。
     * 空间复杂度：O(N)。
     *
     * https://leetcode-cn.com/problems/find-the-duplicate-number/solution/287-xun-zhao-zhong-fu-shu-by-chen-li-gua-ihi5/
     * @param nums
     * @return
     */
    fun findDuplicate(nums: IntArray): Int {
        var slow = 0
        var fast = 0

        while (true) {
            slow = nums[slow]
            fast = nums[nums[fast]]

            // 第一次相遇
            if (slow == fast) {
                break
            }
        }

        // slow 指针位置不变，将 fast 指针重新 指向链表头部节点
        fast = 0

        // slow和fast同时每轮向前走 1 步；
        while (true) {
            // 第二次相遇
            if (slow == fast) {
                break
            }

            slow = nums[slow]
            fast = nums[fast]
        }

        // 返回 slow
        return slow
    }


    /**
     * 剑指 Offer 29. 顺时针打印矩阵 == 54. 螺旋矩阵
     *
     * 时间复杂度 O(MN) ：M, N分别为矩阵行数和列数
     * 空间复杂度 O(MN)
     *
     * https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/solution/jian-zhi-offer-29-shun-shi-zhen-da-yin-j-z25q/
     * https://leetcode-cn.com/problems/spiral-matrix/solution/jian-zhi-offer-29-shun-shi-zhen-da-yin-j-92ui/
     *
     * @param matrix
     * @return
     */
    fun spiralOrder(matrix: Array<IntArray>): IntArray {
        if (matrix.isEmpty()) return IntArray(0)

        var l = 0
        var r = matrix[0].size - 1
        var t = 0
        var b = matrix.size - 1

        var x = 0
        val array = IntArray((r + 1) * (b + 1))

        while (true) {
            // 从左向右
            for (i in l..r) array[x++] = matrix[t][i]
            // 上边界 t 加 1，是否 t > b
            if (++t > b) break

            // 从上向下
            for (i in t..b) array[x++] = matrix[i][r]
            // 右边界 r 减 1，是否 l > r
            if (--r < l) break

            // 从右向左
            for (i in r downTo l) array[x++] = matrix[b][i]
            // 下边界 b 减 1，是否 t > b
            if (--b < t) break

            // 从下向上
            for (i in b downTo t) array[x++] = matrix[i][l]
            // 左边界 l 加 1，是否 l > r
            if (++l > r) break
        }
        return array
    }

    /**
     * 59. 螺旋矩阵 II
     *
     * 时间复杂度 O(MN) ：M, N分别为矩阵行数和列数
     * 空间复杂度 O(MN)
     *
     * https://leetcode-cn.com/problems/spiral-matrix-ii/solution/59-luo-xuan-ju-zhen-ii-by-chen-li-guan-mttp/
     * @param n
     * @return
     */
    fun generateMatrix(n: Int): Array<IntArray>? {
        var l = 0
        var r = n - 1
        var t = 0
        var b = n - 1

        val array = Array(n) { IntArray(n) }
        var num = 1
        val tar = n * n

        // 注意：<=，包含 =
        while (num <= tar) {
            // left to right.
            for (i in l..r) array[t][i] = num++
            ++t

            // top to bottom.
            for (i in t..b) array[i][r] = num++
            --r

            // right to left.
            for (i in r downTo l) array[b][i] = num++
            --b

            // bottom to top.
            for (i in b downTo t) array[i][l] = num++
            ++l
        }
        return array
    }

    /**
     * 560. 和为K的子数组  方法一：构建前缀和数组，以快速计算区间和
     *
     * 时间复杂度：O(N^2)，这里 N 是数组的长度；
     * 空间复杂度：O(N)。
     *
     * https://leetcode-cn.com/problems/subarray-sum-equals-k/solution/560-he-wei-kde-zi-shu-zu-by-chen-li-guan-6y6i/
     *
     * @param nums
     * @param k
     * @return
     */
    fun subarraySum(nums: IntArray, k: Int): Int {
        // 计算前缀和数组
        val preSum = IntArray(nums.size + 1)
        preSum[0] = 0
        for (i in 0 until nums.size) {
            preSum[i + 1] = preSum[i] + nums[i]
        }

        var count = 0
        for (i in 0 until nums.size) {
            for (j in i until nums.size) {
                // 区间和 [i..j]/[left..right]，注意下标偏移
                if (preSum[j + 1] - preSum[i] == k) {
                    count++
                }
            }
        }
        return count
    }


    /**
     * 31. 下一个排列     [1,3,5,4,1]，k=3，
     *
     * 时间复杂度：对数组线性遍历。复杂度为 O(n)。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/next-permutation/solution/31-xia-yi-ge-pai-lie-by-chen-li-guan-gq77/
     * @param nums
     */
    fun nextPermutation(nums: IntArray) {
        var k = nums.size - 1

        // 1.从后往前找，找到第一个下降的位置，计为 k - 1。eg：nums[k - 1]=3
        while (k - 1 >= 0 && nums[k - 1] >= nums[k]) {
            k--
        }

        if (k == 0) {
            // 如果找不到直接翻转数组。eg:[3,2,1]->[1,2,3]
            reverses(nums, 0, nums.size - 1)
        } else {
            // 2.从 k - 1 往后找到最小的比 k - 1 大的数，计为 t。eg：nums[t - 1]=4
            var t = k - 1
            while (t + 1 <= nums.size - 1 && nums[t + 1] > nums[k - 1]) {
                t++
            }

            // 3.将两者交换。注意此时 k 以后的位置仍然是降序的
            swap(nums, k - 1, t)

            // 4.直接将 k 以后的部分翻转，变为升序（保证下一个排列增加幅度最小）
            reverses(nums, k, nums.size - 1)
        }
    }

    fun reverses(nums: IntArray, l: Int, r: Int) {
        var l = l
        var r = r

        while (l < r) {
            swap(nums, l, r)
            l++
            r--
        }
    }

    fun swap(nums: IntArray, l: Int, r: Int) {
        val temp = nums[l]
        nums[l] = nums[r]
        nums[r] = temp
    }


    /**
     * 556. 下一个更大元素 III     n = 12，res = 21
     *
     * 时间复杂度：对数组线性遍历。复杂度为 O(n)。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/next-greater-element-iii/solution/556-xia-yi-ge-geng-da-yuan-su-iii-by-che-i47u/
     * @param nums
     */
    fun nextGreaterElement3(n: Int): Int {
        val nums = n.toString().toCharArray()

        // 下一个排列
        nextPermutation(nums)

        var res = 0
        for (i in 0 until nums.size) {
            res = res * 10 + (nums[i] - '0') % 10
        }
        return if (res > n) res else -1
    }

    /**
     * 31. 下一个排列
     * https://leetcode-cn.com/problems/next-permutation/solution/31-xia-yi-ge-pai-lie-by-chen-li-guan-gq77/
     * @param nums
     */
    fun nextPermutation(nums: CharArray) {
        var k = nums.size - 1

        // 1.从后往前找，找到第一个下降的位置，计为 k - 1。eg：nums[k - 1]=3
        while (k - 1 >= 0 && nums[k - 1] >= nums[k]) {
            k--
        }

        if (k == 0) {
            // 如果找不到直接翻转数组。eg:[3,2,1]->[1,2,3]
            reverses(nums, 0, nums.size - 1)
        } else {
            // 2.从 k - 1 往后找到最小的比 k - 1 大的数，计为 t。eg：nums[t - 1]=4
            var t = k - 1
            while (t + 1 <= nums.size - 1 && nums[t + 1] > nums[k - 1]) {
                t++
            }

            // 3.将两者交换。注意此时 k 以后的位置仍然是降序的
            swap(nums, k - 1, t)

            // 4.直接将 k 以后的部分翻转，变为升序（保证下一个排列增加幅度最小）
            reverses(nums, k, nums.size - 1)
        }
    }

    fun reverses(nums: CharArray, l: Int, r: Int) {
        var l = l
        var r = r

        while (l < r) {
            swap(nums, l, r)
            l++
            r--
        }
    }

    fun swap(nums: CharArray, l: Int, r: Int) {
        val temp = nums[l]
        nums[l] = nums[r]
        nums[r] = temp
    }


    /**
     * 118. 杨辉三角
     * 题意：每个数是它左上方和右上方的数的和
     *
     * 时间复杂度：O(numRows^2)。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/pascals-triangle/solution/118-yang-hui-san-jiao-by-chen-li-guan-z1tc/
     *
     * @param numRows
     * @return
     */
    fun generate(numRows: Int): List<List<Int>>? {
        val res = arrayListOf<List<Int>>()
        for (i in 0 until numRows) {
            val row = arrayListOf<Int>()
            // 注意：0-i
            for (j in 0 until i + 1) {
                if (j == 0 || j == i) {
                    row.add(1)
                } else {
                    // 每个数是它 左上方 和 正上方 的数的 和
                    row.add(res[i - 1][j - 1] + res[i - 1][j])
                }
            }
            res.add(row)
        }
        return res
    }


    /**
     * 470. 用 Rand7() 实现 Rand10()
     *
     * 时间复杂度：O(1)；
     * 空间复杂度：O(1)；

     * https://leetcode-cn.com/problems/implement-rand10-using-rand7/solution/263-chou-shu-by-chen-li-guan-9uky/
     *
     * @return
     */
    fun rand10(): Int {
        // 首先得到一个数
        var num = (rand7() - 1) * 7 + rand7()
        // 只要它还大于40，那你就给我不断生成吧
        while (num > 40) {
            num = (rand7() - 1) * 7 + rand7()
        }
        // 返回结果，+1是为了解决 40%10为0的情况
        return 1 + num % 10
    }

    fun rand7(): Int {
        return 1
    }


    /**
     * 263. 丑数
     * 题意：丑数 就是只包含质因数 2、3 和/或 5 的正整数。
     *
     * 时间复杂度：当 n 是以 2 为底的对数时，需要除以 logn 次。复杂度为 O(logn)；
     * 空间复杂度：O(1)；
     *
     * https://leetcode-cn.com/problems/ugly-number/solution/263-chou-shu-by-chen-li-guan-cqha/
     *
     * @param n
     * @return
     */
    fun isUgly(n: Int): Boolean {
        var n = n
        if (n <= 0) return false
        while (n % 2 == 0) n /= 2
        while (n % 3 == 0) n /= 3
        while (n % 5 == 0) n /= 5
        return n == 1
    }

    /**
     * 264. 丑数 II   方法一：优先队列（小根堆）解法
     * 题意：给你一个整数 n ，请你找出并返回第 n 个 丑数。n = 7，[1, 2, 3, 4, 5, 6, 8]，输出：8
     *
     * 时间复杂度：从优先队列中取最小值为 O(1)，往优先队列中添加元素复杂度为 O(logn)。整体复杂度为 O(nlogn)。
     * 空间复杂度：O(n)。
     *
     * https://leetcode-cn.com/problems/ugly-number-ii/solution/264-chou-shu-ii-by-chen-li-guan-8l4f/
     *
     * @param n
     * @return
     */
    fun nthUglyNumber(n: Int): Int {
        val nums = intArrayOf(2, 3, 5)

        val set = hashSetOf<Long>()
        // 小顶堆
        val pq = PriorityQueue<Long>()

        // 先将最小丑数 1 放入队列
        set.add(1L)
        pq.add(1L)

        // 循环多次，第 n 次出队的值即是答案
        for (i in 1 until n + 1) {
            // 每次从队列取出最小值 x
            val x = pq.poll()
            if (i == n) {
                return x.toInt()
            }

            // 然后将 x 所对应的丑数 2x、3x 和 5x 进行入队
            for (num in nums) {
                val t = num * x
                if (!set.contains(t)) {
                    set.add(t)
                    pq.add(t)
                }
            }
        }
        return -1
    }


    /**
     * 179. 最大数
     *
     * 字符串比较：a.compareTo(b) 比较的时候是按照ASCII码逐位比较的，如果大于0，返回值为正数，就会交换a和b
     * 如果a的第一个字符和b的第一个字符不等，结束比较，返回他们之间的长度差值；
     * 如果a的第一个字符和b的第一个字符相等，则a的第二个字符和b的第二个字符做比较；
     *
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(n)。
     *
     * https://leetcode-cn.com/problems/largest-number/solution/179-zui-da-shu-by-chen-li-guan-nwk6/
     *
     * @param nums
     * @return
     */
    fun largestNumber(nums: IntArray): String? {
        val numToWords = arrayOfNulls<String>(nums.size)
        for (i in 0 until nums.size) {
            numToWords[i] = nums[i].toString()
        }

        // 通过比较 (a+b) 和 (b+a) 的大小，就可以判断出a,b两个字符串，正序逆序相加的值大的在前面
        // 所以 [3,30,34] 排序后变为 [34,3,30]。eg：a="30",b="3" -> b+a="330",a+b="303",(b+a).compareTo(a+b)=3>1, 不交换 3,30 --> 3,30
        Arrays.sort(numToWords) { a, b -> (b + a).compareTo(a + b) }
        // 如果排序后的第一个元素是0，那后面的元素肯定小于或等于0，则可直接返回0
        if (numToWords[0] == "0") {
            return "0"
        }

        val sb = StringBuilder()
        for (i in 0 until nums.size) {
            sb.append(numToWords[i])
        }
        return sb.toString()
    }


    /**
     * 38. 外观数列
     * 1.     1       第一项是数字 1
     * 2.     11      描述前一项，这个数是 1 即 “一 个 1 ”，记作 "11"
     * 3.     21      描述前一项，这个数是 11 即 “二 个 1 ” ，记作 "21"
     * 4.     1211    描述前一项，这个数是 21 即 “一 个 2 + 一 个 1 ” ，记作 "1211"
     *
     * 时间复杂度：循环 n 次，复杂度为 O(n)；每次循环处理一遍当前的 s 字符串，复杂度为 O(n) 。整体复杂度为 O(n^2)。
     * 空间复杂度：O(n)。
     *
     * https://leetcode-cn.com/problems/count-and-say/solution/38-wai-guan-shu-lie-by-chen-li-guan-cia2/
     *
     * @param n
     * @return
     */
    fun countAndSay(n: Int): String? {
        var s = "1"
        for (i in 1 until n) {
            // 只需要统计字符串 s 中每一段连续出现的字符的类型和出现次数即可
            s = nextString(s)
        }
        return s
    }

    fun nextString(s: String): String {
        // 往结尾添加一个"哨兵"，使用「哨兵技巧」来简化我们统计的逻辑，避免在循环外统计最后一次字符
        val s = s + "a"
        val sb = StringBuilder()

        var first = s[0]
        var count = 1
        for (i in 1 until s.length) {
            if (s[i] == first) {
                count++
            } else {
                sb.append(count)
                sb.append(first)

                first = s[i]
                count = 1
            }
        }
        return sb.toString()
    }


    /**
     * 128. 最长连续序列
     *
     * 时间复杂度：O(n)；
     * 空间复杂度：O(n)；
     *
     * https://leetcode-cn.com/problems/longest-consecutive-sequence/solution/128-zui-chang-lian-xu-xu-lie-by-chen-li-jxkyk/
     *
     * @param nums
     * @return
     */
    fun longestConsecutive(nums: IntArray): Int {
        if (nums.isEmpty()) return 0

        var res = 1

        // 找到 nums 中有哪些元素能够当做连续序列的左边界
        val set = hashSetOf<Int>()
        for (num in nums) {
            set.add(num)
        }

        for (num in nums) {
            // 若一个元素值 num 满足：num - 1 不在 set 中，则该元素值 num 就可以当做连续序列的左边界。否则就不是连续序列了。
            if (set.contains(num - 1)) continue

            // r: 表示「以 num 当做左边界，则继续找 num + 1，num + 2... 是否存在于 set 集合中」
            var r = num
            // 逐个查看
            while (set.contains(r + 1)) {
                r++
            }

            // 记录最大的连续序列的长度
            res = Math.max(res, r - num + 1)
        }
        return res
    }


    /**
     * 406. 根据身高重建队列
     * 思想：高个子先站好位，矮个子插入到K位置上，前面肯定有K个高个子，矮个子再插到前面，也满足K的要求
     *
     * [7,0], [4,4], [7,1], [5,0], [6,1], [5,2]  --> 排序前
     * [7,0], [7,1], [6,1], [5,0], [5,2], [4,4]  --> 排序后
     * 再一个一个插入。
     * [7,0]                                      // 将 [7, 0] 插入到 i = 0 处
     * [7,0], [7,1]                               // 将 [7, 1] 插入到 i = 1 处
     * [7,0], [6,1], [7,1]                        // 将 [6, 1] 插入到 i = 1 处，如果原来位置有数据了，会往后移（关键）
     * [5,0], [7,0], [6,1], [7,1]                 // 将 [5, 0] 插入到 i = 0 处
     * [5,0], [7,0], [5,2], [6,1], [7,1]          // 将 [5, 2] 插入到 i = 2 处
     * [5,0], [7,0], [5,2], [6,1], [4,4], [7,1]   // 将 [4, 4] 插入到 i = 4 处
     *
     * 时间复杂度：O(nlogn)，其中 n 是数组 people 的长度。我们需要 O(nlogn) 的时间进行排序，随后需要 O(n^2) 的时间遍历每一个人并将他们放入队列中???
     * 空间复杂度：O(logn)，即为排序需要使用的栈空间。
     *
     * https://leetcode-cn.com/problems/queue-reconstruction-by-height/solution/406-gen-ju-shen-gao-zhong-jian-dui-lie-b-99hr/
     *
     * @param array
     * @return
     */
    fun reconstructQueue(array: Array<IntArray>): Array<IntArray>? {
        val res = LinkedList<IntArray>()

        // 1.排序规则：先按身高降序，若身高相同则按第二个数字升序
        Arrays.sort(array) { o1, o2 ->
            if (o1[0] != o2[0]) {
                // 按身高降序
                o2[0] - o1[0]
            } else {
                // 按第二个数字升序
                o1[1] - o2[1]
            }
        }

        // 2.遍历排序后的数组，第二个数字作为索引位置，把数组插入到目标索引位置上，如果原来位置有数据了，会往后移。
        for (ar in array) {
            res.add(ar[1], ar)
        }

        return res.toArray(Array(res.size) { IntArray(2) })
    }


    /**
     * 207. 课程表  方法一：拓扑排序（Kahn 算法，其实就是广度优先遍历的思路）
     * 题意：numCourses = 2, prerequisites = [[1,0]]。总共有 2 门课程，学习课程 1 之前，你需要完成课程 0。
     *
     * 拓扑排序：每一次都从图中删除没有前驱的顶点，这里并不需要真正的做删除操作，可以设置一个入度数组，每一轮都输出入度为 0 的结点，
     *          并移除它、修改它指向的结点的入度（-1即可），依次得到的结点序列就是拓扑排序的结点序列。如果图中还有结点没有被移除，则说明“不能完成所有课程的学习”。
     * 邻接表：通过结点的索引，我们能够得到这个结点的后继结点；
     * 入度数组：通过结点的索引，我们能够得到指向这个结点的结点个数。
     *
     * 时间复杂度：O(E + V)。这里 E 表示邻边的条数，V 表示结点的个数。
     * 空间复杂度：O(E + V)。邻接表长度是 V，每个课程里又保存了它所有的边。
     *
     * https://leetcode-cn.com/problems/course-schedule/solution/207-ke-cheng-biao-by-chen-li-guan-ixkm/
     *
     * @param courses
     * @param preres
     * @return
     */
    fun canFinish(courses: Int, preres: Array<IntArray>): Boolean {
        if (courses <= 0) return false
        if (preres.isEmpty()) return true

        // 开始排序前，扫描对应的存储空间（使用邻接表），将入度为 0 的结点放入队列
        val inDegree = IntArray(courses)
        val adj = arrayOfNulls<HashSet<Int>>(courses)
        for (i in 0 until courses) {
            adj[i] = hashSetOf()
        }
        for (p in preres) {
            inDegree[p[0]]++
            adj[p[1]]?.add(p[0])
        }

        val queue = LinkedList<Int>()
        // 首先加入入度为 0 的结点
        for (i in 0 until courses) {
            if (inDegree[i] == 0) {
                queue.add(i)
            }
        }

        // 记录已经出队的课程数量：只要队列非空，就从队首取出入度为 0 的结点，将这个结点输出到结果集中
        var cnt = 0
        while (queue.isNotEmpty()) {
            val top = queue.poll()
            cnt += 1
            // 遍历当前出队结点的所有后继结点
            for (successor in adj[top]!!) {
                // 这个结点的所有邻接结点（它指向的结点）的入度减 1，在减 1 以后，如果这个被减 1 的结点的入度为 0，就继续入队
                inDegree[successor]--
                if (inDegree[successor] == 0) {
                    queue.add(successor)
                }
            }
        }
        // 当队列为空的时候，检查结果集中的顶点个数是否和课程数相等即可
        return cnt == courses
    }
}


