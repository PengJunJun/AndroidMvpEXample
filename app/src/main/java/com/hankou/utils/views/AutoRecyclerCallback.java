package com.hankou.utils.views;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by bykj003 on 2016/9/21.
 */
public class AutoRecyclerCallback<T extends RecyclerView.Adapter> extends ItemTouchHelper.SimpleCallback {

    private T mAdapter;

    public AutoRecyclerCallback(int dragDirs, int swipeDirs, T adapter) {
        super(dragDirs, swipeDirs);
        this.mAdapter = adapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int tempPosition = viewHolder.getAdapterPosition();
        int targetPosition = target.getAdapterPosition();
        mAdapter.notifyItemMoved(tempPosition, targetPosition);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
    }
}
