package com.read.kotlinlib.jvm;

import java.util.ArrayList;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/2/17.
 * Mender:
 * Modify:
 * Description: 内存溢出
 */
public class OomError {

    public static void main() {
        ArrayList list = new ArrayList();
        while (true) {
            list.add(new OomError());
        }
    }
}
