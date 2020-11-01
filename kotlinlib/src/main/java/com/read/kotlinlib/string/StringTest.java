package com.read.kotlinlib.string;

import com.seniorlibs.baselib.utils.LogUtils;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/10/25.
 * Mender:
 * Modify:
 * Description: 字符串测试
 */
public class StringTest {

    private static final String TAG = "StringTest";

    public StringTest() {
        super();

        intern();
    }

    /**
     * 存储
     */
    public void store() {

    }

    /**
     * intern()的官方解释：
     * String在Java 6以后提供了intern()方法，目的是提示JVM把相应字符串缓存起来，以备重复使用。
     * 在我们创建字符串对象并调用intern()方法的时候，如果常量池中已经有缓存的字符串，就会返回缓存里的对象；
     * 否则将其缓存到常量池中，并返回对该对象。一般来说，JVM会将所有的类似“abc”这样的文本字符串，或者字符串常量之类缓存起来。
     *
     * 对于任意两个字符串，当且仅当{@code s.equals(t)}为{@code true}时，{s.intern() == t.intern()}为{@code true}。
     */
    public void intern() {
        // 创建了2个对象，分别是 字符串常量池中的对象"1" 和 在堆中(s引用指向)的对象"1"
        String s = new String("1");
        // 堆中s去常量池中寻找后发现"1"已经在常量池里了，返回常量池中的字符串。注意：s引用指向还是在堆中的对象"1"
        s.intern();
        // 创建一个s2的引用指向 字符串常量池中的对象"1"
        String s2 = "1";
        // s 和 s2 地址不同 false
        LogUtils.d(TAG, "s == s2：" + (s == s2));

        // 创建了2个对象，分别是 字符串常量池中的对象"3" 和 在堆中(s3引用指向)的对象(s3引用对象的内容是"33")。但此时常量池中是没有对象"33"的。
        String s3 = new String("3") + new String("3");
        // 将s3中的"33"字符串放入String常量池中，因为此时常量池中不存在"33"字符串，
        // JDK6常量池是放在永久代中，永久代区和正常的堆区域是完全分开的，所以在常量池中创建一个新"33"对象，因此引用地址是不同的；
        // JDK7后常量池移到了堆上，常量池中不会重新创建对象了，会直接保存堆中(s3引用指向)的对象引用，因此引用地址是相同的。
        s3.intern();
        // 声明s4的"33"是显示声明的，因此会直接去常量池中创建，创建时发现常量池中已存在"33"对象，s4指向s3引用对象，所以s4引用指向就和s3一样了，因此最后s3==s4是true
        String s4 = "33";
        // JDK6：s3与s4地址不同 false
        // JDK7：s3与s4地址相同 true
        LogUtils.d(TAG, "s3 == s4：" + (s3 == s4));

        // 创建了2个对象，分别是 字符串常量池中的对象"7" 和 在堆中(s7引用指向)的对象(s7引用对象内容是"77")。但此时常量池中是没有对象"77"的。
        String s7 = new String("7") + new String("7");
        // 声明s8时，常量池中是不存在对象"77"的，但执行完毕后，"77"对象是s8声明创建的新对象
        String s8 = "77";
        // 常量池中对象"77"已经存在了，因此s7和s8的引用是不同的
        s7.intern();
        // s7 和 s8 地址不同 false
        LogUtils.d(TAG, "s7 == s8：" + (s7 == s8));
    }
}