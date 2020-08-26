package com.seniorlibs.algorithm.array

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.linkedlist.LinkedActivity
import com.seniorlibs.baselib.utils.LogUtils

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
        else -> {
        }
    }

    private fun initData() {

    }

    /**
     * 删除排序数组中的重复项
     */
    fun removeDuplicates(nums: IntArray): Int {
        // 不重复指针-慢指针
        var i = 0
        // 快指针
        var j = 0

        while (j < nums.size) {
            // 不重复项才需要操作
            if (nums[i] != nums[j]) {
                // 1.移动不重复指针指向下一个待填充位置：
                // 避免：如果没有重复数据，q指向的元素原地复制了一遍
                i++
                // 2.判断当前指针和不重复指针的差异，如果不等，说明不重复指针和快指针之间都是某个相等数字
                //（否则，当前项是没移动过的，不能清空）
                if (i != j) {
                    // 3.把当前的值赋予不重复指针指针
                    nums[i] = nums[j]
                }
            }
            // 4.移动快指针指向下一个待填充位置
            j++
        }

        // 返回不重复指针+1位置
        return i + 1
    }

    /**
     * 移动零
     */
    fun moveZeroes(nums: IntArray): Unit {
        // 非零指针-慢指针
        var i = -1
        // 快指针
        var j = 0

        while (j < nums.size) {
            // 非零项才需要操作
            if (nums[j] != 0) {
                i++
                // 1.判断当前指针和非零指针的差异，如果不等，说明非零指针和快指针之间都是零
                //（否则，当前项是没移动过的，不能清空）
                if (i != j) {
                    // 2.把当前的值赋予非零指针，把自己赋值为0
                    nums[i] = nums[j]
                    nums[j] = 0
                }
            }
            // 3.移动非零指针指向下一个待填充位置
            j++
        }
    }

    /**
     * 11. 盛最多水的容器
     *
     * @param height
     * @return
     */
    fun maxArea(a: IntArray): Int {
        var max = 0
        var i = 0
        var j = a.size - 1

        while (i < j) {
            // 1.选择左右指针最小的高度，最大指针不动，移动小的指针向中间移动
            val minHeight = if (a[i] < a[j]) a[i++] else a[j--]

            // 2.计算区域面积
            val area = (j - i + 1) * minHeight

            // 3.更新最大面积
            max = Math.max(max, area)
        }

        return max
    }
}
