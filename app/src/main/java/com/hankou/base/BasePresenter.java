package com.hankou.base;

/**
 * Created by bykj003 on 2016/9/5.
 */
public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
}
