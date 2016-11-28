package com.hankou.utils.views;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bykj003 on 2016/8/10.
 */
public class AutoRecyclerAdapter extends RecyclerView.Adapter {

    private RecyclerView.Adapter mAdapter;

    private View mHeaderViews;

    private View mFooterViews;

    public static final int TYPE_HEADER_VIEW = 10;
    public static final int TYPE_FOOTER_VIEW = 11;

    private int mHeaderSize;
    private int mFooterSize;

    private AutoRecyclerView.OnItemClickListener mOnItemClickListener;

    public AutoRecyclerAdapter(RecyclerView.Adapter mAdapter, View mHeaderViews, View mFooterViews) {
        this.mAdapter = mAdapter;
        this.mHeaderViews = mHeaderViews;
        this.mFooterViews = mFooterViews;
        mHeaderSize = mHeaderViews != null ? 1 : 0;
        mFooterSize = mFooterViews != null ? 1 : 0;
    }

    public void setOnItemClickListener(AutoRecyclerView.OnItemClickListener itemClickListener) {
        this.mOnItemClickListener = itemClickListener;
    }

    public void addHeaderView(View view) {
        mHeaderSize = 1;
        mHeaderViews = view;
        notifyItemChanged(0);
    }

    public void addFooterView(View view) {
        mFooterSize = 1;
        mFooterViews = view;
        notifyDataSetChanged();
    }

    public void removeHeaderView() {
        mHeaderSize = 0;
        mHeaderViews = null;
        notifyDataSetChanged();
    }

    public void removeFooterView() {
        mFooterSize = 0;
        mFooterViews = null;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER_VIEW) {
            return new HeaderFooterHolder(mFooterViews);
        } else if (viewType == TYPE_HEADER_VIEW) {
            return new HeaderFooterHolder(mHeaderViews);
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderFooterHolder) {
        } else {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, position);
                    }
                }
            });
            mAdapter.onBindViewHolder(holder, position - mHeaderSize);
        }
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount() + mHeaderSize + mFooterSize;
    }

    @Override
    public long getItemId(int position) {
        if (position < mHeaderSize || position >= (mAdapter.getItemCount() + mHeaderSize)) {
            return -1;
        }
        return mAdapter.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position < 1 && mHeaderViews != null) {
            return TYPE_HEADER_VIEW;
        }
        if (position >= (mAdapter.getItemCount() + mHeaderSize) && mFooterViews != null) {
            return TYPE_FOOTER_VIEW;
        }
        return 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int viewType = getItemViewType(position);
                    if (viewType == TYPE_HEADER_VIEW || viewType == TYPE_FOOTER_VIEW) {
                        return gridManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
        mAdapter.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mAdapter.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (position < mHeaderSize || position >= (mAdapter.getItemCount() + mHeaderSize)) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null
                    && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p =
                        (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }

    public class HeaderFooterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public HeaderFooterHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
