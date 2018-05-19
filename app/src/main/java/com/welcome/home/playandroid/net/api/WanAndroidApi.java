package com.welcome.home.playandroid.net.api;

import com.welcome.home.playandroid.bean.BannerList;
import com.welcome.home.playandroid.bean.CollectList;
import com.welcome.home.playandroid.bean.ColumnContentList;
import com.welcome.home.playandroid.bean.ColumnList;
import com.welcome.home.playandroid.bean.HomeList;
import com.welcome.home.playandroid.bean.LoginBean;
import com.welcome.home.playandroid.net.response.HttpResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by wuxiaoqi on 2018/4/26.
 * WanAndroid Api
 */

public interface WanAndroidApi {

    @GET("/article/list/{page}/json")
    Observable<HttpResponse<HomeList>> getHomeList(@Path("page") int page);

    @GET("/tree/json")
    Observable<HttpResponse<List<ColumnList>>> getColumnList();

    @GET("/article/list/{page}/json")
    Observable<HttpResponse<ColumnContentList>> getColumnContentList(@Path("page") int page, @Query("cid") int c_id);

    @GET("/banner/json")
    Observable<HttpResponse<List<BannerList>>> getBannerList();

    @FormUrlEncoded
    @POST("/user/register")
    Observable<HttpResponse<LoginBean>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

    @FormUrlEncoded
    @POST("/user/login")
    Observable<HttpResponse<LoginBean>> login(@Field("username") String username, @Field("password") String password);

    @GET("/lg/collect/list/0/json")
    Observable<HttpResponse<CollectList>> getCollectList();
}
