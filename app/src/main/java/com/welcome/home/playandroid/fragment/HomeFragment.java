package com.welcome.home.playandroid.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.welcome.home.playandroid.R;
import com.welcome.home.playandroid.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by wuxiaoqi on 2018/4/20.
 * 首页 Fragment
 */

public class HomeFragment extends BaseFragment {

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @BindView(R.id.refresh_content_smart_layout)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R.id.refresh_content_recyclerview)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(Bundle bundle) {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void lazyFetchData() {

    }
}
