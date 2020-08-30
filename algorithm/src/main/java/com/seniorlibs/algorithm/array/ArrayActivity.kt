package com.seniorlibs.algorithm.array

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.linkedlist.LinkedActivity
import com.seniorlibs.baselib.utils.LogUtils
import java.util.*
import kotlin.collections.ArrayList

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
        findViewById<View>(R.id.btn_three_sum).setOnClickListener(this)
    }

    override fun onClick(v: View) = when (v.id) {
        R.id.btn_remove_duplicates -> {
            val nums: IntArray = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
            // 删除排序数组中的重复项
            removeDuplicates(nums)
            LogUtils.d(TAG, "删除排序数组中的重复项：${nums.asList().toString()}")
        }
        R.id.btn_move_zeroes -> {
            val nums: IntArray = intArrayOf(0, 1, 0, 3, 12, 0, 1, 12, 0, 0, 6)
            // 移动零
            moveZeroes(nums)
            LogUtils.d(TAG, "移动零：${nums.asList().toString()}")
        }
        R.id.btn_max_area -> {
            val nums: IntArray = intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)
            // 11. 盛最多水的容器
            LogUtils.d(TAG, "盛最多水的容器：${maxArea(nums)}")
        }
        R.id.btn_three_sum -> {
            val nums: IntArray = intArrayOf(-4, -1, -1, -1, 0, -1, 1, 2)
            // 15. 三数之和
            LogUtils.d(TAG, "三数之和：${threeSum(nums)}")
        }
        else -> {
        }
    }

    private fun initData() {

    }

    /**
     * 删除排序数组中的重复项
     */
    fun removeDuplicates(nums: IntArray): Int {
        var i = 0  // 不重复指针-慢指针
        var j = 0 // 快指针

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
     * 移动零
     */
    private fun moveZeroes(nums: IntArray): Unit {
        var i = -1 // 非零指针-慢指针
        var j = 0  // 快指针

        while (j < nums.size) {
            if (nums[j] != 0) {  // 非零项才需要操作
                i++
                if (i != j) {  // 1.判断当前指针和非零指针的差异，如果不等，说明非零指针和快指针之间都是零（否则，当前项是没移动过的，不能清空）
                    nums[i] = nums[j]  // 2.把当前的值赋予非零指针，把自己赋值为0
                    nums[j] = 0
                }
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
        var max = 0
        var i = 0
        var j = a.size - 1

        while (i < j) {
            val width = j - i  // 1.计算宽
            val minHeight = if (a[i] < a[j]) a[i++] else a[j--] // 2.选择左右指针最小的高度，最大指针不动，移动小的指针向中间移动
            val area = width * minHeight  // 3.计算区域面积
            max = Math.max(max, area)  // 4.更新最大面积
        }

        return max
    }

    /**
     * 15. 三数之和 -4, -1, -1, -1, 0, -1, 1, 2
     *
     * @param a
     * @return
     */
    private fun threeSum(nums: IntArray): List<List<Int>> {
        val res: MutableList<MutableList<Int>> = mutableListOf()
        if (nums.size < 3) {
            return res
        }

        Arrays.sort(nums) // 排序，从小到大
        if (nums[0] > 0 || nums[nums.size - 1] < 0) { // 优化1: 整个数组同符号，则无解
            return res
        }

        for (k in 0 until nums.size - 1) { // C位人选，最左（最小）数字的指针k，最大nums.size - 2，右边有i和j
            if (nums[k] > 0) break // 优化2: 最左值为正数则一定无解，跳出循环
            if (k > 0 && nums[k] == nums[k - 1]) continue // 跳过所有相同的nums[k]，已将nums[k-1]的所有组合加入到结果中

            // 通过双指针i、j交替向中间移动
            var i = k + 1 // i 在 k
            var j = nums.size - 1 // j 在 nums.size - 1
            while (i < j) {
                val sum = nums[k] + nums[i] + nums[j]
                when {
                    sum < 0 -> {
                        while (i < j && nums[i] == nums[++i]) {
                        } // 实力太弱，把菜鸟那边右移一位，并跳过所有相同的nums[i]
                    }
                    sum > 0 -> {
                        while (i < j && nums[j] == nums[--j]) {
                        } // 实力太强，把大神那边左移一位，并跳过所有相同的nums[j]
                    }
                    else -> {
                        res.add(mutableListOf(nums[k], nums[i], nums[j])) // 记录组合[k, i, j]至res
                        while (i < j && nums[i] == nums[++i]) { // 执行++i和--j并跳过所有相同复的nums[i]和nums[j]
                        }
                        while (i < j && nums[j] == nums[--j]) {
                        }
                    }
                }
            }
        }
        return res
    }
}
