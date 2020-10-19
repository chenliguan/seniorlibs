package com.read.kotlinlib.bit;

public class ListViewEx2 {
    public void print() {
        String s1 = "Ja" + "va";
        String s2 = "Java";
        System.out.println(s1 == s2);

        String s3 = new String("Android");
        byte[] bytes = null;  // 读取文件才是最真实模拟在堆的场景
        String s4 = new String(bytes);
    }
}
