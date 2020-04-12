package com.read.javalib.generic;

import com.seniorlibs.baselib.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/4/1.
 * Mender:
 * Modify:
 * Description: 泛型测试类
 */
public class GenericTest {

    private static final String TAG = "javalib + GenericTest";

    /**
     * 泛型擦除
     */
    public void genericErasure() {
        List list = new ArrayList();
        List listString = new ArrayList<String>();
        List listInteger = new ArrayList<Integer>();

        try {
            list.getClass().getMethod("add", Object.class).invoke(list, 1);
            listString.getClass().getMethod("add", Object.class).invoke(listString, 1);
            // 给不服气的读者们的测试之处，你可以改成字符串来尝试。
            listInteger.getClass().getMethod("add", Object.class).invoke(listInteger, 1);
            listInteger.getClass().getMethod("add", Object.class).invoke(listInteger, "12");
        } catch (Exception e) {
            e.printStackTrace();
        }

        LogUtils.d(TAG, "list size:" + list.size());
        LogUtils.d(TAG, "listString size:" + listString.size());
        LogUtils.d(TAG, "listInteger size:" + listInteger.size());

        /**
         * 04-01 21:11:58.404 31723-31723/com.read.javalib D/javalib + GenericTest: list size:1
         * 04-01 21:11:58.405 31723-31723/com.read.javalib D/javalib + GenericTest: listString size:1
         * 04-01 21:11:58.405 31723-31723/com.read.javalib D/javalib + GenericTest: listInteger size:2
         */
    }

//    /**
//     * 为什么要用通配符和边界？
//     */
//    public void whyWildcardsBoundaries() {
//        // 定义一个“水果盘子”，逻辑上水果盘子当然可以装苹果
//        // 实际上Java编译器不允许这个操作。会报错，“装苹果的盘子”无法转换成“装水果的盘子”
//        Plate<Fruit> p = new Plate<Apple>(new Apple());
//    }
//
//    /**
//     * 上界<? extends T>不能往里存，只能往外取
//     */
//    public void testExtends() {
//        // 填写Apple的位置，级别一定要小于或等于Fruit
//        Plate<? extends Fruit> p1 = new Plate<Apple>(new Apple());
//        // 检查不通过
//        Plate<? extends Fruit> p2 = new Plate<Food>(new Food());
//        Plate<? extends Fruit> p3 = new Plate<Apple>(new Beef());
//
//        // 不能存入任何元素
//        p1.set(new Banana());       // Error
//        p1.set(new Fruit());        // Error
//        p1.set(new Apple());        // Error
//
//        // 数据获取正常
//        // 但是他只能精确到Fruit或它的基类
//        Fruit result1 = p1.get();
//        Object result2 = p1.get();
//        Apple result3 = p1.get();   // Error
//    }
//
//    /**
//     * 下界<? super T>不影响往里存，但往外取只能放在Object对象里
//     */
//    public void testSuper() {
//        // 填写Fruit的位置，级别一定要大于或等于Fruit
//        Plate<? super Fruit> p1 = new Plate<Fruit>(new Fruit());
//        // 检查不通过
//        Plate<? super Fruit> p3 = new Plate<Fruit>(new Beef());
//        Plate<? super Fruit> p2 = new Plate<Apple>(new Apple());
//
//        // 存入元素正常
//        // 因为元素是Fruit的基类，那往里存粒度比Fruit小的都可以
//        p1.set(new Fruit());
//        p1.set(new Apple());
//        p1.set(new Food());         // Error
//
//        // 读取出来的东西只能精确到Object类。
//        Apple result1 = p1.get();   // Error
//        Fruit result2 = p1.get();   // 会报错，一定要经过强制转化，因为返回的只是一个Object
//        Object result3 = p1.get();  // 返回一个Object数据我们已经属于快要丢失掉全部数据了，所以不适合读取
//    }

    /**
     * 承载者
     *
     * @param <T>
     */
    class Plate<T> {
        private T item;

        public Plate(T t) {
            item = t;
        }

        public void set(T t) {
            item = t;
        }

        public T get() {
            return item;
        }
    }

    /**
     * Lev 1
     */
    class Food {
    }

    /**
     * Lev 2
     */
    class Fruit extends Food {
    }

    class Meat extends Food {
    }

    /**
     * Lev 3
     */
    class Apple extends Fruit {
    }

    class Banana extends Fruit {
    }

    class Pork extends Meat {
    }

    class Beef extends Meat {
    }

    /**
     * Lev 4
     */
    class RedApple extends Apple {
    }

    class GreenApple extends Apple {
    }
}
