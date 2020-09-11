package com.seniorlibs.algorithm.binarytree

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.baselib.utils.LogUtils

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
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_n_preorder -> {
                list.clear()
                val node = Node(1)
                node.children.add(Node(2))
                node.children.add(Node(4))
                node.children.add(Node(5))

                val node1 = Node(5)
                node.children.add(node1)
                node1.children.add(Node(6))

                LogUtils.e(TAG, "589. N叉数的前序遍历：${preorder(node)}")
//                E/MainActivity: nums：[1, 2]
//                E/MainActivity: nums：[1, 2, 4]
//                E/MainActivity: nums：[1, 2, 4, 5]
//                E/MainActivity: nums：[1, 2, 4, 5, 5, 6]
//                E/MainActivity: nums：[1, 2, 4, 5, 5, 6]
            }
            R.id.btn_n_postorder -> {
                list.clear()
                val node = Node(1)
                node.children.add(Node(2))
                node.children.add(Node(4))
                node.children.add(Node(5))

                val node1 = Node(5)
                node.children.add(node1)
                node1.children.add(Node(6))

                LogUtils.e(TAG, "590. N叉树的后序遍历：${postorder(node)}")
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
            R.id.btn_ineorder -> {
                list.clear()
                val node = TreeNode(1)
                val node_right = TreeNode(2)
                node.left = null
                node.right = node_right
                val node2 = TreeNode(3)
                node_right.left = node2
                LogUtils.e(TAG, "94. 二叉树的中序遍历：${inorderTraversal(node)}")
            }
            R.id.btn_preorder -> {
                list.clear()
                val node = TreeNode(1)
                val node_right = TreeNode(2)
                node.left = null
                node.right = node_right
                val node2 = TreeNode(3)
                node_right.left = node2
                LogUtils.e(TAG, "144. 二叉树的前序遍历：${preorderTraversal(node)}")
            }
            R.id.btn_postorder -> {
                list.clear()
                val node = TreeNode(1)
                val node_right = TreeNode(2)
                node.left = null
                node.right = node_right
                val node2 = TreeNode(3)
                node_right.left = node2
                LogUtils.e(TAG, "145. 二叉树的后序遍历：${postorderTraversal(node)}")
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
            else -> {
            }
        }
    }


    class Node(var `val`: Int) {
        var children = ArrayList<Node>()
    }

    /**
     * N叉数的前序遍历
     */
    private fun preorder(root: Node?): List<Int> {
        if (root == null) {
            return list
        }

        list.add(root.`val`)
        root.children.forEach {
            preorder(it)
        }

        return list
    }

    /**
     * 590. N叉树的后序遍历
     *
     * n为结点数，每个节点都要进栈和出栈，不过是根据那种遍历方式改变的是每个节点的进栈顺序，
     * 所以时间复杂度为O(n)，同样空间复杂度也为O(n)。
     * 递归：空间复杂度与系统堆栈有关，系统栈需要记住每个节点的值，所以空间复杂度为O(n)，时间复杂度也是O(n)
     *
     * @param root
     * @return
     */
    fun postorder(root: Node?): List<Int> {
        if (root == null) {
            return list
        }

        root.children.forEach {
            postorder(it)
        }

        list.add(root.`val`)

        return list
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    /**
     *  104.二叉树的最大深度
     */
    private fun maxDepth(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }

        val left = maxDepth(root.left) + 1
        val right = maxDepth(root.right) + 1
        return Math.max(left, right)
    }

    /**
     *  111.二叉树的最小深度
     */
    private fun minDepth(root: TreeNode?): Int {
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
     * 94. 二叉树的中序遍历
     *
     * @param root
     * @return
     */
    private fun inorderTraversal(root: TreeNode?): List<Int> {
        if (root == null) {
            return list
        }

        inorderTraversal(root.left)

        list.add(root.`val`)

        inorderTraversal(root.right)

        return list
    }

    /**
     * 144. 二叉树的前序遍历
     *
     * @param root
     * @return
     */
    private fun preorderTraversal(root: TreeNode?): List<Int?>? {
        if (root == null) {
            return list
        }

        list.add(root.`val`)

        preorderTraversal(root.left)

        preorderTraversal(root.right)

        return list
    }

    /**
     * 145. 二叉树的后序遍历
     *
     * @param root
     * @return
     */
    fun postorderTraversal(root: TreeNode?): List<Int> {
        if (root == null) {
            return list
        }

        postorderTraversal(root.left)

        postorderTraversal(root.right)

        list.add(root.`val`)

        return list
    }


    /**
     * 98. 验证二叉搜索树
     *
     * 方法二：中序遍历
     * 思路：二叉搜索树「中序遍历」得到的值构成的序列一定是升序的，如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回false；否则满足
     *
     * 时间复杂度 : O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度 : O(n)，递归函数在递归过程中需要为每一层递归函数分配栈空间，取决于递归的深度；
     *           最坏情况下二叉树为一条链，树的高度为n ，递归最深达到n层，故最坏情况下空间复杂度为O(n)。
     *
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
}
