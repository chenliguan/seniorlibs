package com.seniorlibs.study.variable

/*************************************** 变量+类型 ******************************************/
int x = 10
println "x.class" + x.class // x.classclass java.lang.Integer

double y = 3.14
println "y.class" + y.class // y.classclass java.lang.Double

def x_1 = 11
println x_1.class // class java.lang.Integer

def y_1 = 3.1415
println y_1.class // class java.math.BigDecimal

/***************************************** 方法 ********************************************/
// 无类型的函数定义，必须使用def关键字，方法如果不指定返回值，默认返回最后一行代码的值。
// java 如果指定了函数返回类型，则可不必加def关键字来定义函数
int getNum(def num) {
    num * num
}
def getNum1(def num) {
    num * num
}
def getNum2(num) {
    num * num
}

// Groovy中函数调用的时可以不加括号：println() --> println，println("text") --> println "text"
println getNum(2)  // 4
println getNum1(2) // 4
println getNum2(2) // 4
getNum2 2   // Result: 4


/*********************************** 字符串基础用法 **************************************/
def str1 = 'Qndroid'      // 单引号
println "str1：${str1} +  str1.class： ${str1.class} " // str1：Qndroid +  str1.class： class java.lang.String

def str2 = "Qndroid"      // 双引号
println "str2：${str2} +  str2.class： ${str2.class} " // str2：Qndroid +  str2.class： class java.lang.String

def str3 = '''
line one
line two
line three
 '''
println "str3：${str3}"   // 三引号
println "str3.class： ${str3.class} "
/**
 * str3：
 * line one
 * line two
 * line three
 * str3.class： class java.lang.String
 */

def str4 = "Qndroid"
def str5 = "str5：${str4} +  str5.class： ${str4.class} "
println str5        // str5：Qndroid +  str5.class： class java.lang.String
println str5.class  // class org.codehaus.groovy.runtime.GStringImpl

// 可扩展做任意的表达式
def str6 = "str6：2 + 3 = ${2 + 3}"
println str6   // str6：2 + 3 = 5

// 返回值是方法最后一条语句的值（默认返回null）
String echo(String msg) {
    return msg    // 等于： msg
}

def result = echo(str6)
println "result：" + result + "， result.class：" + result.class // result：str6：2 + 3 = 5， result.class：class java.lang.String


/************************************* 字符串的方法 **************************************/
def str7 = "groovy"
println str7.center(8, 'kk')    // 两边填充：kgroovyk
println str7.padLeft(8, 'aa')   // 左边填充：aagroovy
def str8 = "hello"
println str7 > str8   // false
println str8[0]       // h
println str8[0..1]    // he
println str8.minus("he")    // 减法：hello - he = llo
println str8.reverse()    // 倒序：olleh
println str8.isNumber()   // false

/************************************* 判断逻辑 ****************************************/
def k = 90
def result1
switch (k) {
    case 'bar':
        ret = 'find bar'
        break
    case [1.23, 4, 5, 6, 'inlist']:
        result1 = '列表'   // 1.23 --> 列表，'inlist' --> 列表
        break
    case 12..30:
        result1 = '范围'    // 20 --> 范围
        break
    case Integer:
        result1 = 'Integer' // 90 --> Integer
        break
    default:
        result1 = ''
        break
}
println "判断逻辑：" + result1

/***************************************** 遍历 ********************************************/
// Java中的遍历
void printHello() {
    for (int i = 0; i < 3; i++) {
        println("Java中的遍历");
    }
}
printHello()

// Groovy中的遍历1
for (i in 0..2) {
    println("Groovy中的遍历1")
}
// Groovy中的遍历2
3.times {
    println("Groovy中的遍历2")
}
// Groovy中的遍历3
0.upto(2) {
    println("Groovy中的遍历3")
}
// Groovy中的遍历4
List helloList = ["遍历4", "遍历4", "遍历4"]
helloList.each { element ->
    println("the element is ${element}")
}


// 对范围的for循环
def result2 = 0
for (i in 0..9) {
    result2 += i
}
println "对范围的for循环：" + result2   // 对范围的for循环：45

// 对list的循环
def result3 = 0
for (i in [1, 2, 3, 4, 5, 6, 7, 8, 9]) {
    result3 += i
}
println "对list的循环：" + result3   // 对list的循环：45

// 对map的循环
def result4 = 0
for (i in ['aa': 1, "bb": 2, "cc": 3]) {
    result4 += i.value
}
println "对map的循环：" + result3   // 对map的循环：45

/************************************* 闭包（最重要） **************************************/
// 闭包定义：闭包实质上是一个groovy.lang.Closure类对象
// 闭包的参数声明写在‘->’符号前，调用闭包的的标准写法是：闭包名.call(闭包参数)（为了和方法区分，建议），也可以用：闭包名(闭包参数)
def clouser = {
    println "Hello groovy"
}
clouser()       // Hello groovy
clouser.call()  // Hello groovy

// 闭包传参
def clouser1 = { String name -> // 等于：name -> 或 def name ->
    println "Hello ${name}"
}
clouser1.call('groovy1')  // Hello groovy1

// 闭包传多个参
def clouser2 = { name, age ->
    println "Hello ${name}, age ${age}"
}
clouser2.call('groovy2', 12)  // Hello groovy2, age 12

// 闭包对于单一存在的参数it默认隐藏，直接使用it
def clouser3 = {    // 等于： it ->
    println "Hello ${it}"
}
clouser3.call('groovy3')  // Hello groovy3

// 闭包总会返回一个值，返回值是闭包的最后一条语句的值（默认返回null）
def clouser4 = { name ->
//    println "Hello ${name}"
    // 等于：return "Hello ${name}"
    "Hello ${name}"
}
def result5 = clouser4.call('groovy4') // 闭包返回值：null ； Hello groovy4
println "闭包返回值：" + result5

// 当闭包有多个参数
def clouser5 = { name, age ->
    "Hello $name, $age"
}
println clouser5.call('groovy5', 20)   // Hello groovy5, 20

// 如果闭包是唯一的一个参数，则闭包或方法参数所在的圆括号也可以省略
def clouser7 = { age ->
    age.call() + age.call()
}
println clouser7.call({ 'groovy7' })   // groovy7groovy7
println clouser7.call() { 'groovy7' }   // groovy7groovy7
println clouser7.call {  // groovy7groovy7
    'groovy7'
}
println clouser7 { 'groovy7' }  // groovy7groovy7 , 等于：repositories { mavenCentral() }

println clouser7 {  // getEnv yes groovy6 name age str  getEnv yes groovy6 name age str
    // 等于：name = "name"
    def name = "name"
    age = "age"
    str = "str"
    // $必须在""里面
    'groovy6 ${name}'
    getEnv("yes") + " groovy6 ${name} ${age} ${str}  "

}

// 方法
def getEnv(def params) {
    def value = "getEnv ${params}"
    return value
}

println getEnv("yes")    // getEnv yes


// 当闭包作为闭包或方法的最后一个参数，可以将闭包从参数圆括号中提取出来接在最后
def clouser8 = { name, age ->
    age.call(name)
}
println clouser8.call('groovy8', { name -> name * 2 })  // groovy8groovy8
println clouser8.call('groovy8') { name -> name * 2 }   // groovy8groovy8

// 计算number的阶乘 1-->number
def calculate1(int number) {
    def result = 1
    1.upto(number, { num ->
        result *= num
    })
    return result
}

println "calculate1：" + calculate1(5)  // 120

// 计算number的阶乘 number-->1
def calculate2(int number) {
    def result = 1
    number.downto(1, { num ->
        result *= num
    })
//    number.downto(1) { num ->
//        result *= num
//    }
    return result
}

println "calculate2：" + calculate2(5)  // 120

// 累计求和（n - 1）
def calculate3(int number) {
    def result = 0
    number.times { num ->
        result += num
    }
    return result
}

println "calculate2：" + calculate3(5)  // 0 + 1 + 2 + 3 + 4 = 10

// 字符串与闭包结合使用
String str = "the 23"
// each的遍历
str.each { temp ->
    println temp   // t h e  2 3
}
println str.each {   // the 23

}
// find来查找符合条件的第一个
println str.find { temp ->   // 2
    temp.isNumber()
}
// findAll来查找符合条件的所有值，放在集合里
println str.findAll { temp ->  // [2, 3]
    temp.isNumber()
}
// any来查找只要符合就返回true
println str.any { temp ->  // true
    temp.isNumber()
}
// every来查找每一项符合才返回true
println str.every() { temp ->  // false
    temp.isNumber()
}
// collect遍历并放在集合里
println str.collect() { temp ->  // [T, H, E,  , 2, 3]
    temp.toUpperCase()
}

/************************************* 闭包进阶 **************************************/

// 闭包的三个重要变量：this,owner,delegate
class Delegate {
}

class Person {
    def classClouser = {
        // 代表闭包定义处的类
        println "classClouser this：" + this
        // 代表闭包定义处的类或对象
        println "classClouser owner：" + owner
        // 代表任意对象
        println "classClouser delegate：" + delegate
    }

    def static say() {
        def scriptClouser = {
            // 代表闭包定义处的类
            println "method classClouser this：" + this
            // 代表闭包定义处的类或对象
            println "method classClouser owner：" + owner
            // 代表任意对象
            println "method classClouser delegate：" + delegate
        }
        scriptClouser.call()
    }

    def nestClouser = {
        def innerClouser = {
            // 代表闭包定义外的类
            println "innerClouser this：" + this
            // 代表闭包定义处的类或对象
            println "innerClouser owner：" + owner
            // 代表任意修改的对象
            println "innerClouser delegate：" + delegate
        }
        Delegate d = new Delegate()
        innerClouser.delegate = d
        innerClouser.call()
    }
}

def p = new Person()
p.classClouser.call()
//classClouser this：Person@73a1e9a9
//classClouser owner：Person@73a1e9a9
//classClouser delegate：Person@73a1e9a9
Person.say()
//method classClouser this：class Person
//method classClouser owner：class Person
//method classClouser delegate：class Person
p.nestClouser.call()
//innerClouser this：Person@73a1e9a9
//innerClouser owner：Person$_closure2@65fb9ffc
//innerClouser delegate：Delegate@3e694b3f

// 闭包的委托策略
class Student {
    String name
    def pretty = { "My name is ${name}" }

//    Student(String name) {
//        this.name = name
//    }
    String toString() {
        pretty.call()
    }
}

class Teacher {
    String name
}

// 定义了构造函数，直接赋值
// def student = new Student("委托")
// 没定义构造函数，必须指定属性:赋值
def student = new Student(name: "委托1")
def teacher = new Teacher(name: "委托2")
println student.toString()   // My name is 委托1

// 通过闭包的委托策略修改对象
student.pretty.delegate = teacher
student.pretty.resolveStrategy = Closure.DELEGATE_FIRST
println student.toString()   // My name is 委托2


/***************************************** 列表和数组 ********************************************/
// List：链表，其底层对应Java中的List接口，一般用ArrayList作为真正的实现类。
def list = [1, 2, 3, 4, 5]
println "list.class：" + list.class  // list.class：class java.util.ArrayList

// 创建新集合
def list2 = new ArrayList<String>(list)
println "创建新集合：" + list2.toListString() // 创建新集合：[1, 2, 3, 4, 5]

// 集合的数据可以不同类型，并且可以重复，可以为null
def list3 = ['a', 1, 'a', 'a', 2.5, 2.5f, 2.5d, 'hello', 7g, null, 9 as byte]
println "集合的数据灵活：" + list3.toListString() // 集合的数据灵活：[a, 1, a, a, 2.5, 2.5, 2.5, hello, 7, null, 9]

// 数组的定义方法
def array1 = [1, 2, 3, 4, 5] as int[]
int[] array2 = [1, 2, 3, 4, 5]
println "array1.class：" + array1.class  // array1.class：class [I
println "array2.class：" + array2.class  // array1.class：class [I

// 增
list.add(6)
println "增 list：" + list.toListString() // 增 list：[1, 2, 3, 4, 5, 6]

// 删
list.remove(5)
println "删 list：" + list.toListString() // 删 list：[1, 2, 3, 4, 5]

list.removeElement(4)
println "删 list：" + list.toListString() // 删 list：[1, 2, 3, 5]

// removeAll { it == 3 } 等于：removeAll() { it == 3 } 或 removeAll({ it == 3 })
list.removeAll { it == 3 }
println "删 list：" + list.toListString() // 删 list：[1, 2, 5]

list = list - [5]
println "删 list：" + list.toListString() // 删 list：[1, 2]

// 改
list[1] = 100
println "改 list.get(2)：" + list.get(1) // 改 list.get(1)：100

// 查
println "查 list.find { it == 1 }：" + list.find { it == 1 } // 查 list.find { it == 1 }：1
println "查 list.findAll { it > 0 }：" + list.findAll { it > 0 } // 查 list.findAll { it > 0 }：[1, 100]
// any来查找只要符合就返回true
println "查 list.any { it == 1 }：" + list.any { it == 1 } // 查 list.any { it == 1 }：true
// every来查找每一项符合才返回true
println "查 list.every { it == 1 }：" + list.every { it % 2 == 0 } // 查 list.every { it % 2 == 0 }：false

/**
 * 列表的排序
 */
def sortList = [1, 5, 8, 3, 0, 6, 9, 8]
println sortList.sort()   // [0, 1, 3, 5, 6, 8, 8, 9]

// 排序策略1：Java的排序策略
Comparator mc = { def a, def b ->
    a == b ? 0 :
            Math.abs(a) < Math.abs(b) ? 1 : -1
}
Collections.sort(sortList, mc)
println sortList  // [9, 8, 8, 6, 5, 3, 1, 0]

// 排序策略2：Groovy的排序策略（建议）
sortList.sort { def a, def b ->
    a == b ? 0 :
            Math.abs(a) < Math.abs(b) ? 1 : -1
}
println sortList  // [9, 8, 8, 6, 5, 3, 1, 0]

// 遍历
list.each { def element ->
    println("the element is ${element}")  // the element is 1  the element is 100
}
list.eachWithIndex { def element, def index ->
    println("${index}:the element is ${element}") // 0:the element is 1  0:the element is 1
}


/***************************************** Map ********************************************/
// Map：键-值表，其底层对应Java中的LinkedHashMap
// 定义：def map = new HashMap()
def color = [red: 'ff0000', green: '00ff00', blue: '0000ff']

// 类型
println color.getClass() // class java.util.LinkedHashMap

// 索引
println color['red']  // ff0000
println color.red     // ff0000

// 添加元素
color.isColor = true
color.complex = [a: 1, b: 2]
println color.toMapString() // [red:ff0000, green:00ff00, blue:0000ff, isColor:true, complex:[a:1, b:2]]
// 注意key的取值， key：默认是单引号不可变字符串，red会编译为'red'，key做为变量时特殊处理
def pink = 'pink'
color.(pink) = 'ff00ff'
println color.toMapString() // [red:ff0000, green:00ff00, blue:0000ff, isColor:true, complex:[a:1, b:2], pink:ff00ff]

// 查找
def colorResult = color.find { def entry ->
    entry.value == true
}
println colorResult  // isColor=true

def colorResults = color.findAll { def entry ->
    entry.key instanceof String
}.collect {
    it.key.getClass() // [class java.lang.String, class java.lang.String, class java.lang.String, class java.lang.String, class java.lang.String, class java.lang.String]
}
println colorResults // [red:ff0000, green:00ff00, blue:0000ff, isColor:true, complex:[a:1, b:2]]

// 分组
def group = color.groupBy { def entry ->
    entry.value == true ? "值非字符串" : "其他"
}
println group  // [其他:[red:ff0000, green:00ff00, blue:0000ff, complex:[a:1, b:2], pink:ff00ff], 值非字符串:[isColor:true]]

// 排序
def sort = color.sort{ color1, color2 ->
    color1.value == color2.value ? 0 : color1.value != color1.value ? 1 : -1
}
println sort.toMapString()  // [pink:ff00ff, complex:[a:1, b:2], isColor:true, blue:0000ff, green:00ff00, red:ff0000]

// 遍历entry
color.each { def entry ->
    println "the key is:${entry.key}, the value is: ${entry.value}"
}
//the key is:red, the value is: ff0000
//the key is:green, the value is: 00ff00
//the key is:blue, the value is: 0000ff
//the key is:isColor, the value is: true
//the key is:complex, the value is: [a:1, b:2]
//the key is:pink, the value is: ff00ff

// 带索引遍历
color.eachWithIndex { def key, def value, def index ->
    println "${index}:the key is ${key}, the value is ${value}"
}
//0:the key is red, the value is ff0000
//1:the key is green, the value is 00ff00
//2:the key is blue, the value is 0000ff
//3:the key is isColor, the value is true
//4:the key is complex, the value is [a:1, b:2]
//5:the key is pink, the value is ff00ff


/***************************************** Range ********************************************/
// Range：范围，它其实是List的一种拓展
// 创建左右包含的Rangerange = 5..<8; // 不包含8，小于8
def range = 5..8;
println range[0]  // 5
println range.contains(6)  // true
println range.from // 5
println range.to // 8

// 遍历
range.each { // 建议
    println it  // 5 6 7 8
}
for(i in range) {
    println i  // 5 6 7 8
}

// switch
def getRange(def num) {
    def result = "";
    switch(num) {
        case 3..<5:
            result = "3-5，不包含5"
            break
        case 5..6:
            result = "5-6"
            break
        case 7..8:
            result = "7-8"
            break
        default:
            break
    }
    result
}
println getRange(5)  // 5-6


/*************************************** 面向对象 ******************************************/

/***************************************** 接口 ********************************************/
// 接口不允许定义非public的方法
interface Action {
    int getAge()
}

/***************************************** 类 ********************************************/
// 默认所有的类和方法都是public的，所有类的字段都是private的
class People implements Action {
    List hobby = ['swim', 'play', 'watch TV']
    String name = "Jerry"

    @Override
    int getAge() {
        return 20
    }
}

// 使用def接受类对象的引用：def people = new People()
def people = new People()
// 在类中声明的字段都默认会生成对应的setter,getter方法，调用.属性实际是调用setter,getter方法
println "people.hobby.get(0)：${people.hobby.get(0)}"  // people.hobby.get(0)：swim
println "people.age：${people.age} people.name：${people.name} people.getName()：${people.getName()}"  // people.age：20 people.name：Jerry people.getName()：Jerry

// 为类动态的添加一个属性，添加后重新new一个对象
People.metaClass.sex = "male"
def peopleMeta = new People()
println peopleMeta.sex    // male
peopleMeta.sex = "female"
println peopleMeta.sex    // female

// 为类动态的添加一个方法，添加后重新new一个对象
People.metaClass.sexUpperCase = { sex.toUpperCase() }
def peopleMeta1 = new People()
println peopleMeta1.sexUpperCase()  // MALE

// 为类动态的添加一个静态方法，添加后重新new一个对象（没定义构造函数，必须指定属性:赋值）
People.metaClass.static.createPeople = { def hobby, def name ->
    new People(hobby: hobby, name: name)
}
def peopleMeta2 = People.createPeople(['play'], 1)
println peopleMeta2.hobby + peopleMeta2.name // [play, 1]