package com.read.kotlinlib.annotation;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/12/25.
 * Mender:
 * Modify:
 * Description: @Documentedt测试
 */
public class MyDocumentedTest {
    /**
     * 测试 document
     * @return String the response
     */
    @MyDocumentedt
    public String test(){
        return "测试document";
    }
}
