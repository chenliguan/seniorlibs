package com.seniorlibs.view.recycledview.inter;

/**
 *上拉加载更多异常监听
 */
public interface OnErrorListener {

    /**
     * 上拉加载，加载更多数据异常展示，这个方法可以暂停或者停止加载数据
     */
    void onErrorShow();
    /**
     * 这个方法是点击加载更多数据异常展示布局的操作，比如恢复加载更多等等
     */
    void onErrorClick();
}
