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
        findViewById<View>(R.id.btn_peak_index_in_mountain_array).setOnClickListener(this)
        findViewById<View>(R.id.btn_valid_mountain_array).setOnClickListener(this)
        findViewById<View>(R.id.btn_get_number_same_as_index).setOnClickListener(this)
        findViewById<View>(R.id.btn_sqrt).setOnClickListener(this)
        findViewById<View>(R.id.btn_find_peak_element).setOnClickListener(this)
        findViewById<View>(R.id.btn_search_matrix).setOnClickListener(this)
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
            R.id.btn_peak_index_in_mountain_array -> {
                LogUtils.d(TAG, "852. 山脉数组的峰顶索引：${peakIndexInMountainArray(intArrayOf(1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1))}")
            }
            R.id.btn_valid_mountain_array -> {
                LogUtils.d(TAG, "941. 有效的山脉数组：${validMountainArray(intArrayOf(3, 5, 5))}")
            }
            R.id.btn_get_number_same_as_index -> {
                LogUtils.d(TAG, "数组中数值和下标相等的元素：${getNumberSameAsIndex(intArrayOf(-3, -1, 1, 3, 5))}")
            }
            R.id.btn_sqrt -> {
                LogUtils.d(TAG, "69. target 的平方根：${mySqrt(8)}")
            }
            R.id.btn_find_peak_element -> {
                LogUtils.d(TAG, "162. 寻找峰值：${findPeakElement(intArrayOf(3, 5, 5))}")
            }
            R.id.btn_search_matrix -> {
//                [[1,4,7,11,15],[2,5,6,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]
                val array = arrayOf(intArrayOf(1, 4, 7, 11, 15),
                        intArrayOf(2, 5, 6, 12, 19),
                        intArrayOf(3, 6, 9, 16, 22),
                        intArrayOf(10, 13, 14, 17, 24),
                        intArrayOf(18, 21, 23, 26, 30))
                LogUtils.d(TAG, "74. 搜索二维矩阵：${searchMatrix(array, 5)}")
                LogUtils.d(TAG, "74. 搜索二维矩阵1：${searchMatrix1(array, 5)}")
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
     * 941. 有效的山脉数组   方法一：双指针
     *
     * 时间复杂度：O(N)。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/valid-mountain-array/solution/941-you-xiao-de-shan-mai-shu-zu-by-chen-buvws/
     * @param nums
     * @return
     */
    fun validMountainArray(nums: IntArray): Boolean {
        var l = 0
        var r = nums.size - 1

        // 从左边往右边找，一直找到山峰为止
        while (l + 1 < nums.size && nums[l] < nums[l + 1]) {
            l++
        }

        // 从右边往左边找，一直找到山峰为止
        while (r > 0 && nums[r - 1] > nums[r]) {
            r--
        }

        // 判断从左边和从右边找的山峰是不是同一个
        return l > 0 && r < nums.size - 1 && l == r
    }

    /**
     * 852. 山脉数组的峰顶索引    1,2,3,4,5,6,5,4,3,2,1  nums[5] = 6大于左边和右边的所有元素，并且数组刚好是个山峰。
     *
     * 思路：提问破解法，找到下标，满足比左边和右边的元素都大。
     *      1、第一步：要的是"数组的下标"，x 就是数组的下标，范围就是 [1, nums.size-1)。
     *      2、第二步：满足约束条件的 f(x)=0。
     *      3、第三步：不满足约束条件的 f(x) 设置为 -1 或者 1
     *          1. 由于整个数组形成了一个山峰，山峰的左边是升序，山峰的右边是降序。
     *          2. 山峰左边的元素满足 nums[i-1] < nums[i] < nums[i+1]，可以把这种关系记录为 -1；
     *          3. 山峰元素满足 nums[i-1] < nums[i] && nums[i] > nums[i+1]，可以把这种关系记录 0；
     *          4. 山峰右边的元素满足 nums[i-1] > nums[i] > nums[i+1]，可以把这种关系记录为 1。
     *          5. nums[] = [1,2,3,4,5,6,5,4,3,2,1] --> 最终确定 f(x) 可以映射成一个有序数组 C[] = [-1,-1,-1,-1,0,1,1,1,1]
     *      4、第四步：最优解 0 在 C[] 的最左边还是最右边，决定使用 lowerBound 还是 upperBound。
     *
     * 时间复杂度：O(logN)。
     * 空间复杂度：O(1)。只需要常数级别的空间存放变量。
     *
     * https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/solution/852-shan-mai-shu-zu-de-feng-ding-suo-yin-njqh/
     *
     * @param nums
     * @return
     */
    fun peakIndexInMountainArray(nums: IntArray): Int {
        if (nums.size < 3) return -1

        // 山峰元素就只有一个，可以认为是一个最左边的元素，那么只需要用 lowerBound 就可以了
        val l = peakLowerBound(nums)

        return l
    }

    /**
     * 寻找数组中给定元素的下界
     *
     * 注意：求峰顶索引 nums.size >= 3，因此范围就是 [1, nums.size-1)
     *
     * @param nums
     * @return
     */
    fun peakLowerBound(nums: IntArray): Int {
        var l = 1
        var r = nums.size - 1
        while (l < r) {
            val m = l + (r - l) / 2
            // mov表示是中间映射的值
            val mov = getC(nums, m)
            if (mov < 0) {
                l = m + 1
            } else {
                r = m
            }
        }

        // 由于执行到最后 nums[l..r] 里一定存在插入元素的位置，并且退出循环的时候一定有 l == r 成立，因此直接返回 l 或者 r 均可。
        return l
    }

    fun getC(nums: IntArray, i: Int): Int {
        // 关键：不满足约束条件的 f(i) ，设置为 -1 或者 1
        if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) {
            return -1
        }

        // 关键：找到下标，山峰元素满足比左边和右边的元素都大，设置为 0
        if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) {
            return 0
        }

        // 关键：不满足约束条件的 f(i) ，设置为 -1 或者 1
        return 1
    }


    /**
     * 数组中数值和下标相等的元素
     *
     * 题目：一个单调递增的数组里的每个元素都是整数并且是唯一的。请编程实现一个函数找出数组中任意一个数值等于其下标的元素。例如，在数组 [−3,−1,1,3,5] 中，数字 3 和它的下标相等。
     *
     * 时间复杂度：O(logN)。
     * 空间复杂度：O(1)。只需要常数级别的空间存放变量。
     *
     * https://www.acwing.com/problem/content/65/
     *
     * @param nums
     * @return
     */
    fun getNumberSameAsIndex(nums: IntArray): Int {
        if (nums.isEmpty()) return -1

        val l = getNumberLowerBound(nums)

        if (l < nums.size && nums[l] == l) {
            return l
        }

        return -1
    }

    /**
     * 寻找数组中给定元素的下界
     *
     * 注意：范围就是 [0, nums.size)
     *
     * @param nums
     * @return
     */
    fun getNumberLowerBound(nums: IntArray): Int {
        var l = 0
        var r = nums.size
        while (l < r) {
            val m = l + (r - l) / 2
            val mov: Int = getX(nums, m)
            if (mov < 0) {
                l = m + 1
            } else {
                r = m
            }
        }

        return l
    }

    fun getX(nums: IntArray, i: Int): Int {
        val v = nums[i]
        // 中间的数字与下标比较
        if (v < i) {
            // 如果中间数字小于下标，说明目标数字在右半段，对右半段进行二分查找
            return -1
        } else if (v > i) {
            // 如果中间数字大于下标，说明目标数字在左半段，对左半段进行二分查找
            return 1
        } else {
            // 如果中间数字等于下标，则是我们要找的
            return 0
        }
    }


    /**
     * 69. target 的平方根。方法一：二分查找
     *
     * 时间复杂度：O(logX)，二分法的时间复杂度是对数级别的。
     * 空间复杂度：O(1)，使用了常数个数的辅助空间用于存储和比较。
     *
     * https://leetcode-cn.com/problems/sqrtx/solution/69-target-de-ping-fang-gen-by-chen-li-guan-2/
     */
    fun mySqrt(x: Int): Int {
        val l = mySqrtLowerBound(x)

        // 注意，当在映射之后的数组中不存在0的时候
        // l 的下标是指向映射之后的 C[x] = 1 第一个位置，此时 l * l > m，所以这里需要返回 l - 1。因为按照题意，需要返回整数部分。
        if (l * l == x) {
            return l
        }

        // 8 的平方根是 2.82842...，l = 3，返回 l - 1 = 2
        return l - 1
    }

    /**
     * 寻找数组中给定元素的下界
     *
     * 注意：范围就是 [0, target)
     *
     * @param target
     * @return
     */
    fun mySqrtLowerBound(target: Int): Int {
        var l = 0
        var r = target
        while (l < r) {
            val m = l + (r - l) / 2
            val mov = getC(target.toLong(), m.toLong())
            if (mov < 0) {
                l = m + 1
            } else {
                r = m
            }
        }

        return l
    }

    fun getC(x: Long, m: Long): Int {
        if (m * m < x) {
            // 如果结果小于x，说明目标数字在右半段，对右半段进行二分查找
            return -1
        } else if (m * m > x) {
            // 如果结果大于x，说明数字在左半段，对左半段进行二分查找
            return 1
        } else {
            // 如果结果等于x，则是我们要找的
            return 0
        }
    }


    /**
     * 74. 搜索二维矩阵 == 240. 搜索二维矩阵 II   方法一：二分
     *
     * 思路：有序，第一反应就是二分查找。最直接的做法，一行一行的进行二分查找即可。
     *
     * 时间复杂度：O(mlogn)，其中 m 和 n 分别是矩阵的行数和列数。
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
        if (matrix.isEmpty() || matrix[0].isEmpty()) return false

        for (i in 0 until matrix.size) {
            // 一行的第一个元素大于 target，直接返回 false
            if (matrix[i][0] > target) break
            // 一行的最后一个元素小于 target，当前行就不用考虑了，换下一行。
            if (matrix[i][matrix[i].size - 1] < target) continue

            if (lowerBoundMatrix(matrix[i], target)) return true
        }
        return false
    }

    /**
     * 寻找数组中给定元素的下界
     *
     * @param nums
     * @param target
     * @return
     */
    fun lowerBoundMatrix(nums: IntArray, target: Int): Boolean {
        val col = lowerBound(nums, target)

        // 注意：退出循环的时候，我们不能确定 nums[l] 是否等于 target，因此还需要单独做一次判断；
        return col < nums.size && nums[col] == target
    }

    /**
     * 74. 搜索二维矩阵  方法二：从左下角往右上遍历
     *
     * 时间复杂度：O(logmn)，其中 m 和 n 分别是矩阵的行数和列数。
     * 空间复杂度：O(1)。
     *
     * @param matrix
     * @param target
     * @return
     */
    fun searchMatrix1(matrix: Array<IntArray>, target: Int): Boolean {
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



    /**
     * 162. 寻找峰值   [1,2,1,3,5,6,4]  位置5->6
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(1)。只需要常数级别的空间存放变量。
     *
     * 情况 1. 所有的数字以降序排列。第一个元素即为峰值。首先检查当前元素是否大于下个元素。第一个元素满足这一条件，因此被正确判断为峰值。此时，我们不需要继续向下判断。
     * 情况 2. 所有的数字以升序排列。会一直比较nums[i]与nums[i+1]以判断nums[i]是否是峰值元素。没有元素符合这一条件，说明处于上坡而非峰值。于是，在结尾，返回末尾元素作为峰值元素。
     * 情况 3. 峰值出现在中间某处。在num[i]>num[i+1]的地方是"峰值"。
     *
     * https://leetcode-cn.com/problems/find-peak-element/solution/162-xun-zhao-feng-zhi-by-chen-li-guan-1ooh/
     * @param array
     * @return
     */
    fun findPeakElement(nums: IntArray): Int {
        for (i in 0 until nums.size - 1) {
            if (nums[i] > nums[i + 1]) {
                return i
            }
        }
        return nums.size - 1
    }
}
