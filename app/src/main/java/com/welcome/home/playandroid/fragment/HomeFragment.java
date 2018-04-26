package com.welcome.home.playandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.welcome.home.playandroid.R;
import com.welcome.home.playandroid.base.BaseFragment;
import com.welcome.home.playandroid.bean.HomeList;
import com.welcome.home.playandroid.contract.HomeContract;
import com.welcome.home.playandroid.presenter.HomePresenter;
import com.welcome.home.playandroid.util.SmartRefreshLayoutUtils;

import butterknife.BindView;

/**
 * Created by wuxiaoqi on 2018/4/20.
 * 首页 Fragment
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @BindView(R.id.refresh_content_smart_layout)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R.id.refresh_content_recyclerview)
    RecyclerView recyclerView;

    private HomePresenter presenter;

    private int page = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(Bundle bundle) {
        presenter = new HomePresenter(this);
        presenter.loadHomeList(0);
        initRefreshLayout();
    }

    private void initRefreshLayout() {
        SmartRefreshLayoutUtils.initRefreshLayoutBz(getActivity(), smartRefreshLayout);
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.loadHomeList(page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                presenter.loadHomeList(page);
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void lazyFetchData() {
    }

    @Override
    public void showErrMsg(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setHomeList(HomeList homeList) {
        smartRefreshLayout.finishRefresh();
        Toast.makeText(mContext, "加载成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addHomeList(HomeList homeList) {
        smartRefreshLayout.finishLoadMore();
        Toast.makeText(mContext, "加载更多", Toast.LENGTH_SHORT).show();

    }
}
