package com.hankou.home.presenter;

import com.hankou.common.model.CommonEntity;
import com.hankou.home.model.ImageEntity;
import com.hankou.utils.HttpUtil;
import com.hankou.utils.network.CommonObserver;
import com.hankou.utils.network.HttpManager;

/**
 * Created by bykj003 on 2016/11/1.
 */

public class VideoImagePresentImpl implements VideoImageContact.VideoImagePresenter {

    private VideoImageContact.VideoImageView mVideoImageView;

    @Override
    public void getVideoImage() {
        HttpUtil.getHttpResult(HttpManager.getInstance().getUserApi().getVideoImage(),
                new CommonObserver<CommonEntity<ImageEntity>>() {
                    @Override
                    public void onNext(CommonEntity<ImageEntity> imageEntityCommonEntity) {
                        if (imageEntityCommonEntity.status) {
                            mVideoImageView.showSuccess(imageEntityCommonEntity.data);
                        } else {
                            mVideoImageView.showError(imageEntityCommonEntity.msg);
                        }
                    }
                });
    }

    @Override
    public void attachView(VideoImageContact.VideoImageView view) {
        mVideoImageView = view;
    }
}
