package com.hankou.utils.behavior;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Scroller;

/**
 * Created by bykj003 on 2016/12/15.
 */

public class RecyclerViewBehavior extends CoordinatorLayout.Behavior<View> {

    public static final String TAG = "ImageBehavior";

    private Scroller mScroller;

    private Handler mHandler;

    private View mDependencyView;

    private int mDependencyHeight;

    private boolean isScrolling = false;

    public RecyclerViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        mHandler = new Handler();
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        mDependencyView = dependency;
        mDependencyHeight = dependency.getMeasuredHeight();
        return dependency instanceof ImageView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setTranslationY(mDependencyHeight + dependency.getTranslationY());
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
        mScroller.abortAnimation();
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
        float newTranslationY = (mDependencyView.getTranslationY() - dy);
        if (newTranslationY >= -mDependencyHeight) {
            mDependencyView.setTranslationY(newTranslationY);
            consumed[1] = dy;
            isScrolling = false;
        }
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target,
                               int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyUnconsumed > 0) {
            return;
        }
        float newTranslationY = mDependencyView.getTranslationY() - dyUnconsumed;
        if (newTranslationY <= 0) {
            mDependencyView.setTranslationY(newTranslationY);
            isScrolling = true;
        }
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        scrollToPosition();
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        scrollToPosition();
    }

    private void scrollToPosition() {
        int translationY = (int) mDependencyView.getTranslationY();
        int dy = 0;
        if (translationY == (-mDependencyHeight)) {
            return;
        }
        if (!isScrolling) {
            dy = -(mDependencyHeight + translationY);
        } else {
            dy = (-translationY);
        }
        mScroller.startScroll(0, (int) mDependencyView.getTranslationY(), 0, dy, 500);
        mHandler.post(runnable);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (mScroller.computeScrollOffset()) {
                mDependencyView.setTranslationY(mScroller.getCurrY());
                mHandler.post(this);
            }
        }
    };
}
