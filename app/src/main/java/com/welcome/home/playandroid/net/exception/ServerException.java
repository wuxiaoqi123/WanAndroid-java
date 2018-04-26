package com.welcome.home.playandroid.net.exception;

/**
 * Created by wuxiaoqi on 2018/4/26.
 */

public class ServerException extends RuntimeException {

    public int code;

    public String message;

    public ServerException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
