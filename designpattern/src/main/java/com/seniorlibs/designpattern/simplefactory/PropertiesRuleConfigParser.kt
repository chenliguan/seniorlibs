package com.seniorlibs.designpattern.simplefactory

class PropertiesRuleConfigParser : IRuleConfigParser {
    override fun parse(config: String): String = "properties"
}