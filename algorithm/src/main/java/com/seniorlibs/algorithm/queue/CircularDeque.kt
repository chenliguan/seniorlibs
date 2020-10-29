package com.seniorlibs.algorithm.queue

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/9/10
 * Mender:
 * Modify:
 * Description: 641. 设计循环双端队列
 */
class CircularDeque(capacity: Int) {

    class Node(var `val`: Int) {
        var pre: Node? = null
        var next: Node? = null
    }

    /**
     * 总容量
     */
    private var capacity: Int
    /**
     * 当前容量
     */
    private var size: Int = 0
    /**
     * 使用伪头部和伪尾部节点
     */
    private var head: Node = Node(-1)
    private var tail: Node = Node(-1)

    /**
     * 在此初始化您的数据结构。将双端队列的大小设置为k。
     */
    init {
        this.capacity = capacity
        head.next = tail
        tail.pre = head
    }

    /**
     * 在Deque的前面添加一个项目。如果操作成功，则返回true。
     *
     * @param value
     * @return
     */
    fun insertFront(value: Int): Boolean {
        if (isFull()) return false

        val node = Node(value)

        // 处理右指向
        head.next?.pre = node
        node.next = head.next

        // 处理左指向
        node.pre = head
        head.next = node

        size++
        return true
    }

    /**
     * 在Deque的背面添加一个项目。如果操作成功，则返回true。
     *
     * @param value
     * @return
     */
    fun insertLast(value: Int): Boolean {
        if (isFull()) return false

        val node = Node(value)
        // 处理左指向
        tail.pre?.next = node
        node.pre = tail.pre

        // 处理右指向
        tail.pre = node
        node.next = tail

        size++

        return true
    }

    /**
     * 从Deque的前面删除一个项目。如果操作成功，则返回true。
     */
    fun deleteFront(): Boolean {
        if (isEmpty()) return false

        head.next?.next?.pre = head
        head.next = head.next?.next

        size--

        return true
    }

    /**
     * 从Deque的背面删除一个项目。如果操作成功，则返回true。
     */
    fun deleteLast(): Boolean {
        if (isEmpty()) return false

        tail.pre?.pre?.next = tail
        tail.pre = tail.pre?.pre

        size--

        return true
    }

    /**
     * 从双端队列中获取前项。
     */
    fun getFront(): Int {
        return head.next?.`val` ?: 0
    }

    /**
     * 从双端队列中获取最后一个项目。
     */
    fun getRear(): Int {
        return tail.pre?.`val` ?: 0
    }

    /**
     * 检查圆形双端队列是否为空。
     */
    fun isEmpty(): Boolean {
        return size == 0
    }

    /**
     * 检查圆形双端队列是否已满。
     */
    fun isFull(): Boolean {
        return size == capacity
    }
}
