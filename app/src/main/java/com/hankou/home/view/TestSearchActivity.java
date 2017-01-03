package com.hankou.home.view;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.EditText;

import com.hankou.R;
import com.hankou.base.BaseActivity;
import com.hankou.utils.Res;

import butterknife.BindView;

/**
 * Created by bykj003 on 2017/1/3.
 */

public class TestSearchActivity extends BaseActivity {

    @BindView(R.id.et_search)
    public EditText mEtDetail;

    @Override
    public int getLayoutResId() {
        return R.layout.act_search;
    }

    @Override
    public void initListeners() {
        mEtDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startToDragActivity();
            }
        });
    }

    private void startToDragActivity() {
        Intent intent = new Intent(this, TestSearchDetailActivity.class);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.
                makeSceneTransitionAnimation(
                        this,
                        mEtDetail,
                        Res.getString(R.string.transition_name));
        ActivityCompat.startActivity(this, intent, optionsCompat.toBundle());
    }
}
