package com.seniorlibs.view.recycledview;

import androidx.recyclerview.widget.GridLayoutManager;

import com.seniorlibs.view.recycledview.inter.InterItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义SpanSizeLookup
 */
public class GridSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private int mMaxCount;
    private ArrayList<InterItemView> headers;
    private ArrayList<InterItemView> footers;
    private List<Object> mObjects;


    public GridSpanSizeLookup(int maxCount, ArrayList<InterItemView> headers,
                              ArrayList<InterItemView> footers, List<Object> objects) {
        this.mMaxCount = maxCount;
        this.headers = headers;
        this.footers = footers;
        this.mObjects = objects;
    }

    /**
     * 该方法的返回值就是指定position所占的列数
     *
     * @param position 指定索引
     * @return 列数
     */
    @Override
    public int getSpanSize(int position) {
        //如果有headerView，则
        if (headers.size() != 0) {
            if (position < headers.size()) {
                return mMaxCount;
            }
        }
        //如果有footerView，则
        if (footers.size() != 0) {
            int i = position - headers.size() - mObjects.size();
            if (i >= 0) {
                return mMaxCount;
            }
        }
        return 1;
    }

}
