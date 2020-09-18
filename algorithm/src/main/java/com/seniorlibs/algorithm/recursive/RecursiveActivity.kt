package com.seniorlibs.algorithm.recursive

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.scaleMatrix
import com.seniorlibs.algorithm.R
import com.seniorlibs.baselib.utils.LogUtils
import java.util.*

/**
 * 递归
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
        findViewById<View>(R.id.btn_climb_stairs).setOnClickListener(this)
        findViewById<View>(R.id.btn_bracket_generate).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_climb_stairs -> {
                LogUtils.e(TAG, "70. 爬楼梯1：${climbStairs(5)}")
                LogUtils.e(TAG, "70. 爬楼梯2：${climbStairs1(5)}")
                LogUtils.e(TAG, "70. 爬楼梯3：${climbStairs3(5)}")
                LogUtils.e(TAG, "70. 爬楼梯4：${climbStairs4(5)}")
            }
            R.id.btn_bracket_generate -> {
                list.clear()
                LogUtils.e(TAG, "22. 括号生成：${generateParenthesis(3)}")
            }

            else -> {
            }
        }
    }

    /**
     * 70. 爬楼梯
     * 方法一：动态规划
     *
     * 时间复杂度：循环执行n次，每次花费常数的时间代价，时间复杂度为 O(n)；
     * 空间复杂度：用了n空间的数组辅助，空间复杂度为 O(n)。
     *
     * @param n
     * @return
     */
    fun climbStairs(n: Int): Int {
        if (n <= 2) return 2

        val array = IntArray(n + 1)

        array[1] = 1
        array[2] = 2

        for (i in 3 until n + 1) {
            array[i] = array[i - 1] + array[i - 2]
        }

        return array[n]
    }

    /**
     * 70. 爬楼梯
     * 方法二：动态规划优化，斐波那契数。数组当前值是依赖他前面两个值的（前两个除外），我们只需要用两个临时变量即可，不需要申请一个数组
     *
     * 时间复杂度：循环执行n次，每次花费常数的时间代价，时间复杂度为 O(n)；
     * 空间复杂度：只用了常数个变量作为辅助空间，空间复杂度为 O(1)。
     *
     * @param n
     * @return
     */
    fun climbStairs1(n: Int): Int {
        if (n <= 2) return n

        var first = 1
        var second = 2
        var sum = 0

        for (i in 3 until n + 1) {
            sum = first + second
            first = second
            second = sum
        }

        return sum
    }

    /**
     * 70. 爬楼梯
     * 方法三：暴力递归
     *
     * 时间复杂度：O(2^n)。树形递归的大小为2^n；
     * 空间复杂度：O(n)。递归树的深度可以达到n
     *
     * @param n
     * @return
     */
    fun climbStairs3(n: Int): Int {
        if (n <= 2) return n

        return climbStairs3(n - 1) + climbStairs3(n - 2)
    }

    /**
     * 70. 爬楼梯
     * 方法三：记忆化递归
     *
     * 时间复杂度：O(n)。树形递归的大小可以达到 n；
     * 空间复杂度：O(n)。递归树的深度可以达到 n
     *
     * @param n
     * @return
     */
    fun climbStairs4(n: Int): Int {

        return climbStair(n, 1 , 2)
    }

    fun climbStair(n: Int, first: Int, second: Int): Int {
        if (n <= 1) return first
        if (n == 2) return second

        return climbStair(n - 1, second, first + second)  // 5 -> 4 -> 3 (2->1)
    }



    /**
     * 回溯法 的中心思想：像这种强烈暗示要暴力遍历所有组合（遍历决策树）的题，明显应该采用回溯法
     */

    /**
     * 22. 括号生成
     *
     * 思路：left随时可以加，只要别用完(n) ; right必须之前有左括号，左个数>右个数
     *
     * 时间复杂度：O(4^n)，n值对应的决策树有2*n层，节点个数是1,2,4,8......，应该有2^{2n} - 1个节点，每个节点代表一个子问题，需要用O(1)O(1)时间解决，时间复杂度为O(2^{2n} - 1) = O(4^n)
     * 空间复杂度：O(n)，除了答案数组之外，需要的空间取决于递归栈的深度，每一层递归函数需要O(1)的空间，最多递归2n层，因此空间复杂度为O(n)。
     *
     * https://leetcode-cn.com/problems/generate-parentheses/
     * @param n
     * @return
     */
    fun generateParenthesis(n: Int): List<String> {
        generate(0, 0, n, "")
        return list
    }

    val list: MutableList<String> = mutableListOf()

    private fun generate(left: Int, right: Int, n: Int, s: String) {
        // 1.递归终结条件（最先写） // 肯定不合法，提前结束，即“剪枝”
        if (left > n || left < right) {
            return
        }
        if (left == n && right == n) {
            // 2.处理当前层逻辑
            list.add(s)
            return
        }

        // 3.下探到下一层
        if (left < n) {  // left随时可以加，只要别用完(n)
            generate(left + 1, right, n, s + "(")
        }

        if (left > right) { // right必须之前有左括号，左个数>右个数
            generate(left, right + 1, n, s + ")")
        }

        // 4.清理恢复当前层
    }
}
