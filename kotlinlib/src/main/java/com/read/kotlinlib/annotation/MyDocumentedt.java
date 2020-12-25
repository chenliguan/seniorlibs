package com.read.kotlinlib.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/12/25.
 * Mender:
 * Modify:
 * Description: @Documentedt
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface MyDocumentedt {
    public String value() default "这是@Documented注解为文档添加的注释";
}
