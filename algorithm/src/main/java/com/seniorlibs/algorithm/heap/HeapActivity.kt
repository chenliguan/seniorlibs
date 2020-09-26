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
import kotlin.Comparator
import kotlin.collections.ArrayList

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/9/10
 * Mender:
 * Modify:
 * Description: 堆
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
        findViewById<View>(R.id.btn_heap).setOnClickListener(this)
        findViewById<View>(R.id.btn_top_k_frequent).setOnClickListener(this)
        findViewById<View>(R.id.btn_get_least_numbers).setOnClickListener(this)
    }

    private fun initData() {

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_heap -> {
                val maxHeap = BinaryHeap(10)
                maxHeap.insert(10)
                maxHeap.insert(4)
                maxHeap.insert(9)
                maxHeap.insert(1)
                maxHeap.insert(7)
                maxHeap.insert(5)
                maxHeap.insert(3)
                maxHeap.printHeap()
                maxHeap.delete(5)
                maxHeap.printHeap()
                maxHeap.delete(2)
                maxHeap.printHeap()
            }
            R.id.btn_top_k_frequent -> {
                val nums: IntArray = intArrayOf(0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6)
                LogUtils.d(TAG, "347. 前 K 个高频元素 ：" + topKFrequent(nums, 3))
            }
            R.id.btn_top_k_frequent -> {
                LogUtils.d(TAG, "40. 最小的k个数 ：" + getLeastNumbers(intArrayOf(3, 2, 1), 2))
            }
            else -> {
            }
        }
    }

    /**
     * 347. 前 K 个高频元素  nums = [0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6] , k = 2
     *
     * 思路：如果堆的元素个数小于k，就可以直接插入堆中。
     *      如果堆的元素个数等于k，则检查堆顶与新元素的次数的大小。如果堆顶更大，说明至少有k个数字的出现次数比当前值大，故舍弃当前值；否则，就弹出堆顶，并将当前值插入堆中。
     *
     * 时间复杂度：O(nlogk)。维护一个大小为k的小顶堆，每次插入元素的时候，需要的时间是logk；
     *          而整个过程中处理n个元素，最坏的情况就是，每次如果新元素的次数比堆顶端的元素大，
     *          则弹出堆顶端的元素，将新的元素添加进堆中，这个过程总共需要做n次，结果是nlogk
     *
     * 空间复杂度：O(n)，最坏情况下（每个元素都不同），map需要存储n个键值对，优先队列需要存储k个元素，
     *          因此空间复杂度是O(n)。
     *
     * https://leetcode-cn.com/problems/top-k-frequent-elements/solution/347-qian-k-ge-gao-pin-yuan-su-by-chen-li-guan/
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

        // 2.1 优先堆队列：按升序用小顶堆保存次数最大的k个元素
        val heap = PriorityQueue(Comparator<Int> { o1, o2 -> map[o1]!! - map[o2]!! })
        for (key in map.keys) {
            if (heap.size < k) {   // 2.2 如果堆的元素个数小于k，就可以直接插入堆中
                heap.offer(key)
            } else if (map[key]!! > map[heap.peek()]!!) { // 如果堆的元素个数等于k，
                heap.poll()                               // 2.3 如果新元素的次数比堆顶端的元素大，则弹出堆顶端的元素，将新的元素添加进堆中
                heap.offer(key)
            }
        }

        val list: MutableList<Int> = mutableListOf()   // 3. 最小堆中的k个元素即为前k个高频元素，取出堆中的元素
        for (i in heap) {
            list.add(i)
        }
        return list
    }


    /**
     * 40. 最小的k个数 - 方法一：堆
     *
     * 时间复杂度：O(nlogk)。维护一个大小为k的小顶堆，每次插入元素的时候，需要的时间是logk；
     *          而整个过程中处理n个元素，最坏的情况就是，每次如果新元素的次数比堆顶端的元素大，
     *          则弹出堆顶端的元素，将新的元素添加进堆中，这个过程总共需要做n次，结果是nlogk
     *
     * 空间复杂度：O(n)，因为小根堆里最多n个数，n是数组的长度。
     *
     * @param arr
     * @param k
     * @return
     */
    fun getLeastNumbers(arr: IntArray, k: Int): IntArray {
        // val heap = PriorityQueue<Int>()   // 默认是小根堆
        val heap = PriorityQueue(Comparator<Int> { o1, o2 -> o1 - o2 })
        for (c in arr) {    // 将所有数加入到小根堆中
            heap.offer(c)
        }

        val res = IntArray(k)   // 从小根堆中取出最小数，取k次
        for (i in 0 until k) {
            res[i] = heap.poll()
        }

        return res
    }
}
