package com.seniorlibs.algorithm.stack

import java.util.*

/**
 * Author: 陈李冠
 * Time: 2021/5/6
 * Description: 225.两个队列实现栈
 *
 * 时间复杂度：入栈操作 O(n)，其余操作都是 O(1)。
 * 空间复杂度：O(n)，其中 n 是栈内的元素。需要使用两个队列存储栈内的元素。
 *
 * https://leetcode-cn.com/problems/implement-stack-using-queues/solution/225-yong-dui-lie-shi-xian-zhan-by-chen-l-q4em/
 */
class QueueStack {

    /*

    其中 queue1 用于存储栈内的元素，queue2 作为入栈操作的辅助队列。

    每次入栈操作都确保 queue1 的前端元素为栈顶元素：
    1.入栈操作时，首先将元素入队到 queue2；
    2.然后将 queue1 的元素全部依次出队，并入队到 queue2，此时 queue2 的前端的元素即为新入栈的元素；
    3.再将 queue1 和 queue2 互换，则 queue1 的元素即为栈内的元素，queue1 的前端和后端分别对应栈顶和栈底。
     */


    var queue1: Queue<Int> = LinkedList<Int>()
    var queue2: Queue<Int> = LinkedList<Int>()

    /**
     * 2 9 5 -> stack -> 5、9、2
     *
     * 2 > queue2
     *  queue1 -> queue2  queue2 -> queue1
     * queue1 = 2
     *
     * 9 > queue2 ，queue1 > 2 ，2 > queue2 = 9、2
     *  queue1 -> queue2  queue2 -> queue1
     * queue1 = 9、2
     *
     * 5 > queue2 ，queue1 > 9、2 ，9、2 > queue2 = 5、9、2
     *  queue1 -> queue2  queue2 -> queue1
     * queue1 = 5、9、2
     *
     * @param x
     */
    fun push(x: Int) {
        queue2.offer(x)
        while (queue1.isNotEmpty()) {
            queue2.offer(queue1.poll())
        }

        val temp = queue1
        queue1 = queue2
        queue2 = temp
    }

    fun pop(): Int {
        return queue1.poll()
    }

    fun top(): Int {
        return queue1.peek()
    }

    fun empty(): Boolean {
        return queue1.isEmpty()
    }
}