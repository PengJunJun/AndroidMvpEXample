package com.hankou.utils.views;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ImageView;

/**
 * Created by pengjunjun on 2016/12/29.
 */

public class DragPhotoView extends ImageView implements NestedScrollingChild {
    private static final String TAG = "DrawPhotoView";
    private Context mContext;
    private int mTouchSlop;
    private NestedScrollingChildHelper mScrollingChildHelper;

    private float mDownX;
    private float mDownY;
    private float mMoveX;
    private float mMoveY;
    private float mTotalMoveX;
    private float mTotalMoveY;
    int[] location = new int[2];
    private final int[] consumed = new int[2];
    private final int[] offsets = new int[2];

    private OnPhotoMoveOutListener mPhotoMoveOutListener;

    public DragPhotoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragPhotoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledPagingTouchSlop();
        this.mContext = context;
        mScrollingChildHelper = new NestedScrollingChildHelper(this);
        mScrollingChildHelper.setNestedScrollingEnabled(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                dispatchOnStartNestScroll(ViewCompat.SCROLL_AXIS_NONE);
                break;
            case MotionEvent.ACTION_MOVE:
                mMoveX = event.getX() - mDownX;
                mMoveY = event.getY() - mDownY;
                mDownX = event.getX();
                mDownY = event.getY();
                event.offsetLocation(location[0], location[1]);
                if (dispatchOnNestPreScroll((int) mMoveX, (int) mMoveY, consumed, location)) {
                    mMoveX -= consumed[0];
                    mMoveY -= consumed[1];
                    event.offsetLocation(location[0], location[1]);
                    offsets[0] += location[0];
                    offsets[1] += location[1];
                }
                mTotalMoveX += mMoveX;
                mTotalMoveY += mMoveY;
                if (mTotalMoveY > 0) {
                    offsetTopAndBottom((int) (mMoveY));
                    offsetLeftAndRight((int) (mMoveX));
                }
                break;
            case MotionEvent.ACTION_UP:
                handlerUpEvent();
                break;
        }
        return true;
    }

    private void handlerUpEvent() {
        dispatchOnStopNestScroll();
        mMoveX = 0;
        mMoveY = 0;
        mDownY = 0;
        mMoveX = 0;
        mTotalMoveX = 0;
        mTotalMoveY = 0;
    }

    private boolean dispatchOnStartNestScroll(int axes) {
        return mScrollingChildHelper.startNestedScroll(axes);
    }

    private boolean dispatchOnNestPreScroll(int dx, int dy, int[] consume, int[] window) {
        return mScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consume, window);
    }

    private boolean dispatchOnNestScroll(int dx, int dy, int udx, int udy, int[] window) {
        return mScrollingChildHelper.dispatchNestedScroll(dx, dy, udx, udy, window);
    }

    private void dispatchOnStopNestScroll() {
        mScrollingChildHelper.stopNestedScroll();
    }

    public void onMoveOut() {
        if (mPhotoMoveOutListener != null) {
            mPhotoMoveOutListener.onMoveOut();
        }
    }

    public void setOnPhotoMoveOutListener(OnPhotoMoveOutListener onPhotoMoveOutListener) {
        this.mPhotoMoveOutListener = onPhotoMoveOutListener;
    }

    public interface OnPhotoMoveOutListener {
        void onMoveOut();
    }
}

