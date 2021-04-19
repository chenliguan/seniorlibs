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
                LogUtils.d(TAG, "33. 搜索旋转排序数组：${search1(intArrayOf(1, 3), 2)}")
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
     * 空间复杂度：O(1) 。只需要常数级别的空间存放变量。
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

            if (nums[mid] === target) {
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

            } else {
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

    fun search1(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1
        var mid = 0

        while (left <= right) {
            mid = (right - left) / 2 + left
            if (nums[mid] == target) return mid

            if (nums[0] <= nums[mid] && (target < nums[0] || target > nums[mid])) {
                left = mid + 1
            } else if (target < nums[0] && target > nums[mid]) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }

        return -1
    }
}
