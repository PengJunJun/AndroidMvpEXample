package com.hankou.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hankou.component.DaggerFragmentComponent;
import com.hankou.component.FragmentComponent;
import com.hankou.module.FragmentModule;
import com.hankou.utils.IView;
import com.hankou.utils.ToastManager;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by pjj on 2016/9/5.
 */
public abstract class BaseFragment extends Fragment implements IView {

    public FragmentComponent mFragmentComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIntentData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getLayoutResId() != -1) {
            View view = inflater.inflate(getLayoutResId(), null);
            ButterKnife.bind(this, view);
            return view;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        injectFragment();
        initJect();
        initViews();
        initListeners();
        loadData();
    }

    private void injectFragment() {
        mFragmentComponent = DaggerFragmentComponent.builder().
                appComponent(HankouApplication.getApplication().getAppComponent()).
                fragmentModule(new FragmentModule(this)).
                build();
    }

    @Override
    public int getLayoutResId() {
        return -1;
    }

    @Override
    public void initIntentData() {
    }

    @Override
    public void initViews() {
    }

    @Override
    public void initListeners() {
    }

    @Override
    public void loadData() {
    }

    @Override
    public void showToast(String toastString) {
        ToastManager.showToast(toastString);
    }

    @Override
    public void showToast(int stringResId) {
        ToastManager.showToast(stringResId);
    }

    @Override
    public void initJect() {
    }
}
