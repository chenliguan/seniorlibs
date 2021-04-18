package com.seniorlibs.algorithm.recursive

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
                res.clear()
                LogUtils.e(TAG, "78. 子集：${subsets(intArrayOf(1, 2, 3))}")
            }
            R.id.btn_solve_n_queens -> {
                LogUtils.e(TAG, "51. N 皇后：${solveNQueens(4)}")
            }

            else -> {
            }
        }
    }

    /**
     * 22. 括号生成  方法一：递归+剪枝 ——> 由于字符串的特殊性，产生一次拼接都生成新的对象，因此无需回溯
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
        val res: MutableList<String> = mutableListOf()
        if (n == 0) return res

        // ((()))
        // 添加 ( , 左括号只要小于 n，left 随时可以加
        // 添加 ) ，右括号之前必须有左括号，左括号个数 > 右括号个数
        generate(0, 0,  n, "", res)

        return res
    }

    fun generate(l: Int, r: Int, n : Int, str : String, res : MutableList<String>) {
        // 1.递归终结条件（最先写） // 肯定不合法，提前结束，即“剪枝”
        if (l > n || r > l) return

        // 2.处理当前层逻辑
        if (l == n && r == l) {
            res.add(str)
            return
        }

        // 3.下探到下一层
        if (l < n) {
            generate(l + 1, r, n , str + "(", res)
        }

        if (r < l) {
            generate(l, r + 1, n , str + ")", res)
        }
    }


    /**
     * 17. 电话号码的字母组合
     *
     * 时间复杂度：O(3^m * 4^n)，其中 m 是输入中对应 3 个字母的数字个数（包括数字2、3、4、5、6、8），n 是输入中对应 4 个字母的数字个数（包括数字 7、9）
     * 空间复杂度：O(m+n)，m+n 是输入数字的总个数
     *
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

        findCombination(digits, 0, "")

        return array
    }

    private fun findCombination(digits: String, index: Int, s: String) {
        if (index == digits.length) {
            array.add(s)
            return
        }

        val c = digits[index]
        val letters = letterMap[c - '0']

        for (i in 0 until letters.length) {
            // a + d
            findCombination(digits, index + 1, s + letters[i])
        }
        return
    }


    /**
     * 78. 子集--方法一：回溯
     *
     * 回溯思想：找到一个子集，结束了递归，要撤销当前的选择（从list中删掉），回到选择前的状态，做另一个选择：不选当前的数，往下递归，继续生成子集。
     *          回退到上一步，把路走全，才能在包含解的空间树中，回溯出所有的解。
     *
     * 时间复杂度：O(n×2^n)。一共2^n个状态，每种状态需要O(n)的时间来构造子集；
     * 空间复杂度：O(n)。临时数组t的空间代价是O(n)，递归时栈空间的代价为O(n)。
     *
     * https://leetcode-cn.com/problems/subsets/solution/78-zi-ji-by-chen-li-guan-jh4l/
     * @param nums
     * @return
     */
    var res: MutableList<List<Int>> = ArrayList()

    /* 主函数，输入一组不重复的数字，返回它们的全排列 */
    fun subsets(nums: IntArray): List<List<Int>>? {
        val track: MutableList<Int> = ArrayList()
        dfs(0, nums, track)
        return res
    }

    // 已选路径记录在 track 中
    // 可选择列表：track[1,2] 中不存在于 nums[1,2,3] 的那些元素[3]。
    fun dfs(cur: Int, nums: IntArray, track: MutableList<Int>) {
        // 1 递归终结条件（最先写）：结束条件，nums[1,2,3] 中的元素全都在 track[1,2,3] 中出现
        if (cur == nums.size) {
            res.add(ArrayList(track))
            return
        }

        // 2.1 做选择：选择当前位置元素
        track.add(nums[cur])

        // 3.1 下探到下一层（类似左子树）
        dfs(cur + 1, nums, track)

        // 取消选择，不选择当前位置元素
        track.removeAt(track.size - 1)

        // 3.2 下探到下一层（类似右子树）
        dfs(cur + 1, nums, track)
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
