package com.seniorlibs.algorithm.other

/**
 * 散列表 + 双向链表
 *
 * 使用双向链表存储数据，链表中的每个结点处理存储数据（data）、前驱指针（prev）、后继指针（next），
 * 和散列表通过链表法解决散列冲突，因此增加一个特殊的字段 hnext。
 *
 * 所以每个结点会在两条链中。一个链是刚刚我们提到的双向链表，另一个链是散列表中的拉链。
 * 前驱和后继指针是为了将结点串在双向链表中，hnext 指针是为了将结点串在散列表的拉链中。
 *
 * 为什么使用双向链表？在删除一个元素时，虽然能 O(1) 的找到目标结点，但是要删除该结点需要拿到前一个结点的指针，遍历到前一个结点复杂度会变为 O(N），所以用双链表实现比较合适。
 * 为什么要在链表中同时存储 key 和 val，而不是只存储 val？当缓存容量已满，我们不仅仅要删除最后一个 Node 节点，还要把 map 中映射到该节点的 key 同时删除，而这个 key 只能由 Node 得到。
 * @constructor Create empty L r u cache
 */
class LRUCache(private val cap: Int) {

    // key -> Node(key, val)
    private val map: HashMap<Int, Node?>

    // Node(k1, v1) <-> Node(k2, v2)...
    private val cache: DoubleList

    init {
        map = HashMap()
        cache = DoubleList()
    }

    /* 将某个 key 提升为最近使用的 */
    private fun makeRecently(key: Int) {
        val x = map[key]
        // 先从链表中删除这个节点
        cache.remove(x)
        // 重新插到队尾
        cache.addLast(x)
    }

    /* 添加最近使用的元素 */
    private fun addRecently(key: Int, `val`: Int) {
        val x = Node(key, `val`)
        // 链表尾部就是最近使用的元素
        cache.addLast(x)
        // 别忘了在 map 中添加 key 的映射
        map[key] = x
    }

    /* 删除某一个 key */
    private fun deleteKey(key: Int) {
        val x = map[key]
        // 从链表中删除
        cache.remove(x)
        // 从 map 中删除
        map.remove(key)
    }

    /* 删除最久未使用的元素 */
    private fun removeLeastRecently() {
        // 链表头部的第一个元素就是最久未使用的
        val deletedNode = cache.removeFirst()
        // 同时别忘了从 map 中删除它的 key
        val deletedKey = deletedNode!!.key
        map.remove(deletedKey)
    }

    operator fun get(key: Int): Int {
        if (!map.containsKey(key)) {
            return -1
        }
        // 将该数据提升为最近使用的
        makeRecently(key)
        return map[key]!!.`val`
    }

    fun put(key: Int, `val`: Int) {
        if (map.containsKey(key)) {
            // 删除旧的数据
            deleteKey(key)
            // 新插入的数据为最近使用的数据
            addRecently(key, `val`)
            return
        }
        if (cap == cache.size()) {
            // 删除最久未使用的元素
            removeLeastRecently()
        }
        // 添加为最近使用的元素
        addRecently(key, `val`)
    }



    internal class Node(var key: Int, var `val`: Int) {
        var next: Node? = null
        var prev: Node? = null
    }

    internal class DoubleList {
        // 头尾虚节点
        private val head: Node
        private val tail: Node

        // 链表元素数
        private var size: Int

        // 在链表尾部添加节点 x，时间 O(1)
        fun addLast(x: Node?) {
            x!!.prev = tail.prev
            x.next = tail
            tail.prev!!.next = x
            tail.prev = x
            size++
        }

        // 删除链表中的 x 节点（x 一定存在）
        // 由于是双链表且给的是目标 Node 节点，时间 O(1)
        fun remove(x: Node?) {
            x!!.prev!!.next = x.next
            x.next!!.prev = x.prev
            size--
        }

        // 删除链表中第一个节点，并返回该节点，时间 O(1)
        fun removeFirst(): Node? {
            if (head.next === tail) return null
            val first = head.next
            remove(first)
            return first
        }

        // 返回链表长度，时间 O(1)
        fun size(): Int {
            return size
        }

        init {
            // 初始化双向链表的数据
            head = Node(0, 0)
            tail = Node(0, 0)
            head.next = tail
            tail.prev = head
            size = 0
        }
    }
}