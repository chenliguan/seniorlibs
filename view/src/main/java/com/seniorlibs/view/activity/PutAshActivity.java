package com.seniorlibs.view.activity;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.seniorlibs.view.R;

/**
 * App页面置灰
 *
 * 参考：https://opensource.sensorsdata.cn/question-daily/2020-04-10/
 *      https://mp.weixin.qq.com/s/EioJ8ogsCxQEFm44mKFiOQ
 */
public class PutAshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_ash);

        putAsh();
    }

    /**
     * DecorView是Activity窗口的根视图，根视图通过ColorMatrix设置置灰应该可以在全部子元素有效。
     */
    private void putAsh() {
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        // 一般选择硬件绘制还是软件绘制会调用下面的API：
        getWindow().getDecorView().setLayerType(View.LAYER_TYPE_HARDWARE, paint);
    }
}
