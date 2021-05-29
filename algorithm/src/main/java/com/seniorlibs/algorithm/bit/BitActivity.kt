package com.seniorlibs.algorithm.bit

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
 * Date: 2020/10/25
 * Mender:
 * Modify:
 * Description: 位运算页面
 */
class BitActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "BitActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, BitActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bit)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_hamming_weight).setOnClickListener(this)
        findViewById<View>(R.id.btn_is_power_of_two).setOnClickListener(this)
        findViewById<View>(R.id.btn_find_the_difference).setOnClickListener(this)
        findViewById<View>(R.id.btn_single_number).setOnClickListener(this)
        findViewById<View>(R.id.btn_single_numbers).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_hamming_weight -> {
                // 00000000000000000000001000001001
                LogUtils.e(TAG, "191. 位1的个数（求一个整数的二进制中 1 的个数）：" + hammingWeight(521))
                LogUtils.e(TAG, "191. 位1的个数：" + hammingWeight(521))
                // 00000000000000000000000000001011
                LogUtils.e(TAG, "191. 位1的个数2：" + hammingWeight2(11))
            }
            R.id.btn_is_power_of_two -> {
                LogUtils.e(TAG, "231. 2的幂：" + isPowerOfTwo(4))
            }
            R.id.btn_find_the_difference -> {
                LogUtils.e(TAG, "389. 找不同：" + findTheDifference("abcd", "abcde"))
            }
            R.id.btn_single_number -> {
                LogUtils.e(TAG, "136. 只出现一次的数字：" + singleNumber(intArrayOf(4, 1, 2, 1, 2)))
            }
            R.id.btn_single_numbers -> {
                LogUtils.e(
                    TAG,
                    "剑指 Offer 56 - I. 数组中数字出现的次数：" + singleNumbers(intArrayOf(3, 3, 5, 5, 6, 7))
                )
            }
            else -> {
            }
        }
    }

    /**
     * 191. 位1的个数（求一个整数的二进制中 1 的个数）  方法1：位操作的小技巧（方法2的优化：推荐）
     *
     * 思路：不断把数字最后一个 1 反转，并把答案加一
     *
     * 时间复杂度：O(1) 。运行时间与n中位为1的有关。在最坏情况下， n中所有位都是1 。对于32位整数，运行时间是O(1)的。
     * 空间复杂度：O(1) 。没有使用额外空间。
     *
     * https://leetcode-cn.com/problems/number-of-1-bits/solution/191-wei-1de-ge-shu-by-chen-li-guan/
     * @param n
     * @return
     */
    fun hammingWeight(n: Int): Int {
        var n = n
        var sum = 0
        while (n != 0) {
            sum++
            // 10011 & 10010 = 10010
            // 10010 & 10001 = 10000
            // 10000 & 01000 = 00000
            n = n and n - 1  // &
        }
        return sum
    }

    /**
     * 191. 位1的个  方法2：循环和位移动
     *
     * 思路：遍历数字的 32 位。如果某一位是 1 ，将计数器加一
     *
     * 时间复杂度：O(1) 。运行时间与n中位为1的有关。在最坏情况下， n中所有位都是1 。对于32位整数，运行时间是O(1)的。
     * 空间复杂度：O(1) 。没有使用额外空间。
     *
     * https://leetcode-cn.com/problems/number-of-1-bits/solution/191-wei-1de-ge-shu-by-chen-li-guan/
     * @param n
     * @return
     */
    fun hammingWeight2(n: Int): Int {
        var bits = 0
        var mask = 1
        for (i in 0..31) {
            // 00000000000000000000001000001001 & 1 / 1000 / 100000000 != 0
            if (n and mask != 0) {  // &
                bits++
            }
            mask =
                mask shl 1  // 左移 <<（x >> 1-> x/2）， 1 / 10 / 100 / 1000 / ... / 1000000000000000000000000000000
        }
        return bits
    }


    /**
     * 231. 2的幂
     *
     * 2^0 = 0001    n  & (n - 1)    = 清零最低位的 1
     * 2^1 = 0010  0010 & (0010 - 1) = 0010 & 0001 = 0
     * 2^2 = 0100
     * 2^3 = 1000
     *
     * https://leetcode-cn.com/problems/power-of-two/solution/231-2de-mi-by-chen-li-guan/
     *
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return
     */
    fun isPowerOfTwo(n: Int): Boolean {
        return n > 0 && n and n - 1 == 0  // &
    }


    /**
     * 389. 找不同
     *
     * 使用异或运算可以解题主要因为异或运算有以下几个特点：
     *     一个数和0做XOR运算等于本身：  a⊕0 = a；
     *     一个数和其本身做XOR运算等于0：a⊕a = 0；
     *     XOR 运算满足交换律和结合律： a⊕b⊕a = (a⊕a)⊕b = 0⊕b = b；
     * 在以上的基础条件上，将所有数字按照顺序做抑或运算，最后剩下的结果即为唯一的数字。
     *
     * 时间复杂度：O(m+n)，m 为字符串 s 的长度，n 为字符串t的长度。
     * 空间复杂度：O(m+n)。
     *
     * https://leetcode-cn.com/problems/find-the-difference/solution/389-zhao-bu-tong-by-chen-li-guan-3hwy/
     * @param s
     * @param t
     * @return
     */
    private fun findTheDifference(s: String, t: String): Char {
        var res = t[t.length - 1].toInt()

        // 注意：s 的长度，不是 t 的长度
        for (i in 0 until s.length) {
            res = res xor s[i].toInt()
            res = res xor t[i].toInt()
        }

        return res.toChar()
    }

    /**
     * 136. 只出现一次的数字
     * 题意：除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/single-number/solution/136-zhi-chu-xian-yi-ci-de-shu-zi-by-chen-l8d2/
     * @param nums
     * @return
     */
    fun singleNumber(nums: IntArray): Int {
        var res = 0
        for (num in nums) {
            res = res xor num
        }
        return res
    }


    /**
     * 剑指 Offer 56 - I. 数组中数字出现的次数
     *
     * 时间复杂度：O(n)，只需要遍历数组两次
     * 空间复杂度：O(1)，只需要常数的空间存放若干变量
     *
     * https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/solution/jian-zhi-offer-56-i-shu-zu-zhong-shu-zi-fyloj/
     *
     * @param nums
     * @return
     */
    fun singleNumbers(nums: IntArray): IntArray? {
        // 1.对所有数字进行一次异或，得到两个出现一次的数字的异或值的结果 k，k!=0。eg：335567，最终求出 6,7
        var k = 0
        for (num in nums) {
            k = k xor num
        }

        // 2.根据k找到67相互嫌弃的点在哪，即获得k中最低位的1。eg：6:1 1 0，7:1 1 1
        var mask = 1
        // mask = k & (-k) 这种方法也可以得到mask
        while (k and mask == 0) {
            mask = mask shl 1
        }

        // 3.根据这个互相嫌弃的点，分成两组，对所有的数字进行遍历，6,7就不会被分到一个组了，成对出现的数字分到一组
        var a = 0
        var b = 0
        for (num in nums) {
            // 在每个组内进行异或操作，最终剩下6，7分在不同组里
            if (num and mask == 0) {
                a = a xor num
            } else {
                b = b xor num
            }
        }
        return intArrayOf(a, b)
    }

}
