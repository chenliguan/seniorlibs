package com.seniorlibs.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.IntRange;
import androidx.core.view.ViewCompat;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/1/3.
 * Mender:
 * Modify:
 * Description: 自定义View-学习onMeasure()和onDraw
 */
public class PieImageView extends View {

    private static final int MAX_PROGRESS = 100;
    private Paint mArcPaint;
    private RectF mBound;
    private Paint mCirclePaint;
    private int mProgress = 0;

    public PieImageView(Context context) {
        this(context, null, 0);
    }

    public PieImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setProgress(@IntRange(from = 0, to = MAX_PROGRESS) int mProgress) {
        this.mProgress = mProgress;
        ViewCompat.postInvalidateOnAnimation(this);
    }

    private void init() {
        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mArcPaint.setStrokeWidth(dpToPixel(0.1f, getContext()));
        mArcPaint.setColor(Color.RED);

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(dpToPixel(2, getContext()));
        mCirclePaint.setColor(Color.argb(120, 0xff, 0xff, 0xff));
        mBound = new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        // 判断是wrap_content的测量模式
        if (MeasureSpec.AT_MOST == widthMode || MeasureSpec.AT_MOST == heightMode) {
            int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
            int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);
            // 将宽高设置为传入宽高的最小值
            int size = measuredWidth > measuredHeight ? measuredHeight : measuredWidth;
            // 调用setMeasuredDimension设置View实际大小
            setMeasuredDimension(size, size);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int min = Math.min(w, h);
        int max = w + h - min;
        int r = Math.min(w, h) / 3;
        mBound.set((max >> 1) - r, (min >> 1) - r, (max >> 1) + r, (min >> 1) + r);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mProgress != MAX_PROGRESS && mProgress != 0) {
            float mAngle = mProgress * 360f / MAX_PROGRESS;
            canvas.drawArc(mBound, 270, mAngle, true, mArcPaint);
            canvas.drawCircle(mBound.centerX(), mBound.centerY(), mBound.height() / 2, mCirclePaint);
        }
    }

    private float scale = 0;

    private int dpToPixel(float dp, Context context) {
        if (scale == 0) {
            scale = context.getResources().getDisplayMetrics().density;
        }
        return (int) (dp * scale);
    }
}
