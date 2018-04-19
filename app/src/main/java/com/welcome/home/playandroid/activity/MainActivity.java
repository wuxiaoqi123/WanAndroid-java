package com.welcome.home.playandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.welcome.home.playandroid.R;
import com.welcome.home.playandroid.base.BaseActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    public static void startActivity(Context activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.activity_main_toolbar)
    Toolbar toolbar;

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
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");
        }
    }

    @Override
    protected void initListener() {

    }
}
