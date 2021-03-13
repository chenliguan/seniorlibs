package com.seniorlibs.algorithm.trie

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/10/21
 * Mender:
 * Modify:
 * Description: 并查集常用来解决连通性的问题，即将一个图中连通的部分划分出来。并查集不关心节点有哪些儿子，只关心节点的根是谁。
 */
class FindCircleNum {

    private var count = 0
    private lateinit var parent: IntArray

    /**
     * 初始化
     *
     * @param board
     */
    fun init(board: Array<IntArray>) {
        // 一开始互不连通
        count = board.size
        // M.siz 为图的节点总数
        parent = IntArray(board.size)
        // 父节点指针初始指向自己
        for (i in board.indices) {
            parent[i] = i
        }
    }

    /**
     * 查找朋友圈数量
     *
     * 时间复杂度：O(n^3)，访问整个矩阵一次，并查集操作需要最坏O(n)时间；
     * 空间复杂度：O(n)，parent大小为n；
     *
     * @param board
     * @return
     */
    fun findCircleNum(board: Array<IntArray>): Int {
        // 初始化
        init(board)

        // 遍历i, j查找
        for (i in board.indices) {
            for (j in board.indices) {
                // board[i][j] = 1，表示已知第i个和j个学生互为朋友关系，否则为不知道。然后把互为朋友圈的学生合并在一个朋友圈中
                if (board[i][j] == 1 && i != j) {
                    // 合并
                    union(i, j)
                }
            }
        }
        return count
    }

    /**
     * 返回某个节点 x 的根节点
     *
     * @param x
     * @return
     */
    fun find(x: Int): Int {
        // 根节点的 parent[x] == x
        var x = x
        while (parent[x] != x) {
            // 进行路径压缩
            parent[x] = parent[parent[x]]
            x = parent[x]
        }
        return x
    }

    fun find1(x: Int): Int {
        // 根节点的 parent[x] == x
        var x = x
        while (parent[x] != x) {
            x = parent[x]
        }
        return x
    }

    /**
     * 合并：通过 find 函数找到 u，v 的根节点 root_u, root_v。
     *      如果两者的根节点不相同，则将 root_u 的父节点设为 root_v。如果相同，则无需任何操作
     *
     * @param p
     * @param q
     */
    fun union(p: Int, q: Int) {
        val rootP = find(p)
        val rootQ = find(q)
        if (rootP == rootQ)
            return

        // 将两棵树合并为一棵：如果节点p和q连通的话，它们一定拥有相同的根节点
        // parent[rootQ] = rootP 也一样
        parent[rootP] = rootQ
        count--
    }
}