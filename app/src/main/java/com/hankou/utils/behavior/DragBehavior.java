package com.hankou.utils.behavior;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;

import com.hankou.utils.views.DragPhotoView;

/**
 * Created by bykj003 on 2016/11/18.
 */

public class DragBehavior extends CoordinatorLayout.Behavior {
    private static final String TAG = "DragBehavior";
    private int mScreenHeight;
    private int mScreenWidth;
    private Rect mPrevRect;
    private int mDependencyHeight;
    private int mDependencyWidth;
    private DragPhotoView mDependencyView;
    private int mTotalMoveY;
    private int mTotalMoveX;
    private float mCurrAlpha;
    private int mStartX;
    private int mStartY;

    public DragBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        mScreenHeight = metrics.heightPixels;
        mScreenWidth = metrics.widthPixels;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        if (dependency instanceof DragPhotoView) {
            mPrevRect = new Rect(dependency.getLeft(), dependency.getTop(), dependency.getRight(), dependency.getBottom());
            mDependencyHeight = dependency.getMeasuredHeight();
            mDependencyWidth = dependency.getMeasuredWidth();
            mDependencyView = (DragPhotoView) dependency;
            mStartX = (mScreenWidth - mDependencyWidth) / 2;
            mStartY = (mScreenHeight - mDependencyHeight) / 2;
            return true;
        }
        return false;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_NONE;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        mTotalMoveX += dx;
        mTotalMoveY += dy;
        mCurrAlpha = (float) mTotalMoveY / (float) (mDependencyHeight);
        child.setAlpha(1 - (Math.min(1, Math.abs(mCurrAlpha))));
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        int minCompare = Math.min(mDependencyHeight, mDependencyWidth) * 2 / 3;
        if (Math.abs(mTotalMoveY) >= minCompare) {
            mDependencyView.onMoveOut();
        } else {
            mDependencyView.offsetLeftAndRight(-mTotalMoveX);
            mDependencyView.offsetTopAndBottom(-mTotalMoveY);
            child.setAlpha(1);
        }
        mTotalMoveY = 0;
        mTotalMoveX = 0;
    }

    private void dependencyTranslationAnimation(View view, int fromX, int toX, int fromY, int toY) {
        TranslateAnimation animation = new TranslateAnimation(fromX, toX, fromY, toY);
        animation.setDuration(100);
        view.startAnimation(animation);
    }

    private void childAlphaAnimation(View child, float fromAlpha) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(child, "alpha", fromAlpha, 1);
        alpha.setDuration(100);
        alpha.start();
    }
}
