package com.seniorlibs.designpattern

import com.seniorlibs.designpattern.ch35v3.RandomIdGenerator
import org.junit.Assert
import org.junit.Test

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/9/20
 * Mender:
 * Modify:
 * Description: 第三轮重构：编写完善的单元测试
 */
class RandomIdGeneratorTest {

    @Test
    fun testGetLastSubstrSplittedByDot() {
        val idGenerator = RandomIdGenerator()
        var actualSubstr = idGenerator.getLastSubstrSplittedByDot("field1.field2.field3")
        Assert.assertEquals("field3", actualSubstr)

        actualSubstr = idGenerator.getLastSubstrSplittedByDot("field1")
        Assert.assertEquals("field1", actualSubstr)

        actualSubstr = idGenerator.getLastSubstrSplittedByDot("field1#field2#field3")
        Assert.assertEquals("field1#field2#field3", actualSubstr)
    }

    // 此单元测试会失败，因为我们在代码中没有处理hostName为null或空字符串的情况
    // 这部分优化留在第36、37节课中讲解
    @Test
    fun testGetLastSubstrSplittedByDot_nullOrEmpty() {
        val idGenerator = RandomIdGenerator()
        var actualSubstr = idGenerator.getLastSubstrSplittedByDot(null)
        Assert.assertNull(actualSubstr)

        actualSubstr = idGenerator.getLastSubstrSplittedByDot("")
        Assert.assertEquals("", actualSubstr)
    }

    @Test
    fun testGenerateRandomAlphameric() {
        val idGenerator = RandomIdGenerator()
        val actualRandomString = idGenerator.generateRandomAlphameric(6)
        Assert.assertNotNull(actualRandomString)
        Assert.assertEquals(6, actualRandomString.length)
        for (c in actualRandomString.toCharArray()) {
            Assert.assertTrue(c in '0'..'9' || c in 'a'..'z' || c in 'A'..'Z')
        }
    }

    // 此单元测试会失败，因为我们在代码中没有处理length<=0的情况
    // 这部分优化留在第36、37节课中讲解
    @Test
    fun testGenerateRandomAlphameric_lengthEqualsOrLessThanZero() {
        val idGenerator = RandomIdGenerator()
        var actualRandomString = idGenerator.generateRandomAlphameric(0)
        Assert.assertEquals("", actualRandomString)

        actualRandomString = idGenerator.generateRandomAlphameric(-1)
        Assert.assertNull(actualRandomString)
    }
}