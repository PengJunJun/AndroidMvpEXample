package com.hankou.home.view;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import com.hankou.R;
import com.hankou.base.BaseActivity;
import com.hankou.mine.view.MineFragment;
import com.hankou.scene.view.SceneFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import butterknife.BindView;

/**
 * Created by pjj on 2016/11/15.
 */

public class SearchActivity extends BaseActivity {

    @BindView(R.id.bottomBar)
    public BottomBar mBottomBar;

    private HomeFragment mHomeFragment;

    private SceneFragment mSceneFragment;

    private MineFragment mMineFragment;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_search;
    }

    @Override
    public void initViews() {
        setTitle("搜索");
        hideToolbarBackIcon();
        initFragment();
    }

    @Override
    public void initListeners() {
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.home:
                        showFragment(0);
                        break;
                    case R.id.scene:
                        showFragment(1);
                        break;
                    case R.id.mine:
                        showFragment(2);
                        break;
                }
            }
        });
    }

    private void initFragment() {
        mHomeFragment = new HomeFragment();
        mSceneFragment = new SceneFragment();
        mMineFragment = new MineFragment();

        getSupportFragmentManager().beginTransaction().
                add(R.id.container, mHomeFragment, "home").
                add(R.id.container, mSceneFragment, "scene").
                add(R.id.container, mMineFragment, "mine").
                commitAllowingStateLoss();
    }

    private void showFragment(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(mHomeFragment).hide(mSceneFragment).hide(mMineFragment);
        switch (index) {
            case 0:
                transaction.show(mHomeFragment);
                break;
            case 1:
                transaction.show(mSceneFragment);
                break;
            case 2:
                transaction.show(mMineFragment);
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void showError(String message) {
    }
}
