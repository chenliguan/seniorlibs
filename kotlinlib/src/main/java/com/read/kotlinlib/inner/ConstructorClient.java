package com.read.kotlinlib.inner;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/11/8.
 * Mender:
 * Modify:
 * Description: 测试匿名内部类的构造方法（关注：匿名内部类对外部类的引用）
 * 注意：生成字节码时，只保留 1、2、3 方法的一个
 */
public class ConstructorClient {

    private final String TAG = "ConstructorInnerClassClient";


    /**
     * 1、父类非静态、所在方法（匿名内部类被new出来的位置所处的方法）非静态
     *    非静态的匿名内部类
     */
    public void runInnerClass() {
        Constructor.InnerClass inner = new Constructor().new InnerClass() {
            @Override
            public void test() {
            }
        };
    }

    /**
     * 2、父类静态（接口跟静态匿名内部类的效果是差不多的，就是静态的）、所在方法非静态
     */
    public void runInnerInterface() {
        Constructor.InnerInterface inner = new Constructor.InnerInterface() {
            @Override
            public void test() {
            }
        };
    }

    /**
     * 3、父类非静态、所在方法静态
     */
    public static void runStaticInnerClass() {
        Constructor.InnerClass inner = new Constructor().new InnerClass() {
            @Override
            public void test() {
            }
        };
    }

    /**
     * 4、父类静态、所在方法静态
     */
    public static void runStaticInnerInterface() {
        Constructor.InnerInterface inner = new Constructor.InnerInterface() {
            @Override
            public void test() {
            }
        };
    }

    /**
     * 5、匿名内部类 所在方法内（外部作用域） 的 局部变量快照的情况
     */
    public static void runInnerObject() {
//        final Object obj = new Object();
        // Java8更加智能，如果局部变量被方法内的匿名内部类访问，该局部变量相当于自动使用了final修饰
        Object obj = new Object();

        Constructor.InnerInterface inner = new Constructor.InnerInterface() {
            @Override
            public void test() {
                System.out.println("runInnerInterface + obj.toString()" + obj.toString());
            }
        };
    }
}
