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
        findViewById<View>(R.id.btn_hamming_weight).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_hamming_weight -> {
                // 00000000000000000000001000001001
                LogUtils.e(TAG, "191. 位1的个数：" + hammingWeight(521))
                LogUtils.e(TAG, "191. 位1的个数1：" + hammingWeight1(521))
                // 00000000000000000000000000001011
                LogUtils.e(TAG, "191. 位1的个数：" + hammingWeight(11))
            }
            R.id.btn_is_power_of_two -> {
                LogUtils.e(TAG, "231. 2的幂：" + isPowerOfTwo(4))
            }
            else -> {
            }
        }
    }

    /**
     * 191. 位1的个  方法1：循环和位移动
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
    fun hammingWeight(n: Int): Int {
        var bits = 0
        var mask = 1
        for (i in 0..31) {
            // 00000000000000000000001000001001 & 1 / 1000 / 100000000 != 0
            if (n and mask != 0) {  // &
                bits++
            }
            mask = mask shl 1  // 左移 <<（x >> 1-> x/2）， 1 / 10 / 100 / 1000 / ... / 1000000000000000000000000000000
        }
        return bits
    }

    /**
     * 191. 位1的个  方法2：位操作的小技巧（方法1的优化）
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
    fun hammingWeight1(n: Int): Int {
        var n = n
        var sum = 0
        while (n != 0) {
            sum++
            // 00000000000000000000001000001001 & 00000000000000000000001000001000 = 00000000000000000000001000001000
            // 00000000000000000000001000001000 & 00000000000000000000001000000111 = 00000000000000000000001000000000
            // 00000000000000000000001000000000 & 00000000000000000000000111111111 = 00000000000000000000000000000000
            n = n and n - 1  // &
        }
        return sum
    }


    /**
     * 231. 2的幂
     *
     * 若 n = 2^x ，且 x 为自然数（即 n 为 2 的幂），则一定满足以下条件：
     * 1.恒有 n & (n - 1) == 0，这是因为：
     *  （1）n 二进制最高位为 1，其余所有位为 0；
     *  （2）n−1 二进制最高位为 00，其余所有位为 11；
     * 2.一定满足 n > 0。
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
}
