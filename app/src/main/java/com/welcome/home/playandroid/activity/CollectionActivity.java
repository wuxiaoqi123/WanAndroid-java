package com.welcome.home.playandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.welcome.home.playandroid.R;
import com.welcome.home.playandroid.base.BaseActivity;

import butterknife.BindView;

public class CollectionActivity extends BaseActivity {

    @BindView(R.id.refresh_content_smart_layout)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R.id.refresh_content_recyclerview)
    RecyclerView recyclerView;


    public static void startActivity(Context activity) {
        Intent intent = new Intent(activity, CollectionActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection;
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
