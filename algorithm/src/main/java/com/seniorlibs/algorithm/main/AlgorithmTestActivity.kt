package com.seniorlibs.algorithm.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.db.DbActivity
import com.seniorlibs.algorithm.db.DbActivity2
import com.seniorlibs.baselib.utils.LogUtils
import java.util.*

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/9/26
 * Mender:
 * Modify:
 * Description: 混合测试
 */
class AlgorithmTestActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "AlgorithmTestActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, AlgorithmTestActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_algorithm_test)

        lifecycle
        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_1).setOnClickListener(this)

    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_1 -> {
                val nums: IntArray = intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)
                LogUtils.d(TAG, "42. 接雨水：${trap(nums)}")
            }
            else -> {
            }
        }
    }


    /**
     * 42. 接雨水
     *
     * 时间复杂度： O(n)。
     * 空间复杂度： O(1)。
     *
     * https://leetcode-cn.com/problems/trapping-rain-water/solution/42-jie-yu-shui-by-chen-li-guan-0dsm/
     * @param height
     * @return
     */
    fun trap(height: IntArray): Int {
        if (height.isEmpty()) return 0

        val n = height.size
        var left = 0
        var right = n - 1
        var res = 0

        var l_max = height[0]
        var r_max = height[n - 1]

        while (left < right) {
            l_max = Math.max(l_max, height[left])
            r_max = Math.max(r_max, height[right])

            if (l_max < r_max) {
                res += l_max - height[left]
                left++
            } else {
                res += r_max - height[right]
                right--
            }
        }

        return res
    }

}









