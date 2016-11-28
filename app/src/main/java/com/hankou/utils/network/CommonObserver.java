package com.hankou.utils.network;

import android.accounts.NetworkErrorException;
import android.util.Log;

import com.hankou.R;
import com.hankou.utils.Res;
import com.hankou.utils.ToastManager;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.util.concurrent.TimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;

/**
 * Created by bykj003 on 2016/7/21.
 */
public class CommonObserver<T> implements Observer<T> {

    private static final String TAG = "CommonObserver";

    @Override
    public void onCompleted() {
        Log.i(TAG, "onCompleted()");
    }

    @Override
    public void onError(Throwable e) {
        Log.i(TAG, e.getMessage());
        Log.i(TAG, e.toString());
        if (e instanceof HttpException) {
            ToastManager.showToast(Res.getString(R.string.request_fail));
        } else if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            ToastManager.showToast(apiException.getMessage());
        }else if(e instanceof SocketException){
            //ToastManager.showToast("连接失败");
        }else if(e instanceof TimeoutException){
            ToastManager.showToast("连接超时");
        }else if(e instanceof NetworkErrorException){
            ToastManager.showToast("网络异常");
        }
    }

    @Override
    public void onNext(T t) {
    }
}
