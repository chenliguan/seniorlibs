package com.seniorlibs.algorithm.sort

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.baselib.utils.LogUtils


/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/11/10
 * Mender:
 * Modify:
 * Description: 排序页面
 */
class SortActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "SortActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, SortActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sort)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_quick_sort).setOnClickListener(this)
        findViewById<View>(R.id.btn_merge_sort).setOnClickListener(this)
        findViewById<View>(R.id.btn_heap_sort).setOnClickListener(this)
        findViewById<View>(R.id.btn_select_sort).setOnClickListener(this)
        findViewById<View>(R.id.btn_insert_sort).setOnClickListener(this)
        findViewById<View>(R.id.btn_bubble_sort).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_quick_sort -> {
                val array = intArrayOf(9, 8, 6, 3, 2, 4)
                quickSort(array, 0, 5)
                LogUtils.e(TAG, "快速排序：${array.asList()}")
            }
            R.id.btn_merge_sort -> {
                val array = intArrayOf(9, 8, 6, 3, 2, 4)
                mergeSort(array, 0, 5)
                LogUtils.e(TAG, "归并排序：${array.asList()}")
            }
            R.id.btn_heap_sort -> {
                val array = intArrayOf(4, 6, 8, 5, 9)
                heapSort(array)
                LogUtils.e(TAG, "堆排序：${array.asList()}")
            }
            R.id.btn_select_sort -> {
                val array = intArrayOf(4, 6, 8, 5, 9)
                selectSort(array)
                LogUtils.e(TAG, "选择排序：${array.asList()}")
            }
            R.id.btn_insert_sort -> {
                val array = intArrayOf(4, 6, 8, 5, 9)
                insertSort(array)
                LogUtils.e(TAG, "插入排序：${array.asList()}")
            }
            R.id.btn_bubble_sort -> {
                val array = intArrayOf(4, 6, 8, 5, 9)
                bubbleSort(array)
                LogUtils.e(TAG, "冒泡排序：${array.asList()}")
            }
            else -> {
            }
        }
    }

    /**
     * 快速排序     9, 8, 6, 3, 2, 4
     *
     * 基本思路：通过一趟排序将，排定一个元素呆在了它最终应该呆的位置，将要排序的数据分割成独立的两部分，
     *          然后递归地去排它左边的部分（比它小的值）和右边的部分（比它大的值），依次进行下去，直到数组有序；
     *
     * 算法思想：分而治之（分治思想），与「归并排序」不同，「快速排序」在「分」这件事情上不想「归并排序」无脑地一分为二，
     *          而是采用了 partition（划分） 的方法，因此就没有「合」的过程。
     *
     * 时间复杂度：O(nlogn)。
     * 空间复杂度：O(logn) 。
     *
     * @param array
     * @param begin
     * @param end
     * @return
     */
    fun quickSort(array: IntArray, begin: Int, end: Int) {
        if (end <= begin) return

        // pivot: 基准位置
        val pivot = partition(array, begin, end)

        // 对两个子序列左边进行快排，直到序列为空或者只有一个元素
        quickSort(array, begin, pivot - 1)

        // 对两个子序列右边进行快排
        quickSort(array, pivot + 1, end)
    }

    /**
     * 划分数组，将要排序的数据分割成独立的两部分，分在最终基准位置左右
     *
     * @param array
     * @param begin
     * @param end 最初的基准位置
     * @return
     */
    private fun partition(array: IntArray, begin: Int, end: Int): Int {
        // counter，指从begin开始，小于end的元素的位置，此时end作为临时基准位置
        var counter = begin
        // 如果存在比end的值小的元素，都和counter交换，然后+1
        for (i in begin until end) {
            if (array[i] < array[end]) {
                val temp = array[counter]
                array[counter] = array[i]
                array[i] = temp
                counter++
            }
        }

        // 最后，将+1后counter 和 end元素 交换，成为新的基准位置
        val temp = array[end]
        array[end] = array[counter]
        array[counter] = temp
        return counter
    }

    /**
     * 归并排序（Merge Sort）  采用分治法的一个非常典型的应用
     * 思路：1.把长度为n的输入序列分成两个长度为n/2的子序列；
     *      2.对这两个子序列分别采用归并排序；
     *      3.将两个排序好的子序列合并成一个最终的排序序列。
     *
     * 时间复杂度：O(n * log n)，这里n是数组的长度：
     * 空间复杂度：O(n)，辅助数组与输入数组规模相当。
     *
     * @param array
     * @param left
     * @param right
     */
    fun mergeSort(array: IntArray, left: Int, right: Int) {
        if (right <= left) return

        val mid = (left + right) shr 1 // 右移 (left + right) / 2

        mergeSort(array, left, mid)
        mergeSort(array, mid + 1, right)

        merge(array, left, mid, right)
    }

    fun merge(array: IntArray, left: Int, mid: Int, right: Int) {
        // 临时数组
        val temp = IntArray(right - left + 1)
        var i = left
        var j = mid + 1
        var k = 0

        // 比较两个小数组相应下标位置的数组大小，小的先放进缓存数组
        while (i <= mid && j <= right) {
            temp[k++] = if (array[i] <= array[j]) array[i++] else array[j++]
        }

        // 如果左边还有数据需要拷贝，把左边数组剩下的拷贝到缓存数组
        while (i <= mid) temp[k++] = array[i++]
        // 如果右边还有数据需要拷贝，把左边数组剩下的拷贝到缓存数组
        while (j <= right) temp[k++] = array[j++]
        // 将缓存数组中的内容复制回原数组
        for (p in temp.indices) {
            array[left + p] = temp[p]
        }
    }


    /**
     * 堆排序（heap sort）
     *
     * 核心思想：先将待排序的序列建成大根堆，使得每个父节点的元素大于等于它的子节点。此时整个序列最大值即为堆顶元素，
     *          我们将其与末尾元素交换，使末尾元素为最大值，然后再调整堆顶元素使得剩下的 n-1n−1 个元素仍为大根堆，再重复执行以上操作我们即能得到一个有序的序列
     *
     * 时间复杂度：O(nlogn)。初始化建堆的时间复杂度为O(n)，建完堆以后需要进行n−1次调整，一次调整（即maxHeapify） 的时间复杂度为O(logn)，
     *                     那么n−1次调整即需要O(nlogn)的时间复杂度。因此，总时间复杂度为O(n+nlogn)=O(nlogn)；
     * 空间复杂度：O(1)。只需要常数的空间存放若干变量。
     *
     * @param array
     */
    fun heapSort(array: IntArray) : IntArray {
        if (array.size <= 1) return array

        // 建立最大堆：遍历父节点，索引为的父结点的索引是(i-1)/2，假设有5个
        for (i in (array.size - 1) / 2 downTo 0) {
            // 调整大堆，只需遍历 i = 1、0
            maxHeap(array, array.size, i)
        }

        // 排序：每次忽略最后一个最大的值，假设有5个，i = 4、3、2、1
        for (i in array.size - 1 downTo 1) {
            // 最大的在0位置，那么开始沉降，这样每交换一次最大的值就丢到最后了
            val temp = array[0]
            array[0] = array[i]
            array[i] = temp
            // 调整大堆
            maxHeap(array, i, 0)
        }

        return array
    }

    /**
     * 调整大堆
     *
     * @param array 堆数组
     * @param length 表示用于构造大堆的数组长度元素数量
     * @param index 从哪位置开始
     */
    private fun maxHeap(array: IntArray, length: Int, index: Int) {
        // 左/右节点：索引为i的左孩子的索引是(2*i+1)，索引为i的左孩子的索引是(2*i+2)
        val left = index * 2 + 1
        val right = index * 2 + 2
        // 目标序号：父节点，largest == 变化的值
        var largest = index

        // left < length。左节点大于根节点，将左序号赋值为目标序号
        if (left < length && array[left] > array[index]) largest = left

        // 右节点大于根节点（新目标序号），将右序号赋值为目标序号
        if (right < length && array[right] > array[largest]) largest = right

        // 目标序号元素不是最大值
        if (index != largest) {
            // 数据交换
            val temp = array[index]
            array[index] = array[largest]
            array[largest] = temp

            // 继续调整大堆
            maxHeap(array, length, largest)
        }
    }


    /**
     * 选择排序
     * 思想：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置；
     *      然后，再从剩余未排序元素中继续寻找最小（大）元素；最后放到 前面已排序数组的 末尾。
     *
     * 时间复杂度：O(n^2)，这里n是数组的长度；
     * 空间复杂度：O(1)，使用到常数个临时变量。
     *
     * @param array
     * @return
     */
    fun selectSort(array: IntArray) : IntArray {
        if (array.size <= 1) return array

        // i = 1 逐步递增，是指 前面已排序数组的 最后一个位置
        for (i in array.indices) {
            var minIndex = i
            // 从下一个位置后，找到最小数的下标
            for (j in i + 1 until array.size) {
                // 将最小数的索引保存
                if (array[j] < array[minIndex]) minIndex = j
            }

            // 最后交换元素
            val temp = array[i]
            array[i] = array[minIndex]
            array[minIndex] = temp
        }

        return array
    }


    /**
     * 插入排序
     * 思想：将一个数字插入 前面已排序的数组，不使用逐步交换，使用先赋值给「临时变量」；
     *      然后把不适合的元素逐个后移；最后空出一个位置，把「临时变量」赋值给这个空位；
     *
     * 时间复杂度：O(n^2)，这里n是数组的长度；
     * 空间复杂度：O(1)，使用到常数个临时变量。
     *
     * @param array
     * @return
     */
    fun insertSort(array: IntArray) : IntArray {
        if (array.size <= 1) return array

        // i = 1 逐步递增，是指 前面已排序数组的 后一个位置
        for (i in 1 until array.size) {
            // 先临时暂存这个变量
            val temp = array[i]
            var pre = i - 1
            // 然后前面比插入元素大的值逐个后移，空出一个位置
            while(pre >= 0 && temp < array[pre]) {
                array[pre + 1] = array[pre]
                pre--
            }

            // 最后把「临时变量」赋值给空位
            array[pre + 1] = temp
        }

        return array
    }

    /**
     * 冒泡排序  超时
     * 思想：进行 n-1 趟比较并交换，对相邻的元素进行两两比较，大小不相等则进行交换，每一趟会将最小或最大的元素“冒”到 后面已排序数组 前面
     *
     * 时间复杂度：O(n^2)，这里n是数组的长度；
     * 空间复杂度：O(1)，使用到常数个临时变量。
     *
     * @param array
     * @return
     */
    fun bubbleSort(array: IntArray): IntArray {
        if (array.size <= 1) return array

        // array.size - 1 逐步递减，是指 后面已排序数组 前一个位置
        for (i in array.size - 1 downTo 0) {
            var sorted = true
            for (j in 0 until i) {
                // 相邻元素两两对比
                if (array[j] > array[j + 1]) {
                    // 元素交换
                    val temp = array[j]
                    array[j] = array[j + 1]
                    array[j + 1] = temp

                    sorted = false
                }
            }
            if (sorted) break
        }

        return array
    }

}