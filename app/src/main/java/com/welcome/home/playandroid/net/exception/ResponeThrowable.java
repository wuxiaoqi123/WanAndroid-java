package com.welcome.home.playandroid.net.exception;

/**
 * Created by wuxioqi on 2018/4/26.
 */

public class ResponeThrowable extends Exception {

    public int code;

    public String message;

    public ResponeThrowable(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public ResponeThrowable(Throwable throwable, int code, String message) {
        super(throwable);
        this.code = code;
        this.message = message;
    }
}
