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
                obj.push(1)
                obj.push(-1)
                obj.push(2)
                obj.pop()
                val param_3 = obj.top()
                val param_4 = obj.getMin()
                LogUtils.d(TAG, "155. 最小栈：${param_3} ${param_4}")
            }
            R.id.btn_eval_rpn -> {
                LogUtils.d(TAG, "150. 逆波兰表达式求值：${evalRPN(arrayOf("2","1","+","3","*"))}")
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
    fun evalRPN(array: Array<String>): Int {
        val stack: Deque<Int> = LinkedList()
        for (s in array) {
            if ("+-*/".contains(s)) {
                // 遇到运算符时，从栈中取出两个数进行运算，并将结果放回栈内
                val b = stack.pollLast()  // 1，后进先出
                val a = stack.pollLast()  // 2
                val result = calc(a, b, s)
                stack.addLast(result)
            } else {
                // 入栈所有的数字
                stack.addLast(s.toInt())
            }
        }
        // 整个过程结束后，栈顶元素就是最终结果，弹出
        return stack.pollLast()
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
}
