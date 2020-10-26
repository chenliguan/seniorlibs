package com.seniorlibs.algorithm.main

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.array.ArrayActivity
import com.seniorlibs.algorithm.map.MapActivity
import com.seniorlibs.algorithm.recursive.RecursiveActivity
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
        findViewById<View>(R.id.btn_is_valid).setOnClickListener(this)
        findViewById<View>(R.id.btn_bracket_generate).setOnClickListener(this)
        findViewById<View>(R.id.btn_top_k_frequent).setOnClickListener(this)
        findViewById<View>(R.id.btn_is_anagram).setOnClickListener(this)
        findViewById<View>(R.id.btn_is_anagram_groups).setOnClickListener(this)
        findViewById<View>(R.id.btn_is_fizz_buzz).setOnClickListener(this)
        findViewById<View>(R.id.btn_max_area).setOnClickListener(this)

    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_is_valid -> {
                val valid = isValid("]")
                LogUtils.d(TAG, "20. 有效的括号：${valid}")
            }
            R.id.btn_bracket_generate -> {
                LogUtils.e(RecursiveActivity.TAG, "22. 括号生成：${generateParenthesis(3)}")
            }
            R.id.btn_top_k_frequent -> {
                val nums: IntArray = intArrayOf(1, 1, 1, 2, 2, 3)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    LogUtils.d(TAG, "347. 前 K 个高频元素 ：" + topKFrequent(nums, 2))
                }
            }
            R.id.btn_is_anagram -> {
                LogUtils.e(MapActivity.TAG, "242. 有效的字母异位词：" + isAnagram("a", "b"))
            }

            R.id.btn_is_anagram_groups -> {
                LogUtils.e(
                    MapActivity.TAG,
                    "49. 字母异位词分组：" + groupAnagrams(
                        arrayOf(
                            "eat",
                            "tea",
                            "tan",
                            "ate",
                            "nat",
                            "bat"
                        )
                    )
                )
            }

            R.id.btn_is_fizz_buzz -> {
                LogUtils.e(MapActivity.TAG, "412. Fizz buzz：" + fizzBuzz(15))
            }

            R.id.btn_max_area -> {
                val nums: IntArray = intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)
                // 11. 盛最多水的容器
                LogUtils.d(ArrayActivity.TAG, "11. 盛最多水的容器：${maxArea(nums)}")
            }
            else -> {
            }
        }
    }

    /**
     * 思想：如果输入是 ( ，入栈 )，当输入 ) 时弹出栈中的 (，对比是否相等。不等直接返回false退出
     *      如果输入是 ) ，此时栈为空，以后也无法再匹配此 ) ，直接返回false退出；
     *      如果连续输入是 ((，最后栈不为空，返回结果false；
     *
     * @param s
     * @return
     */
    fun isValid(s: String): Boolean {
        if (s.isEmpty()) return false

        val chars = s.toCharArray()
        val stack = LinkedList<Char>()

        for (c in chars) {
            if (c == '(') {
                stack.push(')')
            } else if (c == '[') {
                stack.push(']')
            } else if (c == '{') {
                stack.push('}')
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false
            }
        }

        return stack.isEmpty()
    }

    fun generateParenthesis(n: Int): List<String> {
        val res: MutableList<String> = mutableListOf()
        if (n == 0) return res

        // ((()))
        // 添加 ) ，需要 ( 的数量大于 )
        // 添加 ( , 需要 ( 数量小于 n
        return generate(0, 0, n, "", res)
    }

    fun generate(
        l: Int,
        r: Int,
        n: Int,
        str: String,
        res: MutableList<String>
    ): MutableList<String> {
        // 1.递归终结条件（最先写） // 肯定不合法，提前结束，即“剪枝”
        if (l > n || r > l) return res

        // 2.处理当前层逻辑
        if (l == n && r == l) res.add(str)

        // 3.下探到下一层
        if (l < n) generate(l + 1, r, n, str + "(", res)

        if (r < l) generate(l, r + 1, n, str + ")", res)

        // 4.清理恢复当前层

        return res
    }

    fun isAnagram(s: String, t: String): Boolean {
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

        for (i in array) {
            if (i > 0) return false
        }

        return true
    }

    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val res: MutableList<List<String>> = mutableListOf()
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

    fun fizzBuzz(n: Int): List<String> {
        val res = mutableListOf<String>()
        val map = mutableMapOf<Int, String>(3 to "fizz", 5 to "buzz")

        for (i in 1 until n + 1) {
            val str = StringBuilder()
            for (key in map.keys) {
                if (i % key == 0) {
                    str.append(map[key])
                }
            }

            if (str.isEmpty()) {
                str.append(i.toString())
            }

            res.add(str.toString())
        }

        return res
    }

    fun maxArea(a: IntArray): Int {
        if (a.isEmpty()) return 0

        var i = 0
        var j = a.size - 1
        var max = 0

        while (i < j) {
            // 1.计算宽/高
            val w = j - i
            val h = Math.min(a[i], a[j])
            // 2.计算区域面积，并更新最大面积
            val area = w * h
            if (area > max) {
                max = area
            }
            // 3.选择左右指针最小的高度，最大指针不动，移动小的指针向中间移动
            if (a[i] < a[j]) i++ else j--
        }

        return max
    }

    /**
     * 347. 前 K 个高频元素
     *
     * @param nums
     * @param k
     * @return
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val res = IntArray(k)
        if (nums.isEmpty()) return res

        val map = mutableMapOf<Int, Int>()
        for (n in nums) {
            if (map.containsKey(n)) {
                map[n] = map[n]!! + 1
            } else {
                map[n] = 1
            }
        }

        // 优先级队列，升序->小顶堆
        val queue = PriorityQueue(Comparator<Int> { o1, o2 -> map[o1]!! - map[o2]!! })

        for (key in map.keys) {
            if (queue.size < k) {
                queue.offer(key)
            } else if (map[key]!! > map[queue.peek()]!!) {
                queue.poll()
                queue.offer(key)
            }
        }

        for (i in 0 until queue.size) {
            res[i] = queue.poll()
        }

        return res
    }
}
