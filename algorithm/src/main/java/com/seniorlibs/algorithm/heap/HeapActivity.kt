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
        findViewById<View>(R.id.btn_find_kth_largest).setOnClickListener(this)
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
            R.id.btn_get_least_numbers -> {
                LogUtils.d(TAG, "40. 最小的k个数 ：" + getLeastNumbers(intArrayOf(3, 2, 1), 2))
            }
            R.id.btn_find_kth_largest -> {
                LogUtils.d(TAG, "215. 数组中的第K个最大元素 ：" + findKthLargest(intArrayOf(3, 2, 3, 1, 2, 4, 5, 5, 6), 4))
            }
            else -> {
            }
        }
    }

    /**
     * 347. 前 K 个高频元素  方法一：小顶堆
     *
     * 思路：如果堆的元素个数小于k，就可以直接插入堆中。
     *      如果堆的元素个数等于k，则检查堆顶与新元素的次数的大小。如果堆顶更大，说明至少有k个数字的出现次数比当前值大，故舍弃当前值；否则，就弹出堆顶，并将当前值插入堆中。
     *
     * 时间复杂度：O(nlogk)。维护一个大小为k的小顶堆，每次插入元素的时候，需要的时间是logk，而整个过程中处理n个元素。
     *          最坏的情况就是，每次如果新元素的次数比堆顶端的元素大，则弹出堆顶端的元素，将新的元素添加进堆中，这个过程总共需要做n次，结果是nlogk
     *
     * 空间复杂度：O(n)，最坏情况下（每个元素都不同），map需要存储n个键值对，优先队列需要存储k个元素，因此空间复杂度是O(n)。
     *
     * https://leetcode-cn.com/problems/top-k-frequent-elements/solution/347-qian-k-ge-gao-pin-yuan-su-by-chen-li-guan/
     * @param array
     * @param k
     * @return
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun topKFrequent(array: IntArray, k: Int): MutableList<Int> {
        val res = mutableListOf<Int>()
        if (array.isEmpty() || k == 0) return res

        // 1. 使用map统计每个元素出现的次数：元素为键，元素出现的次数为值
        val map = mutableMapOf<Int, Int>()
        for (num in array) {
            if (map.containsKey(num)) {
                map[num] = map[num]!! + 1
            } else {
                map[num] = 1
            }
        }

        // 2.1 优先堆队列：按升序用小顶堆保存次数最大的k个元素
        val heap = PriorityQueue(Comparator<Int> { o1, o2 -> map[o1]!! - map[o2]!! })
        for (key in map.keys) {
            if (heap.size < k) {
                // 2.2 如果堆的元素个数小于k：就可以直接插入堆中
                heap.offer(key)
            } else if (map[key]!! > map[heap.peek()]!!) {
                // 2.3 如果堆的元素个数等于k：如果新元素的次数比堆顶端的元素大，则弹出堆顶端的元素，将新的元素添加进堆中
                heap.poll()
                heap.offer(key)
            }
        }

        // 3. 最小堆中的k个元素即为前k个高频元素，遍历取出堆中的元素
        for (i in 0 until k) {
            res.add(heap.poll())
        }

        return res
    }


    /**
     * 40. 最小的k个数 - 方法一：大顶堆
     *
     * 时间复杂度：O(nlogk)。维护一个大小为k的小顶堆，每次插入元素的时候，需要的时间是logk；
     *          而整个过程中处理n个元素，最坏的情况就是，每次如果新元素的次数比堆顶端的元素大，
     *          则弹出堆顶端的元素，将新的元素添加进堆中，这个过程总共需要做n次，结果是nlogk
     *
     * 空间复杂度：O(n)，因为小根堆里最多n个数，n是数组的长度。
     *
     * https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/solution/40-zui-xiao-de-kge-shu-by-chen-li-guan/
     * @param array
     * @param k
     * @return
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun getLeastNumbers(array: IntArray, k: Int): IntArray {
        // 从小根堆中取出最小数，取k次
        val res = IntArray(k)
        if (array.isEmpty() || k == 0) return res
        if (array.size <= k) return array

        // 优先堆队列：按降序用小顶堆保存次数最小的k个数
        val heap = PriorityQueue(Comparator<Int> { o1, o2 -> o2 - o1 })
        for (i in array) {
            if (heap.size < k) {
                // 如果堆的元素个数小于k：就可以直接插入堆中
                heap.offer(i)
            } else if (i < heap.peek()) {
                // 如果堆的元素个数等于k：如果新元素的次数比堆顶端的元素大，则弹出堆顶端的元素，将新的元素添加进堆中
                heap.poll()
                heap.offer(i)
            }
        }

        // 最小堆中的k个元素即为最小的k个数，遍历取出堆中的元素
        for (i in 0 until k) {
            res[i] = heap.poll()
        }

        return res
    }

    /**
     * 40. 最小的k个数 - 方法二：快排变形（了解）
     *
     * 思想：寻找最小的 k 个数。假设经过一次 partition 操作，枢纽元素位于下标 m，也就是说，左侧的数组有 m 个元素，是原数组中最小的 m 个数。那么：
     *
     * 时间复杂度：O(n)，由于快速选择只需要递归一边的数组，都是上一次遍历的 1/2，结果是 N + N/2 + N/4 + ... + N/N = 2N, 因此时间复杂度是 O(N)。
     * 空间复杂度：O(logN)，划分函数的平均递归深度为 O(logN)。
     *
     * https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/solution/40-zui-xiao-de-kge-shu-by-chen-li-guan/
     * @param array
     * @param k
     * @return
     */
    fun getLeastNumbers2(array: IntArray, k: Int): IntArray? {
        // 从小根堆中取出最小数，取k次
        val res = IntArray(k)
        if (array.isEmpty() || k == 0) return res
        if (array.size <= k) return array

        // 原地不断划分数组
        partitionArray(array, 0, array.size - 1, k)

        // 数组的前 k 个数此时就是最小的 k 个数，将其存入结果
        for (i in 0 until k) {
            res[i] = array[i]
        }
        return res
    }

    fun partitionArray(array: IntArray, left: Int, right: Int, k: Int) {
        val index = partition(array, left, right)
        if (k == index) {
            // 若 k = index，就找到了最小的 k 个数，就是左侧的数组
            return
        } else if (k < index) {
            // 若 k < index，则最小的 k 个数一定都在左侧数组中，只需要对左侧数组递归地 parition 即可
            partitionArray(array, left, index - 1, k)
        } else {
            // 若 k > index，则左侧数组中的 index 个数都属于最小的 k 个数，还需要在右侧数组中寻找最小的 k-index 个数，对右侧数组递归地 partition 即可
            partitionArray(array, index + 1, right, k)
        }
    }


    /**
     * 215. 数组中的第K个最大元素
     * 思路：1.借助 partition 操作定位到最终排定以后索引为 len - k 的那个元素；
     *      2.partition（切分）操作总能排定一个元素，还能够知道这个元素它最终所在的位置，这样每经过一次 partition（切分）操作就能缩小搜索的范围。
     *
     * 时间复杂度：O(N)，由于快速选择只需要递归一边的数组，都是上一次遍历的 1/2，结果是 N + N/2 + N/4 + ... + N/N = 2N, 因此时间复杂度是 O(N)
     * 空间复杂度：O(1)，原地排序，没有借助额外的辅助空间。
     *
     * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/215-shu-zu-zhong-de-di-kge-zui-da-yuan-s-jrqy/
     * @param nums
     * @param k
     * @return
     */
    fun findKthLargest(array: IntArray, k: Int): Int {
        var left = 0
        var right = array.size - 1

        // 转换一下，第 k 大元素的索引是 len - k
        val target = array.size - k
        while (true) {
            val mid = partition(array, left, right)
            if (mid == target) {
                return array[mid]
            } else if (mid < target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
    }

    /**
     * 划分数组：
     * 定义 counter 是从 left 到 right 元素的位置，最初以 right 作为临时基准位置；
     * 如果存在比 right 的值小的元素，都和 counter 交换，然后+1；遍历结束以后，将 counter 和 right 元素交换，成为新的基准位置；
     * 这样，排序的数据分割成独立的两部分，分在新的基准位置左右。
     *
     * @param array
     * @param left
     * @param right 最初的基准位置
     * @return
     */
    fun partition(array: IntArray, left: Int, right: Int): Int {
        // 定义 counter 是从 left 到 right 元素的位置，最初以 end 作为分区点
        var counter = left

        // 如果存在比 end 的值小的元素，都和 counter 交换，然后+1
        for (i in left until right) {
            if (array[i] < array[right]) {
                // 小于 right 的元素，都和 counter 交换
                swap(array, counter, i)
                // 然后+1
                counter++
            }
        }

        // 遍历结束以后，将 counter 和 end 元素交换，成为新的分区点
        swap(array, counter, right)
        return counter
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }
}
