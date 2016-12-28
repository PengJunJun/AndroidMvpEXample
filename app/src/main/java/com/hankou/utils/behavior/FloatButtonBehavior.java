package com.hankou.utils.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.hankou.utils.views.AutoRecyclerView;

/**
 * Created by pjj on 2016/11/18.
 */

public class FloatButtonBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

    private static final String TAG = "FloatButtonBehavior";

    private boolean mIsShow = true;

    private int mScreenHeight;

    public FloatButtonBehavior() {
    }

    public FloatButtonBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScreenHeight = context.getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return dependency instanceof AutoRecyclerView;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                               View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        int diff = (mScreenHeight - child.getBottom());
        if (dyConsumed > 0) {
            child.animate().translationY(-(child.getMeasuredHeight() + diff));
        } else if (dyConsumed < 0) {
            child.animate().translationY((child.getMeasuredHeight() + diff));
        }
    }
}
