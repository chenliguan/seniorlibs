package com.seniorlibs.designpattern.builder

import external.org.apache.commons.lang3.StringUtils

/**
 * 建造者模式
 */
class ResourcePoolConfig private constructor(builder: Builder) {

    private val name: String?
    private val maxTotal: Int
    private val maxIdle: Int
    private val minIdle: Int

    init {
        name = builder.name
        maxTotal = builder.maxTotal
        maxIdle = builder.maxIdle
        minIdle = builder.minIdle
    }

    // 我们将 Builder 类设计成了 ResourcePoolConfig 的内部类，
    // 也可以将 Builder 类设计成独立的非内部类 ResourcePoolConfigBuilder。
    class Builder {
        internal var name: String? = null
        internal var maxTotal = DEFAULT_MAX_TOTAL
        internal var maxIdle = DEFAULT_MAX_IDLE
        internal var minIdle = DEFAULT_MIN_IDLE

        fun build(): ResourcePoolConfig {
            // 校验逻辑放到这里来做，包括必填项校验、依赖关系校验、约束条件校验等
            require(StringUtils.isNotBlank(name)) { "..." }
            require(maxIdle <= maxTotal) { "..." }
            require(!(minIdle > maxTotal || minIdle > maxIdle)) { "..." }
            return ResourcePoolConfig(this)
        }

        fun setName(name: String?): Builder {
            require(!StringUtils.isBlank(name)) { "..." }
            this.name = name
            return this
        }

        fun setMaxTotal(maxTotal: Int): Builder {
            require(maxTotal > 0) { "..." }
            this.maxTotal = maxTotal
            return this
        }

        fun setMaxIdle(maxIdle: Int): Builder {
            require(maxIdle >= 0) { "..." }
            this.maxIdle = maxIdle
            return this
        }

        fun setMinIdle(minIdle: Int): Builder {
            require(minIdle >= 0) { "..." }
            this.minIdle = minIdle
            return this
        }

        companion object {
            private const val DEFAULT_MAX_TOTAL = 8
            private const val DEFAULT_MAX_IDLE = 8
            private const val DEFAULT_MIN_IDLE = 0
        }
    }
}

// 这段代码会抛出 IllegalArgumentException，因为 minIdle > maxIdle
var config = ResourcePoolConfig.Builder()
    .setName("dbconnectionpool")
    .setMaxTotal(16)
    .setMaxIdle(10)
    .setMinIdle(12)
    .build()

