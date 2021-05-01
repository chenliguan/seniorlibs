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
     * 时间复杂度：O(n)。其中 n 为 s 的长度。
     * 空间复杂度：O(E)。其中 E 为字符集大小，此处 E=26。
     *
     * https://leetcode-cn.com/problems/valid-anagram/solution/242-you-xiao-de-zi-mu-yi-wei-ci-by-chen-li-guan/
     * @param s
     * @param t
     * @return
     */
    fun isAnagram(s: String, t: String): Boolean {
        if(s.length != t.length) return false

        val array = IntArray(26)

        for (c in s) {
            array[c - 'a']++
        }

        for (c in t) {
            array[c - 'a']--
        }

        for (i in array) {
            if (i > 0) return false
        }

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
     * 思路：当且仅当它们的字符计数（每个字符的出现次数）相同时，两个字符串是字母异位词。
     *
     * 时间复杂度：O(nk)。其中 n 是 strs 中的字符串的数量，k 是 strs 中的字符串的的最大长度。（所以是 O(n(k + 26))，常数忽略后就是 O(nk)。）
     * 空间复杂度：O(nk)。需要用哈希表存储全部字符串。
     *
     * https://leetcode-cn.com/problems/group-anagrams/solution/49zi-mu-yi-wei-ci-fen-zu-by-chen-li-guan/
     * @param strs
     * @return
     */
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val res = mutableMapOf<String, MutableList<String>>()
        if (strs.isEmpty()) return res.values.toList()

        for (str in strs) {
            val array = IntArray(26)
            for (c in str) {
                array[c - 'a']++
            }

            // 将每个字符串s转换为字符数组count-->key，由26个非负整数组成，表示a，b，c的数量。"eat", "tea"的key是相等的
            val sb = StringBuilder()
            for (c in array) {
                sb.append("#${c}")
            }

            // 将字符串添加到 res 中
            val key = sb.toString()
            if (!res.containsKey(key)) {
                res[key] = mutableListOf()
            }
            res[key]?.add(str)
        }

        return res.values.toList()
    }

    /**
     * 412. Fizz buzz
     *
     * 时间复杂度： O(N)，这里 NN 是输入字符串的长度；
     * 空间复杂度： O(1)，保存结果集的空间不计算在内。
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
