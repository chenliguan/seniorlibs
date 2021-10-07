package com.seniorlibs.designpattern.simplefactory

/**
 * 简单工厂模式
 */
class RuleConfigSourceV1 {
    fun load(ruleConfigFilePath: String): String {
        val ruleConfigFileExtension = getFileExtension(ruleConfigFilePath)
        var parser: IRuleConfigParser? = null
        if ("json".equals(ruleConfigFileExtension, ignoreCase = true)) {
            parser = JsonRuleConfigParser()
        } else if ("xml".equals(ruleConfigFileExtension, ignoreCase = true)) {
            parser = XmlRuleConfigParser()
        } else if ("yaml".equals(ruleConfigFileExtension, ignoreCase = true)) {
            parser = YamlRuleConfigParser()
        } else if ("properties".equals(ruleConfigFileExtension, ignoreCase = true)) {
            parser = PropertiesRuleConfigParser()
        } else {
            throw InvalidRuleConfigException(
                "Rule config file format is not supported: $ruleConfigFilePath"
            )
        }
        val configText = ""
        // 从 ruleConfigFilePath 文件中读取配置文本到 configText中
        return parser.parse(configText)
    }

    private fun getFileExtension(filePath: String): String {
        // ...解析文件名获取扩展名，比如rule.json，返回json
        return "json"
    }
}