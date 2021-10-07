package com.seniorlibs.designpattern.simplefactory

/**
 * 简单工厂模式
 * 单例模式和简单工厂模式的结合，简单工厂模式的第二种实现方法
 */
class RuleConfigSourceV4 {
    fun load(ruleConfigFilePath: String): String {
        val ruleConfigFileExtension = getFileExtension(ruleConfigFilePath)
        val parser = RuleConfigParserFactory.createParser(ruleConfigFileExtension)

        val configText = ""
        // 从 ruleConfigFilePath 文件中读取配置文本到 configText中
        return parser?.parse(configText)!!
    }

    private fun getFileExtension(filePath: String): String {
        // ...解析文件名获取扩展名，比如rule.json，返回json
        return "json"
    }

    object RuleConfigParserFactory {
        private val cachedParsers = mutableMapOf<String, IRuleConfigParser>()

        init {
            cachedParsers["json"] = JsonRuleConfigParser()
            cachedParsers["xml"] = XmlRuleConfigParser()
            cachedParsers["yaml"] = YamlRuleConfigParser()
            cachedParsers["properties"] = PropertiesRuleConfigParser()
        }

        fun createParser(configFormat: String): IRuleConfigParser? {
            return if (configFormat.isEmpty()) null else cachedParsers[configFormat.toLowerCase()]
        }
    }
}