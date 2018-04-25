package com.welcome.home.playandroid.net.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wuxiaoqi on 2018/4/25.
 * retrofit client
 */

public class RetrofitClient {

    private OkHttpClient mOkHttpClient;

    private String mBaseUrl;

    private Retrofit mRetrofit;

    public RetrofitClient(String baseUrl, OkHttpClient okHttpClient) {
        this.mBaseUrl = baseUrl;
        this.mOkHttpClient = okHttpClient;
    }

    public RetrofitClient setBaseUrl(String baseUrl) {
        this.mBaseUrl = baseUrl;
        return this;
    }

    public <T> T build(Class<T> service) {
        if (mBaseUrl == null) {
            throw new RuntimeException("baseUrl is null!");
        }
        if (service == null) {
            throw new RuntimeException("api Service is null!");
        }
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return mRetrofit.create(service);
    }
}
