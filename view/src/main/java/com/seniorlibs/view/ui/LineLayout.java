package com.seniorlibs.view.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.seniorlibs.view.R;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2019/5/4
 * Mender:
 * Modify:
 * Description: 线栏布局-用于"发现/我的/设置/关于"
 */
public class LineLayout extends RelativeLayout {

    /**
     * 动态设置控件的id
     */
    private static final int ICON_ID = 100;
    private static final int TEXT_ID = 200;
    private static final int ARROW_ID = 300;
    private static final int END_TEXT_ID = 400;

    private Context mContext;
    private ImageView mIcon;
    private TextView mTvTitle;
    private TextView mTvEnd;
    private View mDivider;

    private int mBackgroundColor;
    private boolean mHasIcon = true;
    private int mTextColor;
    private int mTextSize;
    private String mTitleStr = "";
    private String mEndStr = "";
    private int mSrcIcon;
    private int mSrcPadding;
    private int mSrcWidth;
    private int mSrcHeight;
    private boolean mHasBorder = false;
    private int mPadding;

    public LineLayout(Context context) {
        super(context);
        init(context, null);
    }

    public LineLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * 初始化
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        mContext = context;

        initAttrs(context, attrs);

        // 通常：initViews()和addViews()二选一
        initViews();

        addViews();
    }

    /**
     * 初始化自定义属性
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        mBackgroundColor = ContextCompat.getColor(mContext, R.color.gray_f0f0f0);
        mSrcIcon = R.drawable.ic_launcher;
        mTextColor = ContextCompat.getColor(mContext, R.color.default_color);
        mTextSize = getResources().getDimensionPixelSize(R.dimen.small_text_size);
        mSrcWidth = LayoutParams.WRAP_CONTENT;
        mSrcHeight = LayoutParams.WRAP_CONTENT;
        mPadding = dp2px(mContext, 16);

        if (null != attrs && !isInEditMode()) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LineLayout);
            mTitleStr = a.getString(R.styleable.LineLayout_titleText);
            mBackgroundColor = a.getColor(R.styleable.LineLayout_backgroundColor, mBackgroundColor);
            mTextColor = a.getColor(R.styleable.LineLayout_textColor, mTextColor);
            mTextSize = a.getDimensionPixelSize(R.styleable.LineLayout_textSize, mTextSize);
            mEndStr = a.getString(R.styleable.LineLayout_endText);
            mSrcIcon = a.getResourceId(R.styleable.LineLayout_icon, mSrcIcon);
            mSrcPadding = a.getDimensionPixelSize(R.styleable.LineLayout_srcPadding, mSrcPadding);
            mSrcWidth = a.getDimensionPixelSize(R.styleable.LineLayout_srcWidth, mSrcWidth);
            mSrcHeight = a.getDimensionPixelSize(R.styleable.LineLayout_srcHeight, mSrcHeight);
            mHasBorder = a.getBoolean(R.styleable.LineLayout_border, false);
            mHasIcon = a.getBoolean(R.styleable.LineLayout_hasIcon, true);
            a.recycle();
        }
    }

    /**
     * 初始化布局View（如果需要通过布局方式获取View，可使用如下方法）
     */
    private void initViews() {
//        View root = LayoutInflater.from(mContext).inflate(R.layout.layout_simple_line, this, true);
//        TextView mTvContent = root.findViewById(R.id.tv_content);
    }

    /**
     * 动态添加View
     */
    private void addViews() {
        // 基础属性
        setBaseLayout();

        // icon
        addIcon();

        // text
        addTitleText();

        // arrow
        addArrow();

        // mEndStr
        addEndText();

        // divider
        addDivider();
    }

    /**
     * 设置基础属性
     */
    private void setBaseLayout() {
        setBackgroundColor(mBackgroundColor);
    }

    /**
     * 添加图标
     */
    private void addIcon() {
        LayoutParams iconLy = new LayoutParams(mSrcWidth, mSrcHeight);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            iconLy.addRule(ALIGN_PARENT_START);
        } else {
            iconLy.addRule(ALIGN_PARENT_LEFT);
        }
        iconLy.addRule(CENTER_VERTICAL);
        mIcon = new ImageView(mContext);
        mIcon.setLayoutParams(iconLy);
        mIcon.setPadding(mSrcPadding, mSrcPadding, mSrcPadding, mSrcPadding);
        mIcon.setImageResource(mSrcIcon);
        mIcon.setId(ICON_ID);
        if (!mHasIcon) {
            mIcon.setVisibility(View.GONE);
        }
        addView(mIcon);
    }

    /**
     * 添加标题文本
     */
    private void addTitleText() {
        LayoutParams textLy = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        if (mHasIcon) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                textLy.addRule(END_OF, ICON_ID);
            } else {
                textLy.addRule(RIGHT_OF, ICON_ID);
            }
            textLy.setMargins(mPadding, 0, 0, 0);
        }
        textLy.addRule(CENTER_VERTICAL);
        mTvTitle = new TextView(mContext);
        mTvTitle.setLayoutParams(textLy);
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        mTvTitle.setTextColor(mTextColor);
        mTvTitle.setText(mTitleStr);
        mTvTitle.setId(TEXT_ID);
        addView(mTvTitle);
    }

    /**
     * 添加行末文本
     */
    private void addEndText() {
        mTvEnd = new TextView(mContext);
        LayoutParams endTextLy = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            endTextLy.addRule(LEFT_OF, ARROW_ID);
        } else {
            endTextLy.addRule(START_OF, ARROW_ID);
        }
        endTextLy.setMargins(0, 0, mPadding, 0);
        endTextLy.addRule(CENTER_VERTICAL);
        mTvEnd.setLayoutParams(endTextLy);
        mTvEnd.setText(mEndStr);
        mTvEnd.setTextColor(mTextColor);
        mTvEnd.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        mTvEnd.setId(END_TEXT_ID);
        if (TextUtils.isEmpty(mEndStr)) {
            mTvEnd.setVisibility(GONE);
        }
        addView(mTvEnd);
    }

    /**
     * 添加箭头
     */
    private void addArrow() {
        LayoutParams arrowLy = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        arrowLy.addRule(CENTER_VERTICAL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            arrowLy.addRule(ALIGN_PARENT_END);
        } else {
            arrowLy.addRule(ALIGN_PARENT_RIGHT);
        }
        ImageView arrow = new ImageView(mContext);
        arrow.setImageResource(R.drawable.ic_forward_black);
        arrow.setLayoutParams(arrowLy);
        arrow.setPadding(0, 0, mPadding, 0);
        arrow.setId(ARROW_ID);
        addView(arrow);
    }

    /**
     * 添加分割线
     */
    private void addDivider() {
        mDivider = new View(mContext);
        LayoutParams dividerLy = new LayoutParams(LayoutParams.MATCH_PARENT, dp2px(mContext,1));
        dividerLy.addRule(ALIGN_PARENT_TOP);
        mDivider.setLayoutParams(dividerLy);
        mDivider.setBackgroundColor(ContextCompat.getColor(mContext, R.color.default_color));
        if (!mHasBorder) {
            mDivider.setVisibility(View.GONE);
        }
        setPadding(mPadding, 0, 0, 0);
        addView(mDivider);
    }


    /**
     * 设置标题颜色/尺寸/文本
     *
     * @param color
     */
    public void setTextColor(int color) {
        mTvTitle.setTextColor(color);
    }

    /**
     * 设置标题尺寸（dp单位）
     *
     * @param size
     */
    public void setTextSize(float size) {
        mTvTitle.setTextSize(size);
    }

    public void setText(CharSequence text) {
        mTvTitle.setText(text);
    }

    public void setTypeface(int style) {
        mTvTitle.setTypeface(Typeface.defaultFromStyle(style));
    }

    /**
     * 设置Icon图标
     *
     * @param resId
     */
    public void setIcon(int resId) {
        mIcon.setImageResource(resId);
    }

    /**
     * 设置item项末文本
     *
     * @param charSequence
     */
    public void setEndText(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            mTvEnd.setVisibility(GONE);
        } else {
            mTvEnd.setVisibility(VISIBLE);
            mTvEnd.setText(charSequence);
        }
    }

    public TextView getEndText() {
        return mTvEnd;
    }

    /**
     * dp转px
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dp2px(Context context, int dp) {
        if (context == null) {
            return 0;
        }
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 转换sp为px
     */
    public static int sp2px(Context context, float spValue) {
        if (context == null) {
            return 0;
        }
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
