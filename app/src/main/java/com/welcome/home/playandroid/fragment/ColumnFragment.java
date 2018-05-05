package com.welcome.home.playandroid.fragment;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.welcome.home.playandroid.R;
import com.welcome.home.playandroid.base.BaseFragment;
import com.welcome.home.playandroid.bean.ColumnList;
import com.welcome.home.playandroid.contract.ColumnContract;
import com.welcome.home.playandroid.presenter.ColumnPresenter;

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

    private ColumnPresenter presenterImp;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_column;
    }

    @Override
    protected void initView(Bundle bundle) {
        presenterImp = new ColumnPresenter(this);
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
    public void setColumnList(ColumnList list) {
        Toast.makeText(mContext, "成功", Toast.LENGTH_SHORT).show();
    }
}
