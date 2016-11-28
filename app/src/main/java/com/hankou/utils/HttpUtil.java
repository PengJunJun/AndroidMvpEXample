package com.hankou.utils;

import com.hankou.common.model.CommonEntity;
import com.hankou.mine.model.UserEntity;
import com.hankou.utils.network.CommonObserver;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by bykj003 on 2016/7/1.
 */
public class HttpUtil {

    public static RequestBody create(String param) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), param);
    }

    public RequestBody parseRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    public String parseImageMapKey(String key, String fileName) {
        return key + "\"; filename=\"" + fileName;
    }

    public void getResult(Observable<CommonEntity<List<UserEntity>>> observable, CommonObserver<CommonEntity<List<UserEntity>>> resultCallback){
        observable.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(resultCallback);
    }

    /**
     * 获取网络请求的结果,
     *
     * @param observable
     * @param resultCallback
     * @param <T>
     */
    public static <T> void getHttpResult(Observable<CommonEntity<T>> observable, CommonObserver<CommonEntity<T>> resultCallback) {
        observable.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(resultCallback);
    }

    /**
     * 取消网络请求
     *
     * @param subscribe
     */
    public static void cancelRequest(Subscription subscribe) {
        if (subscribe != null) {
            subscribe.unsubscribe();
        }
    }
}
