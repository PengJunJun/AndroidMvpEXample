package com.hankou.utils.network.api;

import com.hankou.common.model.CommonEntity;
import com.hankou.home.model.ImageEntity;
import com.hankou.home.model.VideoEntity;
import com.hankou.mine.model.UserEntity;

import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by bykj003 on 2016/9/23.
 */

public interface UserApi {

    @GET("/all/user")
    Observable<CommonEntity<List<UserEntity>>> getAllUser();

    @GET("/user/selected")
    Observable<CommonEntity<UserEntity>> selectedUser(@Query("uid") int userId);

    @FormUrlEncoded
    @POST("/user/deleted")
    Observable<CommonEntity<Object>> deleteUser(@Field("uid") int userId);

    @POST("/user/insert")
    Observable<CommonEntity<Object>> insertUser(@Body UserEntity userEntity);

    @POST("/user/update")
    Observable<CommonEntity<Object>> updateUser(@Body UserEntity userEntity);

    @HTTP(method = "DELETE", path = "/delete_user", hasBody = true)
    Observable<CommonEntity<Object>> deleUser(@Body Map<String, Object> param);

    @GET("/video/all")
    Observable<CommonEntity<List<VideoEntity>>> getVideos();

    @POST("/video/add")
    Observable<CommonEntity<Object>> addVideo(@Body VideoEntity videoEntity);

    @GET("/video/image")
    Observable<CommonEntity<ImageEntity>> getVideoImage();
}
