package com.seniorlibs.algorithm.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.linked.LinkedActivity
import com.seniorlibs.algorithm.stack.getmin.IStackGetMin
import com.seniorlibs.algorithm.stack.getmin.StackGetMinOfficialA
import com.seniorlibs.algorithm.stack.twostacksqueue.ITwoStackQueue
import com.seniorlibs.algorithm.stack.twostacksqueue.TwoStackQueueOfficial
import com.seniorlibs.algorithm.string.anagram.AnagramMy

/**
 * 主页
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "main_activity_tag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_stack_get_min).setOnClickListener(this)
        findViewById<View>(R.id.two_stack_queue).setOnClickListener(this)
        findViewById<View>(R.id.anagram_my).setOnClickListener(this)
        findViewById<View>(R.id.btn_linked).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_stack_get_min -> {
                val stackGetMin: IStackGetMin<Int> = StackGetMinOfficialA()
                stackGetMin.addData()
                Log.d(TAG, "getMin功能获取的最小的值：" + stackGetMin.min)
            }
            R.id.two_stack_queue -> {
                val twoStackQueue: ITwoStackQueue<Int> = TwoStackQueueOfficial()
                twoStackQueue.addData()
                Log.d(TAG, "两个栈实现队列的接口 peek：" + twoStackQueue.peek())
                var i = 0
                while (i < 10) {
                    Log.d(TAG, "两个栈实现队列的接口 poll：" + i + " = " + twoStackQueue.poll())
                    i++
                }
            }
            R.id.anagram_my -> {
                val anagramMy = AnagramMy()
                Log.d(TAG, "判断两个字符串是否互为变形词 anagramMy1：" + anagramMy.isAnagram("abcd", "abdc"))
                Log.d(TAG, "判断两个字符串是否互为变形词 anagramMy2：" + anagramMy.isAnagram("abcd", "acdb"))
                Log.d(TAG, "判断两个字符串是否互为变形词 anagramMy3：" + anagramMy.isAnagram("abcd", "abbd"))
                Log.d(TAG, "判断两个字符串是否互为变形词 anagramMy4：" + anagramMy.isAnagram("abcd", "axcd"))
                Log.d(TAG, "判断两个字符串是否互为变形词 anagramMy5：" + anagramMy.isAnagram("aaa", "aaaa"))
            }
            R.id.btn_linked -> LinkedActivity.actionStart(this@MainActivity)
            R.id.btn_remove_duplicates -> {
                val nums: IntArray = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
                removeDuplicates(nums)
            }

            else -> {
            }
        }
    }

    /**
     * 删除排序数组中的重复项
     */
    private fun removeDuplicates(nums: IntArray) {
        return nums?.let {
            var p = 0
            var q = 1

            while (q < nums.size) {
                if (nums[p] != nums[q]) {
                    if (q - p > 1) { // 避免：如果没有重复数据，q指向的元素原地复制了一遍
                        nums[p + 1] = nums[q]
                    }
                    p++
                }
                q++
            }

            p + 1
        }
    }
}