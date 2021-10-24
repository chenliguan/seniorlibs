package com.seniorlibs.designpattern.refactor.ch35v2

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/9/20
 * Mender:
 * Modify:
 * Description: 递增有序的 ID 生成算法
 */
class SequenceIdGenerator : LogTraceIdGenerator {

    /**
     * generate() 函数中的三个 if 逻辑重复了，且实现过于复杂，我们要对其进行简化
     *
     * @return String?
     */
    override fun generate(): String = ""
}