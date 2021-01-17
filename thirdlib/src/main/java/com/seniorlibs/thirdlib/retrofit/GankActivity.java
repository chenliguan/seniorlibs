package com.seniorlibs.thirdlib.retrofit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.seniorlibs.thirdlib.R;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/1/3
 * Mender:
 * Modify:
 * Description: Retrofit + RxJava ＋ OkHttp测试
 */
public class GankActivity extends Activity {

    private GankLoader mGankLoader;
    private GanKAdapter mAdapter;

    public static void actionStart(Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, GankActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gank_activity);

        mGankLoader = new GankLoader();
        initView();
        getGankList();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.gank_recycler_view);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new MyItemDecoration());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new GanKAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    /**
     * 获取福利列表
     */
    @SuppressLint("CheckResult")
    private void getGankList() {
        mGankLoader.getGankList()
                .subscribe(new Consumer<List<GankEntry>>() {
                    @Override
                    public void accept(List<GankEntry> gankEntries) {
                        Log.i("FK", "gank size:" + gankEntries.size());
                        mAdapter.setData(gankEntries);
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    public static class MyItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0, 0, 20, 20);
        }
    }
}
