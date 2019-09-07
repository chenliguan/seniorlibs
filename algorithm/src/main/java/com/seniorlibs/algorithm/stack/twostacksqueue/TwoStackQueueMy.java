package com.seniorlibs.algorithm.stack.twostacksqueue;

import java.util.Stack;

/**
 * 两个栈实现队列（本人实现）
 */
public class TwoStackQueueMy implements ITwoStackQueue<Integer> {

    public final static String TAG = "TwoStackQueueMy";

    private Stack<Integer> mStackInput;
    private Stack<Integer> mStackOutput;

    public TwoStackQueueMy() {
        mStackInput = new Stack<>();
        mStackOutput = new Stack<>();
    }

    /**
     * 初始化数据
     */
    @Override
    public void addData() {
        add(1440);
        add(100);
        add(20);
        add(90);
        add(900);
        add(90);
        add(10);
        add(60);
        add(110);
        add(130);
    }

    /**
     * add
     *
     * @param newItem
     * @return
     */
    @Override
    public boolean add(Integer newItem) {
        Integer result = mStackInput.push(newItem);
        return newItem.equals(result);
    }

    /**
     * poll
     *
     * @return
     */
    @Override
    public Integer poll() {
        if (!mStackOutput.empty()) {
            return mStackOutput.pop();
        } else if (!mStackInput.empty()) {
            while(!mStackInput.empty()) {
                mStackOutput.push(mStackInput.pop());
            }
            return mStackOutput.pop();
        } else {
            throw new RuntimeException("TwoStackQueue.empty()");
        }
    }

    /**
     * peek
     *
     * @return
     */
    @Override
    public Integer peek() {
        if (!mStackOutput.empty()) {
            return mStackOutput.peek();
        } else if (!mStackInput.empty()) {
            while(!mStackInput.empty()) {
                mStackOutput.push(mStackInput.pop());
            }
            return mStackOutput.peek();
        } else {
            throw new RuntimeException("TwoStackQueue.empty()");
        }
    }
}
