package com.seniorlibs.algorithm.greedy

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
 * Description: 贪心算法
 */
class GreedyActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "GreedyActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, GreedyActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greedy)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_can_jump).setOnClickListener(this)
        findViewById<View>(R.id.btn_jump).setOnClickListener(this)
        findViewById<View>(R.id.btn_can_complete_circuit).setOnClickListener(this)
        findViewById<View>(R.id.btn_maxi_mum_swap).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_can_jump -> {
                LogUtils.e(TAG, "55. 跳跃游戏：${canJump(intArrayOf(2, 3, 1, 1, 4))}")
            }
            R.id.btn_jump -> {
                LogUtils.e(TAG, "45. 跳跃游戏 ||：${jump(intArrayOf(2, 3, 1, 1, 4))}")
            }
            R.id.btn_can_complete_circuit -> {
                LogUtils.e(
                    TAG,
                    "134. 加油站：${canCompleteCircuit(
                        intArrayOf(1, 2, 3, 4, 5),
                        intArrayOf(3, 4, 5, 1, 2)
                    )}"
                )
            }
            R.id.btn_maxi_mum_swap -> {
                LogUtils.e(TAG, "670. 最大交换：${maximumSwap(2736)}")
            }
            else -> {
            }
        }
    }

    /**
     * 55. 跳跃游戏
     * 思想：跳几步无所谓，关键在于可跳的覆盖范围。问题就转化为跳跃覆盖范围究竟可不可以覆盖到终点！
     * 贪心算法局部最优解：每次取最大跳跃步数（取最大覆盖范围），最后得到整体最大覆盖范围，看是否能到终点。
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(1)。
     * @param nums
     * @return
     */
    fun canJump(nums: IntArray): Boolean {
        var cover = 0
        for (i in 0 until nums.size - 1) {
            // 不断计算可以覆盖的最大范围
            cover = Math.max(cover, i + nums[i])
            // 可能碰到了 0，卡住跳不动了 [0,2,3]
            if (cover <= i) return false
        }
        // 说明可以覆盖到终点了
        return cover >= nums.size - 1
    }

    /**
     * 45. 跳跃游戏 2
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(1)。
     * @param nums
     * @return
     */
    fun jump(nums: IntArray): Int {
        // 当前覆盖的最远距离下标
        var end = 0
        // 总覆盖的最远距离下标
        var cover = 0
        // 记录了跳跃次数
        var jumps = 0

        for (i in 0 until nums.size - 1) {
            cover = Math.max(nums[i] + i, cover)
            // i 等于 当前覆盖的最远距离下标时，跳跃到下一个覆盖范围，更新为当前覆盖范围
            if (i == end) {
                jumps++
                end = cover
            }
        }
        return jumps
    }

    /**
     * 134. 加油站
     *
     * 时间复杂度：O(N)；
     * 空间复杂度：O(1)；
     *
     * https://leetcode-cn.com/problems/gas-station/solution/134-jia-you-zhan-by-chen-li-guan-rse3/
     * @param G
     * @param C
     * @return
     */
    fun canCompleteCircuit(G: IntArray, C: IntArray): Int {
        // 出发时加油站的编号
        var res = 0
        // 车里剩余的油量
        var left = 0
        // 遍历计算每个加油站的总油量
        var total = 0

        for (i in 0 until G.size) {
            val get = G[i]  // 第 i 个加油站的汽油 G[i] 升
            val cost = C[i] // 从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 C[i] 升

            total += get - cost

            // 如果油量足够，还能开到下一站，那么继续开
            if (left + get - cost >= 0) {
                left += get - cost
            } else {
                // 油量不够，尝试新加油站出发
                left = 0
                res = i + 1
            }
        }

        // 当总和 >= 0 时有解，返回 res
        return if (total >= 0) res else -1
    }


    /**
     * 670. 最大交换
     * 思路：核心，就是把第一个小数和它后面最大的大数进行交换
     *
     * 时间复杂度：O(N)。其中 N 是输入数字的总位数。每个数字最多只考虑一次。
     * 空间复杂度：O(1)，last 使用的额外空间最多只有 10 个。
     *
     * https://leetcode-cn.com/problems/maximum-swap/solution/670-zui-da-jiao-huan-by-chen-li-guan-x4ig/
     *
     * @param num
     * @return
     */
    fun maximumSwap(num: Int): Int {
        val s = num.toString()
        val ch = s.toCharArray()

        // 记录每个数字出现的最后一次出现的下标
        val array = IntArray(10)
        for (i in 0 until s.length) {
            array[ch[i] - '0'] = i
        }

        // 原数组从左到右遍历，找到当前位置右边的最大的数字并交换
        for (i in 0 until s.length - 1) {
            // 遇到比当前位值大的，交换，因为索引数组从后往前遍历的，所以保证了值为最大
            for (j in 9 downTo ch[i] - '0' + 1) {
                if (array[j] > i) {
                    swap(ch, i, array[j])
                    // 只允许交换一次，因此直接返回
                    return String(ch).toInt()
                }
            }
        }

        return num
    }

    fun swap(ch: CharArray, index1: Int, index2: Int) {
        val temp = ch[index1]
        ch[index1] = ch[index2]
        ch[index2] = temp
    }
}
