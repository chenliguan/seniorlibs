package com.seniorlibs.algorithm.stack.getmin;

import java.util.Stack;

/**
 * 一个带getMin功能的栈（学习官方后重新开发方案A，时间复杂度O(1)，通过）
 */
public class StackGetMinOfficialA implements IStackGetMin<Integer> {

    public final static String TAG = "StackGetMinOfficialA";

    private Stack<Integer> mStackData;
    private Stack<Integer> mStackMin;

    public StackGetMinOfficialA() {
        mStackData = new Stack<>();
        mStackMin = new Stack<>();
    }

    /**
     * 初始化数据
     */
    @Override
    public void addData() {
        push(1440);
        push(100);
        push(20);
        push(20);
        push(90);
        push(900);
        push(90);
        push(10);
        push(60);
        push(90);
        push(60);
        push(30);
        push(700);
        push(600);
        push(60);
        push(60);
    }

    /**
     * push
     *
     * @param newItem
     * @return
     */
    @Override
    public Integer push(Integer newItem) {
        if (mStackMin.empty()) {
            mStackMin.push(newItem);
        } else if (newItem < mStackMin.peek()) {
            mStackMin.push(newItem);
        }

        mStackData.push(newItem);
        return newItem;
    }

    /**
     * pop
     *
     * @return
     */
    @Override
    public Integer pop() {
        if (mStackData.empty()) {
            throw new RuntimeException("mStackData.empty()");
        }

        int value = mStackData.peek();
        if (value == getMin()) {
            mStackMin.pop();
        }

        return value;
    }

    /**
     * getMin
     *
     * @return
     */
    @Override
    public Integer getMin() {
        if (mStackMin.empty()) {
            throw new RuntimeException("mStackMin.empty()");
        }

        return mStackMin.peek();
    }
}
