package com.seniorlibs.algorithm.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.array.ArrayActivity
import com.seniorlibs.algorithm.binarytree.BinaryTreeActivity
import com.seniorlibs.algorithm.linkedlist.LinkedActivity
import com.seniorlibs.algorithm.map.MapActivity
import com.seniorlibs.algorithm.stack.getmin.IStackGetMin
import com.seniorlibs.algorithm.stack.getmin.StackGetMinOfficialA
import com.seniorlibs.algorithm.stack.twostacksqueue.ITwoStackQueue
import com.seniorlibs.algorithm.stack.twostacksqueue.TwoStackQueueOfficial
import com.seniorlibs.algorithm.string.anagram.AnagramMy
import com.seniorlibs.baselib.utils.LogUtils

/**
 * 主页
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "MainActivity"
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
        findViewById<View>(R.id.btn_linked_list).setOnClickListener(this)
        findViewById<View>(R.id.btn_array).setOnClickListener(this)
        findViewById<View>(R.id.btn_binary_tree).setOnClickListener(this)
        findViewById<View>(R.id.btn_map).setOnClickListener(this)
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
            R.id.btn_linked_list -> LinkedActivity.actionStart(this)
            R.id.btn_array -> ArrayActivity.actionStart(this)
            R.id.btn_binary_tree -> BinaryTreeActivity.actionStart(this)
//            R.id.btn_map -> { MapActivity.actionStart(this) }
            R.id.btn_map -> {

            }

            else -> {
            }
        }
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun maxDepth(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }

        val left = maxDepth(root.left) + 1
        val right = maxDepth(root.right) + 1
        return Math.max(left, right)
    }
}
