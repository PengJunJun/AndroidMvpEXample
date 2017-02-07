package com.hankou.home.presenter;

import android.util.Log;

import com.hankou.base.BaseView;
import com.hankou.common.model.CommonEntity;
import com.hankou.mine.model.UserEntity;
import com.hankou.utils.HttpUtil;
import com.hankou.utils.network.CommonObserver;
import com.hankou.utils.network.HttpManager;
import com.hankou.utils.network.api.UserApi;

import java.util.HashMap;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pjj on 2016/9/20.
 */
public class HomePresenterImpl implements HomeContact.IHomePresenter {

    private HomeContact.IHomeView mHomeView;

    private UserApi mUserResponsibility;

    public HomePresenterImpl(HomeContact.IHomeView homeView, UserApi userResponsibility) {
        this.mHomeView = homeView;
        this.mUserResponsibility = userResponsibility;
    }

    public HomePresenterImpl(){}

    @Override
    public void getData() {
        mUserResponsibility.getAllUser().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new CommonObserver<CommonEntity<List<UserEntity>>>() {
                    @Override
                    public void onNext(CommonEntity<List<UserEntity>> listCommonEntity) {
                        if (listCommonEntity.status) {
                            System.out.print(listCommonEntity.data.get(0).toString());
                            mHomeView.showSuccess(listCommonEntity.data);
                        } else {
                            mHomeView.showError(listCommonEntity.msg);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mHomeView.showError("服务器已挂,勿念!");
                    }
                });
    }

    @Override
    public void attachResponsibility(UserApi userApi) {
        this.mUserResponsibility = userApi;
    }

    @Override
    public void attachView(HomeContact.IHomeView view) {
        this.mHomeView = view;
    }
}
