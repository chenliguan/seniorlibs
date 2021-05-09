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
        findViewById<View>(R.id.btn_sqrt).setOnClickListener(this)
        findViewById<View>(R.id.btn_search).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_sqrt -> {
                LogUtils.d(TAG, "69. x 的平方根：${mySqrt(8)}")
                LogUtils.d(TAG, "69. x 的平方根：${mySqrt1(8)}")
            }
            R.id.btn_search -> {
                LogUtils.d(TAG, "33. 搜索旋转排序数组：${search(intArrayOf(1, 3), 2)}")
            }
            else -> {
            }
        }
    }


    /**
     * 69. x 的平方根。方法一：二分查找
     *
     * 思想：一个数的平方根肯定不会超过它自己，最小是0，范围最大是自己。例如 ：8的平方根，8的一半是4，4^2=16>8，
     *      意即：如果一个数的一半的平方大于它自己，那么这个数的取值范围在小于4的一半：0-3
     *
     * 时间复杂度：O(logX)，二分法的时间复杂度是对数级别的。
     * 空间复杂度：O(1)，使用了常数个数的辅助空间用于存储和比较。
     *
     * https://leetcode-cn.com/problems/sqrtx/solution/69-x-de-ping-fang-gen-by-chen-li-guan-2/
     */
    fun mySqrt(x: Int): Int {
        var left: Long = 0
        var right: Long = x.toLong()
        var res : Long = -1

        // 在区间 [left..right] 查找目标元素
        while (left <= right) {
            val mid = (right - left) / 2 + left

            // x = 8，mid = 2.82842... 注意：如果一个数 mid 的平方大于 x ，那么 mid 一定不是 x 的平方根。所以结果在 mid * mid < x 这边
            if (mid * mid <= x) {
                // 下一轮搜索区间是 [left..mid - 1]（）
                left = mid + 1
                res = mid
            } else {
                // 下一轮搜索区间是 [mid..right]
                right = mid - 1
            }
        }

        return res.toInt()
    }

    /**
     * 69. x 的平方根。方法二：牛顿迭代，
     *
     * 思想：v = (v + a/v)/2
     *
     * 时间复杂度：O(logX)，此方法是二次收敛的，相较于二分查找更快。。
     * 空间复杂度：O(1)，使用了常数个数的辅助空间用于存储和比较。
     *
     * https://leetcode-cn.com/problems/sqrtx/solution/69-x-de-ping-fang-gen-by-chen-li-guan-2/
     */
    fun mySqrt1(x: Int): Int {
        var v = x.toLong()
        while (x < v * v) {
            v = (v + x / v) / 2
        }
        return v.toInt()
    }


    /**
     * 33. 搜索旋转排序数组  5,6,7,0,1,2,3,4  6
     *
     *      对于旋转数组，我们无法直接根据 nums[mid] 与 target 的大小关系来判断 target 是在 mid 的左边还是右边，因此需要「分段讨论」。
     * 思想：循环判断，直到排除到只剩最后一个元素时，退出循环，如果该元素和 target 相同，直接返回下标，否则返回 -1。
     *
     * 时间复杂度：O(logn)，其中n为nums数组的大小。整个算法时间复杂度即为二分搜索的时间复杂度O(logn)。
     * 空间复杂度：O(1)。只需要常数级别的空间存放变量。
     *
     * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/33-sou-suo-xuan-zhuan-pai-xu-shu-zu-by-chen-li-gua/
     *
     * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/yi-wen-dai-ni-shua-bian-er-fen-cha-zhao-dtadq/
     * @param nums
     * @param target
     * @return
     */
    fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right: Int = nums.size - 1
        var mid = 0

        while (left <= right) {
            mid = (right - left) / 2 + left

            if (nums[mid] == target) {
                // 直接返回
                return mid
            }

            // 先根据 nums[mid] 与 nums[left] 的关系判断 mid 是在左段还是右段
            if (nums[mid] >= nums[left]) {
                // 落在同一数组的情况，同时落在数组1 或 数组2
                if (target >= nums[left] && target < nums[mid]) {
                    // target 落在 left 和 mid 之间，则移动我们的 right，完全有序的一个区间内查找
                    right = mid - 1
                } else {
                    // target 落在 mid 和 right 之间，有可能在数组1， 也有可能在数组2
                    left = mid + 1
                }

            } else if (nums[mid] < nums[left]) {
                // 不落在同一数组的情况，left 在数组1，mid 落在 数组2
                if (target > nums[mid] && target <= nums[right]) {
                    // 有序的一段区间，target 在 mid 和 right 之间
                    left = mid + 1
                } else {
                    // 两种情况，target 在 left 和 mid 之间
                    right = mid - 1
                }

            }
        }

        // 没有查找到
        return -1
    }

    /**
     * 153. 寻找旋转排序数组中的最小值
     *
     * 时间复杂度：时间复杂度为 O(logn)。
     * 空间复杂度：O(1)。
     *
     * @param nums
     * @return
     */
    fun findMin(nums: IntArray): Int {
        var left = 0
        var right = nums.size - 1                   // 左闭右闭区间，如果用右开区间则不方便判断右值

        while (left < right) {                           // 循环不变式，如果left == right，则循环结束
            val mid = left + (right - left) / 2     // 地板除，mid更靠近left

            if (nums[mid] > nums[right]) {               // 中值 > 右值，最小值在右半边，收缩左边界
                left = mid + 1                           // 因为中值 > 右值，中值肯定不是最小值，左边界可以跨过 mid
            } else if (nums[mid] <= nums[right]) {       // 明确中值 < 右值，最小值在左半边，收缩右边界
                right = mid                              // 因为中值 <= 右值，中值也可能是最小值，右边界只能取到 mid 处
            }
        }

        return nums[left]                                // 循环结束，left == right，最小值输出 nums[left] 或 nums[right] 均可
    }


    /**
     * 154. 寻找旋转排序数组中的最小值 II == 剑指 Offer 11. 旋转数组的最小数字
     * 区别 153：可能存在 重复 元素值的数组 nums
     *
     * 时间复杂度：时间复杂度为 O(logn)。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/solution/154-xun-zhao-xuan-zhuan-pai-xu-shu-zu-zh-xshl/
     * @param nums
     * @return
     */
    fun minArray(nums: IntArray): Int {
        var left = 0
        var right = nums.size - 1                   // 左闭右闭区间，如果用右开区间则不方便判断右值

        while (left < right) {                           // 循环不变式，如果left == right，则循环结束
            val mid = left + (right - left) / 2     // 地板除，mid更靠近left

            if (nums[mid] > nums[right]) {               // 中值 > 右值，最小值在右半边，收缩左边界
                left = mid + 1                           // 因为中值 > 右值，中值肯定不是最小值，左边界可以跨过 mid
            } else if (nums[mid] < nums[right]) {        // 明确中值 < 右值，最小值在左半边，收缩右边界
                right = mid                              // 因为中值 <= 右值，中值也可能是最小值，右边界只能取到 mid 处
            } else {
                right--  // middle 既不大于 left 的值，也不小于 right 的值，代表着 middle 可能等于 left 的值，或者 right 的值，只能让 right 递减，来一个一个找最小值了。
            }
        }

        return nums[left]                                // 循环结束，left == right，最小值输出 nums[left] 或 nums[right] 均可
    }

    /**
     * 162. 寻找峰值
     * 思路：出现了 nums[-1] = nums[n] = -∞，这就代表着 只要数组中存在一个元素比相邻元素大，那么沿着它一定可以找到一个峰值。因此，使用二分查找找到峰值。
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
}
