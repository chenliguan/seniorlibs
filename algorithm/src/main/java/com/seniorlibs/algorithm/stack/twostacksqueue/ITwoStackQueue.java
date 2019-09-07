package com.seniorlibs.algorithm.stack.twostacksqueue;

/**
 * 两个栈实现队列的接口
 */
public interface ITwoStackQueue<E> {

    /**
     * 初始化数据
     */
    void addData();

    /**
     * add
     */
    boolean add(E e);

    /**
     * poll
     *
     * @return E
     */
    E poll();

    /**
     * peek
     *
     * @return E
     */
    E peek();
}
