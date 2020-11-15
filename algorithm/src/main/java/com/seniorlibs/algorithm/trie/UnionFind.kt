package com.seniorlibs.algorithm.trie

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/11/15.
 * Mender:
 * Modify:
 * Description: 并查集模板
 */
class UnionFind(n: Int) {

    private var count = 0
    private var parent: IntArray

    /**
     * 用 parent 数组记录每个节点的父节点，相当于指向父节点的指针，所以 parent 数组内实际存储着一个森林（若干棵多叉树）
     * 构造函数，n 为图的节点总数
     * @param x
     * @return
     */
    init {
        // 一开始互不连通
        count = n
        parent = IntArray(n)
        // 父节点指针初始指向自己
        for (i in 0 until n) parent[i] = i
    }

    /**
     * 返回某个节点 x 的根节点
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
     * @param p
     * @param q
     */
    fun union(p: Int, q: Int) {
        val rootP = find(p)
        val rootQ = find(q)
        if (rootP == rootQ) return

        // 将两棵树合并为一棵
        parent[rootP] = rootQ
        count--
    }
}
