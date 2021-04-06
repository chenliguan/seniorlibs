package com.seniorlibs.algorithm.main

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.array.ArrayActivity
import com.seniorlibs.baselib.utils.LogUtils
import java.util.*

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/9/26
 * Mender:
 * Modify:
 * Description: 混合测试
 */
class AlgorithmTestActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "AlgorithmTestActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, AlgorithmTestActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_algorithm_test)

        lifecycle
        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_1).setOnClickListener(this)

    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_1 -> {
                val node = TreeNode(1)
                val node_right = TreeNode(2)
                node.left = null
                node.right = node_right
                val node2 = TreeNode(3)
                node_right.left = node2
                LogUtils.e(TAG, "144. 二叉树的前序遍历-迭代：${preorderTraversal1(node)}")
            }
            else -> {
            }
        }
    }


    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    /**
     * 3. 无重复字符的最长子串
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param s
     * @return
     */
    fun lengthOfLongestSubstring(s: String): Int {
        if (s.isEmpty()) return 0

        val map = mutableMapOf<Char, Int>()
        var left = 0
        var res = 0

        for (i in 0 until s.length) {
            if (map.containsKey(s[i])) {
                left = Math.max(left, map[s[i]]!!)
            }

            map[s[i]] = i + 1
            res = Math.max(res, i - left + 1)
        }

        return res
    }


    private fun findTheDifference(s: String, t: String): Char {
        var res = t[t.length - 1].toInt()

        for (i in 0 until s.length) {
            res = res xor s[i].toInt()
            res = res xor t[i].toInt()
        }

        return res.toChar()
    }

    fun hammingWeight1(n: Int): Int {
        var n = n
        var num = 0

        while (n != 0) {
            num++

            n = n and n - 1
        }

        return num
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
        if (array.size != pattern.length) return false

        val map = mutableMapOf<Any, Int>()
        for (i in 0 until pattern.length) {
            if (map.put(pattern[i], i) != map.put(array[i], i)) {
                return false
            }
        }
        return true
    }



    private var list: MutableList<Int> = mutableListOf()

    /**
     * 144. 二叉树的前序遍历：递归
     *
     * 时间复杂度：O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度：O(n)，由于使用递归，将会使用隐式栈空间，递归深度会达到 n 层。
     *
     * 题解：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/144-er-cha-shu-de-qian-xu-bian-li-by-chen-li-guan/
     * @param root
     * @return
     */
    fun preorderTraversal(root: TreeNode?): List<Int?>? {
        if (root == null) return list

        list.add(root.`val`)

        preorderTraversal(root.left)

        preorderTraversal(root.right)

        return list
    }

    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    fun preorderTraversal1(root: TreeNode?): List<Int?>? {
        val res: MutableList<Int> = mutableListOf()
        if (root == null) return res

        val stack : Deque<TreeNode> = LinkedList<TreeNode>()
        stack.push(root)

        while (!stack.isEmpty()) {
            val node = stack.pop()
            if (node != null) {
                if (node.right != null) stack.push(node.right)

                if (node.left != null) stack.push(node.left)

                stack.push(node)
                stack.push(null)

            } else {
                res.add(stack.pop().`val`)
            }
        }

        return res
    }
}









