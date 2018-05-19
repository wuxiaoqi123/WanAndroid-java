package com.welcome.home.playandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.welcome.home.playandroid.R;
import com.welcome.home.playandroid.base.BaseActivity;
import com.welcome.home.playandroid.bean.CollectList;
import com.welcome.home.playandroid.net.api.WanAndroidApi;
import com.welcome.home.playandroid.net.callback.RxObserver;
import com.welcome.home.playandroid.net.exception.ResponeThrowable;
import com.welcome.home.playandroid.net.http.HttpUtils;
import com.welcome.home.playandroid.net.transformer.DefaultTransformer;

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
        HttpUtils.getInstance().getRetrofitClient().build(WanAndroidApi.class)
                .getCollectList()
                .compose(new DefaultTransformer<>())
                .subscribe(new RxObserver<CollectList>() {
                    @Override
                    public void onFail(ResponeThrowable ex) {
                        Toast.makeText(CollectionActivity.this, ex.message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(CollectList collectList) {
                        Toast.makeText(CollectionActivity.this, "成功", Toast.LENGTH_SHORT).show();
                        Toast.makeText(CollectionActivity.this, collectList.getDatas().get(0).getLink(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void initListener() {

    }
}
