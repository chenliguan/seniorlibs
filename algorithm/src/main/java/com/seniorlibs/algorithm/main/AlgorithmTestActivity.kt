package com.seniorlibs.algorithm.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.array.ArrayActivity
import com.seniorlibs.algorithm.db.DbActivity
import com.seniorlibs.algorithm.db.DbActivity2
import com.seniorlibs.baselib.utils.LogUtils
import java.util.*

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/9/26
 * Mender:
 * Modify:
 * Description: 混合测试
 */
class AlgorithmTestActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "AlgorithmTestActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, AlgorithmTestActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_algorithm_test)

        lifecycle
        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_1).setOnClickListener(this)

    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_1 -> {
                LogUtils.d(ArrayActivity.TAG, "3. 无重复字符的最长子串：${lengthOfLongestSubstring("pwwkew")}")
            }
            else -> {
            }
        }
    }


    /**
     * 3. 无重复字符的最长子串
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param s
     * @return
     */
    fun lengthOfLongestSubstring(s: String): Int {
        if (s.isEmpty()) return 0

        val map = mutableMapOf<Char, Int>()
        var left = 0
        var res = 0

        for (i in 0 until s.length) {
            if (map.containsKey(s[i])) {
                left = Math.max(left, map[s[i]]!!)
            }

            map[s[i]] = i + 1
            res = Math.max(res, i - left + 1)
        }

        return res
    }


    private fun findTheDifference(s: String, t: String): Char {
        var res = t[t.length - 1].toInt()

        for (i in 0 until s.length) {
            res = res xor s[i].toInt()
            res = res xor t[i].toInt()
        }

        return res.toChar()
    }

    fun hammingWeight1(n: Int): Int {
        var n = n
        var num = 0

        while (n != 0) {
            num++

            n = n and n - 1
        }

        return num
    }
}









