package com.seniorlibs.algorithm.string.anagram;

/**
 * 判断两个字符串是否互为变形词
 */
public interface IAnagram {

    /**
     * 初始化数据
     */
    void addData();

    /**
     * 判断两个字符串是否互为变形词
     *
     * @param str1
     * @param str2
     * @return
     */
    boolean isAnagram(String str1, String str2);
}
