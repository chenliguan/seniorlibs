package com.seniorlibs.designpattern.simplefactory

internal open class InvalidRuleConfigException : java.lang.RuntimeException {

    /**
     * Constructs a `InvalidRuleConfigException` with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    constructor(s: String?) : super(s)

    companion object {
        private const val serialVersionUID = 5162710183389028792L
    }
}
