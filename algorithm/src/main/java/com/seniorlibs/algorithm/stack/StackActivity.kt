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
 * 栈
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
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_is_valid -> {
                val valid = isValid("]")
                LogUtils.e(TAG, "有效的括号：${valid}")
            }

            else -> {
            }
        }
    }

    /**
     * 20. 有效的括号
     *
     * @param s
     * @return
     */
    fun isValid(s: String): Boolean {
        val stack = Stack<Char>()
        for (c in s.toCharArray()) {
            if (c == '(') {
                stack.push(')')
            } else if (c == '{') {
                stack.push('}')
            } else if (c == '[') {
                stack.push(']')
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false
            }
        }

        return stack.isEmpty()
    }
}
