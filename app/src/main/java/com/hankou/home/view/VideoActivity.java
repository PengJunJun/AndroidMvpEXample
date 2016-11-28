package com.hankou.home.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.hankou.R;
import com.hankou.adapter.TestVideoAdapter;
import com.hankou.adapter.VideoAdapter;
import com.hankou.base.BaseActivity;
import com.hankou.home.model.VideoEntity;
import com.hankou.home.presenter.VideoContact;
import com.hankou.home.presenter.VideoPresenterImpl;
import com.hankou.utils.Constant;
import com.hankou.utils.views.AutoRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by bykj003 on 2016/10/14.
 */

public class VideoActivity extends BaseActivity implements VideoContact.VideoView {

    @BindView(R.id.recyclerView)
    public AutoRecyclerView mRecyclerView;

    @BindView(R.id.drawerLayout)
    public DrawerLayout mDrawerLayout;

    @BindView(R.id.navigationView)
    public NavigationView mNavigationView;

    @BindView(R.id.floatingActionButton)
    public FloatingActionButton mBtnAddVideo;

    @Inject
    public VideoPresenterImpl mVideoPresenter;

    private TestVideoAdapter mAdapter;

    public static String[] videoUrls = {};

    @Override
    public int getLayoutResId() {
        return R.layout.activity_user_detail;
    }

    @Override
    public void initViews() {
        setTitle("用户中心");
        mVideoPresenter.attachView(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TestVideoAdapter(this, getVideoList());
        mRecyclerView.setAdapter(mAdapter);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.__picker_ic_camera_p);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch mutedSwatch = palette.getMutedSwatch();
                if (mutedSwatch != null) {
                }
            }
        });
    }

    @Override
    public void initJect() {
        mActivityComponent.inJect(this);
    }

    @Override
    public void initListeners() {
        mBtnAddVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoEntity videoEntity = new VideoEntity("https://www.baidu.com", "video8");
                mVideoPresenter.addVideo(videoEntity);
            }
        });
    }

    private ArrayList<VideoEntity> getVideoList() {
        ArrayList<VideoEntity> arrayList = new ArrayList<>();
        for (int n = 0; n < videoUrls.length; n++) {
            VideoEntity entity = new VideoEntity(videoUrls[n], "pengjunjun" + n);
            arrayList.add(entity);
        }
        return arrayList;
    }

    @Override
    public void loadData() {
        mVideoPresenter.getVideos();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showError(String message) {
        showToast(message);
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        JCVideoPlayer.releaseAllVideos();
        super.onPause();
    }

    @Override
    public void showVideoList(List<VideoEntity> videoList) {
        mAdapter.setData(videoList);
    }

    @Override
    public void showAddSuccess(String message) {
        showToast(message);
    }
}
