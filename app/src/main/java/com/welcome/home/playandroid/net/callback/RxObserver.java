package com.welcome.home.playandroid.net.callback;


import com.welcome.home.playandroid.net.exception.ResponeThrowable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by wuxiaoqi on 2018/4/26.
 */

public abstract class RxObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ResponeThrowable) {
            onFail((ResponeThrowable) e);
        } else {
            onFail(new ResponeThrowable(e, 1000));
        }
    }

    @Override
    public void onComplete() {

    }

    public abstract void onFail(ResponeThrowable ex);

    public abstract void onSuccess(T t);
}
