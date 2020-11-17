package com.seniorlibs.algorithm.dfsbfs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.binarytree.BinaryTreeActivity
import com.seniorlibs.baselib.utils.LogUtils
import java.util.*

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/9/10
 * Mender:
 * Modify:
 * Description: 深度优先遍历DFS、广度优先遍历BFS
 */
class DfsBfsActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "DfsBfsActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, DfsBfsActivity::class.java)
            context.startActivity(intent)
        }
    }

    private var list: MutableList<Int> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dfs_bfs)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_level_order_bottom).setOnClickListener(this)
        findViewById<View>(R.id.btn_largest_values).setOnClickListener(this)
        findViewById<View>(R.id.btn_ladder_length).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
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

                LogUtils.e(
                    BinaryTreeActivity.TAG,
                    "107. 二叉树的层次遍历 -- 方法一：BFS广度遍历-迭代：${levelOrder(node)}"
                )
                LogUtils.e(
                    BinaryTreeActivity.TAG,
                    "107. 二叉树的层次遍历 -- 方法二：DFS深度遍历-递归：${levelOrder1(node)}"
                )
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
            R.id.btn_ladder_length -> {
                val list = mutableListOf("hot","dot","dog","lot","log","cog")
                LogUtils.e(TAG, "127. 单词接龙 -- 方法一：双向BFS：" + "${ladderLength("hit", "cog", list)}")
            }
            else -> {
            }
        }
    }


    class Node(var `val`: Int) {
        var children = mutableListOf<Node>()
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
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
        // 1.1 创建一个存放最终结果的集合 和 存放节点的队列
        val res: MutableList<List<Int>> = mutableListOf()
        val queue: Queue<TreeNode> = LinkedList()

        // 1.2 根节点不==null，将根节点放入其中
        if (root == null) return res else queue.offer(root)

        // 2.1 遍历每一层前，下层的队列不为空，继续遍历
        while (queue.isNotEmpty()) {
            val list: MutableList<Int> = mutableListOf()
            // 2.2 将这一层的元素全部取出，因为长度已确定，不会遍历新加入的左右节点
            for (i in 0 until queue.size) {
                val node = queue.poll()
                // 2.3 添加遍历后的元素到集合中
                list.add(node.`val`)

                // 3 如果节点的左右孩子不为空，放入队列
                if (node.left != null) queue.offer(node.left)
                if (node.right != null) queue.offer(node.right)
            }

            // 4 赋值到集合中
            res.add(list)
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
        // 存放最终结果的集合
        val res: MutableList<MutableList<Int>> = mutableListOf()
        if (root == null) return res

        dfsLevel(1, root, res)
        return res
    }

    fun dfsLevel(index: Int, root: TreeNode?, res: MutableList<MutableList<Int>>) {
        if (root == null) return

        // 假设res是[[1],[2,3]]，index是3，就再插入一个空list放到res中
        if (res.size < index) res.add(mutableListOf())

        // 将当前节点的值加入到res中，index-1代表当前层，假设index-1是3-1=2，节点值是6。res是[[1],[2,5],[3,4]]，加入后res就变为[[1],[2,5],[3,4,6]]
        res[index - 1].add(root.`val`)

        // 递归的处理左子树，右子树，同时将层数index+1
        if (root.left != null) dfsLevel(index + 1, root.left, res)

        if (root.right != null) dfsLevel(index + 1, root.right, res)
    }

    /**
     * 515. 在每个树行中找最大值 -- 方法一：BFS广度遍历-迭代：
     *
     * 思想：每次递归的时候都需要带一个 index(表示当前的层数)，也就对应那个田字格子中的第几行，如果 当前值 比 此位置的值 大，就用 当前值 覆盖。
     *
     * 时间复杂度：O(n)，每个点进队出队各一次，故渐进时间复杂度为 O(n)；
     * 空间复杂度：O(n)，队列中元素的个数不超过 nn 个，故渐进空间复杂度为 O(n)。
     *
     * https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/solution/515-zai-mei-ge-shu-xing-zhong-zhao-zui-da-zhi-by-c/
     *
     * @param root
     * @return
     */
    fun largestValues(root: TreeNode?): List<Int> {
        // 1.1 创建一个存放最终结果的集合 和 存放节点的队列
        val res = mutableListOf<Int>()
        val queue: Queue<TreeNode> = LinkedList<TreeNode>()

        // 1.2 创建一个队列，将根节点放入其中
        if (root == null) return res else queue.offer(root)

        // 2.1 遍历每一层前，存下当前队列的长度
        while (queue.size > 0) {
            var max = Int.MIN_VALUE
            // 2.2 将这一层的元素全部取出，因为长度已确定，不会遍历新加入的左右节点
            for (i in 0 until queue.size) {
                val node = queue.poll()
                // 2.3 记录每层的最大值
                max = Math.max(max, node.`val`)

                // 3 如果节点的左右孩子不为空，放入队列
                if (node.left != null) queue.offer(node.left)
                if (node.right != null) queue.offer(node.right)
            }

            // 4 赋值到集合中
            res.add(max)
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

        if (res.size < index) res.add(Int.MIN_VALUE)

        // 2.处理当前层逻辑：如果当前值比此位置的值大，就用当前值覆盖。
        if (res[index - 1] < root.`val`) res[index - 1] = root.`val`

        // 3.下探到下一层
        if (root.left != null) dfsLargest(index + 1, root.left, res)

        if (root.right != null) dfsLargest(index + 1, root.right, res)

        // 4.清理恢复当前层
    }

    /**
     * 127. 单词接龙  方法一：双向BFS
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
        val allWordSet: Set<String> = HashSet(wordList)

        // 2.1 创建一个队列，将新访问到的节点放入其中
        var queue1: Queue<String> = LinkedList()
        var queue2: Queue<String> = LinkedList()
        // 2.2 已经访问过的 word 添加到 visited 哈希表里
        var visited1: MutableSet<String?> = HashSet()
        var visited2: MutableSet<String?> = HashSet()
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
