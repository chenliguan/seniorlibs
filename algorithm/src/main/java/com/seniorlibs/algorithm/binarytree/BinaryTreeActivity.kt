package com.seniorlibs.algorithm.binarytree

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.baselib.utils.LogUtils
import java.util.*


/**
 * 二叉树
 */
class BinaryTreeActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "BinaryTreeActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, BinaryTreeActivity::class.java)
            context.startActivity(intent)
        }
    }

    private var list: MutableList<Int> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binary_tree)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_n_preorder).setOnClickListener(this)
        findViewById<View>(R.id.btn_n_postorder).setOnClickListener(this)
        findViewById<View>(R.id.btn_max_depth).setOnClickListener(this)
        findViewById<View>(R.id.btn_min_depth).setOnClickListener(this)
        findViewById<View>(R.id.btn_ineorder).setOnClickListener(this)
        findViewById<View>(R.id.btn_preorder).setOnClickListener(this)
        findViewById<View>(R.id.btn_postorder).setOnClickListener(this)
        findViewById<View>(R.id.btn_is_valid_BST).setOnClickListener(this)
        findViewById<View>(R.id.btn_build_tree).setOnClickListener(this)
        findViewById<View>(R.id.btn_level_order_bottom).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_n_preorder -> {
                list.clear()
                val node = Node(1)

                val node1 = Node(3)
                node1.children.add(Node(5))
                node1.children.add(Node(6))
                node.children.add(node1)

                node.children.add(Node(2))
                node.children.add(Node(4))

                LogUtils.e(TAG, "589. N叉数的前序遍历-递归：${preorder(node)}")   // [1, 3, 5, 6, 2, 4]
                LogUtils.e(TAG, "589. N叉数的前序遍历-迭代：${preorder1(node)}")
            }
            R.id.btn_n_postorder -> {
                list.clear()
                val node = Node(1)

                val node1 = Node(3)
                node1.children.add(Node(5))
                node1.children.add(Node(6))
                node.children.add(node1)

                node.children.add(Node(2))
                node.children.add(Node(4))

                LogUtils.e(TAG, "590. N叉树的后序遍历-递归：${postorder(node)}")   // [5, 6, 3, 2, 4, 1]
                LogUtils.e(TAG, "590. N叉树的后序遍历-迭代：${postorder1(node)}")
            }
            R.id.btn_max_depth -> {
                val node = TreeNode(1)
                val node1 = TreeNode(5)
                node.left = node1
                val node2 = TreeNode(8)
                node1.left = node2
                val node3 = TreeNode(10)
                node2.left = node3
                node1.right = TreeNode(4)
                LogUtils.e(TAG, "104. 二叉树的最大深度：${maxDepth(node)}") // 4
            }
            R.id.btn_min_depth -> {
                val node = TreeNode(1)
                val node1 = TreeNode(5)
                node.left = node1
                val node2 = TreeNode(8)
                node1.left = node2
                val node3 = TreeNode(10)
                node2.left = node3
                node1.right = TreeNode(4)
                LogUtils.e(TAG, "111. 二叉树的最小深度：${minDepth(node)}") // 3
            }
            R.id.btn_preorder -> {
                list.clear()
                val node = TreeNode(1)
                val node_right = TreeNode(2)
                node.left = null
                node.right = node_right
                val node2 = TreeNode(3)
                node_right.left = node2
                LogUtils.e(TAG, "144. 二叉树的前序遍历-递归：${preorderTraversal(node)}")
                LogUtils.e(TAG, "144. 二叉树的前序遍历-迭代：${preorderTraversal1(node)}")
            }
            R.id.btn_ineorder -> {
                list.clear()
                val node = TreeNode(1)
                val node_right = TreeNode(2)
                node.left = null
                node.right = node_right
                val node2 = TreeNode(3)
                node_right.left = node2
                LogUtils.e(TAG, "94. 二叉树的中序遍历-递归：${inorderTraversal(node)}")
                LogUtils.e(TAG, "94. 二叉树的中序遍历-迭代：${inorderTraversal1(node)}")
            }
            R.id.btn_postorder -> {
                list.clear()
                val node = TreeNode(1)
                val node_right = TreeNode(2)
                node.left = null
                node.right = node_right
                val node2 = TreeNode(3)
                node_right.left = node2
                LogUtils.e(TAG, "145. 二叉树的后序遍历-递归：${postorderTraversal(node)}")
                LogUtils.e(TAG, "145. 二叉树的后序遍历-迭代：${postorderTraversal1(node)}")
            }
            R.id.btn_is_valid_BST -> {
                pre = Long.MIN_VALUE

                val node = TreeNode(4)
                val node_right = TreeNode(5)
                node.right = node_right
                val node_right2 = TreeNode(6)
                node_right.right = node_right2

                val node_left = TreeNode(2)
                node.left = node_left
                val node_left2 = TreeNode(1)
                node_left.left = node_left2
                val node_left_right = TreeNode(3)
                node_left.right = node_left_right

                LogUtils.e(TAG, "98. 验证二叉搜索树：${isValidBST(node)}")
            }
            R.id.btn_build_tree -> {
                LogUtils.e(TAG, "105. 从前序与中序遍历序列构造二叉树：${buildTree(intArrayOf(3, 9, 20, 15, 7), intArrayOf(9, 3, 15, 20, 7))}")
            }
            R.id.btn_level_order_bottom -> {
                val node = TreeNode(3)
                val node_left = TreeNode(9)
                val node_right = TreeNode(20)
                node.left = node_left
                node.right = node_right

                val node_right2 = TreeNode(7)
                node_right.right = node_right2
                val node_left2 = TreeNode(15)
                node_right.left = node_left2

                LogUtils.e(TAG, "107. 二叉树的层次遍历 -- 方法一：BFS广度遍历-迭代：${levelOrder(node)}")
                LogUtils.e(TAG, "107. 二叉树的层次遍历 -- 方法二：DFS深度遍历-递归：${levelOrder1(node)}")
            }
            else -> {
            }
        }
    }


    class Node(var `val`: Int) {
        var children = mutableListOf<Node>()
    }


    /**
     * 589. N叉数的前序遍历：递归
     * 思路：（1）二叉树：根->左->右；（2）N叉树：根->子节点；
     *
     * 时间复杂度为O(n)：N叉树的每个节点最多被访问一次，因此时间复杂度为O(n)
     * 空间复杂度：O(n)，由于使用递归，将会使用隐式栈空间，递归深度会达到 n 层。
     *
     * 题解：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/solution/589-ncha-shu-de-qian-xu-bian-li-by-chen-li-guan/
     * @param root
     * @return
     */
    fun preorder(root: Node?): List<Int> {
        if (root == null) return list

        list.add(root.`val`)

        root.children.forEach {
            preorder(it)
        }

        return list
    }

    /**
     * 589. N叉数的前序遍历：迭代
     * 思路：（1）二叉树：根->左->右；（2）N叉树：根->子节点；
     *
     * 时间复杂度：O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度：O(n)，空间复杂度与系统堆栈有关，系统栈需要记住每个节点的值，所以空间复杂度为O(n)。
     *
     * 题解：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/solution/589-ncha-shu-de-qian-xu-bian-li-by-chen-li-guan/
     * @param root
     * @return
     */
    fun preorder1(root: Node?): List<Int> {
        val res: MutableList<Int> = mutableListOf()
        val stack: Deque<Node?> = LinkedList()

        if (root == null) return res else stack.push(root)

        while (!stack.isEmpty()) {
            val p = stack.pop()        // 将根节点弹出
            res.add(p!!.`val`)                 // 加入到结果集合中

            for (i in p.children.size - 1 downTo 0) {  // 将该节点的子节点从右往左压入栈
                stack.push(p.children[i])
            }
        }
        return res
    }

    /**
     * 590. N叉树的后序遍历：递归
     * 思路：（1）二叉树：左->右->根；（2）N叉树：子节点->根；
     *
     * 时间复杂度为O(n)：N叉树的每个节点最多被访问一次，因此时间复杂度为O(n)
     * 空间复杂度：O(n)，由于使用递归，将会使用隐式栈空间，递归深度会达到 n 层。
     *
     * 题解：https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/solution/590-ncha-shu-de-hou-xu-bian-li-by-chen-li-guan/
     * @param root
     * @return
     */
    fun postorder(root: Node?): List<Int> {
        if (root == null) return list

        root.children.forEach {
            postorder(it)
        }

        list.add(root.`val`)

        return list
    }

    /**
     * 590. N叉树的后序遍历：迭代
     * 思路：（1）二叉树：左->右->根；（2）N叉树：子节点->根；
     *
     * 时间复杂度：O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度：O(n)，空间复杂度与系统堆栈有关，系统栈需要记住每个节点的值，所以空间复杂度为O(n)。
     *
     * 题解：https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/solution/590-ncha-shu-de-hou-xu-bian-li-by-chen-li-guan/
     * @param root
     * @return
     */
    fun postorder1(root: Node?): List<Int> {
        val res: MutableList<Int> = mutableListOf()
        val stack: Deque<Node?> = LinkedList()

        if (root == null) return res else stack.push(root)

        while (!stack.isEmpty()) {
            val node = stack.pop()        // 将根节点弹出
            res.add(node!!.`val`)                 // 加入到结果集合中

            for (i in 0 until node.children.size) {  // 将该节点的子节点从左往右压入栈
                stack.push(node.children[i])
            }
        }
        return res.reversed()  // 最后将list反转
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    /**
     * 104.二叉树的最大深度
     *
     * 时间复杂度：O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度：O(n)，由于使用递归，将会使用隐式栈空间，递归深度会达到 n 层。
     *
     * @param root
     * @return
     */
    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0

        val left = maxDepth(root.left) + 1

        val right = maxDepth(root.right) + 1

        return Math.max(left, right)
    }

    /**
     * 111.二叉树的最小深度
     *
     * 时间复杂度：O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度：O(n)，由于使用递归，将会使用隐式栈空间，递归深度会达到 n 层。
     *
     * @param root
     * @return
     */
    fun minDepth(root: TreeNode?): Int {
        if (root == null) return 0

        // 左子树的最小深度
        val left = minDepth(root.left)
        // 右子树的最小深度
        val right = minDepth(root.right)

        // 如果left和right都为0，返回1即可;
        // 如果left和right只有一个为0，说明他只有一个子结点，只需要返回它另一个子节点的最小深度+1即可;
        // 如果left和right都不为0，说明他有两个子节点，只需要返回最小深度的+1即可。
        return if ((left == 0 || right == 0)) left + right + 1 else Math.min(left, right) + 1
    }

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

    /**
     * 94. 二叉树的中序遍历：递归
     *
     * 时间复杂度：O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度：O(n)，由于使用递归，将会使用隐式栈空间，递归深度会达到 n 层。
     *
     * 题解：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/94-er-cha-shu-de-zhong-xu-bian-li-by-chen-li-guan/
     * @param root
     * @return
     */
    fun inorderTraversal(root: TreeNode?): List<Int> {
        if (root == null) return list

        inorderTraversal(root.left)

        list.add(root.`val`)

        inorderTraversal(root.right)

        return list
    }

    /**
     * 145. 二叉树的后序遍历：递归
     *
     * 时间复杂度：O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度：O(n)，由于使用递归，将会使用隐式栈空间，递归深度会达到 n 层。
     *
     * 题解：https://leetcode-cn.com/problems/binary-tree-postorder-traversal/solution/145-er-cha-shu-de-hou-xu-bian-li-by-chen-li-guan/
     * @param root
     * @return
     */
    fun postorderTraversal(root: TreeNode?): List<Int> {
        if (root == null) return list

        postorderTraversal(root.left)

        postorderTraversal(root.right)

        list.add(root.`val`)

        return list
    }


    /**
     * 144. 二叉树的前序遍历：迭代
     *
     * 时间复杂度：O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度：O(n)，空间复杂度与系统堆栈有关，系统栈需要记住每个节点的值，所以空间复杂度为O(n)。
     *
     * 题解：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/144-er-cha-shu-de-qian-xu-bian-li-by-chen-li-guan/
     * @param root
     * @return
     */
    fun preorderTraversal1(root: TreeNode?): List<Int> {
        val res: MutableList<Int> = mutableListOf()
        val stack: Deque<TreeNode?> = LinkedList()

        if (root == null) return res else stack.push(root)

        while (!stack.isEmpty()) {
            val node = stack.pop()  // 将根节点弹出，如果为标记null，则是将空节点弹出即可；如果不为null，下面再将根节点添加到栈中
            if (node != null) {
                if (node.right != null) stack.push(node.right)   // 添加右节点
                if (node.left != null) stack.push(node.left)     // 添加左节点
                stack.push(node)               // 添加根节点
                stack.push(null)            // 根节点访问过，但还没有处理，需要做一下标记null
            } else {                           // 遇到标记，弹出栈顶元素，加入到集合中
                res.add(stack.pop()!!.`val`)
            }
        }
        return res
    }

    /**
     * 94. 二叉树的中序遍历：迭代
     *
     * 时间复杂度：O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度：O(n)，空间复杂度与系统堆栈有关，系统栈需要记住每个节点的值，所以空间复杂度为O(n)。
     *
     * 题解：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/94-er-cha-shu-de-zhong-xu-bian-li-by-chen-li-guan/
     * @param root
     * @return
     */
    fun inorderTraversal1(root: TreeNode?): List<Int> {
        val res: MutableList<Int> = mutableListOf()
        val stack: Deque<TreeNode?> = LinkedList()

        if (root == null) return res else stack.push(root)

        while (!stack.isEmpty()) {
            val node = stack.pop()     // 将根节点弹出，如果为标记null，则是将空节点弹出即可；如果不为null，下面再将根节点添加到栈中
            if (node != null) {
                if (node.right != null) stack.push(node.right)   // 添加右节点
                stack.push(node)                  // 添加根节点
                stack.push(null)               // 根节点访问过，但还没有处理，需要做一下标记null
                if (node.left != null) stack.push(node.left)     // 添加左节点
            } else {
                res.add(stack.pop()!!.`val`)      // 遇到标记，弹出栈顶元素，加入到集合中
            }
        }
        return res
    }

    /**
     * 145. 二叉树的后序遍历：迭代
     *
     * 时间复杂度：O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度：O(n)，空间复杂度与系统堆栈有关，系统栈需要记住每个节点的值，所以空间复杂度为O(n)。
     *
     * 题解：https://leetcode-cn.com/problems/binary-tree-postorder-traversal/solution/145-er-cha-shu-de-hou-xu-bian-li-by-chen-li-guan/
     * @param root
     * @return
     */
    fun postorderTraversal1(root: TreeNode?): List<Int> {
        val res: MutableList<Int> = mutableListOf()
        val stack: Deque<TreeNode?> = LinkedList()

        if (root == null) return res else stack.push(root)

        while (!stack.isEmpty()) {
            val node = stack.pop()    // 将根节点弹出，如果为标记null，则是将空节点弹出即可；如果不为null，下面再将根节点添加到栈中
            if (node != null) {
                stack.push(node)                  // 添加根节点
                stack.push(null)            // 根节点访问过，但还没有处理，需要做一下标记null
                if (node.right != null) stack.push(node.right)    // 添加右节点
                if (node.left != null) stack.push(node.left)      // 添加左节点
            } else {                           // 遇到标记，模拟执行方法内部操作
                res.add(stack.pop()!!.`val`)
            }
        }
        return res
    }


    /**
     * 98. 验证二叉搜索树
     *
     * 方法二：中序遍历
     * 思路：二叉搜索树「中序遍历」得到的值构成的序列一定是升序的，如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回false；否则满足
     *
     * 时间复杂度 : O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度 : O(n)，由于使用递归，将会使用隐式栈空间，递归深度会达到 n 层。
     *
     * 题解：
     * @param root
     * @return
     */
    var pre = Long.MIN_VALUE
    var flag: Boolean = true  // 当前节点小于是否大于前一个节点标记

    fun isValidBST(root: TreeNode?): Boolean {
        if (root == null) return true

        if (flag) isValidBST(root.left)       // 遍历左子树

        if (root.`val` <= pre) flag = false   // 如果当前节点小于等于中序遍历的前一个节点，说明不是BST，flag = false

        pre = root.`val`.toLong()             // 记录前一个节点

        if (flag) isValidBST(root.right)      // 遍历右子树

        return flag
    }

    /**
     * 98. 验证二叉搜索树
     *
     * 方法一：递归
     * 如果该二叉树的左子树不为空，则左子树上所有节点均小于它的根节点；若它的右子树不空，则右子树上所有节点均大于它的根节点；则满足BST
     *
     * 题解：
     * @param root
     * @return
     */
    fun isValidBST1(root: TreeNode?): Boolean {
        return solution(root, Long.MIN_VALUE, Long.MAX_VALUE)
    }

    private fun solution(root: TreeNode?, minValue: Long, maxValue: Long): Boolean {
        if (root == null) return true

        if (root.`val` <= minValue || root.`val` >= maxValue) return false  // 左子树节点小于它的根节点

        return solution(root.left, minValue, root.`val`.toLong())
                && solution(root.right, root.`val`.toLong(), maxValue)
    }


    /**
     * 105. 从前序与中序遍历序列构造二叉树
     *
     * 核心思想：前序遍历的根节点始终出现在数组的第一位，而中序遍历中根节点出现在数组的中间位置
     *
     * 时间复杂度：O(n)，其中n是树中的节点个数；
     * 空间复杂度：O(n)，除去返回的答案需要的O(n)空间之外，还需要使用O(n)的空间存储哈希映射，
     *       以及O(h)（其中h是树的高度）的空间表示递归时栈空间。这里h<n，所以总空间复杂度为O(n)。
     * @param preorder
     * @param inorder
     * @return
     */
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        // 构造哈希映射，帮助我们快速定位中序数组根节点
        val map: MutableMap<Int, Int> = mutableMapOf()
        for (i in inorder.indices) {
            map[inorder[i]] = i
        }

        return buildTreeHelper(preorder, 0, preorder.size, inorder, 0, inorder.size, map)
    }

    private fun buildTreeHelper(preorder: IntArray, pStart: Int, pEnd: Int,
                                inorder: IntArray, iStart: Int, iEnd: Int, map: MutableMap<Int, Int>): TreeNode? {

        if (pStart == pEnd) return null      // 前序数组为空，直接返回null

        val rootValue = preorder[pStart]     // 在前序数组中找到根节点值
        val rootNode = TreeNode(rootValue)   // 构造根节点
        val iRootIndex = map[rootValue]!!    // 在中序哈希映射中找到根节点下标

        val leftNum = iRootIndex - iStart    // 中序数组的根节点下标 与 中序起点下标 差距

        rootNode.left = buildTreeHelper(preorder, pStart + 1, pStart + 1 + leftNum,
                inorder, iStart, iRootIndex, map)    // 递归的构造左子树

        rootNode.right = buildTreeHelper(preorder, pStart + 1 + leftNum, pEnd,
                inorder, iRootIndex + 1, iEnd, map)  // 递归的构造右子树
        return rootNode
    }

    /**
     * 107. 二叉树的层次遍历 -- 方法一：BFS广度遍历-迭代
     *
     * 时间复杂度：O(n)，每个点进队出队各一次，故渐进时间复杂度为 O(n)；
     * 空间复杂度：O(n)，队列中元素的个数不超过 nn 个，故渐进空间复杂度为 O(n)。
     *
     * @param root
     * @return
     */
    fun levelOrder(root: TreeNode?): List<List<Int>>? {
        val res: MutableList<List<Int>> = mutableListOf()  // 存放最终结果的集合
        val queue: Queue<TreeNode> = LinkedList()   // 创建一个队列，将根节点放入其中

        if (root == null) return res else queue.offer(root)

        while (!queue.isEmpty()) {
            val level: MutableList<Int> = mutableListOf()
            val size: Int = queue.size   // 每次遍历的数量为队列的长度

            for (i in 0 until size) {   // 将这一层的元素全部取出，放入到结果集合
                val node = queue.poll()
                level.add(node.`val`)

                val left = node.left
                val right = node.right
                if (left != null) queue.offer(left)  // 如果节点的左右孩子不为空，放入队列
                if (right != null) queue.offer(right)
            }

            res.add(level)
        }

        return res
    }


    /**
     * 107. 二叉树的层次遍历 -- DFS深度遍历-递归
     *
     * 思想：每次递归的时候都需要带一个 index(表示当前的层数)，也就对应那个田字格子中的第几行，如果当前行对应的 list 不存在，就加入一个空 list 进去。
     *
     * 时间复杂度：O(n)，每个点进队出队各一次，故渐进时间复杂度为 O(n)；
     * 空间复杂度：O(h)，h 是树的高度。
     *
     * @param root
     * @return
     */
    fun levelOrder1(root: TreeNode?): List<List<Int>>? {
        val res: MutableList<MutableList<Int>> = mutableListOf()  // 存放最终结果的集合
        if (root == null) {
            return res
        }

        dfsLevel(1, root, res)
        return res
    }

    fun dfsLevel(index: Int, root: TreeNode?, res: MutableList<MutableList<Int>>) {
        if (root == null) {
            return
        }

        if (res.size < index) {      // 假设res是[ [1],[2,3] ]， index是3，就再插入一个空list放到res中
            res.add(mutableListOf())
        }

        res[index - 1].add(root.`val`)   // 将当前节点的值加入到res中，index代表当前层，假设index是3，节点值是6。res是[ [1],[2,5],[3,4] ]，加入后res就变为 [ [1],[2,5],[3,4,6] ]

        if (root.left != null) {    // 递归的处理左子树，右子树，同时将层数index+1
            dfsLevel(index + 1, root.left, res)
        }
        if (root.right != null) {
            dfsLevel(index + 1, root.right, res)
        }
    }
}
