package com.welcome.home.playandroid.presenter;

import com.welcome.home.playandroid.bean.ColumnList;
import com.welcome.home.playandroid.contract.ColumnContract;
import com.welcome.home.playandroid.net.api.WanAndroidApi;
import com.welcome.home.playandroid.net.callback.RxObserver;
import com.welcome.home.playandroid.net.exception.ResponeThrowable;
import com.welcome.home.playandroid.net.http.HttpUtils;
import com.welcome.home.playandroid.net.transformer.DefaultTransformer;

import java.util.List;

/**
 * Created by wuxiaoqi on 2018/5/4.
 * ColumnFragment-> P
 */

public class ColumnPresenter implements ColumnContract.Presenter {

    private ColumnContract.View mView;

    public ColumnPresenter(ColumnContract.View view) {
        this.mView = view;
    }

    @Override
    public void getColumnList() {
        HttpUtils.getInstance().getRetrofitClient().build(WanAndroidApi.class)
                .getColumnList()
                .compose(new DefaultTransformer<>())
                .compose(mView.bindToLife())
                .subscribe(new RxObserver<List<ColumnList>>() {
                    @Override
                    public void onFail(ResponeThrowable ex) {
                        mView.showErrMsg(ex.message);
                    }

                    @Override
                    public void onSuccess(List<ColumnList> columnLists) {
                        mView.setColumnList(columnLists);
                    }
                });
    }
}
