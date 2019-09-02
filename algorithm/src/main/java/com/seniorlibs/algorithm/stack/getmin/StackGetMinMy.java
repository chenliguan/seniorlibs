package com.seniorlibs.algorithm.stack.getmin;

import android.util.Log;

import java.util.Stack;

/**
 * 一个带getMin功能的栈（本人实现，时间复杂度O(n)，不通过）
 */
public class StackGetMinMy implements IStackGetMin<Integer> {

    public final static String TAG = "StackGetMinMy";

    private Stack<Integer> mStackData;
    private Stack<Integer> mStackTemp;
    private Integer mTemp = Integer.MAX_VALUE;

    public StackGetMinMy() {
        mStackData = new Stack<>();
        mStackTemp = new Stack<>();
    }

    /**
     * 初始化数据
     */
    @Override
    public void addData() {
        mStackData.clear();
        mStackTemp.clear();

        mStackData.push(10);
        mStackData.push(30);
        mStackData.push(50);
        mStackData.push(2);
        mStackData.push(90);
        mStackData.push(100);
        mStackData.push(40);
        mStackData.push(40);
    }

    /**
     * push
     *
     * @param newItem
     * @return
     */
    @Override
    public Integer push(Integer newItem) {
        if (newItem == null) {
            return null;
        }

        return mStackData.push(newItem);
    }

    /**
     * pop
     *
     * @return
     */
    @Override
    public Integer pop() {
        if (mStackData.empty()) {
            return null;
        }

        return mStackData.pop();
    }

    /**
     * getMin
     *
     * @return
     */
    @Override
    public Integer getMin() {
        Integer temp = pop();
        while (temp != null) {
            Log.e(TAG, "mStackTemp + temp=" + temp);
            mStackTemp.push(temp);
            if (temp < mTemp) {
                mTemp = temp;
            }

            temp = pop();
        }

        // 恢复mStackData
        while (!mStackTemp.empty()) {
            Integer pop = mStackTemp.pop();
            push(pop);
            Log.e(TAG, "mStackTemp + pop=" + pop + " mStackData.size()=" + mStackData.size());
        }

//        while (!mStackData.empty()) {
//            Log.e(TAG, "mStackData :" + pop());
//        }

        return mTemp;
    }
}
