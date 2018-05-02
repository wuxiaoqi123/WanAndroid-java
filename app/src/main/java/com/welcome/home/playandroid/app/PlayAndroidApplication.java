package com.welcome.home.playandroid.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;
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

        initTencentX5();
    }

    private void initTencentX5() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }
}
