package com.seniorlibs.view.lazyload;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seniorlibs.view.R;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/3/13.
 * Mender:
 * Modify:
 * Description: 懒加载Fragment父类
 */
public abstract class LazyloadFragment extends Fragment {

    /**
     * 父容器
     */
    private ViewGroup mViewRoot;
    /**
     * 是否已初始化View
     */
    private boolean mIsCreateView = false;
    /**
     * 是否可见
     */
    private boolean mIsVisible = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesTate) {
        View rootView = inflater.inflate(R.layout.layout_view_root, container, false);
        mViewRoot = rootView.findViewById(R.id.view_root);

        mIsCreateView = true;
        isCanLoadData();
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // mIsVisible这个boolean值表示：该fragment的ui，用户是否可见，获取该标志记录下来
        if (isVisibleToUser) {
            mIsVisible = true;
            isCanLoadData();
        } else {
            mIsVisible = false;
        }
    }

    /**
     * view初始化完成并且对用户可见
     */
    private void isCanLoadData() {
        if (mViewRoot != null && mIsCreateView && mIsVisible) {
            initBaseView(mViewRoot);
            lazyInitLoad();

            // 防止重复加载数据
            mIsCreateView = false;
            mIsVisible = false;
        }
    }

    /**
     * 动态加载布局
     *
     * @param viewRoot
     */
    private void initBaseView(ViewGroup viewRoot) {
        viewRoot.removeAllViews();
        View rootView = LayoutInflater.from(getActivity()).inflate(setContentView(), viewRoot, true);
        initView(rootView);
    }


    /**
     * 加载页面布局文件
     *
     * @return
     */
    protected abstract int setContentView();

    /**
     * 让布局中的view与fragment中的变量建立起映射
     * TextView tvInput = rootView.findViewById(R.id.tv_input);
     *
     * @param rootView
     */
    protected abstract void initView(View rootView);

    /**
     * 加载数据
     */
    protected abstract void lazyInitLoad();
}