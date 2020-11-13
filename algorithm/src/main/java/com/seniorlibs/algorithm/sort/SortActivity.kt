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
     * 堆排序
     *
     * 核心思想：（1）将无需序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆；
     * 　　     （2）将堆顶元素与末尾元素交换，将最大元素”沉”到数组末端；
     * 　     　（3）重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
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
        val length: Int = array.size
        for (i in (length - 1) / 2 downTo 0) {
            // 调整大堆，只需遍历 i = 1、0
            maxHeap(array, array.size, i)
        }

        // 排序：每次忽略最后一个最大的值，假设有5个，i = 4、3、2、1
        for (i in length - 1 downTo 1) {
            // 最大的在0位置，那么开始沉降，这样每交换一次最大的值就丢到最后了
            swap(array, 0, i)
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
        // 目标序号：父节点
        var largest = index

        // left < length。左节点大于根节点，将左序号赋值为目标序号
        if (left < length && array[left] > array[index]) largest = left

        // 右节点大于根节点（新目标序号），将右序号赋值为目标序号
        if (right < length && array[right] > array[largest]) largest = right

        // 目标序号元素不是最大值
        if (index != largest) {
            // 数据交换
            swap(array, index, largest)
            // 继续调整大堆
            maxHeap(array, length, largest) // largest==变化的值
        }
    }

    /**
     * 数据交换
     *
     * @param array
     * @param index1
     * @param index2
     */
    fun swap(array: IntArray, index1: Int, index2: Int) {
        val temp = array[index1]
        array[index1] = array[index2]
        array[index2] = temp
    }
}
