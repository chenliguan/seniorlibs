package com.seniorlibs.algorithm.map

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.linkedlist.LinkedActivity
import com.seniorlibs.algorithm.main.MainActivity
import com.seniorlibs.baselib.utils.LogUtils

/**
 * 哈希表
 */
class MapActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "MapActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, MapActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_two_sums).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_two_sums -> {
                LogUtils.e(TAG, "两数之和：${twoSum(intArrayOf(2, 11, 15, 7), 9).asList().toString()}")
            }

            else -> {
            }
        }
    }

    /**
     * 两数之和
     */
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map: MutableMap<Int, Int> = mutableMapOf()
        for (i in nums.indices) {
            // 1.采用目标值-当前值=另一个值，然后在表中查这个值
            val other : Int = target - nums[i]
            // 2.回过头来检查表中是否已经存在当前元素所对应的目标元素，有就返回，没有走3
            if (map.containsKey(other)) {
                return intArrayOf(map[other]!! , i)
            }
            // 3.将元素插入到表中：key是数值，value是下标
            map[nums[i]] = i
        }
        throw IllegalArgumentException("No two sum solution")
    }
}
