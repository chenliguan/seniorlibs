package com.read.kotlinlib.generic;

import java.util.List;
import java.util.Map;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/11/22.
 * Mender:
 * Modify:
 * Description: 泛型类
 */
public abstract class GenericClass<T> {
    Class<T> type;

    public GenericClass() {
        this.type = (Class<T>) getClass();
    }

    public List<Map<T, T>> getValue() {
        return null;
    }
}
