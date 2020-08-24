package com.seniorlibs.algorithm.binarytree

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.linkedlist.LinkedActivity

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binary_tree)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_binary_tree).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_binary_tree -> {

            }

            else -> {
            }
        }
    }

    private fun initData() {

    }

    /**
     * N叉数的遍历
     */
    class Node(var `val`: Int) {
        var children: List<Node?> = listOf()
    }

    private val list = ArrayList<Int>()

    fun preorder(root: Node?): List<Int>? {
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
     *  二叉树的最大深度
     */
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun maxDepth(root: TreeNode?): Int {
        return if (root == null) {
            0
        } else {
            val left = maxDepth(root.left)
            val right = maxDepth(root.right)
            Math.max(left, right) + 1
        }
    }
}
