package com.seniorlibs.designpattern.factory

import com.seniorlibs.designpattern.simplefactory.IRuleConfigParser
import com.seniorlibs.designpattern.simplefactory.XmlRuleConfigParser

class XmlRuleConfigParserFactory : IRuleConfigParserFactory {
    override fun createParser(): IRuleConfigParser? {
        // .....
        // .....
        // .....
        return XmlRuleConfigParser()
    }
}