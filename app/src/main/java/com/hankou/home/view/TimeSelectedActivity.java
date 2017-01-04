package com.hankou.home.view;

import com.hankou.R;
import com.hankou.base.BaseActivity;
import com.hankou.utils.views.TimeSelectedView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by bykj003 on 2017/1/4.
 */

public class TimeSelectedActivity extends BaseActivity {

    @BindView(R.id.timeSelectedView)
    public TimeSelectedView mTimeSelectedView;

    @Override
    public int getLayoutResId() {
        return R.layout.act_time_selected;
    }

    @Override
    public void initViews() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("item1");
        stringArrayList.add("item2");
        stringArrayList.add("item3");
        stringArrayList.add("item4");
        stringArrayList.add("item5");
        stringArrayList.add("item6");
        stringArrayList.add("item7");
        stringArrayList.add("item8");
        stringArrayList.add("item9");
        stringArrayList.add("item10");
        stringArrayList.add("item11");
        stringArrayList.add("item12");
        mTimeSelectedView.addItem(stringArrayList);
    }
}
