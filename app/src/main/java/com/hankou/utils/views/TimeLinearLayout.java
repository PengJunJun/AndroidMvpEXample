package com.hankou.utils.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by bykj003 on 2017/1/4.
 */

public class TimeLinearLayout extends LinearLayout {

    public TimeLinearLayout(Context context) {
        this(context, null);
    }

    public TimeLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int childCount = getChildCount();
        if (childCount > 0) {
            int childHeight = getChildHeight();
            int top = getStartLayoutPos();
            int width = getMeasuredWidth();
            for (int n = 0; n < childCount; n++) {
                View child = getChildAt(n);
                child.layout(0, top, width, top + childHeight);
                top += childHeight;
            }
        }
    }

    public int getChildHeight() {
        int childCount = getChildCount();
        if (childCount > 0) {
            return getChildAt(0).getMeasuredHeight();
        }
        return 0;
    }

    public int getStartLayoutPos(){
        TimeSelectedView parent = (TimeSelectedView) getParent();
        return (parent.getMeasuredHeight() - getChildHeight()) / 2;
    }
}
