package com.welcome.home.playandroid.presenter;

import com.welcome.home.playandroid.bean.ColumnContentList;
import com.welcome.home.playandroid.contract.ColumnContentContract;
import com.welcome.home.playandroid.net.api.WanAndroidApi;
import com.welcome.home.playandroid.net.callback.RxObserver;
import com.welcome.home.playandroid.net.exception.ResponeThrowable;
import com.welcome.home.playandroid.net.http.HttpUtils;
import com.welcome.home.playandroid.net.transformer.DefaultTransformer;

/**
 * Created by wuxiaoqi on 2018/5/12.
 * ColumnContentActivity -> P
 */

public class ColumnContentPresenter implements ColumnContentContract.Presenter {

    private ColumnContentContract.View mView;

    public ColumnContentPresenter(ColumnContentContract.View view) {
        this.mView = view;
    }

    @Override
    public void getColumnContent(int page, int c_id) {
        HttpUtils.getInstance()
                .getRetrofitClient()
                .build(WanAndroidApi.class)
                .getColumnContentList(page, c_id)
                .compose(new DefaultTransformer<>())
                .compose(mView.bindToLife())
                .subscribe(new RxObserver<ColumnContentList>() {
                    @Override
                    public void onFail(ResponeThrowable ex) {
                        mView.showErrMsg(ex.message);
                    }

                    @Override
                    public void onSuccess(ColumnContentList columnContentList) {
                        if (page == 0) {
                            mView.setColumnContent(columnContentList);
                        } else {
                            mView.addColumnContent(columnContentList);
                        }
                    }
                });
    }
}
