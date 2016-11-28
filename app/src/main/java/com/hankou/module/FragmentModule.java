package com.hankou.module;

import com.hankou.base.BaseFragment;
import com.hankou.component.PerFragment;
import com.hankou.home.presenter.HomePresenterImpl;
import com.hankou.mine.presenter.MinePresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by bykj003 on 2016/10/14.
 */
@Module
public class FragmentModule {

    public BaseFragment mFragment;

    public FragmentModule(BaseFragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @PerFragment
    BaseFragment provinceFragment(){
        return mFragment;
    }

    @Provides
    @PerFragment
    HomePresenterImpl provinceHomePresenter(){
        return new HomePresenterImpl();
    }

    @Provides
    @PerFragment
    MinePresenterImpl provinceMinePresenter(){
        return new MinePresenterImpl();
    }
}
