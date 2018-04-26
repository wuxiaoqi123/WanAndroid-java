package com.welcome.home.playandroid.app;

import android.app.Application;
import android.content.Context;

import com.welcome.home.playandroid.R;
import com.welcome.home.playandroid.net.http.HttpUtils;

/**
 * Created by wuxiaoqi on 2018/4/17.
 * PlayAndroidApplication
 */

public class PlayAndroidApplication extends Application {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;

        String server_url = getResources().getString(R.string.server_url);
        HttpUtils.getInstance().init(this, server_url);
    }
}
