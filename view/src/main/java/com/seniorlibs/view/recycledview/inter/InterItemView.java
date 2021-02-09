package com.seniorlibs.view.recycledview.inter;

import android.view.View;
import android.view.ViewGroup;

/**
 * 支持多种状态切换；支持上拉加载更多，下拉刷新；支持添加头部或底部view
 */
public interface InterItemView {

    /**
     * 创建view
     * @param parent            parent
     * @return                  view
     */
    View onCreateView(ViewGroup parent);

    /**
     * 绑定view
     * @param headerView        headerView
     */
    void onBindView(View headerView);
}
