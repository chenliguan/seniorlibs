package com.seniorlibs.baselib.hook;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.seniorlibs.baselib.utils.LogUtils;

import de.robv.android.xposed.XC_MethodHook;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/2/17
 * Mender:
 * Modify:
 * Description: 方法挂钩子类
 */
public class ImageHook extends XC_MethodHook {

    public static final String TAG = "BaseApplication";

    @Override
    protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        // 实现我们的逻辑
        ImageView imageView = (ImageView) param.thisObject;
        checkBitmap(imageView,((ImageView) param.thisObject).getDrawable());
    }

    /**
     * 检测图标宽高都大于view带下的2倍以上，则警告
     *
     * @param thiz
     * @param drawable
     */
    private static void checkBitmap(Object thiz, Drawable drawable) {
        if (drawable instanceof BitmapDrawable && thiz instanceof View) {
            final Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap != null) {
                final View view = (View) thiz;
                int width = view.getWidth();
                int height = view.getHeight();
                if (width > 0 && height > 0) {
                    // 图标宽高都大于view带下的2倍以上，则警告
                    if (bitmap.getWidth() >= (width << 1)
                            && bitmap.getHeight() >= (height << 1)) {
                        warn(bitmap.getWidth(), bitmap.getHeight(), width, height, new RuntimeException("Bitmap size too large"));
                    }
                } else {
                    final Throwable stackTrace = new RuntimeException();
                    view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            int w = view.getWidth();
                            int h = view.getHeight();
                            if (w > 0 && h > 0) {
                                if (bitmap.getWidth() >= (w << 1) && bitmap.getHeight() >= (h << 1)) {
                                    warn(bitmap.getWidth(), bitmap.getHeight(), w, h, stackTrace);
                                }
                                view.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                            return true;
                        }
                    });
                }
            }
        }
    }

    private static void warn(int bitmapWidth, int bitmapHeight, int viewWidth, int viewHeight, Throwable t) {
        String warnInfo = new StringBuilder("Bitmap size too large: ")
                .append("\n real size: (").append(bitmapWidth).append(',').append(bitmapHeight).append(')')
                .append("\n desired size: (").append(viewWidth).append(',').append(viewHeight).append(')')
                .append("\n call stack trace: \n").append(Log.getStackTraceString(t)).append('\n')
                .toString();

        LogUtils.e(TAG, warnInfo);
    }
}
