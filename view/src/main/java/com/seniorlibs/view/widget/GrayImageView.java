package com.seniorlibs.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * 只会ImageView
 */
public class GrayImageView extends AppCompatImageView {

    private Paint mPaint;

    public GrayImageView(Context context) {
        super(context, null);
    }

    public GrayImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        // 一个颜色矩阵 ColorMatrix，来处理图像的色彩效果。通过这个类，可以很方便地改变矩阵值来处理颜色效果
        ColorMatrix colorMatrix = new ColorMatrix();
        // 设置颜色的饱和度，参数代表设置颜色饱和度的值，当饱和度为0时，图像就变成灰度图像；1是原图。
        colorMatrix.setSaturation(0);
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.saveLayer(null, mPaint, Canvas.ALL_SAVE_FLAG);
        super.onDraw(canvas);
    }
}
