package com.hankou.home.presenter;

import com.hankou.common.model.CommonEntity;
import com.hankou.home.model.VideoEntity;
import com.hankou.mine.model.UserEntity;
import com.hankou.utils.HttpUtil;
import com.hankou.utils.network.CommonObserver;
import com.hankou.utils.network.HttpManager;

import java.util.HashMap;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by bykj003 on 2016/10/14.
 */

public class VideoPresenterImpl implements VideoContact.VideoPresenter {

    private VideoContact.VideoView mUserDetailView;

    @Override
    public void attachView(VideoContact.VideoView view) {
        mUserDetailView = view;
    }

    @Override
    public void getVideos() {
        HttpUtil.getHttpResult(HttpManager.getInstance().getUserApi().getVideos(),
                new CommonObserver<CommonEntity<List<VideoEntity>>>() {
                    @Override
                    public void onNext(CommonEntity<List<VideoEntity>> listCommonEntity) {
                        if (listCommonEntity.status) {
                            mUserDetailView.showVideoList(listCommonEntity.data);
                        } else {
                            mUserDetailView.showError(listCommonEntity.msg);
                        }
                    }
                });
    }

    @Override
    public void addVideo(VideoEntity videoEntity) {
        HttpUtil.getHttpResult(HttpManager.getInstance().getUserApi().addVideo(videoEntity),
                new CommonObserver<CommonEntity<Object>>(){
                    @Override
                    public void onNext(CommonEntity<Object> objectCommonEntity) {
                        if(objectCommonEntity.status){
                            mUserDetailView.showAddSuccess("添加成功");
                        }else{
                            mUserDetailView.showError("添加失败");
                        }
                    }
                });
    }
}
