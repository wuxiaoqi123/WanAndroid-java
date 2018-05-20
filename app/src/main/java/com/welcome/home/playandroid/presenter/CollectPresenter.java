package com.welcome.home.playandroid.presenter;

import com.welcome.home.playandroid.bean.CollectArticleList;
import com.welcome.home.playandroid.contract.CollectContract;
import com.welcome.home.playandroid.net.api.WanAndroidApi;
import com.welcome.home.playandroid.net.callback.RxObserver;
import com.welcome.home.playandroid.net.exception.ResponeThrowable;
import com.welcome.home.playandroid.net.http.HttpUtils;
import com.welcome.home.playandroid.net.transformer.DefaultTransformer;

public class CollectPresenter implements CollectContract.Presenter {

    private CollectContract.View mView;

    public CollectPresenter(CollectContract.View view) {
        this.mView = view;
    }

    @Override
    public void collectArticle(String title, String author, String link) {
        HttpUtils.getInstance().getRetrofitClient()
                .build(WanAndroidApi.class)
                .collectArticle(title, author, link)
                .compose(new DefaultTransformer<>())
                .compose(mView.bindToLife())
                .subscribe(new RxObserver<CollectArticleList>() {
                    @Override
                    public void onFail(ResponeThrowable ex) {
                        mView.showErrMsg(ex.message);
                    }

                    @Override
                    public void onSuccess(CollectArticleList collectArticleList) {
                        mView.collectSuccess(collectArticleList);
                    }
                });
    }
}
