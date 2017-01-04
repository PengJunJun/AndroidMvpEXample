package com.hankou.home.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.hankou.R;
import com.hankou.adapter.HomePagerAdapter;
import com.hankou.base.BaseFragment;
import com.hankou.home.presenter.HomeContact;
import com.hankou.home.presenter.HomePresenterImpl;
import com.hankou.mine.model.UserEntity;
import com.hankou.utils.views.AutoRecyclerItemDecoration;
import com.hankou.utils.views.AutoRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by bykj003 on 2016/9/5.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, HomeContact.IHomeView {

    @BindView(R.id.recyclerView)
    public AutoRecyclerView mRecyclerView;

    @BindView(R.id.floatingActionButton)
    public FloatingActionButton mBtnFloating;

    @Inject
    public HomePresenterImpl mHomePresenter;

    private HomePagerAdapter mAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.frag_home;
    }

    @Override
    public void initViews() {
        mHomePresenter.attachView(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new AutoRecyclerItemDecoration(1, 20, true));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new HomePagerAdapter(getActivity(), new ArrayList<UserEntity>());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initJect() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void initListeners() {
        mRecyclerView.setOnItemClickListener(new AutoRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), TimeSelectedActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void loadData() {
        mHomePresenter.getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
        showEmptyView();
    }

    @Override
    public void showSuccess(List<UserEntity> data) {
        ArrayList<UserEntity> userEntities = new ArrayList<>();
        userEntities.addAll(data);
        userEntities.addAll(data);
        userEntities.addAll(data);
        userEntities.addAll(data);
        mAdapter.setData(userEntities);
    }
}
