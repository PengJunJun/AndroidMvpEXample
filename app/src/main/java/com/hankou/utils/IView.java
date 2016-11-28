package com.hankou.utils;

/**
 * Created by bykj003 on 2016/6/23.
 */
public interface IView {

    int getLayoutResId();

    void initIntentData();

    void initViews();

    void initListeners();

    void loadData();

    void showToast(String toastString);

    void showToast(int stringResId);

    void initJect();
}
