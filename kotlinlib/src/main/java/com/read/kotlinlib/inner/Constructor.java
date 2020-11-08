package com.read.kotlinlib.inner;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/11/8.
 * Mender:
 * Modify:
 * Description: 构造函数类（匿名内部类的父类）
 */
class Constructor {

    /**
     * 非静态的内部类
     */
    public abstract class InnerClass {
        abstract void test();
    }

    /**
     * 接口
     */
    public interface InnerInterface {
        void test();
    }
}
