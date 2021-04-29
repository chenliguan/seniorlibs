package com.seniorlibs.algorithm.string

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.baselib.utils.LogUtils
import java.util.*
import kotlin.collections.HashMap

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
        findViewById<View>(R.id.btn_first_uniq_char).setOnClickListener(this)
        findViewById<View>(R.id.btn_my_atoi).setOnClickListener(this)
        findViewById<View>(R.id.btn_reverse_string).setOnClickListener(this)
        findViewById<View>(R.id.btn_reverse_string2).setOnClickListener(this)
        findViewById<View>(R.id.btn_reverse).setOnClickListener(this)
        findViewById<View>(R.id.btn_longest_common_prefix).setOnClickListener(this)
        findViewById<View>(R.id.btn_decode_string).setOnClickListener(this)
        findViewById<View>(R.id.btn_str_str).setOnClickListener(this)
        findViewById<View>(R.id.btn_word_pattern).setOnClickListener(this)
        findViewById<View>(R.id.btn_is_palindrome_str).setOnClickListener(this)
        findViewById<View>(R.id.btn_is_palindrome_num).setOnClickListener(this)
        findViewById<View>(R.id.btn_add_strings).setOnClickListener(this)
        findViewById<View>(R.id.btn_add_two_numbers).setOnClickListener(this)
        findViewById<View>(R.id.btn_multiply).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_to_lower_case -> {
                LogUtils.e(TAG, "709. 转换成小写字母1：" + toLowerCase("Hello"))
                LogUtils.e(TAG, "709. 转换成小写字母2：" + toLowerCase1("Hello"))
            }
            R.id.btn_first_uniq_char -> {
                LogUtils.e(TAG, "387. 字符串中的第一个唯一字符：" + firstUniqChar("leetcode"))
            }
            R.id.btn_my_atoi -> {
                LogUtils.e(TAG, "8. 字符串转换整数 (atoi)：" + myAtoi("   -42  with words"))
            }
            R.id.btn_reverse_string -> {
                val s: CharArray = charArrayOf('h', 'e', 'l', 'l', 'o')
                reverseString(s)
                LogUtils.d(TAG, "344. 反转字符串：${s}")
            }
            R.id.btn_reverse_string2 -> {
                LogUtils.d(TAG, "541. 反转字符串2：${reverseStr("abcdefg", 2)}")
            }
            R.id.btn_reverse -> {
                LogUtils.d(TAG, "7. 整数反转：${reverse(-123)}")
            }
            R.id.btn_longest_common_prefix -> {
                LogUtils.d(TAG, "14. 最长公共前缀：${longestCommonPrefix(arrayOf("flower", "flow", "flight"))}")
            }
            R.id.btn_decode_string -> {
                LogUtils.d(TAG, "394. 字符串解码：${decodeString("3[a2[c]]")}")
            }
            R.id.btn_str_str -> {
                LogUtils.d(TAG, "28. 实现朴素的字符串匹配 strStr()：${strStr2("hello", "ll")}")
                LogUtils.d(TAG, "28. 实现KMP字符串匹配 strStr()：${strStr("BBC ABCDAB ABCDABCDABDE", "ABCDABD")}")
//                LogUtils.d(TAG, "28. 实现 strStr()：${strStr("aabaaabaaac", "aabaaac")}")
            }
            R.id.btn_word_pattern -> {
                LogUtils.d(TAG, "290. 单词规律：${wordPattern("abba","dog cat cat dog")}")
            }
            R.id.btn_is_palindrome_str -> {
                LogUtils.d(TAG, "125. 验证回文串：${isPalindrome("A man, a plan, a canal: Panama")}")
            }
            R.id.btn_is_palindrome_num -> {
                LogUtils.d(TAG, "9. 回文数：${isPalindrome(121)}")
            }
            R.id.btn_add_strings -> {
                LogUtils.d(TAG, "415. 字符串相加（两个大数相加）：${addStrings("121", "12000000")}")
            }
            R.id.btn_add_two_numbers -> {
//                LogUtils.d(TAG, "2. 两数相加：${addTwoNumbers(}")
            }
            R.id.btn_multiply -> {
                LogUtils.d(TAG, "43. 字符串相乘：${multiply("123", "45")}")
            }
            else -> {
            }
        }
    }


    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    /**
     * 709. 转换成小写字母  方法1：ASCCII码
     *
     * 通过 ASCII 码表操作字符串即可：SCII码表中大写的A是65，小写的a是97，它们的差是32
     * a-z：97-122
     * A-Z：65-90
     * 0-9：48-57
     *
     * 时间复杂度：O(n)。只遍历了一遍字符串。
     * 空间复杂度：O(n)。用到了数组来存储字符串中每个元素出现的次数。
     *
     * https://leetcode-cn.com/problems/to-lower-case/solution/709-zhuan-huan-cheng-xiao-xie-zi-mu-by-chen-li-gua/
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
     * 用位运算的技巧：
     * 大写变小写、小写变大写：字符 ^= 32;
     * 大写变小写、小写变小写：字符 |= 32;  A -> 65 | 32 转为二进制 -> 0100 0001 | 0010 0000 = 0110 0001 = 97 = a
     * 大写变大写、小写变大写：字符 &= 33;  a -> 97 & 33 转为二进制 -> 0110 0001 & 0010 0001 = 0010 0001 = 33 = a
     *
     * https://leetcode-cn.com/problems/to-lower-case/solution/709-zhuan-huan-cheng-xiao-xie-zi-mu-by-chen-li-gua/
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

    /**
     * 387. 字符串中的第一个唯一字符
     *
     * 时间复杂度：O(n)。只遍历了一遍字符串，同时散列表中查找操作是常数时间复杂度的。
     * 空间复杂度：O(n)。用到了散列表来存储字符串中每个元素出现的次数。
     *
     * https://leetcode-cn.com/problems/first-unique-character-in-a-string/solution/387-zi-fu-chuan-zhong-de-di-yi-ge-wei-yi-zi-fu--11/
     * @param s
     * @return
     */
    fun firstUniqChar(s: String): Int {
        if (s.isEmpty()) return -1

        val map = HashMap<Char, Int>(s.length)
        // 构建哈希表：字符及其出现的频率
        for (c in s) {
            if (map[c] == null) map[c] = 0
            map[c] = map[c]!! + 1
        }
        // 找到索引
        for (i in s.indices) {
            if (map[s[i]] == 1) return i
        }
        return -1
    }

    /**
     * 8. 字符串转换整数 (atoi)
     *
     * 思路：
     * 1、去除前导空格
     * 2、处理正负号：如果出现符号字符，仅第1个有效，并记录正负
     * 3、从后续出现的数字字符中取出当前数字，在进行进制转换，注意不合法的情况
     *
     * 时间复杂度：O(n)，其中n为字符串的长度。我只需要依次处理所有的字符，处理每个字符需要的时间为O(1)。
     * 空间复杂度：O(1)，自动机的状态只需要常数空间存储
     *
     * https://leetcode-cn.com/problems/string-to-integer-atoi/solution/8-zi-fu-chuan-zhuan-huan-zheng-shu-atoi-by-chen-li/
     * @param str
     * @return
     */
    fun myAtoi(str: String): Int {
        if (str.isEmpty()) return 0

        // 1、去除前导空格
        var index = 0
        while (index < str.length && str[index] == ' ') index++
        // 如果已经遍历完成（针对极端用例 "      "）
        if (index == str.length) return 0

        // 2、处理正负号：如果出现符号字符，仅第1个有效，并记录正负
        var sign = 1
        val firstChar = str[index]
        if (firstChar == '+') {
            index++
        } else if (firstChar == '-') {
            index++
            sign = -1
        }

        // 3、从后续出现的数字字符中取出当前数字，在进行进制转换，注意不合法的情况
        var total = 0
        while (index < str.length) {
            // 3.1 取出当前数字
            val digit = str[index] - '0'
            // 3.2 先判断不合法的情况
            if (digit < 0 || digit > 9) break

            // 题目中说：环境只能存储 32 位大小的有符号整数，因此，需要提前判断乘以 10 以后是否越界
            if (total > Int.MAX_VALUE / 10 || (total == Int.MAX_VALUE / 10 && digit > Int.MAX_VALUE % 10)) {
                return if (sign == 1) Int.MAX_VALUE else Int.MIN_VALUE
            }

            // 3.3 合法的情况下，才考虑转换
            total = 10 * total + digit
            index++
        }

        // 3.4 最后，把符号位乘进去
        return total * sign
    }

    /**
     * 344. 反转字符串
     *
     * 时间复杂度：O(n)，其中n为字符数组的长度，一共执行了n/2次的交换；
     * 空间复杂度：O(1),只使用了常数空间来存放若干变量。
     *
     * https://leetcode-cn.com/problems/reverse-string/solution/344-fan-zhuan-zi-fu-chuan-by-chen-li-guan/
     * @param s
     */
    fun reverseString(s: CharArray): Unit {
        if (s.isEmpty()) return

        var i = 0
        var j = s.size - 1

        while (i < j) {
            // 跳过相等的字符
            if (s[i] != s[j]) {
                // 交换
                val temp = s[i]
                s[i] = s[j]
                s[j] = temp
            }

            i++
            j--
        }
    }

    /**
     * 541. 反转字符串 II
     *
     * 时间复杂度：O(n)，其中n是 s 的大小。我们建立一个辅助数组，用来翻转 s 的一半字符。
     * 空间复杂度：O(n)，a 的大小。
     *
     * https://leetcode-cn.com/problems/reverse-string-ii/solution/541-fan-zhuan-zi-fu-chuan-ii-by-chen-li-guan/
     * @param s
     * @param k
     * @return
     */
    fun reverseStr(s: String, k: Int): String {
        val ch = s.toCharArray()
        var start = 0
        while (start < s.length) {
            // 每个块开始于 2k 的倍数，也就是 0, 2k, 4k, 6k, ...。
            var i = start

            // 如果剩余字符少于 k 个，则将剩余字符全部反转。--> j = s.length - 1
            // 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，类同常规情况。--> j = start + k - 1
            var j = Math.min(s.length - 1, start + k - 1)

            // 交换
            while (i < j) {
                val temp = ch[i]
                ch[i] = ch[j]
                ch[j] = temp

                i++
                j--
            }

            // 直接翻转每个 2k 字符块
            start += 2 * k
        }
        return String(ch)
    }


    /**
     * 7. 整数反转
     * 思想："弹出" x 的最后一位数字，并将它 "推入" 到 res 的后面。最后，res 将与 x 相反
     *
     * 时间复杂度：O(log(x)，x 中大约有 log10(x)位数字
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/reverse-integer/solution/7-zheng-shu-fan-zhuan-by-chen-li-guan-lrh0/
     * @param x
     * @return
     */
    fun reverse(x: Int): Int {
        var x = x
        var res = 0
        while (x != 0) {
            // pop
            // 每次取末尾数字  -123 % 10 = -3 -> -12 % 10 = -2 -> -1 % 10 = -1
            val temp = x % 10
            // -123/10 = -12 -> -12/10 = -1 -> -1/10 = -1
            x /= 10

            // 判断是否 大于 最大32位整数，小于 最小32位整数
            if (res > Integer.MAX_VALUE / 10 || res < Integer.MIN_VALUE / 10)  return 0

            // push
            // 0 * 10 + -3 = -3 -> -3 * 10 + -2 = 32 -> 32 * 10 + -1 = -321
            res = res * 10 + temp
        }
        return res
    }


    /**
     * 14. 最长公共前缀   方法1：纵向扫描
     *
     * 纵向扫描时，从前往后遍历所有字符串的每一列，比较相同列上的字符是否相同，
     * 如果相同则继续对下一列进行比较，如果不相同则当前列不再属于公共前缀，当前列之前的部分为最长公共前缀。
     *
     * 时间复杂度：O(mn)，其中m是字符串数组中的字符串的平均长度，n是字符串的数量。最坏情况下，字符串数组中的每个字符串的每个字符都会被比较一次。
     * 空间复杂度：O(1)。使用的额外空间复杂度为常数。
     *
     * https://leetcode-cn.com/problems/longest-common-prefix/solution/14-zui-chang-gong-gong-qian-zhui-by-chen-li-guan/
     * @param strs
     * @return
     */
    fun longestCommonPrefix(strs: Array<String>): String? {
        if (strs.isEmpty()) return ""

        // 从前往后遍历 0 行字符串的每一列 j
        for (j in strs[0].indices) {
            val c = strs[0][j]
            // 比较一列上的所有字符 i 是否相同，如果全相同则继续对下一列 j 进行比较
            for (i in 1 until strs.size) {
                // 如果达到当前行长度 或 存在不相同，则当前列 j 不再属于公共前缀，当前列之前的部分为最长公共前缀。
                if (j == strs[i].length || strs[i][j] != c) {
                    return strs[0].substring(0, j)
                }
            }
        }
        return strs[0]
    }

    /**
     * 28. 实现 strStr()    解法一：暴力法。最主要的问题是，如果字符串中重复的字符比较多，该算法就显得很蠢
     * haystack = "hello", needle = "ll"
     * 思路：将长度为 needle.length 的滑动窗口沿着 haystack 字符串逐步移动，并将窗口内的子串与 needle 字符串相比较
     *
     * 时间复杂度：O(mn)，嵌套 for 循环。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/implement-strstr/solution/28-shi-xian-strstr-by-chen-li-guan-53hk/
     * @param haystack
     * @param needle
     * @return
     */
    fun strStr2(haystack: String, needle: String): Int {
        val m = haystack.length
        val n = needle.length

        for (i in 0..m - n) {
            var j = 0
            while (j < n) {
                if (needle[j] != haystack[i + j]) {
                    break
                }
                j++
            }
            // needle 全都匹配了
            if (j == n) return i
        }
        // haystack 中不存在 needle 子串
        return -1
    }

    /**
     * 28. 实现 strStr()    解法二：KMP
     * haystack = "hello", needle = "ll"
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(n)。
     *
     * https://leetcode-cn.com/problems/implement-strstr/solution/28-shi-xian-strstr-by-chen-li-guan-53hk/
     * @param haystack
     * @param needle
     * @return
     */
    fun strStr(haystack: String, needle: String): Int {
        // 两种特殊情况
        if (needle.isEmpty()) return 0
        if (haystack.isEmpty()) return -1

        // char 数组
        val haystackArray = haystack.toCharArray()
        val needleArray = needle.toCharArray()
        // 长度
        val m = haystackArray.size
        val n = needleArray.size
        // 返回下标
        return kmp(haystackArray, m, needleArray, n)
    }

    /**
     * 第二步：根据 next 数组直接匹配
     *
     * @param haystack
     * @param m
     * @param needle
     * @param n
     * @return
     */
    fun kmp(haystack: CharArray, m: Int, needle: CharArray, n: Int): Int {
        // 获取 next 数组
        val next = next(needle, n)
        var j = 0
        // 注意 i 就从0开始
        for (i in 0 until m) {
            // 不匹配
            while (j > 0 && haystack[i] != needle[j]) {
                // 移动位数 = 已匹配的字符数 - 最后一个匹配字符对应的部分匹配值 -> j = j - (j - next[j - 1])
                // 寻找 j 之前匹配的位置 = 已匹配的字符数 - 移动位数 = j = j - (j - next[j - 1]) = next[j - 1]
                j = next[j - 1]
            }
            // 如果相同就将指针同时后移一下，比较下个字符
            if (haystack[i] == needle[j]) {
                j++
            }
            // 遍历完整个模式串，返回模式串的起点下标
            if (j == n) {
                return i - n + 1
            }
        }
        return -1
    }

    /**
     * 第一步：构建 next 数组（部分匹配表）
     *
     * @param needle
     * @param len
     * @return
     */
    fun next(needle: CharArray, n: Int): IntArray {
        // 定义 next 数组
        val next = IntArray(n)
        // 初始化
        next[0] = 0
        // 快慢指针 i 和 j
        var i = 0
        for (j in 1 until n) {
            // 当前后缀不相同时（needle[i + 1] != needle[j]），则回溯，找 next[i]
            // eg：aabaaac : aabaa -> aa ；aabaaa -> needle[2]!=needle[5]，i=next[1]=0，needle[i+1=1]==needle[5]，aa
            while (i > 0 && needle[i] != needle[j]) {
                i = next[i - 1]
            }
            // 找到相同的前后缀（needle[i + 1] == needle[j]）：已知 [0,i-1] 的最长前后缀；然后 k - 1 又和 i 相同，最长前后缀加 1
            if (needle[i] == needle[j]) {
                i++
            }
            next[j] = i
        }
        return next
    }


    /**
     * 394. 字符串解码
     *
     * 时间复杂度 O(N)，一次遍历 s；
     * 空间复杂度 O(N)，辅助栈在极端情况下需要线性空间，例如 2[2[a]]。
     *
     * https://leetcode-cn.com/problems/decode-string/solution/394-zi-fu-chuan-jie-ma-by-chen-li-guan-jncg/
     * @param s
     * @return
     */
    fun decodeString(s: String): String? {
        var res = StringBuilder()
        var multi = 0
        val stackMulti: LinkedList<Int> = LinkedList()
        val stackRes: LinkedList<String> = LinkedList()

        for (c in s.toCharArray()) {
            when (c) {
                '[' -> {
                    stackMulti.addLast(multi)
                    stackRes.addLast(res.toString())
                    multi = 0
                    res = StringBuilder()
                }
                ']' -> {
                    val curMulti: Int = stackMulti.removeLast()
                    // curMulti * res
                    val curRes = StringBuilder()
                    for (i in 0 until curMulti) {
                        curRes.append(res)
                    }
                    // lastRes + (curMulti * res)
                    val lastRes = stackRes.removeLast()
                    res = StringBuilder(lastRes + curRes)
                }
                in '0'..'9' -> {
                    multi = multi * 10 + c.toString().toInt()
                }
                else -> {
                    res.append(c)
                }
            }
        }
        return res.toString()
    }


    /**
     * 290. 单词规律
     *
     * 时间复杂度：O(n + m)；
     * 空间复杂度：O(n + m)；
     *
     * https://leetcode-cn.com/problems/word-pattern/solution/290-dan-ci-gui-lu-by-chen-li-guan-1wz3/
     * @param pattern
     * @param s
     * @return
     */
    fun wordPattern(pattern: String, s: String): Boolean {
        val array = s.split(" ").toTypedArray()
        if (pattern.length != array.size) {
            return false
        }

        val map = mutableMapOf<Any, Int>()
        for (i in 0 until pattern.length) {
            // 利用map.put方法返回值（key第一次put时返回null，第n次put时返回第n-1次的value）
            if (map.put(pattern[i], i) != map.put(array[i], i)) {
                return false
            }
        }
        return true
    }

    /**
     * 125. 验证回文串
     *
     * 时间复杂度：O(n)。只遍历了一遍字符串。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/valid-palindrome/solution/125-yan-zheng-hui-wen-chuan-by-chen-li-g-8i3w/
     * @param s
     * @return
     */
    fun isPalindrome(s: String): Boolean {
        val s = s.toLowerCase()
        var left = 0
        var right = s.length - 1

        while (left < right) {
            // 过滤非字母和数字
            if (!isAccord(s[left])) {
                left++
                continue
            }
            if (!isAccord(s[right])) {
                right--
                continue
            }

            // 双指针从两端向中间逼近
            if (s[left] != s[right]) {
                return false
            }
            left++
            right--
        }

        return true
    }

    private fun isAccord(c: Char): Boolean {
        return c in 'a'..'z' || c in '0'..'9'
    }

    /**
     * 9. 回文数
     *
     * 时间复杂度：O(n)。只遍历了一遍字符串。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/palindrome-number/solution/9-hui-wen-shu-by-chen-li-guan-yh68/
     * @param x
     * @return
     */
    fun isPalindrome(x: Int): Boolean {
        // 如果是负数则一定不是回文数，直接返回 false
        if (x < 0) return false

        var xs = x
        var cur = 0

        // 如果是正数，则将其倒序数值计算出来，然后比较和原数值是否相等
        while (xs != 0) {
            // 每次取末尾数字  -123 % 10 = -3 -> -12 % 10 = -2 -> -1 % 10 = -1
            val temp = xs % 10
            // -123/10 = -12 -> -12/10 = -1 -> -1/10 = -1
            xs /= 10

            // 0 * 10 + -3 = -3 -> -3 * 10 + -2 = 32 -> 32 * 10 + -1 = -321
            cur = cur * 10 + temp
        }

        return cur == x
    }


    /**
     * 415. 字符串相加（两个大数相加）
     * 思路：设定 i，j 双指针分别指向 num1，num2 尾部，模拟人工加法
     *
     * 时间复杂度 O(max(M,N)))：其中 M，N 为 2 数字长度，按位遍历一遍数字（以较长的数字为准）；
     * 空间复杂度 O(1)：指针与变量使用常数大小空间。
     *
     * https://leetcode-cn.com/problems/add-strings/solution/415-zi-fu-chuan-xiang-jia-liang-ge-da-sh-cicg/
     * @param num1
     * @param num2
     * @return
     */
    fun addStrings(num1: String, num2: String): String? {
        val res = StringBuilder("")
        var i = num1.length - 1
        var j = num2.length - 1
        var carry = 0

        while (i >= 0 || j >= 0) {
            val n1 = if (i >= 0) num1[i] else '0'
            val n2 = if (j >= 0) num2[j] else '0'

            // 分别获取两个字符对应的字面数值，然后相加，再加上进位
            var sum = n1.toInt() + n2.toInt() - 2 * '0'.toInt() + carry
            // 求值：获取进位
            carry = sum / 10
            // 求余：获取当前位
            sum = sum % 10

            // 添加当前位
            res.append(sum)

            i--
            j--
        }

        // 处理最后一个的进位（当循环结束后，是不是还可能会有一个进位）
        if (carry == 1) res.append(1)

        // 最后翻转恢复字符串，再返回
        return res.reverse().toString()
    }

    /**
     * 2. 两数相加
     * 思路：设定 i，j 双指针分别指向 num1，num2 尾部，模拟人工加法
     *
     * 时间复杂度 O(max(M,N)))：其中 M，N 为 2 数字长度，按位遍历一遍数字（以较长的数字为准）；
     * 空间复杂度 O(1)：指针与变量使用常数大小空间。
     *
     * https://leetcode-cn.com/problems/add-two-numbers/solution/2-liang-shu-xiang-jia-by-chen-li-guan-cfp8/
     * @param l1
     * @param l2
     * @return
     */
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var l1 = l1
        var l2 = l2

        val dummy = ListNode(0)
        var cur: ListNode? = dummy
        var carry = 0

        while (l1 != null || l2 != null) {
            val x = l1?.`val` ?: 0
            val y = l2?.`val` ?: 0

            // 分别获取两个节点对应的字面数值，然后相加，再加上进位
            var sum = x + y + carry
            // 求值：获取进位
            carry = sum / 10
            // 求余：获取当前位
            sum = sum % 10

            // 添加下一个节点
            cur?.next = ListNode(sum)
            cur = cur?.next

            if (l1 != null) l1 = l1.next
            if (l2 != null) l2 = l2.next
        }

        // 处理最后一个的进位（当循环结束后，是不是还可能会有一个进位）
        if (carry == 1) cur?.next = ListNode(carry)

        // 返回虚拟头结点 dummy 的下一个节点，即是 头结点
        return dummy.next
    }


    /**
     * 43. 字符串相乘
     * 思路：设定 i，j 双指针分别指向 num1，num2 尾部，从个位数开始逐位相乘。一个字符与一个字符相乘，找出相乘的结果在 res 对应的位置添加，叠加到 res 上
     *
     * 时间复杂度：O(M N)。M,N 分别为 num1 和 num2 的长度。
     * 空间复杂度：O(M+N)。用于存储计算结果。
     *
     * https://leetcode-cn.com/problems/multiply-strings/solution/43-zi-fu-chuan-xiang-cheng-by-chen-li-gu-d40z/
     * @param l1
     * @param l2
     * @return
     */
    fun multiply(num1: String, num2: String): String {
        if(num1.equals("0") || num2.equals("0")) return "0";

        val m = num1.length
        val n = num2.length
        if (m == 0 || n == 0) return "0"

        // 结果最多为 m + n 位数
        val res = IntArray(m + n)

        // 从个位数开始逐位相乘
        for (i in m - 1 downTo 0) {
            for (j in n - 1 downTo 0) {
                // 一个字符与一个字符相乘
                val mul = (num1[i] - '0') * (num2[j] - '0')
                // 找出相乘的结果在 res 对应的位置添加
                val p1 = i + j
                val p2 = i + j + 1
                // 叠加到 res 上
                val sum = mul + res[p2]
                res[p2] = sum % 10
                // 此处的+=是为了处理此位有了数字
                res[p1] += sum / 10
            }
        }

        // 找到结果前缀那些未使用的0的位置
        val sb = StringBuilder()
        var i = 0
        while (res[i] == 0) {
            i++
        }

        // 将计算结果转化成字符串
        while (i < res.size) {
            sb.append(res[i])
            i++
        }
        return sb.toString()
    }

}
