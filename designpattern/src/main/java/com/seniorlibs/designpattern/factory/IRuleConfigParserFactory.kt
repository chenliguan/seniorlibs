package com.seniorlibs.designpattern.factory

import com.seniorlibs.designpattern.simplefactory.IRuleConfigParser

interface IRuleConfigParserFactory {
    fun createParser(): IRuleConfigParser?
}