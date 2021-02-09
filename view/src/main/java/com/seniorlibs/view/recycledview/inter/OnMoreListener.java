package com.seniorlibs.view.recycledview.inter;

/**
 * 上拉加载更多监听
 */
public interface OnMoreListener {

    /**
     * 上拉加载更多操作
     */
    void onMoreShow();
    /**
     * 上拉加载更多操作，手动触发
     */
    void onMoreClick();

}
