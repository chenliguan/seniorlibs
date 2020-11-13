package com.seniorlibs.algorithm.heap

import java.util.*
import kotlin.NoSuchElementException

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/9/26
 * Mender:
 * Modify:
 * Description: 二叉堆实现
 */
class BinaryHeap(capacity: Int) {

    private val d = 2
    private var heap: IntArray
    private var heapSize = 0

    /**
     * 这将使用默认大小初始化我们的堆。
     */
    init {
        heapSize = 0
        heap = IntArray(capacity + 1)
        Arrays.fill(heap, -1)
    }

    fun isEmpty(): Boolean {
        return heapSize == 0
    }


    fun isFull(): Boolean {
        return heapSize == heap.size
    }

    /**
     * 父节点，索引为的父结点的索引是(i-1)/2
     *
     * @param i
     * @return
     */
    private fun parent(i: Int): Int {
        return (i - 1) / d
    }

    /**
     * 左/右节点：索引为i的左孩子的索引是(2*i+1)，索引为i的左孩子的索引是(2*i+2)
     *
     * @param i
     * @param k
     * @return
     */
    private fun kthChild(i: Int, k: Int): Int {
        return d * i + k
    }

    /**
     * 在堆中插入新元素
     * 时间复杂性: O(log N)，在最坏的情况下，我们需要遍历到根本
     */
    fun insert(x: Int) {
        if (isFull()) throw NoSuchElementException("Heap is full, No space to insert new element")

        heap[heapSize] = x
        heapSize++
        heapifyUp(heapSize - 1)
    }

    /**
     * 删除索引为x的元素
     * Complexity: O(log N)
     */
    fun delete(x: Int): Int {
        if (isEmpty()) throw NoSuchElementException("Heap is empty, No element to delete")

        val maxElement = heap[x]
        heap[x] = heap[heapSize - 1]
        heapSize--
        heapifyDown(x)
        return maxElement
    }

    /**
     * 插入元素时保持堆属性
     *
     * @param i
     */
    private fun heapifyUp(i: Int) {
        var i = i
        val insertValue = heap[i]
        while (i > 0 && insertValue > heap[parent(i)]) {
            heap[i] = heap[parent(i)]
            i = parent(i)
        }
        heap[i] = insertValue
    }

    /**
     * 在删除元素时保持堆属性
     *
     * @param i
     */
    private fun heapifyDown(i: Int) {
        var i = i
        var child: Int
        val temp = heap[i]
        while (kthChild(i, 1) < heapSize) {
            child = maxChild(i)
            if (temp >= heap[child]) break
            
            heap[i] = heap[child]
            i = child
        }
        heap[i] = temp
    }

    private fun maxChild(i: Int): Int {
        val leftChild = kthChild(i, 1)
        val rightChild = kthChild(i, 2)
        return if (heap[leftChild] > heap[rightChild]) leftChild else rightChild
    }

    /**
     * 打印堆的所有元素
     */
    fun printHeap() {
        print("nHeap = ")
        for (i in 0 until heapSize) print(heap[i].toString() + " ")
        println()
    }

    /**
     * 此方法返回堆的max元素。
     * complexity: O(1)
     */
    fun findMax(): Int {
        if (isEmpty()) throw NoSuchElementException("Heap is empty.")
        return heap[0]
    }
}