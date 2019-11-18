package com.seniorlibs.binder.aidl;

interface ISecurityCenter {
    String encrypt(String content);
    String decrypt(String password);
}