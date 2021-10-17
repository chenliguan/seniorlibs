package com.seniorlibs.designpattern.proxy.v1

import com.seniorlibs.designpattern.ch25v0.model.UserVo

open class UserController : IUserController {
    //...省略其他属性和方法...
    override fun login(telephone: String?, password: String?): UserVo {
        //...省略login逻辑...
        //...返回UserVo数据...
        return UserVo()
    }

    override fun register(telephone: String?, password: String?): UserVo {
        //...省略register逻辑...
        //...返回UserVo数据...
        return UserVo()
    }
}