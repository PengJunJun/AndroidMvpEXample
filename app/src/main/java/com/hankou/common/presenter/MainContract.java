package com.hankou.common.presenter;

import com.hankou.base.BasePresenter;
import com.hankou.base.BaseView;

/**
 * Created by 彭竣竣 on 2016/9/5.
 */
public interface MainContract {

    interface IMainView extends BaseView {

        void onTabChange(int index);

        void initTabText();

        void initFragment();

        void hideFragment();

        void showFragment(int index);
    }

    interface IMainPresenter extends BasePresenter {
    }
}
