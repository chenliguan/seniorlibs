package com.seniorlibs.algorithm.binarytree

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.baselib.utils.LogUtils
import java.util.*
import kotlin.collections.ArrayList

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/9/10
 * Mender:
 * Modify:
 * Description: 二叉树
 */
class BinaryTreeActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "BinaryTreeActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, BinaryTreeActivity::class.java)
            context.startActivity(intent)
        }
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    private var list = mutableListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binary_tree)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_ineorder).setOnClickListener(this)
        findViewById<View>(R.id.btn_preorder).setOnClickListener(this)
        findViewById<View>(R.id.btn_postorder).setOnClickListener(this)
        findViewById<View>(R.id.btn_n_preorder).setOnClickListener(this)
        findViewById<View>(R.id.btn_n_postorder).setOnClickListener(this)
        findViewById<View>(R.id.btn_binary_tree_paths).setOnClickListener(this)
        findViewById<View>(R.id.btn_max_depth).setOnClickListener(this)
        findViewById<View>(R.id.btn_min_depth).setOnClickListener(this)
        findViewById<View>(R.id.btn_has_path_sum).setOnClickListener(this)
        findViewById<View>(R.id.btn_path_sum).setOnClickListener(this)
        findViewById<View>(R.id.btn_is_valid_bst).setOnClickListener(this)
        findViewById<View>(R.id.btn_pre_in_build_tree).setOnClickListener(this)
        findViewById<View>(R.id.btn_in_post_build_tree).setOnClickListener(this)
        findViewById<View>(R.id.btn_invert_tree).setOnClickListener(this)
        findViewById<View>(R.id.btn_level_order).setOnClickListener(this)
        findViewById<View>(R.id.btn_level_order_bottom).setOnClickListener(this)
        findViewById<View>(R.id.btn_largest_values).setOnClickListener(this)
        findViewById<View>(R.id.btn_right_side_view).setOnClickListener(this)
        findViewById<View>(R.id.btn_zigzag_level_order).setOnClickListener(this)
        findViewById<View>(R.id.btn_is_symmetric).setOnClickListener(this)
        findViewById<View>(R.id.btn_lowest_common_ancestor).setOnClickListener(this)
        findViewById<View>(R.id.btn_flatten).setOnClickListener(this)
        findViewById<View>(R.id.btn_diameter_of_binary_tree).setOnClickListener(this)
        findViewById<View>(R.id.btn_is_balanced).setOnClickListener(this)
        findViewById<View>(R.id.btn_sorted_array_to_bst).setOnClickListener(this)
        findViewById<View>(R.id.btn_sorted_list_to_bst).setOnClickListener(this)
        findViewById<View>(R.id.btn_count_nodes).setOnClickListener(this)
        findViewById<View>(R.id.btn_is_sub_structure).setOnClickListener(this)
        findViewById<View>(R.id.btn_ladder_length).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
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
            R.id.btn_binary_tree_paths -> {
                val node = TreeNode(1)
                val node1 = TreeNode(5)
                node.left = node1
                val node2 = TreeNode(8)
                node1.left = node2
                val node3 = TreeNode(10)
                node2.left = node3
                node1.right = TreeNode(4)
                LogUtils.e(TAG, "257. 二叉树的所有路径：${binaryTreePaths(node)}")
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
                LogUtils.e(TAG, "104. 二叉树的最大深度1：${maxDepth1(node)}") // 4
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
                LogUtils.e(TAG, "111. 二叉树的最小深度1：${minDepth1(node)}") // 3
            }
            R.id.btn_has_path_sum -> {
                val node = TreeNode(1)
                val node1 = TreeNode(5)
                node.left = node1
                val node2 = TreeNode(8)
                node1.left = node2
                val node3 = TreeNode(10)
                node2.left = node3
                node1.right = TreeNode(4)
                LogUtils.e(TAG, "112. 路径总和：${hasPathSum(node, 22)}")
                LogUtils.e(TAG, "112. 路径总和：${hasPathSum1(node, 22)}")
            }
            R.id.btn_path_sum -> {
                val node = TreeNode(1)
                val node1 = TreeNode(5)
                node.left = node1
                val node2 = TreeNode(8)
                node1.left = node2
                val node3 = TreeNode(10)
                node2.left = node3
                node1.right = TreeNode(4)
                LogUtils.e(TAG, "113. 路径总和 II：${pathSum(node, 22)}")
            }
            R.id.btn_is_valid_bst -> {
                pre = Long.MIN_VALUE

                val node = TreeNode(10)
                val node_right = TreeNode(15)
                node.right = node_right
                val node_right2 = TreeNode(20)
                node_right.right = node_right2
                val node_left2 = TreeNode(6)
                node_right.left = node_left2

                val node_left = TreeNode(5)
                node.left = node_left

                LogUtils.e(TAG, "98. 验证二叉搜索树：${isValidBST(node)}")
                LogUtils.e(TAG, "98. 验证二叉搜索树：${isValidBST1(node)}")
            }
            R.id.btn_pre_in_build_tree -> {
                LogUtils.e(
                    TAG,
                    "105. 从前序与中序遍历序列构造二叉树：${buildTree(
                        intArrayOf(3, 9, 20, 15, 7),
                        intArrayOf(9, 3, 15, 20, 7)
                    )}"
                )
            }
            R.id.btn_in_post_build_tree -> {
                LogUtils.e(
                    TAG, "106. 从中序与后序遍历序列构造二叉树：${buildTree1(
                        intArrayOf(9, 15, 7, 20, 3),
                        intArrayOf(9, 3, 15, 20, 7)
                    )}"
                )
            }
            R.id.btn_invert_tree -> {
                val node = TreeNode(1)
                val node1 = TreeNode(5)
                node.left = node1
                val node2 = TreeNode(8)
                node1.left = node2
                val node3 = TreeNode(10)
                node2.left = node3
                node1.right = TreeNode(4)
                LogUtils.e(TAG, "226. 翻转二叉树：${invertTree(node)}")
                LogUtils.e(TAG, "226. 翻转二叉树：${invertTree2(node)}")
            }
            R.id.btn_level_order -> {
                val node = TreeNode(3)
                val node_left = TreeNode(9)
                val node_right = TreeNode(20)
                node.left = node_left
                node.right = node_right

                val node_right2 = TreeNode(7)
                node_right.right = node_right2
                val node_left2 = TreeNode(15)
                node_right.left = node_left2

                LogUtils.e(TAG, "107. 二叉树的层序遍历 -- 方法一：BFS广度遍历-迭代：${levelOrder(node)}")
                LogUtils.e(TAG, "107. 二叉树的层序遍历 -- 方法二：DFS深度遍历-递归：${levelOrder1(node)}")
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

                LogUtils.e(TAG, "107. 二叉树的层序遍历 II -- 方法一：BFS广度遍历-迭代：${levelOrderBottom(node)}")
                LogUtils.e(TAG, "107. 二叉树的层序遍历 -- 方法二：DFS深度遍历-递归：${levelOrderBottom1(node)}")
            }
            R.id.btn_largest_values -> {
                val node = TreeNode(1)
                val node_left = TreeNode(3)
                val node_right = TreeNode(2)
                node.left = node_left
                node.right = node_right

                val node_right1 = TreeNode(3)
                node_left.right = node_right1
                val node_left1 = TreeNode(5)
                node_left.left = node_left1

                val node_right2 = TreeNode(9)
                node_right.right = node_right2

                LogUtils.e(TAG, "515. 在每个树行中找最大值 -- 方法一：BFS广度遍历-迭代：${largestValues(node)}")
                LogUtils.e(TAG, "515. 在每个树行中找最大值 -- 方法二：DFS深度遍历-递归：${largestValues1(node)}")
            }
            R.id.btn_right_side_view -> {
                val node = TreeNode(1)
                val node_left = TreeNode(3)
                val node_right = TreeNode(2)
                node.left = node_left
                node.right = node_right

                val node_right1 = TreeNode(3)
                node_left.right = node_right1
                val node_left1 = TreeNode(5)
                node_left.left = node_left1

                val node_right2 = TreeNode(9)
                node_right.right = node_right2

                LogUtils.e(TAG, "199. 二叉树的右视图 -- 方法一：BFS广度遍历-迭代：${rightSideView(node)}")
            }
            R.id.btn_zigzag_level_order -> {
                val node = TreeNode(1)
                val node_left = TreeNode(3)
                val node_right = TreeNode(2)
                node.left = node_left
                node.right = node_right

                val node_right1 = TreeNode(3)
                node_left.right = node_right1
                val node_left1 = TreeNode(5)
                node_left.left = node_left1

                val node_right2 = TreeNode(9)
                node_right.right = node_right2

                LogUtils.e(TAG, "103. 二叉树的锯齿形层序遍历：${zigzagLevelOrder(node)}")
            }
            R.id.btn_is_symmetric -> {
                val node = TreeNode(1)
                val node_left = TreeNode(3)
                val node_right = TreeNode(2)
                node.left = node_left
                node.right = node_right

                val node_right1 = TreeNode(3)
                node_left.right = node_right1
                val node_left1 = TreeNode(5)
                node_left.left = node_left1

                val node_right2 = TreeNode(9)
                node_right.right = node_right2

                LogUtils.e(TAG, "101. 对称二叉树：${isSymmetric(node)}")
                LogUtils.e(TAG, "101. 对称二叉树q：${isSymmetric2(node)}")
            }
            R.id.btn_lowest_common_ancestor -> {
                val node = TreeNode(1)
                val node_left = TreeNode(3)
                val node_right = TreeNode(2)
                node.left = node_left
                node.right = node_right

                val node_right1 = TreeNode(3)
                node_left.right = node_right1
                val node_left1 = TreeNode(5)
                node_left.left = node_left1

                val node_right2 = TreeNode(9)
                node_right.right = node_right2

                LogUtils.e(
                    TAG,
                    "236. 二叉树的最近公共祖先：${lowestCommonAncestor(node, node_left, node_right)}"
                )
            }
            R.id.btn_flatten -> {
                val node = TreeNode(1)
                val node_left = TreeNode(3)
                val node_right = TreeNode(2)
                node.left = node_left
                node.right = node_right

                val node_right1 = TreeNode(3)
                node_left.right = node_right1
                val node_left1 = TreeNode(5)
                node_left.left = node_left1

                val node_right2 = TreeNode(9)
                node_right.right = node_right2

                LogUtils.e(TAG, "114. 二叉树展开为链表：${flatten(node)}")
            }
            R.id.btn_diameter_of_binary_tree -> {
                val node = TreeNode(1)
                val node_left = TreeNode(3)
                val node_right = TreeNode(2)
                node.left = node_left
                node.right = node_right

                val node_right1 = TreeNode(3)
                node_left.right = node_right1
                val node_left1 = TreeNode(5)
                node_left.left = node_left1

                val node_right2 = TreeNode(9)
                node_right.right = node_right2

                LogUtils.e(TAG, "543. 二叉树的直径：${diameterOfBinaryTree(node)}")
            }
            R.id.btn_is_balanced -> {
                val node = TreeNode(1)
                val node_left = TreeNode(3)
                val node_right = TreeNode(2)
                node.left = node_left
                node.right = node_right

                val node_right1 = TreeNode(3)
                node_left.right = node_right1
                val node_left1 = TreeNode(5)
                node_left.left = node_left1

                val node_right2 = TreeNode(9)
                node_right.right = node_right2

                LogUtils.e(TAG, "110. 平衡二叉树：${isBalanced(node)}")
            }
            R.id.btn_sorted_array_to_bst -> {
                LogUtils.e(
                    TAG,
                    "108. 将有序数组转换为二叉搜索树：${sortedArrayToBST(intArrayOf(1, 2, 3, 4, 5, 6))}"
                )
            }
            R.id.btn_sorted_list_to_bst -> {
                LogUtils.e(
                    TAG,
                    "109. 有序链表转换二叉搜索树：${sortedListToBST(ListNode(0))}"
                )
            }
            R.id.btn_count_nodes -> {
                val node = TreeNode(1)
                val node_left = TreeNode(2)
                val node_right = TreeNode(3)
                node.left = node_left
                node.right = node_right

                val node_right1 = TreeNode(4)
                node_left.right = node_right1
                val node_left1 = TreeNode(5)
                node_left.left = node_left1

                val node_right2 = TreeNode(9)
                node_right.left = node_right2
                LogUtils.e(TAG, "222. 完全二叉树的节点个数：${countNodes(node)}")
                LogUtils.e(TAG, "222. 完全二叉树的节点个数2：${countNodes2(node)}")
            }
            R.id.btn_is_sub_structure -> {
                val node = TreeNode(1)
                val node_left = TreeNode(2)
                val node_right = TreeNode(3)
                node.left = node_left
                node.right = node_right

                val node_right1 = TreeNode(4)
                node_left.right = node_right1
                val node_left1 = TreeNode(5)
                node_left.left = node_left1

                val node_right2 = TreeNode(9)
                node_right.left = node_right2
                LogUtils.e(TAG, "剑指 Offer 26. 树的子结构：${isSubStructure(node, node_right1)}")
            }
            R.id.btn_ladder_length -> {
                val list = mutableListOf("hot", "dot", "dog", "lot", "log", "cog")
                LogUtils.e(TAG, "127. 单词接龙 -- 方法一：双向BFS：" + "${ladderLength("hit", "cog", list)}")
            }
            else -> {
            }
        }
    }


    class Node(var `val`: Int) {
        var children = mutableListOf<Node>()
    }

    class DNode {
        var `val` = 0
        var left: Node? = null
        var right: Node? = null

        constructor() {}
        constructor(_val: Int) {
            `val` = _val
        }

        constructor(_val: Int, _left: Node?, _right: Node?) {
            `val` = _val
            left = _left
            right = _right
        }
    }


    /**
     * 144. 二叉树的前序遍历：递归
     *
     * 时间复杂度：O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度：O(n)，由于使用递归，将会使用隐式栈空间，递归深度会达到 n 层。
     *
     * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/144-er-cha-shu-de-qian-xu-bian-li-by-chen-li-guan/
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
     * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/94-er-cha-shu-de-zhong-xu-bian-li-by-chen-li-guan/
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
     * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/solution/145-er-cha-shu-de-hou-xu-bian-li-by-chen-li-guan/
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
     * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/144-er-cha-shu-de-qian-xu-bian-li-by-chen-li-guan/
     * @param root
     * @return
     */
    fun preorderTraversal1(root: TreeNode?): List<Int> {
        val res = mutableListOf<Int>()
        if (root == null) return res

        val stack = LinkedList<TreeNode>()
        stack.push(root)

        while (stack.isNotEmpty()) {
            // 将根节点弹出，如果是标记null，则是将空节点弹出即可；如果不是null，下面再将根节点添加到栈中
            val node = stack.pop()
            if (node != null) {
                // 添加右节点
                if (node.right != null) stack.push(node.right)
                // 添加左节点
                if (node.left != null) stack.push(node.left)

                // 添加根节点
                stack.push(node)
                // 根节点访问过，但还没有处理，需要做一下标记null
                stack.push(null)
            } else {
                // 遇到标记，弹出栈顶元素，加入到集合中
                res.add(stack.pop().`val`)
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
     * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/94-er-cha-shu-de-zhong-xu-bian-li-by-chen-li-guan/
     * @param root
     * @return
     */
    fun inorderTraversal1(root: TreeNode?): List<Int> {
        val res = mutableListOf<Int>()
        if (root == null) return res

        val stack = LinkedList<TreeNode>()
        stack.push(root)

        while (stack.isNotEmpty()) {
            // 将根节点弹出，如果为标记null，则是将空节点弹出即可；如果不为null，下面再将根节点添加到栈中
            val node = stack.pop()
            if (node != null) {
                // 添加右节点
                if (node.right != null) stack.push(node.right)

                // 添加根节点
                stack.push(node)
                // 根节点访问过，但还没有处理，需要做一下标记null
                stack.push(null)

                // 添加左节点
                if (node.left != null) stack.push(node.left)
            } else {
                // 遇到标记，弹出栈顶元素，加入到集合中
                res.add(stack.pop().`val`)
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
     * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/solution/145-er-cha-shu-de-hou-xu-bian-li-by-chen-li-guan/
     * @param root
     * @return
     */
    fun postorderTraversal1(root: TreeNode?): List<Int> {
        val res = mutableListOf<Int>()
        if (root == null) return res

        val stack = LinkedList<TreeNode>()
        stack.push(root)

        while (stack.isNotEmpty()) {
            // 将根节点弹出，如果为标记null，则是将空节点弹出即可；如果不为null，下面再将根节点添加到栈中
            val node = stack.pop()
            if (node != null) {
                // 添加根节点
                stack.push(node)
                // 根节点访问过，但还没有处理，需要做一下标记null
                stack.push(null)

                // 添加右节点
                if (node.right != null) stack.push(node.right)

                // 添加左节点
                if (node.left != null) stack.push(node.left)
            } else {
                // 遇到标记，模拟执行方法内部操作
                res.add(stack.pop()!!.`val`)
            }
        }
        return res
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
        val res = mutableListOf<Int>()
        if (root == null) return res

        val stack = LinkedList<Node?>()
        stack.push(root)

        while (stack.isNotEmpty()) {
            // 将根节点弹出
            val node = stack.pop()
            if (node != null) {
                // 将该节点的子节点从右往左压入栈
                for (i in node.children.size - 1 downTo 0) {
                    stack.push(node.children[i])
                }

                // 添加根节点
                stack.push(node)
                // 根节点访问过，但还没有处理，需要做一下标记null
                stack.push(null)
            } else {
                // 遇到标记，弹出栈顶元素，加入到集合中
                res.add(stack.pop()!!.`val`)
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
        val res = mutableListOf<Int>()
        if (root == null) return res

        val stack = LinkedList<Node?>()
        stack.push(root)

        while (stack.isNotEmpty()) {
            // 将根节点弹出
            val node = stack.pop()
            if (node != null) {
                // 添加根节点
                stack.push(node)
                // 根节点访问过，但还没有处理，需要做一下标记null
                stack.push(null)

                // 将该节点的子节点从右往左压入栈
                for (i in node.children.size - 1 downTo 0) {
                    stack.push(node.children[i])
                }
            } else {
                // 遇到标记，弹出栈顶元素，加入到集合中
                res.add(stack.pop()!!.`val`)
            }
        }

        return res
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }


    /**
     * 257. 二叉树的所有路径
     * 思路：类似前序遍历，使用字符串拼接，避免使用回溯
     *
     * 时间复杂度：O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度：O(n)，空间复杂度与系统堆栈有关，系统栈需要记住每个节点的值，所以空间复杂度为O(n)。
     *
     * https://leetcode-cn.com/problems/binary-tree-paths/solution/257-er-cha-shu-de-suo-you-lu-jing-by-che-0o6p/
     * @param root
     * @return
     */
    fun binaryTreePaths(root: TreeNode?): List<String>? {
        val res = mutableListOf<String>()
        dfs(root, "", res)
        return res
    }

    private fun dfs(root: TreeNode?, path: String, res: MutableList<String>) {
        // 如果为空，直接返回
        if (root == null) return

        // 如果是叶子节点，说明找到了一条路径，把它加入到res中
        if (root.left == null && root.right == null) {
            res.add(path + root.`val`)
            return
        }

        // 如果不是叶子节点，在分别遍历他的左右子节点
        dfs(root.left, path + root.`val` + "->", res)

        dfs(root.right, path + root.`val` + "->", res)
    }


    /**
     * 104.二叉树的最大深度 - 方法一：DFS
     *
     * 时间复杂度：O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度：O(height)，其中height表示二叉树的高度。递归函数需要栈空间，而栈空间取决于递归的深度，因此空间复杂度等价于二叉树的高度。
     *
     * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/solution/104-er-cha-shu-de-zui-da-shen-du-by-chen-li-guan/
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
     * 104.二叉树的最大深度 - 方法二：BFS
     *
     * 时间复杂度：O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度：O(n)，空间的消耗取决于队列存储的元素数量，其在最坏情况下会达到O(n)
     *
     * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/solution/104-er-cha-shu-de-zui-da-shen-du-by-chen-li-guan/
     * @param root
     * @return
     */
    fun maxDepth1(root: TreeNode?): Int {
        if (root == null) return 0

        var level = 0
        val queue = LinkedList<TreeNode>()
        queue.offer(root)

        var size = queue.size
        while (size > 0) {
            level++
            for (i in 0 until size) {
                val node = queue.poll()
                if (node.left != null) queue.offer(node.left) // 左右子节点，哪个不为空，哪个加入到队列中
                if (node.right != null) queue.offer(node.right)
            }
            size = queue.size
        }

        return level
    }

    /**
     * 111.二叉树的最小深度 - 方法一：DFS
     *
     * 时间复杂度：O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度：O(height)，其中height是树的高度。空间复杂度主要取决于递归时栈空间的开销；
     *          最坏情况下，树呈现链状，空间复杂度为O(n)。平均情况下树的高度与节点数的对数正相关，空间复杂度为O(logN)
     *
     * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/solution/111er-cha-shu-de-zui-xiao-shen-du-by-chen-li-guan/
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
        // 如果left和right只有一个为0，说明他只有一个子结点，只需要返回它另一个子节点的最小深度即可;
        // 如果left和right都不为0，说明他有两个子节点，只需要返回最小深度的+1即可。
        return if ((left == 0 || right == 0)) left + right + 1 else Math.min(left, right) + 1
    }

    /**
     * 111.二叉树的最小深度 - 方法二：BFS
     *
     * 时间复杂度：O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度：O(n)，空间复杂度与系统堆栈有关，系统栈需要记住每个节点的值，所以空间复杂度为O(n)。
     *
     * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/solution/111er-cha-shu-de-zui-xiao-shen-du-by-chen-li-guan/
     * @param root
     * @return
     */
    fun minDepth1(root: TreeNode?): Int {
        if (root == null) return 0

        var level = 0
        val queue = LinkedList<TreeNode>()
        queue.offer(root)

        var size = queue.size
        while (size > 0) {
            level++
            for (i in 0 until size) {
                val node = queue.poll()

                if (node.left == null && node.right == null) return level

                if (node.left != null) queue.offer(node.left)
                if (node.right != null) queue.offer(node.right)
            }
            size = queue.size
        }

        return level
    }


    /**
     * 112. 路径总和  方式一：递归
     * 思想：一直向下找到叶子节点，同时判断节点的左右子树同时为空才是叶子节点
     *
     * 时间复杂度：O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度：O(height)，其中height是树的高度。空间复杂度主要取决于递归时栈空间的开销；
     *
     * https://leetcode-cn.com/problems/path-sum/solution/112-lu-jing-zong-he-by-chen-li-guan-yi5m/
     * @param root
     * @param targetSum
     * @return
     */
    fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
        // 1.递归终结条件（最先写）
        if (root == null) return false

        // 2.处理当前层逻辑：同时判断节点的左右子树同时为空才是叶子节点
        if (root.left == null && root.right == null) {
            return root.`val` == targetSum
        }

        // 3.下探到下一层，关键：sum - root.`val`
        val isLeftHas = hasPathSum(root.left, targetSum - root.`val`)

        val isRightHas = hasPathSum(root.right, targetSum - root.`val`)

        return isLeftHas || isRightHas
    }

    /**
     * 112. 路径总和  方式二：栈
     *
     * @param root
     * @param targetSum
     * @return
     */
    fun hasPathSum1(root: TreeNode?, targetSum: Int): Boolean {
        if (root == null) return false

        val stack = LinkedList<TreeNode>()
        // 根节点入栈
        stack.push(root)

        while (stack.isNotEmpty()) {
            val node = stack.pop()
            if (node != null) {
                // 添加右节点
                if (node.right != null) {
                    node.right?.`val` = node.`val` + node.right!!.`val`
                    stack.push(node.right)
                }
                // 添加左节点
                if (node.left != null) {
                    node.left?.`val` = node.`val` + node.left!!.`val`
                    stack.push(node.left)
                }

                // 添加根节点
                stack.push(node)
                // 根节点访问过，但还没有处理，需要做一下标记null
                stack.push(null)
            } else {
                val rootNode = stack.pop()
                // 遇到标记，弹出栈顶元素：累减到叶子节点之后，结果等于 targetSum，说明存在这样的一条路径
                if (rootNode?.left == null && rootNode?.right == null) {
                    if (rootNode?.`val` == targetSum) return true
                }
            }
        }
        return false
    }


    /**
     * 113. 路径总和 II == Offer 34. 二叉树中和为某一值的路径
     * 思想：一直向下找到叶子节点，同时判断节点的左右子树同时为空才是叶子节点
     *
     * 时间复杂度：O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度：O(n)。
     *
     * https://leetcode-cn.com/problems/path-sum-ii/solution/113-lu-jing-zong-he-ii-by-chen-li-guan-mx4f/
     * @param root
     * @param sum
     * @return
     */
    fun pathSum(root: TreeNode?, sum: Int): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        if (root == null) {
            return res
        }

        pathDfs(root, sum, mutableListOf(), res)
        return res
    }

    fun pathDfs(
        root: TreeNode?,
        targetSum: Int,
        list: MutableList<Int>,
        res: MutableList<List<Int>>
    ) {
        // 如果到达叶子节点，就不能往下走了，直接 return
        if (root?.left == null && root?.right == null) {
            if (targetSum - root?.`val`!! == 0) {
                list.add(root.`val`)
                res.add(ArrayList(list))
                // 把最后加入的结点值给移除掉，因为下一步直接 return 了，不会再走最后一行的 remove 了
                list.removeAt(list.size - 1)
            }
            return
        }

        // 如果没到达叶子节点，就继续从它的左右两个子节点往下找，注意 sum 要减去当前节点的值
        if (root.left != null) {
            // 把当前节点值加入到list中
            list.add(root.`val`)
            pathDfs(root.left, targetSum - root.`val`, list, res)
            // 撤销选择
            list.removeAt(list.size - 1)
        }

        if (root.right != null) {
            list.add(root.`val`)
            pathDfs(root.right, targetSum - root.`val`, list, res)
            list.removeAt(list.size - 1)
        }
    }


    /**
     * 226. 翻转二叉树  方法一：递归
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(n)。
     *
     * https://leetcode-cn.com/problems/invert-binary-tree/solution/226-fan-zhuan-er-cha-shu-by-chen-li-guan-o4wd/
     * @param root
     * @return
     */
    fun invertTree(root: TreeNode?): TreeNode? {
        // 递归函数的终止条件，节点为空时返回
        if (root == null) return null

        // 下面三句是将当前节点的左右子树交换
        val tmp = root.right
        root.right = root.left
        root.left = tmp

        // 递归交换当前节点的 左子树
        invertTree(root.left)
        // 递归交换当前节点的 右子树
        invertTree(root.right)
        // 函数返回时就表示当前这个节点，以及它的左右子树都已经交换完了
        return root
    }

    /**
     * 226. 翻转二叉树  方法二：栈
     *
     * @param root
     * @return
     */
    fun invertTree2(root: TreeNode?): TreeNode? {
        if (root == null) return root

        val stack = LinkedList<TreeNode>()
        stack.push(root)

        while (!stack.isEmpty()) {
            // 将根节点弹出，如果是标记null，则是将空节点弹出即可；如果不是null，下面再将根节点添加到栈中
            val node = stack.pop()
            if (node != null) {
                // 添加右节点
                if (node.right != null) stack.push(node.right)
                // 添加左节点
                if (node.left != null) stack.push(node.left)

                // 添加根节点
                stack.push(node)
                // 根节点访问过，但还没有处理，需要做一下标记null
                stack.push(null)
            } else {
                // 遇到标记，弹出栈顶元素
                val rootNode = stack.pop()

                // 下面三句是将当前节点的左右子树交换
                val tmp = rootNode.right
                rootNode.right = rootNode.left
                rootNode.left = tmp
            }
        }
        return root
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

    fun isValidBST(root: TreeNode?): Boolean {
        if (root == null) return true

        // 访问左子树
        if (!isValidBST(root.left)) {
            return false
        }

        // 访问当前节点：如果当前节点 <= 中序遍历的前一个节点，说明不满足BST，返回 false。否则继续遍历。
        if (root.`val` <= pre) {
            return false
        }
        pre = root.`val`.toLong()

        // 访问右子树
        return isValidBST(root.right)
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
                && solution(
            root.right, root.`val`.toLong(), maxValue
        )
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     *
     * 核心思想：前序遍历的根节点始终出现在数组的第一位，而中序遍历中根节点出现在数组的中间位置
     *
     * 时间复杂度：O(n)，其中n是树中的节点个数；
     * 空间复杂度：O(n)，除去返回的答案需要的O(n)空间之外，还需要使用O(n)的空间存储哈希映射，
     *       以及O(h)（其中h是树的高度）的空间表示递归时栈空间。这里h<n，所以总空间复杂度为O(n)。
     *
     * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/105-cong-qian-xu-yu-zhong-xu-bian-li-xu-lie-gou-42/
     * @param preorder
     * @param inorder
     * @return
     */
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        // 构造哈希映射，帮助我们快速定位中序数组根节点
        val map = mutableMapOf<Int, Int>()
        for (i in 0 until inorder.size) {
            map[inorder[i]] = i
        }

        return buildTrees(
            preorder, 0, preorder.size - 1,
            inorder, 0, inorder.size - 1, map
        )
    }

    private fun buildTrees(
        preorder: IntArray, preStart: Int, preEnd: Int,
        inorder: IntArray, inStart: Int, inEnd: Int, map: MutableMap<Int, Int>
    ): TreeNode? {
        // 1.递归终结条件：当后指针在前指针前时，返回 null 结束
        if (preStart > preEnd) return null

        // 2.处理当前层逻辑
        // 在前序数组中找到根节点值
        val rootVal = preorder[preStart]
        // 根据根节点值，从map中找到根节点下标
        val rootIndex = map[rootVal]!!
        // 中序数组的根节点下标 与 中序起点下标 差距
        val leftSize = rootIndex - inStart

        // 3.下探到下一层，
        // 构造根节点
        val rootNode = TreeNode(rootVal)
        // 递归的构造左子树
        rootNode.left = buildTrees(
            preorder, preStart + 1, preStart + leftSize,
            inorder, inStart, rootIndex, map
        )

        // 递归的构造右子树
        rootNode.right = buildTrees(
            preorder, preStart + leftSize + 1, preEnd,
            inorder, rootIndex + 1, inEnd, map
        )
        return rootNode
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     *
     * 核心思想：后序遍历的根节点始终出现在数组的最后一位，而中序遍历中根节点出现在数组的中间位置
     *
     * 时间复杂度：O(n)，其中n是树中的节点个数；
     * 空间复杂度：O(n)，除去返回的答案需要的O(n)空间之外，还需要使用O(n)的空间存储哈希映射，
     *       以及O(h)（其中h是树的高度）的空间表示递归时栈空间。这里h<n，所以总空间复杂度为O(n)。
     *
     * https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/solution/106-cong-zhong-xu-yu-hou-xu-bian-li-xu-lie-gou-2-4/
     * @param preorder
     * @param inorder
     * @return
     */
    fun buildTree1(postorder: IntArray, inorder: IntArray): TreeNode? {
        // 构造哈希映射，帮助我们快速定位中序数组根节点
        val map = mutableMapOf<Int, Int>()
        for (i in 0 until inorder.size) {
            map[inorder[i]] = i
        }

        return buildTree1s(
            postorder, 0, postorder.size - 1,
            inorder, 0, inorder.size - 1, map
        )
    }

    private fun buildTree1s(
        postorder: IntArray, postStart: Int, postEnd: Int,
        inorder: IntArray, inStart: Int, inEnd: Int, map: MutableMap<Int, Int>
    ): TreeNode? {
        // 1.递归终结条件：当后指针在前指针前时，返回null结束
        if (postStart > postEnd) return null

        // 2.处理当前层逻辑
        // 在后序数组中找到根节点值
        val rootVal = postorder[postEnd]
        // 在中序哈希映射中找到根节点下标
        val rootIndex = map[rootVal]!!
        // 中序数组的根节点下标 与 中序起点下标 差距
        val leftSize = rootIndex - inStart

        // 3.下探到下一层，
        // 构造根节点
        val rootNode = TreeNode(rootVal)
        // 递归的构造左子树
        rootNode.left = buildTree1s(
            postorder, postStart, postStart + leftSize - 1,
            inorder, inStart, rootIndex, map
        )

        // 递归的构造右子树
        rootNode.right = buildTree1s(
            postorder, postStart + leftSize, postEnd - 1,
            inorder, rootIndex + 1, inEnd, map
        )
        return rootNode
    }


    /**
     * 102. 二叉树的层序遍历 -- 方法一：BFS广度遍历-迭代
     *
     * 时间复杂度：O(n)，每个点进队出队各一次，故渐进时间复杂度为 O(n)；
     * 空间复杂度：O(n)，队列中元素的个数不超过 nn 个，故渐进空间复杂度为 O(n)。
     *
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/102-er-cha-shu-de-ceng-xu-bian-li-by-chen-li-guan/
     * @param root
     * @return
     */
    fun levelOrder(root: TreeNode?): List<List<Int>>? {
        // 1.1 创建一个存放最终结果的集合 和 存放节点的队列
        val res = mutableListOf<List<Int>>()
        if (root == null) return res

        // 1.2 根节点不==null，将根节点放入队列
        val queue = LinkedList<TreeNode>()
        queue.offer(root)

        // 2.1 遍历每一层前，当前层的队列不为空，继续遍历
        var size = queue.size
        while (size > 0) {
            val list = mutableListOf<Int>()
            // 2.2 将这一层的元素全部取出（因为长度已确定，不会遍历新加入的左右节点）
            for (i in 0 until size) {
                val node = queue.poll()
                // 2.3 添加遍历后的元素到集合中
                list.add(node.`val`)

                // 3 如果节点的左右孩子不为空，放入队列
                if (node.left != null) queue.offer(node.left)
                if (node.right != null) queue.offer(node.right)
            }

            // 4 将结果的集合赋值到大集合中
            res.add(list)
            size = queue.size
        }

        return res
    }


    /**
     * 102. 二叉树的层序遍历 -- DFS深度遍历-递归
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
        // 存放最终结果的集合
        val res = mutableListOf<MutableList<Int>>()
        if (root == null) return res

        dfsLevel(0, root, res)
        return res
    }

    fun dfsLevel(index: Int, root: TreeNode?, res: MutableList<MutableList<Int>>) {
        // 1 终止条件
        if (root == null) return

        // 2.1 假设res是[[1],[2,3]]，index(0开始)是2，就再插入一个空list放到res中
        if (res.size == index) {
            res.add(mutableListOf())
        }

        // 2.2 将当前节点的值加入到res中，index-1代表当前层，假设index-1是3-1=2，节点值是6。res是[[1],[2,5],[3,4]]，加入后res就变为[[1],[2,5],[3,4,6]]
        res[index].add(root.`val`)

        // 3 递归的处理左子树，右子树，同时将层数index+1
        if (root.left != null) {
            dfsLevel(index + 1, root.left, res)
        }

        if (root.right != null) {
            dfsLevel(index + 1, root.right, res)
        }
    }


    /**
     * 107. 二叉树的层序遍历 II -- 方法一：BFS广度遍历-迭代
     *
     * 时间复杂度：O(n)，每个点进队出队各一次，故渐进时间复杂度为 O(n)；
     * 空间复杂度：O(n)，队列中元素的个数不超过 n 个，故渐进空间复杂度为 O(n)。
     *
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/solution/107-er-cha-shu-de-ceng-xu-bian-li-ii-by-txr7z/
     * @param root
     * @return
     */
    fun levelOrderBottom(root: TreeNode?): List<List<Int>> {
        // 1.1 创建一个存放最终结果的集合 和 存放节点的队列
        val res = mutableListOf<List<Int>>()
        if (root == null) return res

        // 1.2 根节点不==null，将根节点放入队列
        val queue = LinkedList<TreeNode>()
        queue.offer(root)

        // 2.1 遍历每一层前，当前层的队列不为空，继续遍历
        var size = queue.size
        while (size > 0) {
            val list = mutableListOf<Int>()
            // 2.2 将这一层的元素全部取出（因为长度已确定，不会遍历新加入的左右节点）
            for (i in 0 until size) {
                val node = queue.poll()
                // 2.3 添加遍历后的元素到集合中
                list.add(node.`val`)

                // 3 如果节点的左右孩子不为空，放入队列
                if (node.left != null) queue.offer(node.left)
                if (node.right != null) queue.offer(node.right)
            }

            // 4 将结果的集合添加到集合的前面
            res.add(0, list)
            size = queue.size
        }

        return res
    }


    fun levelOrderBottom1(root: TreeNode?): List<List<Int>> {
        // 存放最终结果的集合
        val res = mutableListOf<MutableList<Int>>()
        if (root == null) return res

        levelOrder(0, root, res)
        res.reverse()
        return res
    }

    fun levelOrder(index: Int, root: TreeNode?, res: MutableList<MutableList<Int>>) {
        // 1 终止条件
        if (root == null) return

        // 2.1 假设res是[[1],[2,3]]，index(0开始)是2，就再插入一个空list放到res中
        if (res.size == index) {
            res.add(mutableListOf())
        }

        // 2.2 将当前节点的值加入到res中，index-1代表当前层，假设index-1是3-1=2，节点值是6。res是[[1],[2,5],[3,4]]，加入后res就变为[[1],[2,5],[3,4,6]]
        res[index].add(root.`val`)

        // 3 递归的处理左子树，右子树，同时将层数index+1
        if (root.left != null) {
            levelOrder(index + 1, root.left, res)
        }

        if (root.right != null) {
            levelOrder(index + 1, root.right, res)
        }
    }


    /**
     * 515. 在每个树行中找最大值 -- 方法一：BFS广度遍历-迭代：
     *
     * 思想：每次递归的时候都需要带一个 index(表示当前的层数)，也就对应那个田字格子中的第几行，如果 当前值 比 此位置的值 大，就用 当前值 覆盖。
     *
     * 时间复杂度：O(n)，每个点进队出队各一次，故渐进时间复杂度为 O(n)；
     * 空间复杂度：O(n)，队列中元素的个数不超过 n 个，故渐进空间复杂度为 O(n)。
     *
     * https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/solution/515-zai-mei-ge-shu-xing-zhong-zhao-zui-da-zhi-by-c/
     *
     * @param root
     * @return
     */
    fun largestValues(root: TreeNode?): List<Int> {
        // 1.1 创建一个存放最终结果的集合 和 存放节点的队列
        val res = mutableListOf<Int>()

        // 1.2 创建一个队列，将根节点放入其中
        if (root == null) return res

        val queue = LinkedList<TreeNode>()
        queue.offer(root)

        // 2.1 遍历每一层前，存下当前队列的长度
        var size = queue.size
        while (size > 0) {
            var max = Int.MIN_VALUE
            // 2.2 将这一层的元素全部取出，因为长度已确定，不会遍历新加入的左右节点
            for (i in 0 until size) {
                val node = queue.poll()
                // 2.3 记录每层的最大值
                max = Math.max(max, node.`val`)

                // 3 如果节点的左右孩子不为空，放入队列
                if (node.left != null) queue.offer(node.left)
                if (node.right != null) queue.offer(node.right)
            }

            // 4 赋值到集合中
            res.add(max)
            size = queue.size
        }

        return res
    }

    /**
     * 515. 在每个树行中找最大值 -- 方法二：DFS深度遍历-递归：
     *
     * 时间复杂度：O(n)，每个点进队出队各一次，故渐进时间复杂度为 O(n)；
     * 空间复杂度：O(h)，h 是树的高度。
     *
     * https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/solution/515-zai-mei-ge-shu-xing-zhong-zhao-zui-da-zhi-by-c/
     * @param root
     * @return
     */
    fun largestValues1(root: TreeNode?): List<Int> {
        val res = mutableListOf<Int>()
        if (root == null) return res

        dfsLargest(1, root, res)
        return res
    }

    private fun dfsLargest(index: Int, root: TreeNode?, res: MutableList<Int>) {
        // 1.递归终结条件
        if (root == null) return

        if (res.size < index) {
            res.add(Int.MIN_VALUE)
        }

        // 2.处理当前层逻辑：如果当前值比此位置的值大，就用当前值覆盖。
        if (res[index - 1] < root.`val`) {
            res[index - 1] = root.`val`
        }

        // 3.下探到下一层
        if (root.left != null) {
            dfsLargest(index + 1, root.left, res)
        }

        if (root.right != null) {
            dfsLargest(index + 1, root.right, res)
        }

        // 4.清理恢复当前层
    }

    /**
     * 199. 二叉树的右视图
     *
     * 时间复杂度：O(n)，每个点进队出队各一次，故渐进时间复杂度为 O(n)；
     * 空间复杂度：O(h)，h 是树的高度。
     *
     * https://leetcode-cn.com/problems/binary-tree-right-side-view/solution/199-er-cha-shu-de-you-shi-tu-by-chen-li-8k5xr/
     * @param root
     * @return
     */
    fun rightSideView(root: TreeNode?): List<Int> {
        val res = mutableListOf<Int>()
        if (root == null) return res

        val queue = LinkedList<TreeNode>()
        queue.offer(root)

        var size = queue.size
        while (size > 0) {

            for (i in 0 until size) {
                val node = queue.poll()
                if (i == size - 1) {
                    res.add(node.`val`)
                }

                if (node.left != null) queue.offer(node.left)
                if (node.right != null) queue.offer(node.right)
            }

            size = queue.size
        }

        return res
    }


    /**
     * 103. 二叉树的锯齿形层序遍历 -- 方法一：BFS广度遍历-迭代
     *
     * 时间复杂度：O(n)，每个点进队出队各一次，故渐进时间复杂度为 O(n)；
     * 空间复杂度：O(n)，队列中元素的个数不超过 n 个，故渐进空间复杂度为 O(n)。
     *
     *
     * @param root
     * @return
     */
    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>>? {
        // 1.1 创建一个存放最终结果的集合 和 存放节点的队列
        val res = mutableListOf<List<Int>>()
        if (root == null) return res

        // 1.2 根节点不==null，将根节点放入队列
        val queue = LinkedList<TreeNode>()
        queue.offer(root)

        // 计数。从偶数开始
        var count = 0
        // 2.1 遍历每一层前，当前层的队列不为空，继续遍历
        var size = queue.size
        while (size > 0) {
            val list = mutableListOf<Int>()
            // 2.2 将这一层的元素全部取出（因为长度已确定，不会遍历新加入的左右节点）
            for (i in 0 until size) {
                val node = queue.poll()
                if (count % 2 == 0) {
                    // 2.3 偶数
                    list.add(node.`val`)
                } else {
                    // 奇数
                    list.add(0, node.`val`)
                }

                // 3 如果节点的左右孩子不为空，放入队列
                if (node.left != null) queue.offer(node.left)
                if (node.right != null) queue.offer(node.right)
            }

            // 计数+1
            count++
            // 4 将结果的集合赋值到大集合中
            res.add(list)
            size = queue.size
        }

        return res
    }


    /**
     * 101. 对称二叉树  方法一：递归实现
     * 思路：比较 left 是否等于 right，不等的话直接返回。相当于，比较 left 的左节点和 right 的右节点，再比较 left 的右节点和 right 的左节点。
     *
     * 时间复杂度：这里遍历了这棵树，渐进时间复杂度为 O(n)。
     * 空间复杂度：这里的空间复杂度和递归使用的栈空间有关，这里递归层数不超过 nn，故渐进空间复杂度为 O(n)。
     *
     * https://leetcode-cn.com/problems/symmetric-tree/solution/101-dui-cheng-er-cha-shu-by-chen-li-guan-je8g/
     * @param root
     * @return
     */
    fun isSymmetric(root: TreeNode?): Boolean {
        if (root == null) return true

        // 调用递归函数，比较左节点，右节点
        return dfs(root.left, root.right)
    }

    fun dfs(left: TreeNode?, right: TreeNode?): Boolean {
        // 递归的终止条件是两个节点都为空，返回 true
        if (left == null && right == null) return true

        // 两个节点中有一个为空，或者两个节点的值不相等，返回 false
        if (left == null || right == null) return false

        if (left.`val` != right.`val`) return false

        // 再递归的比较 左节点的左孩子 和 右节点的右孩子，以及比较 左节点的右孩子 和 右节点的左孩子
        return dfs(left.left, right.right) && dfs(left.right, right.left)
    }


    /**
     *  101. 对称二叉树  方法二：层序遍历实现
     *
     * 首先从队列中拿出两个节点 left 和 right 比较
     * 将 left 的 left 节点和 right 的 right 节点放入队列，将 left 的 right 节点和 right 的 left 节点放入队列
     *
     * 时间复杂度是 O(n)。
     * 空间复杂度是 O(n)。
     *
     * @param root
     * @return
     */
    fun isSymmetric2(root: TreeNode?): Boolean {
        if (root == null || root.left == null && root.right == null) {
            return true
        }

        // 用队列保存节点
        val queue = LinkedList<TreeNode?>()
        // 将根节点的左右孩子放到队列中
        queue.offer(root.left)
        queue.offer(root.right)

        while (queue.size > 0) {
            // 从队列中取出两个节点，再比较这两个节点
            val left = queue.poll()
            val right = queue.poll()

            // 如果两个节点都为空就继续循环
            if (left == null && right == null) {
                continue
            }

            // 两个节点中有一个为空，或者两个节点的值不相等，返回 false
            if (left == null || right == null) {
                return false
            }
            if (left.`val` != right.`val`) {
                return false
            }

            // 将左节点的左孩子，右节点的右孩子放入队列
            queue.offer(left.left)
            queue.offer(right.right)

            // 将左节点的右孩子，右节点的左孩子放入队列
            queue.offer(left.right)
            queue.offer(right.left)
        }

        return true
    }


    /**
     * 236. 二叉树的最近公共祖先
     *
     * 时间复杂度 O(N) ： 其中 N 为二叉树节点数；最差情况下，需要递归遍历树的所有节点。
     * 空间复杂度 O(N) ： 最差情况下，递归深度达到 N ，系统使用 O(N) 大小的额外空间。
     *
     * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/236-er-cha-shu-de-zui-jin-gong-gong-zu-x-bfgv/
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null) return root

        // 找到了一个根节点，返回 root 节点
        if (root == p || root == q) return root


        // 递归遍历左右子节点
        val left = lowestCommonAncestor(root.left, p, q)

        val right = lowestCommonAncestor(root.right, p, q)

        // 当 left 和 right 同时为空：说明 root 的左/右子树中都不包含 p,q，返回 null
        if (left == null && right == null) return null // 1.

        // p,q 两节点都在 root 的 右子树 中，返回 right。此时的 right 指向 最近公共祖先节点
        if (left == null) return right // 3.
        // 同理
        if (right == null) return left // 4.

        // 当 left 和 right 同时不为空 ：说明 p, q 分别在左/右子树，因此 root 为最近公共祖先，返回 root 节点
        return root   // 2. if(left != null and right != null)
    }

    /**
     * 114. 二叉树展开为链表
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(n)。
     *
     * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/114-er-cha-shu-zhan-kai-wei-lian-biao-by-zkzf/
     * @param root
     */
    fun flatten(root: TreeNode?) {
        var root = root
        while (root != null) {
            // 5.左子树为 null，直接考虑右子节点
            if (root.left == null) {
                root = root.right
            } else {
                // 1.找左子树最右边的节点
                var pre = root.left
                while (pre?.right != null) {
                    pre = pre.right
                }

                // 2.将原来的右子树接到左子树的最右边节点
                pre?.right = root.right

                // 3.将左子树插入到右子树的地方
                root.right = root.left

                // 4.左节点置空，并考虑下一个右子节点
                root.left = null
                root = root.right
            }
        }
    }

    // 426. 将二叉搜索树转化为排序的双向链表 == 剑指 Offer 36. 二叉搜索树与双向链表

//    // 记录头结点，用来最后返回的
//    private var head: DNode? = null
//
//    // 记录遍历的当前节点的前一个节点, 用来把当前节点给串起来的
//    private var pre: DNode? = null
//
//    fun treeToDoublyList(root: DNode?): DNode? {
//        if (root == null) return root
//
//        inorder(root)
//
//        // 注意，上面的方法中，我们只是把第一个接点到最后一个节点串了起来，但并没有把第一个节点
//        // 和最后一个节点串起来连成一个环形，所以这里还要把链表的首尾连接起来（这里pre已经是尾节点了）
//        head.left = pre
//        pre.right = head
//        return head
//    }
//
//    // 二叉树的中序遍历
//    private fun inorder(root: DNode?) {
//        // 边界条件的判断
//        if (root == null) return
//
//        // 先遍历左子节点
//        inorder(root.left)
//
//        // 下面是对当前节点的操作
//        if (pre == null) {
//            // 这行代码只会执行一次，就是root是树的左子节点的左子节点的……,一直没有左子节点为止,实际上就是中序遍历结果的第一个节点
//            head = root
//        } else {
//            // 串起来的结果就是前一个节点pre的right指向当前节点，然后当前节点的left指向前一个节点pre
//            pre.right = root
//        }
//        root.left = pre
//
//        // 前一个节点和当前节点串起来之后，就让当前节点变成前一个节点
//        pre = root
//
//        // 最后在遍历右子节点
//        inorder(root.right)
//    }


    /**
     * 543. 二叉树的直径
     *
     * 时间复杂度：O(N)；
     * 空间复杂度：O(Height)，其中 Height 为二叉树的高度。
     *
     * https://leetcode-cn.com/problems/diameter-of-binary-tree/solution/543-er-cha-shu-de-zhi-jing-by-chen-li-gu-w75a/
     * @param root
     * @return
     */
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        depth(root)
        // -1 是因为根节点是同一个。计算左右深度，把根节点计算了2次
        return res - 1
    }

    var res = 0
    fun depth(node: TreeNode?): Int {
        // 访问到空节点了，返回0
        if (node == null) return 0

        // 左儿子为根的子树的深度
        val left = depth(node.left) + 1
        // 右儿子为根的子树的深度
        val right = depth(node.right) + 1

        // 计算最大深度即 left + right - 1(因为+1了两次)，并更新 res
        res = Math.max(res, left + right - 1)

        // 返回该节点为根的子树的深度
        return Math.max(left, right)
    }


    /**
     * 110. 平衡二叉树
     *
     * 时间复杂度：O(N)；
     * 空间复杂度：O(Height)，其中 Height 为二叉树的高度。
     *
     * https://leetcode-cn.com/problems/balanced-binary-tree/solution/110-ping-heng-er-cha-shu-by-chen-li-guan-hq18/
     * @param root
     * @return
     */
    fun isBalanced(root: TreeNode?): Boolean {
        return balanced(root) != -1
    }

    fun balanced(root: TreeNode?): Int {
        // 当越过叶子节点时，返回高度 0
        if (root == null) return 0

        val left = balanced(root.left)
        // 当左（右）子树高度 left== -1 时，代表此子树的 左（右）子树 不是平衡树，因此直接返回 -1−1 ；
        if (left == -1) return -1

        val right = balanced(root.right)
        if (right == -1) return -1

        // 当节点 root 左/右子树的，高度差<2，则返回最大深度，继续计算。 高度差≥2，返回−1 ，表示子树不是平衡树
        if (Math.abs(left - right) < 2) {
            return Math.max(left, right) + 1
        } else {
            return -1
        }
    }


    /**
     * 108. 将有序数组转换为二叉搜索树
     * 二叉搜索树的中序遍历是升序的，因此本题等同于根据中序遍历的序列恢复二叉搜索树
     *
     * 时间复杂度：O(N)；
     * 空间复杂度：O(Height)，其中 Height 为二叉树的高度。
     *
     * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/solution/108-jiang-you-xu-shu-zu-zhuan-huan-wei-e-0bhp/
     * @param nums
     * @return
     */
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        return dfs(nums, 0, nums.size - 1)
    }

    private fun dfs(nums: IntArray, l: Int, r: Int): TreeNode? {
        if (l > r) return null

        // 求高度平衡，因此以升序数组的中间元素作为根节点 root。
        val mid = l + (r - l) / 2

        val root = TreeNode(nums[mid])

        // 递归的构建 root 的左子树与右子树。
        root.left = dfs(nums, l, mid - 1)

        root.right = dfs(nums, mid + 1, r)

        return root
    }


    /**
     * 109. 有序链表转换二叉搜索树
     *
     * 时间复杂度：O(n)，其中 n 是链表的长度。
     * 空间复杂度：O(logn)，这里只计算除了返回答案之外的空间。平衡二叉树的高度为 O(logn)，即为递归过程中栈的最大深度，也就是需要的空间。
     *
     * https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/solution/109-you-xu-lian-biao-zhuan-huan-er-cha-s-4zzl/
     * @param head
     * @return
     */
    var dummy: ListNode? = null

    fun sortedListToBST(head: ListNode?): TreeNode? {
        dummy = head
        val length = listLength(head)
        return buildTree(0, length - 1)
    }

    fun buildTree(l: Int, r: Int): TreeNode? {
        if (l > r) return null

        val mid = l + (r - l) / 2

        val root = TreeNode(0)

        root.left = buildTree(l, mid - 1)

        // 关键：二叉搜索树的中序遍历结果就是链表本身，因此获取当前链表节点，赋值
        root.`val` = dummy?.`val`!!
        dummy = dummy?.next

        root.right = buildTree(mid + 1, r)
        return root
    }

    // 计算链表的长度
    fun listLength(head: ListNode?): Int {
        var head = head
        var length = 0
        while (head != null) {
            length++
            head = head.next
        }
        return length
    }



    /**
     * 222. 完全二叉树的节点个数   方法一：利用完全二叉树特点
     *
     * 完全二叉树的性质简化遍历次数，除最后一层外，其余层全部铺满；且最后一层向左停靠
     *
     * 时间复杂度：O(logn * logn)。每递归到一个节点，都要计算深度，二叉树深度计算，O(logN)。每一层只看一个节点，一共多少层？logN层。
     * 空间复杂度：O(Height)，其中 Height 为二叉树的高度。
     *
     * https://leetcode-cn.com/problems/count-complete-tree-nodes/solution/222-wan-quan-er-cha-shu-de-jie-dian-ge-s-z64u/
     * @param root
     * @return
     */
    fun countNodes(root: TreeNode?): Int {
        if (root == null) return 0

        val leftHeight = treeHeight(root.left)
        val rightHeight = treeHeight(root.right)

        if (leftHeight == rightHeight) {
            // 如果根节点的左子树深度等于右子树深度，则说明左子树为满二叉树，通过公式计算左子树，只需要递归右子树就行了
            // 注意：公式是：(1 << height) - 1，加上根节点就是：(1 << height)
            return (1 shl leftHeight) + countNodes(root.right)
        } else {
            // 如果根节点的左子树深度大于右子树深度，则说明右子树为满二叉树，通过公式计算右子树，只需要递归左子树就行了
            return (1 shl rightHeight) + countNodes(root.left)
        }
    }

    // 计算树的高度
    private fun treeHeight(root: TreeNode?): Int {
        var root = root
        var height = 0
        while (root != null) {
            root = root.left
            height++
        }
        return height
    }


    /**
     * 222. 完全二叉树的节点个数   方法二：暴力递归
     */
    fun countNodes2(root: TreeNode?): Int {
        if (root == null) return 0

        val left = countNodes2(root.left)

        val right = countNodes2(root.right)

        return left + right + 1
    }


    /**
     * 剑指 Offer 26. 树的子结构
     * 思路：若树 B 是树 A 的子结构，则子结构的根节点可能为树 A 的任意一个节点。
     *
     * 时间复杂度 O(MN)：其中 M,N 分别为树 A 和 树 B 的节点数量；先序遍历树 A 占用 O(M)，每次调用 recur(A, B) 判断占用 O(N)。
     * 空间复杂度 O(M)：当树 A 和树 B 都退化为链表时，递归调用深度最大。
     *
     * @param A
     * @param B
     * @return
     */
    fun isSubStructure(A: TreeNode?, B: TreeNode?): Boolean {
        // 约定空树不是任意一个树的子结构
        if (A == null || B == null) return false

        // 判断 A 树和 B 树的 根节点值 和 孩子节点值 是否对应相等
        if (helper(A, B)) return true

        // 递归判断 A 树的左、右子树是否包含 B 树
        return isSubStructure(A.left, B) || isSubStructure(A.right, B)
    }

    /**
     * 判断 A 树和 B 树的根节点值是否相等，
     * 若不等，返回 false；
     * 若相等，再递归判断 A 树和 B 树的的孩子节点是否对应相等，如果相等了，就说明 A 的子结构包含 B 树，返回 true。否者就不包含，返回 false
     */
    fun helper(A: TreeNode?, B: TreeNode?): Boolean {
        if (B == null) return true

        // A 树为空或者 A 节点的值不等于 B 结点的值，返回 false
        if (A == null || A.`val` != B.`val`) return false

        // 当 A.val == B.val 时，递归判断A树的子节点和B树的子节点是否对应相等
        return helper(A.left, B.left) && helper(A.right, B.right)
    }


    /**
     * 127. 单词接龙  方法一：双向BFS（困难）
     *
     * 时间复杂度：O(M×N)，其中 MM 是单词的长度N是单词表中单词的总数。与单向搜索相同的是，找到所有的变换需要M * N次操作。
     *          但是搜索时间会被缩小一半，因为两个搜索会在中间某处相遇。
     * 空间复杂度：O(M×N)，要在all_combo_dict字典中记录每个单词的M个通用状态，这与单向搜索相同。
     *          但是因为会在中间相遇，所以双向搜索的搜索空间变小。
     *
     * https://leetcode-cn.com/problems/word-ladder/solution/127-dan-ci-jie-long-by-chen-li-guan/
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        if (!wordList.contains(endWord)) return 0

        // 1 先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        val wordList = wordList as MutableList<String>
        wordList.add(beginWord)
        val allWordSet = HashSet(wordList)

        // 2.1 创建一个队列，将新访问到的节点放入其中
        var queue1 = LinkedList<String>()
        var queue2 = LinkedList<String>()
        // 2.2 已经访问过的 word 添加到 visited 哈希表里
        var visited1 = hashSetOf<String?>()
        var visited2 = hashSetOf<String?>()
        queue1.offer(beginWord)
        queue2.offer(endWord)
        visited1.add(beginWord)
        visited2.add(endWord)
        // 2.3 计算已遍历节点总数
        var count = 0

        // 3.1 执行双向 BFS，左右交替扩散的步数之和为所求
        while (queue1.isNotEmpty() && queue2.isNotEmpty()) {
            // 3.2 从AB两个方向的中，选择当前节点更少的队列，在另一个方向进行层序遍历（这种方式搜索的这一层单词数量会更小一些）
            if (queue1.size > queue2.size) {
                val temp = queue1
                queue1 = queue2
                queue2 = temp
                val v = visited1
                visited1 = visited2
                visited2 = v
            }

            // 3.3 开始遍历每一层的每个字符串
            count++
            var size1: Int = queue1.size
            while (size1-- > 0) {

                // 4.1 轮流替换一个字符串的每个字符后遍历
                val chars = queue1.poll().toCharArray()
                for (i in chars.indices) {
                    // 4.2 保存第i位的字符
                    val temp = chars[i]

                    // 4.3 用a~z替换当前字符，再对比新字符串，比较26次；
                    // 因为单词是由a~z字符组成，可以遍历当前单词能转换成的所有单词，判断其是否包含在候选单词中
                    var ch = 'a'
                    while (ch <= 'z') {
                        chars[i] = ch
                        val newString = String(chars)
                        // 4.4 已经访问过了，跳过
                        if (visited1.contains(newString)) {
                            ch++
                            continue
                        }

                        // 4.5 两端遍历相遇，结束遍历，返回count
                        if (visited2.contains(newString)) {
                            return count + 1
                        }
                        // 4.6 如果单词在列表中存在，将其添加到队列，并标记为已访问
                        if (allWordSet.contains(newString)) {
                            queue1.offer(newString)
                            visited1.add(newString)
                        }
                        ch++
                    }

                    // 4.7 恢复第i位的字符，为其他字符替换准备
                    chars[i] = temp
                }
            }
        }
        return 0
    }
}
