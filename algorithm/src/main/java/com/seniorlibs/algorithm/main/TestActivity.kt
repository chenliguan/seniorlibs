package com.seniorlibs.algorithm.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.array.ArrayActivity
import com.seniorlibs.baselib.utils.LogUtils
import java.util.*
import kotlin.collections.ArrayList

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/9/26
 * Mender:
 * Modify:
 * Description: 混合测试
 */
class TestActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "TestActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, TestActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_remove_duplicates).setOnClickListener(this)
        findViewById<View>(R.id.btn_move_zeroes).setOnClickListener(this)
        findViewById<View>(R.id.btn_three_sum).setOnClickListener(this)
        findViewById<View>(R.id.btn_four_sum).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_remove_duplicates -> {
                LogUtils.e(TAG, "26. 删除排序数组中的重复项：${removeDuplicates(intArrayOf(1, 1, 2))}")
            }
            R.id.btn_move_zeroes -> {
                LogUtils.e(TAG, "283. 移动零：${moveZeroes(intArrayOf(0, 1, 0, 3, 12))}")
            }
            R.id.btn_three_sum -> {
                val nums: IntArray = intArrayOf(-4, -1, -1, -1, 0, -1, 1, 2)
                LogUtils.d(ArrayActivity.TAG, "15. 三数之和：${threeSum(nums)}")
            }
            R.id.btn_four_sum -> {
                val nums: IntArray = intArrayOf(1,0,-1,0,-2,2)
                LogUtils.d(ArrayActivity.TAG, "18. 四数之和：${fourSum(nums, 0)}")
            }
            else -> {
            }
        }
    }

    /**
     * 26. 删除排序数组中的重复项
     */
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.size < 2) return nums.size

        var i = 0  // 不重复指针-慢指针
        var j = 1 // 快指针

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
     * 283. 移动零
     *
     * @param nums
     */
    fun moveZeroes(nums: IntArray): Unit {
        var i = 0 // 非零指针-慢指针
        var j = 0  // 快指针

        while (j < nums.size) {
            if (nums[j] != 0) {  // 非零项才需要操作
                if (i != j) {  // 1.判断当前指针和非零指针的差异，如果不等，说明非零指针和快指针之间都是零（否则，当前项是没移动过的，不能清空）
                    nums[i] = nums[j]  // 2.把 当前指针 赋值给 非零指针
                    nums[j] = 0   // 把 当前指针 赋值为 0
                }

                i++
            }

            j++ // 3.移动非零指针指向下一个待填充位置
        }
    }

    /**
     * 15. 三数之和
     * 判断nums中是否存在三个元素a，b，c，使得a+b+c=0，转化为->a+b=-c
     *
     * @param nums
     * @return
     */
    private fun threeSum(nums: IntArray): List<List<Int>> {
        val res = mutableListOf<MutableList<Int>>()
        if (nums.size < 3) return res

        // 1 排序
        Arrays.sort(nums)

        // 2 k
        for (k in 0 until nums.size - 2) {
            if (k > 0 && nums[k] == nums[k - 1]) continue

            var i = k + 1
            var j = nums.size - 1

            /* 获取当前最小值，如果最小值比目标值大，说明整个循环越来越大的值根本没戏 */
            val min1 = nums[k] + nums[i] + nums[i + 1]
            if (min1 > 0) {
                break
            }
            /* 获取当前最大值，如果最大值比目标值小，说明当前这一轮循环后面越来越小的值根本没戏，但是不能break，下一轮f+还存在==target */
            val max1 = nums[k] + nums[nums.size - 1] + nums[nums.size - 2]
            if (max1 < 0) {
                continue
            }

            // 3 i < j
            while (i < j) {
                val sum = nums[k] + nums[i] + nums[j]

                when {
                    sum < 0 -> {
                        while (i < j && nums[i] == nums[++i]) {}
                    }
                    sum > 0 -> {
                        while (i < j && nums[j] == nums[--j]) {}
                    }
                    sum == 0 -> {
                        res.add(mutableListOf(nums[k], nums[i], nums[j]))
                        while (i < j && nums[i] == nums[++i]) {}
                        while (i < j && nums[j] == nums[--j]) {}
                    }
                }
            }
        }

        return res
    }

    /**
     * 18. 四数之和
     *
     * @param nums
     * @param target
     * @return
     */
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        val res = mutableListOf<MutableList<Int>>()
        if (nums.size < 4) return res

        // 1 排序
        Arrays.sort(nums)

        // 2 f
        for (f in 0 until nums.size - 3) {
            if (f > 0 && nums[f] == nums[f - 1]) continue

            /* 获取当前最小值，如果最小值比目标值大，说明整个循环越来越大的值根本没戏 */
            val min = nums[f] + nums[f + 1] + nums[f + 2] + nums[f + 3]
            if (min > target) {
                break
            }
            /* 获取当前最大值，如果最大值比目标值小，说明当前这一轮循环后面越来越小的值根本没戏，但是不能break，下一轮f+还存在==target */
            val max = nums[f] + nums[nums.size - 1] + nums[nums.size - 2] + nums[nums.size - 3]
            if (max < target) {
                continue
            }

            // 3 k
            for (k in f + 1 until nums.size - 2) {
                if (k > f + 1 && nums[k] == nums[k - 1]) continue

                var i = k + 1
                var j = nums.size - 1

                /* 获取当前最小值，如果最小值比目标值大，说明整个循环越来越大的值根本没戏 */
                val min1 = nums[f] + nums[k] + nums[k + 1] + nums[k + 2]
                if (min1 > target) {
                    break
                }
                /* 获取当前最大值，如果最大值比目标值小，说明当前这一轮循环后面越来越小的值根本没戏，但是不能break，下一轮f+还存在==target */
                val max1 = nums[f] + nums[k] + nums[nums.size - 1] + nums[nums.size - 2]
                if (max1 < target) {
                    continue
                }

                // 4 i < j
                while (i < j) {
                    val sum = nums[f] + nums[k] + nums[i] + nums[j]

                    when {
                        sum < target -> {
                            while (i < j && nums[i] == nums[++i]) {
                            }
                        }
                        sum > target -> {
                            while (i < j && nums[j] == nums[--j]) {
                            }
                        }
                        sum == target -> {
                            res.add(mutableListOf(nums[f], nums[k], nums[i], nums[j]))
                            while (i < j && nums[i] == nums[++i]) {
                            }
                            while (i < j && nums[j] == nums[--j]) {
                            }
                        }
                    }
                }
            }
        }

        return res
    }
}
