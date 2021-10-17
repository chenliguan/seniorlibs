package com.seniorlibs.designpattern.proxy.v1

import com.seniorlibs.designpattern.ch25v0.model.UserVo

interface IUserController {
    fun login(telephone: String?, password: String?): UserVo?
    fun register(telephone: String?, password: String?): UserVo?
}