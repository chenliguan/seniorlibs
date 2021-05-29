package com.seniorlibs.algorithm.stack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.baselib.utils.LogUtils
import java.util.*

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/9/10
 * Mender:
 * Modify:
 * Description: 栈
 */
class StackActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "StackActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, StackActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_is_valid).setOnClickListener(this)
        findViewById<View>(R.id.btn_min_stack).setOnClickListener(this)
        findViewById<View>(R.id.btn_eval_rpn).setOnClickListener(this)
        findViewById<View>(R.id.btn_remove_k_digits).setOnClickListener(this)
        findViewById<View>(R.id.btn_daily_temperatures).setOnClickListener(this)
        findViewById<View>(R.id.btn_find_max).setOnClickListener(this)
        findViewById<View>(R.id.btn_find_132_pattern).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_is_valid -> {
                val valid = isValid("]")
                LogUtils.d(TAG, "20. 有效的括号：${valid}")
            }
            R.id.btn_min_stack -> {
                val obj = MinStack()
                obj.push(1)
                obj.push(3)
                obj.push(-2)
                obj.push(1)
                obj.push(-1)
                obj.push(2)
                obj.pop()
                val param_3 = obj.top()
                val param_4 = obj.getMin()
                LogUtils.d(TAG, "155. 最小栈：${param_3} ${param_4}")
            }
            R.id.btn_eval_rpn -> {
                LogUtils.d(TAG, "150. 逆波兰表达式求值：${evalRPN(arrayOf("2", "1", "+", "3", "*"))}")
            }
            R.id.btn_remove_k_digits -> {
                LogUtils.d(TAG, "402. 移掉K位数字：${removeKdigits("1432219", 3)}")
            }
            R.id.btn_daily_temperatures -> {
                LogUtils.d(
                    TAG,
                    "739. 每日温度：${dailyTemperatures(intArrayOf(73, 74, 75, 71, 69, 72, 76, 73))}"
                )
            }
            R.id.btn_find_max -> {
                LogUtils.d(TAG, "找到数组中, 比左边所有数字都小, 比右边所有数字都大的数字：${findMax(intArrayOf(3, 3, 1))}")
            }
//            R.id.btn_queue_stack -> {
//                LogUtils.d(TAG, "225.两个队列实现栈")
//            }
//            R.id.btn_stack_queue -> {
//                LogUtils.d(TAG, "232. 用栈实现队列")
//            }
//            R.id.btn_stack_queue -> {
//                LogUtils.d(TAG, "232. 用栈实现队列")
//            }
            R.id.btn_find_132_pattern -> {
                LogUtils.d(TAG, "456. 132 模式：${find132pattern(intArrayOf(3, 1, 4, 2))}")
            }
            else -> {
            }
        }
    }


    /**
     * 20. 有效的括号
     *
     * 思想：遍历所有的元素，当扫描到左括号时，则将它对应的右括号压栈。当扫描到右括号时，从栈顶取出元素。如果能够匹配，则继续扫描剩下的字符串。
     *
     * 时间复杂度O(n)：正确的括号组合需要遍历一遍s；
     * 空间复杂度O(n)：栈使用线性的空间大小。
     *
     * @param s
     * @return
     */
    fun isValid(s: String): Boolean {
        val stack = LinkedList<Char>()
        for (char in s) {
            // 如果是左括号，就把他们对应的右括号压栈
            if (char == '(') {
                stack.push(')')
            } else if (char == '{') {
                stack.push('}')
            } else if (char == '[') {
                stack.push(']')
            } else if (stack.isEmpty() || char != stack.pop()) {
                // 否则就只能是右括号。
                // 1.如果栈为空，说明括号无法匹配。
                // 2.如果栈不为空，栈顶元素就要出栈，和这个右括号比较。
                // 如果栈顶元素不等于这个右括号，说明无法匹配，直接返回false。
                return false
            }
        }

        // 最后如果栈为空，说明完全匹配，是有效的括号。否则不完全匹配，就不是有效的括号
        return stack.isEmpty()
    }

    /**
     * 150. 逆波兰表达式求值
     * 思想：建立一个「数字栈」，存放所有的数字，当遇到运算符时，从栈中取出两个数进行运算，并将结果放回栈内。整个过程结束后，栈顶元素就是最终结果。
     *
     * ["2","1","+","3","*"] ————> ((2 + 1) * 3) = 9
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/solution/150-ni-bo-lan-biao-da-shi-qiu-zhi-by-che-g469/
     * @param array
     * @return
     */
    fun evalRPN(ts: Array<String>): Int {
        val stack = LinkedList<Int>()
        for (s in ts) {
            if ("+-*/".contains(s)) {
                // 遇到运算符时，从栈中取出两个数进行运算，并将结果放回栈内
                val b = stack.pop()  // 1，后进先出
                val a = stack.pop()  // 2
                val result = calc(a, b, s)
                stack.push(result)
            } else {
                // 入栈所有的数字
                stack.push(s.toInt())
            }
        }
        // 整个过程结束后，栈顶元素就是最终结果，弹出
        return stack.pop()
    }

    fun calc(a: Int, b: Int, op: String): Int {
        return if (op == "+") {
            a + b
        } else if (op == "-") {
            a - b
        } else if (op == "*") {
            a * b
        } else if (op == "/") {
            a / b
        } else {
            -1
        }
    }


    /**
     * 什么时候用单调栈：要给当前的元素，找右边/左边第一个比它大/小的位置。
     * 单调递增栈，利用波谷剔除栈中的波峰，留下波谷；
     * 单调递减栈，利用波峰剔除栈中的波谷，留下波峰。
     */

    /**
     * 402. 移掉K位数字
     *
     * 思想：尽量维护高位的递增，这样更小，所以维护单调递增栈。
     *
     * 1432219 这样「高位递增」的数，肯定不会想删高位，高位肯定想尽量小，会尽量删低位。= 1219
     * 4321351 这样「高位递减」的数，会想干掉高位，直接让高位变小，效果很好。= 1351
     *
     * b.如果当前遍历的数比栈顶大，符合递增，就是满意的，入栈。
     * a.如果当前遍历的数比栈顶小，立马删掉栈顶的数，不管后面有没有更大的。因为栈顶的数在高位，删掉它，小的顶上，高位变小，变小的幅度大于低位变小。
     *
     * "1432219"  k = 3
     * bottom[1       ]top		1入
     * bottom[1 4     ]top		4入
     * bottom[1 3     ]top	4出	3入
     * bottom[1 2     ]top	3出	2入
     * bottom[1 2 2   ]top		2入
     * bottom[1 2 1   ]top	2出	1入	出栈满3个，停止出栈
     * bottom[1 2 1 9 ]top		9入
     *
     * bottom[1 2 1 9 ]top  --> 从底部弹出，1-2-1-9
     *
     * 时间复杂度：O(n)；
     * 空间复杂度：O(n)。栈存储数字需要线性的空间。
     *
     * https://leetcode-cn.com/problems/remove-k-digits/solution/402-yi-diao-kwei-shu-zi-by-chen-li-guan-bqxq/
     * @param str
     * @param k
     * @return
     */
    fun removeKdigits(str: String, k: Int): String {
        var k = k
        val stack = LinkedList<Char>()
        var res = ""

        // 遍历 str 字符串
        for (c in str) {
            // a.如果当前遍历的数比栈顶小，立马删掉栈顶的数，不管后面有没有更大的。只要 k>0 且当前的 c 比栈顶的小，则栈顶出栈，k--
            while (k > 0 && stack.isNotEmpty() && c < stack.peek()) {
                stack.pop()
                k--
            }

            // b.如果当前遍历的数比栈顶大，符合递增，就是满意的。不是"0"或栈非空（避免0入空栈），入栈
            if (c != '0' || stack.isNotEmpty()) {
                stack.push(c)
            }
        }

        // 如果还没删够，要从 stack 继续删，直到 k=0
        while (k > 0 && stack.isNotEmpty()) {
            stack.pop()
            k--
        }

        // 如果栈空了，返回"0"，如果栈非空，转成字符串返回
        if (stack.isEmpty()) return "0"

        while (stack.isNotEmpty()) {
            res += stack.pollLast()
        }
        return res
    }

    /**
     * 739. 每日温度
     * 题目：对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。给定一个列表 [73, 74, 75, 71, 69, 72, 76, 73]，输出是 [1, 1, 4, 2, 1, 1, 0, 0]。
     *
     * 存储下标的单调递减栈，从栈底到栈顶的下标对应的温度列表中的温度依次递减。如果一个下标在单调栈里，则表示尚未找到下一次温度更高的下标。
     *
     * 时间复杂度：O(n)；正向遍历温度列表一遍，对于温度列表中的每个下标，最多有一次进栈和出栈的操作。
     * 空间复杂度：O(n)，其中 n 是温度列表的长度。需要维护一个单调栈存储温度列表中的下标。
     *
     * https://leetcode-cn.com/problems/daily-temperatures/solution/739-mei-ri-wen-du-by-chen-li-guan-i0u2/
     * @param T
     * @return
     */
    fun dailyTemperatures(T: IntArray): IntArray? {
        val stack = LinkedList<Int>()
        val res = IntArray(T.size)

        for (i in 0 until T.size) {
            // 如果当前遍历的数比栈顶大，立马弹出栈顶计算天数，直到栈为空
            while (stack.isNotEmpty() && T[i] > T[stack.peek()]) {
                val prevIndex = stack.pop()
                res[prevIndex] = i - prevIndex
            }

            stack.push(i)
        }

        while (stack.isNotEmpty()) {
            // 这里可以删掉
            res[stack.pop()] = 0
        }
        return res
    }


    /**
     * 找到数组中, 比左边所有数字都小, 比右边所有数字都大的数字
     *
     * 思路：维护一个单调递减栈，同时记录已遍历的最小值。遍历数组，对于数组当前值，小于最小值，则入栈，同时更新最小值；否则，出栈直到栈顶元素大于当前值。
     *
     * input:  9,8,7,3,4,2,1
     * output: 9,8,7,2,1
     *
     * input:  3,3,1
     * output: 1
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(n)。
     */
    fun findMax(array: IntArray) {
        val stack = LinkedList<Int>()
        var minLeft = Int.MAX_VALUE

        for (num in array) {
            if (num < minLeft) {
                minLeft = num
                stack.push(num)
            } else {
                while (stack.isNotEmpty() && num >= stack.peek()) {
                    stack.pop()
                }
            }
        }

        while (stack.isNotEmpty()) {
            System.out.println(stack.pop())
        }
    }


    /**
     * 456. 132 模式  方法：使用单调递减栈维护 3。最大元素在栈底，次大元素在栈底的第二元素。eg：3、1、4、2
     * 题意：32 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j]。
     *
     * 时间复杂度：O(n)，由于每一个元素最多被加入和弹出单调栈各一次，因此操作单调栈的时间复杂度一共为 O(n)。
     * 空间复杂度：O(n)，即为单调栈需要使用的空间
     *
     * https://leetcode-cn.com/problems/132-pattern/solution/456-132-mo-shi-by-chen-li-guan-hqgb/
     *
     * @param nums
     * @return
     */
    fun find132pattern(nums: IntArray): Boolean {
        val stack = LinkedList<Int>()
        // stack = [2]
        stack.push(nums[nums.size - 1])

        var twoNumber = Int.MIN_VALUE
        for (i in nums.size - 2 downTo 0) {
            // 4 > 2，twoNumber = 2。1 !> 4
            while (stack.isNotEmpty() && nums[i] > stack.peek()) {
                twoNumber = stack.pop()
            }

            // stack = [4, 1]
            stack.push(nums[i])

            // 4 !< 2。   1 < 2  --> 1,4,2，符合模式
            if (nums[i] < twoNumber) {
                return true
            }
        }
        return false
    }
}
