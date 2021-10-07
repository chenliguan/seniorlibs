package com.seniorlibs.designpattern.factory

import com.seniorlibs.designpattern.simplefactory.IRuleConfigParser
import com.seniorlibs.designpattern.simplefactory.JsonRuleConfigParser

class JsonRuleConfigParserFactory : IRuleConfigParserFactory {
    override fun createParser(): IRuleConfigParser? {
        // .....
        // .....
        // .....
        return JsonRuleConfigParser()
    }
}