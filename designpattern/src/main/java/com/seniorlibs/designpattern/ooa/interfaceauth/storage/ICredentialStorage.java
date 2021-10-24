package com.seniorlibs.designpattern.ooa.interfaceauth.storage;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/8/15
 * Mender:
 * Modify:
 * Description: 从存储中取出 AppID 和对应的密码
 */
public interface ICredentialStorage {

    String getPasswordByAppID(String appId);
}
