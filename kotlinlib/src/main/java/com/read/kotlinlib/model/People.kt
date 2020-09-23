package com.read.kotlinlib.model

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/4/1
 * Mender:
 * Modify:
 * Description: People实体
 */
class People(var name: String, var age: Int, var hobby: List<String>) {

    fun isMen() : Boolean {
        return true
    }

    fun isYong() : Boolean {
        return false
    }
}