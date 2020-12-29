package com.seniorlibs.thread.collection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.thread.R

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/12/29.
 * Mender:
 * Modify:
 * Description: 测试CollectionActivity
 */
class CollectionActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "seniorLibsThreadCollection"

        fun actionStart(context: Context?) {
            if (context == null) {
                return
            }
            val intent = Intent(context, CollectionActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)

        initView()
    }

    private fun initView() {
//        findViewById<View>(R.id.test_handler).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
//            R.id.test_handler -> testHandler()
        }
    }

    /**
     * 测试ArrayList和Collections.synchronizedList(new ArrayList<>());
     *
     * @param view
     */
    fun testWriteArrayListError(view: View?) {
        ArrayListTest.testWriteArrayListError()
    }

    /**
     * 测试HashSet是否线程安全
     *
     * @param view
     */
    fun testHashSet(view: View?) {
        HashSetTest.main()
    }

    /**
     * 测试HashMap是否线程安全
     *
     * @param view
     */
    fun testHashMap(view: View?) {
        // 多线程下，扩容期间取出的值不准确
//        HashMapTest.resizeBug()
        // 为什么HashMap的"key"部分存放自定义的对象时，需要重写equals()和hashcode()方法？
        HashMapTest.testHashConflict()
    }
}