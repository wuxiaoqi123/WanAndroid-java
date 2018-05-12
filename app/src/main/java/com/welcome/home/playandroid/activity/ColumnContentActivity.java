package com.welcome.home.playandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.welcome.home.playandroid.R;
import com.welcome.home.playandroid.adapter.ColumnContentAdapter;
import com.welcome.home.playandroid.base.BaseActivity;
import com.welcome.home.playandroid.bean.ColumnContentList;
import com.welcome.home.playandroid.contract.ColumnContentContract;
import com.welcome.home.playandroid.presenter.ColumnContentPresenter;
import com.welcome.home.playandroid.util.SmartRefreshLayoutUtils;

import butterknife.BindView;

public class ColumnContentActivity extends BaseActivity implements ColumnContentContract.View {

    private static final String KEY_CID = "key_cid";

    public static void startActivity(Context activity, int cid) {
        Intent intent = new Intent(activity, ColumnContentActivity.class);
        intent.putExtra(KEY_CID, cid);
        activity.startActivity(intent);
    }

    @BindView(R.id.refresh_content_smart_layout)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R.id.refresh_content_recyclerview)
    RecyclerView recyclerView;

    private ColumnContentAdapter mAdapter;

    private int c_id = 0;

    private int page = 0;

    private ColumnContentPresenter presenterImp;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_column_content;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected void initData() {
        c_id = getIntent().getIntExtra(KEY_CID, 0);
    }

    @Override
    protected void initViews() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null) {
            setSupportActionBar(findViewById(R.id.toolbar));
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        initRefreshLayout();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter = new ColumnContentAdapter(this));
        presenterImp = new ColumnContentPresenter(this);
        presenterImp.getColumnContent(page, c_id);
    }

    private void initRefreshLayout() {
        SmartRefreshLayoutUtils.initRefreshLayoutBz(this, smartRefreshLayout);
//        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                page++;
//                presenter.loadBannerList();
//                presenter.loadHomeList(page);
//            }
//
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                page = 0;
//                presenter.loadHomeList(page);
//            }
//        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void showErrMsg(String msg) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setColumnContent(ColumnContentList columnContent) {
        smartRefreshLayout.finishRefresh();
        page = 0;
        initSmartRefreshListener(columnContent);
        mAdapter.setHomeList(columnContent);
    }

    private void initSmartRefreshListener(ColumnContentList columnContent) {
        if (columnContent.getPageCount() > 1) {
            smartRefreshLayout.setEnableLoadMore(true);
            smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    page++;
                    if (page < columnContent.getPageCount()) {
                        presenterImp.getColumnContent(page, c_id);
                    } else {
                        Toast.makeText(ColumnContentActivity.this, "已经是最后一页", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(() -> smartRefreshLayout.finishLoadMore(), 300);
                    }
                }

                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    page = 0;
                    presenterImp.getColumnContent(page, c_id);
                }
            });
        } else {
            smartRefreshLayout.setEnableLoadMore(false);
            smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
                page = 0;
                presenterImp.getColumnContent(page, c_id);
            });
        }
    }

    @Override
    public void addColumnContent(ColumnContentList columnContentList) {
        smartRefreshLayout.finishLoadMore();
        mAdapter.addHomeList(columnContentList);
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
}
