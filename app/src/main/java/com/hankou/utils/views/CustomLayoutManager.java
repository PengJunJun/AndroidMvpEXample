package com.hankou.utils.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bykj003 on 2016/12/19.
 */

public class CustomLayoutManager extends RecyclerView.LayoutManager {

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        return params;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        int itemCount = getItemCount();
        if (itemCount <= 0) {
            return;
        }
        int left = 0;
        int top = 0;
        int itemOffset = 0;
        for (int n = 0; n < itemCount; n++) {
            View view = recycler.getViewForPosition(n);
            addView(view);
            measureChildWithMargins(view, 0, 0);
            left = (getWidth() / 2 - view.getMeasuredWidth() / 2);
            top = (getHeight() / 2 - view.getMeasuredHeight() / 2 - itemOffset);
            layoutDecoratedWithMargins(view, left, top, left + view.getMeasuredWidth(), top + view.getMeasuredHeight());
            itemOffset += 20;
        }
    }
}
