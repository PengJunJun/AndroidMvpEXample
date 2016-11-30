package com.hankou.home.view;

import com.hankou.R;
import com.hankou.base.BaseActivity;
import com.roughike.bottombar.BottomBar;

import butterknife.BindView;

/**
 * Created by pjj on 2016/11/15.
 */

public class SearchActivity extends BaseActivity {

    @BindView(R.id.bottomBar)
    public BottomBar mBottomBar;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_search;
    }

    @Override
    public void initViews() {
        setTitle("搜索");
    }

    @Override
    public void showError(String message) {
    }
}
