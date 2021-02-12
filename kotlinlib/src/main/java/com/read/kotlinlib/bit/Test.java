package com.read.kotlinlib.bit;

import java.io.Serializable;

public class Test implements Serializable, Cloneable {
    private int num = 1;
    private String str = "abc";

    public int add(int i) {
        int j = 10;
        num = num + i;
        return num;
    }
}
