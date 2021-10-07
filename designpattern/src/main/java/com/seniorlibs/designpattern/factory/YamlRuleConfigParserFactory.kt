package com.seniorlibs.designpattern.factory

import com.seniorlibs.designpattern.simplefactory.IRuleConfigParser
import com.seniorlibs.designpattern.simplefactory.YamlRuleConfigParser

class YamlRuleConfigParserFactory : IRuleConfigParserFactory {
    override fun createParser(): IRuleConfigParser? {
        // .....
        // .....
        // .....
        return YamlRuleConfigParser()
    }
}