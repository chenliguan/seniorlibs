package com.seniorlibs.algorithm.string

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.baselib.utils.LogUtils

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/9/10
 * Mender:
 * Modify:
 * Description: 字符串
 */
class StringActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "StringActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, StringActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_string)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_to_lower_case).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_to_lower_case -> {
                LogUtils.e(TAG, "709. 转换成小写字母1：" + toLowerCase("Hello"))
                LogUtils.e(TAG, "709. 转换成小写字母2：" + toLowerCase1("Hello"))
            }
            else -> {
            }
        }
    }

    /**
     * 709. 转换成小写字母  方法1：ASCCII码
     *
     * 通过 ASCII 码表操作字符串即可：SCII码表中大写的A是65，小写的a是97，它们的差是32
     * a-z：97-122
     * A-Z：65-90
     * 0-9：48-57
     *
     * @param str
     * @return
     */
    fun toLowerCase(str: String): String? {
        if (str.isEmpty()) return str

        val ch = str.toCharArray()
        for (i in str.indices) {
            if (ch[i] in 'A'..'Z') {
                ch[i] = ch[i] + 32
            }
        }
        return String(ch)
    }

    /**
     * 709. 转换成小写字母  方法2：位运算
     *
     * 用位运算的技巧：65 | 32 转为二进制（按8位来算）可以得到 0100 0001 | 0010 0000 = 0110 0001 = 97 = a
     * 大写变小写、小写变大写：字符 ^= 32;
     * 大写变小写、小写变小写：字符 |= 32;
     * 大写变大写、小写变大写：字符 &= 33;
     *
     * @param str
     * @return
     */
    fun toLowerCase1(str: String): String? {
        if (str.isEmpty()) return str

        val ch = str.toCharArray()
        for (i in str.indices) {
            ch[i] = (ch[i].toInt() or 32).toChar()
        }
        return String(ch)
    }
}
