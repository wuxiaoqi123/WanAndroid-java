package com.welcome.home.playandroid.presenter;

import com.welcome.home.playandroid.bean.HomeList;
import com.welcome.home.playandroid.contract.HomeContract;
import com.welcome.home.playandroid.net.api.WanAndroidApi;
import com.welcome.home.playandroid.net.callback.RxObserver;
import com.welcome.home.playandroid.net.exception.ResponeThrowable;
import com.welcome.home.playandroid.net.http.HttpUtils;
import com.welcome.home.playandroid.net.transformer.DefaultTransformer;

/**
 * Created by wuxiaoqi on 2018/4/26.
 * HomeFragment -> P
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;

    public HomePresenter(HomeContract.View view) {
        this.mView = view;
    }

    @Override
    public void loadHomeList(int page) {
        HttpUtils.getInstance()
                .getRetrofitClient()
                .build(WanAndroidApi.class)
                .getHomeList(page)
                .compose(new DefaultTransformer<>())
                .compose(mView.bindToLife())
                .subscribe(new RxObserver<HomeList>() {
                    @Override
                    public void onFail(ResponeThrowable ex) {

                    }

                    @Override
                    public void onSuccess(HomeList homeList) {

                    }
                });

    }
}
