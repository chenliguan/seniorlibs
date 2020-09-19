package com.seniorlibs.algorithm.binarysearch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.baselib.utils.LogUtils


/**
 * 二分查找
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
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_sqrt -> {
                LogUtils.d(TAG, "69. x 的平方根：${mySqrt(8)}")
                LogUtils.d(TAG, "69. x 的平方根：${mySqrt1(8)}")
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

            if (x >= mid * mid) { // 2.82842..., 由于返回类型是整数，小数部分将被舍去，结果是2。所以结果在mid*mid<x这一边
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
}
