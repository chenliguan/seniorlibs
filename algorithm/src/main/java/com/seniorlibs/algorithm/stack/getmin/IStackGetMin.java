package com.seniorlibs.algorithm.stack.getmin;

/**
 * 一个带getMin功能的栈接口
 */
public interface IStackGetMin<T> {

    /**
     * 添加数据
     */
    void addData();

    /**
     * push
     *
     * @param newItem
     * @return
     */
    T push(T newItem);

    /**
     * pop
     *
     * @return
     */
    T pop();

    /**
     * getMin
     *
     * @return
     */
    T getMin();
}
