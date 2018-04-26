package com.welcome.home.playandroid.net.transformer;

import com.welcome.home.playandroid.net.response.HttpResponse;

import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wuxiaoqi on 2018/4/26.
 */

public class DefaultTransformer<T> implements ObservableTransformer<HttpResponse<T>, T> {
    @Override
    public ObservableSource<T> apply(io.reactivex.Observable<HttpResponse<T>> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .compose(ErrorTransformer.getInstance())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
