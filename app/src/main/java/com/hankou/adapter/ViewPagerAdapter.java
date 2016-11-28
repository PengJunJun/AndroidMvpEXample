package com.hankou.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

/**
 * Created by pjj on 2015/12/31.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> mFragments;

    private FragmentTransaction mTransaction;

    private String[] mTabTitles = {"推荐","风光","我的"};

    public ViewPagerAdapter(FragmentManager fm) {
        this(fm, null);
    }

    public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> mFragments) {
        super(fm);
        mTransaction = fm.beginTransaction();
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        if (mFragments != null && mFragments.size() > 0) {
            return mFragments.size();
        }
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];
    }
}
