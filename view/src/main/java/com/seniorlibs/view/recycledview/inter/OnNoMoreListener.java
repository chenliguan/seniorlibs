package com.seniorlibs.view.recycledview.inter;

/**
 * 上拉加载没有更多数据监听
 */
public interface OnNoMoreListener {

    /**
     * 上拉加载，没有更多数据展示，这个方法可以暂停或者停止加载数据
     */
    void onNoMoreShow();

    /**
     * 这个方法是点击没有更多数据展示布局的操作，比如可以做吐司等等
     */
    void onNoMoreClick();

}
