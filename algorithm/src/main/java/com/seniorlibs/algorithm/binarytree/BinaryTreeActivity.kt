package com.seniorlibs.algorithm.binarytree

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.linkedlist.LinkedActivity
import com.seniorlibs.algorithm.main.MainActivity
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
        findViewById<View>(R.id.btn_preorder).setOnClickListener(this)
        findViewById<View>(R.id.btn_max_depth).setOnClickListener(this)
        findViewById<View>(R.id.btn_ineorder).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_preorder -> {
                list.clear()
                val node = Node(1)
                node.children.add(Node(2))
                node.children.add(Node(4))
                node.children.add(Node(5))

                val node1 = Node(5)
                node.children.add(node1)
                node1.children.add(Node(6))
                preorder(node)

//                E/MainActivity: nums：[1, 2]
//                E/MainActivity: nums：[1, 2, 4]
//                E/MainActivity: nums：[1, 2, 4, 5]
//                E/MainActivity: nums：[1, 2, 4, 5, 5, 6]
//                E/MainActivity: nums：[1, 2, 4, 5, 5, 6]
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
            else -> {
            }
        }
    }

    /**
     * N叉数的遍历
     */
    class Node(var `val`: Int) {
        var children = ArrayList<Node>()
    }

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


    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    /**
     *  二叉树的最大深度
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
}
