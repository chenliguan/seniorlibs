package com.seniorlibs.algorithm.stack

import java.util.*

/**
 * Author: 陈李冠
 * Time: 2021/5/6
 * Description: 232. 用栈实现队列
 *
 * 时间复杂度：push 和 empty 为 O(1)，pop 和 peek 为均摊 O(1)。
 * 空间复杂度：O(n)。对于有 n 次 push 操作的情况，队列中会有 n 个元素，故空间复杂度为 O(n)。
 *
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/solution/232-yong-zhan-shi-xian-dui-lie-by-chen-l-vmu0/
 */
class StackQueue {

    /**

    用两个栈来模拟队列的特性，一个栈为入队栈，一个栈为出对栈。
    当出队栈存在内容时，出队栈的栈顶，即为第一个出队的元素。
    若出队栈无元素，又需要出队的话，就需要将入队栈的全部数据反序导入出队栈，然后弹出栈顶的元素即可。

     */


    var inStack = LinkedList<Int>()
    var outStack = LinkedList<Int>()

    fun push(x: Int) {
        inStack.push(x)
    }

    fun pop(): Int {
        if (outStack.isEmpty()) {
            in2out()
        }
        return outStack.pop()
    }

    fun peek(): Int {
        if (outStack.isEmpty()) {
            in2out()
        }
        return outStack.peek()
    }

    fun empty(): Boolean {
        return inStack.isEmpty() && outStack.isEmpty()
    }

    private fun in2out() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop())
        }
    }


    // 入队，往第一个栈压入值
    fun appendTail(value: Int) {
        inStack.push(value)
    }

    // 出队
    fun deleteHead(): Int {
        if (outStack.isEmpty()) {
            in2out()
        }
        return outStack.pop()
    }
}