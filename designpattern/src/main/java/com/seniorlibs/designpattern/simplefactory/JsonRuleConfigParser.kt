package com.seniorlibs.designpattern.simplefactory

class JsonRuleConfigParser : IRuleConfigParser {
    override fun parse(config: String): String = "json"
}