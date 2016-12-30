package com.hankou.home.view;

import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import com.hankou.R;
import com.hankou.base.BaseActivity;
import com.hankou.utils.views.DragPhotoView;
import butterknife.BindView;

/**
 * Created by pengjunjun on 2016/12/29.
 */

public class ScrollImageActivity extends BaseActivity {

    @BindView(R.id.drawPhotoView)
    public DragPhotoView mDrawPhotoView;

    @BindView(R.id.view)
    public View mVew;

    @BindView(R.id.coordinatorLayout)
    public CoordinatorLayout mCoordinatorLayout;

    @Override
    public int getLayoutResId() {
        return R.layout.act_drag_photo;
    }

    @Override
    public void initViews() {
        mDrawPhotoView.setOnPhotoMoveOutListener(new DragPhotoView.OnPhotoMoveOutListener() {
            @Override
            public void onMoveOut() {
                onBackPressed();
            }
        });
    }

    @Override
    public void showError(String message) {
    }
}
