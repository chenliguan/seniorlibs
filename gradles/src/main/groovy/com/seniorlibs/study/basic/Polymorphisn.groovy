package com.seniorlibs.study.basic

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/11/20.
 * Mender:
 * Modify:
 * Description: Groovy的多态的表现：分派
 */
public class Polymorphisn1 {
    public static void main() {
        // 方法分派
        SuperClass superClass = new SubClass()
        printHello(superClass)
        // System.out: SubClass + Hello Sub
    }

    public static void printHello(SuperClass superClass) {
        System.out.printIn("SuperClass + Hello " + superClass.getName())

    }

    public static void printHello(SubClass subClass) {
        System.out.println("SubClass + Hello " + subClass.getName())
    }
}

class SuperClass {
    public String getName() {
        return "Super"
    }
}

class SubClass extends SuperClass {
    public String getName() {
        return "Sub"
    }
}


// 执行
Polymorphisn1.main()
// SubClass + Hello Sub