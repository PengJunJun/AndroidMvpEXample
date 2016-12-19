package com.hankou.common.view;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.hankou.R;
import com.hankou.adapter.ViewPagerAdapter;
import com.hankou.base.BaseActivity;
import com.hankou.common.presenter.MainContract;
import com.hankou.home.view.HomeFragment;
import com.hankou.home.view.TestFragment;
import com.hankou.mine.view.MineFragment;
import com.hankou.scene.view.SceneFragment;
import com.hankou.utils.DialogUtils;
import com.hankou.utils.ListUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import butterknife.BindView;

/**
 * Created by 彭竣竣 on 2016/9/5.
 */
public class MainActivity extends BaseActivity implements MainContract.IMainView, View.OnClickListener {

    @BindView(R.id.toolBar)
    public Toolbar mToolbar;

    @BindView(R.id.tv_main_title)
    public TextView mTvTitle;

    @BindView(R.id.tabLayout)
    public TabLayout mTabLayout;

    @BindView(R.id.viewPager)
    public ViewPager mViewPager;

    private HomeFragment mHomeFragment;

    private SceneFragment mSceneFragment;

    private MineFragment mMineFragment;

    private ArrayList<Fragment> mFragments;

    private ViewPagerAdapter mAdapter;

    @Override
    public void initIntentData() {
        hideToolbar();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        initToolbar();
        initFragment();
        initTabLayout();
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);
    }

    private void initToolbar() {
        mToolbar.setNavigationIcon(R.drawable.action_button_back_pressed_light);
        mToolbar.setTitle("");
        mToolbar.inflateMenu(R.menu.menu_toolbar);
        setSupportActionBar(mToolbar);
        mTvTitle.setText("首页");
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        DialogUtils.showBottomSheetDialog(MainActivity.this);
                        break;
                }
                return true;
            }
        });
    }

    private void initTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab(), 0);
        mTabLayout.addTab(mTabLayout.newTab(), 1);
        mTabLayout.addTab(mTabLayout.newTab(), 2);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void loadData() {
        new Thread() {
            @Override
            public void run() {
                testNIO();
            }
        }.start();
    }

    @Override
    public void initListeners() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setScrollPosition(position, 0.0f, false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onTabChange(int index) {
        initTabText();
    }

    @Override
    public void initTabText() {

    }

    @Override
    public void initFragment() {
        mFragments = new ArrayList<>();
        mHomeFragment = new HomeFragment();
        mSceneFragment = new SceneFragment();
        mMineFragment = new MineFragment();
        mFragments.add(mHomeFragment);
        mFragments.add(mSceneFragment);
        mFragments.add(mMineFragment);
    }

    @Override
    public void hideFragment() {

    }

    @Override
    public void showFragment(int index) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    private void testIO() {
        long start = System.currentTimeMillis();
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "image.jpg";
        String outPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "text.jpg";
        File out = new File(outPath);
        if (!out.exists()) {
            try {
                out.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(new File(filePath));
            fos = new FileOutputStream(new File(outPath));
            byte[] b = new byte[1024 * 8];

            while ((fis.read(b)) != -1) {
                fos.write(b, 0, b.length);
                fos.flush();
            }
            Log.e("IO", "间隔1:" + (System.currentTimeMillis() - start));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    private void testNIO() {
        long start = System.currentTimeMillis();
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "image.jpg";
        String outPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "nio.jpg";
        File out = new File(outPath);
        if (!out.exists()) {
            try {
                out.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream(new File(filePath));
            inChannel = fis.getChannel();
            fos = new FileOutputStream(new File(outPath));
            outChannel = fos.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 8);

            while ((inChannel.read(byteBuffer)) != -1) {
                inChannel.read(byteBuffer);
                byteBuffer.flip();
                outChannel.write(byteBuffer);
                byteBuffer.clear();
            }
            Log.e("IO", "间隔2:" + (System.currentTimeMillis() - start));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
