package com.hankou.utils.behavior;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Scroller;

import com.hankou.utils.views.AutoRecyclerView;

/**
 * Created by bykj003 on 2016/12/15.
 */

public class ImageBehavior extends CoordinatorLayout.Behavior<View> {

    public static final String TAG = "ImageBehavior";

    private int mChildHeight = 0;

    private Scroller mScroller;

    private Handler mHandler;

    private View mChild;

    private boolean mIsShow = true;

    public ImageBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        mHandler = new Handler();
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        mChildHeight = child.getMeasuredHeight();
        mChild = child;
        return dependency instanceof AutoRecyclerView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target,
                                  int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        if (dy < 0) {
            return;
        }
        float newTranslationY = (child.getTranslationY() - dy);
        if (newTranslationY >= (-mChildHeight)) {
            Log.i(TAG, "onNestedPreScroll()" + child.getTranslationY());
            child.setTranslationY(newTranslationY);
        }
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target,
                               int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyConsumed > 0) {
            return;
        }
        int disTranslation = ((int) child.getTranslationY() / 2 - dyConsumed);
        if (disTranslation < 0) {
            Log.i(TAG, "onNestedScroll():" + child.getTranslationY());
            child.setTranslationY(disTranslation);
        }
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        if (mIsShow) {
            if (child.getTranslationY() != 0 && Math.abs(child.getTranslationY()) <= mChildHeight) {
                mScroller.startScroll(0, (int) child.getTranslationY(),
                        0, -((int) child.getTranslationY() + mChildHeight) + (int) child.getTranslationY(), 2000);
                mHandler.removeCallbacks(runnable);
                mHandler.post(runnable);
            }
        } else {
            if (child.getTranslationY() < 0) {
                mScroller.startScroll(0, (int) child.getTranslationY() / 2,
                        0, (int) child.getTranslationY() / 2, 2000);
                mHandler.removeCallbacks(runnable);
                mHandler.post(runnable);
            }
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (mScroller.computeScrollOffset()) {
                mChild.setTranslationY(mScroller.getCurrY());
                mHandler.post(this);
                mIsShow = (!mIsShow);
                Log.i(TAG, "Run():" + mChild.getTranslationY());
            }
        }
    };
}
