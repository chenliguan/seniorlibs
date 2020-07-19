package com.seniorlibs.study.basic

/*************************************** 文件操作 ******************************************/

def file = new File('../README.md')

file.eachLine { line ->
    println line  // # seniorlibs  高级进阶学习库
}

def text = file.getText()
println text  // # seniorlibs  高级进阶学习库

def fileResult = file.readLines()
println fileResult // [# seniorlibs, 高级进阶学习库]

// 读取文件部分内容
def reader = file.withReader { reader ->
    char[] buffer = new char[100]
    reader.read(buffer)
    return buffer
}
println reader // # seniorlibs  高级进阶学习库

def result = copy('../README.md', '../README2.md')
def copy(String sourcePath, String destationPath) {
    try {
        //首先创建目标文件
        def desFile = new File(destationPath)
        if (!desFile.exists()) {
            desFile.createNewFile()
        }

        // 开始copy
        new File(sourcePath).withReader { reader ->
            def lines = reader.readLines()
            desFile.withWriter { writer ->
                lines.each { line ->
                    writer.append(line + "\r\n")
                }
            }
        }
        return true
    } catch (Exception e) {
        e.printStackTrace()
    }
    return false
}
println result  // true

//def people = new People(name: 'Qndroid', age: 26)
//saveObject(people, '../person.bin')
//
//def peopleResult = (People) readObject('../person.bin')
//println "the name is ${peopleResult.name} and the age is ${peopleResult.age}"
//
//def saveObject(Object object, String path) {
//    try {
//        //首先创建目标文件
//        def desFile = new File(path)
//        if (!desFile.exists()) {
//            desFile.createNewFile()
//        }
//        desFile.withObjectOutputStream { out ->
//            out.writeObject(object)
//        }
//        return true
//    } catch (Exception e) {
//    }
//    return false
//}
//
//def readObject(String path) {
//    def obj = null
//    try {
//        def file = new File(path)
//        if (file == null || !file.exists()) return null
//        //从文件中读取对象
//        file.withObjectInputStream { input ->
//            obj = input.readObject()
//        }
//    } catch (Exception e) {
//
//    }
//    return obj
//}