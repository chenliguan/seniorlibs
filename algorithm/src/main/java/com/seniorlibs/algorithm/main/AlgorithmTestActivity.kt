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
                isValid("){")
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

    fun isValid(s: String): Boolean {
        val stack : Deque<Char> = LinkedList<Char>()
        for (char in s) {
            if (char == '(') {
                stack.push(')')
            } else if (char == '[') {
                stack.push(']')
            } else if (char == '{') {
                stack.push('}')
            } else if (stack.isEmpty() || stack.pop() != char) {
                return false
            }
        }

        return stack.isEmpty()
    }


    fun mergeSort(array: IntArray, left: Int, right: Int) {
        if (left >= right) return

        // 分治
        val mid = (left + right) / 2

        // 递归排序
        mergeSort(array, left, mid)

        mergeSort(array, mid + 1, right)

        // 合并
        merge(array, left, mid, right)
    }

    fun merge(array: IntArray, left: Int, mid: Int, right: Int) {
        val temp = IntArray(right - left + 1)
        var i = left
        var j = mid + 1
        var k = 0

        while (i <= mid && j <= right){
            temp[k++] = if (array[i] < array[j]) array[i++] else array[j++]
        }

        while (i <= mid) {
            temp[k++] = array[i++]
        }
        while (j <= mid) {
            temp[k++] = array[j++]
        }

        for(p in temp.indices) {
            array[left + p] = temp[p]
        }
    }

    /**
     * 思想：首先在未排序序列中找到最小（大）元素，存放到 前面已排序数组的 末尾。
     *
     * @param array
     * @return
     */
    fun selectSort(array: IntArray) : IntArray {
        if (array.isEmpty()) return array

        for (i in array.indices) {
            var minIndex = i
            for (j in i + 1 until array.size) {
                if (array[j] < array[minIndex]) {
                    minIndex = j
                }
            }

            val temp = array[i]
            array[i] = array[minIndex]
            array[minIndex] = temp
        }

        return array
    }


    /**
     * 思想：将 未排序的数组 的第一个元素先赋值给临时变量，然后把比插入元素大的元素逐个后移，最后空出一个位置，把临时变量赋值给这个空位；
     *
     * @param array
     * @return
     */
    fun insertSort(array: IntArray) : IntArray {
        if (array.isEmpty()) return array

        for (i in 1 until array.size) {
            val temp = array[i]
            var j = i - 1

            while (j >= 0 && temp < array[j]) {
                array[j+1] = array[j]
                j--
            }

            array[j + 1] = temp
        }

        return array
    }

    /**
     * 思想：进行 n-1 趟比较并交换，将大小不相等的相邻元素两两交换，每一趟会将最小或最大的元素“冒”到 后面已排序数组 前面
     *
     * @param array
     * @return
     */
    fun bubbleSort(array: IntArray): IntArray {
        if (array.isEmpty()) return array

        for (i in 0 until array.size) {
            var flag = false
            for (j in 0 until array.size - i - 1) {
                if (array[j] > array[j+1]) {
                    val temp = array[j]
                    array[j] = array[j+1]
                    array[j+1] = temp
                    flag = true
                }
            }
            if (!flag) break
        }

        return array
    }
}









