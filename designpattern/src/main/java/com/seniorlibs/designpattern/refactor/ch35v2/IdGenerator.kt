package com.seniorlibs.designpattern.refactor.ch35v2

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/9/20
 * Mender:
 * Modify:
 * Description: ID 生成器通用接口
 */
interface IdGenerator {
    fun generate(): String?
}