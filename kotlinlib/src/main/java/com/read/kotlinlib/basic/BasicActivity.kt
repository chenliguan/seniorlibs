package com.read.kotlinlib.basic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.read.kotlinlib.R
import com.read.kotlinlib.generic.GenericTestKt
import com.seniorlibs.baselib.utils.LogUtils

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/4/1
 * Mender:
 * Modify:
 * Description: 测试kotlin
 */
class BasicActivity : AppCompatActivity() {

    companion object {
        const val TAG = "kotlin + BasicActivity : "

        fun companionFun(): String {
            return "companionFun"
        }

        @JvmStatic
        fun jvm() {

        }

        fun actionStart(context: Context?) {
            if (context == null) {
                return
            }
            val intent = Intent(context, BasicActivity::class.java)
            context.startActivity(intent)
        }
    }

    // 单例
    class Single private constructor() {
        companion object {
            fun get(): Single {
                return Holder.instance
            }
        }

        private object Holder {
            val instance = Single()
        }
    }

    object DemoManager {
        fun a() {
            LogUtils.d(TAG, "此时 object 表示 声明静态内部类")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)

        testBasic()
    }

    /**
     * 泛型测试
     *
     * @param view
     */
    fun textGeneric(view: View?) {
        val genericTest = GenericTestKt()
        genericTest.main()
    }

    fun testBasic() {
        foo()
        array()
        list()
        map()
        traverse()
        sequence()
        actionFun()
        operator()
        testNull("")
        inline()
        delegates()
    }

    /**
     * 承载者
     *
     * @param <T>
    </T> */
    internal inner class Plate<T>(private var item: T) {
        fun set(t: T) {
            item = t
        }

        fun get(): T {
            return item
        }

    }

    internal class Plate2() {
        private var item: String? = "Holmes, Sherlock"
        fun set(t: String) {
            item = t
        }

        fun get(): String? {
            return item
        }

    }

    interface MyInterface {
        val prop: Int // 抽象的

        val isEmpty: Boolean
            get() = prop == 0

        val propertyWithImplementation: String
            get() = "foo"
    }

    open class Parent public constructor(var name: String) {
        constructor(name: String, age: String) : this(name) {
            println("$TAG constructor ${name}  ${age}")
        }

        init {
            println("$TAG First initializer block that prints ${name}")
        }

        val secondProperty = "$TAG Second property: ${name.length}".also(::println)

        init {
            println("$TAG Second initializer block that prints ${name.length}")
        }
    }

    // 数据类
    data class User(val name: String, val pwd: String)

    open class Child : MyInterface, Parent("类的参数", "类的年龄") {
        override val prop: Int = 29

        val inferredType = 1 // 类型 Int 、默认 getter

        val name1 = propertyWithImplementation
        val age = propertyWithImplementation
    }

    fun double(x: Int): Int = x * 2

    fun double1(x: Int = 10) = x * 2

    fun double2(x: Int): Int {
        fun double3(y: Int): Int {
            return x + y
        }
        println(TAG + "double3 : ${double3(1000)}")
        return x * 2
    }

    object Test {
        fun sayMes(mag: String) {
            println(TAG + mag)
        }
    }

    /**
     * 密闭类
     * 密闭类其构造函数默认为private，它是不能被实例化的，val mOp = Operation()  // 这段代码是错误的，编译器直接会报错不能编译通过。
     * 好处：使用when表达式的时候，如果能够验证语句覆盖了所有情况，就不需要为该语句再添加一个else子句了。当然，这只有当你用when作为表达式（使用结果）而不是作为语句时才有用。
     */
    sealed class Operation {
        class Add(val value: Int) : Operation()
        class Substract(val value: Int) : Operation()
        class Multiply(val value: Int) : Operation()
        class Divide(val value: Int) : Operation()
    }

    fun execute(x: Int, op: Operation) = when (op) {
        is Operation.Add -> x + op.value
        is Operation.Substract -> x - op.value
        is Operation.Multiply -> x * op.value
        is Operation.Divide -> x / op.value
    }

    var stringRepresentation: String = ""
        get() = field
        set(value) {
            field = value + value
        }

    fun foo() {
        val prop: Int = 29

        println(TAG + prop)

        var wh: String = "abc" // 默认情况下，常规初始化意味着非空
        val hi: String? = null

        println(TAG + hi?.length)
        println(TAG + wh.length)
//            println(TAG + hi!!.length)  // kotlin.KotlinNullPointerException

        val l = hi?.length ?: -1
        println(TAG + l)

        println(TAG + "double1 : ${double1()}")
        println(TAG + "double2 : ${double2(1)}")

        stringRepresentation = "teku "
        println(TAG + stringRepresentation)

//            wh = hi!!  // kotlin.KotlinNullPointerException

        Test.sayMes("匿名内部类")

        println(TAG + String::class.java)
        println(TAG + String::class)

        val b: Byte = 1
        val i: Int = b.toInt() // OK：显式拓宽
        println(TAG + i)   // 1

        val la = 1L + 3
        println(TAG + la + " " + la.javaClass)  // 4 long

        DemoManager.a()

        extension()

        println("$TAG 伴生对象： ${companionFun()}")

        val mUser = User("kotlin", "123456")
        println("$TAG 数据类型1： ${mUser}  ${mUser.toString()}")
        val mNewUser = mUser.copy(name = "new Kotlin")
        println("$TAG 数据类型2： ${mNewUser}")

        println("$TAG 密闭类： ${execute(10, Operation.Add(100))}")
    }

    fun extension() {
        var child = Child()
        fun Child.pint(index1: Int, index2: Int) {
            println("$TAG 拓展1： ${index1} ${index2}")
        }
        child.pint(100, 8709)

        open class Shape
        class Rectangle : Shape()

        fun Shape.getName() = "Shape"
        fun Rectangle.getName() = "Rectangle"
        fun printClassName(s: Shape) {
            println("$TAG 扩展2：${s.getName()}")
        }

        fun printClassName(s: Rectangle) {
            println("$TAG 扩展3：${s.getName()}")
        }
        printClassName(Rectangle())
        printClassName(Shape())

        val lambda: (Int) -> Int
        lambda = ({ x: Int -> x + 1 })
        val lambdaI1 = { x: Int -> x + 1 }
        val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
        val sum1 = { x: Int, y: Int -> x + y }
        println("$TAG lambdai ：${lambda(10)}")
        println("$TAG lambdaI1 ：${lambdaI1(10)}")
        println("$TAG sum ：${sum(10, 10)}")
        println("$TAG sum1 ：${sum1(10, 10)}")

        val runnable = Runnable {
            println("Runnable::run")
        }
        val function: () -> Unit
        function = runnable::run  // :: 表示把一个方法当做一个参数，传递到另一个方法中进行使用
        onlyIf(true, function)
        onlyIf(true) {
            println("$TAG 打印日杂")
        }

        val plate = Plate2()
        plate.set(":: 表示把一个方法当做一个参数，传递到另一个方法中进行使用")
        println("$TAG ::1 ${"abc"::length}")
        println("$TAG ::2 ${plate::get}")
        println(plate::get)

        class A(val p: Int) {
            var name: String? = "表示把一个方法当做一个参数"
            var age: String? = "1123"
        }

        val prop = A::p
        val nameA = A::name
        println("$TAG ::nameA ${nameA}")
        println(prop.get(A(1)))

        val strList = listOf("a", "bc", "def")
        println("$TAG ::2 ${strList.map(String::length)}")

        fun isOdd(x: Int) = x % 2 != 0
        val numbers = listOf(1, 2, 3)
        println("$TAG ::3 ${numbers.filter(::isOdd)}")

        /**
         * 高阶函数
         */
        val items = listOf(1, 2, 3, 4, 5)
        // Lambdas 表达式是花括号括起来的代码块。
        // 如果一个 lambda 表达式有参数，前面是参数，后跟“->”
        items.fold(0, { acc: Int, i: Int ->
            print("acc = $acc, i = $i, ")
            val result = acc + i
            println("$TAG result = $result")
            // lambda 表达式中的最后一个表达式是返回值：
            result
        })
        // lambda 表达式的参数类型是可选的，如果能够推断出来的话：
        val joinedToString = items.fold("Elements:", { acc, i -> acc + " " + i })
        // 函数引用也可以用于高阶函数调用：
        val product = items.fold(1, Int::times)

        println("$TAG joinedToString = $joinedToString")
        println("$TAG product = $product")

        val x = "自动转换为字符串"
        if (x is String) {
            println("$TAG length1 = ${x.length}") // x 自动转换为字符串
        }
        when (x) {
            is String -> println("$TAG length2 = ${x.length}")
        }
        println("$TAG ${("123" as? Int)}")
    }

    private fun onlyIf(isDebug: Boolean, block: () -> Unit) {
//        private fun onlyIf(isDebug: Boolean, block : Function0<Unit>) {
        if (isDebug) {
            block()
        }
    }

    private fun array() {
        // arrayOfNulls<数据类型>(长度)，默认值都是null == java中的 int[] intArray = new int[6]
        val arrayOfNulls = arrayOfNulls<Int>(26)
        // 推荐
        val intArray = IntArray(26)

        // 注意以下不是固定长度，而是元素
        val intArray1 = intArrayOf(26)

        // 二维的最佳写法
        val chess = Array(26) { CharArray(26) }
    }

    fun list() {
        // MutableList
        val numbers = mutableListOf("one", "two", "three", "four")  // 可变
        numbers.add("five")   // 这是可以的
        println("$TAG ${numbers}")
        //     numbers = mutableListOf("six", "seven")      // 编译错误
        //     println("${TAG} ${numbers}")

        // List
        var list = listOf("one", "two", "three", "four")  // 只读
        //     list.add("five")   // 编译错误
        println("$TAG ${list}")


        // Collection
        val stringList = listOf("one", "two", "one")
        printAll(stringList)
        val stringSet = setOf("one", "two", "three")
        printAll(stringSet)

        // list 元素（包括空值）可以重复：List 可以包含任意数量的相同对象或单个对象的出现。
        // 如果两个 List 在相同的位置具有相同大小和相同结构的元素，则认为它们是相等的
        val bob = Person("Bob", 31)
        val people = listOf(Person("Adam", 20), bob, bob)
        val people2 = listOf(Person("Adam", 20), Person("Bob", 31), bob)
        println("$TAG : ${people == people2}")
        bob.age = 32
        println("$TAG : ${people == people2}")

        // 如果你想操作这个集合，应使用 MutableList
        val doubled = List(3, { it * 2 })
        println("$TAG ${doubled}")
        val linkedList = ArrayList<String>(listOf("one", "two", "three"))
        println("$TAG ${linkedList}")

        // 复制
        val copyList = list.toMutableList()
        val readOnlyCopyList = list.toList()
        copyList.add("新加")
        println("$TAG Copy : ${copyList}")
        // readOnlyCopyList.add(4)             // 编译异常
        println("$TAG Read-only copy: ${readOnlyCopyList}")

        // 调用其他集合的函数：映射
        println("$TAG longerThan3：${numbers.filter { it.length > 3 }}")  // longerThan3：[three, four, five]
        println("$TAG map：${numbers.map { it }}")  // map：[one, two, three, four, five]
        println("$TAG mapIndexed： ${numbers.mapIndexed { idx, value -> value + idx }}")  // mapIndexed： [one0, two1, three2, four3, five4]

        // 双路合并
        val colors = listOf("red", "brown", "grey")
        val animals = listOf("fox", "bear", "wolf")
        println("$TAG colors zip animals：${colors zip animals}") // colors zip animals：[(red, fox), (brown, bear), (grey, wolf)]
        val twoAnimals = listOf("fox", "bear")
        println("$TAG colors.zip(twoAnimals)：${colors.zip(twoAnimals)}")  // colors.zip(twoAnimals)：[(red, fox), (brown, bear)]

        println(colors.zip(animals) { color, animal ->
            "$TAG The ${animal.capitalize()} is $color"
        }  // [The Fox is red, The Bear is brown, The Wolf is grey]
        )

        // 要分割键值对列表
        val numberPairs = listOf("one" to 1, "two" to 2, "three" to 3, "four" to 4)
        println("$TAG numberPairs.unzip(): ${numberPairs.unzip()}")  // ([one, two, three, four], [1, 2, 3, 4])

        // 关联：转换允许从集合元素和与其关联的某些值构建Map
        println("$TAG associateWith： ${numbers.associateWith { it.length }}}")  // {one=3, two=3, three=5, four=4, five=4}}
        println("$TAG " + numbers.associateBy { it.first().toUpperCase() })  // {O=one, T=three, F=five}
        println("$TAG " + numbers.associateBy(keySelector = { it.first().toUpperCase() }, valueTransform = { it.length })) // {O=3, T=5, F=4}

        // 打平
        val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
        println("$TAG " + numberSets.flatten())  // [1, 2, 3, 4, 5, 6, 1, 2]

        data class StringContainer(val values: List<String>)

        val containers = listOf(
                StringContainer(listOf("one", "two", "three")),
                StringContainer(listOf("four", "five", "six")),
                StringContainer(listOf("seven", "eight"))
        )
        println("$TAG " + containers.flatMap { it.values })  // [one, two, three, four, five, six, seven, eight]

        // joinToString() 根据提供的参数从集合元素构建单个 String。 joinTo() 执行相同的操作，但将结果附加到给定的 Appendable 对象。
        println("$TAG " + numbers.joinToString())  // one, two, three, four, five
        val listString = StringBuffer("The list of numbers: ")
        numbers.joinTo(listString)
        println("$TAG " + listString)  // The list of numbers: one, two, three, four, fiv

        // 排序
        val sortedNumbers = numbers.sorted()
        println("$TAG 排序 + ${sortedNumbers}")  // 排序 + [five, four, one, three, two]
    }

    data class Person(var name: String, var age: Int)

    fun printAll(strings: Collection<String>) {
        for (s in strings) print("$s ")
        println()
    }

    /**
     * map
     */
    fun map() {
        val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1, "key5" to "value5")
        // 解构声明
        for ((key, value) in numbersMap) {
            println("$TAG map的key value: ${key}  ${value}")  // map的key value: key1  1 ......
        }

        val mapApply = mutableMapOf<String, String>().apply { this["one"] = "1"; this["two"] = "2" }
        for ((key, value) in mapApply) {
            // 使用该 key、value 做些事情
            println("$TAG mapApply的key value: ${key}  ${value}")  // mapApply的key value: one  1 ......
        }
    }

    /**
     * 遍历
     */
    fun traverse() {
        for (i in 1..3) {
            println("$TAG i in 1..3：${i}")
        }
        for (i in 1 until 3) {
            println("$TAG i in 1 until 3：${i}")
        }
        for (i in 3 downTo 1) {
            println("$TAG i in 1 downTo 3：${i}")
        }
        for (i in 1..6 step 2) {
            println("$TAG i in 1..6 step 2：${i}")
        }

        val numbers = listOf("one", "two", "three", "four")
        val numbersIterator = numbers.iterator()
        while (numbersIterator.hasNext()) {
            println(numbersIterator.next())
            println("$TAG numbersIterator.next: 循环：${numbersIterator.next()}")
        }

        for (item in numbers) {
            println("$TAG for 循环：${item}")
        }

        for ((index, item) in numbers.withIndex()) {
            println("$TAG for withIndex：${index} ${item}")
        }

        numbers.forEach {
            println("$TAG forEach ：${it}")
        }

        repeat(3) {
            println("$TAG repeat ：${it}")
        }
    }

    /**
     * 序列
     */
    fun sequence() {
        val numbersSequence = sequenceOf("four", "three", "two", "one")
        println("$TAG numbersSequence ：${numbersSequence.toList()}")

        val numbers = listOf("one", "two", "three", "four", "file", "six", "nine")
        val asSequence = numbers.asSequence()
        println("$TAG asSequence ：${asSequence.toList()}")  // asSequence ：[one, two, three, four, file, six, nine]

        val lengthsSequence = numbers
                .filter {
                    println("$TAG filter: $it")  // filter: one two three four file six nine
                    it.length > 3
                }
                .map {
                    println("$TAG $it + length: ${it.length}") // three + length: 5  four + length: 4  file + length: 4  nine + length: 4
                    // 将it（String） 转成 it.length（Int）
                    it.length
                }
                .take(4)

        var lengthList = lengthsSequence.toList();
        println("$TAG lengthList：${lengthList}") // lengthList：[5, 4, 4, 4]

        // 求和 reduce { acc, i -> acc + i}：acc是累加的返回值，i是当前遍历列表中的值
        val num = lengthList.reduce { acc, i ->
            println("$TAG acc：${acc}  i：${i}") // acc：5  i：4  acc：9  i：4  acc：13  i：4
            acc + i
        }
        println("$TAG num：${num}")  // num：17
    }

    /**
     * 作用域函数：在一个对象上执行一个代码块
     * apply 及 also 返回上下文对象；let、run 及 with 返回 lambda 表达式结果.
     */
    fun actionFun() {
        // let 及 also ：作为辅助步骤包含在调用链中：你可以继续在同一个对象上进行链式函数调用
        // 使用 it 作为上下文参数
        MyPerson("Alice", 20, "Amsterdam").let {
            println("$TAG ${it}")  // MyPerson(name=Alice, age=20, city=Amsterdam)
            it.moveTo("London")
            it.incrementAge()
            println("$TAG let的it：${it}")  // let的it：MyPerson(name=Alice, age=21, city=London)
        }

        // 等同于：必须引入一个新变量，并在每次使用它时重复其名称
        val alice = MyPerson("Alice", 20, "Amsterdam")
        println(alice)
        alice.moveTo("London")
        alice.incrementAge()
        println("$TAG let的alice：${alice}")  // let的alice：MyPerson(name=Alice, age=21, city=London)

        // run、with 以及 apply 通过关键字 this 引用上下文对象：需要使用其结果给一个变量赋值，或者在需要对其结果进行链式操作等情况下
        val adam = MyPerson("Adam").apply {
            this.age = 20                       // 和 this.age = 20 或者 adam.age = 20 一样
            city = "London"
        }
        println("$TAG apply：${adam}")

        // let：在调用链的结果上调用一个或多个函数。上下文对象作为 lambda 表达式的参数（it）来访问。返回值是 lambda 表达式的结果。
        val numbers = mutableListOf("one", "two", "three", "four", "five")
        val resultList = numbers.map { it.length }.filter { it > 3 }
        println("$TAG resultList：${resultList}") // resultList：[5, 4, 4]
        // 使用 let，可以写成这样：
        numbers.map { it.length }.filter { it > 3 }?.let {
            println("$TAG it：${it}")  // it：[5, 4, 4]
        }
        // 若代码块仅包含以 it 作为参数的单个函数，则可以使用方法引用(::)代替 lambda 表达式：
//            numbers.map { it.length }.filter { it > 3 }?.let(::println)


        // let 将表达式作为变量引入为局部作用域中
        // 上下文对象是（it）来访问
        MyPerson("Alice", 20, "Amsterdam").let {
            println("$TAG ${it}")  // MyPerson(name=Alice, age=20, city=Amsterdam)
            it.moveTo("London")
            it.incrementAge()
            println("$TAG let的it：${it}")  // let的it：MyPerson(name=Alice, age=21, city=London)
        }

        // run 对象配置并且计算结果，在需要表达式的地方运行语句
        // run 上下文对象（this）来访问
        MyPerson("Alice", 20, "Amsterdam").run {
            println("$TAG ${this}")   // MyPerson(name=Alice, age=20, city=Amsterdam)
            this.moveTo("London")
            this.incrementAge()
            println("$TAG run的this：${this}")  // run的this：MyPerson(name=Alice, age=21, city=London)
        }

        // apply 将以下对象赋值操作配置对象
        // 上下文对象是（this）来访问。 返回值是上下文对象本身
        val apply = MyPerson("Adam").apply {
            this.age = 20                       // 和 this.age = 20 或者 adam.age = 20 一样
            city = "London"
        }
        println("$TAG apply：${apply}")  // apply：MyPerson(name=Adam, age=20, city=London)

        // also 附加效果，并且用该对象执行以下操作
        // 上下文对象是（it）来访问。 返回值是上下文对象本身。
        numbers.also {
            println("$TAG The list elements before adding new one: $it")
        }.add("four") // The list elements before adding new one: [one, two, three, four, five]


        // 特殊：with 对于这个对象，执行以下操作
        // 上下文对象（this）来访问，而不使用 lambda 表达式结果。
        with(numbers) {
            println("$TAG 'with' is called with argument $this")  // 'with' is called with argument [one, two, three, four, five]
            println("$TAG It contains $size elements") // It contains 5 elements
        }
    }

    data class MyPerson(var name: String = "", var age: Int = 0, var city: String = "") {
        fun moveTo(newCity: String) {
            city = newCity
        }

        fun incrementAge() {
            age++
        }
    }

    /**
     * 操作符
     */
    fun operator() {
        val numbers = listOf("one", "two", "three", "four")
        val plusList = numbers + "five"
        println("$TAG plusList：${plusList}")  // plusList：[one, two, three, four, five]
        val minusList = numbers - listOf("three", "four")
        println("$TAG minusList：${minusList}") // minusList：[one, two]

        val a = "Str"
        val b = String("Str".toByteArray())
        // == 判断值是否相等
        println("$TAG a == b：${a == b}")  // a == b：true
        // === 判断对象是否相等
        println("$TAG a === b：${a === b}") // a === b：false

        // 类型别名为现有类型提供替代名称。 如果类型名称太长，你可以另外引入较短的名称，并使用新的名称替代原类型名。

        println("hello world")
    }

    /**
     * 空
     */
    fun testNull(args: String) {
        var a: String? = null

        println(getValue(a))
    }

    fun getValue(s: String?): String {
        return "1" + s?.length
    }

    /**
     * 内联函数
     */
    fun inline() {
        ordinary {
            println("$TAG before inlineName.greet()")

            val inlineName = InlineName("Kotlin")
            inlineName.greet() // `greet` 方法会作为一个静态方法被调用

            println("$TAG after inlineName.greet()")

            return // 错误：不能使 `foo` 在此处返回
        }
    }

    inline fun ordinary(block: (a: String) -> Unit) {
        println("$TAG ordinaryFunction")
    }


    /**
     * 代理/委托模式
     */
    fun delegates() {
        val base = BaseImpl()
        BaseProxy(base).show()  // BaseImpl.show()
        BaseProxy(base).showOther()  // BaseProxy.showOther()
    }

    // 定义一个接口,和一个方法 show()
    interface Base {
        fun show()
    }

    // 定义类实现 Base 接口, 并实现 show 方法
    open class BaseImpl : Base {
        override fun show() {
            println("$TAG BaseImpl.show()")
        }
    }

    // 定义代理类实现 Base 接口, 构造函数参数是一个 Base 对象
    // by 后跟 Base 对象, 不需要再实现 show()
    class BaseProxy(baseS: Base) : Base by baseS {
        fun showOther() {
            println("$TAG BaseProxy.showOther()")
        }
    }

    fun set(x: Int): Int {
        var child = Child()

        return 0
    }

    /**
     * 延迟初始化
     */
    class TestCase {
        lateinit var age: String

        private val case: Int by lazy { 1 }
        private val case1: Int by lazy(LazyThreadSafetyMode.PUBLICATION) { 1 }

        fun printCase() {
            println(case)
        }
    }
}










