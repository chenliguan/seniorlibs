package com.read.kotlinlib.memory;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.read.kotlinlib.R;
import com.read.kotlinlib.memory.listener.RegionImage;

import java.io.IOException;
import java.io.InputStream;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/3/14
 * Mender:
 * Modify:
 * Description: BitmapRegionDecoder 图片分片显示
 */
public class BitmapRegionActivity extends AppCompatActivity {

    private static final String TAG = "BitmapRegionActivity";

    private RegionImage mRegionImage;

    public static void actionStart(Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, BitmapRegionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_region);
        mRegionImage = findViewById(R.id.regionImage);

        // showOriginalImage();
        mRegionImage.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom,
                                       int oldLeft, int oldTop, int oldRight, int oldBottom) {
                showRegionImage();
            }
        });
    }

    private void showRegionImage() {
        try {
            InputStream inputStream = getAssets().open("liaomei.png");
            mRegionImage.setInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
