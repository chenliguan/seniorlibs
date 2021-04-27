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

        store1();
        LogUtils.d(TAG, "========================================================");
        store2();
        LogUtils.d(TAG, "========================================================");
        store3();
        LogUtils.d(TAG, "========================================================");
        store4();
        LogUtils.d(TAG, "========================================================");
        intern1();
        LogUtils.d(TAG, "========================================================");
        intern2();
        LogUtils.d(TAG, "========================================================");
        intern3();
        LogUtils.d(TAG, "========================================================");
        intern4();
    }

    /**
     * 常量与常量的拼接，不使用 StringBuilder，结果在字符串常量池，原理是编译期优化
     */
    public void store1() {
        String s1 = "Ja" + "va";
        String s2 = "Java";
        LogUtils.d(TAG, "Ja" + "va：" + (s1 == s2)); // true
//        ldc "Java"
//        astore 1
//        ldc "Java"
//        astore 2
    }

    /**
     * 变量与常量的拼接，使用 StringBuilder，结果在堆中
     */
    public void store2() {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";    // 字符串常量池中的 "ab" 对象
        /*
        如下的 s1 + s2 的执行细节：(变量s是我临时定义的）
         StringBuilder s = new StringBuilder();
         s.append("a")
         s.append("b")
         s.toString()  --> 约等于 new StringBuilder("ab")
        */
        String s4 = s1 + s2; // 约等于 new String("ab")，堆上的 "ab" 对象
        LogUtils.d(TAG, "s3 == s4：" + (s3 == s4)); // false

//        ldc "a"
//        astore 1
//        ldc "b"
//        astore 2
//        ldc "ab"
//        astore 3
//        _new 'java/lang/StringBuilder'
//        dup
//        INVOKESPECIAL java/lang/StringBuilder.<init> ()V
//        aload 1
//        INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
//        aload 2
//        INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
//        INVOKEVIRTUAL java/lang/StringBuilder.toString ()Ljava/lang/String;
//        astore 4
    }

    /**
     * new String("c") 创建了几个对象？
     */
    public void store3() {
        String s = new String("ab");

//        _new 'java/lang/String'  1
//        dup
//        ldc "ab"  2

//        INVOKESPECIAL java/lang/String.<init> (Ljava/lang/String;)V
//        astore 1
//        return
    }

    /**
     * new String("a") + new String("b") 创建几个对象呢？ 5
     */
    public void store4() {
        String s = new String("a") + new String("b");

//        _new 'java/lang/StringBuilder' 1
//        dup
//        INVOKESPECIAL java/lang/StringBuilder.<init> ()V
//        _new 'java/lang/String'  2
//        dup
//        ldc "a"  3
//        INVOKESPECIAL java/lang/String.<init> (Ljava/lang/String;)V
//        INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
//        _new 'java/lang/String'  4
//        dup
//        ldc "b"  5
//        INVOKESPECIAL java/lang/String.<init> (Ljava/lang/String;)V
//        INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
//        INVOKEVIRTUAL java/lang/StringBuilder.toString ()Ljava/lang/String;
//        astore 1
//        return
    }


    /**
     * intern()的官方解释：
     * String在Java 6以后提供了intern()方法，目的是提示JVM把相应字符串缓存起来，以备重复使用。
     * 在我们创建字符串对象并调用intern()方法的时候，如果常量池中已经有缓存的字符串，就会返回缓存里的对象；
     * 否则将其缓存到常量池中，并返回对该对象。一般来说，JVM会将所有的类似 "abc" 这样的文本字符串，或者字符串常量之类缓存起来。
     * <p>
     * 对于任意两个字符串，当且仅当{@code s.equals(t)}为{@code true}时，{s.intern() == t.intern()}为{@code true}。
     */
    public void intern1() {
        String s = new String("a");
        String s1 = s.intern(); // 调用 s.intern() 之前，字符串常量池中已经存在了"a"
        String s2 = "a";
        // s  指向堆中 new String("a") 对象的内存地址；
        // s1 指向字符串常量池中 "a" 对象的内存地址；
        // s2 指向字符串常量池已存在的 "a" 的内存地址，所以 s1==s2；

        System.out.println(s == s2);  // jdk6：false   jdk7/8：false
        System.out.println(s1 == s2); // jdk6: true   jdk7/8：true
        System.out.println(System.identityHashCode(s));  // 33328697
        System.out.println(System.identityHashCode(s1)); // 83106942
        System.out.println(System.identityHashCode(s2)); // 83106942
    }

    /**
     * JDK6常量池是放在永久代中，永久代区和正常的堆区域是完全分开的，所以在常量池中创建一个新"33"对象，因此引用地址是不同的；
     * JDK7后常量池移到了堆上，常量池中不会重新创建对象了，会直接保存堆中 s3 指向的对象引用，因此引用地址是相同的。
     */
    public void intern2() {
        // s1 变量记录的地址为：new String("ab")。字符串常量池中，是否存在"ab"呢？答案：不存在！！
        String s1 = new String("a") + new String("b");

        // 在字符串常量池中生成"ab"。如何理解：
        // jdk6:创建了一个新的字符串对象 "ab"，也就有新的地址；
        // jdk7:此时字符串常量中并没有创建字符串对象 "ab"，而是创建一个引用，指向在堆上的 new String("ab")，将此引用返回
        s1.intern();
        // s2 变量记录的地址：使用的是上一行代码代码执行时，在常量池中生成的"ab"的地址
        String s2 = "ab";
        System.out.println(s1 == s2); // jdk6：false  jdk7/8：true
    }

    /**
     * 拓展
     */
    public void intern3() {
        // 约等于 new String("ab")。字符串常量池中，是否存在"11"呢？答案：不存在！！
        String s1 = new String("a") + new String("b");
        // 在字符串常量池中创建新字符串对象"11"。
        String s2 = "ab";
        // 字符串常量池中对象"11"已经存在了，不会再创建，相当于此行代码无效
        String s3 = s1.intern();
        System.out.println(s1 == s2); // false
        System.out.println(s3 == s2); // true
    }


    /**
     * 系统预装入到字符常量池的字符串，除了"Java"，类似的还有"false"，"true"(在java.lang.String里)
     * 通过 s.intern() 获取到的系统预装的字符串对象，不等于 堆上的 new String("false") 对象
     */
    public void intern4() {
        String s1 = new String("fal") + new String("se");
        String s2 = s1.intern();
        System.out.println((s1 == s2));  // false

        String s11 = new String("tr") + new String("ue");
        String s22 = s11.intern();
        System.out.println(s11 == s22);  // false

        String s33 = new String("Ja") + new String("va");
        String s44 = s33.intern();
        System.out.println(s33 == s44);  // false

        String s3 = new String("loo") + new String("ooo");
        String s4 = s3.intern();
        System.out.println(s3 == s4);    // true
    }



    private static class Instance {
        private Instance() {

        }
    }

    private static volatile Instance sInstance;

    public static Instance getInstance() {
        if (sInstance == null) {
            synchronized(Instance.class) {
                if (sInstance == null) {
                    sInstance = new Instance(); // 1 3 2
                }
            }
        }
        return sInstance;
    }
}