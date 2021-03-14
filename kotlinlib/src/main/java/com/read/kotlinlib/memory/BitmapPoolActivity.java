package com.read.kotlinlib.memory;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.read.kotlinlib.R;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/3/14
 * Mender:
 * Modify:
 * Description: Bitmap 复用
 */
public class BitmapPoolActivity extends AppCompatActivity {

    private static final String TAG = "BitmapPoolActivity";

    private ImageView poolImage;
    private Bitmap reuseBitmap;
    private int resIndex;
    private int[] resIds = {R.drawable.rodman, R.drawable.rodman2};

    public static void actionStart(Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, BitmapPoolActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool);

        initView();

        initBitmap();
    }

    private void initView() {
        poolImage = findViewById(R.id.poolImage);
    }

    private void initBitmap() {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        // 将 Options.inMutable 置为 true，这里如果不置为 true 的话，BitmapFactory 将不会重复利用 Bitmap 内存
        options.inMutable = true;
        reuseBitmap = BitmapFactory.decodeResource(getResources(), resIds[0], options);
    }



    /**
     * 切换监听
     *
     * @param view
     */
    public void switchImage(View view) {
        // 1.每次调用 switchImage 切换图片时，都需要通过 BitmapFactory 创建一个新的 Bitmap 对象。
        // 当方法执行完毕后，这个 Bitmap 又会被 GC 回收，这就造成不断地创建和销毁比较大的内存对象，从而导致频繁 GC（或者叫内存抖动）。
//        poolImage.setImageBitmap(getOldBitmap());

        // 2.重复利用已经占用内存的 Bitmap 空间，具体做法就是使用 Options.inBitmap 参数
        poolImage.setImageBitmap(getBitmap());
    }


    private Bitmap getOldBitmap() {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        return BitmapFactory.decodeResource(getResources(), resIds[resIndex % 2], options);
    }

    private Bitmap getBitmap() {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        // inJustDecodeBounds设置为true，让解析方法禁止为bitmap分配内存，在加载图片之前就获取到图片的长宽值和MIME类型，
        // 返回值也是null，但是BitmapFactory.Options的outWidth、outHeight和outMimeType属性都会被赋值。
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), resIds[resIndex % 2], options);
        if (canUseForInBitmap(reuseBitmap, options)) {
            Log.e(TAG, "reuseBitmap is reusable");
            options.inMutable = true;
            options.inBitmap = reuseBitmap;
        }
        // inJustDecodeBounds设置为false，就可以得到压缩后的图片了。
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(getResources(), resIds[resIndex++ % 2], options);
    }

    public static boolean canUseForInBitmap(Bitmap candidate, BitmapFactory.Options targetOptions) {
        int width = targetOptions.outWidth / Math.max(targetOptions.inSampleSize, 1);
        int height = targetOptions.outHeight / Math.max(targetOptions.inSampleSize, 1);
        int byteCount = width * height * getBytesPerPixel(candidate.getConfig());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return byteCount <= candidate.getAllocationByteCount();
        } else {
            return byteCount <= candidate.getByteCount();
        }
    }

    private static int getBytesPerPixel(Bitmap.Config config) {
        int bytesPerPixel;
        switch (config) {
            case ALPHA_8:
                bytesPerPixel = 1;
                break;
            case RGB_565:
            case ARGB_4444:
                bytesPerPixel = 2;
                break;
            default:
                bytesPerPixel = 4;
                break;
        }
        return bytesPerPixel;
    }
}
