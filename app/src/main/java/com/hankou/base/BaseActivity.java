package com.hankou.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hankou.R;
import com.hankou.common.view.MainActivity;
import com.hankou.component.ActivityComponent;
import com.hankou.component.DaggerActivityComponent;
import com.hankou.module.ActivityModule;
import com.hankou.utils.IView;
import com.hankou.utils.ToastManager;
import com.hankou.utils.views.SlideView;

import butterknife.ButterKnife;

/**
 * Created by pjj on 2016/9/5.
 */
public abstract class BaseActivity extends AppCompatActivity implements IView, BaseView {

    private SlideView mBaseViewLayout;

    private FrameLayout mContentViewLayout;

    private Toolbar mToolBar;

    private TextView mTvTitle;

    private boolean mHideToolbar = false;

    public ActivityComponent mActivityComponent;

    private ViewStub mEmptyView;

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        initIntentData();
        initBaseView();
        if (getLayoutResId() != -1) {
            View view = LayoutInflater.from(this).inflate(getLayoutResId(), null);
            mContentViewLayout.addView(view);
        }
        setContentView(mBaseViewLayout);
        ButterKnife.bind(this, mBaseViewLayout);
        if (!mHideToolbar) {
            initToolBar();
        } else {
            mToolBar.setVisibility(View.GONE);
        }
        injectActivity();
        initJect();
        initViews();
        initListeners();
        loadData();

        overridePendingTransition(R.anim.act_right_enter, 0);
    }

    private void injectActivity() {
        mActivityComponent = DaggerActivityComponent.builder().
                activityModule(new ActivityModule(this)).
                appComponent(HankouApplication.getApplication().getAppComponent()).
                build();
    }

    private void initBaseView() {
        mBaseViewLayout = (SlideView) LayoutInflater.from(this).inflate(R.layout.activity_base, null);
        mContentViewLayout = (FrameLayout) mBaseViewLayout.findViewById(R.id.layout_content);
        mToolBar = (Toolbar) mBaseViewLayout.findViewById(R.id.toolbar);
        mTvTitle = (TextView) mBaseViewLayout.findViewById(R.id.tv_title);
        mEmptyView = (ViewStub) mBaseViewLayout.findViewById(R.id.viewStub);
        mBaseViewLayout.setOnFinishListener(new SlideView.OnStartFinishListener() {
            @Override
            public void onFinish() {
                finish();
            }
        });
    }

    public void hideToolbar() {
        mHideToolbar = true;
    }

    private void initToolBar() {
        mToolBar.setNavigationIcon(R.drawable.action_button_back_pressed_light);
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public void hideToolbarBackIcon() {
        mToolBar.setNavigationIcon(null);
    }

    public void showToolbarBackIcon() {
        mToolBar.setNavigationIcon(R.drawable.actionbar_back);
    }

    public void showEmptyView() {
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
