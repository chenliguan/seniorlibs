package com.seniorlibs.algorithm.binarysearch

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
 * Description: 二分查找
 *
 * 核心的思想就一个：逐渐缩小问题规模
 */
class BinarySearchActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "BinarySearchActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, BinarySearchActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binary_search)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_search1).setOnClickListener(this)
        findViewById<View>(R.id.btn_search_insert).setOnClickListener(this)
        findViewById<View>(R.id.btn_search_range).setOnClickListener(this)
        findViewById<View>(R.id.btn_search_times).setOnClickListener(this)
        findViewById<View>(R.id.btn_search).setOnClickListener(this)
        findViewById<View>(R.id.btn_search_2).setOnClickListener(this)
        findViewById<View>(R.id.btn_find_min).setOnClickListener(this)
        findViewById<View>(R.id.btn_find_min_2).setOnClickListener(this)
        findViewById<View>(R.id.btn_sqrt).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_search1 -> {
                LogUtils.d(TAG, "704. 二分查找：${search1(intArrayOf(1, 3, 5, 5, 7), 5)}")
            }
            R.id.btn_search_insert -> {
                LogUtils.d(TAG, "35. 搜索插入位置：${searchInsert(intArrayOf(1, 3, 5, 5, 7), 4)}")
            }
            R.id.btn_search_range -> {
                LogUtils.d(TAG, "34. 在排序数组中查找元素的第一个和最后一个位置：${searchRange(intArrayOf(1, 3, 5, 5, 7), 5)}")
            }
            R.id.btn_search_times -> {
                LogUtils.d(TAG, "剑指 Offer 53 - I. 在排序数组中查找数字 I：${searchTimes(intArrayOf(1, 3, 5, 5, 7), 5)}")
            }
            R.id.btn_search -> {
                LogUtils.d(TAG, "33. 搜索旋转排序数组：${search(intArrayOf(5, 6, 7, 0, 1, 2), 1)}")
            }
            R.id.btn_search_2 -> {
                LogUtils.d(TAG, "81. 搜索旋转排序数组 II：${search2(intArrayOf(5, 6, 7, 0, 0, 1, 2), 1)}")
            }
            R.id.btn_find_min -> {
                LogUtils.d(TAG, "153. 寻找旋转排序数组中的最小值：${findMin(intArrayOf(5, 6, 7, 0, 1, 2))}")
            }
            R.id.btn_find_min_2 -> {
                LogUtils.d(TAG, "154. 寻找旋转排序数组中的最小值 II：${findMin2(intArrayOf(5, 6, 7, 0, 0, 1, 2))}")
            }
            R.id.btn_sqrt -> {
                LogUtils.d(TAG, "69. target 的平方根：${mySqrt(8)}")
                LogUtils.d(TAG, "69. target 的平方根：${mySqrt1(8)}")
            }
            else -> {
            }
        }
    }

    /**
     * 写对二分查找不能靠模板，需要理解加练习 （附练习题，持续更新）
     *
     * https://leetcode-cn.com/problems/search-insert-position/solution/te-bie-hao-yong-de-er-fen-cha-fa-fa-mo-ban-python-/
     */


    /**
     * 寻找数组中给定元素的下界
     *
     * @param nums
     * @param target
     * @return
     */
    fun lowerBound(nums: IntArray, target: Int): Int {
        var l = 0
        var r = nums.size
        while (l < r) {
            val m = l + (r - l) / 2
            if (nums[m] < target) {
                l = m + 1
            } else {
                r = m
            }
        }

        // 由于执行到最后 nums[l..r] 里一定存在插入元素的位置，并且退出循环的时候一定有 l == r 成立，因此直接返回 l 或者 r 均可。
        return l
    }

    /**
     * 寻找数组中给定元素的上界
     *
     * @param nums
     * @param target
     * @return
     */
    fun upperBound(nums: IntArray, target: Int): Int {
        var l = 0
        var r = nums.size
        while (l < r) {
            val m = l + (r - l) / 2
            if (nums[m] <= target) {
                l = m + 1
            } else {
                r = m
            }
        }

        // 由于执行到最后 nums[l..r] 里一定存在插入元素的位置，并且退出循环的时候一定有 l == r 成立，因此直接返回 l 或者 r 均可。
        return l
    }


    /**
     * 704. 二分查找
     *
     * 时间复杂度：O(logn)。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/binary-search/solution/704-er-fen-cha-zhao-by-chen-li-guan-wbhk/
     * @param nums
     * @param target
     * @return
     */
    fun search1(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) return 0

        val l = lowerBound(nums, target)

        // 注意：退出循环的时候，我们不能确定 nums[l] 是否等于 target，因此还需要单独做一次判断；
        return if (l < nums.size && nums[l] == target) l else -1
    }

    /**
     * 35. 搜索插入位置
     * 思路：找出第一个大于等于 target 的元素的下标，那么小于 target 的元素就一定不是我们要找的
     *
     * 时间复杂度：O(logn)。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/search-insert-position/solution/35-sou-suo-cha-ru-wei-zhi-by-chen-li-gua-70lp/
     * @param nums
     * @param target
     * @return
     */
    fun searchInsert(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) return 0

        return lowerBound(nums, target)
    }


    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     *
     * 时间复杂度：O(logn)。
     * 空间复杂度：O(n)。
     *
     * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/34-zai-pai-xu-shu-zu-zhong-cha-zhao-yuan-5zut/
     * @param nums
     * @param target
     * @return
     */
    fun searchRange(nums: IntArray, target: Int): IntArray? {
        val res = intArrayOf(-1, -1)
        if (nums.isEmpty()) return res

        val l = lowerBound(nums, target)
        val r = upperBound(nums, target)
        if (l == r) return res

        res[0] = l
        res[1] = r - 1
        return res
    }

    /**
     * 剑指 Offer 53 - I. 在排序数组中查找数字 I
     *
     * 时间复杂度：O(logn)。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/solution/jian-zhi-offer-53-i-zai-pai-xu-shu-zu-zh-9b5r/
     *
     * @param nums
     * @param target
     * @return
     */
    fun searchTimes(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) return 0

        val l = lowerBound(nums, target)
        val r = upperBound(nums, target)
        if (l == r) return 0

        return r - l
    }


    /**
     * 33. 搜索旋转排序数组  5,6,7,0,1,2  0
     *
     * 时间复杂度：O(logn)，其中n为nums数组的大小。整个算法时间复杂度即为二分搜索的时间复杂度O(logn)。
     * 空间复杂度：O(1)。只需要常数级别的空间存放变量。
     *
     * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/33-sou-suo-xuan-zhuan-pai-xu-shu-zu-by-chen-li-gua/
     * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/yi-wen-dai-ni-shua-bian-er-fen-cha-zhao-dtadq/
     *
     * @param nums
     * @param target
     * @return
     */
    fun search(nums: IntArray, target: Int): Int {
        var l = 0
        var r = nums.size

        while (l < r) {
            val m = l + (r - l) / 2
            
            // 先处理掉能够取到的3个值。
            if (nums[l] == target) return l
            if (nums[m] == target) return m
            // 这里不能用A[r]，因为我们使用的是开闭原则，右边始终是不能取到的。
            if (nums[r - 1] == target) return r - 1

            // 这里开始把不要的区间切掉
            if (nums[m] > nums[r - 1]) {
                if (nums[l] < target && target < nums[m]) {
                    // case (a)
                    // 到这里，nums[m]已经不可能等于x，所以需要将[m ... r)这段区间一起扔掉，留下[l, m)这段区间，续断查找
                    r = m
                } else {
                    // case (b)
                    // nums[m]已经不可能等于x，所以这里将[l ... m]这个区间切掉，留下[m + 1, r)
                    l = m + 1
                }

            } else {
                if (nums[m] < target && target < nums[r - 1]) {
                    // case (c)
                    // 到这里，左边的区间[l ... m]已经不需要了，只需要留下[m + 1, r)
                    l = m + 1
                } else {
                    // case (d)
                    // 到这里，右边的区间[m ... r)，已经不需要了，只需要留下区间[l, m)
                    r = m
                }
            }
        }
        return -1
    }


    /**
     * 81. 搜索旋转排序数组 II  5,6,7,0,1,1,2  1
     *
     * 时间复杂度：O(logn)，其中n为nums数组的大小。整个算法时间复杂度即为二分搜索的时间复杂度O(logn)。
     * 空间复杂度：O(1)。只需要常数级别的空间存放变量。
     *
     * https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/solution/81-sou-suo-xuan-zhuan-pai-xu-shu-zu-ii-b-uh9j/
     *
     * @param nums
     * @param target
     * @return
     */
    fun search2(nums: IntArray, target: Int): Boolean {
        var l = 0
        var r = nums.size
        while (l < r) {
            val m = l + (r - l) / 2

            // 先处理掉能够取到的3个值。这里不能用nums[r]，因为我们使用的是 开闭原则，右边始终是不能取到的。
            if (nums[m] == target || nums[l] == target || nums[r - 1] == target) {
                return true
            }
            // 区别：找到重复的下标都在 l，右移 l
            if (nums[l] == nums[m]) {
                l++
                continue
            }

            // 这里开始把不要的区间切掉
            if (nums[m] > nums[r - 1]) {
                if (nums[l] < target && target < nums[m]) {
                    // case (a)
                    // 到这里，nums[m]已经不可能等于x，所以需要将[m ... r)这段区间一起扔掉，留下[l, m)这段区间，续断查找
                    r = m
                } else {
                    // case (b)
                    // nums[m]已经不可能等于x，所以这里将[l ... m]这个区间切掉，留下[m + 1, r)
                    l = m + 1
                }

            } else {
                if (nums[m] < target && target < nums[r - 1]) {
                    // case (c)
                    // 到这里，左边的区间[l ... m]已经不需要了，只需要留下[m + 1, r)
                    l = m + 1
                } else {
                    // case (d)
                    // 到这里，右边的区间[m ... r)，已经不需要了，只需要留下区间[l, m)
                    r = m
                }
            }
        }
        return false
    }
    
    
    /**
     * 153. 寻找旋转排序数组中的最小值     5, 6, 7, 0, 1, 2
     *
     * 注意：区别前面的题目，r = nums.size - 1
     *
     * 时间复杂度：时间复杂度为 O(logn)。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/solution/153-xun-zhao-xuan-zhuan-pai-xu-shu-zu-zh-6hsd/
     * @param nums
     * @return
     */
    fun findMin(nums: IntArray): Int {
        // 由于要使用A[r-1]，这里直接用了闭区间[l, r]
        var l = 0
        var r = nums.size - 1

        // 保证区间中至少有一个元素
        while (l < r) {
            val m = l + (r - l) / 2

            // 当nums[m] > nums[r]时，nums[m]掉在左边的区间，此时[l, m]可以不要了，保留区间[m + 1, r]
            if (nums[m] > nums[r]) {
                l = m + 1
            } else {
                // 如果nums[m] < nums[r]，表示nums[m]掉在了右边的区间，那么此时[m + 1, r]可以不要了，保留区间[l, m]
                r = m
            }
        }
        return nums[l]
    }


    /**
     * 154. 寻找旋转排序数组中的最小值 II == 剑指 Offer 11. 旋转数组的最小数字    5, 6, 7, 0, 0, 1, 2
     * 区别 153：可能存在 重复 元素值的数组 nums
     *
     * 时间复杂度：O(logn)。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/solution/154-xun-zhao-xuan-zhuan-pai-xu-shu-zu-zh-xshl/
     * https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/solution/154-xun-zhao-xuan-zhuan-pai-xu-shu-zu-zh-rzhu/
     *
     * @param nums
     * @return
     */
    fun findMin2(nums: IntArray): Int {
        // 由于要使用A[r-1]，这里直接用了闭区间[l, r]
        var l = 0
        var r = nums.size - 1

        // 保证区间中至少有一个元素
        while (l < r) {
            val m = l + (r - l) / 2

            // 当nums[m] > nums[r]时，nums[m]掉在左边的区间，此时[l, m]可以不要了，保留区间[m + 1, r]
            if (nums[m] > nums[r]) {
                l = m + 1
            } else if (nums[m] < nums[r]) {
                // 如果nums[m] < nums[r]，表示nums[m]掉在了右边的区间，那么此时[m + 1, r]可以不要了，保留区间[l, m]
                r = m
            } else {
                // 区别：如果m与右边元素相等，由于重复元素的存在，并不能确定 nums[m] 究竟在最小值的左侧还是右侧，那么扔掉nums[r]
                r--
            }
        }
        return nums[l]
    }



    /**
     * 162. 寻找峰值   [1,2,1,3,5,6,4]  位置5->6
     * 思路：出现了 nums[-1] = nums[n] = -∞，这就代表着 （只要数组中存在一个元素比相邻元素大，那么沿着它一定可以找到一个峰值）。因此，使用二分查找找到峰值。
     *
     * 时间复杂度：O(logN)。
     * 空间复杂度：O(1)。只需要常数级别的空间存放变量。
     *
     * https://leetcode-cn.com/problems/find-peak-element/solution/162-xun-zhao-feng-zhi-by-chen-li-guan-1ooh/
     * @param array
     * @return
     */
    fun findPeakElement(array: IntArray): Int {
        var left = 0
        var right = array.size - 1

        // 查找时，左指针 l，右指针 r，以其保持左右顺序为循环条件
        while (left < right) {
            // 根据左右指针计算中间位置 mid，并比较 mid 与 mid+1 的值
            val mid = left + (right - left) / 2
            if (array[mid] > array[mid + 1]) {
                // 如果 mid 较大，则左侧存在峰值，r = mid
                right = mid
            } else {
                // 如果 mid + 1 较大，则右侧存在峰值，l = mid + 1
                left = mid + 1
            }
        }
        return left
    }

    /**
     * 方法二：线性扫描
     *
     * 时间复杂度 : O(n)。对长度为 n 的数组 nums 只进行一次遍历。
     * 空间复杂度 : O(1)。只使用了常数空间。
     *
     * @param nums
     * @return
     */
    fun findPeakElement1(nums: IntArray): Int {
        for (i in 0 until nums.size - 1) {
            if (nums[i] > nums[i + 1]) {
                return i
            }
        }
        return nums.size - 1
    }

    /**
     * 69. target 的平方根。方法一：二分查找
     *
     * 思想：一个数的平方根肯定不会超过它自己，最小是0，范围最大是自己。例如 ：8的平方根，8的一半是4，4^2=16>8，
     *      意即：如果一个数的一半的平方大于它自己，那么这个数的取值范围在小于4的一半：0-3
     *
     * 时间复杂度：O(logX)，二分法的时间复杂度是对数级别的。
     * 空间复杂度：O(1)，使用了常数个数的辅助空间用于存储和比较。
     *
     * https://leetcode-cn.com/problems/sqrtx/solution/69-target-de-ping-fang-gen-by-chen-li-guan-2/
     */
    fun mySqrt(target: Int): Int {
        // 特殊值判断
        if (target == 0) return 0
        if (target == 1) return 1

        var left = 1
        var right = target / 2

        // 在区间 [left..right] 查找目标元素
        while (left < right) {
            val mid = left + (right - left + 1) / 2

            // 注意：这里为了避免乘法溢出，改用除法
            if (mid > target / mid) {
                // 下一轮搜索区间是 [left..mid - 1]
                right = mid - 1
            } else {
                // 下一轮搜索区间是 [mid..right]
                left = mid
            }
        }

        return left
    }

    /**
     * 69. target 的平方根。方法二：牛顿迭代，
     *
     * 思想：v = (v + a/v)/2
     *
     * 时间复杂度：O(logX)，此方法是二次收敛的，相较于二分查找更快。。
     * 空间复杂度：O(1)，使用了常数个数的辅助空间用于存储和比较。
     *
     * https://leetcode-cn.com/problems/sqrtx/solution/69-target-de-ping-fang-gen-by-chen-li-guan-2/
     */
    fun mySqrt1(target: Int): Int {
        var v = target.toLong()
        while (target < v * v) {
            v = (v + target / v) / 2
        }
        return v.toInt()
    }

    /**
     * 74. 搜索二维矩阵
     *
     * 时间复杂度：O(logmn)，其中 m 和 n 分别是矩阵的行数和列数。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/search-a-2d-matrix/solution/74-sou-suo-er-wei-ju-zhen-by-chen-li-gua-rj3e/
     * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/solution/240-sou-suo-er-wei-ju-zhen-ii-by-chen-li-f62s/
     *
     * @param matrix
     * @param target
     * @return
     */
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        var rows = matrix.size - 1
        var columns = 0

        // 以二维数组左下角为原点，建立直角坐标轴。
        while (rows >= 0 && columns < matrix[0].size) {
            val num = matrix[rows][columns]

            if (num == target) {
                return true
            } else if (num < target) {
                // 若当前数字小于了查找数，查找往右移一位。
                columns++
            } else {
                // 若当前数字大于了查找数，查找往上移一位。
                rows--
            }
        }

        return false
    }
}
