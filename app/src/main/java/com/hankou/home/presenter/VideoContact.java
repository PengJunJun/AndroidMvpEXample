package com.hankou.home.presenter;

import com.hankou.base.BasePresenter;
import com.hankou.base.BaseView;
import com.hankou.home.model.VideoEntity;
import com.hankou.mine.model.UserEntity;

import java.util.List;

/**
 * Created by bykj003 on 2016/10/14.
 */

public interface VideoContact {

    interface VideoView extends BaseView {
        void showVideoList(List<VideoEntity> videoList);
        void showAddSuccess(String message);
    }

    interface VideoPresenter extends BasePresenter<VideoView> {
        void getVideos();
        void addVideo(VideoEntity videoEntity);
    }
}
