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

            else -> {
            }
        }
    }


    /**
     * 20. 有效的括号
     *
     * 思想：如果输入是 ( ，入栈 )，当输入 ) 时弹出栈中的 (，对比是否相等。不等直接返回false退出
     *      如果输入是 ) ，此时栈为空，以后也无法再匹配此 ) ，直接返回false退出；
     *      如果连续输入是 ((，最后栈不为空，返回结果false；
     *
     * 时间复杂度O(n)：正确的括号组合需要遍历一遍s；
     * 空间复杂度O(n)：栈使用线性的空间大小。
     *
     * @param s
     * @return
     */
    fun isValid(s: String): Boolean {
        val stack = Stack<Char>()
        val array = s.toCharArray()
        array.forEach {
            if (it == '(') {
                stack.push(')')
            } else if (it == '{') {
                stack.push('}')
            } else if (it == '[') {
                stack.push(']')
            } else if (stack.isEmpty() || stack.pop() != it) {
                return false
            }
        }

        return stack.isEmpty()
    }
}
