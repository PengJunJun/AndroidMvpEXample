package com.hankou.home.view;

<<<<<<< HEAD
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.facebook.keyframes.KeyframesDrawable;
import com.facebook.keyframes.KeyframesDrawableBuilder;
import com.facebook.keyframes.deserializers.KFImageDeserializer;
import com.facebook.keyframes.model.KFImage;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.hankou.R;
import com.hankou.base.BaseActivity;
import com.hankou.home.model.ImageEntity;
import com.hankou.home.presenter.VideoImageContact;
import com.hankou.home.presenter.VideoImagePresentImpl;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

<<<<<<< HEAD
    private KFImage mKfImage;
    private KeyframesDrawable mKeyFramesDrawable;
    private final IntentFilter mPreviewKeyframesAnimation = new IntentFilter("PreviewKeyframesAnimation");

    private BroadcastReceiver mPreviewRenderReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String descriptorPath = intent.getStringExtra("descriptorPath");
            if (descriptorPath == null) {
                return;
            }

            InputStream descriptorJSON;
            try {
                descriptorJSON = new FileInputStream(descriptorPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }

            KFImage kfImage;
            try {
                kfImage = KFImageDeserializer.deserialize(descriptorJSON);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            setKFImage(kfImage);
        }
    };

=======
>>>>>>> 523709d7b1ab9cb1dce1ab11c6c9dc8908f555bc
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
        setKFImage(getSampleImage());
        //registerReceiver(mPreviewRenderReceiver, mPreviewKeyframesAnimation);
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

    private void setKFImage(KFImage kfImage) {
        mKfImage = kfImage;

        final Drawable logoDrawable = getResources().getDrawable(R.drawable.ic_bili_logo_2016);
        if (logoDrawable != null) {
            logoDrawable.setBounds(0, 0, 80, 80);
            mKeyFramesDrawable = new KeyframesDrawableBuilder()
                    .withImage(mKfImage)
                    .withMaxFrameRate(60)
                    .withExperimentalFeatures()
                    .withParticleFeatureConfigs(
                            Pair.create("keyframes", Pair.create(logoDrawable, new Matrix())))
                    .build();
        }
        mKeyFramesDrawable.startAnimation();

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        imageView.setImageDrawable(mKeyFramesDrawable);
    }

    private KFImage getSampleImage() {
        InputStream stream = null;
        try {
            stream = getResources().getAssets().open("sample_file");
            KFImage sampleImage = KFImageDeserializer.deserialize(stream);
            return sampleImage;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException ignored) {
                }
            }
        }
        return null;
    }

    public void resetImage(View view) {
        setKFImage(getSampleImage());
    }

    @Override
    public void onPause() {
        if (mKeyFramesDrawable != null) {
            mKeyFramesDrawable.stopAnimation();
        }
        //unregisterReceiver(mPreviewRenderReceiver);
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        //registerReceiver(mPreviewRenderReceiver, mPreviewKeyframesAnimation);
        if (mKeyFramesDrawable != null) {
            mKeyFramesDrawable.startAnimation();
        }
    }
=======
>>>>>>> 523709d7b1ab9cb1dce1ab11c6c9dc8908f555bc
}
