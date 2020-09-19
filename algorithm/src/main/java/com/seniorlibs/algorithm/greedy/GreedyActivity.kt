package com.seniorlibs.algorithm.greedy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.baselib.utils.LogUtils


/**
 * 贪心算法
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
        findViewById<View>(R.id.btn_best_time_buy_stocks).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_best_time_buy_stocks -> {
                LogUtils.d(TAG, "122. 买卖股票的最佳时机 II：${maxProfit(intArrayOf(7,1,5,3,6,4))}")
            }
            else -> {
            }
        }
    }


    /**
     * 122. 买卖股票的最佳时机 II（允许多次买卖，必须在再次购买前卖掉之前的股票）
     *
     * 思想：1.连续上涨交易日：每天都买卖，则第一天买最后一天卖收益最大；
     *      2.连续下降交易日：则不买卖收益最大，即不会亏钱；
     *      3.只要当前价格大于前一天价格，就把利润锁定。
     *
     * 时间复杂度：O(n)，遍历一次；
     * 空间复杂度：O(1)，需要常量的空间。
     */
    fun maxProfit(prices: IntArray): Int {
        var profit = 0
        for (i in 1 until prices.size) {
            val tmp = prices[i] - prices[i - 1]  // 设 tmp 为第 i-1 日买入与第 i 日卖出赚取的利润
            if (tmp > 0) profit += tmp
        }
        return profit
    }
}
