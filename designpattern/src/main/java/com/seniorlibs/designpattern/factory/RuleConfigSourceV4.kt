package com.seniorlibs.designpattern.factory

import com.seniorlibs.designpattern.simplefactory.InvalidRuleConfigException
import com.seniorlibs.designpattern.simplefactory.RuleConfig

/**
 * 工厂方法模式
 * RuleConfigParserFactoryMap 类是创建工厂对象的工厂类，getParserFactory() 返回的是缓存好的单例工厂对象。
 */
class RuleConfigSourceV4 {
    fun load(ruleConfigFilePath: String): String? {
        val ruleConfigFileExtension = getFileExtension(ruleConfigFilePath)
        val parserFactory =
            RuleConfigParserFactoryMap.getParserFactory(ruleConfigFileExtension)
                ?: throw InvalidRuleConfigException("Rule config file format is not supported: $ruleConfigFilePath")
        val parser = parserFactory.createParser()

        val configText = ""
        // 从ruleConfigFilePath文件中读取配置文本到configText中
        return parser?.parse(configText)
    }

    private fun getFileExtension(filePath: String): String {
        // ...解析文件名获取扩展名，比如rule.json，返回json
        return "json"
    }


    // 因为工厂类只包含方法，不包含成员变量，完全可以复用，
    // 不需要每次都创建新的工厂类对象，所以，简单工厂模式的第二种实现思路更加合适。
    object RuleConfigParserFactoryMap {
        // 工厂的工厂
        private val cachedFactories: MutableMap<String, IRuleConfigParserFactory> = HashMap()

        fun getParserFactory(type: String?): IRuleConfigParserFactory? {
            return if (type == null || type.isEmpty()) {
                null
            } else cachedFactories[type.toLowerCase()]
        }

        init {
            cachedFactories["json"] = JsonRuleConfigParserFactory()
            cachedFactories["xml"] = XmlRuleConfigParserFactory()
            cachedFactories["yaml"] = YamlRuleConfigParserFactory()
            cachedFactories["properties"] = PropertiesRuleConfigParserFactory()
        }
    }
}