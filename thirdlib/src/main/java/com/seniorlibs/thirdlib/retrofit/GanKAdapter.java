package com.seniorlibs.thirdlib.retrofit;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.seniorlibs.thirdlib.R;

import java.util.List;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/1/3
 * Mender:
 * Modify:
 * Description: 干货集中营Adapter
 */
public class GanKAdapter extends RecyclerView.Adapter {

    private List<GankEntry> mGankEntries;
    private int position;

    public void setData(List<GankEntry> gankEntries) {
        mGankEntries = gankEntries;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.gank_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        GankEntry entry = mGankEntries.get(position);

        ImageLoader.getInstance().displayImage(entry.url, viewHolder.mImageView);
        viewHolder.descText.setText(entry.desc);
        viewHolder.authorText.setText(entry.who);
        
//        setHasStableIds(true);
//        hasStableIds();
    }

    @Override
    public int getItemCount() {
        return mGankEntries == null ? 0 : mGankEntries.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView descText;
        public TextView authorText;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.gank_iamge);
            descText = (TextView) itemView.findViewById(R.id.desc);
            authorText = (TextView) itemView.findViewById(R.id.author);
        }
    }


    /**
     * 注意getItemId的使用
     */

    // Adapter.setHasStableIds(true);
    // mAdapter.hasStableIds()
    // 当设置了以上的 开关后，detachAndScrapAttachedViews -> scrapOrRecycleView 的 recycler.scrapView(view);
    // 将缓存到 mAttachedScrap，最后直接使用，不需要创建，绑定数据，非常优化喔！！
    @Override
    public long getItemId(int position) {
        GankEntry data = mGankEntries.get(position);
        return (data._id).hashCode();
    }

    // 当你的标题栏被修改了，调用 notifydatasetchanged 刷新.
    // 如果没有被更新，就会使用缓存，相当的优化.

    // 或者一般这样写
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
}
