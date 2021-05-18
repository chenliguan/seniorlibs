package com.seniorlibs.algorithm.array

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
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
        findViewById<View>(R.id.btn_min_sub_arrayLen).setOnClickListener(this)
        findViewById<View>(R.id.btn_rotate).setOnClickListener(this)
        findViewById<View>(R.id.btn_majority_element).setOnClickListener(this)
        findViewById<View>(R.id.btn_intersection).setOnClickListener(this)
        findViewById<View>(R.id.btn_find_duplicate).setOnClickListener(this)
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
            val nums = arrayOf(intArrayOf(1, 4),
                    intArrayOf(2, 5),
                    intArrayOf(8, 16),
                    intArrayOf(17, 24),
                    intArrayOf(26, 30))
            LogUtils.d(TAG, "56. 合并区间：${mergeInterval(nums)}")
        }
        R.id.btn_min_sub_arrayLen -> {
            val nums = intArrayOf(2, 3, 1, 2, 4, 3)
            LogUtils.d(TAG, "209. 长度最小的子数组：${minSubArrayLen(7, nums)}")
        }
        R.id.btn_rotate -> {
            val nums = intArrayOf(1, 2, 3, 4, 5, 6)
            LogUtils.d(TAG, "189. 旋转数组：${rotate(nums, 3)}")
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

        val n: Int = height.size
        var left = 0
        var right = n - 1
        var res = 0

        // l_max是height[0..left]中最高柱子的高度，r_max是height[right..n-1]的最高柱子的高度
        var l_max = height[0]
        var r_max = height[n - 1]

        while (left <= right) {
            l_max = Math.max(l_max, height[left])
            r_max = Math.max(r_max, height[right])

            // res += min(l_max, r_max) - height[i]
            if (l_max < r_max) {
                // height[left] 只和 l_max 有关
                res += l_max - height[left]
                left++
            } else {
                res += r_max - height[right]
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
                return intArrayOf(i, map.getValue(other))
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
        var p1 = m
        var p2 = n
        var k = p1 + p2 - 1

        while (p1 > 0 && p2 > 0) {
            if (nums1[p1 - 1] > nums2[p2 - 1]) {
                nums1[k] = nums1[p1 - 1]
                p1--
            } else {
                nums1[k] = nums2[p2 - 1]
                p2--
            }
            k--
        }

        // 合并剩余的元素
        for (i in 0 until p1) {
            nums1[i] = nums1[i]
        }

        // 当 p2 大于 0 并且 p1 小于 0 时，此时 nums1 数组所有元素已排列过了，而nums2中还剩下p2个元素，需要对nums1的前p2个赋值为nums2的前p2个（直接将前n个进行覆盖）
        for (i in 0 until p2) {
            nums1[i] = nums2[i]
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
     * 空间复杂度：O(nlogn)，其中 n 为区间的数量。这里计算的是存储答案之外，使用的额外空间。(nlogn) 即为排序所需要的空间复杂度。
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
        var idx = -1
        for (interval in intervals) {
            // 如果结果数组是空的，或者 当前区间的起始位置 > 结果数组中最后区间的终止位置，则不合并，直接将当前区间加入结果数组
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval
            } else {
                // 反之将当前区间合并至结果数组的 最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1])
            }
        }

        // 复制指定的数组，以空值截断或填充：idx = 2，因此 +1 [[1,6],[8,10],[15,18],[0,0]] -> [[1,6],[8,10],[15,18]
        return Arrays.copyOf(res, idx + 1)
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
        var left = 0
        var right = 0
        // 总数，用于比较目标值
        var sum = 0
        // 最小区间
        var min = Int.MAX_VALUE

        while (right < nums.size) {
            // 移动右指针，扩大窗口，直到子数组和 >= 目标值 target
            sum += nums[right]
            right++

            // 移动左指针，缩小窗口，直到子数组和 < 目标值 target
            while (sum >= target) {
                min = Math.min(min, right - left)
                sum -= nums[left]
                left++
            }
        }

        return if (min == Int.MAX_VALUE) 0 else min
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
        // 从 k 处分隔数组，左右两数组，各自进行翻转  [7,6,5, 4,3,2,1] -> [5,6,7, 1,2,3,4]
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
                if (n + 1 > half) {
                    return num
                }
            }

            map[num] = n + 1
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


/*
n->f(n)
0->1
1->3
2->4
3->2
类似链表一样的序列：0->1->3->2->4->null

n->f(n)
0->1
1->3
2->4
3->2
4->2
类似链表一样的序列：0->1->3->2->4->2->4->2->... ，这里 2->4 是一个循环
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
        var fast = 0
        var slow = 0

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
            slow = nums[slow]
            fast = nums[fast]

            // 第一次相遇
            if (slow == fast) {
                break
            }
        }

        // 双指针第二次相遇
        return slow
    }
}
