package com.hankou.component;

import com.hankou.home.view.VideoActivity;
import com.hankou.home.view.VideoImageActivity;
import com.hankou.module.ActivityModule;

import dagger.Component;

/**
 * Created by bykj003 on 2016/10/14.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inJect(VideoActivity activity);

    void inJect(VideoImageActivity activity);
}
