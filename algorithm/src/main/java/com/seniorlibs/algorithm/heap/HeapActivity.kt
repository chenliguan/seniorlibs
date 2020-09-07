package com.seniorlibs.algorithm.heap

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
import kotlin.collections.ArrayList

/**
 * 堆
 */
class HeapActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "HeapActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, HeapActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heap)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_top_k_frequent).setOnClickListener(this)
    }

    private fun initData() {

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_top_k_frequent -> {
                val nums: IntArray = intArrayOf(0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6)
                LogUtils.e(TAG, "347. 前 K 个高频元素 ：" + topKFrequent(nums, 3))
            }

            else -> {
            }
        }
    }

    /**
     * 347. 前 K 个高频元素  nums = [0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6] , k = 2
     *
     * 时间复杂度：O(nlogk)。维护一个大小为k的小顶堆，每次插入元素的时候，需要的时间是logk；
     *          而整个过程中处理n个元素，最坏的情况就是，每次如果新元素的次数比堆顶端的元素大，
     *          则弹出堆顶端的元素，将新的元素添加进堆中，这个过程总共需要做n次，结果是nlogk
     *
     * 空间复杂度：O(n)，最坏情况下（每个元素都不同），map需要存储n个键值对，优先队列需要存储k个元素，
     *          因此空间复杂度是O(n)。
     *
     * @param nums
     * @param k
     * @return
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun topKFrequent(nums: IntArray, k: Int): MutableList<Int> {
        val map: MutableMap<Int, Int> = mutableMapOf()  // 1. 使用map统计每个元素出现的次数：元素为键，元素出现的次数为值
        for (num in nums) {
            if (map.containsKey(num)) {
                map[num] = map[num]!! + 1
            } else {
                map[num] = 1
            }
        }

        val queue : PriorityQueue<Int> = PriorityQueue<Int>(object : Comparator<Int> {
            override fun compare(o1: Int?, o2: Int?): Int {
                return map[o1]!! - map[o2]!! // 2.1 优先堆队列：按升序用小顶堆保存次数最大的k个元素
            }
        })
        for (key in map.keys) {
            if (queue.size < k) {  // 2.2 如果堆的元素个数小于k，就可以直接插入堆中
                queue.offer(key)
            } else if (map[key]!! > map[queue.peek()]!!) {  // 2.3 如果新元素的次数比堆顶端的元素大，则弹出堆顶端的元素，将新的元素添加进堆中
                queue.poll()
                queue.offer(key)
            }
        }

        val list: MutableList<Int> = mutableListOf()   // 3. 最小堆中的k个元素即为前k个高频元素，取出堆中的元素
        queue.forEach {
            list.add(it)
        }
        return list
    }
}
