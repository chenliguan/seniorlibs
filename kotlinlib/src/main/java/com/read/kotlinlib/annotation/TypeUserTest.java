package com.read.kotlinlib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/12/25.
 * Mender:
 * Modify:
 * Description: 测试@TYPE_USE
 * @TYPE_USE: 无处不在的注解，可以让编译器执行更严格的代码检查，从而提高程序的健壮性
 * @TYPE_PARAMETER：表示该注解能写在类型参数的声明语句中
 */
public class TypeUserTest {

    @Target(ElementType.TYPE_USE)
    @interface isNotNull {
    }

    @Target(ElementType.TYPE_PARAMETER)
    @interface isParam {
    }

    /**
     * @TYPE_PARAMETER：表示该注解能写在类型参数的声明语句中
     * @param text
     * @param <T>
     */
    public <@isParam T> void  method(T text) {
    }
    /**
     * @TYPE_USE: 无处不在的注解，可以让编译器执行更严格的代码检查，从而提高程序的健壮性
     * @param text
     * @throws @isNotNull RuntimeException
     */
    public <@isNotNull T> void main(@isNotNull String text) throws @isNotNull RuntimeException {
        String str = "str";
        Object obj = (@isNotNull Object) str;
    }
}


