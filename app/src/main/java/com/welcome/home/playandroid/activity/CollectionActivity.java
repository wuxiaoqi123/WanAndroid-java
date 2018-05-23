package com.welcome.home.playandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.welcome.home.playandroid.R;
import com.welcome.home.playandroid.adapter.CollectionListAdapter;
import com.welcome.home.playandroid.base.BaseActivity;
import com.welcome.home.playandroid.bean.CollectList;
import com.welcome.home.playandroid.contract.CollectListContract;
import com.welcome.home.playandroid.presenter.CollectListPresenter;

import butterknife.BindView;

public class CollectionActivity extends BaseActivity implements CollectListContract.View {

    @BindView(R.id.refresh_content_smart_layout)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R.id.refresh_content_recyclerview)
    RecyclerView recyclerView;

    private CollectionListAdapter mAdapter;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        smartRefreshLayout.setEnableAutoLoadMore(false);
        smartRefreshLayout.setEnableRefresh(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter = new CollectionListAdapter(this));
        CollectListPresenter presenterImp = new CollectListPresenter(this);
        presenterImp.getCollectList();
    }

    @Override
    protected void initListener() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setCollectList(CollectList collectList) {
        mAdapter.setHomeList(collectList);
    }

    @Override
    public void showErrMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
