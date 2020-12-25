package com.read.kotlinlib.annotation;

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
 * Description: @Inherited
 */
@Target({ElementType.TYPE})
// Inherited注解的作用是：使被它修饰的注解具有继承性（如果某个类使用了被@Inherited修饰的注解，则其子类将自动具有该注解）。
@Inherited
// 由于该注解的保留策略为RetentionPolicy.RUNTIME，故可在运行期通过反射机制来使用，否则无法通过反射机制来获取
@Retention(RetentionPolicy.RUNTIME)
public @interface MyInherited {

}
