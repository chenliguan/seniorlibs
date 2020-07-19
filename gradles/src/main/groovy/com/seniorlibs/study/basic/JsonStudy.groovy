package com.seniorlibs.study.basic

import groovy.json.JsonOutput
import groovy.json.JsonSlurper

/*************************************** Json操作 ******************************************/

def list = [new People(name: "Terry", age: 23), new People(name: "Tony", age: 22)]
def json = JsonOutput.toJson(list)
println "json：${json}"  // json：[{"hobby":["watch","play"],"age":23,"name":"Terry"},{"hobby":["watch","play"],"age":22,"name":"Tony"}]

// 模拟网络请求
def getNetworkData(String url) {
    // 发送http请求
    def connection = new URL(url).openConnection()
    connection.setRequestMethod('GET')
    connection.connect()
    def response = connection.content.text
    // 将json转化为实体对象
    def jsonSlurper = new JsonSlurper()
    return jsonSlurper.parseText(response)
}

def response = getNetworkData('http://apis.juhe.cn/cook/category?parentid=10001&key=8fac966b379367b0e6f0527d634324ee&dtype=json')
println response  //[resultcode:200, reason:Success, result:[[parentId:10001, name:菜式菜品, list:[[id:1, name:家常菜, parentId:10001], [id:2, name:快手菜, parentId:10001], [id:3, name:创意菜, parentId:10001], [id:4, name:素菜, parentId:10001], [id:5, name:凉菜, parentId:10001], [id:6, name:烘焙, parentId:10001], [id:7, name:面食, parentId:10001], [id:8, name:汤, parentId:10001], [id:9, name:自制调味料, parentId:10001]]]], error_code:0]
println "response.reason：${response.reason}"  // response.reason：Success
println response.result.list.name  // [[家常菜, 快手菜, 创意菜, 素菜, 凉菜, 烘焙, 面食, 汤, 自制调味料]]

// 遍历
def parentIdList = []
response.result.list.each { def item ->
    parentIdList.add(item.parentId)
}
println parentIdList.toListString()  // [[10001, 10001, 10001, 10001, 10001, 10001, 10001, 10001, 10001]]