package com.hankou.utils.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.hankou.common.view.MainActivity;

/**
 * Created by bykj003 on 2016/11/22.
 */

public class SlideView extends LinearLayout {

    private Context mContext;

    private int mTouchSlop;

    private int mScreentWidth;

    private OnStartFinishListener mStartListener;

    private float mStartX;

    private float mStartY;

    private float mMoveX;

    private float mMoveY;

    private float mTotalX;

    private boolean isIntercept;

    private boolean isCancelScroll;

    private Scroller mScroller;

    public SlideView(Context context) {
        this(context, null);
    }

    public SlideView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledPagingTouchSlop();
        mScreentWidth = context.getResources().getDisplayMetrics().widthPixels;
        mScroller = new Scroller(context);
    }

    private void initSlideParam() {
        mStartX = 0;
        mMoveX = 0;
        mTotalX = 0;
        isIntercept = false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mStartX = ev.getX();
                mStartY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mMoveX = ev.getX();
                mMoveY = ev.getY();
                if ((mMoveX - mStartX) > mTouchSlop &&
                        (mMoveX - mStartX) > (Math.abs(mMoveY - mStartY)) &&
                        mStartX <= mScreentWidth / 5 && !isCancelScroll) {
                    isIntercept = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                isIntercept = false;
                break;
            case MotionEvent.ACTION_CANCEL:
                isIntercept = false;
                break;
        }
        if (mContext instanceof MainActivity) {
            isIntercept = false;
        }
        return isIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                mMoveX = event.getX();
                mTotalX += (mMoveX - mStartX);
                if (mTotalX >= 0) {
                    scrollBy(-(int) (mMoveX - mStartX), 0);
                }
                mStartX = mMoveX;
                break;
            case MotionEvent.ACTION_UP:
                if (mTotalX >= mScreentWidth / 5) {
                    scrollBy(-(int) (mScreentWidth - mTotalX), 0);
                    if (mStartListener != null) {
                        mStartListener.onFinish();
                    }
                } else {
                    mScroller.startScroll(0, (int) mTotalX, 0, 0);
                    invalidate();
                }
                initSlideParam();
                break;
            case MotionEvent.ACTION_CANCEL:
                initSlideParam();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
        }
    }

    public void setOnFinishListener(OnStartFinishListener listener) {
        this.mStartListener = listener;
        if (listener != null) {
            isCancelScroll = listener.isCancelScroll();
        }
    }

    public interface OnStartFinishListener {
        void onFinish();

        boolean isCancelScroll();
    }
}
