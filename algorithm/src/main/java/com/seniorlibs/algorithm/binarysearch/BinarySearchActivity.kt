package com.seniorlibs.algorithm.binarysearch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.baselib.utils.LogUtils

/**
 * Author: 陈李冠
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
                LogUtils.d(TAG, "33. 搜索旋转排序数组：${search(intArrayOf(1,3), 2)}")
                LogUtils.d(TAG, "33. 搜索旋转排序数组：${search1(intArrayOf(1,3), 2)}")
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
        var mid: Long = 0
        var result: Long = -1

        while (left <= right) {
            mid = left + (right - left) / 2

            if (mid * mid <= x) { // 2.82842..., 由于返回类型是整数，小数部分将被舍去，结果是2。所以结果在mid*mid<x这一边
                result = mid
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return result.toInt()
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
     * 思想：循环判断，直到排除到只剩最后一个元素时，退出循环，如果该元素和 target 相同，直接返回下标，否则返回 -1。
     * 步骤：1.当[0,mid]升序时: nums[0] <= nums[mid]，当target > nums[mid] || target < nums[0]，target不在[0,mid]中，则向后规约条件
     *      2.当[0,mid]发生旋转时: nums[0] > nums[mid]，当target > nums[mid] && target < nums[0]，target不在[0,mid]中，向后规约条件
     *      3.其他其他情况就是向前规约了
     *
     * 时间复杂度：O(logn)，其中n为nums数组的大小。整个算法时间复杂度即为二分搜索的时间复杂度O(logn)。
     * 空间复杂度：O(1) 。只需要常数级别的空间存放变量。
     *
     * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/33-sou-suo-xuan-zhuan-pai-xu-shu-zu-by-chen-li-gua/
     * @param nums
     * @param target
     * @return
     */
    fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1
        var mid = 0
        while (left < right) {
            mid = (right - left) / 2 + left

            if (nums[mid] == target) return mid

            // 当[0,mid]升序时: nums[0] <= nums[mid]，当target > nums[mid] || target < nums[0]，target不在[0,mid]中，则向后规约条件
            if (nums[0] <= nums[mid] && (target < nums[0] || target > nums[mid])) {
                left = mid + 1
                // 当[0,mid]发生旋转时: nums[0] > nums[mid]，当target > nums[mid] && target < nums[0]，target不在[0,mid]中，向后规约条件
            } else if (target < nums[0] && target > nums[mid]) {
                left = mid + 1
                // 其他其他情况就是向前规约了
            } else {
                right = mid - 1
            }
        }

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
