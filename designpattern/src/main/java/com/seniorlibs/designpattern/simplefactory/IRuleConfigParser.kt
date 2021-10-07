package com.seniorlibs.designpattern.simplefactory

interface IRuleConfigParser {
    fun parse(config : String): String
}