package com.seniorlibs.algorithm.main

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
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
        setContentView(R.layout.activity_test)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_top_k_frequent).setOnClickListener(this)

    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_top_k_frequent -> {
                val nums: IntArray = intArrayOf(1, 1, 1, 2, 2, 3)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    LogUtils.d(TAG, "347. 前 K 个高频元素 ：" + topKFrequent(nums, 2))
                }
            }

            else -> {
            }
        }
    }

    /**
     * 347. 前 K 个高频元素
     *
     * @param nums
     * @param k
     * @return
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val res = IntArray(k)
        if (nums.isEmpty()) return res

        val map = mutableMapOf<Int, Int>()
        for (n in nums) {
            if (map.containsKey(n)) {
                map[n] = map[n]!! + 1
            } else {
                map[n] = 1
            }
        }

        // 优先级队列，升序->小顶堆
        val queue  = PriorityQueue(Comparator<Int> { o1, o2 -> map[o1]!! - map[o2]!!})

        for (key in map.keys) {
            if (queue.size < k) {
                queue.offer(key)
            } else if (map[key]!! > map[queue.peek()]!!) {
                queue.poll()
                queue.offer(key)
            }
        }

        for (i in 0 until queue.size) {
            res[i] = queue.poll()
        }

        return res
    }
}
