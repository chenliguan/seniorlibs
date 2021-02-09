package com.seniorlibs.view.recycledview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.seniorlibs.baselib.utils.AppUtils;

import java.util.List;

public class NarrowImageAdapter extends RecyclerArrayAdapter<Integer> {

    public NarrowImageAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("NarrowImageAdapter", "OnCreateViewHolder + viewType：" + viewType);
        return new NarrowImageViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull List<Object> payloads) {
        Log.i("NarrowImageAdapter", "onBindViewHolder + position：" + position);
        super.onBindViewHolder(holder, position, payloads);
    }

    private static class NarrowImageViewHolder extends BaseViewHolder<Integer>{
        ImageView imgPicture;

        public NarrowImageViewHolder(ViewGroup parent) {
            super(new ImageView(parent.getContext()));
            imgPicture = (ImageView) itemView;
            imgPicture.setLayoutParams(new ViewGroup.LayoutParams((int) AppUtils.convertDpToPixel(72f,getContext()), ViewGroup.LayoutParams.MATCH_PARENT));
            imgPicture.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        @Override
        public void setData(Integer data) {
            imgPicture.setImageResource(data);
        }
    }
}
