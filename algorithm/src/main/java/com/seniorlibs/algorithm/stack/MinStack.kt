package com.seniorlibs.algorithm.stack

import java.util.*

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/9/23
 * Mender:
 * Modify:
 * Description: 最小栈
 */
class MinStack {

    private var xStack = LinkedList<Int>()
    private var minStack = LinkedList<Int>()

    init {
        xStack.push(Int.MAX_VALUE)
        minStack.push(Int.MAX_VALUE)
    }

    fun push(x: Int) {
        xStack.push(x)
        minStack.push(Math.min(minStack.peek(), x))
    }

    fun pop() {
        xStack.pop()
        minStack.pop()
    }

    fun top(): Int {
        return xStack.peek()
    }

    fun getMin(): Int {
        return minStack.peek()
    }
}