package com.hankou.utils.behavior;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.hankou.utils.views.AutoRecyclerView;
import com.roughike.bottombar.BottomBar;

/**
 * Created by pjj on 2016/11/18.
 * <p>
 * 自定义的Behavior,
 * child  使用了当前Behavior的控件(此处就是BottomBar),
 * dependency child需要监听的view,以便child随着view嵌套滑动.
 * </p>
 */

public class BottomBarBehavior extends CoordinatorLayout.Behavior<BottomBar> {

    private static final String TAG = "BottomBarBehavior";

    public BottomBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, BottomBar child, View dependency) {
        return dependency instanceof AutoRecyclerView;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, BottomBar child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, BottomBar child,
                               View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (dyConsumed > 0) {
            child.animate().translationY((child.getMeasuredHeight())).setDuration(400).start();
        } else if (dyConsumed < 0) {
            child.animate().translationY(-(0)).setDuration(400).start();
        }
    }
}
