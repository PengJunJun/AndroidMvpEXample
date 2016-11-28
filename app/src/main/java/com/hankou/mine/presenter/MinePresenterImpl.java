package com.hankou.mine.presenter;

import android.util.Log;

import com.hankou.base.BaseView;
import com.hankou.base.HankouApplication;
import com.hankou.common.model.CommonEntity;
import com.hankou.mine.model.UserEntity;
import com.hankou.utils.HttpUtil;
import com.hankou.utils.network.CommonObserver;
import com.hankou.utils.network.HttpManager;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by bykj003 on 2016/9/23.
 */

public class MinePresenterImpl implements MineContract.IMinePresenter {

    private MineContract.IMineView mMineView;

    @Override
    public void insert(UserEntity userEntity) {
        HttpUtil.getHttpResult(HttpManager.getInstance().getUserApi().insertUser(userEntity),
                new CommonObserver<CommonEntity<Object>>() {
                    @Override
                    public void onNext(CommonEntity<Object> objectCommonEntity) {
                        if (objectCommonEntity.status) {
                            mMineView.showSuccess();
                        } else {
                            mMineView.showFail(objectCommonEntity.msg);
                        }
                    }
                });
    }

    @Override
    public void delete(int userId) {
        HttpUtil.getHttpResult(HttpManager.getInstance().getUserApi().deleteUser(userId),
                new CommonObserver<CommonEntity<Object>>() {
                    @Override
                    public void onNext(CommonEntity<Object> objectCommonEntity) {
                        if (objectCommonEntity.status) {
                            mMineView.showSuccess();
                        } else {
                            mMineView.showFail(objectCommonEntity.msg);
                        }
                    }
                });
    }

    @Override
    public void selected(int userId) {
        HttpUtil.getHttpResult(HttpManager.getInstance().getUserApi().selectedUser(userId),
                new CommonObserver<CommonEntity<UserEntity>>() {
                    @Override
                    public void onNext(CommonEntity<UserEntity> objectCommonEntity) {
                        if (objectCommonEntity.status) {
                            mMineView.showUserInfo(objectCommonEntity.data);
                        } else {
                            mMineView.showFail(objectCommonEntity.msg);
                        }
                    }
                });
    }

    @Override
    public void update(UserEntity userEntity) {
        HttpUtil.getHttpResult(HttpManager.getInstance().getUserApi().updateUser(userEntity),
                new CommonObserver<CommonEntity<Object>>() {
                    @Override
                    public void onNext(CommonEntity<Object> objectCommonEntity) {
                        if (objectCommonEntity.status) {
                            mMineView.showSuccess();
                        } else {
                            mMineView.showFail(objectCommonEntity.msg);
                        }
                    }
                });
    }

    @Override
    public void attachView(BaseView view) {
        mMineView = (MineContract.IMineView) view;
    }
}
