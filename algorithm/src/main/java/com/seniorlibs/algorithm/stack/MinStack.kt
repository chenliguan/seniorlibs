package com.seniorlibs.algorithm.stack

import java.util.*

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/9/23
 * Mender:
 * Modify:
 * Description: 155. 最小栈
 */
class MinStack {

    /**
     * 思想：借用一个辅助栈 minStack，用于存获取栈中最小值。
     *
     * 时间复杂度 O(1) ：压栈，出栈，获取最小值的时间复杂度都为 O(1)。
     * 空间复杂度 O(N) ：包含 N 个元素辅助栈占用线性大小的额外空间。
     *
     * https://leetcode-cn.com/problems/min-stack/solution/155-zui-xiao-zhan-by-chen-li-guan-unbp/
     */

    private var stack = LinkedList<Int>()
    private var minStack = LinkedList<Int>()

    /**
     * 每当 push() 新值进来时，如果 小于等于 minStack 栈顶值，则一起 push() 到 minStack，即更新了栈顶最小值；
     *
     * @param x
     */
    fun push(x: Int) {
        stack.push(x)

        if(minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x)
        }
    }

    /**
     * 判断将 pop() 出去的元素值是否是 minStack 栈顶元素值（即最小值），如果是则将 minStack 栈顶元素一起 pop()，保证 minStack 栈顶元素始终是栈中的最小值。
     */
    fun pop() {
        if(stack.pop() == minStack.peek()) {
            minStack.pop()
        }
    }

    /**
     * 获取栈顶元素，不删除
     *
     * @return
     */
    fun top(): Int {
        return stack.peek()
    }

    /**
     * 栈中的最小元素，不删除
     *
     * @return
     */
    fun getMin(): Int {
        return minStack.peek()
    }
}