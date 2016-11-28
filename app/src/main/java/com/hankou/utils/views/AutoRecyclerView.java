package com.hankou.utils.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hankou.R;

/**
 * Created by 彭竣竣 on 2016/8/10.
 */
public class AutoRecyclerView extends RecyclerView {

    private static final String TAG = "AutoRecyclerView";

    private Context mContext;
    private AutoRecyclerAdapter mAdapter;
    private View mHeadView;
    private View mFootView;

    /**
     * 当前滑动的状态
     */
    private int currentScrollState = 0;

    /**
     * 最后一个可见的item的位置
     */
    private int lastVisibleItemPosition;

    /**
     * layoutManager的类型（枚举）
     */
    protected LAYOUT_MANAGER_TYPE layoutManagerType;

    /**
     * 最后一个的位置
     */
    private int[] lastPositions;

    public static enum LAYOUT_MANAGER_TYPE {
        LINEAR,
        GRID,
        STAGGERED_GRID
    }

    private OnLoadMoreListener mOnLoadMoreListener;

    private OnItemClickListener mOnItemClickListener;

    private boolean mIsNeedLoadMore = false;

    private DataObserver mDataObserver = new DataObserver();

    public AutoRecyclerView(Context context) {
        this(context, null);
    }

    public AutoRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    private void initFooterView(Context context) {
        if (mFootView == null) {
            mFootView = LayoutInflater.from(context).inflate(R.layout.layout_load_more_view, null);
            //mProgressView = (MaterialProgressView) mFootView.findViewById(R.id.progress_view);
            //mTvLoadMessage = (TextView) mFootView.findViewById(R.id.tv_content);
        }
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        final LayoutManager layoutManager = getLayoutManager();
        if (layoutManagerType == null) {
            if (layoutManager instanceof LinearLayoutManager) {
                layoutManagerType = LAYOUT_MANAGER_TYPE.LINEAR;
            } else if (layoutManager instanceof GridLayoutManager) {
                layoutManagerType = LAYOUT_MANAGER_TYPE.GRID;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                layoutManagerType = LAYOUT_MANAGER_TYPE.STAGGERED_GRID;
            } else {
                throw new RuntimeException(
                        "Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
            }
        }

        switch (layoutManagerType) {
            case LINEAR:
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                break;
            case GRID:
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                break;
            case STAGGERED_GRID:
                StaggeredGridLayoutManager staggeredGridLayoutManager
                        = (StaggeredGridLayoutManager) layoutManager;
                if (lastPositions == null) {
                    lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                lastVisibleItemPosition = findMax(lastPositions);
                break;
        }
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        currentScrollState = state;
        final LayoutManager layoutManager = getLayoutManager();
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        if ((visibleItemCount > 0 && currentScrollState == RecyclerView.SCROLL_STATE_IDLE &&
                (lastVisibleItemPosition) >= totalItemCount - 1)) {
            if (mOnLoadMoreListener != null && mIsNeedLoadMore) {
                mOnLoadMoreListener.onLoad();
            }
        }
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        mAdapter = new AutoRecyclerAdapter(adapter, mHeadView, mFootView);
        super.setAdapter(mAdapter);
        adapter.registerAdapterDataObserver(mDataObserver);
        mDataObserver.onChanged();
    }

    public void addHeaderView(View view) {
        mHeadView = view;
        if (mAdapter != null) {
            mAdapter.addHeaderView(mHeadView);
        }
    }

    public void addFooterView(View view) {
        mFootView = view;
        if (mAdapter != null) {
            mAdapter.addFooterView(mFootView);
        }
    }

    public void removeHeaderView() {
        mHeadView = null;
        if (mAdapter != null) {
            mAdapter.removeHeaderView();
        }
    }

    public void removeFooterView() {
        mFootView = null;
        if (mAdapter != null) {
            mAdapter.removeFooterView();
        }
    }

    public View getHeadView() {
        return mHeadView;
    }

    public View getFootView() {
        return mFootView;
    }

    public void onLoadComplete(boolean isLastPager) {
        if (isLastPager) {
            if (mIsNeedLoadMore == true)
                mIsNeedLoadMore = false;
            removeFooterView();
        } else {
            if (mIsNeedLoadMore == false)
                mIsNeedLoadMore = true;
            initFooterView(mContext);
            addFooterView(mFootView);
        }
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    public void onLoadComplete() {
        if (mIsNeedLoadMore == true)
            mIsNeedLoadMore = false;
        if (mFootView != null) {
            removeFooterView();
        }
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.mOnLoadMoreListener = loadMoreListener;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mOnItemClickListener = itemClickListener;
        if (mAdapter != null) {
            mAdapter.setOnItemClickListener(mOnItemClickListener);
        }
    }

    public interface OnLoadMoreListener {
        void onLoad();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private class DataObserver extends AdapterDataObserver {
        @Override
        public void onChanged() {
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            mAdapter.notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            mAdapter.notifyItemRangeChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            mAdapter.notifyItemRangeChanged(positionStart, itemCount, payload);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            mAdapter.notifyItemRangeRemoved(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            mAdapter.notifyItemMoved(fromPosition, toPosition);
        }
    }

}
