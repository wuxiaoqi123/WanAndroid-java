package com.welcome.home.playandroid.activity;

import android.content.Context;
import android.content.Intent;

import com.welcome.home.playandroid.R;
import com.welcome.home.playandroid.base.BaseActivity;

public class MainActivity extends BaseActivity {

    public static void startActivity(Context activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListener() {

    }
}
