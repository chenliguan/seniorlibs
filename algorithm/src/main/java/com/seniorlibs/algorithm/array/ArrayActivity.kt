package com.seniorlibs.algorithm.array

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.seniorlibs.algorithm.R
import com.seniorlibs.baselib.utils.LogUtils
import java.util.*

/**
 * 数组
 */
class ArrayActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "ArrayActivity"

        fun actionStart(context: Context) {
            val intent = Intent(context, ArrayActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_array)

        initView()
        initData()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_remove_duplicates).setOnClickListener(this)
        findViewById<View>(R.id.btn_move_zeroes).setOnClickListener(this)
        findViewById<View>(R.id.btn_max_area).setOnClickListener(this)
        findViewById<View>(R.id.btn_reverse_string).setOnClickListener(this)
        findViewById<View>(R.id.btn_two_sum).setOnClickListener(this)
        findViewById<View>(R.id.btn_three_sum).setOnClickListener(this)
        findViewById<View>(R.id.btn_four_sum).setOnClickListener(this)
    }

    override fun onClick(v: View) = when (v.id) {
        R.id.btn_remove_duplicates -> {
            val nums: IntArray = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
            // 删除排序数组中的重复项
            removeDuplicates(nums)
            LogUtils.d(TAG, "26. 删除排序数组中的重复项：${nums.asList().toString()}")
        }
        R.id.btn_move_zeroes -> {
            val nums: IntArray = intArrayOf(0, 1, 0, 3, 12, 0, 1, 12, 0, 0, 6)
            // 移动零
            moveZeroes(nums)
            LogUtils.d(TAG, "283. 移动零：${nums.asList().toString()}")
        }
        R.id.btn_max_area -> {
            val nums: IntArray = intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)
            // 11. 盛最多水的容器
            LogUtils.d(TAG, "11. 盛最多水的容器：${maxArea(nums)}")
        }
        R.id.btn_reverse_string -> {
            val s: CharArray = charArrayOf('h', 'e', 'l', 'l', 'o')
            reverseString(s)
            LogUtils.d(TAG, "344. 反转字符串：${s}")
        }
        R.id.btn_two_sum -> {
            val sum = twoSum(intArrayOf(2, 11, 15, 7), 9).asList().toString()
            LogUtils.e(TAG, "1. 两数之和：${sum}")
        }
        R.id.btn_three_sum -> {
            val nums: IntArray = intArrayOf(-4, -1, -1, -1, 0, -1, 1, 2)
            LogUtils.d(TAG, "15. 三数之和：${threeSum(nums)}")
        }
        R.id.btn_four_sum -> {
            val nums: IntArray = intArrayOf(1, 0, -1, 0, -2, 2)
            LogUtils.d(TAG, "18. 四数之和：${fourSum(nums, 0)}")
        }
        else -> {
        }
    }

    private fun initData() {

    }

    /**
     *  26. 删除排序数组中的重复项 - 方法一：快慢指针
     *
     * 时间复杂度：O(n)，假设数组的长度是n，那么i和j分别最多遍历n步。
     * 空间复杂度：O(1)，因为要求在原数组中操作，没有创建新数组。
     *
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/solution/26-shan-chu-pai-xu-shu-zu-zhong-de-zhong-fu-xia-90/
     * @param nums
     * @return
     */
    fun removeDuplicates(nums: IntArray): Int {
        var i = 0  // 不重复指针-慢指针
        var j = 1  // 快指针

        while (j < nums.size) {
            if (nums[i] != nums[j]) { // 不重复项才需要操作
                i++ // 1.移动不重复指针指向下一个待填充位置： 避免：如果没有重复数据，q指向的元素原地复制了一遍
                if (i != j) { // 2.判断当前指针和不重复指针的差异，如果不等，说明不重复指针和快指针之间都是某个相等数字（否则，当前项是没移动过的，不能清空）
                    nums[i] = nums[j]  // 3.把当前的值赋予不重复指针指针
                }
            }

            j++ // 4.移动快指针指向下一个待填充位置
        }

        // 返回不重复指针+1位置
        return i + 1
    }

    /**
     * 283. 移动零 - 方法一：快慢指针
     *
     * 时间复杂度：O(n)。代码执行的总操作（数组写入）是非 0 元素的数量。
     * 空间复杂度：O(1)。只使用了常量空间，没有创建新数组。
     *
     * https://leetcode-cn.com/problems/move-zeroes/solution/283-yi-dong-ling-2-by-chen-li-guan/
     * @param nums
     */
    private fun moveZeroes(nums: IntArray): Unit {
        var i = 0  // 非零指针-慢指针
        var j = 0  // 快指针

        while (j < nums.size) {
            if (nums[j] != 0) {  // 非零项才需要操作
                if (i != j) {  // 1.判断当前指针和非零指针的差异，如果不等，说明非零指针和快指针之间都是零（否则，当前项是没移动过的，不能清空）
                    nums[i] = nums[j]  // 2.把 当前指针 赋值给 非零指针
                    nums[j] = 0   // 把 当前指针 赋值为 0
                }

                i++  // 核心：先交换再i++，删除排序数组中的重复项是 先i++再交换。因为它必须处理o位置，而重复项只处理1位置开始
            }

            j++ // 3.移动非零指针指向下一个待填充位置
        }
    }

    /**
     * 11. 盛最多水的容器
     *
     * @param height
     * @return
     */
    private fun maxArea(a: IntArray): Int {
        if (a.isEmpty()) return 0

        var left = 0
        var right = a.size - 1
        var max = 0

        while (left != right) {
            val width = right - left  // 1.计算宽
            val minHeight =
                if (a[left] > a[right]) a[right--] else a[left++] // 2.选择左右指针最小的高度，最大指针不动，移动小的指针向中间移动
            val area = width * minHeight // 3.计算区域面积
            if (area > max) { // 4.更新最大面积
                max = area
            }
        }

        return max
    }

    /**
     * 344. 反转字符串
     *
     * 时间复杂度：O(n)，其中n为字符数组的长度，一共执行了n/2次的交换；
     * 空间复杂度：O(1),只使用了常数空间来存放若干变量。
     *
     * @param s
     */
    fun reverseString(s: CharArray): Unit {
        if (s.isEmpty()) return

        var left = 0
        var right = s.size - 1
        var temp: Char

        while (left < right) {
            // 跳过相等的字符
            if (s[left] == s[right]) {
                left++
                right--
                continue
            }

            // 交换
            temp = s[left]
            s[left++] = s[right]
            s[right--] = temp
        }
    }

    /**
     * 1. 两数之和
     *
     * 时间复杂度：O(n)，把包含有n个元素的列表遍历两次，由于哈希表将查找时间缩短到O(1) ，所以时间复杂度为O(n)。
     * 空间复杂度：O(n)，所需的额外空间取决于哈希表中存储的元素数量，该表中存储了n个元素。
     *
     * @param nums
     * @param target
     * @return
     */
    private fun twoSum(nums: IntArray, target: Int): IntArray {
        val res = mutableListOf<Int>()
        val map: MutableMap<Int, Int> = mutableMapOf()

        for (i in nums.indices) {
            val key = target - nums[i]  // 1.采用目标值-当前值=另一个值，然后在表中查这个值
            if (map.containsKey(key)) {     // 2.回过头来检查表中是否已经存在当前元素所对应的目标元素，有就返回，没有走3
                res.add(map.getValue(key))
                res.add(i)
            } else {
                map[nums[i]] = i    // 3.将元素插入到表中：key是数值，value是下标
            }
        }

        return res.toIntArray()
    }

    /**
     * 15. 三数之和
     * 判断nums中是否存在三个元素a，b，c，使得a+b+c=0，转化为->a+b=-c
     *
     * 时间复杂度 O(n^2)：其中固定指针k循环复杂度O(n)，双指针i，j 复杂度O(n)。
     * 空间复杂度 O(1)：指针使用常数大小的额外空间。
     *
     * https://leetcode-cn.com/problems/3sum/solution/15san-shu-zhi-he-by-chen-li-guan/
     * @param a
     * @return
     */
    private fun threeSum(nums: IntArray): List<List<Int>> {
        val res = mutableListOf<MutableList<Int>>()
        /* 当数组元素小于4个时，直接返回 */
        if (nums.size < 3) return res

        /* 核心1：对数组进行从小到大排序 */
        Arrays.sort(nums)

        /* 核心2：第二层循环k，k从f+1开始遍历，最大值是nums.size-2（右边有i/j）；留下i和j */
        for (k in 0 until nums.size - 2) {
            /* 当k的值与前面的值相等时忽略 */
            if (k > 0 && nums[k] == nums[k - 1]) continue

            /* 定义指针i指向k+1，指针j指向数组末尾，交替向中间移动 */
            var i = k + 1
            var j = nums.size - 1

            /* 获取当前最小值，如果最小值比目标值大，说明整个循环越来越大的值根本没戏 */
            val min = nums[k] + nums[i] + nums[i + 1]
            if (min > 0) {
                break
            }
            /* 获取当前最大值，如果最大值比目标值小，说明当前这一轮循环后面越来越小的值根本没戏，但是不能break，下一轮f+还存在==target */
            val max = nums[k] + nums[nums.size - 1] + nums[nums.size - 2]
            if (max < 0) {
                continue
            }

            /* 核心3：开始i指针和j指针的表演，计算当前和。如果等于目标值，++i并去重，--j并去重；如果当前和大于目标值时--j；如果当前和小于目标值时++i */
            while (i < j) {
                val sum = nums[k] + nums[i] + nums[j]
                when {
                    sum < 0 -> {
                        /* 实力太弱，把菜鸟那边右移一位，并跳过所有相同的nums[i]（注意：++i必须在前，先计算nums[i+1]） */
                        while (i < j && nums[i] == nums[++i]) {
                        }
                    }
                    sum > 0 -> {
                        /* 实力太强，把大神那边左移一位，并跳过所有相同的nums[j] */
                        while (i < j && nums[j] == nums[--j]) {
                        }
                    }
                    sum == 0 -> {
                        /* 记录组合[k, i, j]到list */
                        res.add(mutableListOf(nums[k], nums[i], nums[j]))
                        /* 执行++i和--j并跳过所有相同复的nums[i]和nums[j] */
                        while (i < j && nums[i] == nums[++i]) {
                        }
                        while (i < j && nums[j] == nums[--j]) {
                        }
                    }
                }
            }
        }

        return res
    }

    /**
     * 18. 四数之和
     * 判断nums中是否存在四个元素a，b，c和d ，使得a + b + c + d的值与target相等
     *
     * 时间复杂度 O(n^3)：其中固定指针f循环复杂度O(n)，k循环复杂度O(n)，双指针i，j 复杂度O(n)。
     * 空间复杂度 O(1)：指针使用常数大小的额外空间。
     *
     * https://leetcode-cn.com/problems/4sum/solution/18-si-shu-zhi-he-by-chen-li-guan/
     * @param nums
     * @param target
     * @return
     */
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        val res = mutableListOf<MutableList<Int>>()
        /* 当数组元素小于4个时，直接返回 */
        if (nums.size < 4) return res

        /* 核心1：对数组进行从小到大排序 */
        Arrays.sort(nums)
        /* 核心2：定义4个指针f/k/i/j，f从0开始遍历，最大值是nums.size-3（右边有k/i/j） */
        for (f in 0 until nums.size - 3) {
            /* 当f的值与前面的值相等时忽略 */
            if (f > 0 && nums[f] == nums[f - 1]) continue

            /* 获取当前最小值，如果最小值比目标值大，说明整个循环越来越大的值根本没戏 */
            val min = nums[f] + nums[f + 1] + nums[f + 2] + nums[f + 3]
            if (min > target) {
                break
            }
            /* 获取当前最大值，如果最大值比目标值小，说明当前这一轮循环后面越来越小的值根本没戏；但是不能break，下一轮f+还存在==target */
            val max = nums[f] + nums[nums.size - 1] + nums[nums.size - 2] + nums[nums.size - 3]
            if (max < target) {
                continue
            }

            /* 核心3：第二层循环k，k从f+1开始遍历，最大值是nums.size-2（右边有i/j）；留下i和j */
            for (k in f + 1 until nums.size - 2) {
                /* 当k的值与前面的值相等时忽略 */
                if (k > f + 1 && nums[k] == nums[k - 1]) continue

                /* 定义指针i指向k+1，指针j指向数组末尾，交替向中间移动 */
                var i = k + 1
                var j = nums.size - 1

                /* 获取当前最小值，如果最小值比目标值大，说明整个循环越来越大的值根本没戏 */
                val min = nums[f] + nums[k] + nums[i] + nums[i + 1]
                if (min > target) {
                    break
                }
                /* 获取当前最大值，如果最大值比目标值小，说明当前这一轮循环后面越来越小的值根本没戏，但是不能break，下一轮f+还存在==target */
                val max = nums[f] + nums[k] + nums[nums.size - 1] + nums[nums.size - 2]
                if (max < target) {
                    continue
                }

                /* 核心4：开始i指针和j指针的表演，计算当前和。如果等于目标值，++i并去重，--j并去重；如果当前和大于目标值时--j；如果当前和小于目标值时++i */
                while (i < j) {
                    val sum = nums[f] + nums[k] + nums[i] + nums[j]
                    when {
                        sum < target -> {
                            /* 实力太弱，把菜鸟那边右移一位，并跳过所有相同的nums[i]（注意：++i必须在前，先计算nums[i+1]） */
                            while (i < j && nums[i] == nums[++i]) {
                            }
                        }
                        sum > target -> {
                            /* 实力太强，把大神那边左移一位，并跳过所有相同的nums[j] */
                            while (i < j && nums[j] == nums[--j]) {
                            }
                        }
                        sum == target -> {
                            /* 记录组合[f, k, i, j]到list */
                            res.add(mutableListOf(nums[f], nums[k], nums[i], nums[j]))
                            /* 执行++i和--j并跳过所有相同复的nums[i]和nums[j] */
                            while (i < j && nums[i] == nums[++i]) {
                            }
                            while (i < j && nums[j] == nums[--j]) {
                            }
                        }
                    }
                }
            }
        }

        return res
    }
}
