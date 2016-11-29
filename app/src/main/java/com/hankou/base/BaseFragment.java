package com.hankou.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hankou.R;
import com.hankou.component.DaggerFragmentComponent;
import com.hankou.component.FragmentComponent;
import com.hankou.module.FragmentModule;
import com.hankou.utils.IView;
import com.hankou.utils.StringUtils;
import com.hankou.utils.ToastManager;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by pjj on 2016/9/5.
 */
public abstract class BaseFragment extends Fragment implements IView {

    public FragmentComponent mFragmentComponent;

    private View mBaseView;

    private FrameLayout mContentLayout;

    private ViewStub mViewStub;

    private ImageView mIvEmptyIcon;

    private TextView mTvEmptyMessage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIntentData();
        initBaseView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getLayoutResId() != -1) {
            View view = inflater.inflate(getLayoutResId(), null);
            mContentLayout.addView(view);
        }
        ButterKnife.bind(this, mBaseView);
        return mBaseView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        injectFragment();
        initJect();
        initViews();
        initListeners();
        loadData();
    }

    private void initBaseView() {
        mBaseView = LayoutInflater.from(getActivity()).inflate(R.layout.frag_base, null);
        mContentLayout = (FrameLayout) mBaseView.findViewById(R.id.layout_content);
        mViewStub = (ViewStub) mBaseView.findViewById(R.id.viewStub);
    }

    private void injectFragment() {
        mFragmentComponent = DaggerFragmentComponent.builder().
                appComponent(HankouApplication.getApplication().getAppComponent()).
                fragmentModule(new FragmentModule(this)).
                build();
    }

    @Override
    public int getLayoutResId() {
        return -1;
    }

    @Override
    public void initIntentData() {
    }

    @Override
    public void initViews() {
    }

    @Override
    public void initListeners() {
    }

    @Override
    public void loadData() {
    }

    public void showEmptyView() {
        showEmptyView("");
    }

    public void showEmptyView(String message) {
        showEmptyView(message, null);
    }

    public void showEmptyView(Drawable icon) {
        showEmptyView("", icon);
    }

    public void showEmptyView(String message, Drawable icon) {
        View view = mViewStub.inflate();
        mIvEmptyIcon = (ImageView) view.findViewById(R.id.iv_empty_image);
        mTvEmptyMessage = (TextView) view.findViewById(R.id.tv_empty_message);
        if (!StringUtils.isEmpty(message)) {
            mTvEmptyMessage.setText(message);
        }
        if (icon != null) {
            mIvEmptyIcon.setBackgroundDrawable(icon);
        }
    }

    @Override
    public void showToast(String toastString) {
        ToastManager.showToast(toastString);
    }

    @Override
    public void showToast(int stringResId) {
        ToastManager.showToast(stringResId);
    }

    @Override
    public void initJect() {
    }
}
