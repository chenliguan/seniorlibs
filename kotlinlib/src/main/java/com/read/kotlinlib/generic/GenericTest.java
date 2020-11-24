package com.read.kotlinlib.generic;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/11/21.
 * Mender:
 * Modify:
 * Description: Java泛型测试类
 */
public class GenericTest<T> {
    /**
     * 静态方法无法引用类泛型参数
     */
//    public static void show(T t) {
//        System.out.println("show: " + t);
//    }
    /**
     * 静态方法可以自己定义泛型类型，然后引用泛型参数。字节码：public static method(Ljava/lang/Object;)V
     */
    public static <W> void method(W t) {
        System.out.println(TAG + "method: " + t);
    }


    private static String TAG = "GenericTest";
    private static Gson gson = null;

    public void main() {
        // 泛型擦除
        genericErasure();
//        // 为什么要用通配符和边界？
//        whyWildcardsBoundaries();
//        // 上界不能往里存，只能往外取
//        testExtends();
//        // 下界不影响往里存，但往外取只能放在Object对象里
//        testSuper();
        // 获取泛型参数
        getGenericsArguments();
    }

    /**
     * 泛型擦除
     */
    private void genericErasure() {
        // 能创建参数化类型的集合
        List<String> list = new ArrayList<String>();
        List<T> listT = new ArrayList<T>();


        // 泛型类型会带来类型强转的运行时开销
        List<String> strList = new ArrayList<String>();
        String value = strList.get(0);
        // INVOKEINTERFACE java/util/List.get (I)Ljava/lang/Object;
        // CHECKCAST java/lang/String
    }


    // 字节码：public show(Ljava/lang/Object;)V
    public void show(T t) {
        System.out.println(TAG + "show: " + t);
    }

//    public class Problem extends Exception {
//
//    }

    /**
     * 注意擦除后的冲突：倘若两个接口类型是同一个接口的不同参数化，一个类或类型变量就不能同时作为这两个接口类型的子类
     * 原因：'equals(Object)' in 'java.lang.Object' 这两种方法具有相同的擦除性，但都不覆盖另一种方法。
     *
     * @param o
     * @return
     */
//    public boolean equals(T o) {
//
//    }

    /**
     * 泛型类型无法用作方法重载
     * 报错：Platform declaration clash: The following declarations have the same JVM signature (printList(Ljava/util/List;I)V):
     */
//    public void printList(List<String> list){ }
//
//    public void printList(List<Integer> list){ }

    /**
     * 泛型类型无法当做真实类型使用
     *
     * @param t
     * @param <T>
     */
    private <T> void genericMethod(T t) {
//        // 基本类型无法作为泛型实参
//        List<int> list = new ArrayList<int>();
//
//        List<String> listStr = new ArrayList<String>();
//
//        // 不能实例化类型
//        T newInstance = new T();
//        // 无法获取类型
//        Class c = T.class;
//        // 不能构造泛型数组
//        T[] array = new T[0];
//        // 不能实例化参数化类型的数组。擦除后转换为 Object[] objcts = pairs;
//        Pair[] pairs = new Pair<String, String>[2];
//        // 非法类型判断
//        if (listStr instanceof List<String>) {}
    }

    /**
     * 获取泛型参数
     */
    private void getGenericsArguments() {
        // 匿名内部类
        GenericClass<String> genericClass = new GenericClass<String>() {
            @Override
            public List<Map<String, String>> getValue() {
                return null;
            }
        };

        // 获取类元素泛型
        ParameterizedType superType =
                (ParameterizedType) genericClass.getClass().getGenericSuperclass();
        for (Type actualTypeArgument : superType.getActualTypeArguments()) {
            System.out.println(TAG + " Superclass + actualTypeArgument: " + actualTypeArgument);
            // GenericTest Superclass + actualTypeArgument: class java.lang.String
        }

        // 获取方法元素泛型
        try {
            ParameterizedType methodType =
                    (ParameterizedType) genericClass.getClass().getMethod("getValue").getGenericReturnType();
            for (Type actualTypeArgument : methodType.getActualTypeArguments()) {
                System.out.println(TAG + " Method + actualTypeArgument: " + actualTypeArgument);
                // GenericTest Method + actualTypeArgument: java.util.Map<java.lang.String, java.lang.String>
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    /**
     * 转成list
     * 解决了泛型在编译期类型被擦除导致报错：实际上调用的就是getGenericReturnTypes方法：
     * @param gsonString
     * @return
     */
    public static <T> List<T> gsonToList(String gsonString) {
        List<T> list = null;
        if (gson == null) {
            gson = new Gson();
        }
        list = gson.fromJson(gsonString, new TypeToken<List<T>>() {}.getType());
        return list;
    }
}
