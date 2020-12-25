package com.read.kotlinlib.annotation;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/12/25.
 * Mender:
 * Modify:
 * Description: 测试重复注解
 */
class RolesTest {
    @Role(roleName = "role1")
    @Role(roleName = "role2")
    public String doString() {
        return "";
    }
}
