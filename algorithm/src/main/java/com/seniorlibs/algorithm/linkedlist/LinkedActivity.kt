package com.seniorlibs.algorithm.linkedlist

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
 * Description: 链表
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
        findViewById<View>(R.id.btn_reverse_link).setOnClickListener(this)
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
                LogUtils.e(TAG, "141.环形链表1：${has}")

                val li11 = ListNode(5)
                val li22 = ListNode(4)
                val li33 = ListNode(3)
                val li44 = ListNode(2)
                li11.next = li22
                li22.next = li33
                li33.next = li44
                LogUtils.e(TAG, "141.环形链表2：${hasCycle(li11)}")
            }
            R.id.btn_reverse_link -> {
                val li11 = ListNode(5)
                val li22 = ListNode(4)
                val li33 = ListNode(3)
                val li44 = ListNode(2)
                li11.next = li22
                li22.next = li33
                li33.next = li44
                LogUtils.e(TAG, "206. 反转链表：${reverseList(li11)}")
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

        var slow = head  // 1.快慢指针
        var fast = head.next

        while (fast != slow) {  // 2.如果快慢指针不等，继续往前跑
            if (slow?.next == null || fast?.next?.next == null) {  // 3.没环的特征是：某个next为空
                return false
            }

            slow = slow.next
            fast = fast.next?.next
        }

        return true // 4.如果有环，快跑者最终一定会追上慢跑者
    }

    /**
     * 206. 反转链表。方法一：双指针迭代
     *
     * 时间复杂度：O(n)，假设n是列表的长度，时间复杂度是 O(n)。
     * 空间复杂度：O(1)，使用常量空间。
     *
     * @param head
     * @return
     */
    private fun reverseList1(head: ListNode?): ListNode? {
        if (head == null) return null

        var pre : ListNode? = null
        var cur = head
        var temp : ListNode? = null

        while (cur != null) {
            temp = cur.next  // 缓存当前节点的下一个节点

            cur.next = pre   // 然后将当前节点指向pre

            pre = cur      // pre和cur节点都往后前进一位
            cur = temp
        }

        return pre    // pre最终成了新的表头
    }


    var pre : ListNode? = null
    var temp : ListNode? = null

    /**
     * 206. 反转链表。方法二：递归
     *
     * 时间复杂度：O(n)，假设n是列表的长度，时间复杂度是 O(n)。
     * 空间复杂度：O(n)，由于使用递归，将会使用隐式栈空间，递归深度会达到 n 层。
     *
     * @param head
     * @return
     */
    fun reverseList(h : ListNode?): ListNode? {
        if (h == null) return pre

        var head = h

        temp = head.next   // 缓存当前节点的下一个节点

        head.next = pre    // 然后将当前节点指向pre

        pre = head         // pre和cur节点都往后前进一位
        head = temp

        return reverseList(head)
    }
}