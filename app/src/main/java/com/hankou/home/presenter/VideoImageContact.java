package com.hankou.home.presenter;

import com.hankou.base.BasePresenter;
import com.hankou.base.BaseView;
import com.hankou.home.model.ImageEntity;

/**
 * Created by bykj003 on 2016/11/1.
 */

public interface VideoImageContact {
    interface VideoImageView extends BaseView {
        void showSuccess(ImageEntity imageEntity);
    }

    interface VideoImagePresenter extends BasePresenter<VideoImageView> {
        void getVideoImage();
    }
}
