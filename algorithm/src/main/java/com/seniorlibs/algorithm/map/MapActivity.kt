package com.seniorlibs.algorithm.map

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.baselib.utils.LogUtils
import java.util.*
import kotlin.collections.ArrayList

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/9/10
 * Mender:
 * Modify:
 * Description: 哈希表
 */
class MapActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "MapActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, MapActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_is_anagram).setOnClickListener(this)
        findViewById<View>(R.id.btn_is_anagram_groups).setOnClickListener(this)
        findViewById<View>(R.id.btn_is_fizz_buzz).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_is_anagram -> {
                LogUtils.e(TAG, "242. 有效的字母异位词：" + isAnagram("a", "b"))
                LogUtils.e(TAG, "242. 有效的字母异位词：" + isAnagram1("a", "b"))
            }

            R.id.btn_is_anagram_groups -> {
                LogUtils.e(TAG, "49. 字母异位词分组：" + groupAnagrams(arrayOf("eat", "tea", "tan", "ate", "nat", "bat")))
            }

            R.id.btn_is_fizz_buzz -> {
                LogUtils.e(TAG, "412. Fizz buzz：" + fizzBuzz(15))
            }

            else -> {
            }
        }
    }

    /**
     * 242. 有效的字母异位词：哈希表
     *
     * @param s
     * @param t
     * @return
     */
    private fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val sArray = s.toCharArray()
        val tArray = t.toCharArray()

        val array = IntArray(26)
        for (c in sArray) {
            array[c - 'a']++
        }
        for (c in tArray) {
            array[c - 'a']--
        }

        for (i in array) if (i > 0) return false

        return true
    }

    /**
     * 242. 有效的字母异位词：排序
     *
     * @param s
     * @param t
     * @return
     */
    private fun isAnagram1(s: String, t: String): Boolean {
        if (s.length != t.length) {
            return false
        }

        val sChar = s.toCharArray()
        val tChar = t.toCharArray()
        Arrays.sort(sChar)
        Arrays.sort(tChar)

        return sChar.contentEquals(tChar)
    }

    /**
     * 49. 字母异位词分组
     *
     * @param strs
     * @return
     */
    private fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val res : MutableList<List<String>> = mutableListOf()
        if (strs.isEmpty()) return res

        val map = mutableMapOf<String, MutableList<String>>()
        for (str in strs) {
            val sArray = str.toCharArray()
            val array = IntArray(26)
            for (c in sArray) {
                array[c - 'a']++
            }

            // 将每个字符串s转换为字符数组count-->key，由26个非负整数组成，表示a，b，c的数量。"eat", "tea"的key是相等的
            val sb = StringBuilder()
            for (c in array) {
                sb.append("#${c}")
            }

            val key = sb.toString()
            if (!map.containsKey(key)) {
                map[key] = mutableListOf()
            }
            map[key]!!.add(str)
        }

        return map.values.toList()
    }

    /**
     * 412. Fizz buzz
     *
     * @param n
     * @return
     */
    private fun fizzBuzz(n: Int): List<String> {
        val map = mapOf(3 to "Fizz", 5 to "Buzz")
        val res = mutableListOf<String>()

        for (i in 1 until n + 1) {  // 排除0 % 3 == 0
            var value = ""
            for (key in map.keys) {
                if (i % key == 0) {
                    value += map[key]
                }
            }

            if (value.isEmpty()) {
                value += i
            }

            res.add(value)
        }

        return res
    }
}
