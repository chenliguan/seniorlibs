package com.read.kotlinlib.reflect;

import com.seniorlibs.baselib.utils.LogUtils;

import java.lang.reflect.Field;

/**
 * Author: 陈李冠
 * Time: 2021/4/22
 * Description: 反射测试
 */
public class ReflectTest {

    public static final String TAG = "ReflectTest";

    public static void modifyFinal() {
        User user = new User();
        Class clz = User.class;

        try {

            LogUtils.d(TAG, "修改 私有 String 前：" + user.getName());
            Field field1 = clz.getDeclaredField("name");
            field1.setAccessible(true);
            field1.set(user, "xixi");
            LogUtils.d(TAG, "修改 私有 String 后：" + user.getName());
            // 获取 field 对应的变量在 user 对象中的值
            LogUtils.d(TAG, "获取 field 对应的变量在 user 对象中的值：" + field1.get(user));


            Field field2 = clz.getDeclaredField("student");
            field2.setAccessible(true);
            LogUtils.d(TAG, "修改 基本普通的对象变量 前：" + field2.get(user));

            Field field3 = clz.getDeclaredField("student");
            field3.setAccessible(true);
            LogUtils.d(TAG, "修改 基本普通的对象变量 中：" + field3.get(user) + " " + (field3 == field2));

            field2.set(user, new User.Student());
            Field field4 = clz.getDeclaredField("student");
            field4.setAccessible(true);
            LogUtils.d(TAG, "修改 基本普通的对象变量 后：" + field4.get(user) + " " + (field4 == field2));


            Field field6 = clz.getDeclaredField("name");
            field6.setAccessible(true);
            // 获取静态变量
            Object getName = field6.get(null);
            LogUtils.d(TAG, "修改 静态变量 前：" + getName + " " + User.staticName);
            // 修改静态变量
            field6.set(null, "Jkilkkk");
            field6.setAccessible(true);
            System.out.println("修改 静态变量 后：" + User.staticName);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
