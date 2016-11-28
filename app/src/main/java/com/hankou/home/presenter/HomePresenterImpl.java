package com.hankou.home.presenter;

import com.hankou.base.BaseView;
import com.hankou.common.model.CommonEntity;
import com.hankou.mine.model.UserEntity;
import com.hankou.utils.HttpUtil;
import com.hankou.utils.network.CommonObserver;
import com.hankou.utils.network.HttpManager;

import java.util.List;

/**
 * Created by pjj on 2016/9/20.
 */
public class HomePresenterImpl implements HomeContact.IHomePresenter {

    private HomeContact.IHomeView mHomeView;

    public HomePresenterImpl(HomeContact.IHomeView homeView) {
        this.mHomeView = homeView;
    }

    public HomePresenterImpl() {

    }

    @Override
    public void getData() {
        HttpUtil.getHttpResult(HttpManager.getInstance().getUserApi().getAllUser(),
                new CommonObserver<CommonEntity<List<UserEntity>>>() {
                    @Override
                    public void onNext(CommonEntity<List<UserEntity>> listCommonEntity) {
                        if (listCommonEntity.status) {
                            mHomeView.showSuccess(listCommonEntity.data);
                        } else {
                            mHomeView.showError(listCommonEntity.msg);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mHomeView.showError("dsfdsfdsfdsfdsfdsfsdf");
                    }
                });
    }

    @Override
    public void attachView(HomeContact.IHomeView view) {
        this.mHomeView = view;
    }
}
