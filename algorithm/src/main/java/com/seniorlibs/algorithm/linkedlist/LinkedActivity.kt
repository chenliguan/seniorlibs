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
 *
 * 几乎所有的链表题目，都具有相似的解题思路：
 * 1.建一个「虚拟头节点」dummy 以减少边界判断，往后的答案链表会接在 dummy 后面；
 * 2.使用 tail 代表当前有效链表的结尾；
 * 3.通过原输入的 head 指针赋值给 cur 进行链表扫描。
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
        findViewById<View>(R.id.btn_detect_cycle).setOnClickListener(this)
        findViewById<View>(R.id.btn_reverse_link).setOnClickListener(this)
        findViewById<View>(R.id.btn_reverse_between).setOnClickListener(this)
        findViewById<View>(R.id.btn_middle_node).setOnClickListener(this)
        findViewById<View>(R.id.btn_is_palindrome_linked).setOnClickListener(this)
        findViewById<View>(R.id.btn_reorder_list).setOnClickListener(this)
        findViewById<View>(R.id.btn_merge_two_lists).setOnClickListener(this)
        findViewById<View>(R.id.btn_sort_list).setOnClickListener(this)
        findViewById<View>(R.id.btn_get_kth_from_end).setOnClickListener(this)
        findViewById<View>(R.id.btn_remove_nth_from_end).setOnClickListener(this)
        findViewById<View>(R.id.btn_odd_even_list).setOnClickListener(this)
        findViewById<View>(R.id.btn_get_intersection_node).setOnClickListener(this)
        findViewById<View>(R.id.btn_swap_pairs).setOnClickListener(this)
        findViewById<View>(R.id.btn_remove_elements).setOnClickListener(this)
        findViewById<View>(R.id.btn_delete_duplicates).setOnClickListener(this)
        findViewById<View>(R.id.btn_delete_duplicates2).setOnClickListener(this)
        findViewById<View>(R.id.btn_rotate_right).setOnClickListener(this)
        findViewById<View>(R.id.btn_delete_node).setOnClickListener(this)
        findViewById<View>(R.id.btn_copy_random_list).setOnClickListener(this)
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
            R.id.btn_detect_cycle -> {
                val li11 = ListNode(5)
                val li22 = ListNode(4)
                val li33 = ListNode(3)
                val li44 = ListNode(2)
                li11.next = li22
                li22.next = li33
                li33.next = li44
                LogUtils.e(TAG, "142. 环形链表 II：${detectCycle(li11)}")
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
            R.id.btn_reverse_between -> {
                val li11 = ListNode(1)
                val li22 = ListNode(2)
                val li33 = ListNode(3)
                val li44 = ListNode(4)
                val li55 = ListNode(5)
                li11.next = li22
                li22.next = li33
                li33.next = li44
                li44.next = li55
                LogUtils.e(TAG, "92. 反转链表 II：${reverseBetween(li11, 2, 4)}")
            }
            R.id.btn_middle_node -> {
                val li11 = ListNode(1)
                val li22 = ListNode(2)
                val li33 = ListNode(3)
                val li44 = ListNode(4)
                val li55 = ListNode(5)
                li11.next = li22
                li22.next = li33
                li33.next = li44
                li44.next = li55
                LogUtils.e(TAG, "876. 链表的中间结点：${middleNode(li11)}")
            }
            R.id.btn_is_palindrome_linked -> {
                val li11 = ListNode(1)
                val li22 = ListNode(2)
                val li33 = ListNode(2)
                val li44 = ListNode(1)
                li11.next = li22
                li22.next = li33
                li33.next = li44
                LogUtils.d(StringActivity.TAG, "234. 回文链表：${isPalindrome(li11)}")
            }
            R.id.btn_reorder_list -> {
                val li11 = ListNode(1)
                val li22 = ListNode(2)
                val li33 = ListNode(3)
                val li44 = ListNode(4)
                li11.next = li22
                li22.next = li33
                li33.next = li44
                LogUtils.d(StringActivity.TAG, "143. 重排链表：${reorderList(li11)}")
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
                LogUtils.e(TAG, "21. 合并两个有序链表1：${mergeTwoLists1(li1, li11)}")
            }
            R.id.btn_sort_list -> {
                val li1 = ListNode(1)
                val li2 = ListNode(5)
                val li3 = ListNode(3)
                val li4 = ListNode(9)
                val li5 = ListNode(5)
                li1.next = li2
                li2.next = li3
                li3.next = li4
                li4.next = li5
                li5.next = null
                LogUtils.e(TAG, "148. 排序链表：${sortList(li1)}")
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
            R.id.btn_remove_nth_from_end -> {
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
                LogUtils.e(TAG, "19. 删除链表的倒数第 N 个节点：${removeNthFromEnd(li1, 2)}")
            }
            R.id.btn_odd_even_list -> {
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
                LogUtils.e(TAG, "328. 奇偶链表：${oddEvenList(li1)}")
            }
            R.id.btn_get_intersection_node -> {
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
                LogUtils.e(TAG, "160. 相交链表：${getIntersectionNode(li1, li2)}")
            }
            R.id.btn_swap_pairs -> {
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
                LogUtils.e(TAG, "24. 两两交换链表中的节点：${swapPairs(li1)}")
            }
            R.id.btn_remove_elements -> {
                val li1 = ListNode(1)
                val li2 = ListNode(2)
                val li3 = ListNode(3)
                val li4 = ListNode(3)
                val li5 = ListNode(5)
                li1.next = li2
                li2.next = li3
                li3.next = li4
                li4.next = li5
                li5.next = null
                LogUtils.e(TAG, "203. 移除链表元素：${removeElements(li1, 3)}")
            }
            R.id.btn_delete_duplicates -> {
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
                LogUtils.e(TAG, "83. 删除排序链表中的重复元素：${deleteDuplicates(li1)}")
            }
            R.id.btn_delete_duplicates2 -> {
                val li1 = ListNode(1)
                val li2 = ListNode(3)
                val li3 = ListNode(3)
                val li4 = ListNode(4)
                val li5 = ListNode(5)
                li1.next = li2
                li2.next = li3
                li3.next = li4
                li4.next = li5
                li5.next = null
                LogUtils.e(TAG, "82. 删除排序链表中的重复元素 II：${deleteDuplicates2(li1)}")
            }
            R.id.btn_rotate_right -> {
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
                LogUtils.e(TAG, "61. 旋转链表：${rotateRight(li1, 3)}")
            }
            R.id.btn_delete_node -> {
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
                LogUtils.e(TAG, "237. 删除链表中的节点（没有头结点）：${deleteNode(li2)}")
            }
            R.id.btn_copy_random_list -> {
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
                LogUtils.e(TAG, "138. 复制带随机指针的链表：${copyRandomList(li2)}")
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
     * 时间复杂度：O(n)，让我们将n设为链表中结点的总数。考虑下面两种情况，
     *      链表中不存在环：快指针将会首先到达尾部，其时间取决于列表的长度，也就是O(n)；
     *      链表中存在环：在最糟糕的情形下，时间复杂度为O(n+k)，也就是O(n)；
     *
     * 空间复杂度：O(1)，我们只使用了慢指针和快指针两个结点，所以空间复杂度为O(1)。
     *
     * https://leetcode-cn.com/problems/linked-list-cycle/solution/141-huan-xing-lian-biao-2-by-chen-li-guan/
     * @param head
     * @return
     */
    fun hasCycle(head: ListNode?): Boolean {
        // 1.快慢指针
        var slow = head
        var fast = head?.next

        // 2.如果快慢指针不等，继续往前跑
        while (slow?.next != null || fast?.next != null) {
            // 3.快慢指针相等：如果有环，快跑者最终一定会追上慢跑者
            if (slow == fast) {
                return true
            }

            slow = slow?.next
            fast = fast?.next?.next
        }

        // 4.否则没有环
        return false
    }


    /**
     * 142. 环形链表 II
     *
     * 时间复杂度：O(N)。
     * 空间复杂度：O(N)。
     *
     * https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/142-huan-xing-lian-biao-ii-by-chen-li-gu-okw4/
     * @param head
     * @return
     */
    fun detectCycle(head: ListNode?): ListNode? {
        var slow = head
        var fast = head

        while (true) {
            slow = slow?.next
            fast = fast?.next?.next

            // 第一次相遇
            if (fast === slow) {
                break
            }
        }

        // slow 指针位置不变，将 fast 指针重新 指向链表头部节点
        fast = head

        // slow和fast同时每轮向前走 1 步；
        while (true) {
            // 第二次相遇
            if (slow == fast) {
                break
            }

            slow = slow?.next
            fast = fast?.next
        }

        // 返回 slow
        return slow
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
    fun reverseList(head: ListNode?): ListNode? {
        var prev: ListNode? = null
        var cur = head

        while (cur != null) {
            // 记录当前节点的下一个节点
            val next = cur.next
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
    fun reverseList1(head: ListNode?): ListNode? {
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
     * 92. 反转链表 II
     *
     * 时间复杂度：O(n)，假设n是列表的长度，时间复杂度是 O(n)。
     * 空间复杂度：O(1)，使用常量空间。
     *
     * https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/92-fan-zhuan-lian-biao-ii-by-chen-li-gua-pfch/
     * @param head
     * @param left
     * @param right
     * @return
     */
    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        // 虚拟头结点 dummy，使其指向 head，最终返回 dummy.next
        val dummy = ListNode(0)
        dummy.next = head

        // 定义两个指针，分别称之为 cur、q
        var cur = dummy
        var q = dummy.next

        // 将 cur 移动到第一个要反转的节点的前面，将 q 移动到第一个要反转的节点的位置上
        for (i in 0 until left - 1) {
            cur = cur.next!!
            q = q?.next
        }

        // 将 cur 后面的元素删除，然后插入到 cur 的后面，也即头插法。（根据 left 和 right 重复此步骤）
        for (i in 0 until right - left) {   // cur = 1，q = 2
            val remove = q?.next      // remove = 3
            q?.next = q?.next?.next              // q?.next = 4  ->  1，2，4

            remove?.next = cur.next              // 1、3、2
            cur.next = remove
        }

        // 返回虚拟头结点 dummy 的下一个节点，即是 头结点
        return dummy.next
    }


    /**
     * 876. 链表的中间结点
     *
     * 时间复杂度：O(n)，假设n是列表的长度，时间复杂度是 O(n)。
     * 空间复杂度：O(1)，使用常量空间。
     *
     * https://leetcode-cn.com/problems/middle-of-the-linked-list/solution/876-lian-biao-de-zhong-jian-jie-dian-by-1ost7/
     * @param head
     * @return
     */
    fun middleNode(head: ListNode?): ListNode? {
        var slow = head
        var fast = head

        // 通过快、慢指针找到链表的中点，最终 slow 指针现在指向链表中点
        while (fast?.next != null) {
            slow = slow?.next!!
            fast = fast?.next?.next
        }

        return slow
    }

    /**
     * 234. 回文链表
     *
     * 思路：
     * 用快慢指针，快指针有两步，慢指针走一步，快指针遇到终止位置时，慢指针就在链表中间位置
     * 同时用pre记录慢指针指向节点的前一个节点，用来分割链表
     * 将链表分为前后均等两部分，如果链表长度是奇数，那么后半部分多一个节点
     * 将后半部分反转 ，得cur2，前半部分为cur1
     * 按照cur1的长度，一次比较cur1和cur2的节点数值
     *
     *
     * 时间复杂度：O(n)。只遍历了一遍字符串。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/palindrome-linked-list/solution/234-hui-wen-lian-biao-by-chen-li-guan-nq60/
     *
     * @param head
     * @return
     */
    private fun isPalindrome(head: ListNode?): Boolean {
        var slow = head
        var fast = head

        // 通过快、慢指针找到链表的中点，最终 slow 指针现在指向链表中点
        while (fast?.next != null) {
            slow = slow!!.next
            fast = fast.next!!.next
        }

        // 如果 fast 指针没有指向 null，说明链表的长度是奇数，slow 指针还得再向前进一步
        if (fast != null) {
            slow = slow!!.next
        }

        var l1 = head
        // 从 slow 开始反转后面的链表
        var l2 = reverseList(slow)
        // 开始比较回文串
        while (l2 != null) {
            if (l1?.`val` != l2.`val`) return false

            l1 = l1.next
            l2 = l2.next
        }
        return true
    }


    /**
     * 143. 重排链表
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(1)。
     *
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6
     * 第一步，将链表平均分成两半
     * 1 -> 2 -> 3
     * 4 -> 5 -> 6
     * 第二步，反转第二个链表
     * 1 -> 2 -> 3
     * 6 -> 5 -> 4
     *
     * 第三步，依次连接两个链表
     * 1 -> 6 -> 2 -> 5 -> 3 -> 4
     *
     * https://leetcode-cn.com/problems/reorder-list/solution/143-zhong-pai-lian-biao-by-chen-li-guan-d4y8/
     *
     * @param head
     */
    fun reorderList(head: ListNode?) {
        if (head?.next == null || head.next?.next == null) return
        var head = head

        // 1.通过快、慢指针找到链表的中点，最终 slow 指针现在指向链表中点
        var slow = head
        var fast = head
        while (fast?.next != null) {
            slow = slow!!.next
            fast = fast.next!!.next
        }

        // 2.
        // 链表分成两个
        var l1 = head
        var l2 = slow?.next
        slow?.next = null
        // 反转第二个链表
        l2 = reverseList(l2)

        // 3.合并两个链表（交叉着合并）
        var temp1: ListNode? = null
        var temp2: ListNode? = null
        while (l1 != null && l2 != null) {
            // 缓存两个链表的 next 指针，为链表正常往后指
            temp1 = l1.next
            temp2 = l2.next

            l1.next = l2
            l1 = temp1

            l2.next = l1
            l2 = temp2
        }
    }


    /**
     * 21. 合并两个有序链表  方法1：迭代（推荐）
     *
     * 思路：小的连小的，所以 l1 小，就连到 l1，l2 小就连到 l2
     *
     * 时间复杂度：O(n+m)，其中 n 和 m 分别为两个链表的长度。因为每次调用递归都会去掉 l1 或者 l2 的头节点（直到至少有一个链表为空），
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/21-he-bing-liang-ge-you-xu-lian-biao-by-j3c2y/
     * @param l1
     * @param l2
     * @return
     */
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        var l1 = l1
        var l2 = l2

        val dummy = ListNode(0)
        var cur = dummy

        while (l1 != null && l2 != null) {
            if (l1.`val` <= l2.`val`) {
                cur.next = l1
                l1 = l1.next
            } else {
                cur.next = l2
                l2 = l2.next
            }

            // 记住：指针往前移动
            cur = cur.next!!
        }

        // 合并后 l1 和 l2 只有一个还未被合并完，直接将链表末尾指向未合并完的链表
        if (l1 == null) {
            cur.next = l2
        } else {
            cur.next = l1
        }

        return dummy.next
    }
    
    /**
     * 21. 合并两个有序链表  方法2：递归
     *
     * 思路：递归就是程序内部维护了一个栈。这个题就是每次都把最小值压入栈，最后出栈的时候，将所有数连在一起就可以了。
     *      说白了是用一个栈维护了顺序。最后的连接，当然是小的连小的，所以 l1 小，就连到 l1，l2 小就连到 l2，
     *      最后先返回的，就是最小的头结点。
     *
     * 时间复杂度：O(n+m)，其中 n 和 m 分别为两个链表的长度。因为每次调用递归都会去掉 l1 或者 l2 的头节点（直到至少有一个链表为空），
     *                  函数 mergeTwoList 至多只会递归调用每个节点一次。因此，时间复杂度取决于合并后的链表长度，即 O(n+m)。
     * 空间复杂度：O(n+m)，其中 n 和 m 分别为两个链表的长度。递归调用mergeTwoLists时需要消耗栈空间，栈空间的大小取决于递归调用的深度。
     *                  结束递归调用时 mergeTwoLists 函数最多调用 n+m 次，因此空间复杂度为 O(n+m)。
     *
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/21-he-bing-liang-ge-you-xu-lian-biao-by-j3c2y/
     * @param l1
     * @param l2
     * @return
     */
    fun mergeTwoLists1(l1: ListNode?, l2: ListNode?): ListNode? {
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
     * 148. 排序链表
     *
     * 时间复杂度：O(nlogn)，其中 n 是链表的长度。
     * 空间复杂度：O(logn)，其中 n 是链表的长度。空间复杂度主要取决于递归调用的栈空间。
     *
     * https://leetcode-cn.com/problems/sort-list/solution/148-pai-xu-lian-biao-by-chen-li-guan-xlom/
     *
     * @param head
     * @return
     */
    fun sortList(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        // 获取链表的中间结点
        val slow = middleNode1(head)
        val head2 = slow?.next
        slow?.next = null

        // 分治递归
        val l1 = sortList(head)

        val l2 = sortList(head2)

        // 合并链表
        return mergeTwoLists(l1, l2)
    }

    /**
     * 注意：和 876. 链表的中间结点 的区别，那是 fast = head
     *
     * @param head
     * @return
     */
    fun middleNode1(head: ListNode?): ListNode? {
        var slow = head
        // 区别：
        var fast = head?.next

        // 通过快、慢指针找到链表的中点，最终 slow 指针现在指向链表中点
        while (fast?.next != null) {
            slow = slow?.next!!
            fast = fast.next?.next
        }

        return slow
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
        // 虚拟头结点 dummy，使其指向 head，最终返回 dummy.next
        val dummy = ListNode(0)
        dummy.next = head

        // 设定双指针 left 和 right，初始都指向虚拟节点 dummy
        var left = dummy
        var right = dummy

        // 移动 right，直到 left 与 right 之间相隔的元素个数为 n
        for (i in 0 until k) {
            right = right.next!!
        }

        // 同时移动 left 与 right，直到 right 指向 null（此时，left 指向待删除节点左节点）
        while (right.next != null) {
            left = left.next!!
            right = right.next!!
        }

        // 区别：left 指向倒数 k 个节点的前一个节点
        return left.next
    }

    /**
     * 19. 删除链表的倒数第 N 个节点
     *
     * 时间复杂度：O(n)。只遍历了一遍。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/solution/19-shan-chu-lian-biao-de-dao-shu-di-n-ge-b48v/
     * @param head
     * @param n
     * @return
     */
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        // 虚拟头结点 dummy，使其指向 head，最终返回 dummy.next
        val dummy = ListNode(0)
        dummy.next = head

        // 设定双指针 left 和 right，初始都指向虚拟节点 dummy
        var left = dummy
        var right = dummy

        // 移动 right，直到 left 与 right 之间相隔的元素个数为 n
        for (i in 0 until n) {
            right = right.next!!
        }

        // 同时移动 left 与 right，直到 right 指向 null（此时，left 指向待删除节点左节点）
        while (right.next != null) {
            left = left.next!!
            right = right.next!!
        }

        // 删除节点：将 left 的下一个节点指向下下个节点
        left.next = left.next?.next

        return dummy.next
    }


    /**
     * 328. 奇偶链表
     * 思想：将链表分为奇数链表和偶数链表，然后合并。其中，奇数链表的头节点为原链表的头节点 head，偶数链表的头节点为原链表头节点的下一个节点 head.next。
     *
     * 时间复杂度：O(n)。只遍历了一遍。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/odd-even-linked-list/solution/328-qi-ou-lian-biao-by-chen-li-guan-nzvi/
     * @param head
     * @return
     */
    fun oddEvenList(head: ListNode?): ListNode? {
        // 虚拟头结点 dummy，使其指向 head，最终返回 dummy.next
        val dummy = ListNode(0)
        dummy.next = head

        // head 为奇数链表的头节点，odd 为奇数链表的尾节点
        var odd = dummy.next
        // even 为偶数链表的头节点
        var even = dummy.next?.next
        // evenHead 为偶数链表的头节点
        val evenHead = even

        // 因为先判断的 odd 所以它的约束条件要来前面
        while (odd?.next != null && even?.next != null) {
            odd.next = even.next
            odd = odd.next

            even.next = odd?.next
            even = even.next
        }

        odd?.next = evenHead

        return dummy.next
    }


    /**
     * 160. 相交链表
     *
     * 题解：设链表A的长度为a+c，链表B的长度为b+c，a为链表A不公共部分，b为链表B不公共部分，c为链表A、B的公共部分。
     * 将两个链表连起来，A->B和B->A，长度：a+c+b+c=b+c+a+c，若链表AB相交，则a+c+b与b+c+a就会抵消，它们就会在c处相遇；
     * 若不相交，则c为nullptr，则a+b=b+a，它们各自移动到尾部循环结束，即返回nullptr
     *
     * @param headA
     * @param headB
     * @return
     */
    fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
        if (headA == null || headB == null) return null

        var pA = headA
        var pB = headB
        while (pA !== pB) {
            pA = if (pA == null) headB else pA.next
            pB = if (pB == null) headA else pB.next
        }
        return pA
    }

    /**
     * 24. 两两交换链表中的节点  [1,2,3,4] --> [2,1,4,3]
     *
     * 时间复杂度：O(n)；
     * 空间复杂度：O(1)；
     *
     * https://leetcode-cn.com/problems/swap-nodes-in-pairs/solution/24-liang-liang-jiao-huan-lian-biao-zhong-p05v/
     * @param head
     * @return
     */
    fun swapPairs(head: ListNode?): ListNode? {
        // 虚拟头结点 dummy，使其指向 head，最终返回 dummy.next
        val dummy = ListNode(0)
        dummy.next = head
        var cur = dummy

        while (cur.next != null && cur.next?.next != null) {
            // cur -> 1 -> 2 -> next
            val start = cur.next       // 1
            val end = cur.next?.next   // 2

            // cur -> 2 -> 1 -> next
            start?.next = end?.next
            end?.next = start
            // cur -> 2 -> 1 -> next
            cur.next = end

            // 1 -> 2(cur) -> next
            cur = start!!
        }

        return dummy.next
    }


    /**
     * 203. 移除链表元素    [1,2,6,3,4,5,6], val = 6  -->  [1,2,3,4,5]
     *
     * 时间复杂度：O(N)，只遍历了一次。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/remove-linked-list-elements/solution/203-yi-chu-lian-biao-yuan-su-by-chen-li-vzubk/
     * @param head
     * @param val
     * @return
     */
    fun removeElements(head: ListNode?, `val`: Int): ListNode? {
        // 虚拟头结点 dummy，使其指向 head，最终返回 dummy.next
        val dummy = ListNode(0)
        dummy.next = head

        var pre = dummy
        var cur = dummy.next

        while (cur != null) {
            if (cur.`val` == `val`) {
                pre.next = cur.next
            } else {
                pre = cur
            }
            cur = cur.next
        }

        return dummy.next
    }

    /**
     * 83. 删除排序链表中的重复元素
     * 区别：删除所有重复的元素，使每个元素 只出现一次 。
     *
     * 时间复杂度：O(n)，其中 n 是链表的长度。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/solution/83-shan-chu-pai-xu-lian-biao-zhong-de-zh-vlqd/
     * @param head
     * @return
     */
    fun deleteDuplicates(head: ListNode?): ListNode? {
        // 虚拟头结点 dummy，使其指向 head，最终返回 dummy.next。注意：-100 <= Node.val <= 100
        val dummy = ListNode(-101)
        dummy.next = head
        var cur = dummy

        while (cur.next != null) {
            if (cur.`val` == cur.next?.`val`) {
                // 跳过当前的重复节点，使得cur指向当前重复元素的最后一个位置
                cur.next = cur.next?.next
            } else {
                // 不重复，移到下一个位置
                cur = cur.next!!
            }
        }

        return dummy.next
    }

    /**
     * 82. 删除排序链表中的重复元素 II
     * 区别：删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
     *
     * 时间复杂度：O(n)，其中 n 是链表的长度。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/solution/82-shan-chu-pai-xu-lian-biao-zhong-de-zh-s4sy/
     * @param head
     * @return
     */
    fun deleteDuplicates2(head: ListNode?): ListNode? {
        // 对链表进行一次遍历，就可以删除重复的元素。由于链表的头节点可能会被删除，因此我们需要额外使用一个 dummy 节点指向链表的头节点
        val dummy = ListNode(0)
        dummy.next = head
        var cur = dummy

        while (cur.next != null) {
            // 记下值 x，不断移除 x 指向 cur.next 为空的节点或者其值不等于 x 的节点  1,3,3,4,5  cur->0,cur.next->1,x->1  cur->1,cur.next->3,x->3
            var x = cur.next
            while (x != null && x.`val` == cur.next?.`val`) { // cur.next->1 == x->1  cur.next->3 == x->3 -- cur.next->3 == x->4
                x = x.next  // x->3  x->3--x->4
            }

            if (x?.`val` == cur.next?.next?.`val`) {
                // 将 cur 指向 cur.next，正常移动 cur 指针到下一个节点
                cur = cur.next!!  // x->3 == cur.next.next->0.next->1.next->3  -->  cur->3
            } else {
                // 将 cur.next 指向 x，相当于将链表中所有值为 x 的节点全部删除
                cur.next = x  // x->4 == cur.next.next->1.next->3.next->4  -->  cur.next->1.next->4
            }
        }

        // 返回链表的的哑节点的下一个节点 dummy.next 即可
        return dummy.next
    }



    /**
     * 61. 旋转链表
     *
     * 题意：给你一个链表的头节点 head，旋转链表，将链表每个节点向右移动 k 个位置。
     * 思路：先转化为循环链表，再找头结点
     *
     * 时间复杂度：O(n)，其中 n 是链表的长度。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/rotate-list/solution/61-xuan-zhuan-lian-biao-by-chen-li-guan-zotm/
     * @param head
     * @param k
     * @return
     */
    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        if (head == null) return head

        var k = k

        // 1.计算链表的长度，将 p 指向链表的尾部节点  1,2,3,4,5  head->1,p->5
        var n = 1
        var p = head
        while (p?.next != null) {
            p = p.next
            n++
        }

        // 2.链表的尾部节点的 next 指向原链表的头节点，转化为循环链表  1,2,3,4,5  p.next->1
        p?.next = head

        // 避免 k 可能会大于最大长度
        k %= n

        // 3.找到新链表的头节点 == 将链表每个节点向右移动 k 个位置  1,2,3,4,5  k=2,p.next->1,cur->3
        var count = 0
        var cur = head
        while (count < n - 1 - k) {
            // 记录步数
            count++
            cur = cur?.next
        }

        // 4.在新链表的头结点之前断环  1,2,3,4,5  newHead->4,cur->3,cur.next->null  4,5,1,2,3
        val newHead = cur?.next
        cur?.next = null
        return newHead
    }


    /**
     * 237. 删除链表中的节点（没有头结点）
     *
     * 时间复杂度都是：O(1)。
     * 空间复杂度：O(1)。
     *
     * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/solution/237-shan-chu-lian-biao-zhong-de-jie-dian-6b8v/
     * @param node
     */
    fun deleteNode(node: ListNode) {
        node.`val` = node.next!!.`val`
        node.next = node.next!!.next
    }


    /**
     * 138. 复制带随机指针的链表
     *
     * 时间复杂度：O(N)；
     * 空间复杂度：O(1)；
     *
     * https://leetcode-cn.com/problems/copy-list-with-random-pointer/solution/138-fu-zhi-dai-sui-ji-zhi-zhen-de-lian-b-c8d5/
     *
     * @param head
     * @return
     */
    fun copyRandomList(head: ListNode?): ListNode? {
        if (head == null) return null

        // 1.创建一个哈希表，key 是原节点，value 是新节点
        val map = hashMapOf<ListNode, ListNode>()
        var cur = head
        // 将原节点作为 key，新节点作为 value 放入哈希表中
        while (cur != null) {
            val newNode = ListNode(cur.`val`)
            map[cur] = newNode
            cur = cur.next
        }

        // 2.重新执行原链表头结点
        cur = head
        // 3.遍历原链表，设置新节点的 next 和 random 指针
        while (cur != null) {
            val newNode = map[cur]
            // cur 是原节点，map[cur] 是对应的新节点。cur.next 是原节点的next，map[cur.next] 是原节点的next对应的 新节点的next
            if (cur.next != null) {
                newNode?.next = map[cur.next!!]
            }
            // cur.random 是原节点随机指向，map[cur.random] 是原节点随机指向对应的新节点
//            if (cur.random != null) {
//                newNode.random = map[cur.random]
//            }
            // 移动旧链表
            cur = cur.next
        }

        // 4.返回头结点，即原头节点对应的 新链表的头节点
        return map[head]
    }

}