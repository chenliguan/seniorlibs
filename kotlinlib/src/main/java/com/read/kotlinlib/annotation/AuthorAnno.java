package com.read.kotlinlib.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/12/25.
 * Mender:
 * Modify:
 * Description: 自定义注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
// 由于该注解的保留策略为RetentionPolicy.RUNTIME，故可在运行期通过反射机制来使用，否则无法通过反射机制来获取
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorAnno {
    /**
     * 注解方法不带参数，比如name()，website()；
     * 注解方法返回值类型：基本类型、String、Enums、Annotation以及前面这些类型的数组类型
     * 注解方法可有默认值，比如default "chen.com"，默认website="chen.com"
     * @return
     */
    String name();
    String website() default "chen.com";
    int revision() default 1;
}
