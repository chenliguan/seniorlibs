package com.seniorlibs.designpattern.simplefactory

/**
 * 简单工厂模式
 * 为了让代码逻辑更加清晰，可读性更好，善于将功能独立的代码块封装成函数
 */
class RuleConfigSourceV2 {
    fun load(ruleConfigFilePath: String): String {
        val ruleConfigFileExtension = getFileExtension(ruleConfigFilePath)
        val parser = createParser(ruleConfigFileExtension)

        val configText = ""
        // 从 ruleConfigFilePath 文件中读取配置文本到 configText中
        return parser.parse(configText)
    }

    private fun getFileExtension(filePath: String): String {
        // ...解析文件名获取扩展名，比如rule.json，返回json
        return "json"
    }

    /**
     * 可以将代码中涉及 parser 创建的部分逻辑剥离出来，抽象成 createParser() 函数
     */
    private fun createParser(configFormat : String) : IRuleConfigParser {
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