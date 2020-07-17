package com.seniorlibs.study.variable

/*************************************** 类型 ******************************************/
int x = 10
println "x.class" + x.class // x.classclass java.lang.Integer

double y = 3.14
println "y.class" + y.class // y.classclass java.lang.Double

def x_1 = 11
println x_1.class // class java.lang.Integer

def y_1 = 3.1415
println y_1.class // class java.math.BigDecimal

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

def str6 = "str6：2 + 3 = ${2 + 3}"  // 可扩展做任意的表达式
println str6   // str6：2 + 3 = 5

// 返回值是方法最后一条语句的值（默认返回null）
String echo(String msg) {
    return msg    // 等同于： msg
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

/************************************* 循环逻辑 ****************************************/
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
def clouser1 = { String name -> // 等同于：name ->
    println "Hello ${name}"
}
clouser1.call('groovy1')  // Hello groovy1

// 闭包传多个参
def clouser2 = { name, age ->
    println "Hello ${name}, age ${age}"
}
clouser2.call('groovy2', 12)  // Hello groovy2, age 12

// 闭包对于单一存在的参数it默认隐藏，直接使用it
def clouser3 = {    // 等同于： it ->
    println "Hello ${it}"
}
clouser3.call('groovy3')  // Hello groovy3

// 闭包总会返回一个值，返回值是闭包的最后一条语句的值（默认返回null）
def clouser4 = { name ->
//    println "Hello ${name}"
    "Hello ${name}"  // 等同于：return "Hello ${name}"
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
println clouser7.call() { 'groovy7' }             // groovy7groovy7
println clouser7.call { 'groovy7' }               // groovy7groovy7 , 类同：repositories { mavenCentral() }

println clouser7 {                                // groovy7groovy7 , 类同：repositories { mavenCentral() }
    def name = "name"  // 等同于：name = "name"
    age = "age"
    str = "str"
    'groovy6 ${name}'  // 必须在""里面
    getEnv("yes") + " groovy6 ${name} ${age} ${str}  " // getEnv yes groovy6 name age str  getEnv yes groovy6 name age str
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
println clouser8.call('groovy8', { name -> name * 2 })   // groovy8groovy8
println clouser8.call('groovy8') { name -> name * 2 }    // groovy8groovy8

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
    println temp                 // t h e  2 3
}
println str.each {               // the 23

}
// find来查找符合条件的第一个
println str.find { temp ->       // 2
    temp.isNumber()
}
// findAll来查找符合条件的所有值，放在集合里
println str.findAll { temp ->   // [2, 3]
    temp.isNumber()
}
// any来查找只要符合就返回true
println str.any { temp ->       // true
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


/***************************************** 类型 ********************************************/

// def map = new HashMap()
def color = [red: 'ff0000', green: '00ff00', blue: '0000ff']
color.yellow = 'ffff00' // 添加
println color['red']
println color.green
println color.getClass()
println color.yellow

//注意key的取值,key做为变量时特殊处理
def pink = 'pink'
color.(pink) = 'ff00ff'
println color.toMapString()
def map = [a: 1, b: 2]
color.complex = map
println color.toMapString()

//color.each { def entry ->
//    println "the key is:${entry.key} the value is: ${entry.value}"
//}
//color.eachWithIndex { def key, def value, def index ->
//    println "${index}:the key is ${key}, the value is ${value}"
//}