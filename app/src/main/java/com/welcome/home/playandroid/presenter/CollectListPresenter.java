package com.welcome.home.playandroid.presenter;

import com.welcome.home.playandroid.bean.CollectList;
import com.welcome.home.playandroid.contract.CollectListContract;
import com.welcome.home.playandroid.net.api.WanAndroidApi;
import com.welcome.home.playandroid.net.callback.RxObserver;
import com.welcome.home.playandroid.net.exception.ResponeThrowable;
import com.welcome.home.playandroid.net.http.HttpUtils;
import com.welcome.home.playandroid.net.transformer.DefaultTransformer;

public class CollectListPresenter implements CollectListContract.Presenter {

    private CollectListContract.View mView;

    public CollectListPresenter(CollectListContract.View view) {
        this.mView = view;
    }

    @Override
    public void getCollectList() {
        HttpUtils.getInstance().getRetrofitClient().build(WanAndroidApi.class)
                .getCollectList()
                .compose(new DefaultTransformer<>())
                .subscribe(new RxObserver<CollectList>() {
                    @Override
                    public void onFail(ResponeThrowable ex) {
                        mView.showErrMsg(ex.message);
                    }

                    @Override
                    public void onSuccess(CollectList collectList) {
                        mView.setCollectList(collectList);
                    }
                });
    }
}
