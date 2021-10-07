package com.seniorlibs.designpattern.factory

import com.seniorlibs.designpattern.simplefactory.IRuleConfigParser
import com.seniorlibs.designpattern.simplefactory.PropertiesRuleConfigParser

class PropertiesRuleConfigParserFactory : IRuleConfigParserFactory {
    override fun createParser(): IRuleConfigParser? {
        // .....
        // .....
        // .....
        return PropertiesRuleConfigParser()
    }
}