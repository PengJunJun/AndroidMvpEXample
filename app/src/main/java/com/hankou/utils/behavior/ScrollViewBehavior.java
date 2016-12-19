package com.hankou.utils.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hankou.utils.views.AutoRecyclerView;
import com.hankou.utils.views.ScrollShowOrHideView;

/**
 * Created by pjj on 2016/12/7.
 */

public class ScrollViewBehavior extends CoordinatorLayout.Behavior<AutoRecyclerView> {

    public ScrollViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, AutoRecyclerView child, View dependency) {
        return dependency instanceof ScrollShowOrHideView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, AutoRecyclerView child, View dependency) {
        child.offsetTopAndBottom(1);
        return super.onDependentViewChanged(parent, child, dependency);
    }
}
