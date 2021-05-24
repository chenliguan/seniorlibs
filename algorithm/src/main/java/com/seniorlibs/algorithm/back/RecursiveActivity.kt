package com.seniorlibs.algorithm.back

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
 * Description: 递归
 */
class RecursiveActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "RecursiveActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, RecursiveActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recursive)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_my_pow).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_my_pow -> {
                LogUtils.e(TAG, "50. Pow(x, n)：${myPow(2.00000, 10)}")
            }
            else -> {
            }
        }
    }

    /**
     * 50. Pow(x, n)，计算x的n次幂函数，-100.0 < x < 100.0。
     * 输入: x=2.00000，n=10，输出: 2.0^10 = 1024.00000。  输入: 2.00000，-2，输出: 0.25000，解释: 2^-2 = 1/2^2 = 1/4 = 0.25
     *
     * 方法一：分治：根据重复性的构造和分解
     *
     * 思想：二分思想递归，复杂度是对数级的。
     *
     * 时间复杂度：O(logn)，即为递归的层数;
     * 空间复杂度：O(logn)，即为递归的层数，这是由于递归的函数调用会使用栈空间。
     *
     * @param x
     * @param n
     * @return
     */
    fun myPow(x: Double, n: Int): Double {
        val N = n.toLong()

        // 2.0^10 = 1024.00000；2^-2 = 1/2^--2 = 1/2^2 = 1/4 = 0.2
        return if (N >= 0) quickMul(x, N) else 1.0 / quickMul(x, -N)
    }

    fun quickMul(x: Double, n: Long): Double {
        // 1.递归终结条件（最先写）
        if (n == 0L) {
            return 1.0
        }

        // 2.处理当前层逻辑

        // 3.调用函数下探到下一层，解决更细节的子问题
        val subResult = quickMul(x, n / 2)

        // 4.将子问题的解的合并，产生最终结果
        return if (n % 2 == 0L) subResult * subResult else subResult * subResult * x

        // 5.清理恢复当前层
    }

}
