package com.seniorlibs.algorithm.string

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.array.ArrayActivity
import com.seniorlibs.baselib.utils.LogUtils
import java.util.*

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
        findViewById<View>(R.id.btn_first_uniq_chars).setOnClickListener(this)
        findViewById<View>(R.id.btn_is_unique).setOnClickListener(this)
        findViewById<View>(R.id.btn_my_atoi).setOnClickListener(this)
        findViewById<View>(R.id.btn_reverse_string).setOnClickListener(this)
        findViewById<View>(R.id.btn_reverse_string2).setOnClickListener(this)
        findViewById<View>(R.id.btn_reverse_words).setOnClickListener(this)
        findViewById<View>(R.id.btn_reverse_str_words).setOnClickListener(this)
        findViewById<View>(R.id.btn_reverse).setOnClickListener(this)
        findViewById<View>(R.id.btn_longest_common_prefix).setOnClickListener(this)
        findViewById<View>(R.id.btn_decode_string).setOnClickListener(this)
        findViewById<View>(R.id.btn_compress_string).setOnClickListener(this)
        findViewById<View>(R.id.btn_compress).setOnClickListener(this)
        findViewById<View>(R.id.btn_str_str).setOnClickListener(this)
        findViewById<View>(R.id.btn_word_pattern).setOnClickListener(this)
        findViewById<View>(R.id.btn_is_palindrome_str).setOnClickListener(this)
        findViewById<View>(R.id.btn_is_palindrome_num).setOnClickListener(this)
        findViewById<View>(R.id.btn_longest_palindrome).setOnClickListener(this)
        findViewById<View>(R.id.btn_can_permute_palindrome).setOnClickListener(this)
        findViewById<View>(R.id.btn_add_strings).setOnClickListener(this)
        findViewById<View>(R.id.btn_add_binary).setOnClickListener(this)
        findViewById<View>(R.id.btn_add_two_numbers).setOnClickListener(this)
        findViewById<View>(R.id.btn_multiply).setOnClickListener(this)
        findViewById<View>(R.id.btn_min_remove_to_make_valid).setOnClickListener(this)
        findViewById<View>(R.id.btn_score_of_parentheses).setOnClickListener(this)
        findViewById<View>(R.id.btn_length_of_longest_substring).setOnClickListener(this)
        findViewById<View>(R.id.btn_character_replacement).setOnClickListener(this)
        findViewById<View>(R.id.btn_longest_substring).setOnClickListener(this)
        findViewById<View>(R.id.btn_repeated_substring_pattern).setOnClickListener(this)
        findViewById<View>(R.id.btn_count_binary_substrings).setOnClickListener(this)
        findViewById<View>(R.id.btn_check_inclusion).setOnClickListener(this)
        findViewById<View>(R.id.btn_find_anagrams).setOnClickListener(this)
        findViewById<View>(R.id.btn_convert).setOnClickListener(this)
        findViewById<View>(R.id.btn_roman_to_int).setOnClickListener(this)
        findViewById<View>(R.id.btn_find_continuous_sequence).setOnClickListener(this)
        findViewById<View>(R.id.btn_replace_space).setOnClickListener(this)
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
            R.id.btn_first_uniq_chars -> {
                LogUtils.e(TAG, "50. 第一个只出现一次的字符：" + firstUniqChars("leetcode"))
            }
            R.id.btn_is_unique -> {
                LogUtils.e(TAG, "01.01. 判定字符是否唯一：" + isUnique("leetcode"))
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
            R.id.btn_reverse_words -> {
                LogUtils.d(TAG, "557. 反转字符串中的单词 III：${reverseWords("abc def ghij")}")
            }
            R.id.btn_reverse_str_words -> {
                LogUtils.d(TAG, "557. 反转字符串中的单词 III：${reverseStrWords("abc def ghij")}")
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
            R.id.btn_compress_string -> {
                LogUtils.d(TAG, "01.06. 字符串压缩：${compressString("aabcccccaaa")}")
            }
            R.id.btn_compress -> {
                LogUtils.d(TAG, "443. 压缩字符串：${compress(charArrayOf('a', 'a', 'b', 'b', 'b', 'c', 'c', 'c'))}")
            }
            R.id.btn_str_str -> {
                LogUtils.d(TAG, "28. 实现朴素的字符串匹配 strStr()：${strStr2("hello", "ll")}")
                LogUtils.d(TAG, "28. 实现KMP字符串匹配 strStr()：${strStr("BBC ABCDAB ABCDABCDABDE", "ABCDABD")}")
//                LogUtils.d(TAG, "28. 实现 strStr()：${strStr("aabaaabaaac", "aabaaac")}")
            }
            R.id.btn_word_pattern -> {
                LogUtils.d(TAG, "290. 单词规律：${wordPattern("abba", "dog cat cat dog")}")
            }
            R.id.btn_is_palindrome_str -> {
                LogUtils.d(TAG, "125. 验证回文串：${isPalindrome("A man, a plan, a canal: Panama")}")
            }
            R.id.btn_is_palindrome_num -> {
                LogUtils.d(TAG, "9. 回文数：${isPalindrome(121)}")
            }
            R.id.btn_valid_palindrome -> {
                LogUtils.d(TAG, "680. 验证回文字符串 Ⅱ：${validPalindrome("abccbda")}")
            }
            R.id.btn_longest_palindrome -> {
                LogUtils.d(TAG, "409. 最长回文串：${longestPalindrome("abccccdd")}")
            }
            R.id.btn_can_permute_palindrome -> {
                LogUtils.d(TAG, "01.04. 回文排列：${canPermutePalindrome("aabbaa")}")
            }
            R.id.btn_add_strings -> {
                LogUtils.d(TAG, "415. 字符串相加（两个大数相加）：${addStrings("121", "12000000")}")
            }
            R.id.btn_add_binary -> {
                LogUtils.d(TAG, "67. 二进制求和：${addBinary("111", "111111")}")
            }
            R.id.btn_add_two_numbers -> {
//                LogUtils.d(TAG, "2. 两数相加 == 链表求和：${addTwoNumbers(}")
            }
            R.id.btn_multiply -> {
                LogUtils.d(TAG, "43. 字符串相乘：${multiply("123", "45")}")
            }
            R.id.btn_min_remove_to_make_valid -> {
                LogUtils.d(TAG, "1249. 移除无效的括号：${minRemoveToMakeValid("lee(t(c)o)de)")}")
            }
            R.id.btn_score_of_parentheses -> {
                LogUtils.d(TAG, "856. 括号的分数：${scoreOfParentheses("(()(()))")}")
            }

            R.id.btn_length_of_longest_substring -> {
                LogUtils.d(ArrayActivity.TAG, "3. 无重复字符的最长子串：${lengthOfLongestSubstring("pwwkew")}")
            }
            R.id.btn_character_replacement -> {
                LogUtils.d(ArrayActivity.TAG, "424. 替换后的最长重复字符：${characterReplacement("AABABBA", 1)}")
            }
            R.id.btn_longest_substring -> {
                LogUtils.d(ArrayActivity.TAG, "395. 至少有 K 个重复字符的最长子串：${longestSubstring("ababbc", 2)}")
            }
            R.id.btn_repeated_substring_pattern -> {
                LogUtils.d(ArrayActivity.TAG, "459. 重复的子字符串：${repeatedSubstringPattern("abab")}")
            }
            R.id.btn_count_binary_substrings -> {
                LogUtils.d(ArrayActivity.TAG, "696. 计数二进制子串：${countBinarySubstrings("00111011")}")
            }
            R.id.btn_check_inclusion -> {
                LogUtils.d(TAG, "567. 字符串的排列：${checkInclusion("ab", "eidbaooo")}")
            }
            R.id.btn_find_anagrams -> {
                LogUtils.d(TAG, "438. 找到字符串中所有字母异位词：${findAnagrams("abcdab", "ab")}")
            }
            R.id.btn_convert -> {
                LogUtils.d(TAG, "6. Z 字形变换：${convert("abcdab", 2)}")
            }
            R.id.btn_roman_to_int -> {
                LogUtils.d(TAG, "13. 罗马数字转整数：${romanToInt("IVCDX")}")
            }
            R.id.btn_find_continuous_sequence -> {
                LogUtils.d(TAG, "剑指 Offer 57 - II. 和为s的连续正数序列：${findContinuousSequence(9)}")
            }
            R.id.btn_replace_space -> {
                LogUtils.d(TAG, "剑指 Offer 05. 替换空格：${replaceSpace("abc ef")}")
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

        val map = mutableMapOf<Char, Int>()
        // 构建哈希表：字符及其出现的频率
        for (c in s) {
            if (map.containsKey(c)) {
                map[c] = map[c]!! + 1
            } else {
                map[c] = 1
            }
        }
        // 找到索引
        for (i in 0 until s.length) {
            if (map[s[i]] == 1) return i
        }

        return -1
    }


    /**
     * 剑指 Offer 50. 第一个只出现一次的字符
     *
     * 时间复杂度：O(n)。只遍历了一遍字符串，同时散列表中查找操作是常数时间复杂度的。
     * 空间复杂度：O(n)。用到了散列表来存储字符串中每个元素出现的次数。
     *
     * https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/solution/jian-zhi-offer-50-di-yi-ge-zhi-chu-xian-ou6xe/
     * @param s
     * @return
     */
    fun firstUniqChars(s: String): Char {
        if (s.isEmpty()) return ' '

        val map = mutableMapOf<Char, Int>()

        // 构建哈希表：字符及其出现的频率
        for (c in s) {
            if (map.containsKey(c)) {
                map[c] = map[c]!! + 1
            } else {
                map[c] = 1
            }
        }
        // 找到字符
        for (c in s) {
            if (map[c] == 1) return c
        }

        return ' '
    }


    /**
     * 01.01. 判定字符是否唯一
     *
     * 时间复杂度：O(n)。只遍历了一遍字符串，同时散列表中查找操作是常数时间复杂度的。
     * 空间复杂度：O(n)。用到了散列表来存储字符串中每个元素出现的次数。
     *
     * https://leetcode-cn.com/problems/is-unique-lcci/solution/0101-pan-ding-zi-fu-shi-fou-wei-yi-by-ch-e0eh/
     * @param s
     * @return
     */
    fun isUnique(s: String): Boolean {
        if (s.isEmpty()) return true

        val map = mutableMapOf<Char, Int>()
        // 构建哈希表：字符及其出现的频率
        for (c in s) {
            if (map.containsKey(c)) {
                map[c] = map[c]!! + 1
            } else {
                map[c] = 1
            }
        }
        // 找到索引
        for (c in s) {
            if (map[c]!! > 1) return false
        }

        return true
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
        var i = 0
        while (i < str.length && str[i] == ' ') {
            i++
        }
        // 如果已经遍历完成（针对极端用例 "      "）
        if (i == str.length) return 0

        // 2、处理正负号：如果出现符号字符，仅第1个有效，并记录正负
        var sign = 1
        val ch = str[i]
        if (ch == '+') {
            i++
        } else if (ch == '-') {
            i++
            sign = -1
        }

        // 3、从后续出现的数字字符中取出当前数字，在进行进制转换，注意不合法的情况
        var res = 0
        while (i < str.length) {
            // 3.1 取出当前数字
            val temp = str[i] - '0'

            // 3.2 先判断不合法的情况
            if (temp < 0 || temp > 9) break
            // 题目中说：环境只能存储 32 位大小的有符号整数，因此，需要提前判断乘以 10 以后是否越界
            if (res > Int.MAX_VALUE / 10 || (res == Int.MAX_VALUE / 10 && temp > Int.MAX_VALUE % 10)) {
                return if (sign == 1) Int.MAX_VALUE else Int.MIN_VALUE
            }

            // 3.3 合法的情况下，才考虑转换
            res = res * 10 + temp
            i++
        }

        // 3.4 最后，把符号位乘进去
        return res * sign
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

        swap(0, s.size - 1, s)
    }

    fun swap(i1: Int, j1: Int, s: CharArray) {
        var i = i1
        var j = j1
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
            val i = start

            // 如果剩余字符少于 k 个，则将剩余字符全部反转。--> j = s.length - 1
            // 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，类同常规情况。--> j = start + k - 1
            val j = Math.min(s.length - 1, start + k - 1)

            // 交换
            swap(i, j, ch)

            // 直接翻转每个 2k 字符块
            start += 2 * k
        }

        return String(ch)
    }

    /**
     * 557. 反转字符串中的单词 III
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(n)。
     *
     * @param s
     * @return
     */
    fun reverseWords(s: String): String? {
        val ch = s.toCharArray()
        var start = 0
        for (i in 0 until ch.size) {
            if (ch[i] == ' ') {
                swap(start, i - 1, ch)
                // 更新 start 为下一个单词的左索引
                start = i + 1
                continue
            }

            // 到了字符数组结尾，那么最后一个单词的开头和结束索引分别是 start 和 i = n - 1
            if (i == ch.size - 1) {
                swap(start, i, ch)
            }
        }

        return String(ch)
    }


    /**
     * 151. 翻转字符串里的单词
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(n)。
     *
     * https://leetcode-cn.com/problems/reverse-words-in-a-string/solution/151-fan-zhuan-zi-fu-chuan-li-de-dan-ci-b-8d03/
     * @param s
     * @return
     */
    fun reverseStrWords(s: String): String {
        val stack = LinkedList<String>()

        var word = StringBuilder()
        // 先遍历字符串，提取单词入栈
        for (i in 0 until s.length) {
            if (s[i] != ' ') {
                word.append(s[i])

                // 关键判断条件：什么时候入栈，当字母后有空格或已是最后一个则判断为单词入栈
                if (s[i + 1] == ' ' || i == s.length - 1) {
                    stack.push(word.toString())
                    word = StringBuilder()
                }
            }
        }

        // 加入 栈中元素 和 空格 组成反转后结果
        val res = StringBuilder()
        while (stack.isNotEmpty()) {
            res.append(stack.pop())
            if (stack.isNotEmpty()) {
                res.append(' ')
            }
        }

        return res.toString()
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
            if (res > Integer.MAX_VALUE / 10 || res < Integer.MIN_VALUE / 10) return 0

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
        for (j in 0 until strs[0].length) {
            // 取 0 行 j 的字符 c
            val c = strs[0][j]

            // 比较 1 行开始的每一行的 j 列上的所有字符是否与 c 相同，如果全相同则继续对下一列 j 进行比较
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

        for (i in 0 until m - n + 1) {
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
    fun decodeString(s: String): String {
        val numStack = LinkedList<Int>()
        val strStack = LinkedList<String>()
        var res = ""
        var num = 0

        for (i in 0 until s.length) {
            if (s[i] in '0'..'9') {
                // 算出倍数
                num = num * 10 + s[i].toInt() - '0'.toInt()

            } else if (s[i] == '[') {
                // 倍数 num 进入栈等待
                numStack.push(num)
                // 入栈后清零
                num = 0

                // res 串入栈
                strStack.push(res)
                res = ""

            } else if (s[i] == ']') {
                // 遇到 ]，两个栈的栈顶出栈   a6[c]
                var temp = ""
                // 获取拷贝次数
                val times = numStack.pop() // 6
                // 构建子串
                for (j in 0 until times) {
                    temp += res  // c -> cccccc
                }

                if (strStack.isEmpty()) {
                    res = temp
                } else {
                    res = strStack.pop() + temp  // a + cccccc
                }

            } else {
                // 遇到字母，追加给 res
                res += s[i]
            }
        }
        return res
    }


    /**
     * 01.06. 字符串压缩
     *
     * 时间复杂度：O(n)，其中 n 为字符串的长度，即遍历一次字符串的复杂度。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/compress-string-lcci/solution/0106-zi-fu-chuan-ya-suo-by-chen-li-guan-191f/
     * @param s
     * @return
     */
    fun compressString(S: String): String {
        val N = S.length
        var i = 0
        val sb = StringBuilder()

        while (i < N) {
            var j = i
            while (j < N && S[j] == S[i]) {
                j++
            }

            sb.append(S[i])
            sb.append(j - i)
            i = j
        }

        val res = sb.toString()
        // 若"压缩"后的字符串比原字符串长度更长，则返回原先的字符串
        return if (res.length < S.length) res else S
    }


    /**
     * 443. 压缩字符串（了解）
     *
     * 不断右移右指针，使窗口不断增大；
     * 当窗口内出现一个不同的元素时，就可以将元素及其数量（等于左右指针之差）更新在数组中，然后让左指针指向右指针；
     * 遍历到最后一个字符时也需要结算，因此将右指针的终点设为数组之外一格。当右指针移到终点时也要更新数组。
     *
     * 时间复杂度：O(n)，其中 n 为字符串的长度，即遍历一次字符串的复杂度。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/string-compression/solution/443-ya-suo-zi-fu-chuan-by-chen-li-guan-6osc/
     * @param chars
     * @return
     */
    fun compress(chars: CharArray): Int {
        // 三指针：["a","a","b","b","b","c","c","c"] right = 4、right0 = 2、left = 3
        var rightL = 0
        var left = 0

        // 由于最后一个字符也需要判断，所以将右指针终点放到数组之外一格
        for (right in 0..chars.size) {
            // 当遍历完成 或 右指针元素不等于左指针元素时，更新数组
            if (right == chars.size || chars[right] != chars[rightL]) {
                // 更新字符
                chars[left] = chars[rightL]
                left++

                // 更新计数，当个数大于 1 时才更新
                if (right - rightL > 1) {
                    chars[left] = '0' + (right - rightL)
                    left++
                }

                // 更新 rightL 指针为 right
                rightL = right
            }
        }
        return left
    }


    /**
     * 290. 单词规律
     *
     * 时间复杂度：O(n + m)，其中 n 为 pattern 的长度，m 为 str 的长度。插入和查询哈希表的均摊时间复杂度均为 O(n + m)。每一个字符至多只被遍历一次。
     * 空间复杂度：O(n + m)，其中 n 为 pattern 的长度，m 为 str 的长度。最坏情况下，我们需要存储 pattern 中的每一个字符和 str 中的每一个字符串。
     *
     * https://leetcode-cn.com/problems/word-pattern/solution/290-dan-ci-gui-lu-by-chen-li-guan-1wz3/
     * @param pattern
     * @param s
     * @return
     */
    fun wordPattern(pattern: String, s: String): Boolean {
        val array = s.split(" ")
        // 字符和单词是互相映射，数量必须相等
        if (pattern.length != array.size) {
            return false
        }

        val map = mutableMapOf<Any, Int>()
        for (i in 0 until pattern.length) {
            /*
                如果key不存在，插入成功，返回null；如果key存在，返回之前此key对应的value。

                以pattern = "abba", str = "dog cat cat dog"为例，
                第1次：map.put('a',0)返回null，map.put("dog",0)返回null，两者相等；
                第2次：map.put('b',1)返回null，map.put("cat",1)返回null，两者相等；
                第3次：map.put('b',2)返回1，map.put("cat",2)返回1，两者相等；
                第4次：map.put('a',3)返回0，map.put("dog",3)返回0，两者相等，
                结果为 true。

                以pattern = "abba", str = "dog cat cat fish"为例，
                第1次：map.put('a',0)返回null，map.put("dog",0)返回null，两者相等；
                第2次：map.put('b',1)返回null，map.put("cat",1)返回null，两者相等；
                第3次：map.put('b',2)返回1，map.put("cat",2)返回1，两者相等；
                第4次：map.put('a',3)返回0，map.put("fish",3)返回null，两者不相等，
                结果为 false。
            */

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
     * 9. 是否是回文数 == 验证回文数 == 7. 整数反转
     *
     * 时间复杂度：O(logx)，对于每次迭代，我们会将输入除以 10，因此时间复杂度为 O(logn)。
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
            xs = xs / 10

            // 0 * 10 + -3 = -3 -> -3 * 10 + -2 = 32 -> 32 * 10 + -1 = -321
            cur = cur * 10 + temp
        }

        return cur == x
    }


    /**
     * 680. 验证回文字符串 Ⅱ
     * 题目：给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
     *
     * 如果s[i]==s[j]继续i++、j--，判断是否回文
     * 如果s[i]!=s[j]：1.判断s[i+1]到s[j]范围内字符串是否回文，如果是，去掉s[i]即可
     *                2.或者判断s[i]到s[j-1]范围内是否回文，如果是，删除s[j]即可。
     *
     * 时间复杂度：O(n)；
     * 空间复杂度：O(1)；
     *
     * https://leetcode-cn.com/problems/valid-palindrome-ii/solution/680-yan-zheng-hui-wen-zi-fu-chuan-ii-by-8ue7f/
     * @param s
     * @return
     */
    fun validPalindrome(s: String): Boolean {
        var i = 0
        var j = s.length - 1

        while (i < j) {
            if (s[i] != s[j]) {
                return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1)
            }
            i++
            j--
        }
        return true
    }

    // 判断回文
    fun isPalindrome(s: String, i: Int, j: Int): Boolean {
        var i = i
        var j = j
        while (i < j) {
            if (s[i] != s[j]) {
                return false
            }
            i++
            j--
        }
        return true
    }

    /**
     * 409. 最长回文串
     *
     * "abccccdd" -> "dccaccd", 它的长度是 7
     *
     * 思想：只需要尽可能的左右对称地构造字符串就行了，所以回文串里每种字符都出现了偶数次，除了奇数长度的回文串的时候最中间的那个字符可以出现奇数次。
     *      比如回文串 abba，每个字符都出现了偶数次。而奇数长度的回文串 abcbcbcba，c出现了奇数次。
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/longest-palindrome/solution/409-zui-chang-hui-wen-chuan-by-chen-li-g-lwq9/
     *
     * @param s
     * @return
     */
    fun longestPalindrome(s: String): Int {
        // 统计字符串 s 中个字母的个数
        val array = IntArray(128)
        for (c in s) {
            array[c - 'A']++
        }

        var res = 0
        for (x in array) {
            // 字母是偶数个数的可以直接加到最长回文串长度上
            // 字母是奇数个数的只有一个可以全部加到最长回文串长度上（充当中心），其他奇数个数的字母可以令其个数减1后（奇数减1就变成偶数了）再加到最长回文串上

            // (奇)01 & 01 = 1、(偶)10 & 01 = 0、(奇)11 & 01 = 1、(偶)100 & 001 = 0
            // (x and 1) -> x & 1 -> x 是奇数结果是 1，x 是偶数结果是 0
            res += x - (x and 1)
        }

        // 如果最终的长度小于原字符串的长度，说明里面某个字符出现了奇数次，（因为奇数减1就变成偶数了），那么此奇数字符可以放在回文串的中间，所以额外再加1
        return if (res < s.length) res + 1 else res
    }

    /**
     * 01.04. 回文排列（了解）
     *
     * 回文字符串有两种，一种是奇数的，类似于"abbba"，一种是偶数的，类似于"abba"或者"aabbaa"。
     * 如果是偶数的，只需要找出每个字符都是偶数就行了。如果是奇数的，那么字符串的所有字符中只有一个字符的个数是奇数，其他的都是偶数。
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(n)。
     *
     * @param s
     * @return
     */
    fun canPermutePalindrome(s: String): Boolean {
        val set = mutableSetOf<Char>()
        for (c in s) {
            // set的add方法如果返回false，表示已经有了，就把他删除
            if (!set.add(c)) {
                set.remove(c)
            }
        }

        // 最后判断set的长度是否 <= 1，如果等于1说明：只有一个字符的个数是奇数，其他的都是偶数。如果等于0说明：每个字符都是偶数。
        // 如果大于1，表示不可能构成回文字符串
        return set.size <= 1
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
        var i = num1.length - 1
        var j = num2.length - 1

        var carry = 0
        val res = StringBuilder("")

        while (i >= 0 || j >= 0) {
            val n1 = if (i >= 0) num1[i] else '0'
            val n2 = if (j >= 0) num2[j] else '0'

            // 分别获取两个字符对应的字面数值，然后相加，再加上进位
            val sum = n1.toInt() + n2.toInt() - 2 * '0'.toInt() + carry

            // 求值：获取进位
            carry = sum / 10

            // 添加当前位
            res.append(sum % 10)

            i--
            j--
        }

        // 处理最后一个的进位（当循环结束后，是不是还可能会有一个进位）
        if (carry == 1) res.append(1)

        // 最后翻转恢复字符串，再返回
        return res.reverse().toString()
    }

    /**
     * 67. 二进制求和
     *
     * 思路：设定 i，j 双指针分别指向 num1，num2 尾部，模拟人工加法
     *
     * 时间复杂度 O(max(M,N)))：其中 M，N 为 2 数字长度，按位遍历一遍数字（以较长的数字为准）；
     * 空间复杂度 O(1)：指针与变量使用常数大小空间。
     *
     * @param num1
     * @param num2
     * @return
     */
    fun addBinary(num1: String, num2: String): String? {
        var i = num1.length - 1
        var j = num2.length - 1

        var carry = 0
        val res = StringBuilder()

        while (i >= 0 || j >= 0) {
            val n1 = if (i >= 0) num1[i] else '0'
            val n2 = if (j >= 0) num2[j] else '0'

            // 分别获取两个字符对应的字面数值，然后相加，再加上进位
            val sum = n1.toInt() + n2.toInt() - 2 * '0'.toInt() + carry

            // 求值：获取进位
            carry = sum / 2

            // 添加当前位
            res.append(sum % 2)

            i--
            j--
        }

        // 处理最后一个的进位（当循环结束后，是不是还可能会有一个进位）
        if (carry == 1) res.append(1)

        // 最后翻转恢复字符串，再返回
        return res.reverse().toString()
    }

    /**
     * 2. 两数相加 == 链表求和
     * 思路：设定 i，j 双指针分别指向 num1，num2 尾部，模拟人工加法
     *
     * 时间复杂度 O(max(M,N)))：其中 M，N 为 2 数字长度，按位遍历一遍数字（以较长的数字为准）；
     * 空间复杂度 O(1)：指针与变量使用常数大小空间。
     *
     * https://leetcode-cn.com/problems/add-two-numbers/solution/2-liang-shu-xiang-jia-by-chen-li-guan-cfp8/
     * https://leetcode-cn.com/problems/sum-lists-lcci/solution/2-liang-shu-xiang-jia-lian-biao-qiu-he-b-onpq/
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
     * 445. 两数相加 II（了解）
     *
     * 时间复杂度 O(max(M,N)))：其中 M，N 为 2 数字长度，按位遍历一遍数字（以较长的数字为准）；
     * 空间复杂度 O(1)：指针与变量使用常数大小空间。
     *
     * https://leetcode-cn.com/problems/add-two-numbers-ii/solution/445-liang-shu-xiang-jia-ii-by-chen-li-gu-75pf/
     * @param l1
     * @param l2
     * @return
     */
    fun addTwoNumbers2(l1: ListNode?, l2: ListNode?): ListNode? {
        var l1 = l1
        var l2 = l2

        val stack1 = LinkedList<Int>()
        val stack2 = LinkedList<Int>()

        var cur: ListNode? = null
        var carry = 0

        while (l1 != null) {
            stack1.push(l1.`val`)
            l1 = l1.next
        }
        while (l2 != null) {
            stack2.push(l2.`val`)
            l2 = l2.next
        }

        // carry == 1 处理最后一个的进位（当循环结束后，是不是还可能会有一个进位）
        while (stack1.isNotEmpty() || stack2.isNotEmpty() || carry == 1) {
            val a = if (stack1.isEmpty()) 0 else stack1.pop()
            val b = if (stack2.isEmpty()) 0 else stack2.pop()

            var sum = a + b + carry
            carry = sum / 10
            sum %= 10

            // 添加下一个节点
            val curNode = ListNode(sum)
            curNode.next = cur
            cur = curNode
        }

        // 返回虚拟头结点 dummy 的下一个节点，即是 头结点
        return cur
    }


    /**
     * 43. 字符串相乘
     * 思路：设定 i，j 双指针分别指向 num1，num2 尾部，从个位数开始逐位相乘。一个字符与一个字符相乘，找出相乘的结果在 res 对应的位置添加，叠加到 res 上
     *
     * 时间复杂度：O(M * N)。M,N 分别为 num1 和 num2 的长度。
     * 空间复杂度：O(M+N)。用于存储计算结果。
     *
     * https://leetcode-cn.com/problems/multiply-strings/solution/43-zi-fu-chuan-xiang-cheng-by-chen-li-gu-d40z/
     * @param l1
     * @param l2
     * @return
     */
    fun multiply(num1: String, num2: String): String {
        if (num1.equals("0") || num2.equals("0")) return "0"

        val m = num1.length
        val n = num2.length

        // 结果最多为 m + n 位数
        val array = IntArray(m + n)

        // 从个位数开始逐位相乘
        for (i in m - 1 downTo 0) {
            for (j in n - 1 downTo 0) {
                // 一个字符与一个字符相乘
                val mul = (num1[i] - '0') * (num2[j] - '0')
                // 找出相乘的结果在 res 对应的位置添加
                val p1 = i + j
                val p2 = i + j + 1

                // 叠加到 res 上
                val sum = mul + array[p2]
                array[p2] = sum % 10
                // 此处的+=是为了处理此位有了数字
                array[p1] += sum / 10
            }
        }

        // 找到结果前缀那些未使用的0的位置
        val res = StringBuilder()
        var i = 0
        while (array[i] == 0) {
            i++
        }
        // 将计算结果转化成字符串
        while (i < array.size) {
            res.append(array[i])
            i++
        }
        return res.toString()
    }

    /**
     * 1249. 移除无效的括号（了解）
     *
     * 时间复杂度：O(n)，一共有两个循环，每次循环操作 n 个字符，每次操作 O(1)。循环之外，还有一些 O(n)O(n) 的库函数调用。
     * 空间复杂度：O(1)：指针与变量使用常数大小空间。
     *
     * https://leetcode-cn.com/problems/minimum-remove-to-make-valid-parentheses/solution/1249-yi-chu-wu-xiao-de-gua-hao-by-chen-l-qg82/
     * @param s
     * @return
     */
    fun minRemoveToMakeValid(s: String): String? {
        val sb = StringBuilder()

        // 记录 '(' 的数量
        var count = 0

        // 删除多余的')'
        for (c in s) {
            if (c == '(') {
                count++
                sb.append(c)
            } else if (c == ')') {
                count--
                if (count < 0) {
                    // 如果没有 '(' 匹配 ')'，不加入
                    count = 0
                } else {
                    sb.append(c)
                }
            } else {
                sb.append(c)
            }
        }

        var i = sb.length - 1
        // 删除多余的'('
        while (count != 0) {
            if (sb[i] == '(') {
                count--
                sb.deleteCharAt(i)
            }
            i--
        }

        return sb.toString()
    }


    /**
     * 856. 括号的分数（了解）
     *
     * 时间复杂度：O(N)，其中 N 是字符串 S 的长度。
     * 空间复杂度：O(N)，为栈的大小。
     *
     *  (()  (()) )
     *  (                # 遇到 ( 往栈添加
     *  (, (             # 继续添加
     *
     *  (, 1             # 遇到 ）合成一个 1   // A
     *
     *  (, 1, (          # 遇到 ( 往栈添加
     *  (, 1, (, (       # 继续添加
     *
     *  (, 1, (, 1       # 遇到 ）合成一个 1   // A
     *
     *  (, 1, 2          # 遇到 ），结构是（1），所以计算的话是 1 * 2            // B
     *  6                # 遇到 ），结构是（1，2），所以计算的话是（1 + 2） * 2
     *
     * https://leetcode-cn.com/problems/score-of-parentheses/solution/856-gua-hao-de-fen-shu-by-chen-li-guan-jv3r/
     * @param str
     * @return
     */
    fun scoreOfParentheses(S: String): Int {
        val stack = LinkedList<Int>()

        for (c in S) {
            if (c == '(') {
                // '('用-1来表示
                stack.push(-1)

            } else if (c == ')') {
                // A
                if (stack.peek() == -1) {
                    stack.pop()
                    stack.push(1)
                } else {
                    // B
                    var temp = 0
                    while (stack.peek() != -1) {
                        temp += stack.pop()
                    }

                    stack.pop()
                    stack.push(2 * temp)
                }
            }
        }

        // 最后栈内都是分数，没有括号了，求和即可
        var res = 0
        while (!stack.isEmpty()) {
            res += stack.pop()
        }
        return res
    }


    /**
     * 3. 无重复字符的最长子串
     *
     * 思路：双指针，滑动窗口，保证每个窗口里字母都是唯一的。
     *      使用 map 来记录一个字母，key 值为字符，value 值为字符位置 +1，+1 表示从字符位置后一个才开始不重复.
     *      没有重复字母时，调整右边界。当窗口内出现重复字母时，调整左边界.
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/3-wu-zhong-fu-zi-fu-de-zui-chang-zi-chua-72h1/
     * @param s
     * @return
     */
    fun lengthOfLongestSubstring(s: String): Int {
        if (s.isEmpty()) return 0

        val map = mutableMapOf<Char, Int>()
        var left = 0
        var res = 0

        for (i in 0 until s.length) {
            // 先判断 右边新字符是否已重复
            if (map.containsKey(s[i])) {
                left = Math.max(left, map[s[i]]!!)
            }

            // 扩大右边界并更新窗口状态
            map[s[i]] = i + 1

            // 计算无重复字符的最长子串
            res = Math.max(res, i - left + 1)
        }

        return res
    }


    /**
     * 424. 替换后的最长重复字符
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * https://leetcode-cn.com/problems/longest-repeating-character-replacement/solution/424-ti-huan-hou-de-zui-chang-zhong-fu-zi-2p6l/
     * @param s
     * @param k
     * @return
     */
    fun characterReplacement(s: String, k: Int): Int {
        // 窗口内相同元素的最大个数
        var maxSame = 0
        // 模拟 hashMap，很多场合都可以，大家可以总结一下
        val array = IntArray(26)

        // 左右指针
        var left = 0
        var right = 0

        while (right < s.length) {
            // 得出索引，ASCLL 码
            val index = s[right] - 'A'
            // 数目加1，要求出窗口内最多元素
            array[index]++
            right++
            // 得出最大 maxSame
            maxSame = Math.max(maxSame, array[index])

            // 要替换次数大于 k 了，窗口超出范围了，缩小窗口左边
            while (right - left - maxSame > k) {
                array[s[left] - 'A']--
                left++
            }
        }

        return right - left
    }


    /**
     * 395. 至少有 K 个重复字符的最长子串
     *
     * 思想：过分治 缩小问题的规模，调用递归。
     *      如果一个字符 c 在 s 中出现的次数少于 k 次，那么 s 中所有的包含 c 的子字符串都不能满足题意。所以，应该在 s 的所有不包含 c 的子字符串中继续寻找结果
     *
     * 时间复杂度：O(N⋅E)，其中 N 为字符串的长度，E 为字符集=26。由于每次递归调用都会完全去除某个字符，因此递归深度最多为 E。
     * 空间复杂度：O(E*E)。递归的深度为 O(E)，每层递归需要开辟 O(E) 的额外空间。
     *
     * https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/solution/395-zhi-shao-you-k-ge-zhong-fu-zi-fu-de-u7le8/
     * @param s
     * @param k
     * @return
     */
    fun longestSubstring(s: String, k: Int): Int {
        if (s.length < k) return 0

        val array = IntArray(26)
        for (c in s) {
            array[c - 'a']++
        }

        for (c in s) {
            // 判断条件 找到小于出现 k 次的字符串
            if (array[c - 'a'] < k) {
                var res = 0
                // 将字符串切分成多个小段 分别再分治求解
                for (t in s.split(c)) {
                    res = Math.max(res, longestSubstring(t, k))
                }
                return res
            }
        }

        // 原字符串里面没有小于k的字符串 直接返回字符串长度
        return s.length
    }

    /**
     * 459. 重复的子字符串（了解）
     * 思想：直接判断 str 中去除首尾元素之后，是否包含自身元素。如果包含。则表明存在重复子串。 abab -> a bababa b (ok) ; abc -> a bcab c (no)
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(1)。
     *
     * @param s
     * @return
     */
    fun repeatedSubstringPattern(s: String): Boolean {
        val str = s + s
        return str.substring(1, str.length - 1).contains(s)
    }


    /**
     * 696. 计数二进制子串（了解）
     *
     * 思路：将字符串 s 按照 0 和 1 的连续段分组，存在 list 数组中，例如 s = 00111011，可以得到这样的：list ={2,3,1,2}。
     *      两个相邻的数一定代表的是两种不同的字符，两个相邻的数字为 u 或者 v，它们对应着 u 个 0 和 v 个 1，或者 u 个 1 和 v 个 0，即是 min{u,v}。
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(n)。
     *
     * https://leetcode-cn.com/problems/count-binary-substrings/solution/696-ji-shu-er-jin-zhi-zi-chuan-by-chen-l-dilo/
     * @param s
     * @return
     */
    fun countBinarySubstrings(s: String): Int {
        val list = mutableListOf<Int>()
        var p = 0

        while (p < s.length) {
            val c = s[p]
            var count = 0
            while (p < s.length && s[p] == c) {
                p++
                count++
            }
            list.add(count)
        }

        var res = 0
        for (i in 1 until list.size) {
            res += Math.min(list[i], list[i - 1])
        }
        return res
    }


    /**
     * 567. 字符串的排列
     *
     * 时间复杂度: 0(n)；
     * 空间复杂度: 0(1)，需要常数级别的额外空间。
     *
     * https://leetcode-cn.com/problems/permutation-in-string/solution/567-zi-fu-chuan-de-pai-lie-by-chen-li-gu-v2q2/
     * @param p
     * @param s
     * @return
     */
    fun checkInclusion(p: String, s: String): Boolean {
        val n = s.length
        val m = p.length
        if (n < m) return false

        val pCnt = IntArray(26)
        val sCnt = IntArray(26)

        for (i in 0 until m) {
            pCnt[p[i] - 'a']++
            sCnt[s[i] - 'a']++
        }

        // 若 pCnt 和 sCnt 相等，则找到第一个异位词索引 0
        if (Arrays.equals(sCnt, pCnt)) {
            return true
        }

        // 继续遍历 s 字符串索引为 [pLen, sLen) 的字母，在 sCnt 中每次增加一个新字母，去除一个旧字母
        for (i in m until n) {
            sCnt[s[i - m] - 'a']--
            sCnt[s[i] - 'a']++
            // 判断 pCnt 和 sCnt 是否相等，相等则在返回值 res 中新增异位词索引 i - pLen + 1
            if (Arrays.equals(sCnt, pCnt)) {
                return true
            }
        }
        return false
    }


    /**
     * 438. 找到字符串中所有字母异位词
     *
     * 时间复杂度: 0(n)；
     * 空间复杂度: 0(1)，需要常数级别的额外空间。
     *
     * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/solution/438-zhao-dao-zi-fu-chuan-zhong-suo-you-z-apuf/
     * @param s
     * @param p
     * @return
     */
    fun findAnagrams(s: String, p: String): List<Int> {
        val n = s.length
        val m = p.length
        val res = mutableListOf<Int>()
        if (n < m) return res

        val pCnt = IntArray(26)
        val sCnt = IntArray(26)

        for (i in 0 until m) {
            pCnt[p[i] - 'a']++
            sCnt[s[i] - 'a']++
        }

        // 若 pCnt 和 sCnt 相等，则找到第一个异位词索引 0
        if (Arrays.equals(sCnt, pCnt)) {
            res.add(0)
        }

        // 继续遍历 s 字符串索引为 [pLen, sLen) 的字母，在 sCnt 中每次增加一个新字母，去除一个旧字母
        for (i in m until n) {
            sCnt[s[i - m] - 'a']--
            sCnt[s[i] - 'a']++

            // 判断 pCnt 和 sCnt 是否相等，相等则在返回值 res 中新增异位词起步索引 i - m + 1
            if (Arrays.equals(sCnt, pCnt)) {
                res.add(i - m + 1)
            }
        }
        return res
    }


    /**
     * 6. Z 字形变换
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(n)。
     *
     * https://leetcode-cn.com/problems/zigzag-conversion/solution/6-z-zi-xing-bian-huan-by-chen-li-guan-j7qs/
     * @param s
     * @param numRows
     * @return
     */
    fun convert(s: String, numRows: Int): String? {
        if (numRows < 2) return s

        var i = 0
        var flag = -1

        val rows = mutableListOf<StringBuilder>()
        for (k in 0 until numRows) {
            rows.add(StringBuilder())
        }

        for (c in s) {
            // 把每个字符 c 填入对应行
            rows[i].append(c)

            // 更新当前字符 c 对应的行索引：第 0 行改为 +1(-1+1=0)，最后一行改为 -1(0-1=-1)
            if (i == 0 || i == numRows - 1) {
                flag = -flag
            }

            i += flag
        }

        val res = StringBuilder()
        for (row in rows) {
            res.append(row)
        }
        return res.toString()
    }


    /**
     * 13. 罗马数字转整数
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/roman-to-integer/solution/13-luo-ma-shu-zi-zhuan-zheng-shu-by-chen-dw4p/
     * @param s
     * @return
     */
    fun romanToInt(s: String): Int {
        val map = mutableMapOf<String, Int>()
        map["I"] = 1
        map["IV"] = 4
        map["V"] = 5
        map["IX"] = 9
        map["X"] = 10
        map["XL"] = 40
        map["L"] = 50
        map["XC"] = 90
        map["C"] = 100
        map["CD"] = 400
        map["D"] = 500
        map["CM"] = 900
        map["M"] = 1000

        var res = 0
        var i = 0

        while (i < s.length) {
            if (i + 1 < s.length && map.containsKey(s.substring(i, i + 2))) {
                // 2位
                res += map[s.substring(i, i + 2)]!!
                i += 2
            } else {
                // 1位
                res += map[s.substring(i, i + 1)]!!
                i++
            }
        }
        return res
    }

    /**
     * 剑指 Offer 57 - II. 和为s的连续正数序列
     *
     * 滑动窗口
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/solution/jian-zhi-offer-57-ii-he-wei-sde-lian-xu-v7eb0/
     * @param target
     * @return
     */
    fun findContinuousSequence(target: Int): Array<IntArray>? {
        // 滑动窗口的左边界
        var i = 1
        var j = 1
        var sum = 0

        val res = mutableListOf<IntArray>()
        // 窗口的左边是窗口内的最小数字，只能小于等于target / 2, 因为题中要求的是至少含有两个数
        while (i <= target / 2) {
            if (sum < target) {
                // 如果窗口内的值小于 target，右边界向右移动
                sum += j
                j++
            } else if (sum > target) {
                // 如果窗口内的值大于 target，左边界向右移动
                sum -= i
                i++
            } else {
                // 如果窗口内的值等于 target，说明找到了一组满足条件的序列，把它加入到列表中
                val array = IntArray(j - i)
                for (k in i until j) {
                    array[k - i] = k
                }
                res.add(array)

                // 左边界向右移动
                sum -= i
                i++
            }
        }
        return res.toTypedArray()
    }

    /**
     * 剑指 Offer 05. 替换空格
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/solution/05-ti-huan-kong-ge-by-chen-li-guan-y6se/
     * @param s
     * @return
     */
    fun replaceSpace(s: String): String? {
        val res = StringBuilder()
        for (c in s) {
            if (c == ' ') {
                res.append("%20")
            } else {
                res.append(c)
            }
        }
        return res.toString()
    }
}
