package com.hankou.module;

import android.content.Context;

import com.hankou.base.HankouApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by bykj003 on 2016/9/26.
 */
@Module
public class AppModule {

    private HankouApplication mApplication;

    public AppModule(HankouApplication app) {
        this.mApplication = app;
    }

    @Provides
    @Singleton
    HankouApplication provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient(){
        return new OkHttpClient();
    }
}
