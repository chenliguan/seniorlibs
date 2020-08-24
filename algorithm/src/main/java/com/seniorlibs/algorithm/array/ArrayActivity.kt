package com.seniorlibs.algorithm.array

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.linkedlist.LinkedActivity

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
        findViewById<View>(R.id.btn_array).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_array -> {
                val nums: IntArray = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
                removeDuplicates(nums)
            }

            else -> {
            }
        }
    }

    private fun initData() {
        val nums: IntArray = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
        // 删除排序数组中的重复项
        removeDuplicates(nums)
        // 移动零
        moveZeroes(nums)
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
        var j = 0
        // 快指针
        for (i in nums.indices) {
            // 非零项才需要操作
            if (nums[i] != 0) {
                // 1.把当前的值赋予非零指针
                nums[j] = nums[i]
                // 2.判断当前指针和非零指针的差异，如果不等，说明非零指针和快指针之间都是零
                //（否则，当前项是没移动过的，不能清空）
                if (i != j) {
                    nums[i] = 0
                }
                // 3.移动非零指针指向下一个待填充位置
                j++
            }
        }
    }
}
