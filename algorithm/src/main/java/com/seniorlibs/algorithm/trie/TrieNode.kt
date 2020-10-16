package com.seniorlibs.algorithm.trie

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/10/16
 * Mender:
 * Modify:
 * Description: Trie节点
 */
class TrieNode {

    private var links = arrayOfNulls<TrieNode>(26)

    var isEnd = false

    fun containsKey(ch: Char): Boolean {
        return links[ch - 'a'] != null
    }

    fun get(ch: Char): TrieNode? {
        return links[ch - 'a']
    }

    fun put(ch: Char, node: TrieNode?) {
        links[ch - 'a'] = node
    }
}