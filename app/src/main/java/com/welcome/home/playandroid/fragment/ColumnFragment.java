package com.welcome.home.playandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.welcome.home.playandroid.R;
import com.welcome.home.playandroid.adapter.ColumnExpandableListViewAdapter;
import com.welcome.home.playandroid.base.BaseFragment;
import com.welcome.home.playandroid.bean.ColumnList;
import com.welcome.home.playandroid.contract.ColumnContract;
import com.welcome.home.playandroid.presenter.ColumnPresenter;
import com.welcome.home.playandroid.util.SmartRefreshLayoutUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wuxiaoqi on 2018/4/20.
 * 栏目 Fragment
 */

public class ColumnFragment extends BaseFragment implements ColumnContract.View {

    public static ColumnFragment getInstance() {
        return new ColumnFragment();
    }

    @BindView(R.id.refresh_content_smart_layout)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R.id.fragment_column_expandable_listview)
    ExpandableListView expandableListView;

    private ColumnExpandableListViewAdapter mAdapter;

    private ColumnPresenter presenterImp;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_column;
    }

    @Override
    protected void initView(Bundle bundle) {
        initRefreshLayout();
        mAdapter = new ColumnExpandableListViewAdapter(getActivity());
        expandableListView.setAdapter(mAdapter);
        presenterImp = new ColumnPresenter(this);
    }

    private void initRefreshLayout() {
        SmartRefreshLayoutUtils.initRefreshLayoutBz(getActivity(), smartRefreshLayout);
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenterImp.getColumnList();
            }
        });
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void lazyFetchData() {
        presenterImp.getColumnList();
    }

    @Override
    public void showErrMsg(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setColumnList(List<ColumnList> list) {
        smartRefreshLayout.finishRefresh();
        mAdapter.setColumnLists(list);
    }
}
