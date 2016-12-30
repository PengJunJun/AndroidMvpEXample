package com.hankou.home.view;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ImageView;

import com.hankou.R;
import com.hankou.base.BaseActivity;
import com.hankou.utils.Res;

import butterknife.BindView;

/**
 * Created by bykj003 on 2016/12/29.
 */

public class ScrollTestActivity extends BaseActivity {

    @BindView(R.id.imageView)
    public ImageView mIvImageView;

    @Override
    public int getLayoutResId() {
        return R.layout.act_test_photo;
    }

    @Override
    public void initViews() {
        mIvImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startToDragActivity();
            }
        });
    }

    private void startToDragActivity() {
        Intent intent = new Intent(this, ScrollImageActivity.class);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.
                makeSceneTransitionAnimation(
                        this,
                        mIvImageView,
                        Res.getString(R.string.transition_name));
        ActivityCompat.startActivity(this, intent, optionsCompat.toBundle());
    }

    @Override
    public void showError(String message) {
    }
}
