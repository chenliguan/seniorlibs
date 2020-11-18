package com.read.kotlinlib.inner;

import com.seniorlibs.baselib.utils.LogUtils;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/11/8.
 * Mender:
 * Modify:
 * Description: 测试Java内部类?
 */
public class Outer {

    private final String TAG = "Outer";

    private int count = 0;

    private void add() {
        count++;
    }

    /**
     * 内部类
     */
    public class InnerClass {
        private int number = 1;
        private int getSomething() {
            // 直接可以调用外部类的方法。等价于:Outer.this.add()
            add();
            // 等价于:this.number + Outer.this.count
            return number + count;
        }
    }

    /**
     * 静态内部类
     */
    public static class StaticInnerClass {
        private int number = 1;
        public int getValue() {
            // add(); 编译器直接报错
            // 不持有外部类的引用，所以必须通过外部类的对象来访问
            Outer Outer = new Outer();
            Outer.add();
            // 等价于:this.number + Outer.count
            return number + Outer.count;
        }
    }


    public void main() {
        // 1、测试内部类
        testInnerNested();
        // 2、测试外部类可以访问内部类的private
        testInnerPrivate();
    }

    /**
     * 1、匿名内部类的名字
     */
    private void testInnerNested() {
        // 内部类：必须现new出外部类的对象，才能new出内部类的对象
        Outer.InnerClass innerClass = new Outer().new InnerClass();  // new InnerClass()
        int res = innerClass.getSomething();
        LogUtils.e(TAG, "testInnerNested + Java + 内部类1：" + res);

        // 静态内部类：直接可以new出内部类的对象
        Outer.StaticInnerClass staticInnerClass = new StaticInnerClass();
        int value = staticInnerClass.getValue();
        LogUtils.e(TAG, "testInnerNested + Java + 静态内部类1：" + value);
    }

    /**
     * 2、测试外部类可以访问内部类的private
     */
    private void testInnerPrivate() {
        // 在 Java 中，外部类 可以访问 内部类 的 private 变量：
        int result1 = new InnerClass().number * 2;
        int result2 = new StaticInnerClass().number * 2;
    }
}
