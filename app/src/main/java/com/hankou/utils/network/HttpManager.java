package com.hankou.utils.network;

import com.hankou.utils.network.api.UserApi;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by bykj003 on 2016/9/23.
 */

public class HttpManager {
    public static HttpManager mHttpManager;
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient = new OkHttpClient();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    private static final String HOST_URL = "http://192.168.1.73:8080";
    private UserApi mUserApi;

    private HttpManager() {
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(HOST_URL)
                .addConverterFactory(CommonConverterFactory.create())
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }

    public static HttpManager getInstance() {
        if (mHttpManager == null) {
            mHttpManager = new HttpManager();
        }
        return mHttpManager;
    }

    public UserApi getUserApi() {
        if (mUserApi == null) {
            mUserApi = mRetrofit.create(UserApi.class);
        }
        return mUserApi;
    }

}
