package com.seniorlibs.designpattern.simplefactory

class YamlRuleConfigParser : IRuleConfigParser {
    override fun parse(config: String): String = "yaml"
}