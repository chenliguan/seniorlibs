package com.seniorlibs.algorithm.other

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.baselib.utils.LogUtils

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/9/10
 * Mender:
 * Modify:
 * Description: 其他类型Activity
 */
class OtherActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "OtherActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, OtherActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_lru_cache).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_lru_cache -> {
                /* 缓存容量 */
                val cache = LRUCache(2)
                cache.put(1, 1)
                cache.put(2, 2)
                LogUtils.d(TAG, "cache.get(1)：" + cache.get(1))
                cache.put(3, 3)    // 该操作会使得关键字 2 作废
                LogUtils.d(TAG, "cache.get(2)：" + cache.get(2))
                cache.put(4, 4)    // 该操作会使得关键字 1 作废
                LogUtils.d(TAG, "cache.get(1)：" + cache.get(1))
                LogUtils.d(TAG, "cache.get(3)：" + cache.get(3))
                LogUtils.d(TAG, "cache.get(4)：" + cache.get(4))
            }

            else -> {
            }
        }
    }
}
