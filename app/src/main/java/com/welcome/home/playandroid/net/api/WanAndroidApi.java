package com.welcome.home.playandroid.net.api;

import com.welcome.home.playandroid.bean.ColumnList;
import com.welcome.home.playandroid.bean.HomeList;
import com.welcome.home.playandroid.net.response.HttpResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wuxiaoqi on 2018/4/26.
 * WanAndroid Api
 */

public interface WanAndroidApi {

    @GET("/article/list/{page}/json")
    Observable<HttpResponse<HomeList>> getHomeList(@Path("page") int page);

    @GET("/tree/json")
    Observable<HttpResponse<List<ColumnList>>> getColumnList();
}
