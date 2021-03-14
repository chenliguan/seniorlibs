package com.read.kotlinlib.memory;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.read.kotlinlib.R;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/1/3
 * Mender:
 * Modify:
 * Description: 修改图片加载的 Config 实现 Bitmap 压缩
 */
public class BitmapActivity extends AppCompatActivity {

    private static final String TAG = "BitmapActivity";

    private ImageView mImageView;

    public static void actionStart(Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, BitmapActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        initView();

        initBitmap();
    }

    private void initView() {
        mImageView = findViewById(R.id.image);
    }

    private void initBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inSampleSize = 2;

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.rodman, options);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Log.i(TAG, "bitmap size is " + bitmap.getAllocationByteCount());
        } else {
            Log.i(TAG, "bitmap size is " + bitmap.getByteCount());
        }
        Log.i(TAG, "realBitmapSize size is " + getRealBitmapCount(600, 600));

        mImageView.setImageBitmap(bitmap);
    }

    /**
     * 计算获取实际 Bitmap 大小
     *
     * @param bitmapWidth
     * @param bitmapHeight
     * @return
     */
    private int getRealBitmapCount(int bitmapWidth, int bitmapHeight) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float scale = displayMetrics.densityDpi / 320f;
        return (int) (bitmapWidth * scale * bitmapHeight * scale * 4);
    }
}
