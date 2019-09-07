package com.seniorlibs.algorithm.stack.twostacksqueue;

import java.util.Stack;

/**
 * 两个栈实现队列（学习官方后重新开发方案）
 */
public class TwoStackQueueOfficial implements ITwoStackQueue<Integer> {

    public final static String TAG = "TwoStackQueueOfficial";

    private Stack<Integer> mStackPush;
    private Stack<Integer> mStackPop;

    public TwoStackQueueOfficial() {
        mStackPush = new Stack<>();
        mStackPop = new Stack<>();
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
        Integer result = mStackPush.push(newItem);
        return newItem.equals(result);
    }

    /**
     * poll
     *
     * @return
     */
    @Override
    public Integer poll() {
        if (mStackPop.empty() && mStackPush.empty()) {
            throw new RuntimeException("TwoStackQueue.empty()");
        } else if(mStackPop.empty()) {
            while(!mStackPush.empty()) {
                mStackPop.push(mStackPush.pop());
            }
        }

        return mStackPop.pop();
    }

    /**
     * peek
     *
     * @return
     */
    @Override
    public Integer peek() {
        if (mStackPop.empty() && mStackPush.empty()) {
            throw new RuntimeException("TwoStackQueue.empty()");
        } else if(mStackPop.empty()) {
            while(!mStackPush.empty()) {
                mStackPop.push(mStackPush.pop());
            }
        }

        return mStackPop.peek();
    }
}
