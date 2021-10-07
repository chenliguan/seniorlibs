package com.seniorlibs.designpattern.simplefactory

/**
 * 简单工厂模式
 * 为了让类的职责更加单一、代码更加清晰，还可以进一步将 createParser() 函数剥离到一个独立的类中，让这个类只负责对象的创建
 */
class RuleConfigSourceV3 {
    fun load(ruleConfigFilePath: String): String {
        val ruleConfigFileExtension = getFileExtension(ruleConfigFilePath)
        val parser = RuleConfigParserFactory.createParser(ruleConfigFileExtension)

        val configText = ""
        // 从 ruleConfigFilePath 文件中读取配置文本到 configText中
        return parser.parse(configText)
    }

    private fun getFileExtension(filePath: String): String {
        // ...解析文件名获取扩展名，比如rule.json，返回json
        return "json"
    }

    /**
     * 进一步将 createParser() 函数剥离到一个独立的类中，让这个类只负责对象的创建
     */
    object RuleConfigParserFactory {
        fun createParser(configFormat: String): IRuleConfigParser {
            var parser: IRuleConfigParser? = null
            if ("json".equals(configFormat, ignoreCase = true)) {
                parser = JsonRuleConfigParser()
            } else if ("xml".equals(configFormat, ignoreCase = true)) {
                parser = XmlRuleConfigParser()
            } else if ("yaml".equals(configFormat, ignoreCase = true)) {
                parser = YamlRuleConfigParser()
            } else if ("properties".equals(configFormat, ignoreCase = true)) {
                parser = PropertiesRuleConfigParser()
            } else {
                throw InvalidRuleConfigException(
                    "Rule config file format is not supported: $configFormat"
                )
            }
            return parser
        }
    }
}