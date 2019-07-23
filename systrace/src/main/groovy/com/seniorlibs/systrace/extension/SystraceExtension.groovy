package com.seniorlibs.systrace.extension

/**
 * Created by guan on 19/7/22.
 */
class SystraceExtension {
    boolean enable
    String baseMethodMapFile
    String blackListFile
    String output


    SystraceExtension() {
        enable = true
        baseMethodMapFile = ""
        blackListFile = ""
        output = ""

    }

    @Override
    String toString() {
        """| enable = ${enable}
           | baseMethodMapFile = ${baseMethodMapFile}
           | blackListFile = ${blackListFile}
           | output = ${output}
        """.stripMargin()
    }
}