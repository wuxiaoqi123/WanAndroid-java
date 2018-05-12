package com.welcome.home.playandroid.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.welcome.home.playandroid.R;
import com.welcome.home.playandroid.activity.BrowserActivity;
import com.welcome.home.playandroid.adapter.HomeBannerAdapter;
import com.welcome.home.playandroid.adapter.HomeListAdapter;
import com.welcome.home.playandroid.base.BaseFragment;
import com.welcome.home.playandroid.bean.BannerList;
import com.welcome.home.playandroid.bean.HomeList;
import com.welcome.home.playandroid.contract.HomeContract;
import com.welcome.home.playandroid.presenter.HomePresenter;
import com.welcome.home.playandroid.util.SmartRefreshLayoutUtils;

import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by wuxiaoqi on 2018/4/20.
 * 首页 Fragment
 */

public class HomeFragment extends BaseFragment implements HomeContract.View, BGABanner.Delegate {

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @BindView(R.id.refresh_content_smart_layout)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R.id.refresh_content_recyclerview)
    RecyclerView recyclerView;

    private HomePresenter presenter;

    private int page = 0;

    private HomeListAdapter mAdapter;

    private BGABanner headBanner;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(Bundle bundle) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter = new HomeListAdapter(getActivity()));
        View headerView = mAdapter.setHeaderView(R.layout.item_head_view);
        headBanner = headerView.findViewById(R.id.recommed_banner);
        HomeBannerAdapter homeBannerAdapter = new HomeBannerAdapter();
        headBanner.setDelegate(this);
        headBanner.setAdapter(homeBannerAdapter);
        presenter = new HomePresenter(this);
        initRefreshLayout();
    }

    private void initRefreshLayout() {
        SmartRefreshLayoutUtils.initRefreshLayoutBz(getActivity(), smartRefreshLayout);
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.loadBannerList();
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
        presenter.loadBannerList();
        presenter.loadHomeList(0);
    }

    @Override
    public void showErrMsg(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setBannnerList(List<BannerList> bannnerList) {
        smartRefreshLayout.finishRefresh();
        headBanner.setData(R.layout.item_image_banner, bannnerList, null);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setHomeList(HomeList homeList) {
        smartRefreshLayout.finishRefresh();
        mAdapter.setHomeList(homeList);
    }

    @Override
    public void addHomeList(HomeList homeList) {
        smartRefreshLayout.finishLoadMore();
        mAdapter.addHomeList(homeList);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, View itemView, Object model, int position) {
        if (model instanceof BannerList) {
            Intent intent = new Intent(itemView.getContext(), BrowserActivity.class);
            intent.setData(Uri.parse(((BannerList) model).getUrl()));
            itemView.getContext().startActivity(intent);
        }
    }
}
