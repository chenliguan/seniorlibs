package com.seniorlibs.algorithm.recursive

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.baselib.utils.LogUtils
import java.util.*
import kotlin.collections.ArrayList


/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/9/10
 * Mender:
 * Modify:
 * Description: 回溯算法
 */
class BackActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "BackActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, BackActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_bracket_generate).setOnClickListener(this)
        findViewById<View>(R.id.btn_letter_combinations).setOnClickListener(this)
        findViewById<View>(R.id.btn_subsets).setOnClickListener(this)
        findViewById<View>(R.id.btn_combine).setOnClickListener(this)
        findViewById<View>(R.id.btn_permute).setOnClickListener(this)
        findViewById<View>(R.id.btn_restore_ip_addresses).setOnClickListener(this)
        findViewById<View>(R.id.btn_solve_n_queens).setOnClickListener(this)
    }

    private fun initData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_bracket_generate -> {
                LogUtils.e(TAG, "22. 括号生成：${generateParenthesis(3)}")
            }
            R.id.btn_letter_combinations -> {
                LogUtils.e(TAG, "17. 电话号码的字母组合：${letterCombinations("23")}")
            }
            R.id.btn_subsets -> {
                LogUtils.e(TAG, "78. 子集：${subsets(intArrayOf(1, 2, 3))}")
            }
            R.id.btn_combine -> {
                LogUtils.e(TAG, "77. 组合：${combine(4, 2)}")
            }
            R.id.btn_permute -> {
                LogUtils.e(TAG, "46. 全排列：${permute(intArrayOf(1, 2, 3))}")
            }
            R.id.btn_restore_ip_addresses -> {
                LogUtils.e(TAG, "93. 复原 IP 地址：${restoreIpAddresses("25525511135")}")
            }
            R.id.btn_solve_n_queens -> {
                LogUtils.e(TAG, "51. N 皇后：${solveNQueens(4)}")
            }

            else -> {
            }
        }
    }

    /**
     * 22. 括号生成  方法一：回溯
     *
     * 思路：左括号只要小于 n，left 随时可以加。right 必须之前有左括号，左括号个数 > 右括号个数
     *
     * 时间复杂度：O(4^n)，n值对应的决策树有2*n层，节点个数是1,2,4,8......，应该有2^{2n} - 1个节点，每个节点代表一个子问题，需要用O(1)时间解决，时间复杂度为O(2^{2n} - 1) = O(4^n)
     * 空间复杂度：O(n)，除了答案数组之外，需要的空间取决于递归栈的深度，每一层递归函数需要O(1)的空间，最多递归2n层，因此空间复杂度为O(n)。
     *
     * https://leetcode-cn.com/problems/generate-parentheses/
     * @param n
     * @return
     */
    fun generateParenthesis(n: Int): List<String> {
        val res = mutableListOf<String>()
        if (n == 0) return res

        // ((()))
        // 添加 ( , 左括号只要小于 n，left 随时可以加
        // 添加 ) ，右括号之前必须有左括号，左括号个数 > 右括号个数
        generate(0, 0,  n, StringBuilder(), res)

        return res
    }

    /**
     * 可用的左括号数量为 left 个，可用的右括号数量为 right 个
     */
    fun generate(left: Int, right: Int, n : Int, str : StringBuilder, res : MutableList<String>) {
        // 若左括号比n多 或者 左括号比右括号少，说明不合法，即"剪枝"
        if (left > n || left < right) return

        // 当所有括号都用完时，得到一个合法的括号组合
        if (left == n && right == n) {
            res.add(str.toString())
            return
        }

        // 尝试添加一个左括号
        str.append("(")                               // 选择
        generate(left + 1, right, n , str, res)
        str.deleteCharAt(str.length - 1)       // 撤消选择

        // 尝试添加一个右括号
        str.append(")")
        generate(left, right + 1, n , str, res)
        str.deleteCharAt(str.length - 1)
    }

    /**
     * 方法二：递归+剪枝 ——> 由于字符串的特殊性，产生一次拼接都生成新的对象，因此无需回溯
     */
//    fun generate(left: Int, right: Int, n : Int, str : StringBuilder, res : MutableList<String>) {
//        // 不合法情况提前结束，即"剪枝"
//        if (left > n || right > left) return
//
//        // 当所有括号都用完时，得到一个合法的括号组合
//        if (left == n && right == left) {
//            res.add(str.toString())
//            return
//        }
//
//        // 尝试添加一个左括号
//        if (left < n) {
//            generate(left + 1, right, n , str + "(", res)
//        }
//
//        // 尝试添加一个右括号
//        if (right < left) {
//            generate(left, right + 1, n , str + ")", res)
//        }
//    }


    /**
     * 17. 电话号码的字母组合（排列）
     *
     * 时间复杂度：O(3^m * 4^n)，其中 m 是输入中对应 3 个字母的数字个数（包括数字2、3、4、5、6、8），n 是输入中对应 4 个字母的数字个数（包括数字 7、9）
     * 空间复杂度：O(m+n)，m+n 是输入数字的总个数
     *
     * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/solution/17-dian-hua-hao-ma-de-zi-mu-zu-he-by-che-psfq/
     */
    private val letterMap = arrayOf(
        " ",  //0
        "",  //1
        "abc",  //2
        "def",  //3
        "ghi",  //4
        "jkl",  //5
        "mno",  //6
        "pqrs",  //7
        "tuv",  //8
        "wxyz" //9
    )

    private var array = arrayListOf<String>()

    fun letterCombinations(digits: String): List<String> {
        if (digits == "") return array

        findCombination(digits, 0, StringBuilder())

        return array
    }

    private fun findCombination(digits: String, index: Int, str : StringBuilder) {
        if (index == digits.length) {
            array.add(str.toString())
            return
        }

        val c = digits[index]
        val letters = letterMap[c - '0']

        for (i in 0 until letters.length) {
            // a + d
            str.append(letters[i])                        // 选择

            findCombination(digits, index + 1, str)

            str.delete(str.length - 1, str.length)        // 撤消选择
        }
        return
    }



    /**
     * 排列、子集与组合的区别？
     *
     * 1、排列 是顺序有关的，但 [1,2] 和 [2,1] 是两种不一样的排列。 而子集、组合是无关顺序的，如 [1,2] 和 [2,1] 是同一个组合(子集)。
     *
     * 2、排列是 n 个数随机组合，每个数只出现一次即可，如 [1,2]、[2,1]。而子集、组合从 start 开始，防止产生重复的组合，例如：1-2、2-1（X）
     *
     * 3、排列下一个遍历起点的数字没有递增递减限制，如 [1,2]、[2,1]。 而子集、组合下一个遍历起点的数字都是递增的，即是当前选择的数字(i) +1，如 [1,2]、[1,3]、[1,4]
     */


    /**
     * 回溯思想：找到一个子集，结束了递归，要撤销当前的选择（从list中删掉），回到选择前的状态，做另一个选择：不选当前的数，往下递归，继续生成子集。
     *          回退到上一步，把路走全，才能在包含解的空间树中，回溯出所有的解。
     */

    /**
     * 46. 全排列，输入一组不重复的数字，返回它们的全排列  方法：回溯
     *
     * 时间复杂度：O(n×n!)，其中 n 为序列的长度。
     * 空间复杂度：O(n)
     *
     * https://leetcode-cn.com/problems/permutations/solution/46-quan-pai-lie-by-chen-li-guan-a7o5/
     * @param nums
     * @return
     */
    fun permute(nums: IntArray): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        dfsPermute(nums, mutableListOf(), res)
        return res
    }

    fun dfsPermute(nums: IntArray, list: MutableList<Int>, res: MutableList<List<Int>>) {
        // 区别1：到达叶子节点才加入 res
        if (list.size == nums.size) {
            // dfs 完成以后，回到了根结点，成为空列表。因为指向的是同一块内存地址，因此看到6个空的列表对象。
            res.add(ArrayList(list))
            return
        }

        // 区别2：从 0 开始，允许产生重复的组合，例如：1-2、2-1
        for (i in 0 until nums.size) {
            // 区别2：排除 list 中相同的元素的情况，eg：list={1}，i=1 --> 返回
            if (list.contains(nums[i])) continue

            // 做选择
            list.add(nums[i])

            // 进入下一层决策树
            dfsPermute(nums, list, res)

            // 取消选择：回溯发生在从 深层结点 回到 浅层结点 的过程，代码在形式上和递归之前是对称的
            list.removeAt(list.size - 1)
        }
    }

    /**
     * 剑指 Offer 38. 字符串的排列
     * 区别：使用 MutableSet 避免重复 "aab" ：["aab","aba","aab","aba","baa","baa"] --> ["aba","aab","baa"]
     *
     * 时间复杂度：O(n×n!)，其中 n 为序列的长度。
     * 空间复杂度：O(n)
     *
     * https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/solution/jian-zhi-offer-38-zi-fu-chuan-de-pai-lie-86ak/
     * @param s
     * @return
     */
    fun permutation(s: String): Array<String> {
        val res = mutableSetOf<String>()
        dfsPermutation(s, StringBuilder(), BooleanArray(s.length), res)
        return res.toTypedArray()
    }

    fun dfsPermutation(s: String, sb : StringBuilder, visited : BooleanArray, res: MutableSet<String>) {
        // 区别1：到达叶子节点才加入 res
        if (sb.length == s.length) {
            // dfs 完成以后，回到了根结点，成为空列表。因为指向的是同一块内存地址，因此看到6个空的列表对象。
            res.add(sb.toString())
            return
        }

        // 区别2：从 0 开始，允许产生重复的组合，例如：1-2、2-1
        for (i in 0 until s.length) {
            // 区别2：已经选择过的就不能再选了，eg：list={1}，i=1 --> 返回
            if (visited[i]) continue

            // 做选择
            sb.append(s[i])
            visited[i] = true

            // 进入下一层决策树
            dfsPermutation(s, sb, visited, res)

            // 取消选择：回溯发生在从 深层结点 回到 浅层结点 的过程，代码在形式上和递归之前是对称的
            sb.deleteCharAt(sb.length - 1)
            visited[i] = false
        }
    }


    /**
     * 78. 子集--方法：二叉树 + 回溯
     *
     * 思想：在执行子递归之前，加入解集，即:在递归压栈前 "做事情"。
     *
     * 用 for 枚举出当前可选的数，比如选第一个数时：1、2、3 可选。
     * 如果第一个数选 1 ——> 选第二个数，2、3 可选；
     * 如果第一个数选 2 ——> 选第二个数，只有 3 可选（不能选1，产生重复组合）
     * 如果第一个数选 3 ——> 没有第二个数可选
     *
     * 时间复杂度：O(n×2^n)。一共2^n个状态，每种状态需要O(n)的时间来构造子集；
     * 空间复杂度：O(n)。
     *
     * https://leetcode-cn.com/problems/subsets/solution/78-zi-ji-by-chen-li-guan-jh4l/
     * @param nums
     * @return
     */
    fun subsets(nums: IntArray): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        // 记录「路径」
        dfsSubsets(nums, 0, mutableListOf(), res)
        return res
    }

    fun dfsSubsets(nums: IntArray, start : Int, list: MutableList<Int>, res: MutableList<List<Int>>) {
        // 区别1：前序遍历的位置
        res.add(ArrayList(list))

        // 区别2：从 start 开始，防止产生重复的组合，例如：1-2、2-1（X）
        // 下一个遍历起点的数字都是递增的，即是当前选择的数字(i) +1
        for (i in start until nums.size) {
            // 做选择
            list.add(nums[i])

            // 进入下一层决策树
            dfsSubsets(nums, i + 1, list, res)

            // 取消选择：回溯发生在从 深层结点 回到 浅层结点 的过程，代码在形式上和递归之前是对称的
            list.removeAt(list.size - 1)
        }
    }

    /**
     * 77. 组合  方法：回溯
     * 题目：给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     *
     * 时间复杂度：O(n×2^n)。一共2^n个状态，每种状态需要O(n)的时间来构造子集；
     * 空间复杂度：O(n)。
     *
     * https://leetcode-cn.com/problems/combinations/solution/77-zu-he-by-chen-li-guan-8srk/
     * @param n
     * @param k
     * @return
     */
    fun combine(n: Int, k : Int): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        difCombine(n, k, 1, mutableListOf(), res)
        return res
    }

    fun difCombine(n: Int, k : Int, start : Int, list: MutableList<Int>, res: MutableList<List<Int>>) {
        // 区别1：到达叶子节点才加入 res（组合限制了高度为 k）
        if (k == list.size) {
            // dfs 完成以后，回到了根结点，成为空列表。因为指向的是同一块内存地址，因此看到6个空的列表对象。
            res.add(ArrayList(list))
            return
        }

        // 区别2：从 start 开始，防止产生重复的组合，例如：1-2、2-1（X）
        // 下一个遍历起点的数字都是递增的，即是当前选择的数字(i) +1
        for (i in start until n + 1) {
            // 做选择
            list.add(i)

            // 进入下一层决策树
            difCombine(n, k, i + 1, list, res)

            // 取消选择：回溯发生在从 深层结点 回到 浅层结点 的过程，代码在形式上和递归之前是对称的
            list.removeAt(list.size - 1)
        }
    }

    /**
     * 39. 组合总和
     * 题目：给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *      candidates 中的数字可以无限制重复被选取。
     *
     * 时间复杂度：O(n×2^n)。一共2^n个状态，每种状态需要O(n)的时间来构造子集；
     * 空间复杂度：O(n)。
     *
     * https://leetcode-cn.com/problems/combination-sum/solution/39-zu-he-zong-he-by-chen-li-guan-ebcb/
     * @param array
     * @param target
     * @return
     */
    fun combinationSum(array: IntArray, target: Int): List<List<Int?>?>? {
        val res = mutableListOf<List<Int>>()
        if (array.isEmpty()) {
            return res
        }

        // 排序是剪枝的前提：排序后，如果比 target 大，后面的就不需要递归了，因为后面的数会更大
        Arrays.sort(array)
        dfs(array, 0, target, mutableListOf(), res)
        return res
    }

    private fun dfs(array: IntArray, start: Int, target: Int, list: MutableList<Int>, res: MutableList<List<Int>>) {
        // 由于进入更深层的时候，小于 0 的部分被剪枝，因此递归终止条件值只判断等于 0 的情况
        if (target == 0) {
            res.add(ArrayList(list))
            return
        }

        // 下一个遍历起点的数字都是递增的，即是当前选择的数字(i) +1
        for (i in start until array.size) {
            // 重点理解这里剪枝，前提是候选数组已经有序，
            if (target - array[i] < 0) {
                break
            }

            // 做选择
            list.add(array[i])

            // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
            dfs(array, i,target - array[i], list, res)

            // 取消选择：回溯发生在从 深层结点 回到 浅层结点 的过程，代码在形式上和递归之前是对称的
            list.removeAt(list.size - 1)
        }
    }


    /**
     * 93. 复原 IP 地址
     *
     * 时间复杂度：O(3^n×4)，其中 n 为序列的长度。
     * 空间复杂度：O(4)
     *
     * @param s
     * @return
     */
    fun restoreIpAddresses(s: String): List<String?>? {
        val res: MutableList<String?> = ArrayList()
        // 如果长度不够，不搜索
        if (s.length < 4 || s.length > 12) {
            return res
        }

        val path = LinkedList<String>()
        val splitTimes = 0
        dfs(s, s.length, splitTimes, 0, path, res)
        return res
    }

    private fun dfs(s: String, len: Int, split: Int, begin: Int, path: LinkedList<String>, res: MutableList<String?>) {
        if (begin == len) {
            if (split == 4) {
//                res.add(String.join(".", path))
            }
            return
        }

        // 看到剩下的不够了，就退出（剪枝），len - begin 表示剩余的还未分割的字符串的位数
        if (len - begin < 4 - split || len - begin > 3 * (4 - split)) {
            return
        }

        for (i in 0..2) {
            if (begin + i >= len) {
                break
            }
            val ipSegment = judgeIfIpSegment(s, begin, begin + i)
            if (ipSegment != -1) {
                // 在判断是 ip 段的情况下，才去做截取
                path.addLast(ipSegment.toString() + "")
                dfs(s, len, split + 1, begin + i + 1, path, res)
                path.removeLast()
            }
        }
    }

    /**
     * 判断 s 的子区间 [left, right] 是否能够成为一个 ip 段
     * 判断的同时顺便把类型转了
     *
     * @param s
     * @param left
     * @param right
     * @return
     */
    private fun judgeIfIpSegment(s: String, left: Int, right: Int): Int {
        val len = right - left + 1

        // 大于 1 位的时候，不能以 0 开头
        if (len > 1 && s[left] == '0') {
            return -1
        }

        // 转成 int 类型
        var res = 0
        for (i in left..right) {
            res = res * 10 + s[i].toInt() - '0'.toInt()
        }
        return if (res > 255) -1 else res
    }


    /**
     * 51. N 皇后   方法1：回溯算法
     *
     * 皇后们的约束条件：这一行这一列和这左右两边的对角线上都不能有皇后。
     * 1.不能同行
     * 2.不能同列
     * 3.不能同斜线
     *
     * @param n
     * @return
     */
    fun solveNQueens(n: Int): List<List<String>>? {
        val chess = Array(n) { CharArray(n) }
        for (i in 0 until n) {
            for (j in 0 until n) {
                chess[i][j] = '.'
            }
        }

        val res: MutableList<List<String>> = mutableListOf()
        // DFS
        solveNDfs(res, chess, 0)
        return res
    }

    /**
     * DFS
     */
    private fun solveNDfs(res: MutableList<List<String>>, chess: Array<CharArray>, row: Int) {
        // 终止条件，最后一行都走完了，说明找到了一种解法，把它加入到集合res中
        if (row == chess.size) {
            // 把每一行(数组)转为list
            res.add(turn(chess))
            return
        }

        // 遍历每一行的每一个位置->列
        for (col in chess.indices) {
            // 判断这个位置是否可以放皇后
            if (isValid(chess, row, col)) {
                // 尝试在这个位置放上皇后
                chess[row][col] = 'Q'

                // 递归到下一行继续
                solveNDfs(res, chess, row + 1)

                // 如果最终不能成功，那么返回时把这个位置还原，符合回溯思想
                chess[row][col] = '.'
            }
        }
    }

    /**
     * 把每一行(数组)转为list
     */
    private fun turn(chess: Array<CharArray>): List<String> {
        val list = mutableListOf<String>()
        for (i in chess.indices) {
            // chess[0] 一行 -> "..Q."
            list.add(String(chess[i]))
        }
        return list
    }

    /**
     * 判断这个位置是否可以放皇后：row表示第几行，col表示第几列
     */
    private fun isValid(chess: Array<CharArray>, row: Int, col: Int): Boolean {
        // 判断当前列有没有皇后，因为他是一行一行往下走的，只需要检查走过的行数即可，
        // 通俗一点就是判断当前 坐标位置的上面有没有皇后
        for (i in 0 until row) {
            if (chess[i][col] == 'Q') {
                return false
            }
        }

        // 判断当前坐标的右上角有没有皇后
        var i = row - 1
        var j = col + 1
        while (i >= 0 && j < chess.size) {
            if (chess[i--][j++] == 'Q') {
                return false
            }
        }

        // 判断当前坐标的左上角有没有皇后
        var i1 = row - 1
        var j1 = col - 1
        while (i1 >= 0 && j1 >= 0) {
            if (chess[i1--][j1--] == 'Q') {
                return false
            }
        }
        return true
    }
}
