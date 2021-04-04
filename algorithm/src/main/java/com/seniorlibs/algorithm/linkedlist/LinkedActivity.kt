package com.seniorlibs.algorithm.linkedlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.algorithm.string.StringActivity
import com.seniorlibs.baselib.utils.LogUtils


/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/9/10
 * Mender:
 * Modify:
 * Description: 链表
 */
open class LinkedActivity : AppCompatActivity(), View.OnClickListener {

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
        findViewById<View>(R.id.btn_merge_two_lists).setOnClickListener(this)
        findViewById<View>(R.id.btn_get_kth_from_end).setOnClickListener(this)
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
                LogUtils.e(TAG, "206. 反转链表：${reverseList1(li11)}")
            }
            R.id.btn_merge_two_lists -> {
                val li1 = ListNode(1)
                val li2 = ListNode(2)
                val li3 = ListNode(5)
                li1.next = li2
                li2.next = li3

                val li11 = ListNode(0)
                val li22 = ListNode(3)
                val li33 = ListNode(4)
                li11.next = li22
                li22.next = li33
                LogUtils.e(TAG, "21. 合并两个有序链表：${mergeTwoLists(li1, li11)}")
            }
            R.id.btn_get_kth_from_end -> {
                val li1 = ListNode(1)
                val li2 = ListNode(2)
                val li3 = ListNode(3)
                val li4 = ListNode(4)
                val li5 = ListNode(5)
                li1.next = li2
                li2.next = li3
                li3.next = li4
                li4.next = li5
                li5.next = null
                LogUtils.e(TAG, "剑指 Offer 22. 链表中倒数第k个节点：${getKthFromEnd(li1, 4)}")
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

        // 1.快慢指针
        var slow = head
        var fast = head.next

        // 2.如果快慢指针不等，继续往前跑
        while (fast != slow) {
            // 3.没环的特征是：某个next为空
            if (slow?.next == null || fast?.next?.next == null) {
                return false
            }

            slow = slow.next
            fast = fast.next?.next
        }

        // 4.如果有环，快跑者最终一定会追上慢跑者
        return true
    }


    /**
     * 206. 反转链表。方法一：双指针迭代
     *
     * 时间复杂度：O(n)，假设n是列表的长度，时间复杂度是 O(n)。
     * 空间复杂度：O(1)，使用常量空间。
     *
     * https://leetcode-cn.com/problems/reverse-linked-list/solution/206-fan-zhuan-lian-biao-by-chen-li-guan/
     * @param head
     * @return
     */
    fun reverseList1(head: ListNode?): ListNode? {
        var prev: ListNode? = null
        var cur: ListNode? = head

        while (cur != null) {
            // 记录当前节点的下一个节点
            val next: ListNode? = cur.next
            // 然后将当前节点指向 prev
            cur.next = prev

            // prev 和 cur 节点都前进一位
            prev = cur
            cur = next
        }

        return prev
    }

    /**
     * 206. 反转链表。方法二：利用递归，倒序遍历单链表
     *
     * 时间复杂度：O(n)，假设n是列表的长度，时间复杂度是 O(n)。
     * 空间复杂度：O(n)，由于使用递归，将会使用隐式栈空间，递归深度会达到 n 层。
     *
     * https://leetcode-cn.com/problems/reverse-linked-list/solution/206-fan-zhuan-lian-biao-by-chen-li-guan/
     * @param head
     * @return
     */
    fun reverseList(head: ListNode?): ListNode? {
        // 递归终止条件是当前为空，或者下一个节点为空
        if (head?.next == null) return head

        // 利用递归，倒序遍历单链表
        val res = reverseList(head.next)
        // 后序遍历代码
        head.next?.next = head
        head.next = null
        return res
    }

//    fun reverseList(head: ListNode?): ListNode? {
//        // 递归终止条件是当前为空，或者下一个节点为空
//        if (head?.next == null) return head
//
//        // 这里的cur就是最后一个节点
//        val cur = reverseList(head.next)
//        // 如果链表是 1->2->3->4->5，那么此时的cur就是5
//        // 而head=4，head.next=5，head.next.next=null
//        // 所以head.next.next = 5.next，所以head.next?.next = head -> 5.next = 4
//        head.next?.next = head
//        // 防止链表循环，需要将head.next设置为空
//        head.next = null
//        // 每层递归函数都返回cur，也就是最后一个节点
//        return cur
//    }

    /**
     * 21. 合并两个有序链表
     *
     * 时间复杂度：O(n+m)，其中n和m分别为两个链表的长度。因为每次调用递归都会去掉 l1 或者 l2 的头节点（直到至少有一个链表为空），
     *                  函数 mergeTwoList 至多只会递归调用每个节点一次。因此，时间复杂度取决于合并后的链表长度，即 O(n+m)。
     * 空间复杂度：O(n+m)，其中n和m分别为两个链表的长度。递归调用mergeTwoLists时需要消耗栈空间，栈空间的大小取决于递归调用的深度。
     *                  结束递归调用时 mergeTwoLists 函数最多调用 n+m 次，因此空间复杂度为 O(n+m)。
     *
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/21-he-bing-liang-ge-you-xu-lian-biao-by-j3c2y/
     * @param l1
     * @param l2
     * @return
     */
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        // 1. 两条链表分别名为 l1 和 l2，当 l1 为空或 l2 为空时结束
        if (l1 == null) return l2
        if (l2 == null) return l1

        // 如果 l1 的 val 值更小，则将 l1.next 与排序好的链表头相接，l2 同理
        if (l1.`val` < l2.`val`) {
            l1.next = mergeTwoLists(l1.next, l2)
            // 每一层调用都返回排序好的链表头
            return l1
        } else {
            l2.next = mergeTwoLists(l1, l2.next)
            // 每一层调用都返回排序好的链表头
            return l2
        }
    }


    /**
     * 剑指 Offer 22. 链表中倒数第k个节点
     *
     * 1.初始化：前指针 former 、后指针 latter，双指针都指向头节点 head。
     * 2.构建双指针距离：前指针 former 先向前走 k 步（结束后，双指针 former 和 latter 间相距 k 步）。
     * 3.双指针共同移动：循环中，双指针 former 和 latter 每轮都向前走一步，直至 former 走过链表 尾节点 时跳出（跳出后，latter 与尾节点距离为 k−1，即 latter 指向倒数第 k 个节点）。
     * 4.返回值：返回 latter 即可。
     *
     * 时间复杂度 O(N) ： N 为链表长度；总体看， former 走了 N 步， latter 走了 (N−k) 步。
     * 空间复杂度 O(1) ： 双指针 former , latter 使用常数大小的额外空间。
     *
     * https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/solution/jian-zhi-offer-22-lian-biao-zhong-dao-sh-yy9c/
     * @param head
     * @param k
     * @return
     */
    fun getKthFromEnd(head: ListNode?, k: Int): ListNode? {
        var fast = head
        var slow = head

        for (i in 0 until k) {
            if (fast == null) return null
            fast = fast.next
        }

        while (fast != null) {
            fast = fast.next
            slow = slow!!.next
        }

        return slow
    }
}