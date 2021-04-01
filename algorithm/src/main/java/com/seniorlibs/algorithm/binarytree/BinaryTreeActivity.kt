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
        findViewById<View>(R.id.btn_has_path_sum).setOnClickListener(this)
        findViewById<View>(R.id.btn_ineorder).setOnClickListener(this)
        findViewById<View>(R.id.btn_preorder).setOnClickListener(this)
        findViewById<View>(R.id.btn_postorder).setOnClickListener(this)
        findViewById<View>(R.id.btn_is_valid_bst).setOnClickListener(this)
        findViewById<View>(R.id.btn_pre_in_build_tree).setOnClickListener(this)
        findViewById<View>(R.id.btn_in_post_build_tree).setOnClickListener(this)
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
            // 将根节点弹出
            val p = stack.pop()
            // 加入到结果集合中
            res.add(p!!.`val`)

            // 将该节点的子节点从右往左压入栈
            for (i in p.children.size - 1 downTo 0) {
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
            // 将根节点弹出
            val node = stack.pop()
            // 加入到结果集合中
            res.add(node!!.`val`)

            // 将该节点的子节点从左往右压入栈
            for (i in 0 until node.children.size) {
                stack.push(node.children[i])
            }
        }
        // 最后将list反转
        return res.reversed()
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
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

        val queue: Queue<TreeNode> = LinkedList<TreeNode>()
        var level = 0
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

        val queue: Queue<TreeNode> = LinkedList<TreeNode>()
        var level = 0
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
     * 112. 路径总和
     * 思想：一直向下找到叶子节点，同时判断节点的左右子树同时为空才是叶子节点
     *
     * 时间复杂度：O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度：O(height)，其中height是树的高度。空间复杂度主要取决于递归时栈空间的开销；
     *
     * https://leetcode-cn.com/problems/path-sum/solution/112-lu-jing-zong-he-by-chen-li-guan-yi5m/
     * @param root
     * @param sum
     * @return
     */
    fun hasPathSum(root: TreeNode?, sum: Int): Boolean {
        // 1.递归终结条件（最先写）
        if (root == null) return false

        // 2.处理当前层逻辑
        if (root.left == null && root.right == null) {
            return root.`val` == sum
        }

        // 3.下探到下一层，关键：sum - root.`val`
        val left = hasPathSum(root.left, sum - root.`val`)

        val right = hasPathSum(root.right, sum - root.`val`)

        return left || right

        // 4.清理恢复当前层
    }


    /**
     * 113. 路径总和 II
     * 思想：一直向下找到叶子节点，同时判断节点的左右子树同时为空才是叶子节点
     *
     * 时间复杂度：O(n)，二叉树的每个节点最多被访问一次，因此时间复杂度为O(n)。
     * 空间复杂度：取决于结果列表的长度。
     *
     * https://leetcode-cn.com/problems/path-sum-ii/solution/113-lu-jing-zong-he-ii-by-chen-li-guan-mx4f/
     * @param root
     * @param sum
     * @return
     */
    fun pathSum(root: TreeNode?, sum: Int): List<List<Int>> {
        val result: MutableList<List<Int>> = mutableListOf()

        dfs(root, sum, mutableListOf(), result)

        return result
    }

    fun dfs(root: TreeNode?, sum: Int, list: MutableList<Int>, result: MutableList<List<Int>>) {
        if (root == null) return

        list.add(root.`val`)

        // 如果到达叶子节点，就不能往下走了，直接 return
        if (root.left == null && root.right == null) {
            // 如果到达叶子节点，并且 sum 等于叶子节点的值，说明找到了一组，要把它放到 result 中
            if (sum == root.`val`) {
                result.add(ArrayList(list))
            }

            // 把最后加入的结点值给移除掉，因为下一步直接 return 了，不会再走最后一行的 remove 了
            list.removeAt(list.size - 1)
            // 到叶子节点之后直接返回，因为在往下就走不动了
            return
        }

        // 如果没到达叶子节点，就继续从它的左右两个子节点往下找，注意 sum 要减去当前节点的值
        dfs(root.left, sum - root.`val`, list, result)
        dfs(root.right, sum - root.`val`, list, result)

        // 要理解递归的本质，当递归往下传递的时候他最后还是会往回走，把这个值使用完之后还要把它给移除，这就是回溯
        list.removeAt(list.size - 1)
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
                res.add(stack.pop()!!.`val`)
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

        return solution(root.left, minValue, root.`val`.toLong()) && solution(
            root.right,
            root.`val`.toLong(),
            maxValue
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
        val map: MutableMap<Int, Int> = mutableMapOf()
        for (i in inorder.indices) map[inorder[i]] = i

        return buildTrees(
            preorder, 0, preorder.size - 1,
            inorder, 0, inorder.size - 1, map
        )
    }

    private fun buildTrees(
        preorder: IntArray, preStart: Int, preEnd: Int,
        inorder: IntArray, inStart: Int, inEnd: Int, map: MutableMap<Int, Int>
    ): TreeNode? {
        // 1.递归终结条件：当后指针在前指针前时，返回null结束
        if (preStart > preEnd) return null

        // 2.处理当前层逻辑
        // 在前序数组中找到根节点值
        val rootVal = preorder[preStart]
        // 在中序哈希映射中找到根节点下标
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
        val map: MutableMap<Int, Int> = mutableMapOf()
        for (i in inorder.indices) map[inorder[i]] = i

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
}
