package com.hankou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hankou.utils.views.AutoRecyclerBaseAdapter;

import java.util.List;

/**
 * Created by bykj003 on 2016/11/9.
 */

public abstract class BasePagerRecyclerAdapter<T extends List> extends RecyclerView.Adapter {

    public T mData;

    public Context mContext;

    public void setData(T data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(T data) {
        if (data != null && data.size() > 0) {
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        if (mData != null && mData.size() > 0) {
            return mData.size();
        }
        return 0;
    }

    public Object getItem(int position) {
        if (mData != null) {
            return mData.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
