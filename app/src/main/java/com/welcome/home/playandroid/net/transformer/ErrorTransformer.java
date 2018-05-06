package com.welcome.home.playandroid.net.transformer;

import com.welcome.home.playandroid.net.exception.ExceptionHandler;
import com.welcome.home.playandroid.net.exception.ServerException;
import com.welcome.home.playandroid.net.response.HttpResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * Created by wuxiaoqi on 2018/4/26.
 */

public class ErrorTransformer<T> implements ObservableTransformer<HttpResponse<T>, T> {
    @Override
    public ObservableSource<T> apply(Observable<HttpResponse<T>> upstream) {
        return upstream.map(tHttpResponse -> {
            if (tHttpResponse.getErrorCode() != 0) {
                throw new ServerException(tHttpResponse.getErrorCode(), tHttpResponse.getErrorMsg());
            } else {
                return tHttpResponse.getData();
            }
        }).onErrorResumeNext(throwable -> {
            return Observable.error(ExceptionHandler.handleException(throwable));
        });
    }

    private ErrorTransformer() {
    }

    public static class Singleton {
        static ErrorTransformer instance = new ErrorTransformer();
    }

    public static <T> ErrorTransformer<T> getInstance() {
        return Singleton.instance;
    }
}
