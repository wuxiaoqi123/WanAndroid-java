package com.welcome.home.playandroid.app;

import android.app.Application;
import android.content.Context;

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
    }
}
