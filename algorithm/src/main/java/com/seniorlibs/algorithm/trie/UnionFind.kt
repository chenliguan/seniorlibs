package com.seniorlibs.algorithm.trie

import java.util.*

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/10/21
 * Mender:
 * Modify:
 * Description: 并查集
 */
class UnionFind {

    private var count = 0
    private lateinit var parent: IntArray

    /**
     * 查找圈数
     *
     * @param M
     * @return
     */
    fun findCircleNum(M: Array<IntArray>): Int {
        count = M.size
        // M.siz 为图的节点总数
        parent = IntArray(M.size)

        Arrays.fill(parent, -1)
        for (i in M.indices) {
            for (j in M.indices) {
                if (M[i][j] == 1 && i != j) {
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
        while (parent[x] != -1) {
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