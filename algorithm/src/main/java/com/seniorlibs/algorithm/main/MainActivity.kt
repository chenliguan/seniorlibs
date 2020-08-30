package com.seniorlibs.algorithm.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.array.ArrayActivity
import com.seniorlibs.algorithm.binarytree.BinaryTreeActivity
import com.seniorlibs.algorithm.linkedlist.LinkedActivity
import com.seniorlibs.algorithm.map.MapActivity
import com.seniorlibs.algorithm.queue.QueueActivity
import com.seniorlibs.algorithm.stack.StackActivity

/**
 * 主页
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
        findViewById<View>(R.id.btn_linked_list).setOnClickListener(this)
        findViewById<View>(R.id.btn_array).setOnClickListener(this)
        findViewById<View>(R.id.btn_binary_tree).setOnClickListener(this)
        findViewById<View>(R.id.btn_map).setOnClickListener(this)
        findViewById<View>(R.id.btn_stack).setOnClickListener(this)
        findViewById<View>(R.id.btn_queue).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_linked_list -> LinkedActivity.actionStart(this)
            R.id.btn_array -> ArrayActivity.actionStart(this)
            R.id.btn_binary_tree -> BinaryTreeActivity.actionStart(this)
            R.id.btn_map -> { MapActivity.actionStart(this) }
            R.id.btn_stack -> { StackActivity.actionStart(this) }
            R.id.btn_queue -> { QueueActivity.actionStart(this) }

            else -> {
            }
        }
    }
}
