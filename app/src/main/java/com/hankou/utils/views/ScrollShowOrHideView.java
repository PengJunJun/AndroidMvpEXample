package com.hankou.utils.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

/**
 * Created by bykj003 on 2016/12/7.
 */

public class ScrollShowOrHideView extends LinearLayout {

    private Context mContext;
    private int mTouchSlop;

    private View mChildView;

    private OnScrollListener mScrollListener;

    private float mStartX;

    private float mMoveX;

    private float mStartY;

    private float mMoveY;

    private float mTotalY;

    public ScrollShowOrHideView(Context context) {
        this(context, null);
    }

    public ScrollShowOrHideView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollShowOrHideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledPagingTouchSlop();
        initChild();
    }

    private void initChild() {
        if (getChildCount() > 1) {
            throw new RuntimeException("ScrollShowOrHideView can only one child");
        }
        mChildView = getChildAt(0);
    }

    public int getTotalScrollDistance() {
        return Math.abs((int) mTotalY);
    }

    public int getCurrOffsetY(){
        return (int)(mMoveY - mStartY);
    }

    public void setOnScrollListener(OnScrollListener listener) {
        this.mScrollListener = listener;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        boolean intercept = false;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                mTotalY = 0;
                mStartY = ev.getY();
                mStartX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (isScrollToTopOrBottom(mStartX, mStartY, ev.getX(), ev.getY())) {
                    intercept = true;
                }
                break;
        }
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mTotalY = 0;
                mStartY = event.getY();
                mStartX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                mMoveY = event.getY();
                mMoveX = event.getX();
                mTotalY += (mMoveY - mStartY);
                if (mScrollListener != null) {
                    mScrollListener.onScroll((int) (mMoveY - mStartY));
                }
                mStartY = mMoveY;
                break;
        }
        return true;
    }

    private boolean isScrollToTopOrBottom(float statX, float startY, float moveX, float moveY) {
        return Math.abs(moveY - startY) - Math.abs(moveX - statX) >= mTouchSlop;
    }

    public interface OnScrollListener {
        void onScroll(int scrollY);
    }
}
