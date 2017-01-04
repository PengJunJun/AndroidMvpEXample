package com.hankou.utils.views;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hankou.R;
import com.hankou.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pjj on 2017/1/4.
 */

public class TimeSelectedView extends ScrollView {

    private static final String TAG = "TimeSelectedView";
    private Context mContext;
    private ItemInfo mCurrItem;
    private List<ItemInfo> mItemInfoList;
    private LayoutInflater mLayoutInflater;
    private int mTouchSlop;
    private TimeLinearLayout mParentContainer;
    private int mChildItemHeight;

    private float mDownY;
    private float mMoveY;
    private float mTotalMoveY;

    public TimeSelectedView(Context context) {
        this(context, null);
    }

    public TimeSelectedView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeSelectedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        mItemInfoList = new ArrayList<>();
        mLayoutInflater = LayoutInflater.from(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledPagingTouchSlop();
        addFirstChild();
    }

    private void addFirstChild() {
        mParentContainer = new TimeLinearLayout(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mParentContainer.setLayoutParams(params);
        mParentContainer.setOrientation(LinearLayout.VERTICAL);
        this.addView(mParentContainer, 0);
    }

    private View getItemView() {
        return mLayoutInflater.inflate(R.layout.item_selected_view, null);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            requestDisallowInterceptTouchEvent(true);
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDownY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                handlerMoveEvent(event);
                break;
            case MotionEvent.ACTION_UP:
                mDownY = 0;
                mMoveY = 0;
                break;
        }
        return true;
    }

    private void handlerMoveEvent(MotionEvent event) {
        mMoveY = event.getY();
        mTotalMoveY += (mMoveY - mDownY);
        smallScrollToDistance(-(int) (mMoveY - mDownY));
        mDownY = mMoveY;
    }

    public void addItem(String data) {
        if (StringUtils.isEmpty(data)) {
            return;
        }
        List<String> item = new ArrayList<>();
        item.add(data);
        addItem(item);
    }

    public void addItem(List<String> items) {
        if (items == null || items.size() == 0) {
            return;
        }
        for (int n = 0; n < items.size(); n++) {
            ItemInfo itemInfo = new ItemInfo(n, items.get(n), getItemView());
            mItemInfoList.add(itemInfo);
            if (n == 0) {
                mCurrItem = itemInfo;
                startScaleAnimation(itemInfo.view);
            }
        }
        requestDisplayItems();
    }

    private void requestDisplayItems() {
        for (int n = 0; n < mItemInfoList.size(); n++) {
            ItemInfo itemInfo = mItemInfoList.get(n);
            mParentContainer.addView(itemInfo.view, itemInfo.position);
        }
        mChildItemHeight = mItemInfoList.get(0).view.getMeasuredHeight();
    }

    public void scrollToPosition(int position) {
        int size = mItemInfoList.size();
        int targetPos = Math.min(size, position);
        int totalHeight = getPositionTotalHeight(targetPos - 1);
        smallScrollToDistance((int) mTotalMoveY - totalHeight);
    }

    private void smallScrollToDistance(int scrollY) {
        mParentContainer.scrollBy(0, scrollY);
    }

    private void onItemChange(int oldPosition, int newPosition) {
        ItemInfo oldItemInfo = mItemInfoList.get(oldPosition);
        ItemInfo newItemInfo = mItemInfoList.get(newPosition);

        endScaleAnimation(oldItemInfo.view);
        startScaleAnimation(newItemInfo.view);
    }

    /**
     * 判断能不能向上滑
     *
     * @return
     */
    private boolean canScrollTop() {
        boolean isCanScrollTop = true;
        int itemSize = mItemInfoList.size();
        if (itemSize > 0) {
            ItemInfo itemInfo = mItemInfoList.get(itemSize - 1);
            if (itemInfo.view.getTop() >= getPositionTotalHeight(mItemInfoList.size() - 2)) {
                isCanScrollTop = false;
            }
        }
        return isCanScrollTop;
    }

    /**
     * 判断能不能向下滑
     *
     * @return
     */
    private boolean canScrollBottom() {
        boolean isCanScrollBottom = true;
        int itemSize = mItemInfoList.size();
        if (itemSize > 0) {
            ItemInfo itemInfo = mItemInfoList.get(0);
            if (itemInfo.view.getTop() == 0) {
                isCanScrollBottom = false;
            }
        }
        return isCanScrollBottom;
    }

    private int getPositionTotalHeight(int position) {
        int totalHeight = 0;
        int itemSize = mItemInfoList.size();
        if (itemSize > 0) {
            int targetSize = Math.min(itemSize - 1, position);
            for (int n = 0; n <= targetSize; n++) {
                totalHeight += (mItemInfoList.get(n).view.getMeasuredHeight());
            }
        }
        return totalHeight;
    }

    private void startScaleAnimation(View view) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, View.SCALE_X, 0.0f, 1.5f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.0f, 1.5f);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorX);
        set.playTogether(animatorY);
        set.start();
    }

    private void endScaleAnimation(View view) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, View.SCALE_X, 1.5f, 0.0f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.5f, 0.0f);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorX);
        set.playTogether(animatorY);
        set.start();
    }

    class ItemInfo {
        public int position;
        public String data;
        public View view;
        private TextView mTvName;

        public ItemInfo(int position, String data, View view) {
            this.position = position;
            this.data = data;
            this.view = view;
            mTvName = (TextView) view.findViewById(R.id.textView);
            mTvName.setText(data);
        }
    }
}
