package com.hankou.scene.view;

import com.hankou.R;
import com.hankou.base.BaseFragment;
import com.hankou.scene.presenter.SceneContract;
import com.roughike.bottombar.BottomBar;

import butterknife.BindView;

/**
 * Created by bykj003 on 2016/9/5.
 */
public class SceneFragment extends BaseFragment implements SceneContract.ISceneView {

    @BindView(R.id.bottomBar)
    public BottomBar mBottomBar;

    @Override
    public int getLayoutResId() {
        return R.layout.frag_scene;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initListeners() {
    }

    @Override
    public void showError(String message) {

    }
}
