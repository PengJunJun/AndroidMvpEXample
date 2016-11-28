package com.hankou.base;

import android.app.Application;
import com.hankou.component.AppComponent;
import com.hankou.component.DaggerAppComponent;
import com.hankou.module.AppModule;

/**
 * Created by bykj003 on 2016/9/5.
 */
public class HankouApplication extends Application {

    private static HankouApplication mApplication;

    public AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initContext();
        initInject();
    }

    private void initContext() {
        mApplication = this;
    }

    private void initInject() {
        mAppComponent = DaggerAppComponent.builder().
                appModule(new AppModule(this)).
                build();
        mAppComponent.inJect(this);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static HankouApplication getApplication() {
        return mApplication;
    }
}
