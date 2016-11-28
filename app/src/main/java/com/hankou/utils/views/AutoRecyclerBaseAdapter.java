package com.hankou.utils.views;

import android.support.v7.widget.RecyclerView;

/**
 * Created by bykj003 on 2016/9/23.
 */

public abstract class AutoRecyclerBaseAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T>{

    public abstract Object getItem(int position);
}
