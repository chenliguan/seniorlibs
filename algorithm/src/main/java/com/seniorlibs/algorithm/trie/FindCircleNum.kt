package com.seniorlibs.algorithm.trie

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/10/21
 * Mender:
 * Modify:
 * Description: 并查集
 */
class FindCircleNum {

    private var count = 0
    private lateinit var parent: IntArray

    /**
     * 初始化
     *
     * @param M
     */
    fun init(M: Array<IntArray>) {
        // 一开始互不连通
        count = M.size
        // M.siz 为图的节点总数
        parent = IntArray(M.size)
        // 父节点指针初始指向自己
        for (i in M.indices) parent[i] = i
    }

    /**
     * 查找朋友圈数量
     *
     * 时间复杂度：O(n^3)，访问整个矩阵一次，并查集操作需要最坏O(n)时间；
     * 空间复杂度：O(n)，parent大小为n；
     *
     * @param M
     * @return
     */
    fun findCircleNum(M: Array<IntArray>): Int {
        init(M)

        // 遍历i,j查找
        for (i in M.indices) {
            for (j in M.indices) {
                // M[i][j] = 1，表示已知第i个和j个学生互为朋友关系，否则为不知道。然后把互为朋友关的学生合并在一个朋友圈中
                if (M[i][j] == 1 && i != j) {
                    // 如果某两个节点被连通，它们一定拥有相同的根节点，则让其中的任意一个节点的根节点接到另一个节点的根节点上
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

    /**
     * 合并
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