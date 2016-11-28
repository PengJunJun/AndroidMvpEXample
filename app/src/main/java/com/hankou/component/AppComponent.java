package com.hankou.component;

import com.hankou.base.HankouApplication;
import com.hankou.module.AppModule;

import javax.inject.Scope;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by bykj003 on 2016/9/26.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inJect(HankouApplication app);
}
