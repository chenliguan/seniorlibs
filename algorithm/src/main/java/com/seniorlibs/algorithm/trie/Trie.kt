package com.seniorlibs.algorithm.trie

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/10/16
 * Mender:
 * Modify:
 * Description: Trie实现
 */
class Trie {

    private var root: TrieNode = TrieNode()

    /**
     * 插入
     *
     * 时间复杂度：O(m)，其中m为键长。在算法的每次迭代中，我们要么检查要么创建一个节点，直到到达键尾。只需要m次操作。
     * 空间复杂度：O(m)。最坏的情况下，新插入的键和Trie树中已有的键没有公共前缀。此时需要添加m个结点，使用O(m)空间。
     *
     * @param word
     */
    fun insert(word: String) {
        var node: TrieNode? = root
        for (c in word) {
            if (!node!!.containsKey(c)) {
                node.put(c, TrieNode())
            }
            node = node.get(c)
        }
        node!!.isEnd = true
    }

    /**
     * 在trie中搜索前缀或整个键，返回搜索结束的节点
     *
     * 时间复杂度 : O(m)。算法的每一步均搜索下一个键字符，最坏的情况下需要m次操作。
     * 空间复杂度 : O(1)。
     *
     * @param word
     * @return
     */
    private fun searchPrefix(word: String): TrieNode? {
        var node: TrieNode? = root
        for (c in word) {
            node = if (node!!.containsKey(c)) { node.get(c) } else { return null }
        }
        return node
    }

    /**
     * 返回单词是否在单词中
     *
     * @param word
     * @return
     */
    fun search(word: String): Boolean {
        val node = searchPrefix(word)
        return node != null && node.isEnd
    }

    /**
     * 返回trie中是否有以给定前缀开头的任何单词
     *
     * 时间复杂度 : O(m)
     * 空间复杂度 : O(1)
     *
     * @param prefix
     * @return
     */
    fun startsWith(prefix: String?): Boolean {
        val node = searchPrefix(prefix!!)
        return node != null
    }
}