package com.hankou.utils.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bykj003 on 2016/12/5.
 */

public class TestViewGroup extends ViewGroup {

    private static final String TAG = "TouchEvent";

    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TestViewGroup(Context context) {
        this(context, null);
    }

    public TestViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left = 0;
        int top = 0;
        for (int n = 0; n < childCount; n++) {
            View child = getChildAt(n);
            left = getPaddingLeft();
            top = getPaddingTop();
            child.layout(left, top, left + child.getMeasuredWidth() - getPaddingRight(), top + child.getMeasuredHeight());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        for (int n = 0; n < childCount; n++) {
            View child = getChildAt(n);
            measureChild(child, MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.AT_MOST),
                    MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.AT_MOST));
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "parent down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "parent move");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "parent up");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i(TAG, "parent cancel");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "parent dispatch");
        return super.dispatchTouchEvent(ev);
    }
}
