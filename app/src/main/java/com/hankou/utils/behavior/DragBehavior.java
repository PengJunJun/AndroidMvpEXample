package com.hankou.utils.behavior;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
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
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        if (mTotalMoveY < 0 && dy < 0) {
            return;
        }
        mTotalMoveX += dx;
        mTotalMoveY += dy;
        mCurrAlpha = (float) mTotalMoveY / (float) (mDependencyHeight);
        child.setAlpha(1 - (Math.abs(mCurrAlpha)));
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        int minCompare = Math.min(mDependencyHeight, mDependencyWidth) / 2;
        if (Math.abs(mTotalMoveX) >= minCompare || Math.abs(mTotalMoveY) >= minCompare) {
            mDependencyView.onMoveOut();
        } else {
            if (mTotalMoveY > 0) {
                mDependencyView.offsetTopAndBottom(-mTotalMoveY);
                mDependencyView.offsetLeftAndRight(-mTotalMoveX);
                //dependencyTranslationAnimation(mDependencyView, 0, mTotalMoveX, 0, mTotalMoveY);
                //childAlphaAnimation(child, mCurrAlpha);
                child.setAlpha(1);
                mTotalMoveY = 0;
                mTotalMoveX = 0;
            }
        }
    }

    private void dependencyTranslationAnimation(View view, int fromX, int toX, int fromY, int toY) {
        //ObjectAnimator xAnimation = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, fromX, toX);
        //ObjectAnimator yAnimation = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, fromY, toY);
        //AnimatorSet animatorSet = new AnimatorSet();
        //animatorSet.playTogether(xAnimation, yAnimation);
        //animatorSet.start();
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
