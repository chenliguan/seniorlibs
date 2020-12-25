package com.read.kotlinlib.annotation;

import java.lang.annotation.Repeatable;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/12/25.
 * Mender:
 * Modify:
 * Description: 重复注解
 */
// @Repeatable，指向存储注解 Roles，在使用时候，直接可以重复使用 Role 注解
@Repeatable(Roles.class)
public @interface Role {
    String roleName();
}
