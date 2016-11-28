package com.hankou.module;

import android.content.Context;

import com.hankou.base.BaseActivity;
import com.hankou.component.PerActivity;
import com.hankou.home.presenter.VideoImagePresentImpl;
import com.hankou.home.presenter.VideoPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by bykj003 on 2016/9/26.
 */
@Module
public class ActivityModule {

    private BaseActivity mContext;

    public ActivityModule(BaseActivity activity) {
        mContext = activity;
    }

    @Provides
    @PerActivity
    Context provinceContext() {
        return mContext;
    }

    @Provides
    @PerActivity
    BaseActivity provinceActivity() {
        return mContext;
    }

    @Provides
    @PerActivity
    VideoPresenterImpl provinceUserPresent() {
        return new VideoPresenterImpl();
    }

    @Provides
    @PerActivity
    VideoImagePresentImpl provinceVideoImagePresent() {
        return new VideoImagePresentImpl();
    }
}
