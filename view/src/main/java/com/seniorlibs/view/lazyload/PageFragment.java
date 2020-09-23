package com.seniorlibs.view.lazyload;

import android.view.View;

import com.seniorlibs.view.R;

/**
 * Author: chen
 * Version: 1.28
 * Date: 2020/3/13.
 * Mender:
 * Modify:
 * Description: 懒加载Fragment
 */
public class PageFragment extends LazyloadFragment {

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    /**
     * 加载页面布局文件
     *
     * @return
     */
    @Override
    protected int setContentView() {
        return R.layout.fragment_frg_app;
    }

    /**
     * 让布局中的view与fragment中的变量建立起映射
     * TextView tvInput = rootView.findViewById(R.id.tv_input);
     *
     * @param rootView
     */
    @Override
    protected void initView(View rootView) {

    }

    /**
     * 加载数据
     */
    @Override
    protected void lazyInitLoad() {

    }
}
