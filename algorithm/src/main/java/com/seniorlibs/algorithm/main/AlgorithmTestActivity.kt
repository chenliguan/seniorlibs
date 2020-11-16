package com.seniorlibs.algorithm.main

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
                LogUtils.d(TAG, "1一鲍母A-Z的消息通过以下方式进行了编码：${numDecodings("27")}")
            }
            R.id.btn_2 -> {
                val list = listOf("hot", "dot", "dog", "lot", "log", "cog")
                LogUtils.d(
                    TAG,
                    "给定两个单词(beginWord 和endWord)和一个字典：${ladderLength("hit", "cog", list)}"
                )
            }
            else -> {
            }
        }
    }

    fun numDecodings(s: String): Int {
        if (s.isEmpty() || s == "0") return 0
        if (s.length == 1) return 1

        val map = mutableMapOf<Char, Int>()
        for (c in 'A'..'Z') {
            map[c] = c - 'A' + 1
        }

        return decodings(1, s, s.length, map)
    }

    fun decodings(n: Int, s: String, length : Int, map: MutableMap<Char, Int>): Int {
        if (s.isEmpty()) return 1
        if (s[0] == '0') return 0
        if (s.length == 1) return 1

        var num = 0

        if (s.length > 1) {
            // 取1位
            num += decodings(n + 1, s.substring(1, s.length), length, map)
        }

        if (s.length >= 2) {
            // 取2位
            val value = s.substring(0, 2).toInt()
            if (map.containsValue(value)) {
                num += decodings(n + 1, s.substring(2, s.length), length, map)
            } else {
                return num
            }
        }

        return num
    }

    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        if (wordList.isEmpty()) return 0
        if (!wordList.contains(beginWord) || !wordList.contains(endWord)) return 0


        return 0

    }

}
