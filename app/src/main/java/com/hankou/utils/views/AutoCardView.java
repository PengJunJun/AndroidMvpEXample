package com.hankou.utils.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

/**
 * Created by bykj003 on 2016/10/19.
 */

public class AutoCardView extends CardView {

    private Context mContext;

    private int mScreenWidth;

    public AutoCardView(Context context) {
        this(context, null);
    }

    public AutoCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.mContext = context;
        mScreenWidth = mContext.getResources().getDisplayMetrics().widthPixels;
    }

    public AutoCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(MeasureSpec.makeMeasureSpec(mScreenWidth,MeasureSpec.EXACTLY), heightMeasureSpec);
//    }
}
