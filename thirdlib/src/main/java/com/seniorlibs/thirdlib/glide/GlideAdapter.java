package com.seniorlibs.thirdlib.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.seniorlibs.thirdlib.R;
import com.seniorlibs.thirdlib.ThirdlibApplication;
import com.seniorlibs.thirdlib.retrofit.GankEntry;

import java.util.List;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/1/3
 * Mender:
 * Modify:
 * Description: 干货集中营 Adapter
 */
public class GlideAdapter extends RecyclerView.Adapter {

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

        Glide.with(ThirdlibApplication.getAppContext())
                .load(entry.url)
                .into(viewHolder.mImageView);

        Glide.with(ThirdlibApplication.getAppContext())
                .load(entry.url)
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .override(300, 300)
                .fitCenter()
                .centerCrop()
                .skipMemoryCache(true) // 跳过内存缓存
                .diskCacheStrategy(DiskCacheStrategy.ALL)       // 同时使用{@link #DATA}和{@link #RESOURCE}进行缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE)      // 不保存任何要缓存的图片
                .diskCacheStrategy(DiskCacheStrategy.DATA)      // 在解码之前，将检索到的(原来的全分辨率)的图片直接写到磁盘缓存中
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)  // 将资源解码后(减低分辨率后的)将其写入磁盘
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC) // 尝试根据数据源智能地选择策略
                .priority(Priority.HIGH)
                .into(viewHolder.mImageView);


        /**
         * 定义 CustomTarget、SimpleTarget 和 ViewTarget 用于自定义视图类
         */

        CustomTarget mTarget = new CustomTarget<Bitmap>() {
            /**
             * 资源加载完成后将调用的方法。
             */
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                viewHolder.mImageView.setImageBitmap(resource);
            }

            /**
             * 一个<b>强制性</ b>生命周期回调，当取消加载并释放其资源*时调用。
             */
            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        };

        Glide.with(ThirdlibApplication.getAppContext())
                .asBitmap()
                .load(entry.url)
                .into(mTarget);

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
