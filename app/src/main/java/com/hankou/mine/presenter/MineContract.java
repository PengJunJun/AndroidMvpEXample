package com.hankou.mine.presenter;

import com.hankou.base.BasePresenter;
import com.hankou.base.BaseView;
import com.hankou.mine.model.UserEntity;

import java.util.Map;

/**
 * Created by bykj003 on 2016/9/5.
 */
public interface MineContract {

    interface IMineView extends BaseView{
        void showSuccess();

        void showFail(String msg);

        void showUserInfo(UserEntity userEntity);
    }

    interface IMinePresenter extends BasePresenter {
        void insert(UserEntity userEntity);

        void delete(int userId);

        void selected(int userId);

        void update(UserEntity userEntity);
    }
}
