package com.seniorlibs.study.basic

import groovy.xml.MarkupBuilder

/*************************************** Xml操作 ******************************************/

final String xml = '''
    <response version-api="2.0">
        <value>
            <books id="1" classification="android">
                <book available="20" id="1">
                    <title>疯狂Android讲义</title>
                    <author id="1">李刚</author>
                </book>
                <book available="14" id="2">
                   <title>第一行代码</title>
                   <author id="2">郭林</author>
               </book>
               <book available="13" id="3">
                   <title>Android开发艺术探索</title>
                   <author id="3">任玉刚</author>
               </book>
               <book available="5" id="4">
                   <title>Android源码设计模式</title>
                   <author id="4">何红辉</author>
               </book>
           </books>
           <books id="2" classification="web">
               <book available="10" id="1">
                   <title>Vue从入门到精通</title>
                   <author id="4">李刚</author>
               </book>
           </books>
       </value>
    </response>
'''

// 开始解析此xml数据
def xmlSluper = new XmlSlurper()
def response = xmlSluper.parseText(xml)

println response.value.books[0].book[0].title.text()  // 疯狂Android讲义
println response.value.books[0].book[0].author.text() // 李刚
println response.value.books[1].book[0].@available  // 10

def list = []
response.value.books.each { books ->
    // 下面开始对书结点进行遍历
    books.book.each { book ->
        def author = book.author.text()
        if (author.equals('李刚')) {
            list.add(book.title.text())
        }
    }
}
println list.toListString()  // [疯狂Android讲义, Vue从入门到精通]

// 深度遍历xml数据
def titles = response.depthFirst().findAll { book ->
    return book.author.text() == '李刚' ? true : false
}
println titles.toListString()  // [疯狂Android讲义李刚, Vue从入门到精通李刚]

// 广度遍历xml数据
def name = response.value.books.children().findAll { node ->
    node.name() == 'book' && node.@id == '2'
}.collect { node ->
    return node.title.text()
}
println name  // [第一行代码]

/**
 * 生成xml格式数据
 <langs type='current' count='3' mainstream='true'>
 <language flavor='static' version='1.5'>Java</language>
 <language flavor='dynamic' version='1.6.0'>Groovy</language>
 <language flavor='dynamic' version='1.9'>JavaScript</language>
 </langs>
 */
def sw = new StringWriter()
def xmlBuilder = new MarkupBuilder(sw) //用来生成xml数据的核心类
// 根结点langs创建成功
xmlBuilder.langs(type: 'current', count: '3',
        mainstream: 'true') {
    //第一个language结点
    language(flavor: 'static', version: '1.5') {
        age('16')
    }
    language(flavor: 'dynamic', version: '1.6') {
        age('10')
    }
    language(flavor: 'dynamic', version: '1.9', 'JavaScript')
}
println sw
//<langs type='current' count='3' mainstream='true'>
//  <language flavor='static' version='1.5'>
//    <age>16</age>
//  </language>
//  <language flavor='dynamic' version='1.6'>
//    <age>10</age>
//  </language>
//  <language flavor='dynamic' version='1.9'>JavaScript</language>
//</langs>

def langs = new Langs()
xmlBuilder.langs(type: langs.type, count: langs.count, mainstream: langs.mainstream) {
    //遍历所有的子结点
    langs.languages.each { lang ->
        language(flavor: lang.flavor,
                version: lang.version, lang.value)
    }
}
println langs

// 对应xml中的langs结点
class Langs {
    String type = 'current'
    int count = 3
    boolean mainstream = true
    def languages = [
            new Language(flavor: 'static',
                    version: '1.5', value: 'Java'),
            new Language(flavor: 'dynamic',
                    version: '1.3', value: 'Groovy'),
            new Language(flavor: 'dynamic',
                    version: '1.6', value: 'JavaScript')
    ]
}
// 对应xml中的languang结点
class Language {
    String flavor
    String version
    String value
}



