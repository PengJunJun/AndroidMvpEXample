package com.hankou.home.view;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.hankou.R;
import com.hankou.base.BaseActivity;
import com.hankou.home.model.ImageEntity;
import com.hankou.home.presenter.VideoImageContact;
import com.hankou.home.presenter.VideoImagePresentImpl;
import javax.inject.Inject;
import butterknife.BindView;

/**
 * Created by bykj003 on 2016/11/1.
 */

public class VideoImageActivity extends BaseActivity implements VideoImageContact.VideoImageView {

    @BindView(R.id.imageView)
    public ImageView imageView;

    @Inject
    public VideoImagePresentImpl mPresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_video_image;
    }

    @Override
    public void initJect() {
        mActivityComponent.inJect(this);
    }

    @Override
    public void initViews() {
        mPresenter.attachView(this);
        setTitle("图片");
    }

    @Override
    public void loadData() {
        mPresenter.getVideoImage();
    }

    @Override
    public void showSuccess(ImageEntity imageEntity) {
        Glide.with(this).load(imageEntity.url).into(imageView);
    }

    @Override
    public void showError(String message) {
        showToast(message);
    }
}
