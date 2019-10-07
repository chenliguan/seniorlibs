package com.seniorlibs.algorithm.string.anagram;

/**
 * 判断两个字符串是否互为变形词（本人实现）
 */
public class AnagramMy implements IAnagram {

    public final static String TAG = "AnagramMy";

    public AnagramMy() {

    }

    /**
     * 初始化数据
     */
    @Override
    public void addData() {

    }

    /**
     * 判断两个字符串是否互为变形词
     *
     * @param str1
     * @param str2
     * @return
     */
    @Override
    public boolean isAnagram(String str1, String str2) {
        // 如果str1和str2长度不同，直接返回false
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }

        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();

        // 每种字符的词频统计表
        int[] map = new int[256];
        for (int i = 0; i < char1.length; i++) {
            // 遍历到字符a，它的编码值为97，则map[97]++
            map[char1[i]]++;
        }

        for (int i = 0; i < char2.length; i++) {
            // 遍历到字符a，其编码值为97，则map[97]--，如果减少之后的值小于0则直接返回false
            map[char2[i]]--;
            if (map[char2[i]] < 0) {
                return false;
            }
        }
        return true;
    }
}
