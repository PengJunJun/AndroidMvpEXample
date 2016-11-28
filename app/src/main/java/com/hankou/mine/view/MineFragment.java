package com.hankou.mine.view;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hankou.R;
import com.hankou.base.BaseFragment;
import com.hankou.mine.model.UserEntity;
import com.hankou.mine.presenter.MineContract;
import com.hankou.mine.presenter.MinePresenterImpl;

import java.util.HashMap;

import butterknife.BindView;

/**
 * Created by bykj003 on 2016/9/5.
 */
public class MineFragment extends BaseFragment implements MineContract.IMineView, View.OnClickListener {

    @BindView(R.id.btn_selected)
    public Button mBtnSelected;

    @BindView(R.id.btn_delete)
    public Button mBtnDelete;

    @BindView(R.id.btn_insert)
    public Button mBtnInsert;

    @BindView(R.id.btn_update)
    public Button mBtnUpdate;

    @BindView(R.id.tv_msg)
    public TextView mTvMsg;

    private MinePresenterImpl mMainPresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.frag_mine;
    }

    @Override
    public void initViews() {
        mMainPresenter = new MinePresenterImpl();
        mMainPresenter.attachView(this);
    }

    @Override
    public void initJect() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void initListeners() {
        mBtnSelected.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
        mBtnInsert.setOnClickListener(this);
        mBtnUpdate.setOnClickListener(this);
    }

    @Override
    public void showSuccess() {
        showToast("成功了");
    }

    @Override
    public void showFail(String msg) {
        showToast(msg);
    }

    @Override
    public void showUserInfo(UserEntity userEntity) {
        mTvMsg.setText(userEntity.name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_selected:
                mMainPresenter.selected(1);
                break;
            case R.id.btn_delete:
                mMainPresenter.delete(1);
                break;
            case R.id.btn_insert:
                mMainPresenter.insert(new UserEntity(1, "mmm", "are you llgg", "dssgdsgsdgsdgsg"));
                break;
            case R.id.btn_update:
                mMainPresenter.update(new UserEntity(1, "gggg", "are you llgg", "gggggggggggggggggg"));
                break;
        }
    }

    @Override
    public void showError(String message) {

    }
}
