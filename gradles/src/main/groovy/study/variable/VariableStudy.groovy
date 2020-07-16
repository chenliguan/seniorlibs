package study.variable

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

def result = echo(str6)
println "result：" + result + "， result.class：" + result.class // result：str6：2 + 3 = 5， result.class：class java.lang.String
String echo(String msg) {
    return msg;
}

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
// 闭包定义
def clouser = { println "Hello groovy" }
clouser()       // Hello groovy
clouser.call()  // Hello groovy

// 闭包传参
def clouser1 = { String name -> println "Hello ${name}" }
clouser1('groovy1')  // Hello groovy1

// 闭包传多个参
def clouser2 = { String name, int age -> println "Hello ${name}, age ${age}" }
clouser2('groovy2', 12)  // Hello groovy2, age 12

// 闭包默认参数it（隐藏）
def clouser3 = { println "Hello ${it}" }
clouser3('groovy3')  // Hello groovy3

// 闭包返回值（默认返回null）
def clouser4 = { String name ->
//    println "Hello ${name}" // 闭包返回值：null
    return "Hello ${name}"   // 闭包返回值：Hello groovy4
}
def result5 = clouser4('groovy4')
println "闭包返回值：" + result5


def calculate(int number) {
    def result = 1
    1.upto(number, { num ->
        result *= num
    })

    1.upto(number) { num ->
        result *= num
    }
    return result
}
println "calculate：" + calculate(8)

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