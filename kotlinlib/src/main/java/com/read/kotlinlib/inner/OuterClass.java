package com.read.kotlinlib.inner;

import com.seniorlibs.baselib.utils.LogUtils;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/11/8.
 * Mender:
 * Modify:
 * Description: 测试Java匿名内部类有哪些限制?
 */
public class OuterClass {

    private final String TAG = "OuterClass";

    public void main() {
        // 1、匿名内部类的名字
        innerClassName();
        // 2、匿名内部类的继承结构
        innerClassSucceedStructure();
    }

    /**
     * 1、匿名内部类的名字
     */
    private void innerClassName() {
        try {
            Class fooClass = Class.forName("com.read.kotlinlib.inner.OuterClass$1");
            LogUtils.e(TAG, "fooClass：" + fooClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // fooClass：class com.read.kotlinlib.inner.OuterClass$1（正常）

        try {
            Class fooClass = Class.forName("com.read.kotlinlib.inner.OuterClass$2");
            LogUtils.e(TAG, "fooClass：" + fooClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // java.lang.ClassNotFoundException: com.read.kotlinlib.inner.OuterClass$1（报错）
    }

    /**
     * 2、匿名内部类的继承结构
     */
    private void innerClassSucceedStructure() {
        // 既 继承某个父类 又 实现某个接口 的“匿名内部类” 的 这种类型，在Java中是不被接受的，编译期直接报错，写不出来这样的代码。
        // 因为这其实是一种 或类型，即Runnable或上Foo的结果，作为一种类，这在Java中是不被接受的：
//        Foo runnableFoo = new Foo() implements Runnable {
//            @Override
//            public void run() {
//
//            }
//        }

        // 可是如果实在是想实现一个，既 继承某个父类 又 实现某个接口 的“匿名内部类”这样的类型，但要不想占用太多资源，要求同匿名内部类一样用完即销毁，怎么办？
        // 那别用匿名内部类，在方法体内部实现即可，便可以在方法调用完毕后将其回收，也可以达到需求：
        RunnableFoo runnableFoo = new RunnableFoo();
    }

    /**
     * 方法内部定义一个有名的内部类
     */
    class RunnableFoo extends Foo implements Runnable {

        @Override
        public void run() {

        }
    }


    Foo foo = new Foo() {
        @Override
        int bar() {
            return 0;
        }
    };

    public class Foo {
        int bar() {
            return 1;
        }
    }

    public interface Runnable {
        void run();
    }
}
