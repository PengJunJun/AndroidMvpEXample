package com.hankou.home.presenter;

import com.hankou.base.BasePresenter;
import com.hankou.base.BaseView;
import com.hankou.home.model.HomeEntity;
import com.hankou.mine.model.UserEntity;
import com.hankou.utils.network.api.UserApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bykj003 on 2016/9/20.
 */
public interface HomeContact {

    interface IHomeView extends BaseView{
        void showSuccess(List<UserEntity> userEntities);
    }

    interface IHomePresenter extends BasePresenter<IHomeView> {
        void getData();
        void attachResponsibility(UserApi userApi);
    }
}
