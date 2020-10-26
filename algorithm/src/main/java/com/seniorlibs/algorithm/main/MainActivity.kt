package com.seniorlibs.algorithm.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.array.ArrayActivity
import com.seniorlibs.algorithm.binarysearch.BinarySearchActivity
import com.seniorlibs.algorithm.binarytree.BinaryTreeActivity
import com.seniorlibs.algorithm.bit.BitActivity
import com.seniorlibs.algorithm.db.DbActivity
import com.seniorlibs.algorithm.dfsbfs.DfsBfsActivity
import com.seniorlibs.algorithm.greedy.GreedyActivity
import com.seniorlibs.algorithm.heap.HeapActivity
import com.seniorlibs.algorithm.linkedlist.LinkedActivity
import com.seniorlibs.algorithm.map.MapActivity
import com.seniorlibs.algorithm.queue.QueueActivity
import com.seniorlibs.algorithm.recursive.BackActivity
import com.seniorlibs.algorithm.recursive.RecursiveActivity
import com.seniorlibs.algorithm.stack.StackActivity
import com.seniorlibs.algorithm.trie.TrieActivity

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/9/10
 * Mender:
 * Modify:
 * Description: 主页
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_test).setOnClickListener(this)
        findViewById<View>(R.id.btn_linked_list).setOnClickListener(this)
        findViewById<View>(R.id.btn_array).setOnClickListener(this)
        findViewById<View>(R.id.btn_map).setOnClickListener(this)
        findViewById<View>(R.id.btn_stack).setOnClickListener(this)
        findViewById<View>(R.id.btn_queue).setOnClickListener(this)
        findViewById<View>(R.id.btn_heap).setOnClickListener(this)
        findViewById<View>(R.id.btn_binary_tree).setOnClickListener(this)
        findViewById<View>(R.id.btn_dfs_bfs).setOnClickListener(this)
        findViewById<View>(R.id.btn_recursive).setOnClickListener(this)
        findViewById<View>(R.id.btn_back).setOnClickListener(this)
        findViewById<View>(R.id.btn_db).setOnClickListener(this)
        findViewById<View>(R.id.btn_greedy).setOnClickListener(this)
        findViewById<View>(R.id.btn_binary_search).setOnClickListener(this)
        findViewById<View>(R.id.btn_achieve_trie).setOnClickListener(this)
        findViewById<View>(R.id.btn_bit).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_test -> AlgorithmTestActivity.actionStart(this)
            R.id.btn_linked_list -> LinkedActivity.actionStart(this)
            R.id.btn_array -> ArrayActivity.actionStart(this)
            R.id.btn_map -> { MapActivity.actionStart(this) }
            R.id.btn_stack -> { StackActivity.actionStart(this) }
            R.id.btn_queue -> { QueueActivity.actionStart(this) }
            R.id.btn_heap -> { HeapActivity.actionStart(this) }
            R.id.btn_binary_tree -> BinaryTreeActivity.actionStart(this)
            R.id.btn_dfs_bfs -> DfsBfsActivity.actionStart(this)
            R.id.btn_recursive -> { RecursiveActivity.actionStart(this) }
            R.id.btn_back -> { BackActivity.actionStart(this) }
            R.id.btn_db -> { DbActivity.actionStart(this) }
            R.id.btn_greedy -> { GreedyActivity.actionStart(this) }
            R.id.btn_binary_search -> { BinarySearchActivity.actionStart(this) }
            R.id.btn_achieve_trie -> { TrieActivity.actionStart(this) }
            R.id.btn_bit -> { BitActivity.actionStart(this) }

            else -> {
            }
        }
    }
}
