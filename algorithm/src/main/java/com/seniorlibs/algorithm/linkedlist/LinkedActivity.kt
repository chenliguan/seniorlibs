package com.seniorlibs.algorithm.linkedlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.map.MapActivity
import com.seniorlibs.baselib.utils.LogUtils

/**
 * 链表测试
 */
class LinkedActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private const val TAG = "LinkedActivity"

        fun actionStart(context: Context?) {
            if (context == null) {
                return
            }

            val intent = Intent(context, LinkedActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linked)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_handler_reuse).setOnClickListener(this)
        findViewById<View>(R.id.btn_has_cycle).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_handler_reuse -> {
                handlerReuse()
            }
            R.id.btn_has_cycle -> {
                val li1 = ListNode(5)
                val li2 = ListNode(4)
                val li3 = ListNode(3)
                val li4 = ListNode(2)
                val li5 = ListNode(1)
                li1.next = li2
                li2.next = li3
                li3.next = li4
                li4.next = li5
                li5.next = li1
                val has = hasCycle(li1)
                LogUtils.e(TAG, "141.环形链表：${has}")

                val li11 = ListNode(5)
                val li22 = ListNode(4)
                val li33 = ListNode(3)
                val li44 = ListNode(2)
                li11.next = li22
                li22.next = li33
                li33.next = li44
                LogUtils.e(TAG, "141.环形链表1：${hasCycle1(li11)}")
            }

            else -> {
            }
        }
    }

    /**
     * Handler的message复用原理
     */
    private fun handlerReuse() {
        val msg = Msg()
        msg.sPool = msg
        msg.next = Msg()
        val m = msg.sPool
        LogUtils.d(TAG, "1m：$m")
        LogUtils.d(TAG, "1sPool：" + msg.sPool)
        LogUtils.d(TAG, "1m.next：" + m!!.next)
        msg.sPool = m.next
        LogUtils.e(TAG, "2m：$m")
        LogUtils.e(TAG, "2sPool：" + msg.sPool)
        LogUtils.e(TAG, "2m.next：" + m.next)
        m.next = null
        LogUtils.d(TAG, "3m：$m")
        LogUtils.d(TAG, "3sPool：" + msg.sPool)
        LogUtils.d(TAG, "3m.next：" + m.next)

//        08-09 17:24:15.933 13151-13151/? D/LinkedActivity: 1m：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@29f28fdb
//        08-09 17:24:15.933 13151-13151/? D/LinkedActivity: 1sPool：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@29f28fdb
//        08-09 17:24:15.933 13151-13151/? D/LinkedActivity: 1m.next：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@227e2a78
//        08-09 17:24:15.933 13151-13151/? E/LinkedActivity: 2m：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@29f28fdb
//        08-09 17:24:15.933 13151-13151/? E/LinkedActivity: 2sPool：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@227e2a78
//        08-09 17:24:15.933 13151-13151/? E/LinkedActivity: 2m.next：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@227e2a78
//        08-09 17:24:15.933 13151-13151/? D/LinkedActivity: 3m：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@29f28fdb
//        08-09 17:24:15.933 13151-13151/? D/LinkedActivity: 3sPool：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@227e2a78
//        08-09 17:24:15.933 13151-13151/? D/LinkedActivity: 3m.next：null
        m.next = msg.sPool
        LogUtils.e(TAG, "4m：$m")
        LogUtils.e(TAG, "4sPool：" + msg.sPool)
        LogUtils.e(TAG, "4m.next：" + m.next)
        msg.sPool = m
        LogUtils.d(TAG, "5m：$m")
        LogUtils.d(TAG, "5sPool：" + msg.sPool)
        LogUtils.d(TAG, "5m.next：" + m.next)

//        08-09 17:24:15.933 13151-13151/? E/LinkedActivity: 4m：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@29f28fdb
//        08-09 17:24:15.933 13151-13151/? E/LinkedActivity: 4sPool：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@227e2a78
//        08-09 17:24:15.934 13151-13151/? E/LinkedActivity: 4m.next：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@227e2a78
//        08-09 17:24:15.934 13151-13151/? D/LinkedActivity: 5m：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@29f28fdb
//        08-09 17:24:15.934 13151-13151/? D/LinkedActivity: 5sPool：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@29f28fdb
//        08-09 17:24:15.934 13151-13151/? D/LinkedActivity: 5m.next：com.seniorlibs.algorithm.linked.LinkedActivity$Msg@227e2a78
    }

    inner class Msg {
        var next: Msg? = null
        var sPool: Msg? = null
    }


    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    /**
     * 141.环形链表
     *
     * @param head
     * @return
     */
    private fun hasCycle(head: ListNode?): Boolean {
        if (head?.next == null) {
            return false
        }

        var slow = head  // 快指针
        var fast = head.next  // 慢指针

        while (fast != slow) { // 如果有环，快跑者最终一定会追上慢跑者
            if (fast?.next == null || fast.next?.next == null) {
                return false
            }

            slow = slow?.next
            fast = fast.next?.next
        }

        return true
    }






















    /**
     * 141.环形链表
     *
     * @param head
     * @return
     */
    private fun hasCycle1(head: ListNode?): Boolean {
        if (head == null) {
            return false
        }

        var slow = head
        var fast = head.next

        while (slow != fast) {
            if (fast?.next == null || fast.next?.next == null) {
                return false
            }

            slow = slow?.next
            fast = fast.next?.next
        }

        return true
    }
}