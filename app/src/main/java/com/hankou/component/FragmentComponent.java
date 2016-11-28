package com.hankou.component;

import com.hankou.home.view.HomeFragment;
import com.hankou.mine.view.MineFragment;
import com.hankou.module.FragmentModule;

import dagger.Component;

/**
 * Created by bykj003 on 2016/10/14.
 */
@PerFragment
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(HomeFragment homeFragment);

    void inject(MineFragment mineFragment);
}
