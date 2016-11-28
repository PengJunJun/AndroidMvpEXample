package com.hankou.utils;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hankou.utils.views.AutoRecyclerView;

/**
 * Created by bykj003 on 2016/11/18.
 */

public class AnimationBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

    private boolean mIsShow = true;

    public AnimationBehavior() {
    }

    public AnimationBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                               View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (dyConsumed > 0) {
            //隐藏
            child.animate().translationY(-child.getMeasuredHeight()).setDuration(200).start();
        }
        if (dyConsumed < 0) {
            //显示
            child.animate().translationY(child.getMeasuredHeight()).setDuration(200).start();
        }
        Log.i("Behavior", "dyConsumed:" + dyConsumed + "dyUnconsumed:" + dyUnconsumed);
    }
}
