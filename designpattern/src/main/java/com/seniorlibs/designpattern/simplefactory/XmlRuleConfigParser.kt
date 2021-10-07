package com.seniorlibs.designpattern.simplefactory

class XmlRuleConfigParser : IRuleConfigParser {
    override fun parse(config: String): String = "xml"
}