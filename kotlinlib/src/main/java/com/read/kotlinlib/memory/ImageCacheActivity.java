package com.read.kotlinlib.memory;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.LruCache;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.read.kotlinlib.R;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/3/14
 * Mender:
 * Modify:
 * Description: Bitmap 缓存
 */
public class ImageCacheActivity extends AppCompatActivity {

    private LruCache<String, Bitmap> bitmapCache;

    public static void actionStart(Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, ImageCacheActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_cache);

        int cacheSize = 20 * 1024 * 1024;  // 20M
        bitmapCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // 重写此方法来衡量每张图片的大小，默认返回图片数量。
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    return bitmap.getAllocationByteCount();
                } else {
                    return bitmap.getByteCount();
                }
            }
        };

    }

    public void addBitmapToCache(String key, Bitmap bitmap) {
        if (getBitmapFromCache(key) == null) {
            bitmapCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromCache(String key) {
        return bitmapCache.get(key);
    }
}
